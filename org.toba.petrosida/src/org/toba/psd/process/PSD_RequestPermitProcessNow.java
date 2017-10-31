package org.toba.psd.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.MRequestPermit;
import org.toba.psd.model.X_HC_RequestPermit;


/**
 * @author KevinY
 * Process for HC_RequestPermit
 * Requested & Cancelled
 */
public class PSD_RequestPermitProcessNow extends SvrProcess{

	private String p_Status = "";
	private int HC_RequestPermit_ID = 0;
	/*private final static String Angkut = "TNJ_ANGKT_MAP";
	private final static String Upah ="BASE_SALARY";
	*/
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if(name.equals("Status"))
				p_Status 	   = para[i].getParameterAsString();	
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		HC_RequestPermit_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_RequestPermit_ID <= 0)
			throw new AdempiereException("Error: Request Permit is not selected");

		MRequestPermit permit = new MRequestPermit(getCtx(), HC_RequestPermit_ID, get_TrxName());
		
		//check if parameter status same with document status(look at the status of document permit)
		if(p_Status.equals(permit.getStatus())) {
			if(p_Status.equals(X_HC_RequestPermit.STATUS_Requested))
				throw new AdempiereException("Error: Already requested");
			else if(p_Status.equals(X_HC_RequestPermit.STATUS_Cancelled))
				throw new AdempiereException("Error: Already Cancelled");
		}
		
		String message = "";
		
		double potonganUpah = 0.0;
		potonganUpah = permit.CalculatePayDeduction();
		permit.set_ValueOfColumn("NilaiPotonganUpah", new BigDecimal(potonganUpah));
		permit.setStatus(p_Status);
		permit.saveEx();
		message = "Success ";
		if(p_Status.equals(X_HC_RequestPermit.STATUS_Requested)){
			message = message + "request permit";
		}else{
			message = message + "cancel permit";
		}
		return message;
	}//doIt
	
	
	
}//endClass
