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
import org.taowi.hcm.core.model.I_HC_EmployeeCategory;
import org.taowi.hcm.core.model.I_HC_Job;
import org.taowi.hcm.core.model.I_HC_Org;

/** Generated Model for HC_TravelRequest
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_TravelRequest extends PO implements I_HC_TravelRequest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171021L;

    /** Standard Constructor */
    public X_HC_TravelRequest (Properties ctx, int HC_TravelRequest_ID, String trxName)
    {
      super (ctx, HC_TravelRequest_ID, trxName);
      /** if (HC_TravelRequest_ID == 0)
        {
			setDestination (null);
			setEndDate (new Timestamp( System.currentTimeMillis() ));
			setEndTime (new Timestamp( System.currentTimeMillis() ));
			setHC_Employee_ID (0);
			setHC_EmployeeCategory_ID (0);
			setHC_Job_ID (0);
			setHC_JobLevel_ID (0);
			setHC_Manager_ID (0);
			setHC_Org_ID (0);
			setHC_TravelRequest_ID (0);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
			setStartTime (new Timestamp( System.currentTimeMillis() ));
			setStatus (null);
// 'Drf'
        } */
    }

    /** Load Constructor */
    public X_HC_TravelRequest (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_TravelRequest[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date Column.
		@param DateColumn 
		Fully qualified date column
	  */
	public void setDateColumn (Timestamp DateColumn)
	{
		set_ValueNoCheck (COLUMNNAME_DateColumn, DateColumn);
	}

	/** Get Date Column.
		@return Fully qualified date column
	  */
	public Timestamp getDateColumn () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateColumn);
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

	/** Set Destination.
		@param Destination Destination	  */
	public void setDestination (String Destination)
	{
		set_ValueNoCheck (COLUMNNAME_Destination, Destination);
	}

	/** Get Destination.
		@return Destination	  */
	public String getDestination () 
	{
		return (String)get_Value(COLUMNNAME_Destination);
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
		set_ValueNoCheck (COLUMNNAME_EndTime, EndTime);
	}

	/** Get End Time.
		@return End of the time span
	  */
	public Timestamp getEndTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndTime);
	}

	/** Set Approved PrePayment.
		@param HC_ApprovedPrePayment Approved PrePayment	  */
	public void setHC_ApprovedPrePayment (BigDecimal HC_ApprovedPrePayment)
	{
		set_Value (COLUMNNAME_HC_ApprovedPrePayment, HC_ApprovedPrePayment);
	}

	/** Get Approved PrePayment.
		@return Approved PrePayment	  */
	public BigDecimal getHC_ApprovedPrePayment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_ApprovedPrePayment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Daily Expense.
		@param HC_DailyExpense Daily Expense	  */
	public void setHC_DailyExpense (BigDecimal HC_DailyExpense)
	{
		set_Value (COLUMNNAME_HC_DailyExpense, HC_DailyExpense);
	}

	/** Get Daily Expense.
		@return Daily Expense	  */
	public BigDecimal getHC_DailyExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_DailyExpense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public I_HC_EmployeeCategory getHC_EmployeeCategory() throws RuntimeException
    {
		return (I_HC_EmployeeCategory)MTable.get(getCtx(),I_HC_EmployeeCategory.Table_Name)
			.getPO(getHC_EmployeeCategory_ID(), get_TrxName());	}

	/** Set Employee Category.
		@param HC_EmployeeCategory_ID Employee Category	  */
	public void setHC_EmployeeCategory_ID (int HC_EmployeeCategory_ID)
	{
		if (HC_EmployeeCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeCategory_ID, Integer.valueOf(HC_EmployeeCategory_ID));
	}

	/** Get Employee Category.
		@return Employee Category	  */
	public int getHC_EmployeeCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeCategory_ID);
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

	/** Set Requested PrePayment.
		@param HC_RequestedPrePayment Requested PrePayment	  */
	public void setHC_RequestedPrePayment (BigDecimal HC_RequestedPrePayment)
	{
		set_Value (COLUMNNAME_HC_RequestedPrePayment, HC_RequestedPrePayment);
	}

	/** Get Requested PrePayment.
		@return Requested PrePayment	  */
	public BigDecimal getHC_RequestedPrePayment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_RequestedPrePayment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TravelDays.
		@param HC_TravelDays TravelDays	  */
	public void setHC_TravelDays (int HC_TravelDays)
	{
		set_Value (COLUMNNAME_HC_TravelDays, Integer.valueOf(HC_TravelDays));
	}

	/** Get TravelDays.
		@return TravelDays	  */
	public int getHC_TravelDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_TravelDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_TravelRequest_ID()));
    }

	/** Set HC_TravelRequest_UU.
		@param HC_TravelRequest_UU HC_TravelRequest_UU	  */
	public void setHC_TravelRequest_UU (String HC_TravelRequest_UU)
	{
		set_Value (COLUMNNAME_HC_TravelRequest_UU, HC_TravelRequest_UU);
	}

	/** Get HC_TravelRequest_UU.
		@return HC_TravelRequest_UU	  */
	public String getHC_TravelRequest_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_TravelRequest_UU);
	}

	/** Set Complete.
		@param IsComplete 
		It is complete
	  */
	public void setIsComplete (boolean IsComplete)
	{
		set_Value (COLUMNNAME_IsComplete, Boolean.valueOf(IsComplete));
	}

	/** Get Complete.
		@return It is complete
	  */
	public boolean isComplete () 
	{
		Object oo = get_Value(COLUMNNAME_IsComplete);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsOrganic.
		@param IsOrganic 
		IsOrganic
	  */
	public void setIsOrganic (boolean IsOrganic)
	{
		set_ValueNoCheck (COLUMNNAME_IsOrganic, Boolean.valueOf(IsOrganic));
	}

	/** Get IsOrganic.
		@return IsOrganic
	  */
	public boolean isOrganic () 
	{
		Object oo = get_Value(COLUMNNAME_IsOrganic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nomor SPPD.
		@param NomorSPPD Nomor SPPD	  */
	public void setNomorSPPD (String NomorSPPD)
	{
		set_Value (COLUMNNAME_NomorSPPD, NomorSPPD);
	}

	/** Get Nomor SPPD.
		@return Nomor SPPD	  */
	public String getNomorSPPD () 
	{
		return (String)get_Value(COLUMNNAME_NomorSPPD);
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

	/** Set Sppd1.
		@param Sppd1 
		Sppd1
	  */
	public void setSppd1 (String Sppd1)
	{
		set_Value (COLUMNNAME_Sppd1, Sppd1);
	}

	/** Get Sppd1.
		@return Sppd1
	  */
	public String getSppd1 () 
	{
		return (String)get_Value(COLUMNNAME_Sppd1);
	}

	/** Set Sppd2.
		@param Sppd2 
		Sppd2
	  */
	public void setSppd2 (String Sppd2)
	{
		set_Value (COLUMNNAME_Sppd2, Sppd2);
	}

	/** Get Sppd2.
		@return Sppd2
	  */
	public String getSppd2 () 
	{
		return (String)get_Value(COLUMNNAME_Sppd2);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_ValueNoCheck (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
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
		set_ValueNoCheck (COLUMNNAME_StartTime, StartTime);
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

	/** Set Total Accomodation Expense.
		@param TotalAccomodationExpense Total Accomodation Expense	  */
	public void setTotalAccomodationExpense (BigDecimal TotalAccomodationExpense)
	{
		set_Value (COLUMNNAME_TotalAccomodationExpense, TotalAccomodationExpense);
	}

	/** Get Total Accomodation Expense.
		@return Total Accomodation Expense	  */
	public BigDecimal getTotalAccomodationExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAccomodationExpense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Daily Expense.
		@param TotalDailyExpense Total Daily Expense	  */
	public void setTotalDailyExpense (BigDecimal TotalDailyExpense)
	{
		set_Value (COLUMNNAME_TotalDailyExpense, TotalDailyExpense);
	}

	/** Get Total Daily Expense.
		@return Total Daily Expense	  */
	public BigDecimal getTotalDailyExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalDailyExpense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Expense.
		@param TotalExpense Total Expense	  */
	public void setTotalExpense (BigDecimal TotalExpense)
	{
		set_Value (COLUMNNAME_TotalExpense, TotalExpense);
	}

	/** Get Total Expense.
		@return Total Expense	  */
	public BigDecimal getTotalExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalExpense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Other Point.
		@param TotalOtherPoint Total Other Point	  */
	public void setTotalOtherPoint (BigDecimal TotalOtherPoint)
	{
		set_Value (COLUMNNAME_TotalOtherPoint, TotalOtherPoint);
	}

	/** Get Total Other Point.
		@return Total Other Point	  */
	public BigDecimal getTotalOtherPoint () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalOtherPoint);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Transport Expense.
		@param TotalTransportExpense Total Transport Expense	  */
	public void setTotalTransportExpense (BigDecimal TotalTransportExpense)
	{
		set_Value (COLUMNNAME_TotalTransportExpense, TotalTransportExpense);
	}

	/** Get Total Transport Expense.
		@return Total Transport Expense	  */
	public BigDecimal getTotalTransportExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalTransportExpense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}