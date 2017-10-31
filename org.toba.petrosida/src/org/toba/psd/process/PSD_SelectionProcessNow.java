package org.toba.psd.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.taowi.hcm.core.model.MEmployee;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.toba.psd.model.MSelection;


/**
 * @author KevinY
 * Process for HC_Selection (Process Now)
 */
public class PSD_SelectionProcessNow extends SvrProcess{
	//Comment: Ask jorvan why must define these three employeeClasses for NIK
	private static final String Probation = "BP";
	//private static final String Contract  = "TK";
	private static final String Permanent = "TP";

	private int HC_Selection_ID = 0;
	
	
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		HC_Selection_ID= getRecord_ID();
	}//prepare

	@Override
	protected String doIt() throws Exception {
		if(HC_Selection_ID <= 0) {
			throw new AdempiereException("Error: Selection is not selected");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MSelection selection = new MSelection(getCtx(), HC_Selection_ID, get_TrxName());
		MEmployee employee = new MEmployee(getCtx(), selection.getHC_Employee_ID(), get_TrxName());
		MEmployeeJob employeeJob = new MEmployeeJob(getCtx(), selection.getHC_EmployeeJob_ID(), get_TrxName());
		
		if(!selection.get_ValueAsBoolean("IsActive")){
			throw new AdempiereException("Error: Selection is not active");
		}
		selection.set_ValueOfColumn("Processed", true);
		//if selection doesn't have nomor sk then set disqualified for employee
		if(selection.get_Value(MSelection.COLUMNNAME_NomorSK) == null) {
			/*Set employee status into Disqualified and Employee Work End Date into today's time process*/
			employee.set_ValueOfColumn(MEmployee.COLUMNNAME_HC_Status, "T");//disqualified
			employee.setIsActive(false);
			employee.saveEx();
			/*End set employee status and Work End Date*/
			
			/*Set isActive in C_BPartner to false*/
			MBPartner bpartner = new MBPartner(getCtx(), employee.getC_BPartner_ID(), get_TrxName());
			bpartner.setIsActive(false);
			bpartner.saveEx();
			/*end Set IsActive in C_BPartner*/
			
			employeeJob.setHC_Status(MEmployeeJob.HC_STATUS_Terminate);
			employeeJob.saveEx();
			return "Employee is disqualified and process completed";
		}else {
			/*Set employee Class to new Employee Class and new nomor SK*/
			X_HC_EmployeeClass empClassTo = new X_HC_EmployeeClass(getCtx(), selection.getHC_EmployeeClassTo_ID(), get_TrxName());
			employeeJob.set_ValueOfColumn("HC_EmpClass_Start", selection.get_Value(MSelection.COLUMNNAME_TanggalNomorSK));
			employeeJob.set_ValueOfColumn("HC_EmpClass_End", selection.get_Value(MSelection.COLUMNNAME_HC_TanggalAkhirTMT));
			employeeJob.setHC_EmployeeClass_ID(selection.getHC_EmployeeClassTo_ID());
			if(empClassTo.getDescription().equals(Permanent)) {
				employeeJob.set_CustomColumn("MemberKoperasi", true);
			}
			employeeJob.set_CustomColumn("NomorSK", selection.getNomorSK());
			employeeJob.saveEx();
			/*End set employee class*/
			
			/*Create new NIK for employee because of permanent employee*/
			Calendar CalWorkStartDate = Calendar.getInstance();
			CalWorkStartDate.setTime(sdf.parse(employee.getHC_WorkStartDate().toString()));
			createNIK(employeeJob, employee, (Calendar)CalWorkStartDate.clone());
			/*end Create new NIK for employee*/
			return "Employee is qualified and process completed";
		}
	}//doIt
	
	/**
	 * Create NIK for Employee and Employee's C_BPartner
	 * Author KevinY
	 * @param employeeJob
	 * @param employee
	 * @param calToday
	 * @return
	 * String Message
	 */
	private String createNIK(MEmployeeJob employeeJob, MEmployee employee, Calendar CalWorkStartDate) {
		String NIK = "";
		
		X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(), employeeJob.getHC_EmployeeClass_ID(), get_TrxName());
		
		//check if employeeJob is for creating NIK, seqNo = 1, and NIK from employee still empty
		if(empClass.get_ValueAsBoolean("isNIK") && employeeJob.getSeqNo() == 1 && employeeJob.get_Value("NomorSK")!= null){
			
			String year = ((Integer)CalWorkStartDate.get(Calendar.YEAR)).toString();
			NIK = NIK + empClass.getDescription();//TP(permanent) | BP(Business Partner) | TK (Contract)
			
			/* if employee class is Probation then
			 use 00 not two last digits of year */
			if(!empClass.getDescription().equalsIgnoreCase(Probation))
				NIK = NIK 
				+ year.substring(year.length()-2, year.length()-1) 
				+ year.substring(year.length()-1, year.length());// 2017 -> 17 | 2018 -> 18
			else
				NIK = NIK+ "00";
			/*end set NIK*/
			
			/*Get last sequence of NIK that employee will use 
			 by searching it with the current value of NIK (employeeClass + two digit year)
			 */
			String whereClause = "HC_NIK LIKE '"+NIK+"%'";
			int EmployeeID = new Query(getCtx(), MEmployee.Table_Name, whereClause, get_TrxName())
							 .setOrderBy("HC_NIK DESC")
							 .firstId();
			/*End search last sequence of NIK*/
			
			/*Set the next NIK sequence from the lastest NIK Sequence*/
			if(EmployeeID > 0){
				MEmployee emp = new MEmployee(getCtx(), EmployeeID, get_TrxName());
				String NIK_LastEmp = emp.get_ValueAsString("HC_NIK");
				int sequenceLastNo = Integer.parseInt(NIK_LastEmp.substring(NIK_LastEmp.length()-3, NIK_LastEmp.length()));
				sequenceLastNo++;
				if(sequenceLastNo < 10)
					NIK = NIK + "00" + sequenceLastNo;
				else if(sequenceLastNo < 100 && sequenceLastNo >= 10)
					NIK = NIK + "0"+ sequenceLastNo;
				else if(sequenceLastNo >=100 && sequenceLastNo <= 999)
					NIK = NIK + sequenceLastNo;
			}else{
				NIK = NIK + "001";//NIK is still new or not found then create from index 1
			}
			employee.set_ValueOfColumn("HC_NIK", NIK);
			employee.setValue(employee.get_ValueAsString("HC_NIK"));
			employee.saveEx();
			/*End set employee NIK*/
			
			/*Set C_BPartner's value from employee's value*/
			int C_BPartner_ID = 0;
			if(employee.get_Value("C_BPartner_ID") != null){
				C_BPartner_ID = employee.getC_BPartner_ID();
				MBPartner bpartner = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
				bpartner.setValue(employee.getValue());
				bpartner.saveEx();
			}
			/*End set C_BPartner value*/
			
			employee.saveEx();
		}
		return "";
	}//createNIK
	
}//endClass
