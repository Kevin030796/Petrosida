package org.toba.psd.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.taowi.hcm.core.model.MEmployee;
import org.toba.psd.model.X_HC_EmployeeLeaveBalance;
import org.toba.psd.model.X_HC_LeaveType;

/**
 * @author KevinY
 * Callout for HC_EmployeeLeaveBalance
 * not used
 */
public class PSD_CalloutEmployeeLeaveBalance extends CalloutEngine implements IColumnCallout{
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID))
			return CalloutLeaveTypeID(ctx, WindowNo, mTab, mField, value);
		
		return "";
	}//start

	private String CalloutLeaveTypeID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
	
		if(value == null)
			return "";
		
		int HC_Employee_ID 	 		= 0;
		int HC_LeaveType_ID 		= (Integer) value;
		int period			 		= 0;

		String effectiveDate 		= "";
		String datePeriodFrom 		= "";
		String datePeriodTo	  		= "";
		
		Calendar calFlag			= Calendar.getInstance();
		Calendar calToday 	 		= Calendar.getInstance();
		Calendar calEffectiveDate 	= Calendar.getInstance();
	
		calToday.setTimeInMillis(System.currentTimeMillis());
		Timestamp timePeriodTo		= null;
		Timestamp timePeriod		= null;
		SimpleDateFormat sdf 		= new SimpleDateFormat("yyyy-MM-dd");
		
		X_HC_LeaveType leaveType = new X_HC_LeaveType(ctx, HC_LeaveType_ID, null);
		
		if(mTab.getValue("HC_Employee_ID")!=null)
			HC_Employee_ID = (Integer) mTab.getValue("HC_Employee_ID");
		
		MEmployee employee = new MEmployee(ctx, HC_Employee_ID, null);
		
		if(employee.getEffectiveDateFrom() != null)
			effectiveDate = employee.getEffectiveDateFrom().toString();
	
		try {
			calEffectiveDate.setTime(sdf.parse(effectiveDate));
		} catch (ParseException e1) {
			log.info("Error: Can't Parse EffectiveDate");
		}
		
		/*Calculate period from employee work start date until today */
		period 			= calToday.get(Calendar.YEAR) - calEffectiveDate.get(Calendar.YEAR);
		if(calToday.get(Calendar.MONTH) < calEffectiveDate.get(Calendar.MONTH))
			period = period - 1;
		else if(calToday.get(Calendar.MONTH) == calEffectiveDate.get(Calendar.MONTH))
			if(calToday.get(Calendar.DATE) < calEffectiveDate.get(Calendar.DATE))
				period = period - 1;
		//end Calculate Period
		
		if(leaveType.getValue().equals("CT")){
			if(period == 1){// 1 period
					mTab.setValue("SaldoCutiTahunan", leaveType.getLeaveDays() * period);
					calEffectiveDate.add(Calendar.YEAR, 1);
					timePeriod 	= Timestamp.valueOf(sdf.format(calEffectiveDate.getTime()) + " 00:00:00.0");
					mTab.setValue("CTDatePeriod"		, timePeriod);
					calEffectiveDate.add(Calendar.YEAR, 1);
					timePeriodTo = TimeUtil.addDays(Timestamp.valueOf(sdf.format(calEffectiveDate.getTime())+ " 00:00:00.0"), -1);
					mTab.setValue("CTDatePeriodTo"		, timePeriodTo);
					mTab.setValue("CTGetDate"			, mTab.getValue("CTDatePeriod"));
					mTab.setValue("AllocatedAmt"		, new BigDecimal((Integer)mTab.getValue("SaldoCutiTahunan")));
					datePeriodFrom = mTab.getValue("CTDatePeriod").toString();
					datePeriodTo   = mTab.getValue("CTDatePeriodTo").toString();
					mTab.setValue("LeaveBalancePeriod"	, datePeriodFrom.substring(0, 10) + " / " + datePeriodTo.substring(0,10)  );
			}else if(period > 1 ){ //2 period
					
					if(calEffectiveDate.get(Calendar.DATE) == calToday.get(Calendar.DATE) && 
							calEffectiveDate.get(Calendar.MONTH) == calToday.get(Calendar.MONTH)){
						calFlag = (Calendar)calToday.clone();
						calFlag.add(Calendar.YEAR, -1);
						timePeriod = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						mTab.setValue("CTDatePeriod", timePeriod);
						calFlag.add(Calendar.YEAR, 1);
						timePeriodTo = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
						mTab.setValue("CTDatePeriodTo", timePeriodTo);
						mTab.setValue("CTGetDate", mTab.getValue("CTDatePeriod"));
						
						datePeriodFrom = mTab.getValue("CTDatePeriod").toString();
						datePeriodTo	  = mTab.getValue("CTDatePeriodTo").toString();
						mTab.setValue("LeaveBalancePeriod"	, datePeriodFrom.substring(0, 10) + " / " + datePeriodTo.substring(0,10)  );
						mTab.setValue("SaldoCutiTahunan"	, leaveType.getLeaveDays() * 1);
						mTab.setValue("AllocatedAmt"		,  new BigDecimal((Integer)mTab.getValue("SaldoCutiTahunan")));
						//
						X_HC_EmployeeLeaveBalance newLeave = new X_HC_EmployeeLeaveBalance(ctx, 0, null);
						newLeave.set_ValueOfColumn("HC_Employee_ID"	, mTab.getValue("HC_Employee_ID"));
						newLeave.set_ValueOfColumn("HC_LeaveType_ID", mTab.getValue("HC_LeaveType_ID"));
						calFlag = (Calendar)calToday.clone();
						calFlag.add(Calendar.YEAR, 0);
						timePeriod = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						newLeave.set_ValueOfColumn("CTDatePeriod", timePeriod);
						calFlag.add(Calendar.YEAR, 1);
						timePeriodTo = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
						newLeave.set_ValueOfColumn("CTDatePeriodTo", timePeriodTo);
						newLeave.set_ValueOfColumn("CTGetDate", newLeave.get_Value("CTDatePeriod"));
						
						datePeriodFrom = newLeave.get_Value("CTDatePeriod").toString();
						datePeriodTo   = newLeave.get_Value("CTDatePeriodTo").toString();
						newLeave.set_ValueOfColumn("LeaveBalancePeriod"	, datePeriodFrom.substring(0, 10) + " / " + datePeriodTo.substring(0,10)  );
						newLeave.set_ValueOfColumn("SaldoCutiTahunan"	, leaveType.getLeaveDays() * 1);
						newLeave.set_ValueOfColumn("AllocatedAmt"		, new BigDecimal((Integer)newLeave.get_Value("SaldoCutiTahunan")));
						newLeave.saveEx();
					}else{
						calFlag = (Calendar)calEffectiveDate.clone();
						int year = calToday.get(Calendar.YEAR) - calFlag.get(Calendar.YEAR);
						if(calToday.get(Calendar.MONTH) < calFlag.get(Calendar.MONTH))
							year = year - 1;
						else if(calToday.get(Calendar.MONTH) == calEffectiveDate.get(Calendar.MONTH))
							if(calToday.get(Calendar.DATE) < calEffectiveDate.get(Calendar.DATE))
								year = year - 1;
						calFlag.add(Calendar.YEAR, year - 1);
						timePeriod = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						mTab.setValue("CTDatePeriod", timePeriod);
						calFlag.add(Calendar.YEAR, 1);
						timePeriodTo = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
						mTab.setValue("CTDatePeriodTo", timePeriodTo);
						mTab.setValue("CTGetDate"	  , mTab.getValue("CTDatePeriod"));
						
						datePeriodFrom = mTab.getValue("CTDatePeriod").toString();
						datePeriodTo   = mTab.getValue("CTDatePeriodTo").toString();
						mTab.setValue("LeaveBalancePeriod"	, datePeriodFrom.substring(0, 10) + " / " + datePeriodTo.substring(0,10)  );
						mTab.setValue("SaldoCutiTahunan"	, leaveType.getLeaveDays() * 1);
						mTab.setValue("AllocatedAmt", new BigDecimal((Integer)mTab.getValue("SaldoCutiTahunan")));
						
						//
						X_HC_EmployeeLeaveBalance newLeave = new X_HC_EmployeeLeaveBalance(ctx, 0, null);
						newLeave.set_ValueOfColumn("HC_Employee_ID", mTab.getValue("HC_Employee_ID"));
						newLeave.set_ValueOfColumn("HC_LeaveType_ID", mTab.getValue("HC_LeaveType_ID"));
						calFlag = (Calendar)calEffectiveDate.clone();
						year = calToday.get(Calendar.YEAR) - calFlag.get(Calendar.YEAR);
						if(calToday.get(Calendar.MONTH) < calFlag.get(Calendar.MONTH))
							year = year - 1;
						else if(calToday.get(Calendar.MONTH) == calEffectiveDate.get(Calendar.MONTH))
							if(calToday.get(Calendar.DATE) < calEffectiveDate.get(Calendar.DATE))
								year = year - 1;
						calFlag.add(Calendar.YEAR, year);
						timePeriod = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						newLeave.set_ValueOfColumn("CTDatePeriod", timePeriod);
						calFlag.add(Calendar.YEAR, 1);
						timePeriodTo = Timestamp.valueOf(sdf.format(calFlag.getTime()) + " 00:00:00.0");
						timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
						newLeave.set_ValueOfColumn("CTDatePeriodTo"		, timePeriodTo);
						newLeave.set_ValueOfColumn("CTGetDate"	  		, newLeave.get_Value("CTDatePeriod"));
						newLeave.set_ValueOfColumn("SaldoCutiTahunan"	, leaveType.getLeaveDays() * 1);
						newLeave.set_ValueOfColumn("AllocatedAmt", new BigDecimal((Integer)newLeave.get_Value("SaldoCutiTahunan")));
						
						datePeriodFrom 	  = newLeave.get_Value("CTDatePeriod").toString();
						datePeriodTo	  = newLeave.get_Value("CTDatePeriodTo").toString();
						newLeave.set_ValueOfColumn("LeaveBalancePeriod"	, datePeriodFrom.substring(0, 10) + " / " 
										+ datePeriodTo.substring(0,10)  );
						newLeave.saveEx();
					}//endElse
			}//endElseIf
		}else if(leaveType.getValue().equals("CB1") || leaveType.getValue().equals("CB2")){
			if(period == 6){
				calEffectiveDate.add(Calendar.YEAR, 6);
				timePeriod = Timestamp.valueOf(sdf.format(calEffectiveDate.getTime()) + " 00:00:00.0");
				mTab.setValue("CBDatePeriod", timePeriod);
				mTab.setValue("CBGetDate", mTab.getValue("CBDatePeriod"));
				calEffectiveDate.add(Calendar.YEAR, 3);
				timePeriodTo = Timestamp.valueOf(sdf.format(calEffectiveDate.getTime()) + " 00:00:00.0");
				timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
				mTab.setValue("SaldoCutiBesar", leaveType.getLeaveDays() * 1);
			}else if(period > 6){
				int flag = (period-6) / 3;
				String query = "SELECT HC_LeaveType_ID FROM HC_LeaveType WHERE Value like '%CB2%' ";
				int leaveCB2_ID = DB.getSQLValue(null,query);
				X_HC_LeaveType typeCB2 = new X_HC_LeaveType(ctx, leaveCB2_ID, null);
				if(flag == 0){
					calEffectiveDate.add(Calendar.YEAR, 6);
					timePeriod = Timestamp.valueOf(sdf.format(calEffectiveDate.getTime()) + " 00:00:00.0");
					mTab.setValue("CBDatePeriod", timePeriod);
					mTab.setValue("CBGetDate", mTab.getValue("CBDatePeriod"));
					calEffectiveDate.add(Calendar.YEAR, 3);
					timePeriodTo = Timestamp.valueOf(sdf.format(calEffectiveDate.getTime()) + " 00:00:00.0");
					timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
					mTab.setValue("SaldoCutiBesar", leaveType.getLeaveDays() * 1);
				}//endIf
				else if(flag > 0){
					mTab.setValue("HC_LeaveType_ID", leaveCB2_ID);
					calEffectiveDate.add(Calendar.YEAR, 6 + (3 * flag));
					timePeriod = Timestamp.valueOf(sdf.format(calEffectiveDate.getTime()) + " 00:00:00.0");
					mTab.setValue("CBDatePeriod", timePeriod);
					mTab.setValue("CBGetDate", mTab.getValue("CBDatePeriod"));
					calEffectiveDate.add(Calendar.YEAR, 3);
					timePeriodTo = Timestamp.valueOf(sdf.format(calEffectiveDate.getTime()) + " 00:00:00.0");
					timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
					mTab.setValue("SaldoCutiBesar", typeCB2.getLeaveDays() * 1);
				}
			}//endElseIf
			mTab.setValue("AllocatedAmt", mTab.getValue("SaldoCutiBesar"));
			mTab.setValue("CBDatePeriodTo", timePeriodTo);
			mTab.setValue("LeaveBalancePeriod"	, mTab.getValue("CBDatePeriod").toString().substring(0, 10) + " / " 
					+ mTab.getValue("CBDatePeriodTo").toString().substring(0,10)  );
		}else{
			mTab.setValue("AllocatedAmt", new BigDecimal((Integer)leaveType.getLeaveDays()));
		}//endElse
		return "";
	}//CalloutLeaveEndDate*/
	
}//endClass
