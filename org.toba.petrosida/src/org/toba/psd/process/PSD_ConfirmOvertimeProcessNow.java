package org.toba.psd.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.X_HC_RequestOvertime;

public class PSD_ConfirmOvertimeProcessNow extends SvrProcess{
	private String p_StatusConfirm = "Cnf";
	private int HC_RequestOvertime_ID = 0;
	
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
		
		HC_RequestOvertime_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		if (HC_RequestOvertime_ID <= 0 )
		{
			throw new AdempiereException("Error : Overtime not found");
		}
		
		X_HC_RequestOvertime reqOvertime = new X_HC_RequestOvertime(getCtx(), HC_RequestOvertime_ID, get_TrxName());
		reqOvertime.setStatus(p_StatusConfirm);
		reqOvertime.saveEx();
		
		return "";
	}//doIt
}
