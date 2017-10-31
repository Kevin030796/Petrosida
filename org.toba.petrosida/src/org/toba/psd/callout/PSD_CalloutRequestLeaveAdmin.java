package org.toba.psd.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.toba.psd.model.X_HC_LeaveRequest_Adm;

public class PSD_CalloutRequestLeaveAdmin extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_LeaveRequest_Adm.COLUMNNAME_LeaveDays))
			return CalloutEndDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_NonBusinessDay))
			return CalloutEndDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_LeaveStartDate))
			return CalloutEndDate(ctx, WindowNo, mTab, mField, value);
		return null;
	}
	
	/**
	 *
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if success
	 */
	private String CalloutEndDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null){
			return "";
		}
		
		Timestamp endDate = null;
		Timestamp startDate = null;
		int leaveDays = 0;
		int NonBusinessdays = 0;
		
		if(mField.getColumnName().equals(X_HC_LeaveRequest_Adm.COLUMNNAME_LeaveDays)){
			if(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_LeaveStartDate) == null){
				return "";
			}
			
			startDate = Timestamp.valueOf(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_LeaveStartDate).toString());
			leaveDays = Integer.valueOf(value.toString());
			NonBusinessdays = Integer.valueOf(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_NonBusinessDay).toString());
	
		}else if(mField.getColumnName().equals(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_LeaveStartDate)){
			startDate = Timestamp.valueOf(value.toString());
			leaveDays = Integer.valueOf(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_LeaveDays).toString());
			NonBusinessdays = Integer.valueOf(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_NonBusinessDay).toString());
		}else if(mField.getColumnName().equals(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_NonBusinessDay)){
			if(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_LeaveStartDate) == null){
				return "";
			}
			startDate = Timestamp.valueOf(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_LeaveStartDate).toString());
			leaveDays = Integer.valueOf(mTab.getValue(X_HC_LeaveRequest_Adm.COLUMNNAME_LeaveDays).toString());
			NonBusinessdays = Integer.valueOf(value.toString());
		}
		endDate = TimeUtil.addDays(startDate, leaveDays + NonBusinessdays);
		mTab.setValue(X_HC_LeaveRequest_Adm.COLUMNNAME_HC_LeaveEndDate, endDate);
		
		return "";
	}

}
