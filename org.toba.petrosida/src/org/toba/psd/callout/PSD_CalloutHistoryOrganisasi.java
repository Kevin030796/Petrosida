package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.toba.psd.model.X_HC_Committee;
import org.toba.psd.model.X_HC_OrganizationHistory;


/**
 * @author KevinY
 * Callout HC_Committee
 * PSD - #2805
 */
public class PSD_CalloutHistoryOrganisasi extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_Committee.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		
		return "";
	}//Start
	
	
	/**
	 * Calling out Start Date, End Date from HC_OrganizationHistory
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutEmployeeID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		int HC_OrganizationHistory_ID = 0;
		
		if(mTab.getValue("HC_OrganizationHistory_ID") != null)
			HC_OrganizationHistory_ID = (Integer) mTab.getValue("HC_OrganizationHistory_ID");
		
		X_HC_OrganizationHistory orgHistory = new X_HC_OrganizationHistory(ctx, HC_OrganizationHistory_ID, null);
		
		if(orgHistory.getStartDate() != null)
			mTab.setValue("StartDate", orgHistory.getStartDate());
		if(orgHistory.getEndDate() 	 != null)
			mTab.setValue("EndDate"	 , orgHistory.getEndDate());
		
		return "";
	}//CalloutEmployeeID
	
}//endClass
