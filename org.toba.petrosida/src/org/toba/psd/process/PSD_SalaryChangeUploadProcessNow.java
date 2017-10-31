package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJobDataChange;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.taowi.hcm.payroll.model.MHCAttribute;
import org.taowi.hcm.payroll.model.MHCPayComponent;
import org.toba.psd.model.PSD_MSysConfig;
import org.toba.psd.model.X_HC_SalaryChange;

/**
 * @author KevinY
 * Process for Window Salary Change Upload
 */
public class PSD_SalaryChangeUploadProcessNow extends SvrProcess{

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
		//get All Job Data Change Salary Change
		
		String whereClause = "HC_JobAction ='"+MJobDataChange.HC_JOBACTION_SalaryChange+"' "
				+ "AND Processed= 'N' "
				+ "AND IsActive= 'Y'";
		int[] jobDataChange_IDs = new Query(getCtx(), MJobDataChange.Table_Name, whereClause, get_TrxName())
								.getIDs();
		
		if(jobDataChange_IDs.length > 0) {
			for(int jobDataChange_ID : jobDataChange_IDs) {
			
				MJobDataChange jdc = new MJobDataChange(getCtx(), jobDataChange_ID, get_TrxName());
				MEmployee emp = new MEmployee(getCtx(), jdc.getHC_Employee_ID(), get_TrxName());
				MEmployeeJob empJob = null;
				//getSeqNo=1, Active EmployeeJob
				whereClause = MEmployeeJob.COLUMNNAME_HC_Employee_ID + "=" + emp.get_ID()
						+" AND " + MEmployeeJob.COLUMNNAME_SeqNo+"=1 "
						+" AND " + MEmployeeJob.COLUMNNAME_IsActive+"='Y' "
						+" AND " + MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
				int HC_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, whereClause, get_TrxName())
					.setOnlyActiveRecords(true)
					.firstId();
				
				BigDecimal HC_Compensation1 = Env.ZERO;
				BigDecimal HC_Compensation2 = Env.ZERO;
				
				if(HC_EmployeeJob_ID <= 0) {
					throw new AdempiereException("Error: Employee doesn't have seqNo = 1 Job");
				}
				
				empJob 			 = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
				X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(), empJob.getHC_EmployeeClass_ID(), get_TrxName());
				
				HC_Compensation1 = empJob.getHC_Compensation1();
				HC_Compensation2 = jdc.getHC_Compensation2();

				if(jdc.getHC_JobAction().equals(MJobDataChange.HC_JOBACTION_SalaryChange) && 
						HC_Compensation1.compareTo(HC_Compensation2) == 0) 
					throw new AdempiereException("Error: Can't process new salary due to same value of previous salary");
				
				//set Attribute validfrom at SK day if IsDifferentPayCalculation = 'Y'
				int HC_DaySalary = 0;
				String WorkStartDateNew = "";
				Calendar calWorkStartDate = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
					HC_DaySalary = (Integer)empClass.get_Value("HC_PayrollDay");
					Timestamp HC_WorkStartDateNew = (Timestamp)jdc.get_Value("HC_WorkStartDate2");
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
						WorkStartDateNew = WorkStartDateNew + "0"+calWorkStartDate.get(Calendar.MONTH)+"-";
					}else{
						WorkStartDateNew = WorkStartDateNew + calWorkStartDate.get(Calendar.MONTH)+"-";
					}
					
					if(HC_DaySalary < 10)
						WorkStartDateNew = WorkStartDateNew + "0"+ HC_DaySalary + " 00:00:00.0";
					else
						WorkStartDateNew = WorkStartDateNew + HC_DaySalary + " 00:00:00.0";
				}
				
				//set EmployeeJob
				empJob.setHC_Compensation1(HC_Compensation2);
				empJob.set_CustomColumn("NomorSK", jdc.get_Value("NomorSK2"));
				empJob.setHC_WorkStartDate((Timestamp)jdc.get_Value("HC_WorkStartDate2"));
				empJob.setHC_Reason_ID(jdc.getHC_Reason_ID());
				empJob.setDescription(jdc.getDescriptionNew());
				empJob.setHC_JobAction(jdc.getHC_JobAction());
				empJob.saveEx();
				
				//set Perubahan Gaji
				X_HC_SalaryChange salaryChange = new X_HC_SalaryChange(getCtx(), 0, get_TrxName());
				salaryChange.setHC_Employee_ID(jdc.getHC_Employee_ID());
				salaryChange.setNomorSK(jdc.get_Value("NomorSK2").toString());
				salaryChange.set_ValueOfColumn("HC_JobDataChange_ID", jdc.getHC_JobDataChange_ID());
				salaryChange.setHC_Compensation1(jdc.getHC_Compensation2());
				salaryChange.setDescription(jdc.get_ValueAsString("DescriptionNew"));
				salaryChange.setDate1((Timestamp)jdc.get_Value("HC_WorkStartDate2"));
				salaryChange.setIsActive(true);
				salaryChange.saveEx();
				
				// set base salary to payroll component
				MHCPayComponent payComponent = MHCPayComponent.forValue(getCtx(), PSD_MSysConfig.getValue(PSD_MSysConfig.PAY_COMPONENT_BASE_SALARY));
				MHCAttribute attribute 		 = new MHCAttribute(getCtx(), 0, get_TrxName());
				attribute.setAD_Org_ID(jdc.getAD_Org_ID());
				attribute.setHC_PayComponent_ID(payComponent.get_ID());
				attribute.setHC_Employee_ID(jdc.getHC_Employee_ID());
				attribute.setHC_PayComponentType(MHCAttribute.HC_PAYCOMPONENTTYPE_Numeric);
				if(empClass.get_ValueAsBoolean("IsDifferentPayCalculation")){
					attribute.setValidFrom(Timestamp.valueOf(WorkStartDateNew));
				}else
					attribute.setValidFrom((Timestamp) jdc.get_Value("HC_WorkStartDate2"));
				attribute.setHC_NumValue(jdc.getHC_Compensation2());
				attribute.saveEx();
				
				//set Job Data Change Processed true
				jdc.setProcessed(true);
				jdc.setProcessing(true);
				jdc.saveEx();	
			}//endFor
		}//endIf
		
		return "Success SalaryChange Upload";
	}//DoIt
}//endClass
