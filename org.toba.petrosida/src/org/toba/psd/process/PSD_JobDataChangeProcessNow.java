package org.toba.psd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.MJobDataChange;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.taowi.hcm.payroll.model.MHCAttribute;
import org.taowi.hcm.payroll.model.MHCPayComponent;
import org.toba.psd.model.PSD_MSysConfig;
import org.toba.psd.model.X_HC_Mutation;
import org.toba.psd.model.X_HC_SalaryChange;

/**
 * @author KevinY
 * Process for Job Data Change
 */
public class PSD_JobDataChangeProcessNow extends SvrProcess {
	
	public int p_JobDataChange_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_JobDataChange_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(p_JobDataChange_ID <= 0)
			throw new AdempiereException("Error: Job Data Change not selected");
		
		MJobDataChange jobDataChange = new MJobDataChange(getCtx(), p_JobDataChange_ID, get_TrxName());
		MEmployeeJob Job 			 = new MEmployeeJob(getCtx(), jobDataChange.getHC_EmployeeJob_ID(), get_TrxName());
		//MEmployee emp 				 = new MEmployee(getCtx(), jobDataChange.getHC_Employee_ID(), get_TrxName());
		if(Job.getSeqNo() != 1)
			throw new AdempiereException("Error: Job Data Change only change Job with sequence 1");
		
		if(jobDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_SalaryChange) && 
				jobDataChange.getHC_Compensation1().compareTo(jobDataChange.getHC_Compensation2()) == 0){
			throw new AdempiereException("Error: Can't process new salary due to same value of previous salary");
		}
		
		//@KevinY PSD - 2872
		if(jobDataChange.getHC_WorkStartDate().after((Timestamp)jobDataChange.get_Value("HC_WorkStartDate2"))== true){
			throw new AdempiereException("Error: Work Start Date Baru must be after than Work Start Date");
		}
		//@KevinY end
		
		/*If job action is not job action salary change */
		if(!jobDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_SalaryChange)){
			Job.set_ValueOfColumn		("HC_EmployeeGrade_ID", jobDataChange.get_Value("HC_EmployeeGrade2_ID"));
			Job.setHC_Org_ID			(jobDataChange.get_ValueAsInt("HC_Org2_ID"));
			Job.setHC_Job_ID			(jobDataChange.get_ValueAsInt("HC_Job_ID"));
			Job.setHC_PayGroup_ID		(jobDataChange.getHC_PayGroup_ID());
			//Job.set_ValueOfColumn		("HC_Manager_ID"   , jobDataChange.get_Value("HC_ManagerTo_ID"));
		}
		
		Job.set_ValueOfColumn		("HC_Compensation1", jobDataChange.get_Value("HC_Compensation2"));
		Job.setDescription			(jobDataChange.get_ValueAsString("DescriptionNew"));
		Job.setHC_JobAction			(jobDataChange.getHC_JobAction());
		Job.setHC_WorkStartDate		((Timestamp)jobDataChange.get_Value("HC_WorkStartDate2"));
		
		X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(), Job.getHC_EmployeeClass_ID(), get_TrxName());
		if(empClass.get_ValueAsBoolean("MemberKoperasi"))
			Job.set_ValueOfColumn("MemberKoperasi", true);
		if(jobDataChange.get_Value("NomorSK2")!= null)
			Job.set_ValueOfColumn("NomorSK", jobDataChange.get_Value("NomorSK2"));
		if(jobDataChange.getHC_Reason_ID()> 0)
			Job.setHC_Reason_ID(jobDataChange.getHC_Reason_ID());
		Job.saveEx();
		
		/*Preparing value of HC_Attribute validfrom at Day Salary if IsDifferentPayCalculation = 'Y'*/
		int HC_DaySalary = 0;
		String WorkStartDateNew = "";
		Calendar calWorkStartDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
			HC_DaySalary = (Integer)empClass.get_Value("HC_PayrollDay");
			Timestamp HC_WorkStartDateNew = (Timestamp)jobDataChange.get_Value("HC_WorkStartDate2");
			int daysInMonth = TimeUtil.getNumberOfDaysInMonth(TimeUtil.addMonths(HC_WorkStartDateNew, -1));
			if(HC_DaySalary > daysInMonth){
				HC_DaySalary = daysInMonth;
			}
			HC_WorkStartDateNew = TimeUtil.addMonths(HC_WorkStartDateNew,-1);
			try {
				calWorkStartDate.setTime(sdf.parse(HC_WorkStartDateNew.toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			WorkStartDateNew = calWorkStartDate.get(Calendar.YEAR) + "-";
			
			if(calWorkStartDate.get(Calendar.MONTH) < 10){
				WorkStartDateNew = WorkStartDateNew + "0"+(calWorkStartDate.get(Calendar.MONTH)+1)+"-";
			}else{
				WorkStartDateNew = WorkStartDateNew + (calWorkStartDate.get(Calendar.MONTH)+1)+"-";
			}
			
			if(HC_DaySalary < 10)
				WorkStartDateNew = WorkStartDateNew + "0"+ HC_DaySalary + " 00:00:00.0";
			else
				WorkStartDateNew = WorkStartDateNew + HC_DaySalary + " 00:00:00.0";
		}
		/*End preparing new value for HC_Attribute valid from*/
		
		//@KevinY PSD - 2861
		if(!jobDataChange.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_SalaryChange)){
			X_HC_Mutation mutation = new X_HC_Mutation(getCtx(), 0, get_TrxName());
			mutation.setHC_Employee_ID(Job.getHC_Employee_ID());
			mutation.setHC_Job_ID(Job.getHC_Job_ID());
			mutation.setNomorSK(jobDataChange.get_Value("NomorSK2").toString());
			mutation.setDate1(Job.getHC_WorkStartDate());
			mutation.setIsActive(true);
			mutation.set_ValueOfColumn("HC_JobDataChange_ID", jobDataChange.getHC_JobDataChange_ID());
			mutation.saveEx();
			
			if(jobDataChange.getHC_Compensation1().compareTo(jobDataChange.getHC_Compensation2()) != 0){
				//set perubahan gaji 
				X_HC_SalaryChange salaryChange = new X_HC_SalaryChange(getCtx(), 0, get_TrxName());
				salaryChange.setHC_Employee_ID(Job.getHC_Employee_ID());
				salaryChange.setNomorSK(jobDataChange.get_Value("NomorSK2").toString());
				salaryChange.set_ValueOfColumn("HC_JobDataChange_ID", jobDataChange.getHC_JobDataChange_ID());
				salaryChange.setHC_Compensation1(jobDataChange.getHC_Compensation2());
				salaryChange.setDescription(jobDataChange.get_ValueAsString("DescriptionNew"));
				salaryChange.setDate1((Timestamp)jobDataChange.get_Value("HC_WorkStartDate2"));
				salaryChange.setIsActive(true);
				salaryChange.saveEx();
				
				// set base salary to payroll component
				/*String sql = "SELECT HC_PayComponent_ID FROM HC_PayComponent WHERE Value=?";
				int payComponent_ID = DB.getSQLValue(get_TrxName(), sql	, new Object[]{"BASE_SALARY"});
				MHCPayComponent payComponent = new MHCPayComponent(getCtx(), payComponent_ID, get_TrxName());
				*/
				MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PSD_MSysConfig.getValue(PSD_MSysConfig.PAY_COMPONENT_BASE_SALARY));
				MHCAttribute attribute 		 = new MHCAttribute(getCtx(), 0, get_TrxName());
				attribute.setAD_Org_ID(jobDataChange.getAD_Org_ID());
				attribute.setHC_PayComponent_ID(payComponent.get_ID());
				attribute.setHC_Employee_ID(jobDataChange.getHC_Employee_ID());
				attribute.setHC_PayComponentType(MHCAttribute.HC_PAYCOMPONENTTYPE_Numeric);
				if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
					attribute.setValidFrom(Timestamp.valueOf(WorkStartDateNew));//permanent
				}else
					attribute.setValidFrom((Timestamp) jobDataChange.get_Value("HC_WorkStartDate2"));//not permanent
				attribute.setHC_NumValue(jobDataChange.getHC_Compensation2());
				attribute.saveEx();
			}
			
			//resetManager to all employee related with this employee as previous manager and next manager
			//resetManager(emp, jobDataChange);
			
		}else{
			//JobAction : salary change
			/*set Perubahan Gaji*/
			X_HC_SalaryChange salaryChange = new X_HC_SalaryChange(getCtx(), 0, get_TrxName());
			salaryChange.setHC_Employee_ID(Job.getHC_Employee_ID());
			salaryChange.setNomorSK(jobDataChange.get_Value("NomorSK2").toString());
			salaryChange.set_ValueOfColumn("HC_JobDataChange_ID", jobDataChange.getHC_JobDataChange_ID());
			salaryChange.setHC_Compensation1(jobDataChange.getHC_Compensation2());
			salaryChange.setDescription(jobDataChange.get_ValueAsString("DescriptionNew"));
			salaryChange.setDate1((Timestamp)jobDataChange.get_Value("HC_WorkStartDate2"));
			salaryChange.setIsActive(true);
			salaryChange.saveEx();
			/*End set Perubahan Gaji*/
			
			/*Set base salary to Attribute Payroll Component*/
			/*String sql = "SELECT HC_PayComponent_ID FROM HC_PayComponent WHERE Value=?";
			int payComponent_ID = DB.getSQLValue(get_TrxName(), sql	, new Object[]{"BASE_SALARY"});
			MHCPayComponent payComponent = new MHCPayComponent(getCtx(), payComponent_ID, get_TrxName());
			*/
			MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PSD_MSysConfig.getValue(PSD_MSysConfig.PAY_COMPONENT_BASE_SALARY));
			MHCAttribute attribute 		 = new MHCAttribute(getCtx(), 0, get_TrxName());
			attribute.setAD_Org_ID(jobDataChange.getAD_Org_ID());
			attribute.setHC_PayComponent_ID(payComponent.get_ID());
			attribute.setHC_Employee_ID(jobDataChange.getHC_Employee_ID());
			attribute.setHC_PayComponentType(MHCAttribute.HC_PAYCOMPONENTTYPE_Numeric);
			if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
				attribute.setValidFrom(Timestamp.valueOf(WorkStartDateNew));//permanent
			}else
				attribute.setValidFrom((Timestamp) jobDataChange.get_Value("HC_WorkStartDate2"));//not permanent
			attribute.setHC_NumValue(jobDataChange.getHC_Compensation2());
			attribute.saveEx();
			/*End set Base Salary to Attribute Payroll Component */
		}
		//@KevinY end
	
		jobDataChange.setProcessed(true);
		jobDataChange.setProcessing(true);
		jobDataChange.saveEx();
		return "Success Process Job Data Change";
	}//doIt
	
	
	/**
	 * Reset manager with all employees related
	 * @param emp
	 * @param jdc
	 */
	public void resetManager(MEmployee emp ,MJobDataChange jdc){
		List<MEmployeeJob> EmployeeJobs = new ArrayList<MEmployeeJob>();
		String query = "SELECT HC_EmployeeJob_ID FROM HC_EmployeeJob"
				+ " WHERE "+MEmployeeJob.COLUMNNAME_HC_Manager_ID+"=? AND "
				+ MEmployeeJob.COLUMNNAME_IsActive+"='Y' AND "
				+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setInt(1,emp.get_ID());
			rs = pstmt.executeQuery();
			while(rs.next()){
				int HC_EmployeeJob_ID = rs.getInt(1);
				MEmployeeJob m_employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
				EmployeeJobs.add(m_employeeJob);
			}
		} catch (SQLException e){
			log.log(Level.SEVERE, "searching m_employeeJob with manager related with this employee not found");
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if(EmployeeJobs.size() > 0) {
			for(int i = 0 ; i < EmployeeJobs.size(); i++) {
				MEmployeeJob empJob = new MEmployeeJob(getCtx(), EmployeeJobs.get(i).get_ID(), get_TrxName());
				empJob.set_ValueOfColumn(MEmployeeJob.COLUMNNAME_HC_Manager_ID, null);
				empJob.saveEx();
			}
		}	
		
		//set new manager into all employee with HC_Job_ID from jdc
		EmployeeJobs = new ArrayList<MEmployeeJob>();
		query = "SELECT HC_EmployeeJob_ID FROM HC_EmployeeJob LEFT JOIN HC_Job job ON job.HC_Job_ID = HC_EmployeeJob.HC_Job_ID "
				+ "WHERE job."+ MJob.COLUMNNAME_HC_JobReportTo_ID+"=? AND "
				+ "HC_EmployeeJob."+ MEmployeeJob.COLUMNNAME_IsActive+"='Y' AND "
				+ "HC_EmployeeJob."+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
		/*EmployeeJobs = new Query(getCtx(), MEmployeeJob.Table_Name, query, get_TrxName())
		.addJoinClause("LEFT JOIN HC_Job job ON job.HC_Job_ID = HC_EmployeeJob.HC_Job_ID")
		.setParameters(new Object[]{jdc.getHC_Job_ID()})
		.list();
		*/

		pstmt = null;
		rs = null;
		try {
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setInt(1,jdc.getHC_Job_ID());
			rs = pstmt.executeQuery();
			while(rs.next()){
				int HC_EmployeeJob_ID = rs.getInt(1);
				MEmployeeJob m_employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
				EmployeeJobs.add(m_employeeJob);
			}
		} catch (SQLException e){
			log.log(Level.SEVERE, "searching m_employeeJob with manager related with this employee not found");
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	
		if(EmployeeJobs.size() > 0) {
			for(int i = 0 ; i < EmployeeJobs.size(); i++) {
				MEmployeeJob empJob = new MEmployeeJob(getCtx(), EmployeeJobs.get(i).get_ID(), get_TrxName());
				empJob.set_ValueOfColumn(MEmployeeJob.COLUMNNAME_HC_Manager_ID, jdc.getHC_Employee_ID());
				empJob.saveEx();
			}
		}	
	}//resetManager
}//endClass
