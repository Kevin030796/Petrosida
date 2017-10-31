package org.toba.psd.model;

/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_DailyExpense
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_DailyExpense extends PO implements I_HC_DailyExpense, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171018L;

    /** Standard Constructor */
    public X_HC_DailyExpense (Properties ctx, int HC_DailyExpense_ID, String trxName)
    {
      super (ctx, HC_DailyExpense_ID, trxName);
      /** if (HC_DailyExpense_ID == 0)
        {
			setHC_DailyExpense_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_DailyExpense (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HC_DailyExpense[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Daily Expense.
		@param HC_DailyExpense_ID Daily Expense	  */
	public void setHC_DailyExpense_ID (int HC_DailyExpense_ID)
	{
		if (HC_DailyExpense_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_DailyExpense_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_DailyExpense_ID, Integer.valueOf(HC_DailyExpense_ID));
	}

	/** Get Daily Expense.
		@return Daily Expense	  */
	public int getHC_DailyExpense_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_DailyExpense_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_DailyExpense_ID()));
    }

	/** Set HC_DailyExpense_UU.
		@param HC_DailyExpense_UU HC_DailyExpense_UU	  */
	public void setHC_DailyExpense_UU (String HC_DailyExpense_UU)
	{
		set_Value (COLUMNNAME_HC_DailyExpense_UU, HC_DailyExpense_UU);
	}

	/** Get HC_DailyExpense_UU.
		@return HC_DailyExpense_UU	  */
	public String getHC_DailyExpense_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_DailyExpense_UU);
	}

	public I_HC_JobLevel getHC_JobLevel() throws RuntimeException
    {
		return (I_HC_JobLevel)MTable.get(getCtx(), I_HC_JobLevel.Table_Name)
			.getPO(getHC_JobLevel_ID(), get_TrxName());	}

	/** Set HC Job Level.
		@param HC_JobLevel_ID HC Job Level	  */
	public void setHC_JobLevel_ID (int HC_JobLevel_ID)
	{
		if (HC_JobLevel_ID < 1) 
			set_Value (COLUMNNAME_HC_JobLevel_ID, null);
		else 
			set_Value (COLUMNNAME_HC_JobLevel_ID, Integer.valueOf(HC_JobLevel_ID));
	}

	/** Get HC Job Level.
		@return HC Job Level	  */
	public int getHC_JobLevel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_JobLevel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}