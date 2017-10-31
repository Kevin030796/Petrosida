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
import org.taowi.hcm.core.model.I_HC_Employee;
import org.taowi.hcm.core.model.I_HC_Job;
import org.taowi.hcm.core.model.I_HC_Org;
import org.taowi.hcm.core.model.I_HC_Position;

/** Generated Interface for HC_RequestPermit
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
public interface I_HC_RequestPermit 
{

    /** TableName=HC_RequestPermit */
    public static final String Table_Name = "HC_RequestPermit";

    /** AD_Table_ID=1000168 */
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

	/** Set ApprovalDate	  */
	public void setApprovalDate (Timestamp ApprovalDate);

	/** Get ApprovalDate	  */
	public Timestamp getApprovalDate();

    /** Column name ApprovalDescription */
    public static final String COLUMNNAME_ApprovalDescription = "ApprovalDescription";

	/** Set ApprovalDescription	  */
	public void setApprovalDescription (String ApprovalDescription);

	/** Get ApprovalDescription	  */
	public String getApprovalDescription();

    /** Column name ApprovalTime */
    public static final String COLUMNNAME_ApprovalTime = "ApprovalTime";

	/** Set ApprovalTime	  */
	public void setApprovalTime (Timestamp ApprovalTime);

	/** Get ApprovalTime	  */
	public Timestamp getApprovalTime();

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

    /** Column name EndTime */
    public static final String COLUMNNAME_EndTime = "EndTime";

	/** Set End Time.
	  * End of the time span
	  */
	public void setEndTime (Timestamp EndTime);

	/** Get End Time.
	  * End of the time span
	  */
	public Timestamp getEndTime();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_Job_ID */
    public static final String COLUMNNAME_HC_Job_ID = "HC_Job_ID";

	/** Set Job	  */
	public void setHC_Job_ID (int HC_Job_ID);

	/** Get Job	  */
	public int getHC_Job_ID();

	public I_HC_Job getHC_Job() throws RuntimeException;

    /** Column name HC_Manager_ID */
    public static final String COLUMNNAME_HC_Manager_ID = "HC_Manager_ID";

	/** Set Manager Name	  */
	public void setHC_Manager_ID (int HC_Manager_ID);

	/** Get Manager Name	  */
	public int getHC_Manager_ID();

	public I_HC_Employee getHC_Manager() throws RuntimeException;

    /** Column name HC_Org_ID */
    public static final String COLUMNNAME_HC_Org_ID = "HC_Org_ID";

	/** Set HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID);

	/** Get HC Organization	  */
	public int getHC_Org_ID();

	public I_HC_Org getHC_Org() throws RuntimeException;

    /** Column name HC_Position_ID */
    public static final String COLUMNNAME_HC_Position_ID = "HC_Position_ID";

	/** Set Position	  */
	public void setHC_Position_ID (int HC_Position_ID);

	/** Get Position	  */
	public int getHC_Position_ID();

	public I_HC_Position getHC_Position() throws RuntimeException;

    /** Column name HC_RequestPermit_ID */
    public static final String COLUMNNAME_HC_RequestPermit_ID = "HC_RequestPermit_ID";

	/** Set Request Permit	  */
	public void setHC_RequestPermit_ID (int HC_RequestPermit_ID);

	/** Get Request Permit	  */
	public int getHC_RequestPermit_ID();

    /** Column name HC_RequestPermit_UU */
    public static final String COLUMNNAME_HC_RequestPermit_UU = "HC_RequestPermit_UU";

	/** Set HC_RequestPermit_UU	  */
	public void setHC_RequestPermit_UU (String HC_RequestPermit_UU);

	/** Get HC_RequestPermit_UU	  */
	public String getHC_RequestPermit_UU();

    /** Column name HC_Shift_ID */
    public static final String COLUMNNAME_HC_Shift_ID = "HC_Shift_ID";

	/** Set Shift Employee	  */
	public void setHC_Shift_ID (int HC_Shift_ID);

	/** Get Shift Employee	  */
	public int getHC_Shift_ID();

	public I_HC_Shift getHC_Shift() throws RuntimeException;

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

    /** Column name IsShift */
    public static final String COLUMNNAME_IsShift = "IsShift";

	/** Set IsShift	  */
	public void setIsShift (boolean IsShift);

	/** Get IsShift	  */
	public boolean isShift();

    /** Column name PermitDate */
    public static final String COLUMNNAME_PermitDate = "PermitDate";

	/** Set Permit Date	  */
	public void setPermitDate (Timestamp PermitDate);

	/** Get Permit Date	  */
	public Timestamp getPermitDate();

    /** Column name PermitType */
    public static final String COLUMNNAME_PermitType = "PermitType";

	/** Set Permit Type	  */
	public void setPermitType (String PermitType);

	/** Get Permit Type	  */
	public String getPermitType();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name RejectReason */
    public static final String COLUMNNAME_RejectReason = "RejectReason";

	/** Set Reject Reason.
	  * Alasan Mengapa permintaan request di reject
	  */
	public void setRejectReason (String RejectReason);

	/** Get Reject Reason.
	  * Alasan Mengapa permintaan request di reject
	  */
	public String getRejectReason();

    /** Column name ShiftEnds */
    public static final String COLUMNNAME_ShiftEnds = "ShiftEnds";

	/** Set Shift Ends	  */
	public void setShiftEnds (Timestamp ShiftEnds);

	/** Get Shift Ends	  */
	public Timestamp getShiftEnds();

    /** Column name ShiftStart */
    public static final String COLUMNNAME_ShiftStart = "ShiftStart";

	/** Set Shift Start	  */
	public void setShiftStart (Timestamp ShiftStart);

	/** Get Shift Start	  */
	public Timestamp getShiftStart();

    /** Column name StartTime */
    public static final String COLUMNNAME_StartTime = "StartTime";

	/** Set Start Time.
	  * Time started
	  */
	public void setStartTime (Timestamp StartTime);

	/** Get Start Time.
	  * Time started
	  */
	public Timestamp getStartTime();

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

	/** Set TimeTrx	  */
	public void setTimeTrx (Timestamp TimeTrx);

	/** Get TimeTrx	  */
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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name WorktimeDate */
    public static final String COLUMNNAME_WorktimeDate = "WorktimeDate";

	/** Set WorktimeDate	  */
	public void setWorktimeDate (Timestamp WorktimeDate);

	/** Get WorktimeDate	  */
	public Timestamp getWorktimeDate();
}
