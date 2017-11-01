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

public class PSD_CreateRoleESSFromUser extends SvrProcess{
	
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
		
		int total_roleESS = 0;
		//createroleESS
		total_roleESS = createRoleESS(users);
		return  "Success Process and "+ total_roleESS + " role ESS created" ;
	}//doIt

	public int createRoleESS(List<MUser> users){
		int total_roleCreated = 0;
		
		String sql = "SELECT AD_Role_ID FROM AD_Role WHERE Name LIKE 'ESS'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int roleESS_ID = 0;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			rs = pstmt.executeQuery();
			if(rs.next()){
				roleESS_ID = rs.getInt(1);
			}else{
				throw new AdempiereException("Error: not found ESS");
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
		
		if(users.size() > 0){
			for(MUser m_user : users){
				MRole[] roleIds = m_user.getRoles(m_user.getAD_Org_ID());
				int check = 0;
				if(roleIds.length > 0){
					for(MRole role : roleIds){
						if(role.get_ID() == roleESS_ID){
							check= 1;
						}
					}
				}
				
				if(check == 0){
					MUserRoles userRole = new MUserRoles(getCtx(), 0, get_TrxName());
					userRole.setAD_Org_ID(m_user.getAD_Org_ID());
					userRole.setAD_Role_ID(roleESS_ID);
					userRole.setAD_User_ID(m_user.get_ID());
					userRole.setIsActive(true);
					userRole.saveEx();
					total_roleCreated++;
				}
			}
		}
		return total_roleCreated;
	}
}//endClass
