package org.toba.psd.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.toba.psd.model.X_HC_JobLevel;
import org.toba.psd.model.X_HC_RequestOvertime;
import org.toba.psd.model.X_HC_Shift;

/**
 * @author kevin Callout for HC_RequestOvertime
 */
public class PSD_CalloutRequestOvertime extends CalloutEngine implements
		IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {

		//native
		if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_DateTrx))
			return CalloutTransactionDate(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_StartTime))
			return CalloutTotalOvertimeHour(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_EndTime))
			return CalloutTotalOvertimeHour(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_StartDate))
			return CalloutDate(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_EndDate))
			return CalloutDate(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_IsShift))
			return CalloutIsShift(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_ShiftEnds))
			return CalloutShiftEnds(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestOvertime.COLUMNNAME_HC_Shift_ID))
			return CalloutShift(ctx, WindowNo, mTab, mField, value);
		
		//custom
		else if (mField.getColumnName().equals("WorktimeDate"))
			return CalloutWorktimeDate(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals("IsHoliday"))
			return CalloutIsHoliday(ctx, WindowNo, mTab, mField, value);
		return "";
	}// start
	
	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	private String CalloutShiftEnds(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_StartTime, value);
		
		return "";
	}//CalloutWorktimeDate
	
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
	private String CalloutWorktimeDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		Timestamp WorkTimeDate = (Timestamp) value;
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_StartDate, WorkTimeDate);
		
		return "";
	}//CalloutWorktimeDate
	
	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutTransactionDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		Timestamp transaction = new Timestamp(System.currentTimeMillis());
		
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_DateTrx, transaction);
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_TimeTrx, transaction);
		
		return "";
	}//CalloutTransactionDate
	
	
	/**
	 * Callout Shift start and end time
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutShift(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		int HC_Shift_ID = (Integer) value;
		
		X_HC_Shift shift = new X_HC_Shift(ctx, HC_Shift_ID, null);
		
		boolean isShift = mTab.getValueAsBoolean(X_HC_RequestOvertime.COLUMNNAME_IsShift);
		
		if(isShift) {
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_ShiftStart, shift.getShiftStart());
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_ShiftEnds, shift.getShiftEnds());
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_StartTime, shift.getShiftEnds());
		}
		
		return "";
	}//CalloutShift
	
	private String CalloutDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		Timestamp startDate = null;
		Timestamp endDate = null;
		
		if(mField.getColumnName().equals(X_HC_RequestOvertime.COLUMNNAME_StartDate)){
			if(mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_EndDate) == null) {
				return "";
			}else {
				endDate =Timestamp.valueOf(mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_EndDate).toString());
			}
			startDate = (Timestamp)value;
		}else if(mField.getColumnName().equals(X_HC_RequestOvertime.COLUMNNAME_EndDate)) {
			if(mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_StartDate) == null) {
				return "";
			}else {
				startDate =Timestamp.valueOf(mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_StartDate).toString());
			}
			endDate = (Timestamp)value;
		}
		
		if(endDate.before(startDate)){
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_StartDate, null);
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_EndDate, null);
			mTab.fireDataStatusEEvent("Error: End Date can not before than startDate","",false);
			return "";
		}
		
		CalculateHour(ctx, windowNo, mTab, mField, value);
		return "";
	}//CalloutDate
	
	/**
	 * Callout Shift Start,Ends and HC_Shift_ID null
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Sucess
	 */
	private String CalloutIsShift(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_ShiftStart, null);
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_ShiftEnds	, null);
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_HC_Shift_ID, null);
		
		
		return "";
	}//CalloutIsShift
	
	private String CalloutIsHoliday(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		Boolean isHoliday = Boolean.valueOf(value.toString());
		
		if(isHoliday.booleanValue()) {
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_IsShift, false);
			CalloutIsShift(ctx, windowNo, mTab, mField, mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_IsShift));
		}
		
	
		return "";
	}//CalloutWorktimeDate
	
	/**
	 * Callout for Total Over time Hour 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * empty String if success
	 */
	private String CalloutTotalOvertimeHour(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		Timestamp start = null;
		Timestamp end 	= null;
		int startHour 	= 0;
		int startMin 	= 0;
		
		int endHour 	= 0;
		int endMin 		= 0;
		
		
		
		if(mField.getColumnName().equals(X_HC_RequestOvertime.COLUMNNAME_StartTime)){
			
			//checkMatchwithShiftEnd
	
			start 		= (Timestamp)value;
			startHour 	= Integer.parseInt(start.toString().substring(11, 13));
			startMin  	= Integer.parseInt(start.toString().substring(14, 16));
			if(mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_EndTime) == null)
				return "";
			else 
				end 	= (Timestamp)mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_EndTime);
			
			endHour   	= Integer.parseInt(start.toString().substring(11, 13));
			endMin    	= Integer.parseInt(start.toString().substring(14, 16));
			
		}else if(mField.getColumnName().equals(X_HC_RequestOvertime.COLUMNNAME_EndTime)){
			end   		= (Timestamp)value;
			endHour   	= Integer.parseInt(end.toString().substring(11, 13));
			endMin    	= Integer.parseInt(end.toString().substring(14, 16));
			
			if(mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_StartTime) == null)
				return "";
			else
				start 	= (Timestamp)mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_StartTime);
			
			startHour 	= Integer.parseInt(start.toString().substring(11, 13));
			startMin  	= Integer.parseInt(start.toString().substring(14, 16));
		}
		
		Timestamp startDate = (Timestamp)mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_StartDate);
		Timestamp endDate   = (Timestamp) mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_EndDate);
		Timestamp tempStartDate = Timestamp.valueOf(startDate.toString().substring(0,10)+ " "+ start.toString().subSequence(11, 17)+"00.0");
		Timestamp tempEndDate   = Timestamp.valueOf(endDate.toString().substring(0,10)+" " + end.toString().subSequence(11, 17)+"00.0");
		
		
		//check start time matches with start time and if not matches, then start time = shiftends
		if(mField.getColumnName().equals(X_HC_RequestOvertime.COLUMNNAME_StartTime)) {
			Timestamp ShiftEnds = (Timestamp) mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_ShiftEnds);
			if(!mTab.getValueAsBoolean("IsHoliday")) {
				if(!tempStartDate.equals(ShiftEnds)){
					mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_StartTime, ShiftEnds);
					mTab.fireDataStatusEEvent("Error: Exit hour doesn't match with start time", "", false);
					return "";
				}
			}
		}
		
		if(tempEndDate.before(tempStartDate)){
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_TotalOvertimeHour, null);
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_StartDate, null);
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_StartTime, null);
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_EndDate,null);
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_EndTime,null);
			mTab.fireDataStatusEEvent("Error: End Date and Time can't be before Start Date and Time", "", false);
			return "";
		}
		
		//set Request overtime
		double hour 	= endHour - startHour;
		double minute 	= ((double)(endMin - startMin));
		double temp 	= hour + minute /60.0;
		if(temp < 0)
			temp 		= temp + 24;
		
		BigDecimal total 	= new BigDecimal(temp);
		total 				= total.setScale(0, RoundingMode.DOWN);
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_TotalOvertimeHour, total );
		
		CalculateHour(ctx, windowNo, mTab, mField, value);
		return "";
	}//CalloutTotalOvertimeHour
	
	
	private void CalculateHour(Properties ctx, int windowNo,
			GridTab mTab, GridField mField, Object value){
		BigDecimal totalOvertimeHour = (BigDecimal)mTab.getValue(X_HC_RequestOvertime.COLUMNNAME_TotalOvertimeHour);
		int totalOvertime = totalOvertimeHour.intValue();
		if(totalOvertime >= 1) 
			mTab.setValue("HC_1NormalHour", Env.ONE);
		if(totalOvertime > 1)
			mTab.setValue("HC_2NormalHour", totalOvertimeHour.subtract(Env.ONE));
		if(mTab.getValueAsBoolean("IsHoliday")){
			if(totalOvertime < 8 ){
				mTab.setValue("HC_8HolidayHour", totalOvertimeHour);
			}else if(totalOvertime >= 8){
				mTab.setValue("HC_8HolidayHour", new BigDecimal(8));
			}
			
			if(totalOvertime >= 9){
				mTab.setValue("HC_9HolidayHour", Env.ONE);
			}
			
			if(totalOvertime > 9) {
				mTab.setValue("HC_10HolidayHour", totalOvertimeHour.subtract(new BigDecimal(9)));
			}
		}
	}
	/**
	 * Calling out HC_Job_ID, HC_Org_ID,HC_Manager_ID from HC_EmployeeJob
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return Empty String if Success
	 */
	private String CalloutEmployeeID(Properties ctx, int windowNo,
			GridTab mTab, GridField mField, Object value) {

		if (value == null)
			return "";

		int HC_Employee_ID = (Integer) value;
		MEmployee employee = new MEmployee(ctx, HC_Employee_ID, null);
		int HC_EmployeeJob_ID = employee.getActiveSequenceOneEmployeeJob();

		if (HC_EmployeeJob_ID <= 0){
			mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_HC_Employee_ID, null);
			mTab.fireDataStatusEEvent("Error: Employee doesn't have Sequence 1 Employee Job","",false);
		}

		MEmployeeJob employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);

		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_HC_Org_ID, employeeJob.getHC_Org_ID());
		mTab.setValue(X_HC_RequestOvertime.COLUMNNAME_HC_Job_ID, employeeJob.getHC_Job_ID());
		
		//getManagerWithLevelBelowDireksi
		MJob job = new MJob(ctx, employeeJob.getHC_Job_ID(), null);
		int JobReportTo_ID = job.getHC_JobReportTo_ID();
		MJob jobReportTo = new MJob(ctx, JobReportTo_ID, null);
		X_HC_JobLevel jobLevel = new X_HC_JobLevel(ctx, jobReportTo.getHC_JobLevel_ID(), null);
		if(!jobLevel.get_ValueAsBoolean("IsDireksi")) {
			String sql = "SELECT "+MEmployeeJob.COLUMNNAME_HC_Employee_ID+" FROM "+MEmployeeJob.Table_Name+" WHERE "+MEmployeeJob.COLUMNNAME_IsActive+"='Y' AND "
					+ MEmployeeJob.COLUMNNAME_HC_Job_ID+"=? AND "
					+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
			int manager_ID = DB.getSQLValue(null, sql, JobReportTo_ID);
			MEmployee m_emp_manager = new MEmployee(ctx, manager_ID, null);
			if(manager_ID > 0 && !m_emp_manager.get_ValueAsBoolean("isSanctions"))
				mTab.setValue("HC_Manager_ID", manager_ID);		
		}
	
		CalloutTransactionDate(ctx, windowNo, mTab, mField, value);
		
		return "";
	}// CalloutEmployeeID

}// endClass
