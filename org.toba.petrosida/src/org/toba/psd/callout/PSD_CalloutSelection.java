package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.toba.psd.model.MSelection;

/**
 * @author KevinY
 * Callout for HC_Selection
 */
public class PSD_CalloutSelection extends CalloutEngine implements IColumnCallout{
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(MSelection.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		return "";

	}//start
		
	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if sucess
	 */
	private String CalloutEmployeeID(Properties ctx, int windowNo, GridTab mTab,
				GridField mField, Object value){
		
		if(value == null)
			return "";
		
		int HC_Employee_ID 	 = (Integer)value;
		MEmployee m_employee = new MEmployee(ctx, HC_Employee_ID, null);
		//comment : Ask Jorvan searching EmployeeJob with Outsource
		String whereClause = "HC_EmployeeJob."+ MEmployeeJob.COLUMNNAME_HC_Employee_ID + "=? "
				+ "AND "+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active +"' "
				+ "AND ec.Description IN ('Outsource','TK','BP','Magang (PT. Yasida Putra Makmur)') ";
		int HC_EmployeeJob_ID = new Query(ctx, MEmployeeJob.Table_Name,whereClause,null)
							.addJoinClause("INNER JOIN HC_EmployeeClass ec ON ec.HC_EmployeeClass_ID = HC_EmployeeJob.HC_EmployeeClass_ID")
							.setParameters(m_employee.get_ID())
							.firstId();
	
		if(HC_EmployeeJob_ID > 0) {
			
			//check last HC_WorkStartDate (TMT) 
			whereClause = MSelection.COLUMNNAME_HC_Employee_ID + "=? AND " + MSelection.COLUMNNAME_IsActive + "='Y'";
			int HC_Selection_ID = new Query(ctx,MSelection.Table_Name, whereClause, null)
								  .setParameters(new Object[]{m_employee.get_ID()})
								  .setOrderBy(MSelection.COLUMNNAME_HC_WorkStartDate + " DESC")
								  .firstId();
		
			MSelection selection = new MSelection(ctx, HC_Selection_ID, null);
			MEmployeeJob m_employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
			mTab.setValue("HC_EmployeeJob_ID"	, m_employeeJob.get_ID());
			if(HC_Selection_ID > 0)
				mTab.setValue(MSelection.COLUMNNAME_HC_WorkStartDate, selection.getTanggalNomorSK());
			else
				mTab.setValue(MSelection.COLUMNNAME_HC_WorkStartDate, m_employeeJob.getHC_WorkStartDate());
			mTab.setValue(MSelection.COLUMNNAME_HC_EmployeeClass_ID, m_employeeJob.getHC_EmployeeClass_ID());
			mTab.setValue("HC_Org_ID" , m_employeeJob.getHC_Org_ID());
		}
		
		return "";
	}//CalloutEmployeeID
	
	
}//endClass
