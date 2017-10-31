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
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;

/** Generated Model for HC_HistoryTravel
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_HistoryTravel extends PO implements I_HC_HistoryTravel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171018L;

    /** Standard Constructor */
    public X_HC_HistoryTravel (Properties ctx, int HC_HistoryTravel_ID, String trxName)
    {
      super (ctx, HC_HistoryTravel_ID, trxName);
      /** if (HC_HistoryTravel_ID == 0)
        {
			setHC_HistoryTravel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_HistoryTravel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_HistoryTravel[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set History Travel Request.
		@param HC_HistoryTravel_ID History Travel Request	  */
	public void setHC_HistoryTravel_ID (int HC_HistoryTravel_ID)
	{
		if (HC_HistoryTravel_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_HistoryTravel_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_HistoryTravel_ID, Integer.valueOf(HC_HistoryTravel_ID));
	}

	/** Get History Travel Request.
		@return History Travel Request	  */
	public int getHC_HistoryTravel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_HistoryTravel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_HistoryTravel_ID()));
    }

	/** Set HC_HistoryTravel_UU.
		@param HC_HistoryTravel_UU HC_HistoryTravel_UU	  */
	public void setHC_HistoryTravel_UU (String HC_HistoryTravel_UU)
	{
		set_Value (COLUMNNAME_HC_HistoryTravel_UU, HC_HistoryTravel_UU);
	}

	/** Get HC_HistoryTravel_UU.
		@return HC_HistoryTravel_UU	  */
	public String getHC_HistoryTravel_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_HistoryTravel_UU);
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

	public I_HC_Employee getHC_Manager() throws RuntimeException
    {
		return (I_HC_Employee)MTable.get(getCtx(), I_HC_Employee.Table_Name)
			.getPO(getHC_Manager_ID(), get_TrxName());	}

	/** Set Manager Name.
		@param HC_Manager_ID Manager Name	  */
	public void setHC_Manager_ID (int HC_Manager_ID)
	{
		if (HC_Manager_ID < 1) 
			set_Value (COLUMNNAME_HC_Manager_ID, null);
		else 
			set_Value (COLUMNNAME_HC_Manager_ID, Integer.valueOf(HC_Manager_ID));
	}

	/** Get Manager Name.
		@return Manager Name	  */
	public int getHC_Manager_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Manager_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_TravelRequest getHC_TravelRequest() throws RuntimeException
    {
		return (I_HC_TravelRequest)MTable.get(getCtx(), I_HC_TravelRequest.Table_Name)
			.getPO(getHC_TravelRequest_ID(), get_TrxName());	}

	/** Set Travel Request.
		@param HC_TravelRequest_ID Travel Request	  */
	public void setHC_TravelRequest_ID (int HC_TravelRequest_ID)
	{
		if (HC_TravelRequest_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_TravelRequest_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_TravelRequest_ID, Integer.valueOf(HC_TravelRequest_ID));
	}

	/** Get Travel Request.
		@return Travel Request	  */
	public int getHC_TravelRequest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_TravelRequest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Draft = Drf */
	public static final String STATUS_Draft = "Drf";
	/** Approved by Manager = MN */
	public static final String STATUS_ApprovedByManager = "MN";
	/** Approved by SDM = SDM */
	public static final String STATUS_ApprovedBySDM = "SDM";
	/** Approved by Direksi = DI */
	public static final String STATUS_ApprovedByDireksi = "DI";
	/** Cancelled = Cnc */
	public static final String STATUS_Cancelled = "Cnc";
	/** Confirmed = Cnf */
	public static final String STATUS_Confirmed = "Cnf";
	/** Rejected = Rej */
	public static final String STATUS_Rejected = "Rej";
	/** Requested = Req */
	public static final String STATUS_Requested = "Req";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}