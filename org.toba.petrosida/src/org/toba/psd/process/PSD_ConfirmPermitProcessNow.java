package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.X_AD_Role;
import org.compiere.model.X_AD_User;
import org.compiere.model.X_AD_User_Roles;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.toba.psd.model.MRequestPermit;


/**
 * 
 * @author Kevin Yulianto
 * Process for Confirming Permit
 * Confirm
 */
public class PSD_ConfirmPermitProcessNow extends SvrProcess{
	private String p_StatusConfirm = "Cnf";
	private int HC_RequestPermit_ID = 0;
	
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
		
		HC_RequestPermit_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_RequestPermit_ID <= 0)
			throw new AdempiereException("Error: Request Overtime is not selected");
		
		MRequestPermit permit = new MRequestPermit(getCtx(), HC_RequestPermit_ID, get_TrxName());
		
		if(permit.getStatus().equals("Cnf")){
			throw new AdempiereException("Error: Already confirm");
		}
		
		int ad_User_Id = Env.getAD_User_ID(getCtx());
		X_AD_User user = new X_AD_User(getCtx(), ad_User_Id, get_TrxName());
		int m_role_id = 0;
		boolean isSDM = false;
		
		m_role_id = getRoleId();
		isSDM = checkSDM(m_role_id,ad_User_Id);
		
		int m_employee_id = user.get_ValueAsInt("HC_Employee_ID");
		
		if(m_employee_id <= 0 && isSDM == false){
			throw new AdempiereException("Error: Your account isn't identified as an employee ");
		}
		
		if(isSDM == true)//sdm
			if(!permit.getPermitType().equals("ITMK"))
				throw new AdempiereException("Error: Document type is not for SDM to confirm");
		else//satpam
			if(permit.getPermitType().equals("ITMK"))
				throw new AdempiereException("Error: Document type is not for satpam to confirm");
		
		double potonganUpah = 0.0;
		potonganUpah = permit.CalculatePayDeduction();
		permit.set_ValueOfColumn("NilaiPotonganUpah", new BigDecimal(potonganUpah));
		permit.setStatus(p_StatusConfirm);
		permit.saveEx();
		
		return "Success confirm";
	}//doIt
	
	public boolean checkSDM(int m_role_id,int m_user_id){
		boolean isSDM = false;
		String sql = "SELECT "+X_AD_User_Roles.COLUMNNAME_AD_User_ID+" FROM "+X_AD_User_Roles.Table_Name+" WHERE "
				+ X_AD_User_Roles.COLUMNNAME_AD_Role_ID+"=? AND "
				+ X_AD_User_Roles.COLUMNNAME_AD_User_ID+"=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt(1, m_role_id);
			pstmt.setInt(2, m_user_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				isSDM = true;
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, "Not found user role", e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return isSDM;
	}//checkSDM()
	
	/**
	 * get Role ID Confirm SDM
	 * @return
	 */
	public int getRoleId(){
		//roleApproveTravel

		String roleConfirmSDM = "Confirm SDM";//SDM
		int m_role_id = 0;
		
		String sql = "SELECT "+X_AD_Role.COLUMNNAME_AD_Role_ID+" FROM "+X_AD_Role.Table_Name+" WHERE "
				+ X_AD_Role.COLUMNNAME_IsActive + "='Y' AND "
				+ X_AD_Role.COLUMNNAME_Name + " LIKE ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setString(1, roleConfirmSDM);
			rs = pstmt.executeQuery();
			if(rs.next()){
				m_role_id = rs.getInt(1);
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, "Not found role sdm", e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return m_role_id;
	}//getRoleId()
	
}//endClass
