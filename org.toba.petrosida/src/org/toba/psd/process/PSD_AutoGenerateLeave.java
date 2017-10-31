package org.toba.psd.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.taowi.hcm.core.model.MEmployee;
import org.toba.psd.model.X_HC_EmployeeLeaveBalance;
import org.toba.psd.model.X_HC_LeaveDebt;
import org.toba.psd.model.X_HC_LeaveType;

public class PSD_AutoGenerateLeave extends SvrProcess{
	
	//private int p_HC_Employee_ID = 0;
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			/*else if(name.equals("HC_Employee_ID"))
				p_HC_Employee_ID = para[i].getParameterAsInt();*/
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}//prepare

	@Override
	protected String doIt() throws Exception {
		
		int period			 		= 0;

		String workStartDate 		= "";
		//String datePeriodFrom 		= "";
		//String datePeriodTo	  		= "";
		
		Calendar calToday 	 			= Calendar.getInstance();
		Calendar calWorkStartDate 		= Calendar.getInstance();
		
		calToday.setTimeInMillis(System.currentTimeMillis());
		SimpleDateFormat sdf 		= new SimpleDateFormat("yyyy-MM-dd");
		
		/*String sql = "SELECT "+MEmployee.COLUMNNAME_HC_Employee_ID+" FROM "+MEmployee.Table_Name+" "
				+ "WHERE "+MEmployee.COLUMNNAME_HC_Employee_ID+" IN ("
				+ "SELECT DISTINCT "+MEmployeeJob.COLUMNNAME_HC_Employee_ID+" FROM "+MEmployeeJob.Table_Name+" "
				+ "WHERE "+ MEmployeeJob.COLUMNNAME_IsActive +"='Y' "
				+ "AND " +MEmployeeJob.COLUMNNAME_HC_Status +" ='" + MEmployeeJob.HC_STATUS_Active+ "' "
				+ "AND "+ MEmployeeJob.COLUMNNAME_HC_JobAction+" IS NOT NULL "
				+ "AND "+ MEmployeeJob.COLUMNNAME_SeqNo +" = 1)"
				+ "ORDER BY HC_Employee_ID ASC";
		List<MEmployee> employees = new ArrayList<MEmployee>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()){
				MEmployee emp = new MEmployee(getCtx(), rs.getInt(1), get_TrxName());
				employees.add(emp);
			}
		}
		catch (SQLException e){
			log.log(Level.SEVERE, "Not found employee", e);
		}
		finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}//getAllActivePermanentEmployees
		*/
		String sql = "SELECT DISTINCT emp.HC_Employee_ID FROM HC_EmployeeJob empJob "
				+ " LEFT JOIN HC_Employee emp ON emp.HC_Employee_ID = empJob.HC_Employee_ID"
				+ " LEFT JOIN HC_EmployeeClass empClass ON empClass.HC_EmployeeClass_ID = empJob.HC_EmployeeClass_ID "
				+ " WHERE empJob.seqNo=1"
				+ " AND empJob.HC_Status='A'"
				+ " AND emp.HC_Status='A'"
				+ " AND emp.IsActive='Y'"
				+ " AND empJob.IsActive='Y'"
				+ " AND empClass.IsActive='Y'"
				+ " AND empClass.value like 'Permanent'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MEmployee> employees = new ArrayList<MEmployee>();
		try{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next()){
				employees.add(new MEmployee(getCtx(), rs.getInt(1), get_TrxName()));
			}
		}catch(Exception e){
			log.log(Level.SEVERE, "Not found role sdm", e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		//employees.add(new MEmployee(getCtx(), p_HC_Employee_ID, get_TrxName()));
		if(employees.size() <= 0)
			throw new AdempiereException("Error: employee not found");
	
		sql = "SELECT "+X_HC_LeaveType.COLUMNNAME_HC_LeaveType_ID+" FROM "+X_HC_LeaveType.Table_Name+" "
			  + "WHERE isLeaveCal = 'Y'";
		List<X_HC_LeaveType> types = new ArrayList<X_HC_LeaveType>();
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
			else if(types.get(i).getValue().equals("CB1"))
				HC_LeaveTypeCB1_ID = types.get(i).getHC_LeaveType_ID();
			else if(types.get(i).getValue().equals("CB"))
				HC_LeaveTypeCB_ID = types.get(i).getHC_LeaveType_ID();
		}

		for(MEmployee employee: employees){
			
			int HC_EmployeeJob_ID = employee.getActiveSequenceOneEmployeeJob();
			
			if(HC_EmployeeJob_ID <= 0 ) {
				continue;
			}
			
			if(employee.getHC_WorkStartDate() != null)
				workStartDate = employee.getHC_WorkStartDate().toString();
			try {
				calWorkStartDate.setTime(sdf.parse(workStartDate));
			} catch (ParseException e1) {
				log.info("Error: Can't Parse Work Start Date");
			}
			
			//getPeriod from work start date until today
			period 	= calToday.get(Calendar.YEAR) - calWorkStartDate.get(Calendar.YEAR);
			if(calToday.get(Calendar.MONTH) < calWorkStartDate.get(Calendar.MONTH))
				period = period - 1;
			else if(calToday.get(Calendar.MONTH) == calWorkStartDate.get(Calendar.MONTH))
				if(calToday.get(Calendar.DATE) < calWorkStartDate.get(Calendar.DATE))
					period = period - 1;
			
			if(period > 0){
				if(period == 1){
					//CT
					CheckAndCreateCT((Calendar)calWorkStartDate.clone(), (period), HC_LeaveTypeCT_ID, employee);
				}else if(period == 2){
					//CT
					CheckAndCreateCT((Calendar)calWorkStartDate.clone(), (period), HC_LeaveTypeCT_ID, employee);
				}else if(period > 2){
					//CT
					CheckAndCreateCT((Calendar)calWorkStartDate.clone(), (period), HC_LeaveTypeCT_ID, employee);	
					
					//inactive Leave CT (2 years previous)
					
					X_HC_EmployeeLeaveBalance previous_second_LeaveCT = check((Calendar)calWorkStartDate.clone(), period - 2, HC_LeaveTypeCT_ID, employee);
					
					if(previous_second_LeaveCT != null){
						previous_second_LeaveCT.setIsActive(false);
						previous_second_LeaveCT.saveEx();
					}
					
					//CB1
					if(period >= 6 && period < 9){
						int tempPeriod = period;
						if(period != 6){
							tempPeriod = tempPeriod - (tempPeriod - 6);
						}
						X_HC_EmployeeLeaveBalance leaveCB1 = check((Calendar)calWorkStartDate.clone(), tempPeriod, HC_LeaveTypeCB1_ID, employee);
						
						if(leaveCB1 == null){
							CreateNewCB1((Calendar)calWorkStartDate.clone(), tempPeriod, HC_LeaveTypeCB1_ID, employee);
						}
					}//period == 6
			
					if(period >= 9){
						if(period == 9){
							//CB1
							X_HC_EmployeeLeaveBalance leaveCB1 = check((Calendar)calWorkStartDate.clone(), (period - 3), HC_LeaveTypeCB1_ID, employee);
							
							if(leaveCB1 == null){
								CreateNewCB1((Calendar)calWorkStartDate.clone(), (period - 3), HC_LeaveTypeCB1_ID, employee);
							}
							
							//CB
							X_HC_EmployeeLeaveBalance leaveCB = check((Calendar)calWorkStartDate.clone(), (period), HC_LeaveTypeCB_ID, employee);
							
							if(leaveCB == null){
								CreateNewCB((Calendar)calWorkStartDate.clone(), period, HC_LeaveTypeCB_ID, employee);
							}
							
							//inactive prevCB1
							leaveCB1 = check((Calendar)calWorkStartDate,period-3,HC_LeaveTypeCB1_ID,employee);
							
							if(leaveCB1 != null){
								leaveCB1.setIsActive(false);
								leaveCB1.saveEx();
							}
						}else if(period > 9){
								//CB1
								int tempPeriod = period;
								tempPeriod = tempPeriod - ((tempPeriod - 6) % 3);
								
								//X_HC_EmployeeLeaveBalance leaveCB1 = check((Calendar)calWorkStartDate.clone(),tempPeriod - (tempPeriod - 6),HC_LeaveTypeCB1_ID, employee);
					
								/*if(leaveCB1 == null){
									CreateNewCB1((Calendar)calWorkStartDate.clone(), tempPeriod - (tempPeriod - 6), HC_LeaveTypeCB1_ID, employee);
								}*/
								
								
								//CB
								X_HC_EmployeeLeaveBalance leaveCB = check((Calendar)calWorkStartDate.clone(),tempPeriod, HC_LeaveTypeCB_ID, employee);
								
								if(leaveCB == null){
									CreateNewCB((Calendar)calWorkStartDate.clone(), tempPeriod, HC_LeaveTypeCB_ID, employee);
								}
								
								//inactive prevCB
								X_HC_EmployeeLeaveBalance prevLeaveCB = check((Calendar)calWorkStartDate, tempPeriod-3, HC_LeaveTypeCB_ID,employee);
								
								if(prevLeaveCB!= null){
									prevLeaveCB.setIsActive(false);
									prevLeaveCB.saveEx();
								}	
						}//end Else If
					}//period >= 9 && period%3 == 0
				}//period > 2
			}//period > 0
		}//end For
			
		return "Success Generate Leave";
	}//doIt
	
	
	/**
	 * 
	 * @param calWorkStartDate
	 * @param period
	 * @param HC_LeaveType_ID
	 * @param employee
	 * @return
	 */
	public X_HC_EmployeeLeaveBalance check(Calendar calWorkStartDate, int period,int HC_LeaveType_ID, MEmployee employee){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		X_HC_EmployeeLeaveBalance leave = null;
		Calendar calTempWorkStartDate = (Calendar) calWorkStartDate.clone();
		
		String sql = "SELECT "+X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_EmployeeLeaveBalance_ID+" FROM "+X_HC_EmployeeLeaveBalance.Table_Name+" "
				+ "WHERE "+X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_Employee_ID+" = ? "
				+ "AND "+X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID+" = ? "
				+ "AND "+X_HC_EmployeeLeaveBalance.COLUMNNAME_CTDatePeriod+"= ?";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		calTempWorkStartDate.add(Calendar.YEAR, period);
		try{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt(1, employee.getHC_Employee_ID());
			pstmt.setInt(2, HC_LeaveType_ID);
			pstmt.setTimestamp(3, Timestamp.valueOf(sdf.format(calTempWorkStartDate.getTime()) + " 00:00:00.0"));
			rs = pstmt.executeQuery();
			if(rs.next())
				leave = new X_HC_EmployeeLeaveBalance(getCtx(), rs.getInt(1), get_TrxName());
		}catch (SQLException e){
			log.log(Level.SEVERE, "not found employee leave in period "+period, e);
		}finally{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return leave;
	}//check
	
	
	private void CheckAndCreateCT(Calendar calWorkStartDate, int period, int HC_LeaveTypeCT_ID, MEmployee employee){
		X_HC_EmployeeLeaveBalance leaveCT = null;
		if(period == 1 ){
			leaveCT = check((Calendar)calWorkStartDate.clone(), (period), HC_LeaveTypeCT_ID, employee);
			if(leaveCT == null){
				CreateNewCT((Calendar)calWorkStartDate.clone(),period, HC_LeaveTypeCT_ID, employee);
			}	
		}else{
			leaveCT = check((Calendar)calWorkStartDate.clone(), (period-1), HC_LeaveTypeCT_ID, employee);
			if(leaveCT == null){
				CreateNewCT((Calendar)calWorkStartDate.clone(),(period-1), HC_LeaveTypeCT_ID, employee);
			}	
			
			leaveCT = check((Calendar)calWorkStartDate.clone(), (period), HC_LeaveTypeCT_ID, employee);
			if(leaveCT == null){
				CreateNewCT((Calendar)calWorkStartDate.clone(),(period), HC_LeaveTypeCT_ID, employee);
			}	
		}
	}//CheckAndCreateCT
	
	/**
	 * 
	 * @param calWorkStartDate
	 * @param period
	 * @param HC_LeaveTypeCT_ID
	 * @param employee
	 */
	public void CreateNewCT(Calendar calWorkStartDate, int period,int HC_LeaveTypeCT_ID, MEmployee employee){
		Calendar calTempWorkStartDate = null;
		Timestamp TimePeriod = null;
		Timestamp TimePeriodTo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datePeriodFrom = null;
		String datePeriodTo = null;
		
		int[] LeaveDebtIds = employee.getUtangCutiIDs();
		int utangCutiDays = 0;
		if(LeaveDebtIds.length > 0) {
			for(int leaveDebt_Id: LeaveDebtIds) {
				X_HC_LeaveDebt leaveDebt = new X_HC_LeaveDebt(getCtx(), leaveDebt_Id, get_TrxName());
				utangCutiDays= utangCutiDays + Integer.valueOf(leaveDebt.get_Value("LeaveDays").toString());
				leaveDebt.setIsActive(false);
				leaveDebt.saveEx();
			}
		}
		
		X_HC_LeaveType typeCT = new X_HC_LeaveType(getCtx(), HC_LeaveTypeCT_ID, get_TrxName());
		X_HC_EmployeeLeaveBalance newLeaveCT = new X_HC_EmployeeLeaveBalance(getCtx(), 0, get_TrxName());
		
		calTempWorkStartDate = (Calendar)calWorkStartDate.clone();
		calTempWorkStartDate.add(Calendar.YEAR, period);
		TimePeriod 	= Timestamp.valueOf(sdf.format(calTempWorkStartDate.getTime()) + " 00:00:00.0");
		calTempWorkStartDate.add(Calendar.YEAR, 1);
		TimePeriodTo = TimeUtil.addDays(Timestamp.valueOf(sdf.format(calTempWorkStartDate.getTime())+ " 00:00:00.0"), -1);
		
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_Employee_ID, employee.get_ID());
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID, typeCT.getHC_LeaveType_ID());
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_SaldoCutiTahunan, typeCT.getLeaveDays() - utangCutiDays);
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_CTDatePeriod, TimePeriod);
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_CTDatePeriodTo, TimePeriodTo);
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_CTGetDate, newLeaveCT.get_Value(X_HC_EmployeeLeaveBalance.COLUMNNAME_CTDatePeriod));
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_AllocatedAmt, 
				new BigDecimal(((Integer)newLeaveCT.get_Value(X_HC_EmployeeLeaveBalance.COLUMNNAME_SaldoCutiTahunan)) - utangCutiDays));
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_UsedCutiTahunan, utangCutiDays);
		datePeriodFrom = newLeaveCT.get_Value(X_HC_EmployeeLeaveBalance.COLUMNNAME_CTDatePeriod).toString();
		datePeriodTo   = newLeaveCT.get_Value(X_HC_EmployeeLeaveBalance.COLUMNNAME_CTDatePeriodTo).toString();
		newLeaveCT.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_LeaveBalancePeriod, datePeriodFrom.substring(0, 10) + " / " + datePeriodTo.substring(0,10)  );
		newLeaveCT.saveEx();
	}//CreateNewCT
	
	/**
	 * 
	 * @param calWorkStartDate
	 * @param period
	 * @param HC_LeaveTypeCB1_ID
	 * @param employee
	 */
	private void CreateNewCB1(Calendar calWorkStartDate, int period,int HC_LeaveTypeCB1_ID, MEmployee employee){
		Calendar calTempWorkStartDate = null;
		Timestamp TimePeriod = null;
		Timestamp TimePeriodTo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		X_HC_LeaveType typeCB1 = new X_HC_LeaveType(getCtx(), HC_LeaveTypeCB1_ID, get_TrxName());
		X_HC_EmployeeLeaveBalance newLeaveCB1 = new X_HC_EmployeeLeaveBalance(getCtx(), 0, get_TrxName());
		
		calTempWorkStartDate = (Calendar)calWorkStartDate.clone();
		calTempWorkStartDate.add(Calendar.YEAR, period);
		TimePeriod = Timestamp.valueOf(sdf.format(calTempWorkStartDate.getTime()) + " 00:00:00.0");
		calTempWorkStartDate.add(Calendar.YEAR, 3);
		TimePeriodTo = Timestamp.valueOf(sdf.format(calTempWorkStartDate.getTime()) + " 00:00:00.0");
		TimePeriodTo = TimeUtil.addDays(TimePeriodTo , -1);
		
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_Employee_ID, employee.getHC_Employee_ID());
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_HC_LeaveType_ID, typeCB1.getHC_LeaveType_ID());
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_CBDatePeriod, TimePeriod);
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_CBGetDate, newLeaveCB1.get_Value("CBDatePeriod"));
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_CBDatePeriodTo, TimePeriodTo);
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_SaldoCutiBesar, typeCB1.getLeaveDays());
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_AllocatedAmt, new BigDecimal((Integer)newLeaveCB1.get_Value(X_HC_EmployeeLeaveBalance.COLUMNNAME_SaldoCutiBesar)));
		newLeaveCB1.set_ValueOfColumn(X_HC_EmployeeLeaveBalance.COLUMNNAME_LeaveBalancePeriod , newLeaveCB1.get_Value(X_HC_EmployeeLeaveBalance.COLUMNNAME_CBDatePeriod).toString().substring(0, 10) + " / " 
				+ newLeaveCB1.get_Value("CBDatePeriodTo").toString().substring(0,10)  );
		newLeaveCB1.saveEx();
	}//CreateNewCB1
	
	
	/**
	 * 
	 * @param calWorkStartDate
	 * @param period
	 * @param HC_LeaveTypeCB_ID
	 * @param employee
	 */
	private void CreateNewCB(Calendar calWorkStartDate, int period,int HC_LeaveTypeCB_ID, MEmployee employee){
		Calendar calTempWorkStartDate = null;
		Timestamp TimePeriod = null;
		Timestamp TimePeriodTo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		X_HC_LeaveType typeCB = new X_HC_LeaveType(getCtx(), HC_LeaveTypeCB_ID, get_TrxName());
		X_HC_EmployeeLeaveBalance newLeaveCB = new X_HC_EmployeeLeaveBalance(getCtx(),0, get_TrxName());
		calTempWorkStartDate = (Calendar) calWorkStartDate.clone();
		calTempWorkStartDate.add(Calendar.YEAR, period);
		TimePeriod = Timestamp.valueOf(sdf.format(calTempWorkStartDate.getTime()) + " 00:00:00.0");
		newLeaveCB.set_ValueOfColumn("HC_Employee_ID", employee.getHC_Employee_ID());
		//newLeaveCB.set_ValueOfColumn("AD_Org_ID", employee.getAD_Org_ID());
		newLeaveCB.set_ValueOfColumn("HC_LeaveType_ID", typeCB.getHC_LeaveType_ID());
		newLeaveCB.set_ValueOfColumn("CBDatePeriod", TimePeriod);
		newLeaveCB.set_ValueOfColumn("CBGetDate", newLeaveCB.get_Value("CBDatePeriod"));
		calTempWorkStartDate.add(Calendar.YEAR, 3);
		TimePeriodTo = Timestamp.valueOf(sdf.format(calTempWorkStartDate.getTime()) + " 00:00:00.0");
		TimePeriodTo = TimeUtil.addDays(TimePeriodTo , -1);
		newLeaveCB.set_ValueOfColumn("CBDatePeriodTo", TimePeriodTo);
		newLeaveCB.set_ValueOfColumn("SaldoCutiBesar", typeCB.getLeaveDays());
		newLeaveCB.set_ValueOfColumn("AllocatedAmt", new BigDecimal((Integer)newLeaveCB.get_Value("SaldoCutiBesar")));
		newLeaveCB.set_ValueOfColumn("LeaveBalancePeriod" , newLeaveCB.get_Value("CBDatePeriod").toString().substring(0, 10) + " / " 
				+ newLeaveCB.get_Value("CBDatePeriodTo").toString().substring(0,10)  );
		newLeaveCB.saveEx();
	}//createNewCB
}//endClass
