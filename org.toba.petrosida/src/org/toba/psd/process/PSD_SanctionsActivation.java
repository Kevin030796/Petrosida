package org.toba.psd.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.toba.psd.model.MSanctions;


/**
 * @author KevinY
 * Process for HC_Sanctions
 * PSD - 2774 
 */
public class PSD_SanctionsActivation extends SvrProcess{

	private int p_HC_Sanctions_ID = 0;
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
		p_HC_Sanctions_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_Sanctions_ID <= 0)
			throw new AdempiereException("Error: Pelanggaran is not selected");
		
		Calendar calToday 		= Calendar.getInstance();
		Calendar calEndDate		= Calendar.getInstance();
		Calendar calStartDate   = Calendar.getInstance();
		SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd");
		String endDate			= null;
		String startDate		= null;
	
		MSanctions m_Sanction = new MSanctions(getCtx(), p_HC_Sanctions_ID, get_TrxName());
		MEmployee  m_Employee = new MEmployee(getCtx() , m_Sanction.getHC_Employee_ID(), get_TrxName());
		
		if(m_Sanction.get_Value(MSanctions.COLUMNNAME_EndDate) == null)
			throw new AdempiereException("Error: Pelanggaran doesn't have end date specified");
	
		if(m_Sanction.get_ValueAsBoolean("IsSanctionActive"))
			throw new AdempiereException("Error: Pelanggaran has been already active");
		
		//get endDate, StartDate, todayDate
		endDate 	= m_Sanction.get_Value(MSanctions.COLUMNNAME_EndDate).toString();
		startDate 	= m_Sanction.getStartDate().toString();
		calEndDate.setTime(sdf.parse(endDate));
		calStartDate.setTime(sdf.parse(startDate));
		calToday.setTimeInMillis(System.currentTimeMillis());
		
		//check today is between calStartDate and before calEndDate
		int check = 0;
		if(isNotBetweenDate((Calendar)calToday.clone(),(Calendar)calStartDate.clone(), (Calendar)calEndDate.clone())) {
			m_Sanction.set_ValueOfColumn("IsSanctionActive", false);
			check = 1;
		}else{
			//set all active sanctions into inactive 
			int[] m_Sanction_IDs = new Query(getCtx(), MSanctions.Table_Name, "HC_Employee_ID=? AND IsSanctionActive='Y'", get_TrxName())
			   .setParameters(new Object[]{m_Employee.get_ID()})
			   .getIDs();
			
			if(m_Sanction_IDs.length > 0){
				for(int m_Sanction_ID : m_Sanction_IDs){
					MSanctions m_temp_sanction = new MSanctions(getCtx(), m_Sanction_ID, get_TrxName());
					m_temp_sanction.set_ValueOfColumn("IsSanctionActive", false);
					m_temp_sanction.saveEx();
				}
			}
			
			m_Sanction.set_ValueOfColumn("IsSanctionActive", true);
			m_Employee.set_ValueOfColumn("isSanctions"	   , true);
		}
		m_Sanction.saveEx();
		m_Employee.saveEx();
		
		if(check == 1) 
			return "Process Activate Sanction completed and sanction not active due to not between date start and end date";
		else 
			return "Process Activate Sanction completed and sanction is active";
	}//doIt
	
	/**
	 * Checking the check date between start and end date
	 * @param check
	 * @param start
	 * @param End
	 * @return
	 * true if check is between start and end date else false
	 */
	private boolean isNotBetweenDate(Calendar check, Calendar start, Calendar End){
		if( check.compareTo(start)< 0 || check.compareTo(End) >= 0)
			return true;
		else
			return false;
	}//isNotBetweenDate
	
}//endClass
