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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;

/** Generated Model for HC_SalaryChange
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_SalaryChange extends PO implements I_HC_SalaryChange, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170704L;

    /** Standard Constructor */
    public X_HC_SalaryChange (Properties ctx, int HC_SalaryChange_ID, String trxName)
    {
      super (ctx, HC_SalaryChange_ID, trxName);
      /** if (HC_SalaryChange_ID == 0)
        {
			setHC_SalaryChange_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_SalaryChange (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_SalaryChange[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Compensation 1.
		@param HC_Compensation1 Compensation 1	  */
	public void setHC_Compensation1 (BigDecimal HC_Compensation1)
	{
		set_Value (COLUMNNAME_HC_Compensation1, HC_Compensation1);
	}

	/** Get Compensation 1.
		@return Compensation 1	  */
	public BigDecimal getHC_Compensation1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_Compensation1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_HC_Employee getHC_Employee() throws RuntimeException
    {
		return (I_HC_Employee)MTable.get(getCtx(), I_HC_Employee.Table_Name)
			.getPO(getHC_Employee_ID(), get_TrxName());	}

	/** Set Employee Data.
		@param HC_Employee_ID Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID)
	{
		if (HC_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Employee_ID, Integer.valueOf(HC_Employee_ID));
	}

	/** Get Employee Data.
		@return Employee Data	  */
	public int getHC_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_SalaryChange.
		@param HC_SalaryChange_ID HC_SalaryChange	  */
	public void setHC_SalaryChange_ID (int HC_SalaryChange_ID)
	{
		if (HC_SalaryChange_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_SalaryChange_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_SalaryChange_ID, Integer.valueOf(HC_SalaryChange_ID));
	}

	/** Get HC_SalaryChange.
		@return HC_SalaryChange	  */
	public int getHC_SalaryChange_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_SalaryChange_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_SalaryChange_ID()));
    }

	/** Set HC_SalaryChange_UU.
		@param HC_SalaryChange_UU HC_SalaryChange_UU	  */
	public void setHC_SalaryChange_UU (String HC_SalaryChange_UU)
	{
		set_Value (COLUMNNAME_HC_SalaryChange_UU, HC_SalaryChange_UU);
	}

	/** Get HC_SalaryChange_UU.
		@return HC_SalaryChange_UU	  */
	public String getHC_SalaryChange_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_SalaryChange_UU);
	}

	/** Set Nomor SK.
		@param NomorSK Nomor SK	  */
	public void setNomorSK (String NomorSK)
	{
		set_Value (COLUMNNAME_NomorSK, NomorSK);
	}

	/** Get Nomor SK.
		@return Nomor SK	  */
	public String getNomorSK () 
	{
		return (String)get_Value(COLUMNNAME_NomorSK);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}