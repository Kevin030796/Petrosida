package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.MJobDataChange;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;


/**
 * @author KevinY
 * Callout For imported HC_EmployeeJobDataChange
 * PSD - #2798
 * Using
 */
public class test extends CalloutEngine implements IColumnCallout{
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(MJobDataChange.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(MJobDataChange.COLUMNNAME_HC_JobAction))
			return CalloutJobAction(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(MJobDataChange.COLUMNNAME_HC_Job_ID))
			return CalloutJobID(ctx, WindowNo, mTab, mField, value);
		
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
			if(empClass.get_ValueAsBoolean("IsMutationOnly") && !jobAction.equals(MEmployeeJob.HC_JOBACTION_Transfer)){
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_JobAction, null);
				return "Error: employee is not permanent";
			}
			
			if(jobAction.equals(MJobDataChange.HC_JOBACTION_SalaryChange)){
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Job_ID, mTab.getValue("HC_PreviousJob_ID"));
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Org2_ID, mTab.getValue("HC_Org_ID"));
				if(mTab.getValue(MJobDataChange.COLUMNNAME_HC_Manager_ID)!= null);	
					mTab.setValue(MJobDataChange.COLUMNNAME_HC_ManagerTo_ID, mTab.getValue("HC_Manager_ID"));
			}else if(!jobAction.equals(MJobDataChange.HC_JOBACTION_SalaryChange)){
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Job_ID, null);
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_Org2_ID, null);
				mTab.setValue(MJobDataChange.COLUMNNAME_HC_ManagerTo_ID , null);
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
		
		int HC_EmployeeJob_ID = employee.getJobDataChangeEmployeeJob();
		
		if(HC_EmployeeJob_ID <= 0){
			mTab.setValue("HC_Employee_ID", null);
			mTab.setValue("HC_JobAction"  , null);
			mTab.setValue("HC_Status"	  , null);
			return "Error: Employee must be permanent class";
		}
		MEmployeeJob job = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
		mTab.setValue("HC_EmployeeJob_ID"	, job.getHC_EmployeeJob_ID());
		mTab.setValue("HC_PreviousJob_ID"	, job.getHC_Job_ID());		
		mTab.setValue("HC_Status"			, job.getHC_Status());
		mTab.setValue("HC_Org_ID"			, job.getHC_Org_ID());
		mTab.setValue("HC_Manager_ID"		, job.getHC_Manager_ID());
		mTab.setValue("Description"			, job.getDescription());
		mTab.setValue("NomorSK"				, job.get_Value("NomorSK"));
		mTab.setValue("HC_Reason_ID"		, job.getHC_Reason_ID());
		mTab.setValue("HC_PayGroup_ID"		, job.getHC_PayGroup_ID());
		mTab.setValue("HC_Compensation1"	, job.get_Value("HC_Compensation1"));
		mTab.setValue("HC_Compensation2"	, job.get_Value("HC_Compensation1"));
		mTab.setValue("HC_WorkStartDate"	, job.getHC_WorkStartDate());
		
		mTab.setValue("HC_Job_ID", null);
		mTab.setValue("HC_Org2_ID",null);
		mTab.setValue("HC_ManagerTo_ID", null);
		mTab.setValue("HC_JobAction",null);
		
		if(mTab.getValue("HC_JobAction") != null ) {
			if(mTab.getValue("HC_JobAction").equals("SLC")) {
				mTab.setValue("HC_Job_ID"	, mTab.getValue("HC_PreviousJob_ID"));
				mTab.setValue("HC_Org2_ID"	, mTab.getValue("HC_Org_ID"));
				if(mTab.getValue("HC_Manager_ID")!= null)	
					mTab.setValue("HC_ManagerTo_ID", mTab.getValue("HC_Manager_ID"));
			}
		}
		return "";
	}//CalloutEmployeeID
	
	/**
	 * Calling out Previous Job from HC_EmployeeJob
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutJobID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		if(!mTab.get_ValueAsString("HC_JobAction").equals("SLC")){
			int HC_Job_ID = (Integer)value;
			MJob job = new MJob(ctx, HC_Job_ID, null);
			if(job.get_Value("HC_JobReportTo_ID") == null) {
				mTab.setValue("HC_ManagerTo_ID", null);
			}else{
				int HC_JobReportTo_ID = (Integer) job.get_Value("HC_JobReportTo_ID");
				int HC_EmployeeJob_ID = getActiveSequenceOneEmployeeJob(ctx,HC_JobReportTo_ID);
				if(HC_EmployeeJob_ID <= 0) {
					//EmployeeJobID is not found with related job
					mTab.setValue("HC_ManagerTo_ID",null);
				}else{
					//setManagerToID
					MEmployeeJob employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
					int HC_Employee_ID = employeeJob.getHC_Employee_ID();
					mTab.setValue("HC_ManagerTo_ID", HC_Employee_ID);
				}//endElse
			}//endElse
		}//endIf
		
		return "";
	}//CalloutJobID
	
	/**
	 * Author Kevin Yulianto
	 * searching employee job id active sequence one with parameter job
	 * @param HC_Job_ID
	 * @return
	 * ID of HC_EmployeeJob
	 */
	public int getActiveSequenceOneEmployeeJob(Properties ctx, int HC_Job_ID){
		String whereClause = "HC_Job_ID = ? AND Processed='Y' "
				+ "AND HC_Status='"+MEmployeeJob.HC_STATUS_Active+"' "
				+ "AND SeqNo =1";
		int HC_EmployeeJob_ID = new Query(ctx ,MEmployeeJob.Table_Name,whereClause, null)
						.setParameters(HC_Job_ID)
						.firstId();
		
		if(HC_EmployeeJob_ID <= 0)
			return 0;
		else 
			return HC_EmployeeJob_ID;
	}
}//endClass
