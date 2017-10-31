package org.toba.psd.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MJob;
import org.toba.psd.model.X_HC_ManpowerPlanning;

public class PSD_ManPowerPlanningProcessNow extends SvrProcess{
	private int p_HC_ManPowerPlanning_ID = 0;
	
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
		
		p_HC_ManPowerPlanning_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_ManPowerPlanning_ID <= 0)
			throw new AdempiereException("Error: Man Power planning not selected");
		
		X_HC_ManpowerPlanning mpp = new X_HC_ManpowerPlanning(getCtx(), p_HC_ManPowerPlanning_ID, get_TrxName());
		
		Calendar calPensiun 	= Calendar.getInstance();
		Calendar calToday		= Calendar.getInstance();
		calToday.setTimeInMillis(System.currentTimeMillis());
		String Pensiun 			= null;
		SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd");
		
		if(mpp.get_Value("HC_Job_ID")== null){
			throw new AdempiereException("Error: field Job is not filled");
		}
		
		int HC_Job_ID			= (int)mpp.get_Value("HC_Job_ID");
		MJob job = new MJob(getCtx(), HC_Job_ID, get_TrxName());
		
		
		String jobValue = job.getValue();
		String PjsJob = "Pjs. "+jobValue;// job Pjs
		String PjJob = "Pj. "+ jobValue;// job Pj
		
		int TotalIdeal			= Integer.valueOf(mpp.get_Value(X_HC_ManpowerPlanning.COLUMNNAME_TotalIdealKaryawan).toString());
		int TotalEmployee 		= 0;
		int TotalPensiun 		= 0;
		int TotalRequirement	= 0;
		
		String whereClause 	=  "(hcj.HC_Job_ID= ? OR job.Value in (?,?)) AND HC_Employee.HC_Status = 'A' AND hcj.HC_Status= 'A'";
		List<MEmployee> employees = new ArrayList<MEmployee>();
		employees = new Query(getCtx(), MEmployee.Table_Name, whereClause, get_TrxName())
				.setParameters(HC_Job_ID, PjJob, PjsJob)
				.addJoinClause(" JOIN HC_EmployeeJob hcj ON HC_Employee.HC_Employee_ID = hcj.HC_Employee_ID "
						+ "JOIN HC_Job job ON job.HC_Job_ID = hcj.HC_Job_ID")
				.list();
		
		TotalEmployee 		= employees.size();
		if(TotalEmployee> 0){
			for(int i = 0 ; i < TotalEmployee ; i++){
				Pensiun = employees.get(i).get_Value("PensiunDate").toString();
				try {
					calPensiun.setTime(sdf.parse(Pensiun));
				} catch (ParseException e) {
					log.info("Error: Can't convert Time");
				}
				
				if(calToday.get(Calendar.YEAR) == calPensiun.get(Calendar.YEAR))
					TotalPensiun +=1 ;
			}
			
			mpp.set_ValueOfColumn("TotalEmployee"		, (Integer)TotalEmployee);
			mpp.set_ValueOfColumn("TotalPensionEmployee", (Integer)TotalPensiun);
			TotalRequirement = TotalIdeal - (TotalEmployee - TotalPensiun);
			if(TotalRequirement  < 0)
				return "Error: Total Requirement can't be less than 0";
			mpp.set_ValueOfColumn("TotalRequirement"	, (Integer)TotalRequirement);
			mpp.saveEx();
		}
		
		mpp.set_ValueOfColumn("HC_Month", calToday.get(Calendar.MONTH));
		mpp.set_ValueOfColumn("HC_Year", calToday.get(Calendar.YEAR));
		mpp.saveEx();
		return "Success process Man Power Planning Now";
	}
}
