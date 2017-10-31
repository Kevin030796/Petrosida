package org.toba.psd.model;

import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;

/**
 * 
 * @author kevinY
 *
 */
public class MSelection extends X_HC_Selection{

	public MSelection(Properties ctx, int HC_Selection_ID, String trxName) {
		super(ctx, HC_Selection_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord || is_Changed()){
			//description in employeeClass
			String d_Outsource= "Outsource";
			String d_PKWT = "TK";
			String d_Probation = "BP";
			String d_Permanent = "TP";
			String d_Internship = "Magang (PT. Yasida Putra Makmur)";
		
			X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(),getHC_EmployeeClass_ID(),get_TrxName());
				
				//setEmployeeClassToID
				String empClassTo = "";
				if(empClass.getDescription().equals(d_Outsource) || empClass.getDescription().equals(d_Internship)){
					empClassTo = d_PKWT;
				}else if(empClass.getDescription().equals(d_PKWT)){
					empClassTo = d_Probation;
				}else if(empClass.getDescription().equals(d_Probation)){
					empClassTo = d_Permanent;
				}
				
				int HC_EmployeeClassTo_ID = new Query(getCtx(),X_HC_EmployeeClass.Table_Name,X_HC_EmployeeClass.COLUMNNAME_Description+" like ?",get_TrxName())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.setParameters(new Object[]{empClassTo})
				.firstId();

				if(HC_EmployeeClassTo_ID <= 0)
					throw new AdempiereException("Error: Employee Class to not exists");

				setHC_EmployeeClassTo_ID(HC_EmployeeClassTo_ID);
			}
		return true;
	}
	
}//endClass
