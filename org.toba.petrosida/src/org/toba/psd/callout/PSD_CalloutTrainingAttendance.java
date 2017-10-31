package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.toba.psd.model.X_HC_TrainingAttendance;
import org.toba.psd.model.X_HC_TrainingClass;

/**
 * @author KevinY
 * Callout for HC_TrainingAttendance
 * PSD - #2805
 */
public class PSD_CalloutTrainingAttendance extends CalloutEngine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_TrainingAttendance.COLUMNNAME_HC_Employee_ID))
			return CalloutEmployeeID(ctx, WindowNo, mTab, mField, value);
		
		return "";
	}//Start

	/**
	 * Calling out data HC_Course_ID, Date Start, End Date from HC_TrainingClass
	 * 
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
		
		int HC_TrainingClass_ID = 0;
		
		if(mTab.getValue("HC_TrainingClass_ID")!= null)
			HC_TrainingClass_ID = (Integer) mTab.getValue("HC_TrainingClass_ID");
		
		X_HC_TrainingClass trainingClass = new X_HC_TrainingClass(ctx, HC_TrainingClass_ID, null);
		if(trainingClass.getHC_Course_ID() > 0	)
			mTab.setValue("HC_Course_ID", (Integer)trainingClass.getHC_Course_ID());
		if(trainingClass.getDateStart()!= null	)
			mTab.setValue("DateStart"	, trainingClass.getDateStart());
		if(trainingClass.getEndDate() != null	)
			mTab.setValue("EndDate"		, trainingClass.getEndDate());

		return "";
	}//CalloutEmployeeID
}//endClass
