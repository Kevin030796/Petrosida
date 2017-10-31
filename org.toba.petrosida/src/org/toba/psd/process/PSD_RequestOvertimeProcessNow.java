package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.toba.psd.model.X_HC_RequestOvertime;

/**
 * @author KevinY
 * Process for HC_RequestOverTime
 */
public class PSD_RequestOvertimeProcessNow extends SvrProcess{
	
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
			if(p_Status.equals(X_HC_RequestOvertime.STATUS_Requested))
				throw new AdempiereException("Error: Already requested");
			else if(p_Status.equals(X_HC_RequestOvertime.STATUS_Cancelled))
				throw new AdempiereException("Error: Already Cancelled");
		}
		
		Timestamp today = new Timestamp(System.currentTimeMillis());
	
		overtime.setDateTrx(today);
		overtime.setTimeTrx(today);
		overtime.setStatus(p_Status);
		overtime.saveEx();
		
		BigDecimal totalOvertimeHour = overtime.getTotalOvertimeHour();
		int totalOvertime = totalOvertimeHour.intValue();
		if(totalOvertime >= 1) 
			overtime.set_CustomColumn("HC_1NormalHour", Env.ONE);
		if(totalOvertime > 1)
			overtime.set_CustomColumn("HC_2NormalHour", totalOvertimeHour.subtract(Env.ONE));
		if(overtime.get_ValueAsBoolean("IsHoliday")){
			if(totalOvertime < 8 ){
				overtime.set_CustomColumn("HC_8HolidayHour", totalOvertimeHour);
			}else if(totalOvertime >= 8){
				overtime.set_CustomColumn("HC_8HolidayHour", new BigDecimal(8));
			}
			
			if(totalOvertime >= 9){
				overtime.set_CustomColumn("HC_9HolidayHour", Env.ONE);
			}
			
			if(totalOvertime > 9) {
				overtime.set_CustomColumn("HC_10HolidayHour", totalOvertimeHour.subtract(new BigDecimal(9)));
			}
		}
		overtime.saveEx();
		return "";
	}//doIt
	
}//endClass
