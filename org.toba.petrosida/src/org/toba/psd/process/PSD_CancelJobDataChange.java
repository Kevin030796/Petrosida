package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.MJobDataChange;


/**
 * 
 * @author KevinY
 * Process for Employee Job Data Change
 * PSD - 2845
 */
public class PSD_CancelJobDataChange extends SvrProcess{

	private int HC_JobDataChange_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		HC_JobDataChange_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_JobDataChange_ID <= 0)
			throw new AdempiereException("Error: Job Data Change is not selected");
		
		MJobDataChange jobDataChange  	= new MJobDataChange(getCtx(), HC_JobDataChange_ID, get_TrxName());
		MEmployee employee				= new MEmployee(getCtx(), jobDataChange.getHC_Employee_ID(), get_TrxName());
		MJob nextJob					= new MJob(getCtx(), jobDataChange.getHC_Job_ID(), get_TrxName());
		
		if(jobDataChange.get_ValueAsBoolean("IsCancelled") == true)
			throw new AdempiereException("Error: Job Data Change is already cancelled");
		
		int HC_EmployeeJob_ID 		  	= jobDataChange.get_ValueAsInt("HC_EmployeeJob_ID");
		
		if(HC_EmployeeJob_ID <= 0) 	
			throw new AdempiereException("Error: Employee Job Data is not exists");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MJobDataChange> jobDataChanges = new ArrayList<MJobDataChange>();
		String sql = "SELECT HC_JobDataChange_ID FROM HC_JobDataChange WHERE HC_Employee_ID = ?"
				+ " AND HC_EmployeeJob_ID = ?"
				+ " AND IsCancelled='N'"
				+ " AND Processed ='Y' "
				+ " ORDER BY Updated ASC";
		
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, employee.getHC_Employee_ID());
			pstmt.setInt(2, jobDataChange.getHC_EmployeeJob_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MJobDataChange dataChange = new MJobDataChange(getCtx(), rs.getInt("HC_JobDataChange_ID"), get_TrxName());
				jobDataChanges.add(dataChange);
			}
		} catch (SQLException e){
			log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if(jobDataChanges.size() <= 0)
			throw new AdempiereException("Error: Employee Job Data Change for employee " + employee.getName() +" not exist");
		
		if(jobDataChanges.get(jobDataChanges.size()-1).get_ID() == jobDataChange.get_ID()){
			String query = "DELETE FROM HC_Mutation WHERE HC_Employee_ID = " + jobDataChange.getHC_Employee_ID()
					+ " AND HC_Job_ID = " + jobDataChange.getHC_Job_ID() + " AND HC_JobDataChange_ID is not null AND "
					+ " isActive = 'Y'";
			int no =  DB.executeUpdate(query, false, get_TrxName());
			
			if(no == -1)
				throw new AdempiereException("Error: there is no mutasi jabatan " + nextJob.getName()+ " exists");
			
			jobDataChange.set_ValueOfColumn("IsCancelled", true);
			jobDataChange.saveEx();
			MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
			employeeJob.setHC_Job_ID(jobDataChange.get_ValueAsInt("HC_PreviousJob_ID"));
			employeeJob.setHC_Org_ID(jobDataChange.getHC_Org_ID());
			employeeJob.setHC_Manager_ID(jobDataChange.get_ValueAsInt("HC_Manager_ID"));
			employeeJob.setHC_Compensation1((BigDecimal)jobDataChange.get_Value("HC_Compensation1"));
			employeeJob.set_ValueOfColumn("NomorSK", jobDataChange.get_Value("NomorSK").toString());
			employeeJob.setHC_WorkStartDate(jobDataChange.getHC_WorkStartDate());
			employeeJob.setHC_PayGroup_ID(jobDataChange.getHC_PayGroup_ID());
			employeeJob.saveEx();
		}else{
			throw new AdempiereException("Error: Employee job Data Change is not last Employee Job Data Change");
		}
		
		return "Success Cancel Job Data Change";
	}//doIt

}//endClass
