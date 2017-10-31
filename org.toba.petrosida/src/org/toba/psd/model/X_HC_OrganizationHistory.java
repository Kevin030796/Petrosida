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
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_OrganizationHistory
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_OrganizationHistory extends PO implements I_HC_OrganizationHistory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170602L;

    /** Standard Constructor */
    public X_HC_OrganizationHistory (Properties ctx, int HC_OrganizationHistory_ID, String trxName)
    {
      super (ctx, HC_OrganizationHistory_ID, trxName);
      /** if (HC_OrganizationHistory_ID == 0)
        {
			setHC_OrganizationHistory_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_OrganizationHistory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_OrganizationHistory[")
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_OrganizationHistory_ID()));
    }

	/** Set HC_OrganizationHistory_UU.
		@param HC_OrganizationHistory_UU HC_OrganizationHistory_UU	  */
	public void setHC_OrganizationHistory_UU (String HC_OrganizationHistory_UU)
	{
		set_Value (COLUMNNAME_HC_OrganizationHistory_UU, HC_OrganizationHistory_UU);
	}

	/** Get HC_OrganizationHistory_UU.
		@return HC_OrganizationHistory_UU	  */
	public String getHC_OrganizationHistory_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_OrganizationHistory_UU);
	}

	/** Set Nama Organisasi.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Nama Organisasi.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
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