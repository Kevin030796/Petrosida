package org.toba.psd.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.toba.psd.model.X_HC_TransportPoint;

public class PSD_CalloutTransportPoint extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals(X_HC_TransportPoint.COLUMNNAME_TransportationType))
			return calloutTransportationType(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_TransportPoint.COLUMNNAME_AddOnPrice))
			return calloutTotalPrice(ctx, WindowNo, mTab, mField, value);
		return null;
	}
	
	/**
	 * Callout SearchKey from NIK
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * empty string if success
	 */
	private String calloutTotalPrice(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		BigDecimal addOnPrice = new BigDecimal(value.toString());
		BigDecimal Price = new BigDecimal(mTab.getValue(X_HC_TransportPoint.COLUMNNAME_Price).toString());
		
		BigDecimal totalPrice = Price.add(addOnPrice);
		mTab.setValue(X_HC_TransportPoint.COLUMNNAME_TotalPrice, totalPrice);
		
		return "";
	}//calloutTotalPrice
	
	/**
	 * Callout SearchKey from NIK
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * empty string if success
	 */
	private String calloutTransportationType(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){

		if(value== null)
			return "";
		
		/*
		String transportationType = value.toString();
		
		int HC_TravelRequest_ID = Integer.valueOf(mTab.getValue("HC_TravelRequest_ID").toString());
		X_HC_TravelRequest travelRequest = new X_HC_TravelRequest(ctx, HC_TravelRequest_ID, null);
		int HC_JobLevel_ID = Integer.valueOf(travelRequest.get_Value("HC_JobLevel_ID").toString());
		String sql = "SELECT "+X_HC_TransportExpense.COLUMNNAME_HC_TransportExpense_ID+" "
				+ "FROM "+X_HC_TransportExpense.Table_Name+ " WHERE "
				+ X_HC_TransportExpense.COLUMNNAME_HC_JobLevel_ID + "=? AND "
				+ X_HC_TransportExpense.COLUMNNAME_TransportationType+" LIKE ? AND "
				+ X_HC_TransportExpense.COLUMNNAME_IsActive+"='Y'";
		
		int HC_TransportExpense_ID = DB.getSQLValue(null, sql, HC_JobLevel_ID, transportationType);
		X_HC_TransportExpense transportExpense = new X_HC_TransportExpense(ctx, HC_TransportExpense_ID, null);
		BigDecimal price = transportExpense.getPrice();
		*/
		BigDecimal addOnPrice = new BigDecimal(mTab.getValue(X_HC_TransportPoint.COLUMNNAME_AddOnPrice).toString());
		//mTab.setValue(X_HC_TransportPoint.COLUMNNAME_Price, price);
		mTab.setValue(X_HC_TransportPoint.COLUMNNAME_TotalPrice, addOnPrice);
		return "";
	}//calloutTransportationType

}//endClass
