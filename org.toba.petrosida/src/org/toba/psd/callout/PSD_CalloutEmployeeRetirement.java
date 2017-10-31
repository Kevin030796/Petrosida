package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.toba.psd.model.X_HC_EmployeeRetirement;


/**
 * @author KevinY
 * PSD - 2774 
 * Callout for HC_Sanctions
 */
public class PSD_CalloutEmployeeRetirement extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_EmployeeRetirement.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		return null;
	}//start
	
	/**
	 * Calling out Last Job, HC_Org, EndDate, Start Date from Employee Data
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if success
	 */
	private String CalloutEmployeeID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null){
			return "";
		}
		
		int HC_Employee_ID = (Integer)value;
		MEmployee employee = new MEmployee(ctx, HC_Employee_ID, null);
		int HC_EmployeeJob_ID= employee.getActiveSequenceOneEmployeeJob();
		
		if(HC_EmployeeJob_ID <= 0) {
			mTab.setValue(X_HC_EmployeeRetirement.COLUMNNAME_HC_Employee_ID, null);
			mTab.fireDataStatusEEvent("Error: Not Found Employee Job sequence 1", "", false);
		}
		
		MEmployeeJob employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
		mTab.setValue(X_HC_EmployeeRetirement.COLUMNNAME_HC_Job_ID,(Integer)employeeJob.get_Value("HC_Job_ID"));
		mTab.setValue(X_HC_EmployeeRetirement.COLUMNNAME_HC_Org_ID,employeeJob.getHC_Org_ID());
		mTab.setValue(X_HC_EmployeeRetirement.COLUMNNAME_HC_WorkStartDate, employeeJob.getHC_WorkStartDate());
		mTab.setValue(X_HC_EmployeeRetirement.COLUMNNAME_MasaPersiapanPensiun, employee.get_Value("MasaPersiapanPensiun"));
		mTab.setValue(X_HC_EmployeeRetirement.COLUMNNAME_PensiunDate, employee.get_Value("PensiunDate"));
		mTab.setValue(X_HC_EmployeeRetirement.COLUMNNAME_RetirementDate, employee.get_Value("PensiunDate"));
		
		return "";
		
	}//CalloutEmployeeID
	
	
}//endClass
