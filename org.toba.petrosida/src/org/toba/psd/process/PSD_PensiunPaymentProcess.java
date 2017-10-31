package org.toba.psd.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.X_HC_EmployeeRetirement;

public class PSD_PensiunPaymentProcess extends SvrProcess{
	
	private int HC_Retirement_ID = 0;
	private final static String retirementStatus = "Edc";
	private final static String paymentPensiunStatus = "Ppm";
	
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
	}

	@Override
	protected String doIt() throws Exception {
		
		if(HC_Retirement_ID <= 0)
			throw new AdempiereException("Error: Document Pensiun is not selected");
	
		X_HC_EmployeeRetirement retirement = new X_HC_EmployeeRetirement(getCtx(), HC_Retirement_ID, get_TrxName());
		if(!retirement.getStatus().equals(retirementStatus) && !retirement.getStatus().equals(paymentPensiunStatus)) {
			throw new AdempiereException("Error: Document Pensiun is not processed");
		}
		
		if(retirement.getStatus().equals(paymentPensiunStatus)) {
			throw new AdempiereException("Error: Document Pensiun has already processed to calculate payment pensiun");
		}
		
		
		//MEmployee employee = new MEmployee(getCtx(), retirement.getHC_Employee_ID(),get_TrxName());
		
		retirement.setStatus(paymentPensiunStatus);
		retirement.saveEx();
		
		return "";
	}//doIt

}//endClass
