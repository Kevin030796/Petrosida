package org.toba.psd.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeDataChange;


/**
 * @author kevinY
 * PSD - 2778
 * Process Now Employee Data Approval 
 */
public class PSD_EmployeeDataApprovalProcessNow extends SvrProcess{
	
	private int    HC_EmployeeDataChange_ID = 0;
	private String p_Status = "";
	private String p_RejectReason = "";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if(name.equals("Status"))
				p_Status 	   = para[i].getParameterAsString();
			else if(name.equals("RejectReason"))
				p_RejectReason = para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		HC_EmployeeDataChange_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(HC_EmployeeDataChange_ID <= 0)
			return "Error: Employee Data Change is not selected";
	
		if(p_Status.equals(null))
			return "Error: Status Parameter is empty";
	
		MEmployeeDataChange EmployeeDataChange = new 
			MEmployeeDataChange(getCtx(), HC_EmployeeDataChange_ID, get_TrxName());
		
		EmployeeDataChange.set_ValueOfColumn("Status", p_Status);
		if(p_Status.length() > 0 && p_Status.equals("Rej"))
			EmployeeDataChange.set_ValueOfColumn("RejectReason", p_RejectReason);
		if(p_Status.length() > 0 && p_Status.equals("Acc")){
			MEmployee employee = new MEmployee(getCtx(), EmployeeDataChange.getHC_Employee_ID(), get_TrxName());
			employee.set_ValueOfColumn("effectivedatefrom",employee.getEffectiveDateFrom());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_Birthday			, 	EmployeeDataChange.getBirthday());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_C_BPartner_ID		, 	EmployeeDataChange.getC_BPartner_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_C_TaxOffice_ID	, 	EmployeeDataChange.getC_TaxOffice_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_C_Location_ID		, 	EmployeeDataChange.getC_Location_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_City				, 	EmployeeDataChange.getCity());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_Description		, 	EmployeeDataChange.getDescription());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_EMail				, 	EmployeeDataChange.getEMail());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_Email2			, 	EmployeeDataChange.getEmail2());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_AltID1			, 	EmployeeDataChange.getHC_AltID1());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_BirthCountry_ID, 	EmployeeDataChange.getHC_BirthCountry_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_BirthRegion_ID	, 	EmployeeDataChange.getHC_BirthRegion_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_BloodType		, 	EmployeeDataChange.getHC_BloodType());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_Ethnic_ID		, 	EmployeeDataChange.getHC_Ethnic_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_Gender			, 	EmployeeDataChange.getHC_Gender());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_ID1_ExpDate	, 	EmployeeDataChange.getHC_ID1_ExpDate());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_ID2_ExpDate	, 	EmployeeDataChange.getHC_ID2_ExpDate());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_MaritalDate	, 	EmployeeDataChange.get_Value("HC_MaritalDate"));
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_MaritalStatus	, 	EmployeeDataChange.get_Value("HC_MaritalStatus"));
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_NationalID1	, 	EmployeeDataChange.getHC_NationalID1());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_NationalID2	, 	EmployeeDataChange.getHC_NationalID2());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_NationalID3	, 	EmployeeDataChange.getHC_NationalID3());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_NationalID4	, 	EmployeeDataChange.getHC_NationalID4());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_NationalID5	, 	EmployeeDataChange.getHC_NationalID5());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_Religion_ID	, 	EmployeeDataChange.getHC_Religion_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_HC_TaxStatus_ID	, 	EmployeeDataChange.getHC_TaxStatus_ID());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_Name				, 	EmployeeDataChange.getName());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_Name2				, 	EmployeeDataChange.getName2());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_Phone				, 	EmployeeDataChange.getPhone());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_Phone2			, 	EmployeeDataChange.getPhone2());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_PhoneExt1			, 	EmployeeDataChange.getPhoneExt1());
			employee.set_ValueOfColumn(MEmployeeDataChange.COLUMNNAME_PhoneExt2			, 	EmployeeDataChange.getPhoneExt2());
			employee.saveEx();
			EmployeeDataChange.setProcessed(true);
		}
		EmployeeDataChange.saveEx();
		return "Success Processing Data";
	}//doIt
}//endClass
