package org.toba.psd.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.taowi.hcm.core.model.MEmployee;


/**
 * @author KevinY
 *
 */
public class MSanctions extends X_HC_Sanctions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Make Object HC_Sanctions by Result Set
	 * @param ctx
	 * @param rs
	 * @param trxName
	 * 
	 */
	public MSanctions(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}//MSanctions
	
	/**
	 * Make Object HC_Sanctions by Id Record  
	 * @param ctx
	 * @param HC_Sanctions_ID
	 * @param trxName
	 */
	public MSanctions(Properties ctx, int HC_Sanctions_ID, String trxName) {
		super(ctx, HC_Sanctions_ID, trxName);
	}//MSanctions
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		if(newRecord || is_ValueChanged("HC_Employee_ID") || is_ValueChanged("IsSanctionActive")){
			int HC_Employee_ID = getHC_Employee_ID();
			MEmployee employee = new MEmployee(getCtx(), HC_Employee_ID, get_TrxName());
			if(get_ValueAsBoolean("IsSanctionActive"))
				employee.set_ValueOfColumn("isSanctions", true);
			employee.saveEx();
		}
		return true;
	}//beforeSave
}//endClass
