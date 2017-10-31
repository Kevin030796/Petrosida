package org.toba.psd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.toba.psd.model.X_HC_EmployeeLeaveBalance;
import org.toba.psd.model.X_HC_LeaveDebt;
import org.toba.psd.model.X_HC_LeaveRequest;
import org.toba.psd.model.X_HC_LeaveRequest_Adm;
import org.toba.psd.model.X_HC_LeaveType;

public class PSD_CutiBersamaProcessNow extends SvrProcess{
	
	private int p_HC_LeaveRequest_Adm = 0;
	private String p_Description = "";
	private String CutiTahunanType = "CT";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if(name.equals("Description"))
				p_Description 	   = para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_HC_LeaveRequest_Adm = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_LeaveRequest_Adm <= 0)
			throw new AdempiereException("Error: leave Request admin not found");
		
		X_HC_LeaveRequest_Adm requestAdm = new X_HC_LeaveRequest_Adm(getCtx(), p_HC_LeaveRequest_Adm, get_TrxName());
		int leaveDays = requestAdm.getLeaveDays();
		int NonBusinessDays = requestAdm.getHC_NonBusinessDay();
		Timestamp leaveStartDate = requestAdm.getHC_LeaveStartDate();
		Timestamp leaveEndDate = requestAdm.getHC_LeaveEndDate();
		Timestamp today = new Timestamp(System.currentTimeMillis());
		//process start
		
		String sql = "SELECT DISTINCT emp.HC_Employee_ID, empJob.HC_EmployeeJob_ID, empClass.Value FROM HC_EmployeeJob empJob "
				+ " LEFT JOIN HC_Employee emp ON emp.HC_Employee_ID = empJob.HC_Employee_ID"
				+ " LEFT JOIN HC_EmployeeClass empClass ON empClass.HC_EmployeeClass_ID = empJob.HC_EmployeeClass_ID "
				+ " WHERE empJob.seqNo=1"
				+ " AND empJob.HC_Status='A'"
				+ " AND emp.HC_Status='A'"
				+ " AND emp.IsActive='Y'"
				+ " AND empJob.IsActive='Y'"
				+ " AND empClass.IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEmployee> employees = new ArrayList<MEmployee>();
		List<String> employeeClasses = new ArrayList<String>();
		List<MEmployeeJob> employeeJobs = new ArrayList<MEmployeeJob>();
		int count_employees = 0;
		try{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				employees.add(new MEmployee(getCtx(), rs.getInt(1), get_TrxName()));
				employeeJobs.add(new MEmployeeJob(getCtx(), rs.getInt(2), get_TrxName()));
				employeeClasses.add(rs.getString(3));
				count_employees++;
			}
		}catch(Exception e){
			log.log(Level.SEVERE, "Not found role sdm", e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		String whereClause = "Value like ?";
		int HC_LeaveTypeCutiTahunan_ID = new Query(getCtx(), X_HC_LeaveType.Table_Name, whereClause, get_TrxName())
										 .setParameters(CutiTahunanType)
										 .setOnlyActiveRecords(true)
										 .firstId();
		
		for(int i = 0 ; i < count_employees ; i++){
			MEmployee employee = employees.get(i);
			MEmployeeJob employeeJob = employeeJobs.get(i);
			String empClass = employeeClasses.get(i);
			int tempLeaveDays = leaveDays;
			int totalEmployeeLeaveBalance = 0;
			
			if(empClass.equals("Permanent")){
				//TP: Permanent
				sql = "SELECT "+X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_EmployeeLeaveBalance_ID +" FROM "+X_HC_EmployeeLeaveBalance.Table_Name+""
					+ " WHERE"
					+ " " + X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_Employee_ID + "=?"
					+ " AND "+X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID+"=?"
					+ " AND "+X_HC_EmployeeLeaveBalance.COLUMNNAME_SaldoCutiTahunan+"!=0"
					+ " AND "+X_HC_EmployeeLeaveBalance.COLUMNNAME_IsActive+"='Y'"
				    + " ORDER BY "+X_HC_EmployeeLeaveBalance.COLUMNNAME_Created;
				pstmt = null;
				rs = null;
				try{
					pstmt = DB.prepareStatement(sql, get_TrxName());
					rs = pstmt.executeQuery();
					if(rs != null){
						String periodFrom = null;
						String periodTo = null;
						int leaveQty = 0;
						while(rs.next()){
							X_HC_EmployeeLeaveBalance employeeLeaveBalance = new X_HC_EmployeeLeaveBalance(getCtx(), rs.getInt(1), get_TrxName());
							leaveQty = leaveQty + employeeLeaveBalance.getAllocatedAmt();
							if(i==0){
								periodFrom = employeeLeaveBalance.getLeaveBalancePeriod().substring(0,4);
								periodTo = employeeLeaveBalance.getLeaveBalancePeriod().substring(13, 17);
							}else if(i > 0){
								periodTo = employeeLeaveBalance.getLeaveBalancePeriod().substring(13,17);
							}
							totalEmployeeLeaveBalance++;
						}
						
						//recheck again
						rs.beforeFirst();
						int check = 0;
						while(rs.next()){
							X_HC_EmployeeLeaveBalance empLeaveBalance = new X_HC_EmployeeLeaveBalance(getCtx(), rs.getInt(1), get_TrxName());
							int saldoCutiTahunan = empLeaveBalance.getSaldoCutiTahunan();
							check++;
							if(saldoCutiTahunan >= tempLeaveDays){
								//cukup saldo
								//buat catatan history 
								if(tempLeaveDays != 0){
									X_HC_LeaveRequest new_LeaveRequest = new X_HC_LeaveRequest(getCtx(), 0, get_TrxName());
									new_LeaveRequest.setHC_Employee_ID(employee.get_ID());
									new_LeaveRequest.setAD_Org_ID(employee.getAD_Org_ID());
									new_LeaveRequest.setHC_Job_ID(employeeJob.getHC_Job_ID());
									new_LeaveRequest.setHC_WorkStartDate(employeeJob.getHC_WorkStartDate());
									new_LeaveRequest.setDateTrx(today);
									new_LeaveRequest.setTimeTrx(today);
									new_LeaveRequest.setHC_LeaveType_ID(HC_LeaveTypeCutiTahunan_ID);
									new_LeaveRequest.setLeaveBalance(leaveQty);
									new_LeaveRequest.setLeavePeriodFrom(periodFrom);
									new_LeaveRequest.setLeavePeriodTo(periodTo);
									new_LeaveRequest.setHC_LeaveStartDate(leaveStartDate);
									new_LeaveRequest.setHC_LeaveEndDate(leaveEndDate);
									new_LeaveRequest.setLeaveDays(tempLeaveDays);
									new_LeaveRequest.set_ValueOfColumn("NonBusinessDay", NonBusinessDays);
									new_LeaveRequest.setDescription(p_Description);
									new_LeaveRequest.setApprovalDate(today);
									new_LeaveRequest.setApprovalTime(today);
									new_LeaveRequest.setApprovalDescription(p_Description);
									new_LeaveRequest.setStatus(X_HC_LeaveRequest.STATUS_Confirmed);
									new_LeaveRequest.saveEx();
									//pengurangan employee leave calculation
									saldoCutiTahunan = saldoCutiTahunan - tempLeaveDays;
									
									empLeaveBalance.setSaldoCutiTahunan(saldoCutiTahunan);
									empLeaveBalance.setUsedCutiTahunan(empLeaveBalance.getUsedCutiTahunan() + tempLeaveDays);
									empLeaveBalance.setAllocatedAmt(saldoCutiTahunan);
									tempLeaveDays = 0;
								}
							}else{
								//tidak cukup saldo
								if(saldoCutiTahunan > 0){
									if(tempLeaveDays != 0){
										tempLeaveDays = tempLeaveDays - saldoCutiTahunan;
										empLeaveBalance.setUsedCutiTahunan(empLeaveBalance.getUsedCutiTahunan() + saldoCutiTahunan);
										saldoCutiTahunan = 0;
										empLeaveBalance.setSaldoCutiTahunan(saldoCutiTahunan);
										empLeaveBalance.setAllocatedAmt(saldoCutiTahunan);
									}
									
									if(check == totalEmployeeLeaveBalance){
										//createHistoryCuti
										X_HC_LeaveRequest new_LeaveRequest = new X_HC_LeaveRequest(getCtx(), 0, get_TrxName());
										new_LeaveRequest.setHC_Employee_ID(employee.get_ID());
										new_LeaveRequest.setAD_Org_ID(employee.getAD_Org_ID());
										new_LeaveRequest.setHC_Job_ID(employeeJob.getHC_Job_ID());
										new_LeaveRequest.setHC_WorkStartDate(employeeJob.getHC_WorkStartDate());
										new_LeaveRequest.setDateTrx(today);
										new_LeaveRequest.setTimeTrx(today);
										new_LeaveRequest.setHC_LeaveType_ID(HC_LeaveTypeCutiTahunan_ID);
										new_LeaveRequest.setLeaveBalance(leaveQty);
										new_LeaveRequest.setLeavePeriodFrom(periodFrom);
										new_LeaveRequest.setLeavePeriodTo(periodTo);
										new_LeaveRequest.setHC_LeaveStartDate(leaveStartDate);
										new_LeaveRequest.setHC_LeaveEndDate(leaveEndDate);
										new_LeaveRequest.setLeaveDays(tempLeaveDays);
										new_LeaveRequest.set_ValueOfColumn("NonBusinessDay", NonBusinessDays);
										new_LeaveRequest.setDescription(p_Description);
										new_LeaveRequest.setApprovalDate(today);
										new_LeaveRequest.setApprovalTime(today);
										new_LeaveRequest.setApprovalDescription(p_Description);
										new_LeaveRequest.setStatus(X_HC_LeaveRequest.STATUS_Confirmed);
										new_LeaveRequest.saveEx();
										
										//create utang cuti
										CreateHutangCuti(requestAdm,employee,employeeJob,HC_LeaveTypeCutiTahunan_ID,tempLeaveDays);
									}
								}
							}//end Else
							empLeaveBalance.saveEx();
						}//end While rs.next()
					}else{
						CreateHutangCuti(requestAdm, employee, employeeJob, HC_LeaveTypeCutiTahunan_ID,leaveDays);
					}
				}catch(Exception e){
					log.log(Level.SEVERE, "Not found role sdm", e);
				}finally{
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
			}else{
				//!TP: not Permanent
				CreateHutangCuti(requestAdm, employee, employeeJob, HC_LeaveTypeCutiTahunan_ID,leaveDays);
			}//endElse
			
			employee = null;
			employeeJob = null;
			empClass = null;
		}//endLoop count_employees	
		return "Process Complete";
	}
	
	private void CreateHutangCuti(X_HC_LeaveRequest_Adm requestAdm, 
			MEmployee employee, 
			MEmployeeJob employeeJob, 
			int HC_LeaveTypeCutiTahunan_ID,
			int jumlahHutang){
		
		int NonBusinessDays = requestAdm.getHC_NonBusinessDay();
		Timestamp leaveStartDate = requestAdm.getHC_LeaveStartDate();
		Timestamp leaveEndDate = requestAdm.getHC_LeaveEndDate();
		
		MJob job = new MJob(getCtx(),employeeJob.getHC_Job_ID(),get_TrxName());
		X_HC_LeaveDebt leaveDebt = new X_HC_LeaveDebt(getCtx(), 0 , get_TrxName());
		leaveDebt.setHC_LeaveStartDate(leaveStartDate);
		leaveDebt.setHC_LeaveEndDate(leaveEndDate);
		leaveDebt.setLeaveDays(jumlahHutang);
		leaveDebt.setNonBusinessDay(NonBusinessDays);
		leaveDebt.setHC_JobLevel_ID(job.getHC_JobLevel_ID());
		leaveDebt.setHC_LeaveType_ID(HC_LeaveTypeCutiTahunan_ID);
		leaveDebt.setDescription(p_Description);
		leaveDebt.setIsActive(true);
		leaveDebt.setHC_Employee_ID(employee.getHC_Employee_ID());
		leaveDebt.setAD_Org_ID(employee.getAD_Org_ID());
		leaveDebt.saveEx();
	}
		
}
