package org.toba.psd.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.toba.psd.model.X_HC_MedicalRecord;

/**
 * @author KevinY
 * Callout For HC_MedicalRecord 
 * PSD - 2767
 */
public class PSD_CalloutMedicalRecord extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_MedicalRecord.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
			
		return "";
	}//start
	
	
	/**
	 * Calling out Medical Record Fields from Employee and Employee Jobs Fields
	 * 
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String
	 */
	private String CalloutEmployeeID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		BigDecimal Age 				= null;
		int calc 					= 0;
		int employeeID 				= (Integer)value;
		MEmployeeJob employeeJob 	= null;
		String EmployeeFullName 	= "";
		
		mTab.setValue("DateTrx", new Timestamp(System.currentTimeMillis()));
		
		MEmployee employee = new MEmployee(ctx, employeeID, null);
		
		if(employee.get_ValueAsString("Name2").isEmpty() 	|| 
		   employee.get_ValueAsString("Name2").equals(null)){
			
			EmployeeFullName = employee.getName();
		}else{
			EmployeeFullName = employee.getName();
			EmployeeFullName = EmployeeFullName + " " + employee.getName2();
		}
		
		if(!mTab.getValue("DateTrx").toString().equals(null) 	  && 
		   !employee.get_Value("Birthday").toString().equals(null)){
			
			Timestamp Birthday = (Timestamp) employee.get_Value("Birthday");
			Timestamp DateTrx  = (Timestamp) mTab.getValue("DateTrx");
			
			Calendar birthdayCal = Calendar.getInstance();
			Calendar dateCal 	 = Calendar.getInstance();
			birthdayCal.setTimeInMillis(Birthday.getTime());
			dateCal.setTimeInMillis(DateTrx.getTime());
			
			calc = dateCal.get(Calendar.YEAR)  - birthdayCal.get(Calendar.YEAR);
			
			if(dateCal.get(Calendar.MONTH) < birthdayCal.get(Calendar.MONTH))
				calc = calc - 1;
			else if(dateCal.get(Calendar.MONTH) == birthdayCal.get(Calendar.MONTH))
				if(dateCal.get(Calendar.DATE) < birthdayCal.get(Calendar.DATE))
					calc = calc - 1;
			
			Age = new BigDecimal(calc);
		}
		
		int employeeJobID = employee.getActiveSequenceOneEmployeeJob();
		if (employeeJobID <= 0) 
			return "No First Sequence Job Record found for Employee Name:" + employee.getName();
		
		employeeJob = new MEmployeeJob(ctx, employeeJobID, null);
		
		mTab.setValue("Name", EmployeeFullName);
		mTab.setValue("Age", Age);
		mTab.setValue("HC_Gender", employee.getHC_Gender());
		if(employeeJob != null)
			mTab.setValue("HC_Job_ID",employeeJob.getHC_Job_ID());
		if(employee.getBirthday() != null)
			mTab.setValue("Birthday", employee.getBirthday());

		return "";
	}//EmployeeData
		
}//EndClass
