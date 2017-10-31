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
import org.taowi.hcm.core.model.I_HC_EmployeeGrade;
import org.taowi.hcm.core.model.I_HC_EmployeeJob;
import org.taowi.hcm.core.model.I_HC_Job;
import org.taowi.hcm.core.model.I_HC_JobDataChange;
import org.taowi.hcm.core.model.I_HC_Org;
import org.taowi.hcm.core.model.I_HC_PayGroup;
import org.taowi.hcm.core.model.I_HC_Reason;

/** Generated Interface for IHC_JobDataChange
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
public interface I_IHC_JobDataChange 
{

    /** TableName=IHC_JobDataChange */
    public static final String Table_Name = "IHC_JobDataChange";

    /** AD_Table_ID=1100135 */
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

    /** Column name BPJSRegistrationDate */
    public static final String COLUMNNAME_BPJSRegistrationDate = "BPJSRegistrationDate";

	/** Set BPJS Registration Date	  */
	public void setBPJSRegistrationDate (Timestamp BPJSRegistrationDate);

	/** Get BPJS Registration Date	  */
	public Timestamp getBPJSRegistrationDate();

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

    /** Column name DescriptionNew */
    public static final String COLUMNNAME_DescriptionNew = "DescriptionNew";

	/** Set Description New	  */
	public void setDescriptionNew (String DescriptionNew);

	/** Get Description New	  */
	public String getDescriptionNew();

    /** Column name HC_Compensation1 */
    public static final String COLUMNNAME_HC_Compensation1 = "HC_Compensation1";

	/** Set Compensation 1	  */
	public void setHC_Compensation1 (BigDecimal HC_Compensation1);

	/** Get Compensation 1	  */
	public BigDecimal getHC_Compensation1();

    /** Column name HC_Compensation2 */
    public static final String COLUMNNAME_HC_Compensation2 = "HC_Compensation2";

	/** Set Compensation 2	  */
	public void setHC_Compensation2 (BigDecimal HC_Compensation2);

	/** Get Compensation 2	  */
	public BigDecimal getHC_Compensation2();

    /** Column name HC_Compensation3 */
    public static final String COLUMNNAME_HC_Compensation3 = "HC_Compensation3";

	/** Set Compensation 3	  */
	public void setHC_Compensation3 (BigDecimal HC_Compensation3);

	/** Get Compensation 3	  */
	public BigDecimal getHC_Compensation3();

    /** Column name HC_Compensation4 */
    public static final String COLUMNNAME_HC_Compensation4 = "HC_Compensation4";

	/** Set Compensation 4	  */
	public void setHC_Compensation4 (BigDecimal HC_Compensation4);

	/** Get Compensation 4	  */
	public BigDecimal getHC_Compensation4();

    /** Column name HC_EffectiveSeq */
    public static final String COLUMNNAME_HC_EffectiveSeq = "HC_EffectiveSeq";

	/** Set Effective Sequence	  */
	public void setHC_EffectiveSeq (int HC_EffectiveSeq);

	/** Get Effective Sequence	  */
	public int getHC_EffectiveSeq();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_EmployeeGrade_ID */
    public static final String COLUMNNAME_HC_EmployeeGrade_ID = "HC_EmployeeGrade_ID";

	/** Set Employee Grade	  */
	public void setHC_EmployeeGrade_ID (int HC_EmployeeGrade_ID);

	/** Get Employee Grade	  */
	public int getHC_EmployeeGrade_ID();

	public I_HC_EmployeeGrade getHC_EmployeeGrade() throws RuntimeException;

    /** Column name HC_EmployeeGrade2_ID */
    public static final String COLUMNNAME_HC_EmployeeGrade2_ID = "HC_EmployeeGrade2_ID";

	/** Set Employee Grade To	  */
	public void setHC_EmployeeGrade2_ID (int HC_EmployeeGrade2_ID);

	/** Get Employee Grade To	  */
	public int getHC_EmployeeGrade2_ID();

	public I_HC_EmployeeGrade getHC_EmployeeGrade2() throws RuntimeException;

    /** Column name HC_EmployeeJob_ID */
    public static final String COLUMNNAME_HC_EmployeeJob_ID = "HC_EmployeeJob_ID";

	/** Set Employee Job Data	  */
	public void setHC_EmployeeJob_ID (int HC_EmployeeJob_ID);

	/** Get Employee Job Data	  */
	public int getHC_EmployeeJob_ID();

	public I_HC_EmployeeJob getHC_EmployeeJob() throws RuntimeException;

    /** Column name HC_Job_ID */
    public static final String COLUMNNAME_HC_Job_ID = "HC_Job_ID";

	/** Set Job	  */
	public void setHC_Job_ID (int HC_Job_ID);

	/** Get Job	  */
	public int getHC_Job_ID();

	public I_HC_Job getHC_Job() throws RuntimeException;

    /** Column name HC_JobAction */
    public static final String COLUMNNAME_HC_JobAction = "HC_JobAction";

	/** Set Job Action	  */
	public void setHC_JobAction (String HC_JobAction);

	/** Get Job Action	  */
	public String getHC_JobAction();

    /** Column name HC_JobDataChange_ID */
    public static final String COLUMNNAME_HC_JobDataChange_ID = "HC_JobDataChange_ID";

	/** Set Job Data Change	  */
	public void setHC_JobDataChange_ID (int HC_JobDataChange_ID);

	/** Get Job Data Change	  */
	public int getHC_JobDataChange_ID();

	public I_HC_JobDataChange getHC_JobDataChange() throws RuntimeException;

    /** Column name HC_Manager_ID */
    public static final String COLUMNNAME_HC_Manager_ID = "HC_Manager_ID";

	/** Set Manager Name	  */
	public void setHC_Manager_ID (int HC_Manager_ID);

	/** Get Manager Name	  */
	public int getHC_Manager_ID();

	public I_HC_Employee getHC_Manager() throws RuntimeException;

    /** Column name HC_ManagerTo_ID */
    public static final String COLUMNNAME_HC_ManagerTo_ID = "HC_ManagerTo_ID";

	/** Set Manager To ID	  */
	public void setHC_ManagerTo_ID (int HC_ManagerTo_ID);

	/** Get Manager To ID	  */
	public int getHC_ManagerTo_ID();

	public I_HC_Employee getHC_ManagerTo() throws RuntimeException;

    /** Column name HC_Org_ID */
    public static final String COLUMNNAME_HC_Org_ID = "HC_Org_ID";

	/** Set HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID);

	/** Get HC Organization	  */
	public int getHC_Org_ID();

	public I_HC_Org getHC_Org() throws RuntimeException;

    /** Column name HC_Org2_ID */
    public static final String COLUMNNAME_HC_Org2_ID = "HC_Org2_ID";

	/** Set HC Organization To	  */
	public void setHC_Org2_ID (int HC_Org2_ID);

	/** Get HC Organization To	  */
	public int getHC_Org2_ID();

	public I_HC_Org getHC_Org2() throws RuntimeException;

    /** Column name HC_PayGroup_ID */
    public static final String COLUMNNAME_HC_PayGroup_ID = "HC_PayGroup_ID";

	/** Set Payroll Group	  */
	public void setHC_PayGroup_ID (int HC_PayGroup_ID);

	/** Get Payroll Group	  */
	public int getHC_PayGroup_ID();

	public I_HC_PayGroup getHC_PayGroup() throws RuntimeException;

    /** Column name HC_PreviousJob_ID */
    public static final String COLUMNNAME_HC_PreviousJob_ID = "HC_PreviousJob_ID";

	/** Set Job Sekarang	  */
	public void setHC_PreviousJob_ID (int HC_PreviousJob_ID);

	/** Get Job Sekarang	  */
	public int getHC_PreviousJob_ID();

	public I_HC_Job getHC_PreviousJob() throws RuntimeException;

    /** Column name HC_Reason_ID */
    public static final String COLUMNNAME_HC_Reason_ID = "HC_Reason_ID";

	/** Set HC Reason	  */
	public void setHC_Reason_ID (int HC_Reason_ID);

	/** Get HC Reason	  */
	public int getHC_Reason_ID();

	public I_HC_Reason getHC_Reason() throws RuntimeException;

    /** Column name HC_Status */
    public static final String COLUMNNAME_HC_Status = "HC_Status";

	/** Set HC Status	  */
	public void setHC_Status (String HC_Status);

	/** Get HC Status	  */
	public String getHC_Status();

    /** Column name HC_WorkEndDate */
    public static final String COLUMNNAME_HC_WorkEndDate = "HC_WorkEndDate";

	/** Set Work End Date	  */
	public void setHC_WorkEndDate (Timestamp HC_WorkEndDate);

	/** Get Work End Date	  */
	public Timestamp getHC_WorkEndDate();

    /** Column name HC_WorkPeriodDate */
    public static final String COLUMNNAME_HC_WorkPeriodDate = "HC_WorkPeriodDate";

	/** Set WorkPeriodDate	  */
	public void setHC_WorkPeriodDate (String HC_WorkPeriodDate);

	/** Get WorkPeriodDate	  */
	public String getHC_WorkPeriodDate();

    /** Column name HC_WorkStartDate */
    public static final String COLUMNNAME_HC_WorkStartDate = "HC_WorkStartDate";

	/** Set WorkStartDate	  */
	public void setHC_WorkStartDate (Timestamp HC_WorkStartDate);

	/** Get WorkStartDate	  */
	public Timestamp getHC_WorkStartDate();

    /** Column name HC_WorkStartDate2 */
    public static final String COLUMNNAME_HC_WorkStartDate2 = "HC_WorkStartDate2";

	/** Set Work Start Date Baru	  */
	public void setHC_WorkStartDate2 (Timestamp HC_WorkStartDate2);

	/** Get Work Start Date Baru	  */
	public Timestamp getHC_WorkStartDate2();

    /** Column name IHC_JobDataChange_ID */
    public static final String COLUMNNAME_IHC_JobDataChange_ID = "IHC_JobDataChange_ID";

	/** Set IHC_JobDayaChange	  */
	public void setIHC_JobDataChange_ID (int IHC_JobDataChange_ID);

	/** Get IHC_JobDayaChange	  */
	public int getIHC_JobDataChange_ID();

    /** Column name IHC_JobDataChange_UU */
    public static final String COLUMNNAME_IHC_JobDataChange_UU = "IHC_JobDataChange_UU";

	/** Set IHC_JobDataChange_UU	  */
	public void setIHC_JobDataChange_UU (String IHC_JobDataChange_UU);

	/** Get IHC_JobDataChange_UU	  */
	public String getIHC_JobDataChange_UU();

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

    /** Column name IsCancelled */
    public static final String COLUMNNAME_IsCancelled = "IsCancelled";

	/** Set Cancelled.
	  * The transaction was cancelled
	  */
	public void setIsCancelled (boolean IsCancelled);

	/** Get Cancelled.
	  * The transaction was cancelled
	  */
	public boolean isCancelled();

    /** Column name NomorSK */
    public static final String COLUMNNAME_NomorSK = "NomorSK";

	/** Set Nomor SK	  */
	public void setNomorSK (String NomorSK);

	/** Get Nomor SK	  */
	public String getNomorSK();

    /** Column name NomorSK2 */
    public static final String COLUMNNAME_NomorSK2 = "NomorSK2";

	/** Set Nomor SK Baru	  */
	public void setNomorSK2 (String NomorSK2);

	/** Get Nomor SK Baru	  */
	public String getNomorSK2();

    /** Column name OriginalServiceData */
    public static final String COLUMNNAME_OriginalServiceData = "OriginalServiceData";

	/** Set Original Service Date	  */
	public void setOriginalServiceData (Timestamp OriginalServiceData);

	/** Get Original Service Date	  */
	public Timestamp getOriginalServiceData();

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
