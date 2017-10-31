package org.toba.psd.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployeeDataChange;


/**
 * @author KevinY
 * PSD - 2778
 * Process Now Employee Data Change (Cancel, Request)
 */
public class PSD_EmployeeDataChangeProcessNow extends SvrProcess{
	
	private int    HC_EmployeeDataChange_ID = 0;
	private String p_Status = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if(name.equals("Status"))
				p_Status = para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		HC_EmployeeDataChange_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_EmployeeDataChange_ID <= 0)
			return "Error: Employee Data Change is not Selected";
		
		if(p_Status.equals(null))
			return "Error: Status Parameter is empty";
		
		MEmployeeDataChange EmployeeDataChange = new 
				MEmployeeDataChange(getCtx(), HC_EmployeeDataChange_ID, get_TrxName());
		
		EmployeeDataChange.set_ValueOfColumn("Status"	, p_Status);
		EmployeeDataChange.saveEx();
		
		return "Sucess processing data";
	}//doIt
}//endClass
