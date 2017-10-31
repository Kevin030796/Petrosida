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

/** Generated Model for HC_LeaveDebt
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_LeaveDebt extends PO implements I_HC_LeaveDebt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171025L;

    /** Standard Constructor */
    public X_HC_LeaveDebt (Properties ctx, int HC_LeaveDebt_ID, String trxName)
    {
      super (ctx, HC_LeaveDebt_ID, trxName);
      /** if (HC_LeaveDebt_ID == 0)
        {
			setHC_LeaveDebt_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_LeaveDebt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_LeaveDebt[")
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

	/** Set Hutang Cuti.
		@param HC_LeaveDebt_ID Hutang Cuti	  */
	public void setHC_LeaveDebt_ID (int HC_LeaveDebt_ID)
	{
		if (HC_LeaveDebt_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_LeaveDebt_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_LeaveDebt_ID, Integer.valueOf(HC_LeaveDebt_ID));
	}

	/** Get Hutang Cuti.
		@return Hutang Cuti	  */
	public int getHC_LeaveDebt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_LeaveDebt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_LeaveDebt_ID()));
    }

	/** Set HC_LeaveDebt_UU.
		@param HC_LeaveDebt_UU HC_LeaveDebt_UU	  */
	public void setHC_LeaveDebt_UU (String HC_LeaveDebt_UU)
	{
		set_Value (COLUMNNAME_HC_LeaveDebt_UU, HC_LeaveDebt_UU);
	}

	/** Get HC_LeaveDebt_UU.
		@return HC_LeaveDebt_UU	  */
	public String getHC_LeaveDebt_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_LeaveDebt_UU);
	}

	/** Set Leave End Date.
		@param HC_LeaveEndDate Leave End Date	  */
	public void setHC_LeaveEndDate (Timestamp HC_LeaveEndDate)
	{
		set_Value (COLUMNNAME_HC_LeaveEndDate, HC_LeaveEndDate);
	}

	/** Get Leave End Date.
		@return Leave End Date	  */
	public Timestamp getHC_LeaveEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_LeaveEndDate);
	}

	public I_HC_LeaveRequest getHC_LeaveRequest() throws RuntimeException
    {
		return (I_HC_LeaveRequest)MTable.get(getCtx(), I_HC_LeaveRequest.Table_Name)
			.getPO(getHC_LeaveRequest_ID(), get_TrxName());	}

	/** Set Leave Request.
		@param HC_LeaveRequest_ID Leave Request	  */
	public void setHC_LeaveRequest_ID (int HC_LeaveRequest_ID)
	{
		if (HC_LeaveRequest_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_LeaveRequest_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_LeaveRequest_ID, Integer.valueOf(HC_LeaveRequest_ID));
	}

	/** Get Leave Request.
		@return Leave Request	  */
	public int getHC_LeaveRequest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_LeaveRequest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leave Start Date.
		@param HC_LeaveStartDate Leave Start Date	  */
	public void setHC_LeaveStartDate (Timestamp HC_LeaveStartDate)
	{
		set_Value (COLUMNNAME_HC_LeaveStartDate, HC_LeaveStartDate);
	}

	/** Get Leave Start Date.
		@return Leave Start Date	  */
	public Timestamp getHC_LeaveStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_LeaveStartDate);
	}

	public I_HC_LeaveType getHC_LeaveType() throws RuntimeException
    {
		return (I_HC_LeaveType)MTable.get(getCtx(), I_HC_LeaveType.Table_Name)
			.getPO(getHC_LeaveType_ID(), get_TrxName());	}

	/** Set Leave Type.
		@param HC_LeaveType_ID Leave Type	  */
	public void setHC_LeaveType_ID (int HC_LeaveType_ID)
	{
		if (HC_LeaveType_ID < 1) 
			set_Value (COLUMNNAME_HC_LeaveType_ID, null);
		else 
			set_Value (COLUMNNAME_HC_LeaveType_ID, Integer.valueOf(HC_LeaveType_ID));
	}

	/** Get Leave Type.
		@return Leave Type	  */
	public int getHC_LeaveType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_LeaveType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leave Days.
		@param LeaveDays Leave Days	  */
	public void setLeaveDays (int LeaveDays)
	{
		set_Value (COLUMNNAME_LeaveDays, Integer.valueOf(LeaveDays));
	}

	/** Get Leave Days.
		@return Leave Days	  */
	public int getLeaveDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeaveDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set NonBusinessDay.
		@param NonBusinessDay 
		Used for non business day request cuti 
	  */
	public void setNonBusinessDay (int NonBusinessDay)
	{
		set_Value (COLUMNNAME_NonBusinessDay, Integer.valueOf(NonBusinessDay));
	}

	/** Get NonBusinessDay.
		@return Used for non business day request cuti 
	  */
	public int getNonBusinessDay () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NonBusinessDay);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}