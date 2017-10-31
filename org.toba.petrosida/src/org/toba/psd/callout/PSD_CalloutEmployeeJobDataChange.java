package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJobDataChange;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;


/**
 * @author KevinY
 * Callout For HC_EmployeeJobDataChange
 * PSD - #2798
 */
public class PSD_CalloutEmployeeJobDataChange extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(MJobDataChange.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(MJobDataChange.COLUMNNAME_HC_JobAction))
			return CalloutJobAction(ctx, WindowNo, mTab, mField, value);
		return "";
	}//Start
	
	/**
	 * Callout Next Job ID from HC_EmployeeJob
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutJobAction(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		String jobAction = value.toString();
		MEmployeeJob empJob = null;
		if(mTab.getValue("HC_EmployeeJob_ID")!= null){
			int HC_employeeJob_ID = ((Integer)mTab.getValue("HC_EmployeeJob_ID"));
			empJob = new MEmployeeJob(ctx, HC_employeeJob_ID, null);

			X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(ctx, empJob.getHC_EmployeeClass_ID(), null);
			if(empClass.get_ValueAsBoolean("IsMutationOnly") && 
					!jobAction.equals(MEmployeeJob.HC_JOBACTION_Transfer)){
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_JobAction, null);
				return "Error: employee is not permanent";
			}
			
			if(jobAction.equals(MJobDataChange.HC_JOBACTION_SalaryChange)){
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Job_ID, mTab.getValue("HC_PreviousJob_ID"));
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Org2_ID, mTab.getValue("HC_Org_ID"));
			}else if(!jobAction.equals(MJobDataChange.HC_JOBACTION_SalaryChange)){
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Job_ID, null);
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Org2_ID, null);
			}
		}
		
		return "";
	}//CalloutJobAction
	
	/**
	 * Calling out Employee Job Data Change for Previous Job, Previous Manager, 
	 * Previous HC_Org, Nomor SK from Last Employee Job
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
		
		int HC_Employee_ID = (Integer)value;
		
		MEmployee employee = new MEmployee(ctx, HC_Employee_ID, null);
		
		if(employee.getHC_Status() == "P"){
			return "Error: Employee must be activated first";
		}
		
		/*String whereClause = "SELECT ej.HC_EmployeeJob_ID FROM HC_EmployeeJob ej"
				+ " LEFT JOIN HC_EmployeeClass ec ON ec.HC_EmployeeClass_ID = ej.HC_EmployeeClass_ID "
				+ " WHERE ec.IsForJobDataChange='Y'"
				+ " AND ej.HC_Employee_ID=?"
				+ " AND ej.SeqNo=1 "
				+ " AND ej.IsActive='Y' "
				+ " AND ej.HC_Status='A'";
		int HC_EmployeeJob_ID = DB.getSQLValue(null, whereClause, HC_Employee_ID);//get employeeJob seq 1 and Active
		*/
		int HC_EmployeeJob_ID = employee.getJobDataChangeEmployeeJob();
		
		if(HC_EmployeeJob_ID <= 0) {
			/*Not found HC_EmployeeJob_ID*/
			mTab.setValue("HC_Employee_ID", null);
			mTab.setValue("HC_JobAction"  , null);
			mTab.setValue("HC_Status"	  , null);
			return "Error: Employee must be permanent class";
		}
		MEmployeeJob empJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
		
		mTab.setValue("HC_EmployeeJob_ID"	, empJob.getHC_EmployeeJob_ID());
		mTab.setValue("HC_PreviousJob_ID"	, empJob.getHC_Job_ID());		
		mTab.setValue("HC_Status"			, empJob.getHC_Status());
		mTab.setValue("HC_Org_ID"			, empJob.getHC_Org_ID());
		mTab.setValue("Description"			, empJob.getDescription());
		mTab.setValue("NomorSK"				, empJob.get_Value("NomorSK"));
		mTab.setValue("HC_Reason_ID"		, empJob.getHC_Reason_ID());
		mTab.setValue("HC_PayGroup_ID"		, empJob.getHC_PayGroup_ID());
		mTab.setValue("HC_Compensation1"	, empJob.get_Value("HC_Compensation1"));//Gaji Pokok
		mTab.setValue("HC_Compensation2"	, empJob.get_Value("HC_Compensation1"));//New Gaji
		mTab.setValue("HC_WorkStartDate"	, empJob.getHC_WorkStartDate());
		
		
		/*set Job_ID,OrgTo_ID, JobAction to null because core had already set the value, so set the field below to null first*/
		mTab.setValue("HC_Job_ID", null);
		mTab.setValue("HC_Org2_ID",null);
		mTab.setValue("HC_JobAction",null);

		if(mTab.getValue("HC_JobAction") != null ) {
			if(mTab.getValue("HC_JobAction").equals("SLC")) {
				mTab.setValue("HC_Job_ID"	, mTab.getValue("HC_PreviousJob_ID"));
				mTab.setValue("HC_Org2_ID"	, mTab.getValue("HC_Org_ID"));
			}
		}
		return "";
	}//CalloutEmployeeID

}//endClass
