package org.toba.psd.callout;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.toba.psd.model.X_HC_DailyExpense;
import org.toba.psd.model.X_HC_RequestPermit;
import org.toba.psd.model.X_HC_TravelRequest;

/**
 * 
 * @author Kevin Yulianto
 * Callout for HC_RequestTravel
 */
public class PSD_CalloutRequestTravel extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		if(mField.getColumnName().equals(X_HC_TravelRequest.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_TravelRequest.COLUMNNAME_DateTrx))
			return CalloutTransactionDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_TravelRequest.COLUMNNAME_StartDate))
			return calloutTravelDays(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(X_HC_TravelRequest.COLUMNNAME_EndDate))
			return calloutTravelDays(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals("Sppd2"))
			return CalloutNomorSPPD(ctx, WindowNo, mTab, mField, value);
		return "";
	}//start
	
	/**
	 * Calling out nomor sppd from sppd 1 , document sequence, sppd 2
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success 
	 */
	private String CalloutNomorSPPD(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		String nomorSPPD = "";
		nomorSPPD = nomorSPPD + mTab.getValue("Sppd1").toString();
		int nomorInduk = Integer.valueOf(mTab.getValue("DocumentNo").toString());
		String nomorUrut = "";
		if(nomorInduk < 10){
			nomorUrut = nomorUrut + "000" + nomorInduk;
		}else if(nomorInduk < 100){
			nomorUrut = nomorUrut + "00"+ nomorInduk ;
		}else if(nomorInduk < 1000){
			nomorUrut = nomorUrut + "0"+ nomorInduk;
		}else if(nomorInduk < 10000){
			nomorUrut = nomorUrut + nomorInduk ;
		}
		
		nomorSPPD = nomorSPPD + nomorUrut +  "/"; 
		nomorSPPD = nomorSPPD + mTab.getValue("Sppd2").toString();
		mTab.setValue("DocumentNo", nomorUrut);
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_NomorSPPD, nomorSPPD);

		return "";
	}
	
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
		
		//get HC_EmployeeJob with seq 1 ,and active
		String sql = "SELECT "+MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID +" FROM "
				+ MEmployeeJob.Table_Name +" WHERE "
				+ MEmployeeJob.COLUMNNAME_HC_Employee_ID+"= ? AND "
				+ MEmployeeJob.COLUMNNAME_SeqNo+"= 1 AND "
				+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
		int HC_EmployeeJob_ID = DB.getSQLValue(null, sql, HC_Employee_ID);
		
		//if employeejob not exists, then return error
		if(HC_EmployeeJob_ID <= 0) {
			mTab.setValue(X_HC_RequestPermit.COLUMNNAME_HC_Employee_ID, null);
			return "Error: Employee doesn't have Sequence 1 Employee Job";
		}
		
		MEmployeeJob employeeJob = new MEmployeeJob(ctx, HC_EmployeeJob_ID, null);
		MJob job = new MJob(ctx, employeeJob.getHC_Job_ID(), null);
		X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(ctx, employeeJob.getHC_EmployeeClass_ID(), null);
		sql = "SELECT "+X_HC_EmployeeClass.COLUMNNAME_HC_EmployeeClass_ID+" FROM "+X_HC_EmployeeClass.Table_Name 
			+" WHERE "+X_HC_EmployeeClass.COLUMNNAME_IsActive+"='Y' AND IsOrganic='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, null);
			rs = pstmt.executeQuery();
			while (rs.next()){
				if(empClass.get_ID() == rs.getInt(1)){
					mTab.setValue("IsOrganic", true);
				}else{
					mTab.setValue("IsOrganic",false);
				}
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, "Not found employee", e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_HC_Org_ID, employeeJob.getHC_Org_ID());
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_HC_Job_ID, employeeJob.getHC_Job_ID());
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_HC_EmployeeCategory_ID, employeeJob.getHC_EmployeeCategory_ID());
		mTab.setValue("HC_JobLevel_ID", job.getHC_JobLevel_ID());
		if(mTab.getValueAsBoolean("IsOrganic")){
			mTab.setValue("Sppd1", "T");
		}else{
			mTab.setValue("Sppd1", "N");
		}
		
		if(job.get_Value(MJob.COLUMNNAME_HC_JobReportTo_ID) == null){
			mTab.setValue(X_HC_TravelRequest.COLUMNNAME_HC_Manager_ID, HC_Employee_ID);
		}
			
		sql = "SELECT "+X_HC_DailyExpense.COLUMNNAME_HC_DailyExpense_ID+" "
				+ "FROM "+X_HC_DailyExpense.Table_Name+" WHERE "+X_HC_DailyExpense.COLUMNNAME_HC_JobLevel_ID+"=? "
				+ "AND "+X_HC_DailyExpense.COLUMNNAME_IsActive+"='Y'";
		int HC_DailyExpense_ID = DB.getSQLValue(null, sql, job.getHC_JobLevel_ID());
		
		if(HC_DailyExpense_ID <= 0){
			return "Error: daily expense doesn't have the record with job level related";
		}
		
		X_HC_DailyExpense dailyExpense = new X_HC_DailyExpense(ctx, HC_DailyExpense_ID, null);
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_HC_DailyExpense,dailyExpense.getPrice());
		BigDecimal totalDailyExpense = new BigDecimal(0);
		BigDecimal dailyExpensePrice = dailyExpense.getPrice();
		BigDecimal travelDays = new BigDecimal(mTab.getValue(X_HC_TravelRequest.COLUMNNAME_HC_TravelDays).toString());
		totalDailyExpense = travelDays.multiply(dailyExpensePrice);
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_TotalDailyExpense, totalDailyExpense);
		return "";
	}//CalloutEmployeeID
	
	
	/**
	 * Callout DateTrx with system time (create new)
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * empty string if success
	 */
	public String CalloutTransactionDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		Timestamp today = new Timestamp(System.currentTimeMillis());
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_DateTrx, today);
		
		return "";
	}//CalloutTransactionDate
	
	/**
	 * Calling out total travel days 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * empty string if success
	 */
	private String calloutTravelDays(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value) {
		
		Timestamp startDate = null;
		Timestamp endDate	= null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calToday = Calendar.getInstance();
		calToday.setTimeInMillis(System.currentTimeMillis());
		Timestamp today = Timestamp.valueOf(sdf.format(calToday.getTime()) + " 00:00:00.00");
		if(value == null) {
			return "";
		}else{			
			if(mField.getColumnName().equals(X_HC_TravelRequest.COLUMNNAME_StartDate)) {
				startDate 			= (Timestamp)value;	
				
				if(startDate.before(today)) {
					mTab.setValue(X_HC_TravelRequest.COLUMNNAME_StartDate, null);
					mTab.fireDataStatusEEvent("Error: travel Start Date before today date", "", false);
					return "";
				}
				
				if(mTab.getValue(X_HC_TravelRequest.COLUMNNAME_EndDate) == null){
					return "";
				}
				
				endDate = (Timestamp)mTab.getValue(X_HC_TravelRequest.COLUMNNAME_EndDate);
			}else if(mField.getColumnName().equals(X_HC_TravelRequest.COLUMNNAME_EndDate)) {
				endDate 		    = (Timestamp)value;
				
				if(endDate.before(today)){
					mTab.setValue(X_HC_TravelRequest.COLUMNNAME_EndDate, null);
					mTab.fireDataStatusEEvent("Error: travel End Date before today date", "", false);
					return "";
				}
				
				if(mTab.getValue(X_HC_TravelRequest.COLUMNNAME_StartDate) == null){
					return "";
				}
				
				startDate = (Timestamp)mTab.getValue(X_HC_TravelRequest.COLUMNNAME_StartDate);
			}
		}
		
		int days = TimeUtil.getDaysBetween(startDate, endDate)+1;
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_HC_TravelDays, days);
		BigDecimal totalDailyExpense = new BigDecimal(0);
		BigDecimal dailyExpensePrice = new BigDecimal(mTab.getValue(X_HC_TravelRequest.COLUMNNAME_HC_DailyExpense).toString());
		BigDecimal travelDays = new BigDecimal(mTab.getValue(X_HC_TravelRequest.COLUMNNAME_HC_TravelDays).toString());
		totalDailyExpense = travelDays.multiply(dailyExpensePrice);
		mTab.setValue(X_HC_TravelRequest.COLUMNNAME_TotalDailyExpense, totalDailyExpense);
		
		return "";
	}//CalloutLeaveDate
	
}//endClass
