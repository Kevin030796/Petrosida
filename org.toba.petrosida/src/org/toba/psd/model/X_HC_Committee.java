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


import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;

/** Generated Model for HC_Committee
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_Committee extends PO implements I_HC_Committee, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170602L;

    /** Standard Constructor */
    public X_HC_Committee (Properties ctx, int HC_Committee_ID, String trxName)
    {
      super (ctx, HC_Committee_ID, trxName);
      /** if (HC_Committee_ID == 0)
        {
			setHC_Committee_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_Committee (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_Committee[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Data Kepanitiaan.
		@param HC_Committee_ID Data Kepanitiaan	  */
	public void setHC_Committee_ID (int HC_Committee_ID)
	{
		if (HC_Committee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Committee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Committee_ID, Integer.valueOf(HC_Committee_ID));
	}

	/** Get Data Kepanitiaan.
		@return Data Kepanitiaan	  */
	public int getHC_Committee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Committee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_Committee_ID()));
    }

	/** Set HC_Committee_UU.
		@param HC_Committee_UU HC_Committee_UU	  */
	public void setHC_Committee_UU (String HC_Committee_UU)
	{
		set_Value (COLUMNNAME_HC_Committee_UU, HC_Committee_UU);
	}

	/** Get HC_Committee_UU.
		@return HC_Committee_UU	  */
	public String getHC_Committee_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Committee_UU);
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

	public I_HC_OrganizationHistory getHC_OrganizationHistory() throws RuntimeException
    {
		return (I_HC_OrganizationHistory)MTable.get(getCtx(), I_HC_OrganizationHistory.Table_Name)
			.getPO(getHC_OrganizationHistory_ID(), get_TrxName());	}

	/** Set Histori Organisasi.
		@param HC_OrganizationHistory_ID Histori Organisasi	  */
	public void setHC_OrganizationHistory_ID (int HC_OrganizationHistory_ID)
	{
		if (HC_OrganizationHistory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_OrganizationHistory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_OrganizationHistory_ID, Integer.valueOf(HC_OrganizationHistory_ID));
	}

	/** Get Histori Organisasi.
		@return Histori Organisasi	  */
	public int getHC_OrganizationHistory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_OrganizationHistory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cancelled.
		@param IsCancelled 
		The transaction was cancelled
	  */
	public void setIsCancelled (boolean IsCancelled)
	{
		set_ValueNoCheck (COLUMNNAME_IsCancelled, Boolean.valueOf(IsCancelled));
	}

	/** Get Cancelled.
		@return The transaction was cancelled
	  */
	public boolean isCancelled () 
	{
		Object oo = get_Value(COLUMNNAME_IsCancelled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Jabatan .
		@param Jabatan Jabatan 	  */
	public void setJabatan (String Jabatan)
	{
		set_Value (COLUMNNAME_Jabatan, Jabatan);
	}

	/** Get Jabatan .
		@return Jabatan 	  */
	public String getJabatan () 
	{
		return (String)get_Value(COLUMNNAME_Jabatan);
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start Date.
		@param StartDate 
		The Start Date indicates the first or starting date
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return The Start Date indicates the first or starting date
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
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