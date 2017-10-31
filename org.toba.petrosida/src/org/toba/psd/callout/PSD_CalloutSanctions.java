package org.toba.psd.callout;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.TimeUtil;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.toba.psd.model.X_HC_Sanction_Rule;
import org.toba.psd.model.X_HC_Sanctions;

/**
 * @author KevinY
 * PSD - 2774 
 * Callout for HC_Sanctions
 */
public class PSD_CalloutSanctions extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_Sanctions.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		if(mField.getColumnName().equals(X_HC_Sanctions.COLUMNNAME_HC_Sanction_Rule_ID))
			return CalloutSanctionRuleID(ctx, WindowNo, mTab, mField, value);
		if(mField.getColumnName().equals(X_HC_Sanctions.COLUMNNAME_StartDate)) {
			return CalloutStartDate(ctx, WindowNo, mTab, mField, value);
		}
		return null;
	}//start
	
	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	private String CalloutStartDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null){
			return "";
		}
		
		if(mTab.getValue(X_HC_Sanctions.COLUMNNAME_HC_Sanction_Rule_ID) == null) {
			return "";
		}	
		
		CalloutSanctionRuleID(ctx, windowNo, mTab, mField, mTab.getValue(X_HC_Sanctions.COLUMNNAME_HC_Sanction_Rule_ID));
		
		return "";
	}//CalloutStartDate
	
	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	private String CalloutSanctionRuleID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd");
		Calendar calStartDate 	= Calendar.getInstance();
		Calendar calEndDate	  	= Calendar.getInstance();
		Calendar calToday		= Calendar.getInstance();
		
		calToday.setTimeInMillis(System.currentTimeMillis());
		
		Timestamp startDateTime = null;
		Timestamp endDateTime   =  null;
		
		if(mTab.getValue("startDate")== null) {
			mTab.setValue(X_HC_Sanctions.COLUMNNAME_HC_Sanction_Rule_ID, null);
			mTab.fireDataStatusEEvent("Error: Fill field startDate first", "", false);
		}
		
		startDateTime = (Timestamp)mTab.getValue("startDate");
		X_HC_Sanction_Rule sanctionRule = new X_HC_Sanction_Rule(ctx, (Integer)value, null);
		endDateTime = TimeUtil.addMonths(startDateTime, sanctionRule.getSanction_Period());
		try {
			calStartDate.setTime(sdf.parse(startDateTime.toString()));
			calEndDate.setTime(sdf.parse(endDateTime.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		mTab.setValue("EndDate", endDateTime);
	
		/*if(checkBetweenDate((Calendar)calToday.clone(), (Calendar)calStartDate.clone(), (Calendar)calEndDate.clone())){
			mTab.setValue("IsSanctionActive", true);
		}else{
			mTab.setValue("IsSanctionActive", false);
		}*/
		
		return "";
	}//CalloutSanctionRuleID
	
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
		int HC_EmployeeJob_ID = employee.getActiveSequenceOneEmployeeJob();
		
		if(HC_EmployeeJob_ID <= 0){
			mTab.setValue(X_HC_Sanctions.COLUMNNAME_HC_Employee_ID, null);
			mTab.fireDataStatusEEvent("Error: employee doesn't have active and sequence 1 job", "", false);
		}
			
		MEmployeeJob employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
		mTab.setValue("HC_Job_ID",(Integer)employeeJob.get_Value("HC_Job_ID"));
		mTab.setValue("HC_Org_ID",employeeJob.getHC_Org_ID());

		return "";
		
	}//CalloutEmployeeID
	
	
	/**
	 * Checking the check date between start  and end date
	 * @param check
	 * @param start
	 * @param End
	 * @return
	 * true if check is between start and before end date else false
	 */
	/*private boolean checkBetweenDate(Calendar check, Calendar start, Calendar End){
		if( check.compareTo(start) >= 0 && check.compareTo(End) < 0)
			return true;
		else
			return false;
	}//checkBetweenDate
	*/
}//endClass
