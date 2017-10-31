package org.toba.psd.callout;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.toba.psd.model.X_HC_AccomodationExpense;
import org.toba.psd.model.X_HC_AccomodationPoint;
import org.toba.psd.model.X_HC_TravelRequest;

public class PSD_CalloutAccomodationPoint extends CalloutEngine implements IColumnCallout{
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals(X_HC_AccomodationPoint.COLUMNNAME_Address1))
			return CalloutAddress(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_AccomodationPoint.COLUMNNAME_StartDate))
			return CalloutTotalPrice(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_AccomodationPoint.COLUMNNAME_EndDate))
			return CalloutTotalPrice(ctx, WindowNo, mTab, mField, value);
		
		return "";
	}//start
	
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
	private String CalloutTotalPrice(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		if(mTab.getValue(X_HC_AccomodationPoint.COLUMNNAME_StartDate) == null ||
				mTab.getValue(X_HC_AccomodationPoint.COLUMNNAME_EndDate) == null){
			return "";
		}
		
		Timestamp startDate = (Timestamp) mTab.getValue(X_HC_AccomodationPoint.COLUMNNAME_StartDate);
		Timestamp endDate = (Timestamp) mTab.getValue(X_HC_AccomodationPoint.COLUMNNAME_EndDate);
		
		if(endDate.before(startDate)) {
			mTab.setValue(X_HC_AccomodationPoint.COLUMNNAME_StartDate, null);
			mTab.setValue(X_HC_AccomodationPoint.COLUMNNAME_EndDate, null);
			mTab.fireDataStatusEEvent("Error: End Date before start Date", "", false);
			return "";
		}
		
		int calcDays = TimeUtil.getDaysBetween(startDate, endDate) + 1;
		BigDecimal days = new BigDecimal(calcDays);
		BigDecimal price = new BigDecimal(mTab.getValue(X_HC_AccomodationPoint.COLUMNNAME_Price).toString());
		mTab.setValue(X_HC_AccomodationPoint.COLUMNNAME_TotalPrice, price.multiply(days));
		
		return "";
	}//CalloutTotalPrice

	
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
	private String CalloutAddress(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){

		if(value== null)
			return "";
		
		int HC_TravelRequest_ID = Integer.valueOf(mTab.getValue("HC_TravelRequest_ID").toString());
		X_HC_TravelRequest travelRequest = new X_HC_TravelRequest(ctx, HC_TravelRequest_ID, null);
		int HC_JobLevel_ID = Integer.valueOf(travelRequest.get_Value("HC_JobLevel_ID").toString());
		
		String sql = "SELECT "+X_HC_AccomodationExpense.COLUMNNAME_HC_AccomodationExpense_ID+" "
				+ "FROM "+X_HC_AccomodationExpense.Table_Name+" WHERE "
				+ X_HC_AccomodationExpense.COLUMNNAME_HC_JobLevel_ID + "=? AND "
				+ X_HC_AccomodationExpense.COLUMNNAME_IsActive+"='Y'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int HC_AccomodationExpense_ID =0;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, HC_JobLevel_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				HC_AccomodationExpense_ID = rs.getInt(1);
			}
		} catch (SQLException e){
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		if(HC_AccomodationExpense_ID > 0){
			X_HC_AccomodationExpense AccomodationExpense = new X_HC_AccomodationExpense(ctx, HC_AccomodationExpense_ID, null);
			mTab.setValue(X_HC_AccomodationPoint.COLUMNNAME_Price, AccomodationExpense.getPrice());
			mTab.setValue(X_HC_AccomodationPoint.COLUMNNAME_TotalPrice, AccomodationExpense.getPrice());
		}
		
		return "";
	}//calloutTransportationType
}
