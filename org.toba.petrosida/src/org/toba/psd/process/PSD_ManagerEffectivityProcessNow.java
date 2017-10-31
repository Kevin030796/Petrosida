package org.toba.psd.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.X_HC_EffectivityReport_Mgr;

public class PSD_ManagerEffectivityProcessNow extends SvrProcess{
	
	private int p_HC_ManagerEffectivityReport_ID = 0;
	
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
		p_HC_ManagerEffectivityReport_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_ManagerEffectivityReport_ID <= 0)
			throw new AdempiereException("Error: Employee Effectivity Report  is not selected");
		
		X_HC_EffectivityReport_Mgr ManagerEffectityReport = 
				new X_HC_EffectivityReport_Mgr(getCtx(), p_HC_ManagerEffectivityReport_ID, get_TrxName());
		
		if(ManagerEffectityReport.get_Value("Status").toString().equals("Cnf")){
			throw new AdempiereException("Error: Document has been Already Confirmed");
		}
		
		ManagerEffectityReport.set_ValueOfColumn("Status", "Cnf");
		ManagerEffectityReport.saveEx();
		
		return "Success Process Now";
	}//doIt
	
}//endClass