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
import org.compiere.model.Query;
import org.taowi.hcm.core.model.MEmployee;

/**
 * @author KevinY
 * Callout for HC_Employee
 * PSD - 2789
 */
public class PSD_CalloutEmployeeData extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(MEmployee.COLUMNNAME_Birthday))
			return CalloutBirthDay(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals(MEmployee.COLUMNNAME_HC_WorkStartDate))
			return CalloutWorkStartDate(ctx, WindowNo, mTab, mField, value);
		else if(mField.getColumnName().equals("HC_NIK"))
			return CalloutNIK(ctx, WindowNo, mTab, mField, value);
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
	private String CalloutNIK(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		String HC_NIK = value.toString();
		
		String whereClause = "HC_NIK = ?";
		int HC_Employee_ID = new Query(ctx, MEmployee.Table_Name, whereClause, null)
							.setParameters(HC_NIK)
							.firstId();
		
		if(HC_Employee_ID > 0){
			MEmployee employee = new MEmployee(ctx, HC_Employee_ID, null);
			return "Error: NIK "+HC_NIK+" has been already used by "+employee.getValue()+"";
		}
		mTab.setValue(MEmployee.COLUMNNAME_Value, HC_NIK);
		
		return "";
	}//CalloutNIK
	
	/**
	 * Calling out Pensiun Date, Masa Persiapan Pensiun, Age from Birthday Date
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * String empty if Success
	 */
	private String CalloutBirthDay(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
	
		String birth 				 = value.toString();
		SimpleDateFormat sdf 		 = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp pensiun			 = null;
		Timestamp persiapanPensiun 	 = null;
		Calendar calBirthday 		 = Calendar.getInstance();
		Calendar calPensiun 		 = Calendar.getInstance();
		Calendar calPersiapanPensiun = Calendar.getInstance();
		Calendar calToday 			 = Calendar.getInstance();
		
		try{
			calBirthday.setTime(sdf.parse(birth));
			calPensiun.setTime(sdf.parse(birth));
		}catch(Exception e)
		{
			log.info("Error: Fail Set Time");
		}
		
		calPensiun.add(Calendar.MONTH, 660);
		String Pensiunan 		 = sdf.format(calPensiun.getTime());
		pensiun 	    		 = Timestamp.valueOf(Pensiunan + " 00:00:00.0");
		
		calPersiapanPensiun 	 = calPensiun;
		calPersiapanPensiun.add(Calendar.MONTH, -6);
		
		String PersiapanPensiunan 	= sdf.format(calPersiapanPensiun.getTime());
		persiapanPensiun 			= Timestamp.valueOf(PersiapanPensiunan + " 00:00:00.0");
		
		//calculate age from birthday until today
		int year = 0;
		year = CalculateYear((Calendar)calBirthday.clone(), (Calendar)calToday.clone());
		if(year <= 0) {
			mTab.setValue("Birthday", null);
			return "Error: Age can't be 0";
		}else{
			mTab.setValue("Age", (Integer)year);
		}
		/*int year = calToday.get(Calendar.YEAR) - calBirthday.get(Calendar.YEAR);
		if(year <= 0){
			mTab.setValue("Birthday", null);
			return "Error: Age can't be 0";
		}
		else if(year > 0){
			if(calToday.get(Calendar.MONTH) < calBirthday.get(Calendar.MONTH))
				year = year - 1;
			else if(calToday.get(Calendar.MONTH) == calBirthday.get(Calendar.MONTH))
				if(calToday.get(Calendar.DATE) < calBirthday.get(Calendar.DATE))
					year = year - 1;
			
			mTab.setValue("Age", (Integer)year);
		}*/
	
		mTab.setValue("PensiunDate"			, pensiun);
		mTab.setValue("MasaPersiapanPensiun", persiapanPensiun);
		
		return "";
	}//CalloutBirthDay
	
	
	/**
	 * Calling out WorkPeriod from HC_WorkStartDate
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Sucess
	 */
	private String CalloutWorkStartDate(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		SimpleDateFormat sdf 		= new SimpleDateFormat("yyyy-MM-dd");
		String StartDate 			= ((Timestamp)value).toString();

		Calendar calStartDate 		= Calendar.getInstance();
		Calendar calToday			= Calendar.getInstance();
		int calc					= 0;
		
		try {
			calStartDate.setTime(sdf.parse(StartDate));
		} catch (ParseException e) {
			log.info("Error: Can't convert Time");
		}
		calToday.setTimeInMillis(System.currentTimeMillis());
		
		//calculate year for HC_WorkPeriodDate
		calc = CalculateYear((Calendar)calStartDate.clone(),(Calendar)calToday.clone());
		
		/*calc = calToday.get(Calendar.YEAR)  - calStartDate.get(Calendar.YEAR);
		if(calToday.get(Calendar.MONTH) < calStartDate.get(Calendar.MONTH))
			calc = calc - 1;
		else if(calToday.get(Calendar.MONTH) == calStartDate.get(Calendar.MONTH))
			if(calToday.get(Calendar.DATE) < calStartDate.get(Calendar.DATE))
				calc = calc - 1;
		*/
		mTab.setValue("HC_WorkPeriodDate", calc +"");
		
		return "";
	}//CalloutWorkStartDate
	
	/**
	 * @param c_start
	 * @param c_end
	 * @return
	 * year from calculation start until end
	 */
	private int CalculateYear(Calendar c_start, Calendar c_end){
		int year = 0;
		year = c_end.get(Calendar.YEAR)  - c_start.get(Calendar.YEAR);
		if(c_end.get(Calendar.MONTH) < c_start.get(Calendar.MONTH))
			return (year - 1);
		else if(c_end.get(Calendar.MONTH) == c_start.get(Calendar.MONTH))
			if(c_end.get(Calendar.DATE) < c_start.get(Calendar.DATE))
				return (year - 1);
		
		return year;
	}//calculateYear
}//endClass
