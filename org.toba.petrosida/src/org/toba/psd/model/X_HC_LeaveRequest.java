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
import org.taowi.hcm.core.model.I_HC_Job;

/** Generated Model for HC_LeaveRequest
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_LeaveRequest extends PO implements I_HC_LeaveRequest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171012L;

    /** Standard Constructor */
    public X_HC_LeaveRequest (Properties ctx, int HC_LeaveRequest_ID, String trxName)
    {
      super (ctx, HC_LeaveRequest_ID, trxName);
      /** if (HC_LeaveRequest_ID == 0)
        {
			setHC_LeaveRequest_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_LeaveRequest (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_LeaveRequest[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Approval Date.
		@param ApprovalDate Approval Date	  */
	public void setApprovalDate (Timestamp ApprovalDate)
	{
		set_Value (COLUMNNAME_ApprovalDate, ApprovalDate);
	}

	/** Get Approval Date.
		@return Approval Date	  */
	public Timestamp getApprovalDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ApprovalDate);
	}

	/** Set Approval Description.
		@param ApprovalDescription Approval Description	  */
	public void setApprovalDescription (String ApprovalDescription)
	{
		set_Value (COLUMNNAME_ApprovalDescription, ApprovalDescription);
	}

	/** Get Approval Description.
		@return Approval Description	  */
	public String getApprovalDescription () 
	{
		return (String)get_Value(COLUMNNAME_ApprovalDescription);
	}

	/** Set Approval Time.
		@param ApprovalTime Approval Time	  */
	public void setApprovalTime (Timestamp ApprovalTime)
	{
		set_Value (COLUMNNAME_ApprovalTime, ApprovalTime);
	}

	/** Get Approval Time.
		@return Approval Time	  */
	public Timestamp getApprovalTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ApprovalTime);
	}

	public org.compiere.model.I_C_Calendar getC_Calendar() throws RuntimeException
    {
		return (org.compiere.model.I_C_Calendar)MTable.get(getCtx(), org.compiere.model.I_C_Calendar.Table_Name)
			.getPO(getC_Calendar_ID(), get_TrxName());	}

	/** Set Calendar.
		@param C_Calendar_ID 
		Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID)
	{
		if (C_Calendar_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Calendar_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Calendar_ID, Integer.valueOf(C_Calendar_ID));
	}

	/** Get Calendar.
		@return Accounting Calendar Name
	  */
	public int getC_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Calendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
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

	public I_HC_Job getHC_Job() throws RuntimeException
    {
		return (I_HC_Job)MTable.get(getCtx(), I_HC_Job.Table_Name)
			.getPO(getHC_Job_ID(), get_TrxName());	}

	/** Set Job.
		@param HC_Job_ID Job	  */
	public void setHC_Job_ID (int HC_Job_ID)
	{
		if (HC_Job_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Job_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Job_ID, Integer.valueOf(HC_Job_ID));
	}

	/** Get Job.
		@return Job	  */
	public int getHC_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Leave getHC_Leave() throws RuntimeException
    {
		return (I_HC_Leave)MTable.get(getCtx(), I_HC_Leave.Table_Name)
			.getPO(getHC_Leave_ID(), get_TrxName());	}

	/** Set Employee Leave.
		@param HC_Leave_ID Employee Leave	  */
	public void setHC_Leave_ID (int HC_Leave_ID)
	{
		if (HC_Leave_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Leave_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Leave_ID, Integer.valueOf(HC_Leave_ID));
	}

	/** Get Employee Leave.
		@return Employee Leave	  */
	public int getHC_Leave_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Leave_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_LeaveRequest_ID()));
    }

	/** Set HC_LeaveRequest_UU.
		@param HC_LeaveRequest_UU HC_LeaveRequest_UU	  */
	public void setHC_LeaveRequest_UU (String HC_LeaveRequest_UU)
	{
		set_Value (COLUMNNAME_HC_LeaveRequest_UU, HC_LeaveRequest_UU);
	}

	/** Get HC_LeaveRequest_UU.
		@return HC_LeaveRequest_UU	  */
	public String getHC_LeaveRequest_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_LeaveRequest_UU);
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

	/** Set WorkStartDate.
		@param HC_WorkStartDate WorkStartDate	  */
	public void setHC_WorkStartDate (Timestamp HC_WorkStartDate)
	{
		set_Value (COLUMNNAME_HC_WorkStartDate, HC_WorkStartDate);
	}

	/** Get WorkStartDate.
		@return WorkStartDate	  */
	public Timestamp getHC_WorkStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_WorkStartDate);
	}

	/** Set IsAllowed.
		@param IsAllowed 
		This flag is used for Manager who allow certain request out of regulations.
	  */
	public void setIsAllowed (boolean IsAllowed)
	{
		set_Value (COLUMNNAME_IsAllowed, Boolean.valueOf(IsAllowed));
	}

	/** Get IsAllowed.
		@return This flag is used for Manager who allow certain request out of regulations.
	  */
	public boolean isAllowed () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Leave Balance.
		@param LeaveBalance Leave Balance	  */
	public void setLeaveBalance (int LeaveBalance)
	{
		set_Value (COLUMNNAME_LeaveBalance, Integer.valueOf(LeaveBalance));
	}

	/** Get Leave Balance.
		@return Leave Balance	  */
	public int getLeaveBalance () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeaveBalance);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set LeaveBalanceYear_1.
		@param LeaveBalanceYear_1 
		Saldo Cuti Tahunan untuk tahun pertama
	  */
	public void setLeaveBalanceYear_1 (int LeaveBalanceYear_1)
	{
		set_Value (COLUMNNAME_LeaveBalanceYear_1, Integer.valueOf(LeaveBalanceYear_1));
	}

	/** Get LeaveBalanceYear_1.
		@return Saldo Cuti Tahunan untuk tahun pertama
	  */
	public int getLeaveBalanceYear_1 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeaveBalanceYear_1);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set LeaveBalanceYear_2.
		@param LeaveBalanceYear_2 
		Saldo cuti tahunan untuk tahun ke 2
	  */
	public void setLeaveBalanceYear_2 (int LeaveBalanceYear_2)
	{
		set_Value (COLUMNNAME_LeaveBalanceYear_2, Integer.valueOf(LeaveBalanceYear_2));
	}

	/** Get LeaveBalanceYear_2.
		@return Saldo cuti tahunan untuk tahun ke 2
	  */
	public int getLeaveBalanceYear_2 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeaveBalanceYear_2);
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

	/** Set Leave Description.
		@param LeaveDescription Leave Description	  */
	public void setLeaveDescription (String LeaveDescription)
	{
		set_Value (COLUMNNAME_LeaveDescription, LeaveDescription);
	}

	/** Get Leave Description.
		@return Leave Description	  */
	public String getLeaveDescription () 
	{
		return (String)get_Value(COLUMNNAME_LeaveDescription);
	}

	/** Set LeavePeriodFrom.
		@param LeavePeriodFrom LeavePeriodFrom	  */
	public void setLeavePeriodFrom (String LeavePeriodFrom)
	{
		set_Value (COLUMNNAME_LeavePeriodFrom, LeavePeriodFrom);
	}

	/** Get LeavePeriodFrom.
		@return LeavePeriodFrom	  */
	public String getLeavePeriodFrom () 
	{
		return (String)get_Value(COLUMNNAME_LeavePeriodFrom);
	}

	/** Set LeavePeriodTo.
		@param LeavePeriodTo LeavePeriodTo	  */
	public void setLeavePeriodTo (String LeavePeriodTo)
	{
		set_Value (COLUMNNAME_LeavePeriodTo, LeavePeriodTo);
	}

	/** Get LeavePeriodTo.
		@return LeavePeriodTo	  */
	public String getLeavePeriodTo () 
	{
		return (String)get_Value(COLUMNNAME_LeavePeriodTo);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
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
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Accepted = Acc */
	public static final String STATUS_Accepted = "Acc";
	/** Cancelled = Cnc */
	public static final String STATUS_Cancelled = "Cnc";
	/** Drafted = Drf */
	public static final String STATUS_Drafted = "Drf";
	/** Rejected = Rej */
	public static final String STATUS_Rejected = "Rej";
	/** Requested = Req */
	public static final String STATUS_Requested = "Req";
	/** Confirmed = Cnf */
	public static final String STATUS_Confirmed = "Cnf";
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

	/** Set Transaction Time.
		@param TimeTrx Transaction Time	  */
	public void setTimeTrx (Timestamp TimeTrx)
	{
		set_Value (COLUMNNAME_TimeTrx, TimeTrx);
	}

	/** Get Transaction Time.
		@return Transaction Time	  */
	public Timestamp getTimeTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TimeTrx);
	}
}