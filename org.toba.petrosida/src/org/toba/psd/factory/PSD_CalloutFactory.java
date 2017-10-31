package org.toba.psd.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJobDataChange;
import org.taowi.hcm.core.model.X_HC_EmployeeDataChange;
import org.taowi.hcm.core.model.X_HC_Position;
import org.toba.psd.callout.PSD_CalloutAccomodationPoint;
import org.toba.psd.callout.PSD_CalloutEmployeeData;
import org.toba.psd.callout.PSD_CalloutEmployeeDataChange;
import org.toba.psd.callout.PSD_CalloutEmployeeJob;
import org.toba.psd.callout.PSD_CalloutHCPosition;
import org.toba.psd.callout.PSD_CalloutHistoryOrganisasi;
import org.toba.psd.callout.PSD_CalloutEmployeeJobDataChange;
import org.toba.psd.callout.PSD_CalloutLeaveRequest;
import org.toba.psd.callout.PSD_CalloutMedicalRecord;
import org.toba.psd.callout.PSD_CalloutRequestLeaveAdmin;
import org.toba.psd.callout.PSD_CalloutRequestOvertime;
import org.toba.psd.callout.PSD_CalloutRequestPermit;
import org.toba.psd.callout.PSD_CalloutRequestTravel;
import org.toba.psd.callout.PSD_CalloutEmployeeRetirement;
import org.toba.psd.callout.PSD_CalloutSanctions;
import org.toba.psd.callout.PSD_CalloutSelection;
import org.toba.psd.callout.PSD_CalloutTrainingAttendance;
import org.toba.psd.callout.PSD_CalloutTransportPoint;
import org.toba.psd.callout.test;
import org.toba.psd.model.X_HC_AccomodationPoint;
import org.toba.psd.model.X_HC_Committee;
import org.toba.psd.model.X_HC_LeaveRequest;
import org.toba.psd.model.X_HC_LeaveRequest_Adm;
import org.toba.psd.model.X_HC_MedicalRecord;
import org.toba.psd.model.X_HC_RequestOvertime;
import org.toba.psd.model.X_HC_RequestPermit;
import org.toba.psd.model.X_HC_EmployeeRetirement;
import org.toba.psd.model.X_HC_Sanctions;
import org.toba.psd.model.X_HC_Selection;
import org.toba.psd.model.X_HC_TrainingAttendance;
import org.toba.psd.model.X_HC_TransportPoint;
import org.toba.psd.model.X_HC_TravelRequest;
import org.toba.psd.model.X_IHC_JobDataChange;

public class PSD_CalloutFactory implements IColumnCalloutFactory {

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) {
		
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
		
		if (tableName.equals(X_HC_MedicalRecord.Table_Name))
			list.add(new PSD_CalloutMedicalRecord());
		else if(tableName.equals(X_HC_EmployeeDataChange.Table_Name))
			list.add(new PSD_CalloutEmployeeDataChange());
		else if(tableName.equals(X_HC_Position.Table_Name))
			list.add(new PSD_CalloutHCPosition());
		/*else if(tableName.equals(X_HC_EmployeeLeaveBalance.Table_Name))
			list.add(new PSD_CalloutEmployeeLeaveBalance());*/
		else if(tableName.equals(X_HC_LeaveRequest_Adm.Table_Name))
			list.add(new PSD_CalloutRequestLeaveAdmin());
		else if(tableName.equals(MEmployee.Table_Name))
			list.add(new PSD_CalloutEmployeeData());
		else if(tableName.equals(X_HC_TrainingAttendance.Table_Name))
			list.add(new PSD_CalloutTrainingAttendance());
		else if(tableName.equals(X_HC_Committee.Table_Name))
			list.add(new PSD_CalloutHistoryOrganisasi());
		else if(tableName.equals(MJobDataChange.Table_Name))
			list.add(new PSD_CalloutEmployeeJobDataChange());
		else if(tableName.equals(X_HC_Sanctions.Table_Name))
			list.add(new PSD_CalloutSanctions());
		else if(tableName.equals(X_HC_LeaveRequest.Table_Name))
			list.add(new PSD_CalloutLeaveRequest());
		else if(tableName.equals(X_HC_RequestOvertime.Table_Name))
			list.add(new PSD_CalloutRequestOvertime());
		else if(tableName.equals(X_HC_RequestPermit.Table_Name))
			list.add(new PSD_CalloutRequestPermit());
		else if(tableName.equals(MEmployeeJob.Table_Name))
			list.add(new PSD_CalloutEmployeeJob());
		else if(tableName.equals(X_HC_Selection.Table_Name))
			list.add(new PSD_CalloutSelection());
		else if(tableName.equals(X_HC_EmployeeRetirement.Table_Name))
			list.add(new PSD_CalloutEmployeeRetirement());
		else if(tableName.equals(X_HC_TravelRequest.Table_Name))
			list.add(new PSD_CalloutRequestTravel());
		else if(tableName.equals(X_HC_TransportPoint.Table_Name))
			list.add(new PSD_CalloutTransportPoint());
		else if(tableName.equals(X_HC_AccomodationPoint.Table_Name))
			list.add(new PSD_CalloutAccomodationPoint());
		else if(tableName.equals(X_IHC_JobDataChange.Table_Name))
			list.add(new test());
		return list != null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
