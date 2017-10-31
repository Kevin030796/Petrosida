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
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.toba.psd.model.X_HC_EmployeeLeaveBalance;
import org.toba.psd.model.X_HC_LeaveRequest;
import org.toba.psd.model.X_HC_LeaveType;

public class PSD_ConfirmLeaveProcessNow extends SvrProcess{
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
			/*else if(name.equals("RejectReason"))
				p_RejectReason = para[i].getParameterAsString();*/
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		HC_LeaveRequest_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
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
		if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Confirmed))
			throw new AdempiereException("Error: Already confirmed");
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
			whereClause = "HC_Employee_ID = ? AND HC_LeaveType_ID = ? AND IsActive='Y'";
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
			if(!leaveReq.get_ValueAsBoolean("IsKompensasiCuti")){
			if(leaveType.getValue().equals("CT") || 
			   leaveType.getValue().equals("CB1") || 
			   leaveType.getValue().equals("CB")) {
					if( CheckingLeaveStartandEndInPeriodOfLeave(leaveStartTime, leaveEndTime, timePeriodFrom, timePeriodTo)) {
						//if type CB1, CB2. and leave Start time after 						
						throw new AdempiereException("Error: request leave start and end time not valid or not in date of period");
					}
					
					if(leaveType.getValue().equals("CB1") ||  leaveType.getValue().equals("CB")) {
						GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
						cal.setTime(timePeriodTo);
						cal.set(Calendar.HOUR_OF_DAY, 0);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						cal.add(Calendar.YEAR, -2);
						tempTimePeriod = new Timestamp (cal.getTimeInMillis());
						if(leaveStartTime.after(tempTimePeriod)) 
							throw new AdempiereException("Error: type leave "+leaveType.getName()+" only active for 1 year to request");			
					}
				}
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
							/*if(i == 1){
								leave.set_ValueOfColumn("LeaveBalanceYear_1",saldoCuti);
							}else if(i == 2){
								leave.set_ValueOfColumn("LeaveBalanceYear_2", saldoCuti);
							}*/
							usedCuti  = usedCuti + saldoCuti;
							saldoCuti = 0;
							Allocated = saldoCuti;
						}else if(leaveDays <= saldoCuti){
							saldoCuti = saldoCuti - leaveDays;
							/*if(i == 1){
								leave.set_ValueOfColumn("LeaveBalanceYear_1",leaveDays);
							}else if(i == 2){
								leave.set_ValueOfColumn("LeaveBalanceYear_2", leaveDays);
							}*/
							usedCuti  = usedCuti + leaveDays;
							Allocated = saldoCuti;
							leaveDays = 0;
						}
						
						leave.set_ValueOfColumn("SaldoCutiTahunan"	, (Integer)saldoCuti);
						leave.set_ValueOfColumn("UsedCutiTahunan" 	, (Integer)usedCuti);
						leave.set_ValueOfColumn("AllocatedAmt"		, Allocated);
					}//endElse
				}else if(leaveType.getValue().equals("CB1") || leaveType.getValue().equals("CB")){
					
					//get The leave request 
					whereClause = "HC_Employee_ID = ? AND HC_LeaveType_ID = ? AND IsActive='Y' AND "
							+ X_HC_LeaveRequest.COLUMNNAME_LeavePeriodFrom +" LIKE ? AND "
							+ X_HC_LeaveRequest.COLUMNNAME_LeavePeriodTo +" LIKE ? AND "
							+ X_HC_LeaveRequest.COLUMNNAME_HC_WorkStartDate +"= ?";
					X_HC_LeaveRequest firstLeave = null;
					int firstLeave_ID = new Query(getCtx(), X_HC_LeaveRequest.Table_Name, whereClause, get_TrxName())
							.setParameters(leaveReq.getHC_Employee_ID(), leaveReq.getHC_LeaveType_ID(),leavePeriodFrom+"", leavePeriodTo+"", WorkStartDate)
							.setOrderBy(X_HC_LeaveRequest.COLUMNNAME_CreatedBy +" ASC")
							.firstId();
					
					saldoCuti = leave.getSaldoCutiBesar();
					usedCuti  = leave.getUsedCutiBesar();
					//jika menemukan first leave
					if(firstLeave_ID > 0 && firstLeave_ID != leaveReq.get_ID()){
						firstLeave = new X_HC_LeaveRequest(getCtx(), firstLeave_ID, get_TrxName());
						if(firstLeave.get_ValueAsBoolean("IsKompensasiCuti")){
							//jika pernah meng-uangkan cuti besar 
							if(leaveReq.get_ValueAsBoolean("IsKompensasiCuti")){
								//jika first leave sudah pernah request menguangkan cuti besar, dan menguangkan lagi, maka error
								throw new AdempiereException("Error: Can't request kompensasi Cuti due you already use kompensasi cuti before");
							}else{
								//jika request bukan menguangkan cuti besar
								PotongCuti(leave, leaveDays);
							}
						}else{
							//jika belum pernah request untuk meng-uangkan cuti besar
							if(leaveReq.get_ValueAsBoolean("IsKompensasiCuti")){
								//jika first leave bukan request peng uangan cuti, 
								//dan  ternyata di request di uangkan (request bukan yang pertama kali), maka error
								throw new AdempiereException("Error: Can't request kompensasi Cuti due you didn't request kompensasi cuti before");
							}else{
								PotongCuti(leave, leaveDays);
							}
						}
					}else{//jika ga menemukan first leave
						if(leaveReq.get_ValueAsBoolean("IsKompensasiCuti")){
							//jika request menguangkan cuti
							int pengurangCuti = 0;
							if(leaveType.getValue().equals("CB"))
								pengurangCuti = 45;
							else if(leaveType.getValue().equals("CB1"))
								pengurangCuti = 90;
							
							saldoCuti = saldoCuti - pengurangCuti;
							usedCuti = usedCuti + pengurangCuti;
							Allocated = saldoCuti;
							leave.set_ValueOfColumn("LeaveBalanceYear_1", pengurangCuti);
							leave.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_SaldoCutiBesar	, (Integer)saldoCuti);
							leave.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_UsedCutiBesar 	, (Integer)usedCuti);
							leave.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_AllocatedAmt	, Allocated);
						}else
							flag = flag + PotongCuti(leave, leaveDays);	//jika tidak request menguangkan cuti			
					}
				}//endElseIf
				leave.saveEx();
			}//endFor
			
			if(flag == leaves.size())
				throw new AdempiereException("Employee " + employee.getName() + " Doesn't have saldo " + type.getName());
		}else{
			Timestamp startDate = leaveReq.getStartDate();
			Timestamp endDate = leaveReq.getEndDate();
			X_HC_EmployeeLeaveBalance empLeaveBalance = new X_HC_EmployeeLeaveBalance(getCtx(), 0, get_TrxName());
			empLeaveBalance.setHC_Employee_ID(employee.get_ID());
			empLeaveBalance.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_Employee_ID, employee.get_ID());
			empLeaveBalance.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID, leaveType.getHC_LeaveType_ID());
			empLeaveBalance.set_ValueOfColumn("StartDate", startDate);
			empLeaveBalance.set_ValueOfColumn("EndDate", endDate);
			if((TimeUtil.getDaysBetween(startDate, endDate)+1) > leaveType.getLeaveDays())
				throw new AdempiereException("Days between startDate and endDate more than certain leave days");
			empLeaveBalance.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_AllocatedAmt, 
					leaveType.getLeaveDays());
			empLeaveBalance.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_LeaveBalancePeriod,
					startDate.toString().substring(0, 10) + " / " + endDate.toString().substring(0,10)  );
			empLeaveBalance.setIsActive(true);
			empLeaveBalance.saveEx();
		}
		//}//end Else for requested document
	
		leaveReq.setStatus("Cnf");
		leaveReq.saveEx();
		return "Success Process";
	}//DoIt
	
	private int PotongCuti(X_HC_EmployeeLeaveBalance leave, int leaveDays){
		int saldoCuti = leave.getSaldoCutiBesar();
		int usedCuti = leave.getUsedCutiBesar();
		int Allocated = leave.getAllocatedAmt();
		if(saldoCuti == 0){
			//flag++;
			return 1;
		}else{
			if(leaveDays > saldoCuti){
				leaveDays = leaveDays - saldoCuti;
				//leave.set_ValueOfColumn("LeaveBalanceYear_1",saldoCuti);
				usedCuti  = usedCuti + saldoCuti;
				Allocated = saldoCuti;
				saldoCuti = 0;
			}else if(leaveDays <= saldoCuti){
				saldoCuti = saldoCuti - leaveDays;
				//leave.set_ValueOfColumn("LeaveBalanceYear_1",leaveDays);
				usedCuti  = usedCuti + leaveDays;
				Allocated = saldoCuti;
				leaveDays = 0;
			}
			leave.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_SaldoCutiBesar	, (Integer)saldoCuti);
			leave.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_UsedCutiBesar 	, (Integer)usedCuti);
			leave.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_AllocatedAmt	, Allocated);
			return 0;
		}//endElse
	}
	
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
}
