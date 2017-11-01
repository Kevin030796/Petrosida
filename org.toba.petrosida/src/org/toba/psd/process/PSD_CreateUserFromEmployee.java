package org.toba.psd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MUser;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;

public class PSD_CreateUserFromEmployee extends SvrProcess{
	
	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		
		String msg = "";
		
		List<MEmployee> m_employees = new ArrayList<MEmployee>();
		String sql = "SELECT HC_Employee_ID FROM HC_Employee WHERE HC_Status='A' AND IsActive='Y' "
				+ "AND (HC_NIK NOT IN (SELECT Name FROM AD_User WHERE HC_Employee_ID IS NOT NULL) "
				+ "OR Value NOT IN (SELECT Name FROM AD_User WHERE HC_Employee_ID IS NOT NULL))";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				MEmployee employee = new MEmployee(getCtx(), rs.getInt(1), get_TrxName());
				m_employees.add(employee);
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
		
		msg = createUser(m_employees);
		
		return msg;
	}//doIt
	
	public String createUser(List<MEmployee> m_employees){
		int total_user = 0;
		if(m_employees.size() <= 0)
			return "No employee created ";
		
		for(MEmployee m_employee: m_employees){
			
			MUser user = new MUser(getCtx(), 0, get_TrxName());
			user.setName(m_employee.getValue());
			user.setAD_Org_ID(m_employee.getAD_Org_ID());
			user.setDescription(m_employee.getName());
			user.setPassword("12345");
			user.setIsFullBPAccess(true);
			user.setNotificationType(MUser.NOTIFICATIONTYPE_EMail);
			user.setIsActive(true);
			user.set_ValueOfColumn("HC_Employee_ID", m_employee.get_ID());
			if(m_employee.get_Value(MEmployee.COLUMNNAME_C_BPartner_ID)!=null)
				user.setC_BPartner_ID(Integer.valueOf(m_employee.get_Value(MEmployee.COLUMNNAME_C_BPartner_ID).toString()));
			if(m_employee.get_Value(MEmployee.COLUMNNAME_Name2) != null)
				user.setDescription(user.getDescription() + m_employee.get_Value(MEmployee.COLUMNNAME_Name2).toString());
			user.saveEx();
			total_user++;
		}
		
		return "Success Generated "+total_user+" Users";
	}//createUser
}//endClass
