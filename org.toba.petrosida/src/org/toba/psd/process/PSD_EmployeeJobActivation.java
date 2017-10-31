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
import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.taowi.hcm.core.model.X_HC_JobFunction;
import org.taowi.hcm.payroll.model.MHCAttribute;
import org.taowi.hcm.payroll.model.MHCPayComponent;
import org.toba.psd.model.PSD_MSysConfig;
import org.toba.psd.model.X_HC_Mutation;

public class PSD_EmployeeJobActivation extends SvrProcess{
	//Comment: Ask jorvan why must define these three employeeClasses for NIK
	private static final String Probation = "BP";
	//private static final String Contract  = "TK";
	//private static final String Permanent = "TP";
	
	private int HC_EmployeeJob_ID = 0;
	
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
		
		HC_EmployeeJob_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		String msg = "";
		
		if(HC_EmployeeJob_ID <= 0)
			throw new AdempiereException("Error: Employee Job is not Selected");
		
		MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());

		MEmployee employee = new MEmployee(getCtx(), employeeJob.getHC_Employee_ID(), get_TrxName());
		
		if(employee.get_Value("HC_Status").equals(MEmployee.HC_STATUS_Pending))
			throw new AdempiereException("Error: Employee is not active");
		
		if(employeeJob.getHC_Job_ID() <= 0)
			throw new AdempiereException("Error: Field Job is empty");
		
		if(employeeJob.getHC_Org_ID() <= 0)
			throw new AdempiereException("Error: Field HC Organization is empty");
		
		int HC_EmployeeJob_ID  = 0;
		MJob job = new MJob(getCtx(), employeeJob.getHC_Job_ID(), get_TrxName());
		X_HC_JobFunction function = new X_HC_JobFunction(getCtx(), job.getHC_JobFunction_ID(), get_TrxName());
		
		//check if employeejob seqNo = 1 and isHeadOfficial then set all employeeJob seqNo = 2 to be Inactive
		if(employeeJob.getSeqNo()== 1) {
			if(function.get_ValueAsBoolean("isHeadOfficial")) {
				List<MEmployeeJob> employeeJobs = new ArrayList<MEmployeeJob>();
				String query = "HC_EmployeeJob.HC_Job_ID = ? AND HC_EmployeeJob.SeqNo!=1"
						+ " AND HC_EmployeeJob.HC_Status ='"+MEmployeeJob.HC_STATUS_Active+"' AND HC_EmployeeJob.IsActive='Y'"
						+ " AND emp.HC_Status='"+MEmployee.HC_STATUS_Active+"'";
				employeeJobs = new Query(getCtx(), MEmployeeJob.Table_Name, query, get_TrxName())
								.addJoinClause("JOIN HC_Employee emp ON HC_EmployeeJob.HC_Employee_ID = emp.HC_Employee_ID")
								.setParameters(employeeJob.getHC_Job_ID())
								.list();
				int m_employee_ID = 0;
				if(employeeJobs.size() > 0) {
					for(int i = 0 ; i < employeeJobs.size(); i++) {
						MEmployeeJob empJob = employeeJobs.get(i);
						empJob.setHC_Status("IA");
						empJob.setIsActive(false);
						empJob.setHC_JobAction(null);
						empJob.saveEx();
					}
					m_employee_ID = employeeJobs.get(0).getHC_Employee_ID();
				}
				
				//reset manager for other employees related to previous manager into this employee
				//if this employee is manager
				query = "HC_Manager_ID = ? AND "
						+ "HC_Status = '"+MEmployeeJob.HC_STATUS_Active+"' AND IsActive='Y'";
				employeeJobs = new Query(getCtx(),MEmployeeJob.Table_Name,query,get_TrxName())
								.setParameters(m_employee_ID)
								.list();
				if(employeeJobs.size() > 0) {
					for(int i = 0 ; i < employeeJobs.size();i++) {
						MEmployeeJob empJob = employeeJobs.get(i);
						empJob.setHC_Manager_ID(employee.get_ID());
						empJob.saveEx();
					}
				}
			}
		}else{
			//if employeeJob isHeadOfficial and not seqNo= 1
			if(function.get_ValueAsBoolean("isHeadOfficial")){
				String query = "HC_EmployeeJob.HC_Job_ID = ? AND HC_EmployeeJob.SeqNo=1"
						+ " AND HC_EmployeeJob.HC_Status ='"+MEmployeeJob.HC_STATUS_Active+"' AND HC_EmployeeJob.IsActive='Y'"
						+ " AND emp.HC_Status='"+MEmployee.HC_STATUS_Active+"'";
				HC_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, query, get_TrxName())
									.addJoinClause("JOIN HC_Employee emp ON HC_EmployeeJob.HC_Employee_ID = emp.HC_Employee_ID")
									.setParameters(employeeJob.getHC_Job_ID())
									.firstId();
				
				if(HC_EmployeeJob_ID > 0)
					throw new AdempiereException("Error:Can't activate employeeJob due there is still Active Employee Job Sequence 1");
				
				//getPreviousManager
				query = "HC_Job_ID = ? AND HC_Status = 'IA' AND IsActive='N' AND HC_EmployeeJob.SeqNo= 1";
				HC_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, query, get_TrxName())
									.setParameters(employeeJob.getHC_Job_ID())
									.setOrderBy("HC_WorkStartDate DESC")
									.firstId();
				
				MEmployeeJob m_employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
				MEmployee emp = new MEmployee(getCtx(), m_employeeJob.getHC_Employee_ID(), get_TrxName());
				
				query = "HC_Manager_ID = ? AND SeqNo='1' AND IsActive ='Y' AND HC_Status='"+MEmployeeJob.HC_STATUS_Active+"'";
				List<MEmployeeJob> empJobs = new ArrayList<MEmployeeJob>();
				empJobs = new Query(getCtx(), MEmployeeJob.Table_Name, query, get_TrxName())
						 .setParameters(emp.getHC_Employee_ID())
						 .list();
				
				if(empJobs.size()>0) {
					for(int i = 0 ; i < empJobs.size(); i++) {
						MEmployeeJob empJob = empJobs.get(i);
						empJob.setHC_Manager_ID(employee.getHC_Employee_ID());
						empJob.saveEx();
					}//endFor
				}//endIf
			}//endIf	
		}//endElse
		
		Calendar calToday = Calendar.getInstance();
		calToday.setTimeInMillis(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(calToday.get(Calendar.DATE) == 29 && calToday.get(Calendar.MONTH) == 2)
			throw new AdempiereException( "Error: Can't activate Job in 29 February");
		
		employeeJob.setHC_Status	(MEmployeeJob.HC_STATUS_Active);
		employeeJob.setHC_JobAction	(MEmployeeJob.HC_JOBACTION_Hire);
		employeeJob.set_ValueOfColumn("Processed", true);
		employeeJob.set_ValueOfColumn("IsActive" , true);
		employeeJob.saveEx();
		
		if(employeeJob.getSeqNo() == 1 && employeeJob.get_ValueAsBoolean("MemberKoperasi") == true) {
			employee.setEffectiveDateFrom(Timestamp.valueOf(sdf.format(calToday.getTime()) + " 00:00:00.0"));
			employee.saveEx();
		}
		
		Calendar CalWorkStartDate = Calendar.getInstance();
		CalWorkStartDate.setTime(sdf.parse(employee.getHC_WorkStartDate().toString()));
		
		//setEmployeeNIK
		msg = createNIK(employeeJob, employee, (Calendar)CalWorkStartDate.clone());
		createMutation(employeeJob);
		createBaseSalaryPayComponent(employeeJob);
		resetManager(employeeJob, employee);
		return msg;
	}//doIt
	
	/**
	 * reset manager from related employee with job report to this employee
	 * @param employeeJob
	 * @param employee
	 * @param CalWorkStartDate
	 * 
	 */
	public void resetManager(MEmployeeJob employeeJob, MEmployee employee) {
		List<MEmployeeJob> EmployeeJobs = new ArrayList<MEmployeeJob>();
		String query = "SELECT "+MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID+" FROM "+MEmployeeJob.Table_Name+" empJob "
				+ "LEFT JOIN "+MJob.Table_Name+" job ON job."+MJob.COLUMNNAME_HC_Job_ID+"=empJob."+MEmployeeJob.COLUMNNAME_HC_Job_ID+" "
				+ "WHERE empJob."+MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"' AND "
				+ "empJob."+MEmployeeJob.COLUMNNAME_IsActive+"='Y' AND "
				+ "job."+MJob.COLUMNNAME_HC_JobReportTo_ID+"=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(query, get_TrxName());
			pstmt.setInt(1, employeeJob.getHC_Job_ID());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int HC_EmployeeJob_ID = rs.getInt(1);
				MEmployeeJob empJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
				EmployeeJobs.add(empJob);
			}
		} catch (SQLException e){
			log.log(Level.SEVERE, "not found related employee Job in Import job data change with employee "+ employee.getName());
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if(EmployeeJobs.size() > 0) {
			for(MEmployeeJob m_employeeJob : EmployeeJobs) {
				m_employeeJob.setHC_Manager_ID(employee.get_ID());
				m_employeeJob.saveEx();
			}
		}
	}
	
	/**
	 * Create NIK for Employee and Employee's C_BPartner
	 * Author KevinY
	 * @param employeeJob
	 * @param employee
	 * @param calToday
	 * @return
	 * String Message
	 */
	public String createNIK(MEmployeeJob employeeJob, MEmployee employee, Calendar CalWorkStartDate) {
		String NIK = "";
		
		X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(), employeeJob.getHC_EmployeeClass_ID(), get_TrxName());
		
		//check if employeeJob is for creating NIK, seqNo = 1, and NIK from employee still empty
		if(empClass.get_ValueAsBoolean("isNIK") && employeeJob.getSeqNo() == 1 && employee.get_Value("HC_NIK")== null){
			
			String year = ((Integer)CalWorkStartDate.get(Calendar.YEAR)).toString();
			NIK = NIK + empClass.getDescription();
			
			//if employee class is Probation then use 00 not two last digits of year
			if(!empClass.getDescription().equalsIgnoreCase(Probation))
				NIK = NIK+ year.substring(year.length()-2, year.length()-1) + year.substring(year.length()-1, year.length());
			else
				NIK = NIK+ "00";
			
			//get Last sequence Employee
			String whereClause = "HC_NIK LIKE '"+NIK+"%'";
			int EmployeeID = new Query(getCtx(), MEmployee.Table_Name, whereClause, get_TrxName())
							 .setOrderBy("HC_NIK DESC")
							 .firstId();
			
			//set the next NIK sequence with searching last NIK by using employeeClass Description and two digit year
			if(EmployeeID > 0){
				MEmployee emp = new MEmployee(getCtx(), EmployeeID, get_TrxName());
				String NIK_LastEmp = emp.get_ValueAsString("HC_NIK");
				int sequenceLastNo = Integer.parseInt(NIK_LastEmp.substring(NIK_LastEmp.length()-3, NIK_LastEmp.length()));
				sequenceLastNo++;
				if(sequenceLastNo < 10)
					NIK = NIK + "00" + sequenceLastNo;
				else if(sequenceLastNo < 100 && sequenceLastNo >= 10)
					NIK = NIK + "0"+ sequenceLastNo;
				else if(sequenceLastNo >=100 && sequenceLastNo <= 999)
					NIK = NIK + sequenceLastNo;
			}else{
				NIK = NIK + "001";
			}
			
			employee.set_ValueOfColumn("HC_NIK", NIK);
			employee.setValue(employee.get_ValueAsString("HC_NIK"));
			employee.saveEx();
			
			//set C_BPartner's value from employee's value if employee has C_BPartner_ID w
			int C_BPartner_ID = 0;
			if(employee.get_Value("C_BPartner_ID") != null){
				C_BPartner_ID = employee.getC_BPartner_ID();
				MBPartner bpartner = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
				bpartner.setValue(NIK);
				bpartner.saveEx();
			}
			
			employee.saveEx();
			return "Sucess Activate EmployeeJob and NIK Generated";
		}else{
			//if HC_NIK from employee not null and employeejob seq no 1 and employee class for created NIK
			if(empClass.get_ValueAsBoolean("isNIK") && employeeJob.getSeqNo() == 1 && employee.get_Value("HC_NIK")!= null) {
				int C_BPartner_ID = 0;
				if(employee.get_Value("C_BPartner_ID") != null){
					C_BPartner_ID = employee.getC_BPartner_ID();
					MBPartner bpartner = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
					bpartner.setValue(employee.getValue());
					bpartner.saveEx();
				}
				return "Success Activate EmployeeJob and C_BPartner value is set for employee value";
			}
			return "Sucess Activate EmployeeJob and please fill NIK in Employee Data";
		}
	}//createNIK
	
	/**
	 * Author Kevin Yulianto
	 * set HC_Mutation from EmployeeJob
	 * @param employeeJob
	 */
	public void createMutation(MEmployeeJob employeeJob) {
		X_HC_Mutation mutation = new X_HC_Mutation(getCtx(), 0, get_TrxName());
		mutation.setHC_Employee_ID(employeeJob.getHC_Employee_ID());
		mutation.setHC_Job_ID(employeeJob.getHC_Job_ID());
		mutation.setNomorSK(employeeJob.get_ValueAsString("NomorSK"));
		mutation.setDate1(employeeJob.getHC_WorkStartDate());
		mutation.setIsActive(true);
		mutation.saveEx();
	}//createMutation
	
	
	/**
	 * Author Stephan Christ
	 * Set Base Salary from EmployeeJob to Pay Component
	 * @param employeeJob
	 */
	public void createBaseSalaryPayComponent(MEmployeeJob employeeJob) {
		//set Attribute validfrom at SK day if IsDifferentPayCalculation = 'Y'
		int HC_DaySalary = 0;
		String WorkStartDateNew = "";
		Calendar calWorkStartDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(), employeeJob.getHC_EmployeeClass_ID(), get_TrxName());
		
		//
		if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
			HC_DaySalary = (Integer)empClass.get_Value("HC_PayrollDay");
			
			Timestamp HC_WorkStartDateNew = (Timestamp)employeeJob.get_Value("HC_WorkStartDate");
			int daysInMonth = TimeUtil.getNumberOfDaysInMonth(TimeUtil.addMonths(HC_WorkStartDateNew, -1));
			//int daysInMonth = Integer.valueOf((TimeUtil.addMonths(HC_WorkStartDateNew, -1)).toString().substring(8, 10));
			//int daysInMonth = Integer.valueOf((HC_WorkStartDateNew.toString().substring(8, 10)));
			//comment : Use for checking workstartDate in february
			if(HC_DaySalary > daysInMonth){
				HC_DaySalary = daysInMonth;
				//HC_WorkStartDateNew = TimeUtil.addMonths(HC_WorkStartDateNew,-1);
			}
			HC_WorkStartDateNew = TimeUtil.addMonths(HC_WorkStartDateNew,-1);
			
			try {
				calWorkStartDate.setTime(sdf.parse(HC_WorkStartDateNew.toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			WorkStartDateNew = calWorkStartDate.get(Calendar.YEAR) + "-";
			if(calWorkStartDate.get(Calendar.MONTH+1) < 10){
				WorkStartDateNew = WorkStartDateNew + "0"+(calWorkStartDate.get(Calendar.MONTH)+1)+"-";
			}else{
				WorkStartDateNew = WorkStartDateNew + (calWorkStartDate.get(Calendar.MONTH)+1)+"-";
			}
			
			if(HC_DaySalary < 10)
				WorkStartDateNew = WorkStartDateNew + "0"+ HC_DaySalary + " 00:00:00.0";
			else
				WorkStartDateNew = WorkStartDateNew + HC_DaySalary + " 00:00:00.0";
		}

		//change taowi code into idempiere cde
		/*String sql = "SELECT HC_PayComponent_ID FROM HC_PayComponent WHERE Value=?";
		int payComponent_ID = DB.getSQLValue(get_TrxName(), sql	, new Object[]{"BASE_SALARY"});
		MHCPayComponent payComponent = new MHCPayComponent(getCtx(), payComponent_ID, get_TrxName());
		*/
		MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PSD_MSysConfig.getValue(PSD_MSysConfig.PAY_COMPONENT_BASE_SALARY));
		MHCAttribute attribute = new MHCAttribute(getCtx(), 0, get_TrxName());
		
		attribute.setHC_PayComponentType(MHCAttribute.HC_PAYCOMPONENTTYPE_Numeric);
		attribute.setHC_PayComponent_ID(payComponent.get_ID());
		attribute.setHC_Employee_ID(employeeJob.getHC_Employee_ID());
		attribute.setHC_NumValue(employeeJob.getHC_Compensation1());
		attribute.setAD_Org_ID(employeeJob.getAD_Org_ID());
		if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
			attribute.setValidFrom(Timestamp.valueOf(WorkStartDateNew));
		}else
			attribute.setValidFrom(employeeJob.getHC_WorkStartDate());
		attribute.saveEx();
	}//createBaseSalaryPayComponent
	
}//endClass
