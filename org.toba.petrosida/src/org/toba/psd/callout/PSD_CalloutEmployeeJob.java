package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;

/**
 * 
 * @author KevinY
 * Callout for HC_EmployeeJob
 * PSD - 2876
 */
public class PSD_CalloutEmployeeJob extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals(MEmployeeJob.COLUMNNAME_HC_EmployeeClass_ID))
			return CalloutEmployeeClassID(ctx, WindowNo, mTab, mField, value);
		if(mField.getColumnName().equals(MEmployeeJob.COLUMNNAME_HC_Job_ID))
			return CalloutJobID(ctx, WindowNo, mTab, mField, value);
		
		return "";
	}//start
	
	/**
	 * Callout HC_Manager_ID from Employee that have employee Job with JobReportToID
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Sucess
	 */
	 private String CalloutJobID(Properties ctx, int windowNo, GridTab mTab,
			 GridField mField, Object value){
			
			if(value == null)
				return "";
			
			int HC_Job_ID = (Integer) value;
			MJob job 	  = new MJob(ctx, HC_Job_ID, null);
			
			int HC_JobReportTo_ID = 0;
			if(job.get_Value("HC_jobReportTo_ID")!= null)
				HC_JobReportTo_ID = (Integer)job.getHC_JobReportTo_ID();
			
			String whereClause	  = "HC_Job_ID = ? AND HC_Status='"+MEmployeeJob.HC_STATUS_Active+"' AND IsActive='Y'";
			int HC_EmployeeJob_ID = new Query(ctx, MEmployeeJob.Table_Name, whereClause, null)
									.setParameters(HC_JobReportTo_ID)
									.firstId();
			
			//setManagerID
			if(HC_EmployeeJob_ID <= 0)
				mTab.setValue("HC_Manager_ID", null);
			else{
				MEmployeeJob empJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
				mTab.setValue("HC_Manager_ID", empJob.getHC_Employee_ID());
			}
	 		
	 		return "";
	}//CalloutJobID
	
	/**
	 * Calling out Member Koperasi 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutEmployeeClassID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		int HC_EmployeeClass_ID = (Integer)value;
		
		X_HC_EmployeeClass employeeClass = new X_HC_EmployeeClass(ctx, HC_EmployeeClass_ID, null);
		
		if(employeeClass.get_ValueAsBoolean("MemberKoperasi") == true)
			mTab.setValue("MemberKoperasi", true);
		else
			mTab.setValue("MemberKoperasi", false);
		
		return "";
	}
		
}//endClass
