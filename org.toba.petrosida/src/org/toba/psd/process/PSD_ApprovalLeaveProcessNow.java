package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Language;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.taowi.hcm.core.model.MEmployee;
import org.toba.psd.model.X_HC_EmployeeLeaveBalance;
import org.toba.psd.model.X_HC_LeaveRequest;
import org.toba.psd.model.X_HC_LeaveType;

/**
 * 
 * @author Kevin Yulianto
 * Process for approval Leave Process now 
 * Manager role only
 */
public class PSD_ApprovalLeaveProcessNow extends SvrProcess{
	
	
	private String p_Status = "";
	private int HC_LeaveRequest_ID = 0;
	//private String p_RejectReason = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if(name.equals("Status"))
				p_Status 	   = para[i].getParameterAsString();
			/*else if(name.equals("RejectReason"))
				p_RejectReason = para[i].getParameterAsString();*/
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		HC_LeaveRequest_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(p_Status == null || p_Status.length() <= 0)
			throw new AdempiereException("Error: Status is not selected");
		
		if(HC_LeaveRequest_ID <= 0)
			throw new AdempiereException("Error: Leave Request is not selected");
		
		/*
		 * @Comment : Ask Jorvan why wanted to be more dynamic and logic
		 * due document leave wanted to be more logic and dynamic. 
		 * giving the access to manager to accepted and rejected the document
		 * 1. if document already accepted, document can be rejected
		 * 2. if document already rejected, document can't be accepted, then employee must make new request leave
		 */
		
		X_HC_LeaveRequest leaveReq 	= new X_HC_LeaveRequest(getCtx(), HC_LeaveRequest_ID, get_TrxName());
		X_HC_LeaveType type		   	= new X_HC_LeaveType(getCtx(), leaveReq.getHC_LeaveType_ID(), get_TrxName());
		MEmployee employee 			= new MEmployee(getCtx(), leaveReq.getHC_Employee_ID(), get_TrxName());
		
		//declaration
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calToday = Calendar.getInstance();
		calToday.setTimeInMillis(System.currentTimeMillis());
		Timestamp WorkStartDate     = employee.getHC_WorkStartDate(); 
		Calendar calWorkStartDate   = Calendar.getInstance();
		calWorkStartDate.setTime(sdf.parse(WorkStartDate.toString()));
		int date = calWorkStartDate.get(Calendar.DATE);
		int month = calWorkStartDate.get(Calendar.MONTH);
		//declaration
		Timestamp leaveStartTime = null; //HC_LeaveStart Date&Time
		Timestamp leaveEndTime   = null; //HC_LeaveEnd Date&Time
		
		int leaveDays = 0; 
		int saldoCuti = 0;
		int Allocated = 0;
		int usedCuti  = 0;
		int flag      = 0;// for checking the leaves have the quota of leave
		int leavePeriodFrom = 0;
		int leavePeriodTo = 0;
		String whereClause = "";
		List<X_HC_EmployeeLeaveBalance> leaves = new ArrayList<X_HC_EmployeeLeaveBalance>();

		X_HC_LeaveType leaveType = new X_HC_LeaveType(getCtx(), leaveReq.getHC_LeaveType_ID(), get_TrxName());
		
		if(p_Status.equals("Rej")) {
			if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Rejected))
				throw new AdempiereException("Error: already rejected");
			else if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Cancelled))
				throw new AdempiereException("Error: already cancelled");
			/*
			else if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Accepted)){
				//document is accepted then return the quota of leave back to the employee leave balance
				leaveDays = 0;
				saldoCuti = 0;
				Allocated = 0;
				usedCuti  = 0;
				flag      = 0;
				
				//get Employee Leave Balance
				if(leaveType.getValue().equals("CT") || leaveType.getValue().equals("CB") || leaveType.getValue().equals("CB1")){
					whereClause = "HC_Employee_ID = ? AND HC_LeaveType_ID = ?";
					leaves = new ArrayList<X_HC_EmployeeLeaveBalance>();
					leaves = new Query(getCtx(), X_HC_EmployeeLeaveBalance.Table_Name, whereClause, get_TrxName())
							.setParameters(leaveReq.getHC_Employee_ID(), leaveReq.getHC_LeaveType_ID())
							.setOrderBy(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_EmployeeLeaveBalance_ID)
							.list();
					
					int i = 0;
					for(X_HC_EmployeeLeaveBalance leave: leaves) {
						i++;
						if(leaveType.getValue().equals("CT")){
							if(i == 1){//first CT
								usedCuti = leave.getUsedCutiTahunan() - Integer.valueOf(leaveReq.get_Value("LeaveBalanceYear_1").toString());
								saldoCuti = leave.getSaldoCutiTahunan() + Integer.valueOf(leaveReq.get_Value("LeaveBalanceYear_1").toString());
							}else if(i == 2){ //second CT
								usedCuti = leave.getUsedCutiTahunan() - Integer.valueOf(leaveReq.get_Value("LeaveBalanceYear_2").toString());
								saldoCuti = leave.getSaldoCutiTahunan() + Integer.valueOf(leaveReq.get_Value("LeaveBalanceYear_2").toString());
							}
							leave.setSaldoCutiTahunan(saldoCuti);
							leave.setUsedCutiTahunan(usedCuti);
							leave.setAllocatedAmt(saldoCuti);
						}
						else if(leaveType.getValue().equals("CB1") || leaveType.getValue().equals("CB2"))
							if(i == 1){//to make sure that CB1 or CB2 is only one that gotten
								usedCuti = leave.getUsedCutiTahunan() - Integer.valueOf(leaveReq.get_Value("LeaveBalanceYear_1").toString());
								saldoCuti = leave.getSaldoCutiTahunan() + Integer.valueOf(leaveReq.get_Value("LeaveBalanceYear_1").toString());
								leave.setSaldoCutiBesar(saldoCuti);
								leave.setUsedCutiBesar(usedCuti);
								leave.setAllocatedAmt(saldoCuti);
							}
						leave.saveEx();
					}//endFor
				}
			}//endLeaveStatus Acc*/
		}else{
			//if status = "Acc"
			if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Accepted))
				throw new AdempiereException("Error: already Accepted");
			else if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Cancelled))
				throw new AdempiereException("Error: already cancelled");
			else if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Rejected))
				throw new AdempiereException("Error: already rejected");
			/*
			else{
				//requested document
				leaveDays = 0;
				saldoCuti = 0;
				Allocated = 0;
				usedCuti  = 0;
				flag      = 0; //to check if the leave has the quota or not
				
				if(leaveType.getValue().equals("CT") || 
				   leaveType.getValue().equals("CB1") || 
				   leaveType.getValue().equals("CB")){
					leaveDays = leaveReq.getLeaveDays();
					
					//get The leave balance
					whereClause = "HC_Employee_ID = ? AND HC_LeaveType_ID = ?";
					leaves = new ArrayList<X_HC_EmployeeLeaveBalance>();
					leaves = new Query(getCtx(), X_HC_EmployeeLeaveBalance.Table_Name, whereClause, get_TrxName())
							.setParameters(leaveReq.getHC_Employee_ID(), leaveReq.getHC_LeaveType_ID())
							.setOrderBy(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_EmployeeLeaveBalance_ID)
							.list();
					
					if(leaves.size() <= 0)
						throw new AdempiereException("Error: Employee " + employee.getName() + " doesn't have leave");
					
					BigDecimal totalLeaveBalance = new BigDecimal(leaveReq.get_Value("LeaveBalance").toString());
					
					//if TotalLeaveBalance is more than leave days 
					if(totalLeaveBalance.compareTo(new BigDecimal(leaveReq.getLeaveDays())) < 1){
						throw new AdempiereException("Error: Total Leave is less than leave days");
					}
					
					leavePeriodFrom = leaveReq.get_ValueAsInt("LeavePeriodFrom");
					leavePeriodTo = leaveReq.get_ValueAsInt("LeavePeriodTo");
					
					//get time for period from and To  (using date and month from Employee Work Start Date)
					Timestamp timePeriodFrom = Timestamp.valueOf(leavePeriodFrom + "-" + month + "-" + date + " " + "00:00:00.0");
					Timestamp timePeriodTo = Timestamp.valueOf(leavePeriodTo + "-" + month + "-" + date + " " + "00:00:00.0");	
					timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
					Timestamp tempTimePeriod = null;
					
					//get Leave Start and End Time
					leaveStartTime = leaveReq.getHC_LeaveStartDate();
					leaveEndTime   = leaveReq.getHC_LeaveEndDate();
					
					
					//check if type is CT or CB1, or CB and if leave start and end time outside of period valid of leave, then throw error
					if( CheckingLeaveStartandEndInPeriodOfLeave(leaveStartTime, leaveEndTime, timePeriodFrom, timePeriodTo)) {
						//if type CB1, CB2. and leave Start time after 
						if(leaveType.getValue().equals("CB1") ||  leaveType.getValue().equals("CB")) {
							GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
							cal.setTime(timePeriodTo);
							cal.set(Calendar.HOUR_OF_DAY, 0);
							cal.set(Calendar.MINUTE, 0);
							cal.set(Calendar.SECOND, 0);
							cal.set(Calendar.MILLISECOND, 0);
							cal.add(Calendar.YEAR, -2);
							tempTimePeriod = new Timestamp (cal.getTimeInMillis());
							if(leaveStartTime.after(tempTimePeriod)) {
								throw new AdempiereException("Error: type leave "+leaveType.getName()+" only active for 1 year to request");
							}
						}
						
						throw new AdempiereException("Error: request leave start and end time not valid or not in date of period");
					}
					
					
					int i = 0;
					for(X_HC_EmployeeLeaveBalance leave : leaves){
						i++;
						leaveType = new X_HC_LeaveType(getCtx(), leave.getHC_LeaveType_ID(), get_TrxName());
						
						if(leaveType.getValue().equals("CT")){
							saldoCuti = leave.getSaldoCutiTahunan();
							usedCuti  = leave.getUsedCutiTahunan();
							if(saldoCuti == 0){
								flag++;
							}else{
								if(leaveDays > saldoCuti){
									leaveDays = leaveDays - saldoCuti;
									if(i == 1){
										leave.set_ValueOfColumn("LeaveBalanceYear_1",saldoCuti);
									}else if(i == 2){
										leave.set_ValueOfColumn("LeaveBalanceYear_2", saldoCuti);
									}
									usedCuti  = usedCuti + saldoCuti;
									saldoCuti = 0;
									Allocated = saldoCuti;
								}else if(leaveDays <= saldoCuti){
									saldoCuti = saldoCuti - leaveDays;
									if(i == 1){
										leave.set_ValueOfColumn("LeaveBalanceYear_1",leaveDays);
									}else if(i == 2){
										leave.set_ValueOfColumn("LeaveBalanceYear_2", leaveDays);
									}
									usedCuti  = usedCuti + leaveDays;
									Allocated = saldoCuti;
									leaveDays = 0;
								}
								
								leave.set_ValueOfColumn("SaldoCutiTahunan"	, (Integer)saldoCuti);
								leave.set_ValueOfColumn("UsedCutiTahunan" 	, (Integer)usedCuti);
								leave.set_ValueOfColumn("AllocatedAmt"		, Allocated);
							}//endElse
						}else if(leaveType.getValue().equals("CB1") || leaveType.getValue().equals("CB")){
							saldoCuti = leave.getSaldoCutiBesar();
							usedCuti  = leave.getUsedCutiBesar();
							if(saldoCuti == 0){
								flag++;
							}else{
								if(leaveDays > saldoCuti){
									leaveDays = leaveDays - saldoCuti;
									leave.set_ValueOfColumn("LeaveBalanceYear_1",saldoCuti);
									usedCuti  = usedCuti + saldoCuti;
									Allocated = saldoCuti;
									saldoCuti = 0;
								}else if(leaveDays <= saldoCuti){
									saldoCuti = saldoCuti - leaveDays;
									leave.set_ValueOfColumn("LeaveBalanceYear_1",leaveDays);
									usedCuti  = usedCuti + leaveDays;
									Allocated = saldoCuti;
									leaveDays = 0;
								}
								leave.set_ValueOfColumn("SaldoCutiTahunan"	, (Integer)saldoCuti);
								leave.set_ValueOfColumn("UsedCutiTahunan" 	, (Integer)usedCuti);
								leave.set_ValueOfColumn("AllocatedAmt"		, Allocated);
							}//endElse
						}//endElseIf
						leave.saveEx();
					}//endFor
					
					if(flag == leaves.size())
						throw new AdempiereException("Employee " + employee.getName() + " Doesn't have saldo " + type.getName());
				}
			}//end Else for requested document
			*/
		}//endElse
		leaveReq.setStatus(p_Status);
		leaveReq.saveEx();
		return "Success Process";
	}//DoIt
		
	/**
	 * 
	 * @param leaveStartTime
	 * @param leaveEndTime
	 * @param timePeriodFrom
	 * @param timePeriodTo
	 * @return
	 * Check if leave start and end time before timePeriodFrom and TimePeriodTo
	 * Can't request leave before 
	 */
	private boolean CheckingLeaveStartandEndInPeriodOfLeave(Timestamp leaveStartTime, Timestamp leaveEndTime, Timestamp timePeriodFrom, Timestamp timePeriodTo){
		if(leaveStartTime.before(timePeriodFrom) || leaveStartTime.after(timePeriodTo) && 
				(leaveEndTime.before(timePeriodFrom) || leaveEndTime.after(timePeriodTo))) {
			return true;
		}else{
			return false;
		}
	}
}//endClass

