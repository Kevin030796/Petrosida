package org.toba.psd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.X_HC_Org;
import org.toba.psd.model.X_HC_EffectivityReport;
import org.toba.psd.model.X_HC_EffectivityReport_Mgr;
import org.toba.psd.model.X_HC_JobLevel;
import org.toba.psd.model.X_HC_TrainingAttendance;
import org.toba.psd.model.X_HC_TrainingClass;

public class PSD_TrainingClassActivation extends SvrProcess{
	
	private int p_HC_TrainingClass_id = 0;
	
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
		
		p_HC_TrainingClass_id = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_TrainingClass_id <= 0)
			throw new AdempiereException("Error: Training Class not selected");
	
		X_HC_TrainingClass trainingClass = new X_HC_TrainingClass(
				getCtx(), p_HC_TrainingClass_id, get_TrxName());
		
		if(trainingClass.get_Value("Status").equals("Act")){
			throw new AdempiereException("Error: Training Class has already activated");
		}
		//looping all attendance and make document effectivity report 
		String sql = "SELECT "+X_HC_TrainingAttendance.COLUMNNAME_HC_TrainingAttendance_ID+" FROM "+X_HC_TrainingAttendance.Table_Name + " WHERE "
						+X_HC_TrainingAttendance.COLUMNNAME_IsCancelled+"='N' AND "
						+X_HC_TrainingAttendance.COLUMNNAME_HC_TrainingClass_ID+"=?";
		List<Integer> trainingAttendace_Ids = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt(1, trainingClass.get_ID());
			rs = pstmt.executeQuery();
			while(rs.next()){
				trainingAttendace_Ids.add(rs.getInt(1));
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, "Not found training attendance", e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	
		List<Integer> Managers = new ArrayList<Integer>();
		int DocumentEmployee = 0;
		int DocumentManager = 0;
		if(trainingAttendace_Ids.size() > 0)
		{
			
			for(int i = 0 ; i < trainingAttendace_Ids.size() ; i++){
				X_HC_TrainingAttendance m_trainingAttendance = new X_HC_TrainingAttendance(
						getCtx(), trainingAttendace_Ids.get(i), get_TrxName());
				
				//Get Job Level
				MEmployee employee = new MEmployee(getCtx(), m_trainingAttendance.getHC_Employee_ID(), get_TrxName());
				int employeeJobID = employee.getActiveSequenceOneEmployeeJob();
				if(employeeJobID <= 0)
					throw new AdempiereException("Error: Employee "+employee.getName()+" doesn't have seq 1 job active" );
				
				MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), employeeJobID, get_TrxName());
				MJob job = new MJob(getCtx(), employeeJob.getHC_Job_ID(), get_TrxName());
				X_HC_JobLevel jobLevel = new X_HC_JobLevel(getCtx(), job.getHC_JobLevel_ID(), get_TrxName());
				//end get Job Level
				
				if(jobLevel.getValue().equals("Manager"))
				{
					Managers = addManager(Managers, m_trainingAttendance.getHC_Employee_ID());
					createReportEmployee(m_trainingAttendance, employeeJob, trainingClass);
					DocumentEmployee++;
				}
				else
				{
					//create report employee
					createReportEmployee(m_trainingAttendance, employeeJob, trainingClass);
					DocumentEmployee++;
					//addManager
					X_HC_Org org = new X_HC_Org(getCtx(), employeeJob.getHC_Org_ID(), get_TrxName());
					if(org.get_Value(X_HC_Org.COLUMNNAME_HC_ManagerPosition_ID)!= null){
						final String whereClause = MEmployeeJob.COLUMNNAME_IsActive+"='Y' "
								+ "AND "+MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"' "
								+ "AND "+MEmployeeJob.COLUMNNAME_HC_Job_ID+"=? AND "+MEmployeeJob.COLUMNNAME_SeqNo+"=1";
						int HC_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, whereClause, get_TrxName())
												.setParameters(org.getHC_ManagerPosition_ID())
												.firstId();
						if(HC_EmployeeJob_ID > 0){
							MEmployeeJob managerEmployeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
							int HC_Manager_Id = managerEmployeeJob.getHC_Employee_ID();
							if(HC_Manager_Id > 0)
								Managers = addManager(Managers, HC_Manager_Id);
						}
					}//end IF
				}//endElse
			}//endFor
		}//endIf
		
		if(Managers.size() > 0)
		{
			DocumentManager = Managers.size();
			for(int HC_Manager_ID : Managers)
				createReportManager(HC_Manager_ID, trainingClass);
		}
		
		trainingClass.set_ValueOfColumn("Status", "Act");
		trainingClass.setProcessed(true);
		trainingClass.saveEx();
		
		if(DocumentEmployee == 0 && DocumentManager == 0)
			return "Success Activate Training Class and no document generated";
		else if(DocumentEmployee == 0 )
			return "Success Activate Training Class and "+DocumentManager + " Manager Effectivity Reports generated";
		else if(DocumentManager == 0)
			return "Success Activate Training Class and "+DocumentEmployee + " Employee Effectivity Reports generated";
		else
			return "Success Activate Training Class and "+DocumentEmployee +" Employee Effectivity Reports and "+DocumentManager+" Manager Effectivity Reports generated";
	}	
	
	/**
	 * Add manager to list Managers
	 * @param Managers
	 * @param HC_Employee_ID
	 * @return
	 * 
	 */
	private List<Integer> addManager(List<Integer> Managers, int HC_Employee_ID){
		if(Managers.size() > 0)
		{
			for(int HC_Manager_ID : Managers)
			{
				if(HC_Manager_ID == HC_Employee_ID)
				{
					return Managers;
				}
			}
			
			Managers.add(HC_Employee_ID);
		}
		else
		{
			Managers.add(HC_Employee_ID);
		}
		return Managers;
	}
	
	private void createReportManager(int HC_Employee_ID, X_HC_TrainingClass trainingClass){
	
		String Label_EvaluasiMateri1 =" Seberapa penting materi pelatihan yang telah diikuti dapat memenuhi kebutuhan untuk menunjang tugas-tugas di Unit Kerja Sdr./i? (skala 1 s.d 4) (4 = sangat penting)";
		String Label_EvaluasiMateri2 =" Seberapa besar manfaat program pelatihan ini dalam penyelesaian tugas mantan peserta pelatihan? (skala 1 s.d 4) (4 = sangat bermanfaat)";
		String Label_EvaluasiMateri3 =" Seberapa efektif pelatihan yang telah diikuti dapat diimplementasikan di Unit Kerja Sdr./i? ( skala 1 s.d 4) (4 = sangat efektif)";
		String Label_EvaluasiMateri4 =" Seberapa besar perubahan prilaku mantan peserta pelatihan dalam menerapkan / mengimplementasikan materi pelatihan? ( skala 1 s.d 4) (4 = sangat besar)";
		String Label_EvaluasiMateri5 =" Seberapa besar materi pelatihan yang telah diikuti dapat memberikan kontribusi bagi peningkatan kinerja di Unit Kerja Sdr./i? ( skala 1 s.d 4) (4 = sangat besar)";
		MEmployee employee = new MEmployee(getCtx(), HC_Employee_ID, get_TrxName());
		int employeeJobID = employee.getActiveSequenceOneEmployeeJob();
		if(employeeJobID <= 0){
			throw new AdempiereException("Error: Manager "+employee.getName()+" doesn't have seq 1 job active" );
		}
		
		MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), employeeJobID, get_TrxName());
		MJob job = new MJob(getCtx(), employeeJob.getHC_Job_ID(), get_TrxName());
		X_HC_Org org = new X_HC_Org(getCtx(), employeeJob.getHC_Org_ID(), get_TrxName());
		
		String jobValue = job.getValue();
		String PjsJob = "Pjs. "+jobValue;// job Pjs
		String PjJob = "Pj. "+ jobValue;// job Pj
		String whereClause 	=  "(hcj.HC_Job_ID= ? OR job.Value in (?,?)) AND HC_Employee.HC_Status = 'A' AND hcj.HC_Status= 'A'";
		HC_Employee_ID = new Query(getCtx(), MEmployee.Table_Name, whereClause, get_TrxName())
						.setParameters(job.getHC_Job_ID(), PjJob, PjsJob)
						.addJoinClause(" JOIN HC_EmployeeJob hcj ON HC_Employee.HC_Employee_ID = hcj.HC_Employee_ID "
								+ "JOIN HC_Job job ON job.HC_Job_ID = hcj.HC_Job_ID")
						.firstId();
		//reset employee, employeejob, HC Org, job
		employee = new MEmployee(getCtx(), HC_Employee_ID, get_TrxName());
		employeeJobID = employee.getActiveSequenceOneEmployeeJob();
		if(employeeJobID <= 0){
			throw new AdempiereException("Error: manager "+employee.getName()+" doesn't have seq 1 job active" );
		}
		
		employeeJob = new MEmployeeJob(getCtx(), employeeJobID, get_TrxName());
		job = new MJob(getCtx(), employeeJob.getHC_Job_ID(), get_TrxName());
		org = new X_HC_Org(getCtx(), employeeJob.getHC_Org_ID(), get_TrxName());
		
		Timestamp dateStart = trainingClass.getDateStart();
		Timestamp timeStart = trainingClass.getStartTime();
		Timestamp newDateStart = Timestamp.valueOf(
				dateStart.toString().substring(0, 11) + timeStart.toString().substring(11, 21));
		
		X_HC_EffectivityReport_Mgr mgr_effectivityReport = 
				new X_HC_EffectivityReport_Mgr(getCtx(), 0, get_TrxName());
		mgr_effectivityReport.setHC_Employee_ID(HC_Employee_ID);
		mgr_effectivityReport.setHC_Job_ID(job.get_ID());
		mgr_effectivityReport.setHC_Org_ID(org.get_ID());
		mgr_effectivityReport.setHC_NIK(employee.get_Value("HC_NIK").toString());
		mgr_effectivityReport.setHC_TrainingClass_ID(trainingClass.get_ID());
		mgr_effectivityReport.setAD_Org_ID(trainingClass.getAD_Org_ID());
		mgr_effectivityReport.setDateStart(newDateStart);
		mgr_effectivityReport.setC_BPartner_ID(employee.getC_BPartner_ID());
		mgr_effectivityReport.setIsActive(true);
		mgr_effectivityReport.setLabel_EvaluasiMateri1(Label_EvaluasiMateri1);
		mgr_effectivityReport.setLabel_EvaluasiMateri2(Label_EvaluasiMateri2);
		mgr_effectivityReport.setLabel_EvaluasiMateri3(Label_EvaluasiMateri3);
		mgr_effectivityReport.setLabel_EvaluasiMateri4(Label_EvaluasiMateri4);
		mgr_effectivityReport.setLabel_EvaluasiMateri5(Label_EvaluasiMateri5);
		mgr_effectivityReport.setEvaluasiMateri1(4);
		mgr_effectivityReport.setEvaluasiMateri2(4);
		mgr_effectivityReport.setEvaluasiMateri3(4);
		mgr_effectivityReport.setEvaluasiMateri4(4);
		mgr_effectivityReport.setEvaluasiMateri5(4);
		mgr_effectivityReport.set_ValueOfColumn("Status", "Drf");
		mgr_effectivityReport.saveEx();
	}
	
	private void createReportEmployee(X_HC_TrainingAttendance m_trainingAttendance,
			MEmployeeJob employeeJob, X_HC_TrainingClass trainingClass){
		
		String Label_EvaluasiMateri1 =" Apakah materi yang disampaikan relevan dengan tujuan pelatihan? (skala 1 s.d 4) (4 = sangat relevan)";
		String Label_EvaluasiMateri2 =" Apakah materi pelatihan berguna bagi pelaksanaan tugas Sdr/i sehari-hari? (skala 1 s.d 4) (4 = sangat berguna)";
		String Label_EvaluasiMateri3 =" Seberapa banyak materi pelatihan ini menambah pengetahuan Sdr/i? (skala 1 s.d 4) (4 = banyak [100%])";
		String Label_EvaluasiInstruktur1=" Seberapa baik instruktur dalam hal penguasaan materi pelatihan? (skala 1 s.d 4) (4 = sangat baik)";
		String Label_EvaluasiInstruktur2=" Bagaimana kemampuan instruktur saat mentransfer atau menyampaikan materi pelatihan? (skala 1 s.d 4) (4 = sangat jelas)";
		String Label_EvaluasiPelaksana1=" Bagaimana suasana pembelajaran saat pelatihan? (skala 1 s.d 4) (4 = sangat kondusif)";
		String Label_EvaluasiPelaksana2=" Bagaimana menurut Sdr/i mengenai durasi waktu pelatihan?";
		String Label_EvaluasiPelaksana3=" Bagaimana penilaian pelatihan secara keseluruhan menurut Sdr/i? (skala 1 s.d 4) (4 = baik sekali)";
		
		MEmployee employee = new MEmployee(getCtx(), m_trainingAttendance.getHC_Employee_ID(), get_TrxName());
		Timestamp dateStart = trainingClass.getDateStart();
		Timestamp timeStart = trainingClass.getStartTime();
		Timestamp newDateStart = Timestamp.valueOf(
				dateStart.toString().substring(0, 11) + timeStart.toString().substring(11, 21));
		
		X_HC_EffectivityReport emp_effectivityReport = 
				new X_HC_EffectivityReport(getCtx(), 0, get_TrxName());
		emp_effectivityReport.setHC_Employee_ID(employee.get_ID());
		emp_effectivityReport.setHC_Job_ID(employeeJob.getHC_Job_ID());
		emp_effectivityReport.setHC_Org_ID(employeeJob.getHC_Org_ID());
		emp_effectivityReport.setHC_NIK(employee.get_Value("HC_NIK").toString());
		emp_effectivityReport.setHC_TrainingClass_ID(trainingClass.get_ID());
		emp_effectivityReport.setAD_Org_ID(trainingClass.getAD_Org_ID());
		emp_effectivityReport.setDateStart(newDateStart);
		emp_effectivityReport.setC_BPartner_ID(employee.getC_BPartner_ID());
		emp_effectivityReport.setIsActive(true);
		emp_effectivityReport.setLabel_EvaluasiInstruktur1(Label_EvaluasiInstruktur1);
		emp_effectivityReport.setLabel_EvaluasiInstruktur2(Label_EvaluasiInstruktur2);
		emp_effectivityReport.setLabel_EvaluasiMateri1(Label_EvaluasiMateri1);
		emp_effectivityReport.setLabel_EvaluasiMateri2(Label_EvaluasiMateri2);
		emp_effectivityReport.setLabel_EvaluasiMateri3(Label_EvaluasiMateri3);
		emp_effectivityReport.setLabel_EvaluasiPelaksana1(Label_EvaluasiPelaksana1);
		emp_effectivityReport.setLabel_EvaluasiPelaksana2(Label_EvaluasiPelaksana2);
		emp_effectivityReport.setLabel_EvaluasiPelaksana3(Label_EvaluasiPelaksana3);
		emp_effectivityReport.setEvaluasiInstruktur1(4);
		emp_effectivityReport.setEvaluasiInstruktur2(4);
		emp_effectivityReport.setEvaluasiMateri1(4);
		emp_effectivityReport.setEvaluasiMateri2(4);
		emp_effectivityReport.setEvaluasiMateri3(4);
		emp_effectivityReport.setEvaluasiPelaksana1(4);
		emp_effectivityReport.setEvaluasiPelaksana2(X_HC_EffectivityReport.EVALUASIPELAKSANA2_Cukup);
		emp_effectivityReport.setEvaluasiPelaksana3(4);
		emp_effectivityReport.set_ValueOfColumn("Status", "Drf");
		emp_effectivityReport.saveEx();
	}
	
}//endClass