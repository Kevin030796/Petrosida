package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.MRequestPermit;


/**
 * 
 * @author Kevin Yulianto
 * Process For Approval Permit Process Now 
 * Approve & Rejected
 */
public class PSD_ApprovalPermitProcessNow extends SvrProcess{
	private String p_Status = "";
	private int HC_RequestPermit_ID = 0;
	
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
			throw new AdempiereException("Error: Request Overtime is not selected");
		
		MRequestPermit permit = new MRequestPermit(getCtx(), HC_RequestPermit_ID, get_TrxName());
		//check if parameter status same with document status(look at the status of document permit)
		if(p_Status.equals(permit.getStatus())) {
			if(p_Status.equals(MRequestPermit.STATUS_Rejected))
				throw new AdempiereException("Error: Already rejected");
			else if(p_Status.equals(MRequestPermit.STATUS_Accepted))
				throw new AdempiereException("Error: Already Cancelled");
		}
		
		Timestamp today = new Timestamp(System.currentTimeMillis());
		double potonganUpah = 0.0;
		potonganUpah = permit.CalculatePayDeduction();
		permit.set_ValueOfColumn("NilaiPotonganUpah", new BigDecimal(potonganUpah));
		permit.setApprovalDate(today);
		permit.setApprovalTime(today);
		permit.setStatus(p_Status);
		permit.saveEx();
		
		return "Success " + p_Status + " permit";
	}//doIt
}//endClass
