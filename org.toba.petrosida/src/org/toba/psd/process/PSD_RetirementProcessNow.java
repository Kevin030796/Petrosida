package org.toba.psd.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.toba.psd.model.X_HC_EmployeeRetirement;

public class PSD_RetirementProcessNow extends SvrProcess {

	private int HC_Retirement_ID = 0;
	private final static String retirementStatus = "Edc";
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
		
		HC_Retirement_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_Retirement_ID <= 0)
			throw new AdempiereException("Error: Document Pensiun is not selected");
	
		X_HC_EmployeeRetirement retirement = new X_HC_EmployeeRetirement(getCtx(), HC_Retirement_ID, get_TrxName());
		if(retirement.getStatus().equals(retirementStatus)) {
			throw new AdempiereException("Error: Document Pensiun is already processed");
		}
		
		MEmployee employee = new MEmployee(getCtx(), retirement.getHC_Employee_ID(),get_TrxName());
		if(!employee.get_Value(MEmployee.COLUMNNAME_HC_Status).toString().equals(MEmployee.HC_STATUS_Active)) {
			throw new AdempiereException("Error: Employee status is not active");
		}
		
		//getEmployeeJob
		int HC_EmployeeJob_ID = employee.getActiveSequenceOneEmployeeJob();
		if(HC_EmployeeJob_ID <= 0)
			throw new AdempiereException("Error: not found employee job active sequence 1");
		
		MEmployeeJob employeeJob = new MEmployeeJob(getCtx(),HC_EmployeeJob_ID, get_TrxName());
		
		String retirementType = retirement.getRetirementType();
		
		employee.setHC_WorkEndDate(retirement.getRetirementDate());
		employee.setHC_Status(retirementType);
		employee.saveEx();
		
		employeeJob.setHC_Status("IA");
		employeeJob.setHC_JobAction("RTM");
		employeeJob.saveEx();
		
		retirement.setStatus(retirementStatus);
		retirement.saveEx();
		return "Success process retirement employee";
	}//doIt
}
