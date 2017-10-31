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
import org.taowi.hcm.core.model.I_HC_Job;
import org.taowi.hcm.core.model.I_HC_Org;
import org.taowi.hcm.core.model.I_HC_Position;

/** Generated Model for HC_RequestOvertime
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_RequestOvertime extends PO implements I_HC_RequestOvertime, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170810L;

    /** Standard Constructor */
    public X_HC_RequestOvertime (Properties ctx, int HC_RequestOvertime_ID, String trxName)
    {
      super (ctx, HC_RequestOvertime_ID, trxName);
      /** if (HC_RequestOvertime_ID == 0)
        {
			setHC_Employee_ID (0);
			setHC_RequestOvertime_ID (0);
			setHC_Shift_ID (0);
			setOvertimeDate (new Timestamp( System.currentTimeMillis() ));
			setShiftEnds (new Timestamp( System.currentTimeMillis() ));
			setShiftStart (new Timestamp( System.currentTimeMillis() ));
			setTotalOvertimeHour (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HC_RequestOvertime (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_RequestOvertime[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ApprovalDate.
		@param ApprovalDate ApprovalDate	  */
	public void setApprovalDate (Timestamp ApprovalDate)
	{
		set_Value (COLUMNNAME_ApprovalDate, ApprovalDate);
	}

	/** Get ApprovalDate.
		@return ApprovalDate	  */
	public Timestamp getApprovalDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ApprovalDate);
	}

	/** Set ApprovalDescription.
		@param ApprovalDescription ApprovalDescription	  */
	public void setApprovalDescription (String ApprovalDescription)
	{
		set_Value (COLUMNNAME_ApprovalDescription, ApprovalDescription);
	}

	/** Get ApprovalDescription.
		@return ApprovalDescription	  */
	public String getApprovalDescription () 
	{
		return (String)get_Value(COLUMNNAME_ApprovalDescription);
	}

	/** Set ApprovalTime.
		@param ApprovalTime ApprovalTime	  */
	public void setApprovalTime (Timestamp ApprovalTime)
	{
		set_Value (COLUMNNAME_ApprovalTime, ApprovalTime);
	}

	/** Get ApprovalTime.
		@return ApprovalTime	  */
	public Timestamp getApprovalTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ApprovalTime);
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

	/** Set End Time.
		@param EndTime 
		End of the time span
	  */
	public void setEndTime (Timestamp EndTime)
	{
		set_Value (COLUMNNAME_EndTime, EndTime);
	}

	/** Get End Time.
		@return End of the time span
	  */
	public Timestamp getEndTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndTime);
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

	public I_HC_Org getHC_Org() throws RuntimeException
    {
		return (I_HC_Org)MTable.get(getCtx(), I_HC_Org.Table_Name)
			.getPO(getHC_Org_ID(), get_TrxName());	}

	/** Set HC Organization.
		@param HC_Org_ID HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID)
	{
		if (HC_Org_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Org_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Org_ID, Integer.valueOf(HC_Org_ID));
	}

	/** Get HC Organization.
		@return HC Organization	  */
	public int getHC_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Position getHC_Position() throws RuntimeException
    {
		return (I_HC_Position)MTable.get(getCtx(), I_HC_Position.Table_Name)
			.getPO(getHC_Position_ID(), get_TrxName());	}

	/** Set Position.
		@param HC_Position_ID Position	  */
	public void setHC_Position_ID (int HC_Position_ID)
	{
		if (HC_Position_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Position_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Position_ID, Integer.valueOf(HC_Position_ID));
	}

	/** Get Position.
		@return Position	  */
	public int getHC_Position_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Position_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Request Overtime.
		@param HC_RequestOvertime_ID Request Overtime	  */
	public void setHC_RequestOvertime_ID (int HC_RequestOvertime_ID)
	{
		if (HC_RequestOvertime_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_RequestOvertime_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_RequestOvertime_ID, Integer.valueOf(HC_RequestOvertime_ID));
	}

	/** Get Request Overtime.
		@return Request Overtime	  */
	public int getHC_RequestOvertime_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_RequestOvertime_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_RequestOvertime_ID()));
    }

	/** Set HC_RequestOvertime_UU.
		@param HC_RequestOvertime_UU HC_RequestOvertime_UU	  */
	public void setHC_RequestOvertime_UU (String HC_RequestOvertime_UU)
	{
		set_Value (COLUMNNAME_HC_RequestOvertime_UU, HC_RequestOvertime_UU);
	}

	/** Get HC_RequestOvertime_UU.
		@return HC_RequestOvertime_UU	  */
	public String getHC_RequestOvertime_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_RequestOvertime_UU);
	}

	public I_HC_Shift getHC_Shift() throws RuntimeException
    {
		return (I_HC_Shift)MTable.get(getCtx(), I_HC_Shift.Table_Name)
			.getPO(getHC_Shift_ID(), get_TrxName());	}

	/** Set Shift Employee.
		@param HC_Shift_ID Shift Employee	  */
	public void setHC_Shift_ID (int HC_Shift_ID)
	{
		if (HC_Shift_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Shift_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Shift_ID, Integer.valueOf(HC_Shift_ID));
	}

	/** Get Shift Employee.
		@return Shift Employee	  */
	public int getHC_Shift_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Shift_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IsShift.
		@param IsShift IsShift	  */
	public void setIsShift (boolean IsShift)
	{
		set_Value (COLUMNNAME_IsShift, Boolean.valueOf(IsShift));
	}

	/** Get IsShift.
		@return IsShift	  */
	public boolean isShift () 
	{
		Object oo = get_Value(COLUMNNAME_IsShift);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Overtime Date.
		@param OvertimeDate Overtime Date	  */
	public void setOvertimeDate (Timestamp OvertimeDate)
	{
		set_Value (COLUMNNAME_OvertimeDate, OvertimeDate);
	}

	/** Get Overtime Date.
		@return Overtime Date	  */
	public Timestamp getOvertimeDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_OvertimeDate);
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

	/** Set Reject Reason.
		@param RejectReason 
		Alasan Mengapa permintaan request di reject
	  */
	public void setRejectReason (String RejectReason)
	{
		set_Value (COLUMNNAME_RejectReason, RejectReason);
	}

	/** Get Reject Reason.
		@return Alasan Mengapa permintaan request di reject
	  */
	public String getRejectReason () 
	{
		return (String)get_Value(COLUMNNAME_RejectReason);
	}

	/** Set Shift End.
		@param ShiftEnds Shift End	  */
	public void setShiftEnds (Timestamp ShiftEnds)
	{
		set_Value (COLUMNNAME_ShiftEnds, ShiftEnds);
	}

	/** Get Shift End.
		@return Shift End	  */
	public Timestamp getShiftEnds () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShiftEnds);
	}

	/** Set Shift Start.
		@param ShiftStart Shift Start	  */
	public void setShiftStart (Timestamp ShiftStart)
	{
		set_Value (COLUMNNAME_ShiftStart, ShiftStart);
	}

	/** Get Shift Start.
		@return Shift Start	  */
	public Timestamp getShiftStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShiftStart);
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

	/** Set Start Time.
		@param StartTime 
		Time started
	  */
	public void setStartTime (Timestamp StartTime)
	{
		set_Value (COLUMNNAME_StartTime, StartTime);
	}

	/** Get Start Time.
		@return Time started
	  */
	public Timestamp getStartTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartTime);
	}

	/** Drafted = Drf */
	public static final String STATUS_Drafted = "Drf";
	/** Requested = Req */
	public static final String STATUS_Requested = "Req";
	/** Accepted = Acc */
	public static final String STATUS_Accepted = "Acc";
	/** Rejected = Rej */
	public static final String STATUS_Rejected = "Rej";
	/** Cancelled = Cnc */
	public static final String STATUS_Cancelled = "Cnc";
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

	/** Set TimeTrx.
		@param TimeTrx TimeTrx	  */
	public void setTimeTrx (Timestamp TimeTrx)
	{
		set_Value (COLUMNNAME_TimeTrx, TimeTrx);
	}

	/** Get TimeTrx.
		@return TimeTrx	  */
	public Timestamp getTimeTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TimeTrx);
	}

	/** Set Total Overtime Hour.
		@param TotalOvertimeHour 
		Berapa jam overtime yang diajukan
	  */
	public void setTotalOvertimeHour (BigDecimal TotalOvertimeHour)
	{
		set_Value (COLUMNNAME_TotalOvertimeHour, TotalOvertimeHour);
	}

	/** Get Total Overtime Hour.
		@return Berapa jam overtime yang diajukan
	  */
	public BigDecimal getTotalOvertimeHour () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalOvertimeHour);
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