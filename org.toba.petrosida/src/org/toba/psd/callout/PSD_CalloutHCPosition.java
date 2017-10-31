package org.toba.psd.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.X_HC_Position;

/**
 * 
 * @author KevinY
 * Callout for Window HC_Position 
 * PSD - 2779
 */
public class PSD_CalloutHCPosition extends CalloutEngine implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(mField.getColumnName().equals(X_HC_Position.COLUMNNAME_HC_Job_ID))
			return CalloutJobID(ctx, WindowNo, mTab, mField, value);
			
		return "";
	}//start
	
	/**
	 * Calling out  grade from Job
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * Empty String if Success
	 */
	private String CalloutJobID(Properties ctx, int windowNo, GridTab mTab,
			GridField mField, Object value){
		
		if(value == null)
			return "";
		
		int JobID = (Integer) value; //HC_Job_ID
		
		MJob Job = new MJob(ctx, JobID, null);
		
		if(Job.getHC_GradeMin_ID() > 0)
			mTab.setValue("HC_GradeMin_ID", Job.getHC_GradeMin_ID());
		if(Job.getHC_GradeMid_ID() > 0)
			mTab.setValue("HC_GradeMid_ID", Job.getHC_GradeMid_ID());
		if(Job.getHC_GradeMax_ID() > 0)
			mTab.setValue("HC_GradeMax_ID", Job.getHC_GradeMax_ID());
		
		return "";
	}//CalloutJobID
	
}//endClass
