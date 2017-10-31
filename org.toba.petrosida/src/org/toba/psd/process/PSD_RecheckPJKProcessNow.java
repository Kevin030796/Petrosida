package org.toba.psd.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.X_HC_PJK_TravelRequest;

public class PSD_RecheckPJKProcessNow extends SvrProcess{
	private int p_HC_PJK_TravelRequest_ID = 0;
	
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
		
		p_HC_PJK_TravelRequest_ID = getRecord_ID();
	}//prepare
	
	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_PJK_TravelRequest_ID <= 0){
			throw new AdempiereException("Error: No PJK Travel Request selected");
		}
		
		X_HC_PJK_TravelRequest PJKtravelRequest = new X_HC_PJK_TravelRequest(getCtx(), p_HC_PJK_TravelRequest_ID, get_TrxName());
		if(!PJKtravelRequest.getStatus().equals(X_HC_PJK_TravelRequest.STATUS_Requested)){
			throw new AdempiereException("Error: Can't recheck travel request");
		}
		PJKtravelRequest.setStatus(X_HC_PJK_TravelRequest.STATUS_Drafted);
		PJKtravelRequest.setIsComplete(false);
		PJKtravelRequest.saveEx();
		return "";
	}//doit
}//endClass
