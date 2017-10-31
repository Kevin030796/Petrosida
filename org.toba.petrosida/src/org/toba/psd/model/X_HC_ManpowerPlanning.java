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
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_ManpowerPlanning
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_ManpowerPlanning extends PO implements I_HC_ManpowerPlanning, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170605L;

    /** Standard Constructor */
    public X_HC_ManpowerPlanning (Properties ctx, int HC_ManpowerPlanning_ID, String trxName)
    {
      super (ctx, HC_ManpowerPlanning_ID, trxName);
      /** if (HC_ManpowerPlanning_ID == 0)
        {
			setHC_ManpowerPlanning_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_ManpowerPlanning (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_ManpowerPlanning[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Manpower Calculation.
		@param HC_ManpowerPlanning_ID Manpower Calculation	  */
	public void setHC_ManpowerPlanning_ID (int HC_ManpowerPlanning_ID)
	{
		if (HC_ManpowerPlanning_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_ManpowerPlanning_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_ManpowerPlanning_ID, Integer.valueOf(HC_ManpowerPlanning_ID));
	}

	/** Get Manpower Calculation.
		@return Manpower Calculation	  */
	public int getHC_ManpowerPlanning_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_ManpowerPlanning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_ManpowerPlanning_ID()));
    }

	/** Set HC_ManpowerPlanning_UU.
		@param HC_ManpowerPlanning_UU HC_ManpowerPlanning_UU	  */
	public void setHC_ManpowerPlanning_UU (String HC_ManpowerPlanning_UU)
	{
		set_Value (COLUMNNAME_HC_ManpowerPlanning_UU, HC_ManpowerPlanning_UU);
	}

	/** Get HC_ManpowerPlanning_UU.
		@return HC_ManpowerPlanning_UU	  */
	public String getHC_ManpowerPlanning_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_ManpowerPlanning_UU);
	}

	/** Set Processing 2.
		@param Processing2 Processing 2	  */
	public void setProcessing2 (String Processing2)
	{
		set_Value (COLUMNNAME_Processing2, Processing2);
	}

	/** Get Processing 2.
		@return Processing 2	  */
	public String getProcessing2 () 
	{
		return (String)get_Value(COLUMNNAME_Processing2);
	}

	/** Set Total Employee.
		@param TotalEmployee Total Employee	  */
	public void setTotalEmployee (BigDecimal TotalEmployee)
	{
		set_Value (COLUMNNAME_TotalEmployee, TotalEmployee);
	}

	/** Get Total Employee.
		@return Total Employee	  */
	public BigDecimal getTotalEmployee () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalEmployee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Idea Karyawan.
		@param TotalIdeaKaryawan Total Idea Karyawan	  */
	public void setTotalIdealKaryawan (BigDecimal TotalIdealKaryawan)
	{
		set_Value (COLUMNNAME_TotalIdealKaryawan, TotalIdealKaryawan);
	}

	/** Get Total Idea Karyawan.
		@return Total Idea Karyawan	  */
	public BigDecimal getTotalIdealKaryawan () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalIdealKaryawan);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Pension Employee.
		@param TotalPensionEmployee Total Pension Employee	  */
	public void setTotalPensionEmployee (BigDecimal TotalPensionEmployee)
	{
		set_Value (COLUMNNAME_TotalPensionEmployee, TotalPensionEmployee);
	}

	/** Get Total Pension Employee.
		@return Total Pension Employee	  */
	public BigDecimal getTotalPensionEmployee () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalPensionEmployee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TotalRequirement.
		@param TotalRequirement TotalRequirement	  */
	public void setTotalRequirement (BigDecimal TotalRequirement)
	{
		set_Value (COLUMNNAME_TotalRequirement, TotalRequirement);
	}

	/** Get TotalRequirement.
		@return TotalRequirement	  */
	public BigDecimal getTotalRequirement () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalRequirement);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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