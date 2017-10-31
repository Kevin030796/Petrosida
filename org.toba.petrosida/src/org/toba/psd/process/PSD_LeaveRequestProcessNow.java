package org.toba.psd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.taowi.hcm.imported.classes.TimeUtil;
import org.taowi.hcm.core.model.MEmployee;
import org.toba.psd.model.X_HC_LeaveRequest;
import org.toba.psd.model.X_HC_LeaveType;

/**
 * 
 * @author KevinY
 * Process for HC_LeaveRequest
 * PSD - 2824
 */
public class PSD_LeaveRequestProcessNow extends SvrProcess{
	
	private String p_Status = "";
	private static final String CutiBesar = "CB";
	private static final String CutiTahunan = "CT";
	private static final String CutiBesarPertama ="CB1";
	private int HC_LeaveRequest_ID = 0;
	
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
		HC_LeaveRequest_ID = getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		if(p_Status == null || p_Status.length() <= 0)
			throw new AdempiereException("Error: Status is not selected");
		
		if(HC_LeaveRequest_ID <= 0)
			throw new AdempiereException("Error: Leave Request is not selected");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		X_HC_LeaveRequest leaveReq 	= new X_HC_LeaveRequest(getCtx(), HC_LeaveRequest_ID, get_TrxName());
		X_HC_LeaveType leaveType = new X_HC_LeaveType(getCtx(), leaveReq.getHC_LeaveType_ID(), get_TrxName());
		MEmployee employee 			= new MEmployee(getCtx(), leaveReq.getHC_Employee_ID(), get_TrxName());
		Timestamp WorkStartDate     = employee.getHC_WorkStartDate(); 
		Timestamp today = new Timestamp(System.currentTimeMillis());
		Timestamp leaveStartTime = null; //HC_LeaveStart Date&Time
		Timestamp leaveEndTime   = null; //HC_LeaveEnd Date&Time
		Calendar calWorkStartDate   = Calendar.getInstance();
		Calendar calToday = Calendar.getInstance();
		calWorkStartDate.setTime(sdf.parse(WorkStartDate.toString()));
		calToday.setTime(sdf.parse(today.toString()));
		int date = calWorkStartDate.get(Calendar.DATE);
		int month = calWorkStartDate.get(Calendar.MONTH);
			
		int leavePeriodFrom = 0;
		int leavePeriodTo = 0;
		
		if(!leaveReq.get_ValueAsBoolean("IsKompensasiCuti")){
			if(!leaveReq.get_ValueAsBoolean("IsCutiMendadak")){
				if(TimeUtil.getDaysBetween(today,leaveReq.getHC_LeaveStartDate()) < 13){
					throw new AdempiereException("Error: must request leave 2 weeks before start leave date ");
				}
			}else{
				if(TimeUtil.getDaysBetween(today, leaveReq.getHC_LeaveStartDate()) < 0){
					throw new AdempiereException("Error: can't request leave with start leave date which is yesterday");
				}
			}
		}
		
		String sql = "SELECT "+X_HC_LeaveType.COLUMNNAME_HC_LeaveType_ID+" FROM "+X_HC_LeaveType.Table_Name+" "
				  + "WHERE isLeaveCal = 'Y'";
		List<X_HC_LeaveType> types = new ArrayList<X_HC_LeaveType>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()){
				X_HC_LeaveType type = new X_HC_LeaveType(getCtx(), rs.getInt(1), get_TrxName());
				types.add(type);
			}
		}catch (SQLException e){
			log.log(Level.SEVERE, "Not found leaveType for Leave Calculation", e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
			
		//get Leave type id (Cuti Tahunan, Cuti Besar, Cuti Besar 1)
		int HC_LeaveTypeCT_ID 	= 0;
		int HC_LeaveTypeCB_ID 	= 0;
		int HC_LeaveTypeCB1_ID	 = 0;
		
		for(int i= 0 ; i< types.size() ; i++){
			if(types.get(i).getValue().equals("CT"))
				HC_LeaveTypeCT_ID = types.get(i).getHC_LeaveType_ID();
			if(types.get(i).getValue().equals("CB1"))
				HC_LeaveTypeCB1_ID = types.get(i).getHC_LeaveType_ID();
			else if(types.get(i).getValue().equals("CB"))
				HC_LeaveTypeCB_ID = types.get(i).getHC_LeaveType_ID();
		}
		
		if(!leaveReq.get_ValueAsBoolean("IsKompensasiCuti"))
		{
			if(leaveReq.getHC_LeaveType_ID() == HC_LeaveTypeCB_ID || leaveReq.getHC_LeaveType_ID() == HC_LeaveTypeCB1_ID){
				if(leaveReq.getLeaveDays()!= 7 && leaveReq.getLeaveDays()!= 8 && leaveReq.getLeaveDays()!=15)
				{
					throw new AdempiereException("Error : can't request leave not equals 7 or 8 or 15");
				}
			}else{
				if(leaveReq.getLeaveDays() > 12)
				{
					throw new AdempiereException("Error: leave days can't be more than 12");
				}
			}
		}
		
		
		if(leaveReq.getLeaveBalance()< leaveReq.getLeaveDays()){
			throw new AdempiereException("Error: leave balance is less than leave days");
		}
		
		//RequestProcess Now Parameter : Cancel | Request
		if(p_Status.equals(X_HC_LeaveRequest.STATUS_Cancelled)) {
				if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Cancelled))
					throw new AdempiereException("Error: already Cancelled");
				else if(leaveReq.getStatus().equals("Rej"))
					throw new AdempiereException("Error: already rejected");
				/* if leave document already acc by manager,and employee may cancel, 
				 * WorkAround : 
				 * employee must talk to manager to reject the accepted document leave,
				 * then leave document will return the quota of leave back to document leave calculation
				 */
				else if(leaveReq.getStatus().equals("Acc"))
					throw new AdempiereException("Error: already accepted");
			} else { //if Request
				if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Requested))
					throw new AdempiereException("Error: already Requested");
				else if(leaveReq.getStatus().equals(X_HC_LeaveRequest.STATUS_Cancelled))
					throw new AdempiereException("Error: already cancelled");
				else if(leaveReq.getStatus().equals("Acc"))
					throw new AdempiereException("Error: already accepted");
				else {
					//draft
					if(leaveType.getValue().equals(CutiBesar)|| leaveType.getValue().equals(CutiTahunan) || leaveType.getValue().equals(CutiBesarPertama)){
						//doesn't have leave yet
						if(leaveReq.get_Value("LeavePeriodFrom") == null || leaveReq.get_Value("LeavePeriodTo") == null){
							throw new AdempiereException("Error: Employee doesn't have leave");
						}else {
							//get year period From and To
							leavePeriodFrom = leaveReq.get_ValueAsInt("LeavePeriodFrom");
							leavePeriodTo = leaveReq.get_ValueAsInt("LeavePeriodTo");
							//get time for period from and To  (using date and month from Employee Work Start Date)
							Timestamp timePeriodFrom = Timestamp.valueOf(leavePeriodFrom + "-" + month + "-" + date + " " + "00:00:00.0");
							Timestamp timePeriodTo = Timestamp.valueOf(leavePeriodTo + "-" + month + "-" + date + " " + "00:00:00.0");	
							timePeriodTo = TimeUtil.addDays(timePeriodTo, -1);
							
							//get Leave Start and End Time
							leaveStartTime = leaveReq.getHC_LeaveStartDate();
							leaveEndTime   = leaveReq.getHC_LeaveEndDate();
							
							if( (leaveStartTime.before(timePeriodFrom) || leaveStartTime.after(timePeriodTo)) && 
									(leaveEndTime.before(timePeriodFrom) || leaveEndTime.after(timePeriodTo)) ) {
								throw new AdempiereException("Error: request leave start and end time not valid or not in date of period");
							}
							
							
						}//end Else
					}
					leaveReq.setStatus(p_Status);
					leaveReq.saveEx();
				}//end Else 
			}// end Else
			
		return "";
	}//doIt

}//endClass
