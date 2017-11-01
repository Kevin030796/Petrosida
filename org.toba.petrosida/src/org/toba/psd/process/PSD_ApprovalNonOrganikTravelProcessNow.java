package org.toba.psd.process;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Role;
import org.compiere.model.X_AD_User;
import org.compiere.model.X_AD_User_Roles;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.toba.psd.model.X_HC_AccomodationPoint;
import org.toba.psd.model.X_HC_HistoryTravel;
import org.toba.psd.model.X_HC_JobLevel;
import org.toba.psd.model.X_HC_OtherPoint;
import org.toba.psd.model.X_HC_PJK_AccomodationPoint;
import org.toba.psd.model.X_HC_PJK_OtherPoint;
import org.toba.psd.model.X_HC_PJK_TransportPoint;
import org.toba.psd.model.X_HC_PJK_TravelRequest;
import org.toba.psd.model.X_HC_TransportPoint;
import org.toba.psd.model.X_HC_TravelRequest;

public class PSD_ApprovalNonOrganikTravelProcessNow extends SvrProcess{
	private String p_Status = "";
	private int p_HC_TravelRequest_ID = 0;
	private String approvedByKabag = "KBG";
	//private String Kabag = "Kabag";
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if(name.equals("Status"))
				p_Status 	   = para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		p_HC_TravelRequest_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(p_HC_TravelRequest_ID <= 0)
			throw new AdempiereException("Error: Requested Travel is not selected");
		X_HC_TravelRequest travelRequest = new X_HC_TravelRequest(getCtx(), p_HC_TravelRequest_ID, get_TrxName());
		BigDecimal totalExpense = new BigDecimal(0);
		//calculateTransportPointExpense
		totalExpense = totalExpense.add(calculateTransportPointExpense(travelRequest));
		//calculate AccomodationExpense
		totalExpense = totalExpense.add(calculateAccomodationExpense(travelRequest));
		//calculate OtherPointExpense
		totalExpense = totalExpense.add(calculateOtherPointExpense(travelRequest));
		
		totalExpense = totalExpense.add(new BigDecimal(travelRequest
				.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalDailyExpense)
				.toString()));
		
		travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalExpense, totalExpense);
		travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_HC_ApprovedPrePayment, totalExpense);
		travelRequest.saveEx();
		
		//check if user is manager or sdm
		int m_user_id = Env.getAD_User_ID(getCtx());
		int m_role_id = 0;
		boolean isSDM = false;
		String roleApproveTravel = "Confirm SDM";//SDM
		X_AD_User user = new X_AD_User(getCtx(), m_user_id, get_TrxName());
		
		String sql = "SELECT "+X_AD_Role.COLUMNNAME_AD_Role_ID+" FROM "+X_AD_Role.Table_Name+" WHERE "
				+ X_AD_Role.COLUMNNAME_IsActive + "='Y' AND "
				+ X_AD_Role.COLUMNNAME_Name + " LIKE ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setString(1, roleApproveTravel);
			rs = pstmt.executeQuery();
			if(rs.next()){
				m_role_id = rs.getInt(1);
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, "Not found role sdm", e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		
		sql = "SELECT "+X_AD_User_Roles.COLUMNNAME_AD_User_ID+" FROM "+X_AD_User_Roles.Table_Name+" WHERE "
				+ X_AD_User_Roles.COLUMNNAME_AD_Role_ID+"=? AND "
				+ X_AD_User_Roles.COLUMNNAME_AD_User_ID+"=?";
		pstmt = null;
		 rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt(1, m_role_id);
			pstmt.setInt(2, m_user_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				isSDM = true;
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, "Not found user role", e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		int m_employee_id = user.get_ValueAsInt("HC_Employee_ID");
		
		if(m_employee_id <= 0){
			throw new AdempiereException("Error: Your user isn't an employee ");
		}
		
		if(travelRequest.getStatus().equals(X_HC_TravelRequest.STATUS_Rejected)){
			throw new AdempiereException("Error: Already rejected");
		}
		
		if(isSDM == true){
			//sdm
			if(!travelRequest.getStatus().equals(X_HC_TravelRequest.STATUS_ApprovedByManager)
					|| !(travelRequest.get_ValueAsBoolean("IsComplete"))){
				throw new AdempiereException("Error: travel request not approved yet by manager");
			}
			
			if(p_Status.equals("Acc")){
				travelRequest.setStatus(X_HC_TravelRequest.STATUS_ApprovedBySDM);
			}else{
				travelRequest.setStatus(X_HC_TravelRequest.STATUS_Rejected);
			}
			
			MakeHistoryTravel(travelRequest, m_employee_id);
			GeneratePJKRequestTravel(travelRequest);
		}else{
			
			//get HC_EmployeeJob manager with seq 1 ,and active
			sql = "SELECT "+MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID +" FROM "
					+ MEmployeeJob.Table_Name +" WHERE "
					+ MEmployeeJob.COLUMNNAME_HC_Employee_ID+"= ? AND "
					+ MEmployeeJob.COLUMNNAME_SeqNo+"= 1 AND "
					+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
			int HC_EmployeeJob_ID = 0;
			pstmt = null;
			 rs = null;
			try{
				pstmt = DB.prepareStatement (sql, get_TrxName());
				pstmt.setInt(1, m_employee_id);
				rs = pstmt.executeQuery();
				if(rs.next()){
					HC_EmployeeJob_ID = rs.getInt(1);
				}
			}
			catch (SQLException e){
				log.log(Level.SEVERE, "Not found user role", e);
			}
			finally{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			//if employeejob not exists, then return error
			if(HC_EmployeeJob_ID <= 0) {
				throw new AdempiereException("Error: manager doesn't have Sequence 1 Employee Job");
			}
			
			MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
			MJob job = new MJob(getCtx(), employeeJob.getHC_Job_ID(), get_TrxName());
			int jobLevel_id = job.getHC_JobLevel_ID();
			X_HC_JobLevel jobLevel = new X_HC_JobLevel(getCtx(), jobLevel_id, get_TrxName());
			
			if(!(travelRequest.getHC_Manager_ID() == m_employee_id)){
				throw new AdempiereException("Error: you are not manager to approve this document");
			}
			
			if(travelRequest.get_ValueAsBoolean("IsComplete")){
				throw new AdempiereException("Error: can't approve document completed");
			}
			
			if(p_Status.equals("Acc")){
				if(jobLevel.getValue().equals("Kabag")){
					int jobReportTo_ID = job.getHC_JobReportTo_ID();//manager
					sql = "SELECT "+MEmployeeJob.COLUMNNAME_HC_Employee_ID +" FROM "
							+ MEmployeeJob.Table_Name +" WHERE "
							+ MEmployeeJob.COLUMNNAME_HC_Job_ID+"= ? AND "
							+ MEmployeeJob.COLUMNNAME_SeqNo+"= 1 AND "
							+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"' AND "
							+ MEmployeeJob.COLUMNNAME_IsActive+"='Y'";
					int HC_Manager_ID = DB.getSQLValue(get_TrxName(), sql, jobReportTo_ID);
					travelRequest.setStatus(approvedByKabag);
					travelRequest.setHC_Manager_ID(HC_Manager_ID);
				}else{
					travelRequest.setStatus(X_HC_TravelRequest.STATUS_ApprovedByManager);
					travelRequest.setIsComplete(true);
				}
				MakeHistoryTravel(travelRequest, m_employee_id);
			}else{
				travelRequest.setStatus(X_HC_TravelRequest.STATUS_Rejected);
			}
		}
			
		travelRequest.saveEx();
		
		if(p_Status.equals("Acc")){
			return "Success Process Accept travel ";
		}
		else
			return "Success Process Reject travel";
	}//doIt
	
	/**
	 * Make history travel
	 * @param travelRequest
	 */
	private void MakeHistoryTravel(X_HC_TravelRequest travelRequest, int manager_ID){
		
		//get HC_EmployeeJob with seq 1 ,and active
		String sql = "SELECT "+MEmployeeJob.COLUMNNAME_HC_EmployeeJob_ID +" FROM "
				+ MEmployeeJob.Table_Name +" WHERE "
				+ MEmployeeJob.COLUMNNAME_HC_Employee_ID+"= ? AND "
				+ MEmployeeJob.COLUMNNAME_SeqNo+"= 1 AND "
				+ MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'";
		int HC_EmployeeJob_ID = DB.getSQLValue(get_TrxName(), sql, manager_ID);
		
		//if employeejob not exists, then return error
		if(HC_EmployeeJob_ID <= 0) {
			throw new AdempiereException("Error: manager doesn't have Sequence 1 Employee Job");
		}
		
		MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), HC_EmployeeJob_ID, get_TrxName());
		MJob job = new MJob(getCtx(), employeeJob.getHC_Job_ID(), get_TrxName());
		
		X_HC_HistoryTravel historyTravel = new X_HC_HistoryTravel(getCtx(), 0, get_TrxName());
		historyTravel.setHC_Employee_ID(travelRequest.getHC_Employee_ID());
		historyTravel.setHC_TravelRequest_ID(travelRequest.get_ID());
		historyTravel.setStatus(travelRequest.getStatus());
		historyTravel.setHC_Manager_ID(manager_ID);
		historyTravel.setAD_Org_ID(travelRequest.getAD_Org_ID());
		historyTravel.setHC_JobLevel_ID(job.getHC_JobLevel_ID());
		historyTravel.setIsActive(true);
		historyTravel.saveEx();
	}//makeHistoryTravel
	
	/**
	 * 
	 * @param travelRequest
	 */
	private void GeneratePJKRequestTravel(X_HC_TravelRequest travelRequest){
		List<X_HC_TransportPoint> transportPoints = new ArrayList<X_HC_TransportPoint>();
		List<X_HC_AccomodationPoint> AccomodationPoints = new ArrayList<X_HC_AccomodationPoint>();
		List<X_HC_OtherPoint> otherPoints = new ArrayList<X_HC_OtherPoint>();
		
		String whereClause = X_HC_TravelRequest.COLUMNNAME_HC_TravelRequest_ID +"=? AND "
				+ ""+X_HC_TransportPoint.COLUMNNAME_IsActive+"='Y'";
		
		transportPoints = new Query(getCtx(), X_HC_TransportPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		AccomodationPoints = new Query(getCtx(), X_HC_AccomodationPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		otherPoints = new Query(getCtx(), X_HC_OtherPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		
		X_HC_PJK_TravelRequest pjkTravelRequest = new X_HC_PJK_TravelRequest(getCtx(), 0, get_TrxName());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_Employee_ID, travelRequest.getHC_Employee_ID());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_Job_ID, travelRequest.getHC_Job_ID());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_DateTrx, travelRequest.getDateTrx());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_JobLevel_ID, travelRequest.get_ValueAsInt("HC_JobLevel_ID"));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_StartDate, travelRequest.getStartDate());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_StartTime, travelRequest.getStartTime());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_EndDate, travelRequest.getEndDate());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_EndTime, travelRequest.getEndTime());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_Org_ID, travelRequest.getHC_Org_ID());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_AD_Org_ID, travelRequest.getAD_Org_ID());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_TravelDays, travelRequest.getHC_TravelDays());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_TotalAccomodationExpense, 
				new BigDecimal(travelRequest.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalAccomodationExpense).toString()));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_TotalDailyExpense, 
				new BigDecimal(travelRequest.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalDailyExpense).toString()));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_RealizationDailyExpense, 
				new BigDecimal(travelRequest.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalDailyExpense).toString()));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_TotalExpense,
				new BigDecimal(travelRequest.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalExpense).toString()));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_TotalOtherPoint, 
				new BigDecimal(travelRequest.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalOtherPoint).toString()));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_TotalTransportExpense, 
				new BigDecimal(travelRequest.get_Value(X_HC_TravelRequest.COLUMNNAME_TotalTransportExpense).toString()));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_Status, X_HC_TravelRequest.STATUS_Drafted);
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_RequestedPrePayment, travelRequest.getHC_RequestedPrePayment());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_ApprovedPrePayment, travelRequest.getHC_ApprovedPrePayment());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_Manager_ID, travelRequest.get_Value("HC_Manager_ID"));
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_NomorSPPD, travelRequest.getNomorSPPD());
		pjkTravelRequest.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_EmployeeCategory_ID, travelRequest.getHC_EmployeeCategory_ID());
		pjkTravelRequest.set_ValueOfColumn("HC_TravelRequest_ID",travelRequest.getHC_TravelRequest_ID());
		pjkTravelRequest.set_ValueOfColumn("IsOrganic", travelRequest.get_ValueAsBoolean("IsOrganic"));
		pjkTravelRequest.setIsActive(true);
		pjkTravelRequest.setIsApproved(true);
		pjkTravelRequest.saveEx();
		
		if(transportPoints.size() > 0)
			for(X_HC_TransportPoint transportPoint: transportPoints){
				X_HC_PJK_TransportPoint pjkTransportPoint = new X_HC_PJK_TransportPoint(getCtx(), 0, get_TrxName());
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_SeqNo, transportPoint.getSeqNo());
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_Address1, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_Address1));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_Address2, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_Address2));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_Description, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_Description));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_Date1, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_Date1));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_Time1, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_Time1));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_TravelType, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_TravelType));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_TransportationType, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_TransportationType));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_Price, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_Price));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_AddOnPrice, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_AddOnPrice));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TransportPoint.COLUMNNAME_TotalPrice, transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_TotalPrice));
				pjkTransportPoint.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_PJK_TravelRequest_ID, pjkTravelRequest.get_ID());
				pjkTransportPoint.set_ValueOfColumn("HC_TransportPoint_ID", transportPoint.getHC_TransportPoint_ID());
				pjkTransportPoint.set_ValueOfColumn("RealizationTransportPoint", transportPoint.get_Value(X_HC_TransportPoint.COLUMNNAME_TotalPrice));
				pjkTransportPoint.setIsActive(true);
				pjkTransportPoint.saveEx();
			}
		if(AccomodationPoints.size() > 0)
			for(X_HC_AccomodationPoint accomodationPoint : AccomodationPoints){
				X_HC_PJK_AccomodationPoint pjkAccomodationPoint = new X_HC_PJK_AccomodationPoint(getCtx(), 0, get_TrxName());
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_SeqNo, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_SeqNo));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_Address1, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_Address1));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_Description, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_Description));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_StartDate, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_StartDate));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_StartTime, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_StartTime));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_EndDate, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_EndDate));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_EndTime, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_EndTime));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_Price, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_Price));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_AccomodationPoint.COLUMNNAME_TotalPrice, accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_TotalPrice));
				 pjkAccomodationPoint.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_PJK_TravelRequest_ID, pjkTravelRequest.get_ID());
				 pjkAccomodationPoint.set_ValueOfColumn("HC_AccomodationPoint_ID",accomodationPoint.getHC_AccomodationPoint_ID());
				 pjkAccomodationPoint.set_ValueOfColumn("RealizationAccomodationPoint", accomodationPoint.get_Value(X_HC_AccomodationPoint.COLUMNNAME_TotalPrice));
				 pjkAccomodationPoint.setIsActive(true);
				 pjkAccomodationPoint.saveEx();
			}
		if(otherPoints.size() > 0){
			for(X_HC_OtherPoint otherPoint : otherPoints){
				 X_HC_PJK_OtherPoint pjkOtherPoint = new X_HC_PJK_OtherPoint(getCtx(), 0, get_TrxName());
				 pjkOtherPoint.set_ValueOfColumn(X_HC_PJK_OtherPoint.COLUMNNAME_SeqNo, otherPoint.get_Value(X_HC_OtherPoint.COLUMNNAME_SeqNo));
				 pjkOtherPoint.set_ValueOfColumn(X_HC_PJK_OtherPoint.COLUMNNAME_Date1, otherPoint.get_Value(X_HC_OtherPoint.COLUMNNAME_Date1));
				 pjkOtherPoint.set_ValueOfColumn(X_HC_PJK_OtherPoint.COLUMNNAME_Description, otherPoint.get_Value(X_HC_OtherPoint.COLUMNNAME_Description));			 
				 pjkOtherPoint.set_ValueOfColumn(X_HC_PJK_OtherPoint.COLUMNNAME_Price, otherPoint.get_Value(X_HC_OtherPoint.COLUMNNAME_Price));
				 pjkOtherPoint.set_ValueOfColumn(X_HC_PJK_TravelRequest.COLUMNNAME_HC_PJK_TravelRequest_ID, pjkTravelRequest.get_ID());
				 pjkOtherPoint.set_ValueOfColumn("HC_OtherPoint_ID", otherPoint.getHC_OtherPoint_ID());
				 pjkOtherPoint.set_ValueOfColumn("RealizationOtherPoint", otherPoint.get_Value(X_HC_OtherPoint.COLUMNNAME_Price));
				 pjkOtherPoint.setIsActive(true);
				 pjkOtherPoint.saveEx();
			}
		}
	}
	
	/**
	 * Calculate all transport point expense
	 * @param travelRequest
	 */
	private BigDecimal calculateTransportPointExpense(X_HC_TravelRequest travelRequest){
		
		List<X_HC_TransportPoint> transportPoints = new ArrayList<X_HC_TransportPoint>();
		String whereClause = X_HC_TravelRequest.COLUMNNAME_HC_TravelRequest_ID +"=? AND "
				+ ""+X_HC_TransportPoint.COLUMNNAME_IsActive+"='Y'";
		transportPoints = new Query(getCtx(), X_HC_TransportPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		if(transportPoints.size() > 0) {
			for(X_HC_TransportPoint transportPoint : transportPoints) {
				total = total.add(transportPoint.getTotalPrice());
			}
			travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalTransportExpense, total);
			travelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
		
	}//calculateTransportPointExpense
	
	/**
	 * Calculate all total accomodation expense
	 * @param travelRequest
	 */
	private BigDecimal calculateAccomodationExpense(X_HC_TravelRequest travelRequest){
		
		List<X_HC_AccomodationPoint> AccomodationPoints = new ArrayList<X_HC_AccomodationPoint>();
		String whereClause = X_HC_TravelRequest.COLUMNNAME_HC_TravelRequest_ID +"=? AND "
				+ ""+X_HC_AccomodationPoint.COLUMNNAME_IsActive+"='Y'";
		AccomodationPoints = new Query(getCtx(), X_HC_AccomodationPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		Timestamp startDate = null;
		Timestamp endDate = null;
		if(AccomodationPoints.size() > 0) {
			for(X_HC_AccomodationPoint AccomodationPoint : AccomodationPoints) {
				startDate = AccomodationPoint.getStartDate();
				endDate = AccomodationPoint.getEndDate();
				int calcDay = TimeUtil.getDaysBetween(startDate, endDate)+1;
				BigDecimal days = new BigDecimal(calcDay);
				total = total.add(AccomodationPoint.getTotalPrice().multiply(days));
			}
			travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalAccomodationExpense, total);
			travelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
	}//calculateAccomodationExpense
	
	/**
	 * @param travelRequest
	 */
	private BigDecimal calculateOtherPointExpense(X_HC_TravelRequest travelRequest){
		List<X_HC_OtherPoint> otherPoints = new ArrayList<X_HC_OtherPoint>();
		String whereClause = X_HC_TravelRequest.COLUMNNAME_HC_TravelRequest_ID +"=? AND "
				+ ""+X_HC_OtherPoint.COLUMNNAME_IsActive+"='Y'";
		otherPoints = new Query(getCtx(), X_HC_OtherPoint.Table_Name, whereClause, get_TrxName())
						.setParameters(travelRequest.get_ID())
						.list();
		
		BigDecimal total = new BigDecimal(0);
		if(otherPoints.size() > 0) {
			for(X_HC_OtherPoint otherPoint : otherPoints) {
				total = total.add(otherPoint.getPrice());
			}
			travelRequest.set_ValueOfColumn(X_HC_TravelRequest.COLUMNNAME_TotalOtherPoint, total);
			travelRequest.saveEx();
			return total;
		}else{
			return new BigDecimal(0);
		}
	}//calculateOtherPointExpense
	
}//endClass
