package org.toba.psd.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.toba.psd.model.MSanctions;


/**
 * Process Inactivate HC_Sanctions (Scheduler)
 * @author KevinY
 * Inactivate HC_Sanctions
 */
public class PSD_InactivateSanction extends SvrProcess{

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
	}//prepare

	@Override
	protected String doIt() throws Exception {
		int[] sanction_IDs = new Query(getCtx(), MSanctions.Table_Name, "", get_TrxName())
							.getIDs();
		
		Calendar calToday 		= Calendar.getInstance();
		Calendar calEndDate		= Calendar.getInstance();
		Calendar calStartDate   = Calendar.getInstance();
		SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd");
		String endDate			= null;
		String startDate		= null;
		String whereClause 		= null;
		int    HC_Employee_ID 	= 0;
		
		if(sanction_IDs.length > 0) {
			for(int sanction_ID : sanction_IDs) {
				MSanctions sanction = new MSanctions(getCtx(), sanction_ID, get_TrxName());
				//set EndDate, StartDate, today
				
				endDate 	= sanction.get_Value(MSanctions.COLUMNNAME_EndDate).toString();
				startDate 	= sanction.getStartDate().toString();
				calEndDate.setTime(sdf.parse(endDate));
				calStartDate.setTime(sdf.parse(startDate));
				calToday.setTimeInMillis(System.currentTimeMillis());
				
				if(sanction.get_ValueAsBoolean("IsSanctionActive")) {
					HC_Employee_ID 		= sanction.getHC_Employee_ID();
					MEmployee employee 	= new MEmployee(getCtx(), HC_Employee_ID, get_TrxName());
					
					//check today is before startDate or endDate or after endDate 
					if(isNotBetweenDate(calToday, calStartDate, calEndDate)) {
						sanction.set_ValueOfColumn("IsSanctionActive", false);
						sanction.saveEx();
					}
					
					//checking any employee sanctions still active
					whereClause = "HC_Employee_ID = ? AND IsSanctionActive='Y'";
					int[] HC_Sanction_IDs = new Query(getCtx(), MSanctions.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{HC_Employee_ID})
											.getIDs();
					
					//set isSanctions false if no sanction active
					if(HC_Sanction_IDs.length <= 0) {
						employee.set_ValueOfColumn("IsSanctions", false);
						employee.saveEx();
					}
					
				}//endIf
			}//endFor
		}
		return "Success Inactivate Sanction";
	}//doIt
	
	/**
	 * Checking the check date between start  and end date
	 * @param check
	 * @param start
	 * @param End
	 * @returns
	 * true if check date is between start and end date else false
	 */
	private boolean isNotBetweenDate(Calendar check, Calendar start, Calendar End){
		if( check.compareTo(start)< 0 || check.compareTo(End) >= 0)
			return true;
		else
			return false;
	}//isNotBetweenDate
	
}//endClass
