package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.X_HC_RequestOvertime;



/**
 * @author KevinY
 * Process for HC_RequestOverTime
 */
public class PSD_ApprovalOvertimeProcessNow extends SvrProcess{
	
	private String p_Status = "";
	private int HC_RequestOvertime_ID = 0;
	
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
		
		HC_RequestOvertime_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_RequestOvertime_ID <= 0)
			throw new AdempiereException("Error: Request Overtime is not selected");
		
		X_HC_RequestOvertime overtime = new X_HC_RequestOvertime(getCtx(), HC_RequestOvertime_ID, get_TrxName());
		//check if parameter status same with document status(look at the status of document permit)
		if(p_Status.equals(overtime.getStatus())) {
			if(p_Status.equals(X_HC_RequestOvertime.STATUS_Accepted))
				throw new AdempiereException("Error: Already accepted");
			else if(p_Status.equals(X_HC_RequestOvertime.STATUS_Rejected))
				throw new AdempiereException("Error: Already Rejected");
		}
		
		Timestamp today = new Timestamp(System.currentTimeMillis());
	
		int normalhour1 = (new BigDecimal(overtime.get_Value("HC_1NormalHour").toString())).intValue();
		int holidayHour8 = (new BigDecimal(overtime.get_Value("HC_8HolidayHour").toString())).intValue();
		if((normalhour1 <= 0 && holidayHour8 <= 0) || (overtime.get_Value("HC_1NormalHour") == null
				&& overtime.get_Value("HC_8HolidayHour")== null)) {
			throw new AdempiereException("Error: HC_1NormalHour and HC_8HolidayHour is zero or empty");
		}
		overtime.setApprovalDate(today);
		overtime.setApprovalTime(today);
		overtime.setStatus(p_Status);
		overtime.saveEx();
		
		return "Success " + p_Status;
	}//doIt
	
}//endClass
