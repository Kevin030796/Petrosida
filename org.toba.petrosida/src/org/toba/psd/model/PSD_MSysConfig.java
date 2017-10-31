package org.toba.psd.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MSysConfig;

public class PSD_MSysConfig extends MSysConfig{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3834667309387841692L;

	public static final String PAY_COMPONENT_BASE_SALARY = "PAY_COMPONENT_BASE_SALARY";
	
	public PSD_MSysConfig(Properties ctx, int AD_SysConfig_ID, String trxName) {
		super(ctx, AD_SysConfig_ID, trxName);
	}
	
	public PSD_MSysConfig(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
