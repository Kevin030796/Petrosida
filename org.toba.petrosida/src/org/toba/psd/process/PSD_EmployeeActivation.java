package org.toba.psd.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;

/**
 * @author KevinY
 * Process for HC_Employee
 */
public class PSD_EmployeeActivation extends SvrProcess{
	private int HC_Employee_ID = 0;
	
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
		
		HC_Employee_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_Employee_ID <= 0)
			throw new AdempiereException("Error: Employee Data is not Selected");
		
		MEmployee employee = new MEmployee(getCtx(),HC_Employee_ID , get_TrxName());
		
		employee.setHC_Status(MEmployee.HC_STATUS_Active);
		employee.setIsActive(true);
		employee.saveEx();
		
		return "Success Activate Employee";
	}//doIt

}//endClass
