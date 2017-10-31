package org.toba.psd.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import org.toba.psd.model.X_HC_RequestPermit;
import org.toba.psd.model.X_HC_Shift;


/**
 * 
 * @author Kevin Yulianto
 * Callout For HC_RequestPermit
 */
public class PSD_CalloutRequestPermit extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals(X_HC_RequestPermit.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestPermit.COLUMNNAME_IsShift))
			return CalloutIsShift(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(
				X_HC_RequestPermit.COLUMNNAME_PermitType))
			return CalloutPermitType(ctx,WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestPermit.COLUMNNAME_HC_Shift_ID))
			return CalloutShift(ctx, WindowNo, mTab, mField, value);
		else if (mField.getColumnName().equals(
				X_HC_RequestPermit.COLUMNNAME_WorktimeDate))
			return CalloutWorkTimeDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(
				X_HC_RequestPermit.COLUMNNAME_StartTime))
			return CalloutTime(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(
				X_HC_RequestPermit.COLUMNNAME_EndTime))
			return CalloutTime(ctx, WindowNo, mTab, mField, value);
		
		return "";
	}//start
	
	/**
	 * Callout for Checking time
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutPermitType(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_StartTime, null);
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_EndTime, null);
		mTab.setValue("TotalPermitHour", null);
		return "";
	}
	
	
	/**
	 * Callout for Checking time
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutTime(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		Timestamp startTime = null;
		Timestamp endTime = null;
		int endHour = 0;
		int endMinute = 0;
		int startHour = 0;
		int startMinute = 0;
		if(mTab.getValue(X_HC_RequestPermit.COLUMNNAME_ShiftStart) == null 
				|| mTab.getValue(X_HC_RequestPermit.COLUMNNAME_ShiftEnds) == null) {
			mTab.setValue(X_HC_RequestPermit.COLUMNNAME_StartTime, null);
			mTab.setValue(X_HC_RequestPermit.COLUMNNAME_EndTime, null);
			mTab.fireDataStatusEEvent("Error:Shift start or shift end is still empty","",false);
			return "";
		}
		
		Timestamp shiftStart = (Timestamp)mTab.getValue(X_HC_RequestPermit.COLUMNNAME_ShiftStart);
		Timestamp shiftEnd = (Timestamp)mTab.getValue(X_HC_RequestPermit.COLUMNNAME_ShiftEnds);
	
		if(mField.getColumnName().equals( X_HC_RequestPermit.COLUMNNAME_StartTime)){
			if(mTab.getValue(X_HC_RequestPermit.COLUMNNAME_EndTime) == null){
				return "";
			}else {
				endTime = (Timestamp)mTab.getValue(X_HC_RequestPermit.COLUMNNAME_EndTime);
			}
			
			startTime = (Timestamp)value;
		}else if(mField.getColumnName().equals(X_HC_RequestPermit.COLUMNNAME_EndTime)){
			if(mTab.getValue(X_HC_RequestPermit.COLUMNNAME_StartTime) == null){
				return "";
			}else {
				startTime = (Timestamp)mTab.getValue(X_HC_RequestPermit.COLUMNNAME_StartTime);
			}
			
			endTime = (Timestamp)value;
		}
		
		endHour   	= Integer.parseInt(endTime.toString().substring(11, 13));
		endMinute   = Integer.parseInt(endTime.toString().substring(14, 16));
		startHour 	= Integer.parseInt(startTime.toString().substring(11, 13));
		startMinute = Integer.parseInt(startTime.toString().substring(14, 16));
		double hour 	= endHour - startHour;
		double minute 	= ((double)(endMinute - startMinute));
		double temp 	= hour + minute /60.0;
		if(temp < 0)
			temp 		= temp + 24;
		
		BigDecimal totalPermitHour = new BigDecimal(temp);
		totalPermitHour = totalPermitHour.setScale(2,BigDecimal.ROUND_DOWN);
		mTab.setValue("TotalPermitHour",totalPermitHour);
		
		Calendar calToday = Calendar.getInstance();
		calToday.setTimeInMillis(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp today = Timestamp.valueOf(sdf.format(calToday.getTime()).toString() + " 00:00:00.0");
		String todayDate = today.toString().substring(0, 10);
		
		endTime = Timestamp.valueOf(todayDate + " " + endTime.toString().substring(11,21));
		startTime = Timestamp.valueOf(todayDate + " " +  startTime.toString().substring(11,21));
	    if(endTime.compareTo(startTime)<=0){
	    	endTime = TimeUtil.addDays(endTime, 1);
	    }
		shiftStart = Timestamp.valueOf(todayDate + " " +  shiftStart.toString().substring(11, 21));
		shiftEnd = Timestamp.valueOf(todayDate + " " +  shiftEnd.toString().substring(11,21));
		if(shiftEnd.compareTo(shiftStart)<=0){
			shiftEnd = TimeUtil.addDays(shiftEnd, 1);
		}
		String permitType = mTab.get_ValueAsString("PermitType");

		//checkTimestamp;
		String DocStatus = mTab.get_ValueAsString(X_HC_RequestPermit.COLUMNNAME_Status);
		if(DocStatus.equals(X_HC_RequestPermit.STATUS_Drafted)){
			if(permitType.equals(X_HC_RequestPermit.PERMITTYPE_IzinDatangTerlambat)) {
				if(!startTime.equals(shiftStart)) {
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_StartTime, shiftStart);
				}
				
				if(!( (endTime.after(shiftStart) || endTime.equals(shiftStart)) && (endTime.before(shiftEnd) || endTime.equals(shiftEnd)) ) ){
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_StartTime, null);
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_EndTime, null);
					mTab.fireDataStatusEEvent("Error: End Date can not before shift start and after shift end","",false);
					return "";
				}
			}else if(permitType.equals(X_HC_RequestPermit.PERMITTYPE_IzinPulangCepat)) {
				if(!endTime.equals(shiftEnd)) {
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_EndTime, shiftEnd);
				}
				
				if(!( (startTime.after(shiftStart) || startTime.equals(shiftStart)) && (startTime.before(shiftEnd) || startTime.equals(shiftEnd)) )){
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_StartTime, null);
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_EndTime,null);
					mTab.fireDataStatusEEvent("Error: start time can not before shift start and after shift end","",false);
					return "";
				}
			}else if(permitType.equals(X_HC_RequestPermit.PERMITTYPE_IzinKeluarArealKerja)){
				if(!( (startTime.after(shiftStart) || startTime.equals(shiftStart)) 
						&& (startTime.before(endTime) || startTime.equals(endTime))
						&& (endTime.after(shiftStart) || endTime.equals(shiftStart))
						&& (endTime.before(shiftEnd) || endTime.equals(shiftEnd)))
				  ){
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_StartTime, null);
					mTab.setValue(X_HC_RequestPermit.COLUMNNAME_EndTime, null);
					mTab.fireDataStatusEEvent("Error: End and start time can not before shift start and after shift end","",false);
					return "";
				}
			}
		}
		return "";
	}//CalloutTime
	

	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutWorkTimeDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_PermitDate, value);
		
		return "";
	}//CalloutWorkTimeDate
	
	/**
	 * Callout Shift start and end time
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutShift(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		int HC_Shift_ID = (Integer) value;
		
		X_HC_Shift shift = new X_HC_Shift(ctx, HC_Shift_ID, null);
		
		boolean isShift = mTab.getValueAsBoolean("IsShift");
		
		if(isShift) {
			mTab.setValue(X_HC_RequestPermit.COLUMNNAME_ShiftStart, shift.getShiftStart());
			mTab.setValue(X_HC_RequestPermit.COLUMNNAME_ShiftEnds, shift.getShiftEnds());
		}

		return "";
	}//CalloutShift
	
	/**
	 * Callout Shift Start,Ends and HC_Shift_ID null
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Sucess
	 */
	private String CalloutIsShift(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_ShiftStart, null);
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_ShiftEnds, null);
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_HC_Shift_ID, null);
		
		return "";
	}//CalloutIsShift
	
	/**
	 * Calling out HC_Job_ID, HC_Org_ID,HC_Manager_ID from HC_EmployeeJob
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
		
		int HC_Employee_ID = (Integer) value;
		MEmployee employee = new MEmployee(ctx, HC_Employee_ID, null);
		int HC_EmployeeJob_ID = employee.getActiveSequenceOneEmployeeJob();
		
		if(HC_EmployeeJob_ID <= 0) {
			mTab.setValue(X_HC_RequestPermit.COLUMNNAME_HC_Employee_ID, null);
			return "Error: Employee doesn't have Sequence 1 Employee Job";
		}
		
		MEmployeeJob employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
		
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_HC_Org_ID, employeeJob.getHC_Org_ID());
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_HC_Job_ID, employeeJob.getHC_Job_ID());
		mTab.setValue(MEmployeeJob.COLUMNNAME_HC_EmployeeClass_ID, employeeJob.getHC_EmployeeClass_ID());
		CalloutTransactionDate(ctx, windowNo, mTab, mField, value);
		
		return "";
	}//CalloutEmployeeID
	
	/**
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutTransactionDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		Timestamp timeToday = new Timestamp(System.currentTimeMillis());
		
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_DateTrx, timeToday);
		mTab.setValue(X_HC_RequestPermit.COLUMNNAME_TimeTrx, timeToday);
		
		return "";
	}//CalloutTransactionDate
	
	
	
}//endClass
