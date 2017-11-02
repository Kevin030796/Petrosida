package org.toba.psd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.model.MUserRoles;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.toba.psd.model.X_HC_JobLevel;

public class PSD_CreateRoleESSandMSSFromUser extends SvrProcess{
	
	@Override
	protected void prepare() {
		
	}//prepare

	@Override
	protected String doIt() throws Exception {
		List<MUser> users = new ArrayList<MUser>();
		String sql = "SELECT AD_User_ID FROM AD_User WHERE (name like 'BP%' OR name like 'TP%' OR name like 'TK%')"
				+ " AND IsActive='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				MUser user = new MUser(getCtx(), rs.getInt(1), get_TrxName());
				users.add(user);
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, sql, e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		//createroleESS
		createRoleESSandMSS(users);
		return  "Success Process" ;
	}//doIt

	public void createRoleESSandMSS(List<MUser> users){
		
		String sql = "SELECT AD_Role_ID,Name FROM AD_Role WHERE Name LIKE 'ESS' OR Name LIKE 'MSS'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int roleESS_ID = 0;
		int roleMSS_ID = 0;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString(2).equals("ESS"))
					roleESS_ID = rs.getInt(1);
				else if(rs.getString(2).equals("MSS"))
					roleMSS_ID = rs.getInt(1);
				else
					throw new AdempiereException("Error: not found ESS or MSS");
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, sql, e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		if(users.size() > 0)
			for(MUser m_user : users){
				MRole[] roleIds = m_user.getRoles(m_user.getAD_Org_ID());
				boolean checkESS = false;
				boolean checkMSS = false;
				if(roleIds.length > 0){
					for(MRole role : roleIds){
						if(role.get_ID() == roleESS_ID)
						{
							checkESS=true;
						}
						else if(role.get_ID() == roleMSS_ID)
						{
							checkMSS=true;
						}
					}
				}
				
				if(checkESS == false){
					MUserRoles userRole = new MUserRoles(getCtx(), 0, get_TrxName());
					userRole.setAD_Org_ID(m_user.getAD_Org_ID());
					userRole.setAD_Role_ID(roleESS_ID);
					userRole.setAD_User_ID(m_user.get_ID());
					userRole.setIsActive(true);
					userRole.saveEx();
				}
				
				if(checkMSS == false){
					MEmployee m_employee = new MEmployee(getCtx(), m_user.get_ValueAsInt("HC_Employee_ID"), get_TrxName());
					int HC_EmployeeJob_ID = m_employee.getActiveSequenceOneEmployeeJob();
					MEmployeeJob m_employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
					MJob m_job = new MJob(getCtx(), m_employeeJob.getHC_Job_ID(), get_TrxName());
					X_HC_JobLevel m_jobLevel = new X_HC_JobLevel(getCtx(), m_job.getHC_JobLevel_ID(), get_TrxName());
					if(!m_jobLevel.getName().equals("Pelaksana")){
						MUserRoles userRole = new MUserRoles(getCtx(), 0, get_TrxName());
						userRole.setAD_Org_ID(m_user.getAD_Org_ID());
						userRole.setAD_Role_ID(roleMSS_ID);
						userRole.setAD_User_ID(m_user.get_ID());
						userRole.setIsActive(true);
						userRole.saveEx();
					}
				}	
			}//endFor
	}
}//endClass
