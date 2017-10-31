package org.toba.psd.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.toba.psd.model.X_HC_TrainingClass;


/**
 * @author KevinY
 * Training Class Process Now
 * @temporary@ still not used;
 */
public class PSD_TrainingClassProcessNow extends SvrProcess{
	
	private int HC_TrainingClass_ID = 0;
	
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
		HC_TrainingClass_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_TrainingClass_ID <= 0)
			throw new AdempiereException("Error: Training Class is not selected");
		
		X_HC_TrainingClass training = new X_HC_TrainingClass(getCtx(), HC_TrainingClass_ID, get_TrxName());
		
		//Get end of Time
		//Timestamp t_start = training.getStartTime();
		Timestamp t_end   = training.getEndTime();
		//String timeStart = (t_start.toString()).substring(11,19)+".000";
		String timeEnd   = (t_end.toString()).substring(11,19)+".000";
		
		//Get end of day
		//Timestamp d_start = training.getDateStart();
		Timestamp d_end   = training.getEndDate();
		//String dateStart = (d_start.toString()).substring(0,10)+" ";
		String dateEnd   = (d_end.toString()).substring(0,10)+" ";
		
		//Timestamp startDay = Timestamp.valueOf(dateStart + timeStart);
		Timestamp endDay   	= Timestamp.valueOf(dateEnd + timeEnd);
		Timestamp today 	= new Timestamp(System.currentTimeMillis());

		if(today.compareTo(endDay) >= 0){
			training.setProcessed(true);
			training.saveEx();
		}

		return "";
	}//doIt
	
}//endClass
