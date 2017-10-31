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

/** Generated Interface for HC_EmployeeRetirement
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
public interface I_HC_EmployeeRetirement 
{

    /** TableName=HC_EmployeeRetirement */
    public static final String Table_Name = "HC_EmployeeRetirement";

    /** AD_Table_ID=1000129 */
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

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_EmployeeRetirement_ID */
    public static final String COLUMNNAME_HC_EmployeeRetirement_ID = "HC_EmployeeRetirement_ID";

	/** Set HC_EmployeeRetirement	  */
	public void setHC_EmployeeRetirement_ID (int HC_EmployeeRetirement_ID);

	/** Get HC_EmployeeRetirement	  */
	public int getHC_EmployeeRetirement_ID();

    /** Column name HC_EmployeeRetirement_UU */
    public static final String COLUMNNAME_HC_EmployeeRetirement_UU = "HC_EmployeeRetirement_UU";

	/** Set HC_EmployeeRetirement_UU	  */
	public void setHC_EmployeeRetirement_UU (String HC_EmployeeRetirement_UU);

	/** Get HC_EmployeeRetirement_UU	  */
	public String getHC_EmployeeRetirement_UU();

    /** Column name HC_Job_ID */
    public static final String COLUMNNAME_HC_Job_ID = "HC_Job_ID";

	/** Set Job	  */
	public void setHC_Job_ID (int HC_Job_ID);

	/** Get Job	  */
	public int getHC_Job_ID();

	public I_HC_Job getHC_Job() throws RuntimeException;

    /** Column name HC_Org_ID */
    public static final String COLUMNNAME_HC_Org_ID = "HC_Org_ID";

	/** Set HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID);

	/** Get HC Organization	  */
	public int getHC_Org_ID();

	public I_HC_Org getHC_Org() throws RuntimeException;

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

    /** Column name MasaPersiapanPensiun */
    public static final String COLUMNNAME_MasaPersiapanPensiun = "MasaPersiapanPensiun";

	/** Set Masa Persiapan Pensiun	  */
	public void setMasaPersiapanPensiun (Timestamp MasaPersiapanPensiun);

	/** Get Masa Persiapan Pensiun	  */
	public Timestamp getMasaPersiapanPensiun();

    /** Column name NomorSK */
    public static final String COLUMNNAME_NomorSK = "NomorSK";

	/** Set Nomor SK	  */
	public void setNomorSK (String NomorSK);

	/** Get Nomor SK	  */
	public String getNomorSK();

    /** Column name PensiunDate */
    public static final String COLUMNNAME_PensiunDate = "PensiunDate";

	/** Set Pensiun Date	  */
	public void setPensiunDate (Timestamp PensiunDate);

	/** Get Pensiun Date	  */
	public Timestamp getPensiunDate();

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

    /** Column name RetirementDate */
    public static final String COLUMNNAME_RetirementDate = "RetirementDate";

	/** Set Retirement Date	  */
	public void setRetirementDate (Timestamp RetirementDate);

	/** Get Retirement Date	  */
	public Timestamp getRetirementDate();

    /** Column name RetirementType */
    public static final String COLUMNNAME_RetirementType = "RetirementType";

	/** Set Retirement Type	  */
	public void setRetirementType (String RetirementType);

	/** Get Retirement Type	  */
	public String getRetirementType();

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
}
