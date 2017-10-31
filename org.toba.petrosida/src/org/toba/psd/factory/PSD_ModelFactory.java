package org.toba.psd.factory;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeGrade;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJobDataChange;
import org.toba.psd.model.MRequestPermit;
import org.toba.psd.model.X_HC_AccomodationExpense;
import org.toba.psd.model.X_HC_AccomodationPoint;
import org.toba.psd.model.X_HC_DailyExpense;
import org.toba.psd.model.X_HC_EmployeeRetirement;
import org.toba.psd.model.X_HC_HistoryTravel;
import org.toba.psd.model.X_HC_LeaveDebt;
import org.toba.psd.model.X_HC_LeaveRequest_Adm;
import org.toba.psd.model.X_HC_OtherPoint;
import org.toba.psd.model.X_HC_PJK_AccomodationPoint;
import org.toba.psd.model.X_HC_PJK_OtherPoint;
import org.toba.psd.model.X_HC_PJK_TransportPoint;
import org.toba.psd.model.X_HC_PJK_TravelRequest;
import org.toba.psd.model.X_HC_RequestOvertime;
import org.toba.psd.model.MSanctions;
import org.toba.psd.model.MSelection;
import org.toba.psd.model.X_HC_Committee;
import org.toba.psd.model.X_HC_Course;
import org.toba.psd.model.X_HC_CourseCategory;
import org.toba.psd.model.X_HC_EmployeeLeaveBalance;
import org.toba.psd.model.X_HC_JobLevel;
import org.toba.psd.model.X_HC_Leave;
import org.toba.psd.model.X_HC_LeaveRequest;
import org.toba.psd.model.X_HC_LeaveType;
import org.toba.psd.model.X_HC_ManpowerPlanning;
import org.toba.psd.model.X_HC_MedicalRecord;
import org.toba.psd.model.X_HC_Mutation;
import org.toba.psd.model.X_HC_OrganizationHistory;
import org.toba.psd.model.X_HC_SalaryChange;
import org.toba.psd.model.X_HC_Sanction_Rule;
import org.toba.psd.model.X_HC_Shift;
import org.toba.psd.model.X_HC_TrainingAttendance;
import org.toba.psd.model.X_HC_TrainingClass;
import org.toba.psd.model.X_HC_TransportPoint;
import org.toba.psd.model.X_HC_TravelRequest;

/**
 * Generic Model Factory
 * @author Double Click Sistemas C.A. - http://dcs.net.ve
 * @author Saúl Piña - spina@dcs.net.ve
 */
public class PSD_ModelFactory implements IModelFactory{
	
	private static HashMap<String, String> mapTableModels = new HashMap<String, String>();
	static
	{	
			//HCM Model Core
			mapTableModels.put(MJobDataChange.Table_Name			, "org.taowi.hcm.core.model.MJobDataChange");
			mapTableModels.put(MEmployee.Table_Name					, "org.taowi.hcm.core.model.MEmployee");
			mapTableModels.put(MEmployeeJob.Table_Name				, "org.taowi.hcm.core.model.MEmployeeJob");
			mapTableModels.put(MEmployeeGrade.Table_Name			, "org.taowi.hcm.core.model.MEmployeeGrade");
			
			//Petrosida Model Core
			mapTableModels.put(X_HC_EmployeeLeaveBalance.Table_Name	, "org.toba.psd.model.X_HC_EmployeeLeaveBalance");
			mapTableModels.put(X_HC_LeaveType.Table_Name			, "org.toba.psd.model.X_HC_LeaveType");
			mapTableModels.put(X_HC_LeaveRequest.Table_Name			, "org.toba.psd.model.X_HC_LeaveRequest");
			mapTableModels.put(X_HC_Leave.Table_Name				, "org.toba.psd.model.X_HC_Leave");
			mapTableModels.put(X_HC_JobLevel.Table_Name				, "org.toba.psd.model.X_HC_JobLevel");
			mapTableModels.put(X_HC_Shift.Table_Name				, "org.toba.psd.model.X_HC_Shift");
			mapTableModels.put(X_HC_SalaryChange.Table_Name			, "org.toba.psd.model.X_HC_SalaryChange");
			mapTableModels.put(X_HC_RequestOvertime.Table_Name		, "org.toba.psd.model.X_HC_RequestOvertime");
			mapTableModels.put(MRequestPermit.Table_Name			, "org.toba.psd.model.MRequestPermit");
			mapTableModels.put(X_HC_MedicalRecord.Table_Name		, "org.toba.psd.model.X_HC_MedicalRecord");
			mapTableModels.put(X_HC_TrainingClass.Table_Name		, "org.toba.psd.model.X_HC_TrainingClass");
			mapTableModels.put(X_HC_TrainingAttendance.Table_Name	, "org.toba.psd.model.X_HC_TrainingAttendance");
			mapTableModels.put(X_HC_Course.Table_Name				, "org.toba.psd.model.X_HC_Course");
			mapTableModels.put(X_HC_CourseCategory.Table_Name		, "org.toba.psd.model.X_HC_CourseCategory");
			mapTableModels.put(X_HC_JobLevel.Table_Name				, "org.toba.psd.model.X_HC_JobLevel");
			mapTableModels.put(X_HC_ManpowerPlanning.Table_Name		, "org.toba.psd.model.X_HC_ManpowerPlanning");
			mapTableModels.put(X_HC_OrganizationHistory.Table_Name	, "org.toba.psd.model.X_HC_OrganizationHistory");
			mapTableModels.put(X_HC_Committee.Table_Name			, "org.toba.psd.model.X_HC_Committee");
			mapTableModels.put(X_HC_Mutation.Table_Name			    , "org.toba.psd.model.X_HC_Mutation");
			mapTableModels.put(X_HC_Sanction_Rule.Table_Name		, "org.toba.psd.model.X_HC_Sanction_Rule");
			mapTableModels.put(X_HC_EmployeeRetirement.Table_Name	, "org.toba.psd.model.X_HC_EmployeeRetirement");
			mapTableModels.put(X_HC_DailyExpense.Table_Name			, "org.toba.psd.model.X_HC_DailyExpense");
			mapTableModels.put(X_HC_AccomodationPoint.Table_Name	, "org.toba.psd.model.X_HC_AccomodationPoint");
			mapTableModels.put(X_HC_TransportPoint.Table_Name		, "org.toba.psd.model.X_HC_TransportPoint");
			mapTableModels.put(X_HC_OtherPoint.Table_Name			, "org.toba.psd.model.X_HC_OtherPoint");
			mapTableModels.put(X_HC_TravelRequest.Table_Name		, "org.toba.psd.model.X_HC_TravelRequest");
			mapTableModels.put(X_HC_PJK_TravelRequest.Table_Name	, "org.toba.psd.model.X_HC_PJK_TravelRequest");
			mapTableModels.put(X_HC_PJK_AccomodationPoint.Table_Name, "org.toba.psd.model.X_HC_PJK_AccomodationPoint");
			mapTableModels.put(X_HC_PJK_OtherPoint.Table_Name		, "org.toba.psd.model.X_HC_PJK_OtherPoint");
			mapTableModels.put(X_HC_PJK_TransportPoint.Table_Name	, "org.toba.psd.model.X_HC_PJK_TransportPoint");
			mapTableModels.put(X_HC_AccomodationExpense.Table_Name	, "org.toba.psd.model.X_HC_AccomodationExpense");
			mapTableModels.put(X_HC_HistoryTravel.Table_Name		, "org.toba.psd.model.X_HC_HistoryTravel");
			mapTableModels.put(X_HC_LeaveDebt.Table_Name			, "org.toba.psd.model.X_HC_LeaveDebt");
			mapTableModels.put(X_HC_LeaveRequest_Adm.Table_Name		, "org.toba.psd.model.X_HC_LeaveRequest_Adm");
			mapTableModels.put(MSanctions.Table_Name				, "org.toba.psd.model.MSanctions");
			mapTableModels.put(MSelection.Table_Name				, "org.toba.psd.model.MSelection");
			
	}
	
	@Override
	public Class<?> getClass(String tableName) {
		if (mapTableModels.containsKey(tableName)) {
			Class<?> act = null;
			try {
				act = Class.forName(mapTableModels.get(tableName));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
				return act;
		
		} else 
			return null;
		
	}//getClass

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		
		if (mapTableModels.containsKey(tableName)) {
			Class<?> clazz = null;
			Constructor<?> ctor = null;
			PO object = null;
			try {
				clazz = Class.forName(mapTableModels.get(tableName));
				ctor = clazz.getConstructor(Properties.class, int.class, String.class);
				object = (PO) ctor.newInstance(new Object[] {Env.getCtx(), Record_ID, trxName});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				return object;
		} else 	   
		   return null;
		
	}//getPO

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		
		if (mapTableModels.containsKey(tableName)) {
			Class<?> clazz = null;
			Constructor<?> ctor = null;
			PO object = null;
			try {
				clazz = Class.forName(mapTableModels.get(tableName));
				ctor = clazz.getConstructor(Properties.class, ResultSet.class, String.class);
				object = (PO) ctor.newInstance(new Object[] {Env.getCtx(), rs, trxName});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				return object;
				
		} else  
			return null;
		
	}//getPO

}//endClass
