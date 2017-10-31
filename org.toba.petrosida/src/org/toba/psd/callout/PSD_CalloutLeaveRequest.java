package org.toba.psd.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.toba.psd.model.X_HC_EmployeeLeaveBalance;
import org.toba.psd.model.X_HC_LeaveRequest;
import org.toba.psd.model.X_HC_LeaveType;


/**
 * 
 * @author Kevin Yulianto
 * Callout for HC_LeaveRequest
 */
public class PSD_CalloutLeaveRequest extends CalloutEngine implements IColumnCallout {
	
	private final static String Contract = "Contract";
	private final static String Permanent = "Permanent";
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_LeaveDays))
			return CalloutEndDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals("NonBusinessDay"))
			return CalloutEndDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate))
			return CalloutEndDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveType_ID))
			return CalloutLeaveType(ctx, WindowNo, mTab, mField, value);
		return "";
	}//start
	
	/**
	 *
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if success
	 */
	private String CalloutEndDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null){
			return "";
		}
		
		Timestamp endDate = null;
		Timestamp startDate = null;
		int leaveDays = 0;
		int NonBusinessdays = 0;
		
		if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_LeaveDays)){
			if(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate) == null){
				return "";
			}
			
			startDate = Timestamp.valueOf(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate).toString());
			leaveDays = Integer.valueOf(value.toString());
			NonBusinessdays = Integer.valueOf(mTab.getValue("NonBusinessDay").toString());
			if(leaveDays > 12){
				mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_LeaveDays,0);
				mTab.fireDataStatusEEvent("Error: Leave request can't be more than 12","", false);
			}
		}else if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate)){
			startDate = Timestamp.valueOf(value.toString());
			leaveDays = Integer.valueOf(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_LeaveDays).toString());
			NonBusinessdays = Integer.valueOf(mTab.getValue("NonBusinessDay").toString());
		}else if(mField.getColumnName().equals("NonBusinessDay")){
			if(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate) == null){
				return "";
			}
			startDate = Timestamp.valueOf(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate).toString());
			leaveDays = Integer.valueOf(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_LeaveDays).toString());
			NonBusinessdays = Integer.valueOf(value.toString());
		}
		endDate = TimeUtil.addDays(startDate, (leaveDays + NonBusinessdays)-1);
		mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveEndDate, endDate);
		
		return "";
	}
	
	/**
	 * Calling out leave end from start date and leave days after being added
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * empty string if success
	 */
	private String CalloutLeaveType(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		String CutiBesarPertama = "CB1";
		String CutiBesar = "CB";
		String CutiTahunan = "CT";
		if(value == null)
			return "";
		
		int HC_LeaveType_ID = (Integer)value;
		X_HC_LeaveType leaveType = new X_HC_LeaveType(ctx, HC_LeaveType_ID, null);
		int HC_Employee_ID 	= (Integer)mTab.getValue(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_Employee_ID);
		
		if(leaveType.getValue().equals(CutiBesarPertama) || 
				leaveType.getValue().equals(CutiBesar) ||
				leaveType.getValue().equals(CutiTahunan)) {
			int[] EmployeeLeaveBalances = null;
			String sql = X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID+"=? AND "
					+ X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_Employee_ID+"=? AND "
					+ X_HC_EmployeeLeaveBalance.COLUMNNAME_IsActive+"='Y'";
			EmployeeLeaveBalances = new Query(ctx, X_HC_EmployeeLeaveBalance.Table_Name, sql, null)
									.setParameters(new Object[]{HC_LeaveType_ID, HC_Employee_ID})
									.setOrderBy(X_HC_EmployeeLeaveBalance.COLUMNNAME_LeaveBalancePeriod)
									.getIDs();
			
			if(EmployeeLeaveBalances.length <= 0) {
				mTab.setValue(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID, null);
				mTab.setValue("LeaveBalance", 0);
				mTab.fireDataStatusEEvent("Error: Employee doesn't have leave type "+ leaveType.getName(), "", false);
				return "";
			}else{
				String periodFrom = null;
				String periodTo = null;
				int leaveQty = 0;
				for(int i = 0 ; i < EmployeeLeaveBalances.length; i++) {
					X_HC_EmployeeLeaveBalance employeeLeaveBalance = new X_HC_EmployeeLeaveBalance(ctx, EmployeeLeaveBalances[i], null);
					leaveQty = leaveQty + employeeLeaveBalance.getAllocatedAmt();
					if(i==0){
						periodFrom = employeeLeaveBalance.getLeaveBalancePeriod().substring(0,4);
						periodTo = employeeLeaveBalance.getLeaveBalancePeriod().substring(13, 17);
					}else if(i > 0){
						periodTo = employeeLeaveBalance.getLeaveBalancePeriod().substring(13,17);
					}
				}
				mTab.setValue("LeaveBalance", leaveQty);
				mTab.setValue("LeavePeriodFrom", periodFrom);
				mTab.setValue("LeavePeriodTo" ,periodTo);
				return "";
			}
		}else{
			//cuti type lain - lain 
			mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_LeaveBalance, leaveType.getLeaveDays()); //in Days
			return "";
		}
	}//CalloutLeaveType
	
	
	/**
	 * Calling out leave end from start date and leave days after being added
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * empty string if success
	 */
	/*private String CalloutLeaveDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		Timestamp startDate = null;
		Timestamp endDate	= null;
		Timestamp today = new Timestamp(System.currentTimeMillis());
		
		if(value == null) {
			return "";
		}
		
		if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate)) {
			if(value != null) {
				startDate 			= (Timestamp)value;	
			}
			if(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveEndDate) == null){
				return "";
			}
			
			//if manager has already allowed (dynamic case)
			if(mTab.getValueAsBoolean(X_HC_LeaveRequest.COLUMNNAME_IsAllowed) == false){
				if((startDate.before(today) || startDate.equals(today))){
					mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate, null);
					mTab.fireDataStatusEEvent("Error: leave End Date before or equals Leave Start Date", "", false);
					return "";
				}
			}
			
			endDate = (Timestamp)mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveEndDate);
		}else if(mField.getColumnName().equals(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveEndDate)) {
			if(value != null) {
				endDate 		    = (Timestamp)value;
			}
			if(mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate) == null){
				return "";
			}
			
			//if manager has already allowed (dynamic case)
			if(mTab.getValueAsBoolean(X_HC_LeaveRequest.COLUMNNAME_IsAllowed) == false){
				if((endDate.before(today) || endDate.equals(today))){
					mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate, null);
					mTab.fireDataStatusEEvent("Error: leave End Date before or equals Leave Start Date", "", false);
					return "";
				}
			}
			
			startDate = (Timestamp)mTab.getValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveStartDate);
		}
		
		int days = TimeUtil.getDaysBetween(startDate, endDate)+1;
		
		if(days <= 0){
			mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_HC_LeaveEndDate, null);
			mTab.fireDataStatusEEvent("Error: leave End Date before or equals Leave Start Date", "", false);
			return "";
		}
		//mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_LeaveDays, (Integer)days);
		
		return "";
	}//CalloutLeaveDate*/
	
	/**
	 * Calling out job, HC_Org, and Manager from employee job
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty string if success
	 */
	private String CalloutEmployeeID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		int HC_Employee_ID = (Integer) value;
		
		MEmployee employee = new MEmployee(ctx, HC_Employee_ID, null);
		
		String sql = "SELECT "+MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID+" FROM "+MEmployeeJob.Table_Name+" WHERE "+MEmployeeJob.COLUMNNAME_HC_Employee_ID+"=? "
				+ "AND "+MEmployeeJob.COLUMNNAME_SeqNo+"=1";
		int HC_EmployeeJob_ID = DB.getSQLValue(null, sql, employee.getHC_Employee_ID());
		
		if(HC_EmployeeJob_ID <= 0) {
			mTab.setValue(MEmployeeJob.COLUMNNAME_HC_Employee_ID, null);
			mTab.fireDataStatusEEvent("Error: Employee doesn't have job sequence 1", "", false);
			return "";
		}
		
		MEmployeeJob employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
		X_HC_EmployeeClass employeeClass = new X_HC_EmployeeClass(ctx, employeeJob.getHC_EmployeeClass_ID(), null);
		
		//@Comment : 
		//cuti untuk karyawan tetap dan kontrak (consultant).
		//pkwt, bp tidak ada cuti.
		//outsource tidak dibahas di ESS.
		if(!(employeeClass.getValue().equals(Permanent) || employeeClass.getValue().equals(Contract))) {
			mTab.setValue(MEmployeeJob.COLUMNNAME_HC_Employee_ID, null);
			mTab.fireDataStatusEEvent("Error: Employee is not permanent or contract", "", false);
			return "";
		}
		
		Timestamp timeToday = new Timestamp(System.currentTimeMillis());
		mTab.setValue(X_HC_LeaveRequest.COLUMNNAME_HC_Job_ID		, employeeJob.getHC_Job_ID());
		mTab.setValue("HC_Org_ID"									, employeeJob.getHC_Org_ID());
		mTab.setValue("HC_WorkStartDate"							, employee.getHC_WorkStartDate());
		mTab.setValue("DateTrx", timeToday);
		mTab.setValue("TimeTrx", timeToday);
		
		return "";
	}//CalloutEmployeeID

}//endClass
