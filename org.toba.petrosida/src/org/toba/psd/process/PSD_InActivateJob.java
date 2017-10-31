package org.toba.psd.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;

/**
 * 
 * @author KevinY
 * Process For HC_EmployeeJob
 */
public class PSD_InActivateJob extends SvrProcess{
	
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
		
		if(HC_EmployeeJob_ID <= 0)
			throw new AdempiereException("Error: Employee Job is not selected");
		
		MEmployeeJob empJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
		MEmployee employee  = new MEmployee(getCtx(), empJob.getHC_Employee_ID(), get_TrxName());
		
		if(empJob.getHC_Job_ID() <= 0)
			throw new AdempiereException("Error: Job is not defined in employee job selected");
		
		if(empJob.get_ValueAsBoolean("isActive") == false)
			throw new AdempiereException("Error: can't inactivate non active employee job");
		
		String HC_Status = empJob.getHC_Status();
		String JobAction = empJob.get_Value("HC_JobAction").toString();
		
		if(!HC_Status.equals(MEmployeeJob.HC_STATUS_Active) && JobAction == null) {
			throw new AdempiereException("Error: Employee Job is not active or hired");
		}else { 
			//int HC_Job_ID = empJob.getHC_Job_ID();
			if(empJob.getSeqNo() == 1 && empJob.get_ValueAsBoolean("MemberKoperasi") == true) 
				employee.set_ValueOfColumn("HC_Org_ID", null);
				
			empJob.setHC_Status("IA");
			empJob.set_ValueOfColumn("HC_JobAction", null);
			empJob.setIsActive(false);
			empJob.saveEx();
			
		}//endElse
		return "Success Inactivate Employee Job";
	}//doIt

}
