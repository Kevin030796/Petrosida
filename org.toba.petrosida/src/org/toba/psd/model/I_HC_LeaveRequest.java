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


import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Job;

/** Generated Interface for HC_LeaveRequest
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
public interface I_HC_LeaveRequest 
{

    /** TableName=HC_LeaveRequest */
    public static final String Table_Name = "HC_LeaveRequest";

    /** AD_Table_ID=1000091 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name ApprovalDate */
    public static final String COLUMNNAME_ApprovalDate = "ApprovalDate";

	/** Set Approval Date	  */
	public void setApprovalDate (Timestamp ApprovalDate);

	/** Get Approval Date	  */
	public Timestamp getApprovalDate();

    /** Column name ApprovalDescription */
    public static final String COLUMNNAME_ApprovalDescription = "ApprovalDescription";

	/** Set Approval Description	  */
	public void setApprovalDescription (String ApprovalDescription);

	/** Get Approval Description	  */
	public String getApprovalDescription();

    /** Column name ApprovalTime */
    public static final String COLUMNNAME_ApprovalTime = "ApprovalTime";

	/** Set Approval Time	  */
	public void setApprovalTime (Timestamp ApprovalTime);

	/** Get Approval Time	  */
	public Timestamp getApprovalTime();

    /** Column name C_Calendar_ID */
    public static final String COLUMNNAME_C_Calendar_ID = "C_Calendar_ID";

	/** Set Calendar.
	  * Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID);

	/** Get Calendar.
	  * Accounting Calendar Name
	  */
	public int getC_Calendar_ID();

	public org.compiere.model.I_C_Calendar getC_Calendar() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

    /** Column name HC_Job_ID */
    public static final String COLUMNNAME_HC_Job_ID = "HC_Job_ID";

	/** Set Job	  */
	public void setHC_Job_ID (int HC_Job_ID);

	/** Get Job	  */
	public int getHC_Job_ID();

	public I_HC_Job getHC_Job() throws RuntimeException;

    /** Column name HC_Leave_ID */
    public static final String COLUMNNAME_HC_Leave_ID = "HC_Leave_ID";

	/** Set Employee Leave	  */
	public void setHC_Leave_ID (int HC_Leave_ID);

	/** Get Employee Leave	  */
	public int getHC_Leave_ID();

	public I_HC_Leave getHC_Leave() throws RuntimeException;

    /** Column name HC_LeaveEndDate */
    public static final String COLUMNNAME_HC_LeaveEndDate = "HC_LeaveEndDate";

	/** Set Leave End Date	  */
	public void setHC_LeaveEndDate (Timestamp HC_LeaveEndDate);

	/** Get Leave End Date	  */
	public Timestamp getHC_LeaveEndDate();

    /** Column name HC_LeaveRequest_ID */
    public static final String COLUMNNAME_HC_LeaveRequest_ID = "HC_LeaveRequest_ID";

	/** Set Leave Request	  */
	public void setHC_LeaveRequest_ID (int HC_LeaveRequest_ID);

	/** Get Leave Request	  */
	public int getHC_LeaveRequest_ID();

    /** Column name HC_LeaveRequest_UU */
    public static final String COLUMNNAME_HC_LeaveRequest_UU = "HC_LeaveRequest_UU";

	/** Set HC_LeaveRequest_UU	  */
	public void setHC_LeaveRequest_UU (String HC_LeaveRequest_UU);

	/** Get HC_LeaveRequest_UU	  */
	public String getHC_LeaveRequest_UU();

    /** Column name HC_LeaveStartDate */
    public static final String COLUMNNAME_HC_LeaveStartDate = "HC_LeaveStartDate";

	/** Set Leave Start Date	  */
	public void setHC_LeaveStartDate (Timestamp HC_LeaveStartDate);

	/** Get Leave Start Date	  */
	public Timestamp getHC_LeaveStartDate();

    /** Column name HC_LeaveType_ID */
    public static final String COLUMNNAME_HC_LeaveType_ID = "HC_LeaveType_ID";

	/** Set Leave Type	  */
	public void setHC_LeaveType_ID (int HC_LeaveType_ID);

	/** Get Leave Type	  */
	public int getHC_LeaveType_ID();

	public I_HC_LeaveType getHC_LeaveType() throws RuntimeException;

    /** Column name HC_Manager_ID */
    public static final String COLUMNNAME_HC_Manager_ID = "HC_Manager_ID";

	/** Set Manager Name	  */
	public void setHC_Manager_ID (int HC_Manager_ID);

	/** Get Manager Name	  */
	public int getHC_Manager_ID();

    /** Column name HC_WorkStartDate */
    public static final String COLUMNNAME_HC_WorkStartDate = "HC_WorkStartDate";

	/** Set WorkStartDate	  */
	public void setHC_WorkStartDate (Timestamp HC_WorkStartDate);

	/** Get WorkStartDate	  */
	public Timestamp getHC_WorkStartDate();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsAllowed */
    public static final String COLUMNNAME_IsAllowed = "IsAllowed";

	/** Set IsAllowed.
	  * This flag is used for Manager who allow certain request out of regulations.
	  */
	public void setIsAllowed (boolean IsAllowed);

	/** Get IsAllowed.
	  * This flag is used for Manager who allow certain request out of regulations.
	  */
	public boolean isAllowed();

    /** Column name LeaveBalance */
    public static final String COLUMNNAME_LeaveBalance = "LeaveBalance";

	/** Set Leave Balance	  */
	public void setLeaveBalance (int LeaveBalance);

	/** Get Leave Balance	  */
	public int getLeaveBalance();

    /** Column name LeaveBalanceYear_1 */
    public static final String COLUMNNAME_LeaveBalanceYear_1 = "LeaveBalanceYear_1";

	/** Set LeaveBalanceYear_1.
	  * Saldo Cuti Tahunan untuk tahun pertama
	  */
	public void setLeaveBalanceYear_1 (int LeaveBalanceYear_1);

	/** Get LeaveBalanceYear_1.
	  * Saldo Cuti Tahunan untuk tahun pertama
	  */
	public int getLeaveBalanceYear_1();

    /** Column name LeaveBalanceYear_2 */
    public static final String COLUMNNAME_LeaveBalanceYear_2 = "LeaveBalanceYear_2";

	/** Set LeaveBalanceYear_2.
	  * Saldo cuti tahunan untuk tahun ke 2
	  */
	public void setLeaveBalanceYear_2 (int LeaveBalanceYear_2);

	/** Get LeaveBalanceYear_2.
	  * Saldo cuti tahunan untuk tahun ke 2
	  */
	public int getLeaveBalanceYear_2();

    /** Column name LeaveDays */
    public static final String COLUMNNAME_LeaveDays = "LeaveDays";

	/** Set Leave Days	  */
	public void setLeaveDays (int LeaveDays);

	/** Get Leave Days	  */
	public int getLeaveDays();

    /** Column name LeaveDescription */
    public static final String COLUMNNAME_LeaveDescription = "LeaveDescription";

	/** Set Leave Description	  */
	public void setLeaveDescription (String LeaveDescription);

	/** Get Leave Description	  */
	public String getLeaveDescription();

    /** Column name LeavePeriodFrom */
    public static final String COLUMNNAME_LeavePeriodFrom = "LeavePeriodFrom";

	/** Set LeavePeriodFrom	  */
	public void setLeavePeriodFrom (String LeavePeriodFrom);

	/** Get LeavePeriodFrom	  */
	public String getLeavePeriodFrom();

    /** Column name LeavePeriodTo */
    public static final String COLUMNNAME_LeavePeriodTo = "LeavePeriodTo";

	/** Set LeavePeriodTo	  */
	public void setLeavePeriodTo (String LeavePeriodTo);

	/** Get LeavePeriodTo	  */
	public String getLeavePeriodTo();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name TimeTrx */
    public static final String COLUMNNAME_TimeTrx = "TimeTrx";

	/** Set Transaction Time	  */
	public void setTimeTrx (Timestamp TimeTrx);

	/** Get Transaction Time	  */
	public Timestamp getTimeTrx();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
