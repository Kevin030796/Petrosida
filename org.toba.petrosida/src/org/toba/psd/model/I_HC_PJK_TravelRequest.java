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
import org.taowi.hcm.core.model.I_HC_EmployeeCategory;
import org.taowi.hcm.core.model.I_HC_Job;
import org.taowi.hcm.core.model.I_HC_Org;

/** Generated Interface for HC_PJK_TravelRequest
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
public interface I_HC_PJK_TravelRequest 
{

    /** TableName=HC_PJK_TravelRequest */
    public static final String Table_Name = "HC_PJK_TravelRequest";

    /** AD_Table_ID=1000112 */
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

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

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

    /** Column name Destination */
    public static final String COLUMNNAME_Destination = "Destination";

	/** Set Destination	  */
	public void setDestination (String Destination);

	/** Get Destination	  */
	public String getDestination();

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

    /** Column name HC_ApprovedPrePayment */
    public static final String COLUMNNAME_HC_ApprovedPrePayment = "HC_ApprovedPrePayment";

	/** Set Approved PrePayment	  */
	public void setHC_ApprovedPrePayment (BigDecimal HC_ApprovedPrePayment);

	/** Get Approved PrePayment	  */
	public BigDecimal getHC_ApprovedPrePayment();

    /** Column name HC_DailyExpense */
    public static final String COLUMNNAME_HC_DailyExpense = "HC_DailyExpense";

	/** Set Daily Expense	  */
	public void setHC_DailyExpense (BigDecimal HC_DailyExpense);

	/** Get Daily Expense	  */
	public BigDecimal getHC_DailyExpense();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_EmployeeCategory_ID */
    public static final String COLUMNNAME_HC_EmployeeCategory_ID = "HC_EmployeeCategory_ID";

	/** Set Employee Category	  */
	public void setHC_EmployeeCategory_ID (int HC_EmployeeCategory_ID);

	/** Get Employee Category	  */
	public int getHC_EmployeeCategory_ID();

	public I_HC_EmployeeCategory getHC_EmployeeCategory() throws RuntimeException;

    /** Column name HC_Job_ID */
    public static final String COLUMNNAME_HC_Job_ID = "HC_Job_ID";

	/** Set Job	  */
	public void setHC_Job_ID (int HC_Job_ID);

	/** Get Job	  */
	public int getHC_Job_ID();

	public I_HC_Job getHC_Job() throws RuntimeException;

    /** Column name HC_JobLevel_ID */
    public static final String COLUMNNAME_HC_JobLevel_ID = "HC_JobLevel_ID";

	/** Set HC Job Level	  */
	public void setHC_JobLevel_ID (int HC_JobLevel_ID);

	/** Get HC Job Level	  */
	public int getHC_JobLevel_ID();

	public I_HC_JobLevel getHC_JobLevel() throws RuntimeException;

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

    /** Column name HC_PJK_TravelRequest_ID */
    public static final String COLUMNNAME_HC_PJK_TravelRequest_ID = "HC_PJK_TravelRequest_ID";

	/** Set PJK_TravelRequest	  */
	public void setHC_PJK_TravelRequest_ID (int HC_PJK_TravelRequest_ID);

	/** Get PJK_TravelRequest	  */
	public int getHC_PJK_TravelRequest_ID();

    /** Column name HC_PJK_TravelRequest_UU */
    public static final String COLUMNNAME_HC_PJK_TravelRequest_UU = "HC_PJK_TravelRequest_UU";

	/** Set HC_PJK_TravelRequest_UU	  */
	public void setHC_PJK_TravelRequest_UU (String HC_PJK_TravelRequest_UU);

	/** Get HC_PJK_TravelRequest_UU	  */
	public String getHC_PJK_TravelRequest_UU();

    /** Column name HC_RequestedPrePayment */
    public static final String COLUMNNAME_HC_RequestedPrePayment = "HC_RequestedPrePayment";

	/** Set Requested PrePayment	  */
	public void setHC_RequestedPrePayment (BigDecimal HC_RequestedPrePayment);

	/** Get Requested PrePayment	  */
	public BigDecimal getHC_RequestedPrePayment();

    /** Column name HC_TravelDays */
    public static final String COLUMNNAME_HC_TravelDays = "HC_TravelDays";

	/** Set TravelDays	  */
	public void setHC_TravelDays (int HC_TravelDays);

	/** Get TravelDays	  */
	public int getHC_TravelDays();

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

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsComplete */
    public static final String COLUMNNAME_IsComplete = "IsComplete";

	/** Set Complete.
	  * It is complete
	  */
	public void setIsComplete (boolean IsComplete);

	/** Get Complete.
	  * It is complete
	  */
	public boolean isComplete();

    /** Column name NomorSPPD */
    public static final String COLUMNNAME_NomorSPPD = "NomorSPPD";

	/** Set Nomor SPPD	  */
	public void setNomorSPPD (String NomorSPPD);

	/** Get Nomor SPPD	  */
	public String getNomorSPPD();

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

    /** Column name RealizationAccomodationExpense */
    public static final String COLUMNNAME_RealizationAccomodationExpense = "RealizationAccomodationExpense";

	/** Set Realization Accomodation Expense	  */
	public void setRealizationAccomodationExpense (BigDecimal RealizationAccomodationExpense);

	/** Get Realization Accomodation Expense	  */
	public BigDecimal getRealizationAccomodationExpense();

    /** Column name RealizationDailyExpense */
    public static final String COLUMNNAME_RealizationDailyExpense = "RealizationDailyExpense";

	/** Set Realization Daily Expense	  */
	public void setRealizationDailyExpense (BigDecimal RealizationDailyExpense);

	/** Get Realization Daily Expense	  */
	public BigDecimal getRealizationDailyExpense();

    /** Column name RealizationOtherExpense */
    public static final String COLUMNNAME_RealizationOtherExpense = "RealizationOtherExpense";

	/** Set Realization Other Expense	  */
	public void setRealizationOtherExpense (BigDecimal RealizationOtherExpense);

	/** Get Realization Other Expense	  */
	public BigDecimal getRealizationOtherExpense();

    /** Column name RealizationTransportExpense */
    public static final String COLUMNNAME_RealizationTransportExpense = "RealizationTransportExpense";

	/** Set Realization Transport Expense	  */
	public void setRealizationTransportExpense (BigDecimal RealizationTransportExpense);

	/** Get Realization Transport Expense	  */
	public BigDecimal getRealizationTransportExpense();

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

    /** Column name TotalAccomodationExpense */
    public static final String COLUMNNAME_TotalAccomodationExpense = "TotalAccomodationExpense";

	/** Set Total Accomodation Expense	  */
	public void setTotalAccomodationExpense (BigDecimal TotalAccomodationExpense);

	/** Get Total Accomodation Expense	  */
	public BigDecimal getTotalAccomodationExpense();

    /** Column name TotalDailyExpense */
    public static final String COLUMNNAME_TotalDailyExpense = "TotalDailyExpense";

	/** Set Total Daily Expense	  */
	public void setTotalDailyExpense (BigDecimal TotalDailyExpense);

	/** Get Total Daily Expense	  */
	public BigDecimal getTotalDailyExpense();

    /** Column name TotalExpense */
    public static final String COLUMNNAME_TotalExpense = "TotalExpense";

	/** Set Total Expense	  */
	public void setTotalExpense (BigDecimal TotalExpense);

	/** Get Total Expense	  */
	public BigDecimal getTotalExpense();

    /** Column name TotalOtherPoint */
    public static final String COLUMNNAME_TotalOtherPoint = "TotalOtherPoint";

	/** Set Total Other Point	  */
	public void setTotalOtherPoint (BigDecimal TotalOtherPoint);

	/** Get Total Other Point	  */
	public BigDecimal getTotalOtherPoint();

    /** Column name TotalRealizationExpense */
    public static final String COLUMNNAME_TotalRealizationExpense = "TotalRealizationExpense";

	/** Set Total Realization Expense	  */
	public void setTotalRealizationExpense (BigDecimal TotalRealizationExpense);

	/** Get Total Realization Expense	  */
	public BigDecimal getTotalRealizationExpense();

    /** Column name TotalTransportExpense */
    public static final String COLUMNNAME_TotalTransportExpense = "TotalTransportExpense";

	/** Set Total Transport Expense	  */
	public void setTotalTransportExpense (BigDecimal TotalTransportExpense);

	/** Get Total Transport Expense	  */
	public BigDecimal getTotalTransportExpense();

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
