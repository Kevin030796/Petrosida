package org.toba.psd.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;

/**
 * @author KevinY
 * Process to Renew Work Period base on current year
 * PSD - #2806
 */
public class PSD_EmployeeDataWorkPeriod extends SvrProcess{
	
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

		List<MEmployee> employees = new ArrayList<MEmployee>();
		employees = new Query(getCtx(), MEmployee.Table_Name, "", get_TrxName())
					.list();
		
		if(employees.size() > 0){
			SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd");
			Calendar calToday 		= Calendar.getInstance();
			Calendar calStartDate 	= Calendar.getInstance();
			calToday.setTimeInMillis(System.currentTimeMillis());
			int calc = 0;
			
			for(int i = 0 ; i < employees.size(); i++){
				if(employees.get(i).getHC_WorkStartDate() != null){
					String startDate = employees.get(i).getHC_WorkStartDate().toString();
					calStartDate.setTime(sdf.parse(startDate));
					
					calc = calToday.get(Calendar.YEAR)  - calStartDate.get(Calendar.YEAR);
					
					if(calToday.get(Calendar.MONTH) < calStartDate.get(Calendar.MONTH))
						calc = calc - 1;
					else if(calToday.get(Calendar.MONTH) == calStartDate.get(Calendar.MONTH))
						if(calToday.get(Calendar.DATE) < calStartDate.get(Calendar.DATE))
							calc = calc - 1;
			
					employees.get(i).set_ValueOfColumn("HC_WorkPeriodDate", calc +"");
					employees.get(i).saveEx();
				}//EndIf
			}//EndFor
		}//EndIf
		return "";
	}//doIt
	
}//endDate
