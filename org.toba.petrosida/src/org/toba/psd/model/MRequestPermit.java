package org.toba.psd.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.taowi.hcm.core.model.MEmployeeJob;
import org.taowi.hcm.core.model.MJob;
import org.taowi.hcm.core.model.X_HC_EmployeeClass;
import org.taowi.hcm.payroll.model.MHCAttribute;
import org.taowi.hcm.payroll.model.MHCAttributeMap;
import org.taowi.hcm.payroll.model.MHCPayComponent;

public class MRequestPermit extends X_HC_RequestPermit{
	
	public MRequestPermit(Properties ctx, int HC_RequestPermit_ID,
			String trxName) {
		super(ctx, HC_RequestPermit_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param windowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * return 
	 * potongan upah
	 */
	public double CalculatePayDeduction(){
		String Angkut = "TNJ_ANGKT_MAP";
		String Upah ="BASE_SALARY";
		
		int employee_ID = (Integer)get_Value(X_HC_RequestPermit.COLUMNNAME_HC_Employee_ID);
		int job_ID = (Integer)get_Value(X_HC_RequestPermit.COLUMNNAME_HC_Job_ID);
		
		int HC_EmployeeJob_ID = new Query(getCtx(), MEmployeeJob.Table_Name, MEmployeeJob.COLUMNNAME_HC_Employee_ID+"=? "
										+ "AND "+ MEmployeeJob.COLUMNNAME_HC_Job_ID+"=? "
										+ "AND "+MEmployeeJob.COLUMNNAME_HC_Status+"='"+MEmployeeJob.HC_STATUS_Active+"'", get_TrxName())
								.setParameters(new Object[]{employee_ID,job_ID})
								.firstId();
		
		double potonganUpah = 0.0;
		if(HC_EmployeeJob_ID > 0) {
			MEmployeeJob empJob 		= new MEmployeeJob(getCtx(), HC_EmployeeJob_ID,get_TrxName());
			MJob job 					= new MJob(getCtx(), job_ID, get_TrxName());
			X_HC_EmployeeClass empClass = new X_HC_EmployeeClass(getCtx(), empJob.getHC_EmployeeClass_ID(),get_TrxName());
			
			X_HC_JobLevel jobLevel = new X_HC_JobLevel(getCtx(), job.getHC_JobLevel_ID(), get_TrxName());
			String jobLevelValue = jobLevel.getValue();
			
			double totalPermitHour = ((BigDecimal)get_Value("TotalPermitHour")).doubleValue();
			if(!get_ValueAsBoolean("IsDinas")) {
				if(empClass.getDescription().equals("TP") || empClass.getDescription().equals("BP")){
					//Karyawan Permanent dan BP
					if(totalPermitHour > 5)
						throw new AdempiereException("Permit hour maximum is 5 hours");
					
					int HC_Attribute_ID = new Query(getCtx(), MHCAttribute.Table_Name,
													"mhp."+MHCPayComponent.COLUMNNAME_Value+"=? AND "+ MHCAttribute.Table_Name+"."+MHCAttribute.COLUMNNAME_IsActive+"='Y'", get_TrxName())
											.addJoinClause(" LEFT JOIN "+MHCPayComponent.Table_Name+" mhp "
													+ "ON "+MHCAttribute.Table_Name+"."+MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=mhp."+MHCAttribute.COLUMNNAME_HC_PayComponent_ID)
											.setParameters(new Object[]{Angkut})
											.firstId();
					
					MHCAttribute attribute = new MHCAttribute(getCtx(), HC_Attribute_ID, get_TrxName());
					jobLevelValue = jobLevelValue.toUpperCase();
					
					String whereClause = MHCAttributeMap.COLUMNNAME_HC_Attribute_ID +"=? AND "+MHCAttributeMap.COLUMNNAME_HC_AtrributeMapKey+"=? AND "+MHCAttributeMap.COLUMNNAME_IsActive+"='Y'";
					int HC_AttributeMap_ID = new Query(getCtx(), MHCAttributeMap.Table_Name,whereClause,get_TrxName())
												.setParameters(new Object[]{attribute.get_ID(),jobLevelValue})						
												.firstId();
					
					if(HC_AttributeMap_ID <= 0) {
						throw new AdempiereException("Error: Not found Attribute Map Key : "+ jobLevelValue);
					}
					MHCAttributeMap map = new MHCAttributeMap(getCtx(), HC_AttributeMap_ID, get_TrxName());
					
					if(get_Value(X_HC_RequestPermit.COLUMNNAME_PermitType).equals("ITMK")) {
						potonganUpah = (0.1)* map.getHC_ValueNumeric().doubleValue();
					}else if(get_Value(X_HC_RequestPermit.COLUMNNAME_PermitType).equals("ITA")) {
						potonganUpah = (0.04)* map.getHC_ValueNumeric().doubleValue();
					}else{ 
						if(1.0 <= totalPermitHour && totalPermitHour <= 2.0){
							//1%xAngkut
							potonganUpah = (0.01)* map.getHC_ValueNumeric().doubleValue();
						}else if(2.0 < totalPermitHour && totalPermitHour <= 3.0){
							//2%xAngkut
							potonganUpah = (0.02)* map.getHC_ValueNumeric().doubleValue();
						}else if(3.0 < totalPermitHour && totalPermitHour <= 4.0){
							//3%xAngkut
							potonganUpah = (0.03)* map.getHC_ValueNumeric().doubleValue();
						}else if(4.0 < totalPermitHour && totalPermitHour <= 5.0){
							//4%xAngkut
							potonganUpah = (0.04)* map.getHC_ValueNumeric().doubleValue();
						}
					}

				}else{
					
					int HC_Attribute_ID = new Query(getCtx(), MHCAttribute.Table_Name, "mhp."+MHCPayComponent.COLUMNNAME_Value+"=? "
												+ "AND "+MHCAttribute.Table_Name +"."+ MHCAttribute.COLUMNNAME_IsActive+"='Y' "
												+ "AND "+MHCAttribute.Table_Name +"."+ MHCAttribute.COLUMNNAME_HC_Employee_ID+"=?", get_TrxName())
										.addJoinClause(" LEFT JOIN "+MHCPayComponent.Table_Name+" mhp "
												+ "ON "+MHCAttribute.Table_Name+"."+MHCAttribute.COLUMNNAME_HC_PayComponent_ID+"=mhp."+MHCAttribute.COLUMNNAME_HC_PayComponent_ID)
										.setParameters(new Object[]{Upah,employee_ID})
										.setOrderBy(MHCAttribute.Table_Name +"."+MHCAttribute.COLUMNNAME_ValidFrom +" DESC")
										.firstId();
					
					MHCAttribute attribute = new MHCAttribute(getCtx(), HC_Attribute_ID, get_TrxName());
					//validasi max jam permit
					if(totalPermitHour > 8)
						throw new AdempiereException("Permit hour maximum is 8 hours");
					if(totalPermitHour <= 1.0){
						//1%Upah
						potonganUpah = (0.01)* attribute.getHC_NumValue().doubleValue();
					}else if(1.0 < totalPermitHour && totalPermitHour <= 3.0){
						//2%Upah
						potonganUpah = (0.02)* attribute.getHC_NumValue().doubleValue();
					}else if(3.0 < totalPermitHour && totalPermitHour <= 5.0){
						//3%Upah
						potonganUpah = (0.03)* attribute.getHC_NumValue().doubleValue();
					}else if(5.0 < totalPermitHour && totalPermitHour <= 8.0){
						//4%Upah
						potonganUpah = (0.04)* attribute.getHC_NumValue().doubleValue();
					}	
				}//endIf Outsource
			}//endIf IsDinas
		}//endInf EmployeeJob > 0
		return potonganUpah;
	}//CalculatePayDeduction
}
