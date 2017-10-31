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
import org.compiere.model.Query;
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
import org.toba.psd.model.X_IHC_JobDataChange;

/**
 * @author KevinY
 * Process for Imported Job Data Change
 */
public class PSD_I_JobDataChangeProcessNow extends SvrProcess {
	
	
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
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		/*Get all employee data from import job data change files that are not still processed*/
		String sql = "SELECT DISTINCT "+X_IHC_JobDataChange.COLUMNNAME_HC_Employee_ID+" FROM "+X_IHC_JobDataChange.Table_Name+ " "
				+ "WHERE "+X_IHC_JobDataChange.COLUMNNAME_IsActive+"='Y' AND " +X_IHC_JobDataChange.COLUMNNAME_Processed+"='N'" ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> Employee_Ids = new ArrayList<Integer>();
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Employee_Ids.add(rs.getInt(1));
			}
		} catch (SQLException e){
			log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		/*End get all employee data*/
	
		if(Employee_Ids.size() <= 0) {
			throw new AdempiereException("No Any Employee exists");//not found employee
		}
		
		for(int i = 0 ; i < Employee_Ids.size() ; i++) {
			int employeeId = (Integer)Employee_Ids.get(i);
			MEmployee employee = new MEmployee(getCtx(), employeeId, get_TrxName());
			
			/*Get all Import Job Data Change related with employee data
			searching job data change by HC WOrk start date asc*/
			String whereClause = X_IHC_JobDataChange.COLUMNNAME_Processed+"='N' AND "
						  +X_IHC_JobDataChange.COLUMNNAME_IsActive+"='Y' AND "
						  +X_IHC_JobDataChange.COLUMNNAME_HC_Employee_ID+"=?";
			int[] IHC_JobDataChange_Ids = null;
			IHC_JobDataChange_Ids = new Query(getCtx(), X_IHC_JobDataChange.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{employeeId})
										.setOrderBy("HC_WorkStartDate")
										.getIDs();
			/*End Get All Import Job Data Change related with employee data*/
			
			for(int IHC_JobDataChange_ID : IHC_JobDataChange_Ids) {
				//flag is to check that employeejob import job data change still exists
				int flag = 0;
				X_IHC_JobDataChange iJdc = new X_IHC_JobDataChange(getCtx(), IHC_JobDataChange_ID, get_TrxName());
				MEmployeeJob empJob = new MEmployeeJob(getCtx(), iJdc.getHC_EmployeeJob_ID(), get_TrxName());
				//matching data
				/*sql = "SELECT "+MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID+" FROM "+MEmployeeJob.Table_Name+ " WHERE "
							  +MEmployeeJob.COLUMNNAME_HC_Employee_ID+"=? AND "
							  +MEmployeeJob.COLUMNNAME_HC_Job_ID+"=? AND "
							  +MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"' AND "
							  +"NomorSK=? AND "
							  +MEmployeeJob.COLUMNNAME_HC_Compensation1+"=? AND "
							  +MEmployeeJob.COLUMNNAME_SeqNo+"=1 AND "
							  +MEmployeeJob.COLUMNNAME_IsActive+"='A' AND "
							  +MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID+"=? AND "
							  +MEmployeeJob.COLUMNNAME_HC_WorkStartDate+"=?";
							  if(iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_PayGroup_ID)!= null)
								  whereClause = whereClause + " AND "+X_IHC_JobDataChange.COLUMNNAME_HC_PayGroup_ID+"=?";
				*/
				sql ="SELECT "+MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID+" FROM "+MEmployeeJob.Table_Name + " WHERE "
						+ MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID+"=? AND "+MEmployeeJob.COLUMNNAME_SeqNo+"=1";
				pstmt = null;
			    rs = null;
				try {
					pstmt = DB.prepareStatement(sql, get_TrxName());
					/*pstmt.setInt(1, employeeId);
					pstmt.setInt(2, iJdc.getHC_Job_ID());
					pstmt.setString(3, iJdc.getNomorSK());
					pstmt.setBigDecimal(4, iJdc.getHC_Compensation1());
					pstmt.setInt(5, I_HC_EmployeeJob_ID);
					pstmt.setTimestamp(6, iJdc.getHC_WorkStartDate());
					if(iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_PayGroup_ID)!= null)
						pstmt.setInt(6, iJdc.getHC_PayGroup_ID());
					*/
					pstmt.setInt(1,iJdc.getHC_EmployeeJob_ID());
					rs = pstmt.executeQuery();
					if (rs.next()) {
						flag++;
					}
				} catch (SQLException e){
					log.log(Level.SEVERE, "not found related employee Job in Import job data change with employee "+ employee.getName());
				} finally {
					DB.close(rs, pstmt);
					rs = null;
					pstmt = null;
				}
				
				
				/*If Exists searched employee job then 
				 * process import job data change and create new file job data change*/
				if(flag == 1){
					if(iJdc.getHC_WorkStartDate2().before(empJob.getHC_WorkStartDate()))
						throw new AdempiereException("Error: Work Start Date Baru is before employee job work start date ("+iJdc.get_ID()+")");
					if(iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Org2_ID) == null) 
						throw new AdempiereException("Error: HC Organization To is empty ("+iJdc.get_ID()+")");
					if(iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_NomorSK2) == null) 
						throw new AdempiereException("Error: Nomor SK baru is empty ("+iJdc.get_ID()+")");
					
					MJobDataChange jdc = new MJobDataChange(getCtx(), 0, get_TrxName());
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Employee_ID, iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Employee_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_EmployeeJob_ID, iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_EmployeeJob_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Status, iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Status));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_JobAction, iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_JobAction));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Reason_ID, iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Reason_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_PreviousJob_ID, iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_PreviousJob_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Job_ID,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Job_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_WorkStartDate, iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_WorkStartDate));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_WorkStartDate2,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_WorkStartDate2));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Org_ID,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Org_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Org2_ID,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Org2_ID));
					//if(iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Manager_ID) != null)
					//	jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Manager_ID,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Manager_ID));
					//if(iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_ManagerTo_ID) != null)
					//	jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_ManagerTo_ID,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_ManagerTo_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_NomorSK,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_NomorSK));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_NomorSK2,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_NomorSK2));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_Description,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_Description));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_DescriptionNew,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_DescriptionNew));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_EmployeeGrade2_ID,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_EmployeeGrade2_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_PayGroup_ID,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_PayGroup_ID));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Compensation1,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Compensation1));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_HC_Compensation2,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_HC_Compensation2));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_BPJSRegistrationDate,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_BPJSRegistrationDate));
					jdc.set_ValueOfColumn(X_IHC_JobDataChange.COLUMNNAME_IsActive,iJdc.get_Value(X_IHC_JobDataChange.COLUMNNAME_IsActive));
					jdc.saveEx();
					
					//Process now the job data change that just created
					jobDatachangeDoIt(jdc.get_ID());
					
					iJdc.setProcessed(true);
					iJdc.saveEx();
					flag = 0;
				}//endIf
			}//end For imported Job data change
		}//end for employee
		return "Success Process Data";
	}//doIt
	
	/**
	 * method doIt from job data change
	 * @param p_JobDataChange_ID
	 */
	private void jobDatachangeDoIt(int p_JobDataChange_ID){
		
		if(p_JobDataChange_ID <= 0)
			throw new AdempiereException("Error: Job Data Change not selected");
		
		MJobDataChange jobDataChange = new MJobDataChange(getCtx(), p_JobDataChange_ID, get_TrxName());
		MEmployeeJob Job 			 = new MEmployeeJob(getCtx(), jobDataChange.getHC_EmployeeJob_ID(), get_TrxName());
		//MEmployee emp = new MEmployee(getCtx(), jobDataChange.getHC_Employee_ID(), get_TrxName());
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
		
		
		//set Attribute validfrom at SK day if IsDifferentPayCalculation = 'Y'
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
				MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PSD_MSysConfig.getValue(PSD_MSysConfig.PAY_COMPONENT_BASE_SALARY));
				MHCAttribute attribute 		 = new MHCAttribute(getCtx(), 0, get_TrxName());
				attribute.setAD_Org_ID(jobDataChange.getAD_Org_ID());
				attribute.setHC_PayComponent_ID(payComponent.get_ID());
				attribute.setHC_Employee_ID(jobDataChange.getHC_Employee_ID());
				attribute.setHC_PayComponentType(MHCAttribute.HC_PAYCOMPONENTTYPE_Numeric);
				if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
					attribute.setValidFrom(Timestamp.valueOf(WorkStartDateNew));
				}else
					attribute.setValidFrom((Timestamp) jobDataChange.get_Value("HC_WorkStartDate2"));
				attribute.setHC_NumValue(jobDataChange.getHC_Compensation2());
				attribute.saveEx();
			}
			
			//resetManager to all employee related with this employee as previous manager and next manager
			//resetManager(emp, jobDataChange);
			
		}else{
			//JobAction : salary change
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
			MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PSD_MSysConfig.getValue(PSD_MSysConfig.PAY_COMPONENT_BASE_SALARY));
			MHCAttribute attribute 		 = new MHCAttribute(getCtx(), 0, get_TrxName());
			attribute.setAD_Org_ID(jobDataChange.getAD_Org_ID());
			attribute.setHC_PayComponent_ID(payComponent.get_ID());
			attribute.setHC_Employee_ID(jobDataChange.getHC_Employee_ID());
			attribute.setHC_PayComponentType(MHCAttribute.HC_PAYCOMPONENTTYPE_Numeric);
			if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
				attribute.setValidFrom(Timestamp.valueOf(WorkStartDateNew));
			}else
				attribute.setValidFrom((Timestamp) jobDataChange.get_Value("HC_WorkStartDate2"));
			attribute.setHC_NumValue(jobDataChange.getHC_Compensation2());
			attribute.saveEx();
		}
		//@KevinY end
	
		
		jobDataChange.setProcessed(true);
		jobDataChange.setProcessing(true);
		jobDataChange.saveEx();

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

