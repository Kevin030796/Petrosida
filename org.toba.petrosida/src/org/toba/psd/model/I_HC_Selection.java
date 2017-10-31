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
import org.taowi.hcm.core.model.I_HC_EmployeeClass;
import org.taowi.hcm.core.model.I_HC_EmployeeJob;
import org.taowi.hcm.core.model.I_HC_Org;

/** Generated Interface for HC_Selection
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
public interface I_HC_Selection 
{

    /** TableName=HC_Selection */
    public static final String Table_Name = "HC_Selection";

    /** AD_Table_ID=1000092 */
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

    /** Column name HC_CattEvaluasi */
    public static final String COLUMNNAME_HC_CattEvaluasi = "HC_CattEvaluasi";

	/** Set Catatan Evaluasi	  */
	public void setHC_CattEvaluasi (String HC_CattEvaluasi);

	/** Get Catatan Evaluasi	  */
	public String getHC_CattEvaluasi();

    /** Column name HC_CattKhusus */
    public static final String COLUMNNAME_HC_CattKhusus = "HC_CattKhusus";

	/** Set Catatan Khusus	  */
	public void setHC_CattKhusus (String HC_CattKhusus);

	/** Get Catatan Khusus	  */
	public String getHC_CattKhusus();

    /** Column name HC_CattMedical1 */
    public static final String COLUMNNAME_HC_CattMedical1 = "HC_CattMedical1";

	/** Set Catatan Medis I	  */
	public void setHC_CattMedical1 (String HC_CattMedical1);

	/** Get Catatan Medis I	  */
	public String getHC_CattMedical1();

    /** Column name HC_CattMedical2 */
    public static final String COLUMNNAME_HC_CattMedical2 = "HC_CattMedical2";

	/** Set Catatan Medis 2	  */
	public void setHC_CattMedical2 (String HC_CattMedical2);

	/** Get Catatan Medis 2	  */
	public String getHC_CattMedical2();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_EmployeeClass_ID */
    public static final String COLUMNNAME_HC_EmployeeClass_ID = "HC_EmployeeClass_ID";

	/** Set Employee Class	  */
	public void setHC_EmployeeClass_ID (int HC_EmployeeClass_ID);

	/** Get Employee Class	  */
	public int getHC_EmployeeClass_ID();

	public I_HC_EmployeeClass getHC_EmployeeClass() throws RuntimeException;

    /** Column name HC_EmployeeClassTo_ID */
    public static final String COLUMNNAME_HC_EmployeeClassTo_ID = "HC_EmployeeClassTo_ID";

	/** Set Employee Class To	  */
	public void setHC_EmployeeClassTo_ID (int HC_EmployeeClassTo_ID);

	/** Get Employee Class To	  */
	public int getHC_EmployeeClassTo_ID();

	public I_HC_EmployeeClass getHC_EmployeeClassTo() throws RuntimeException;

    /** Column name HC_EmployeeJob_ID */
    public static final String COLUMNNAME_HC_EmployeeJob_ID = "HC_EmployeeJob_ID";

	/** Set Employee Job Data	  */
	public void setHC_EmployeeJob_ID (int HC_EmployeeJob_ID);

	/** Get Employee Job Data	  */
	public int getHC_EmployeeJob_ID();

	public I_HC_EmployeeJob getHC_EmployeeJob() throws RuntimeException;

    /** Column name HC_EvalDate */
    public static final String COLUMNNAME_HC_EvalDate = "HC_EvalDate";

	/** Set HC_EvalDate	  */
	public void setHC_EvalDate (Timestamp HC_EvalDate);

	/** Get HC_EvalDate	  */
	public Timestamp getHC_EvalDate();

    /** Column name HC_EvalScore */
    public static final String COLUMNNAME_HC_EvalScore = "HC_EvalScore";

	/** Set HC_EvalScore	  */
	public void setHC_EvalScore (String HC_EvalScore);

	/** Get HC_EvalScore	  */
	public String getHC_EvalScore();

    /** Column name HC_MedicalDate1 */
    public static final String COLUMNNAME_HC_MedicalDate1 = "HC_MedicalDate1";

	/** Set HC_MedicalDate1	  */
	public void setHC_MedicalDate1 (Timestamp HC_MedicalDate1);

	/** Get HC_MedicalDate1	  */
	public Timestamp getHC_MedicalDate1();

    /** Column name HC_MedicalDate2 */
    public static final String COLUMNNAME_HC_MedicalDate2 = "HC_MedicalDate2";

	/** Set HC_MedicalDate2	  */
	public void setHC_MedicalDate2 (Timestamp HC_MedicalDate2);

	/** Get HC_MedicalDate2	  */
	public Timestamp getHC_MedicalDate2();

    /** Column name HC_MedicalScore1 */
    public static final String COLUMNNAME_HC_MedicalScore1 = "HC_MedicalScore1";

	/** Set HC_MedicalScore1	  */
	public void setHC_MedicalScore1 (String HC_MedicalScore1);

	/** Get HC_MedicalScore1	  */
	public String getHC_MedicalScore1();

    /** Column name HC_MedicalScore2 */
    public static final String COLUMNNAME_HC_MedicalScore2 = "HC_MedicalScore2";

	/** Set HC_MedicalScore2	  */
	public void setHC_MedicalScore2 (String HC_MedicalScore2);

	/** Get HC_MedicalScore2	  */
	public String getHC_MedicalScore2();

    /** Column name HC_Org_ID */
    public static final String COLUMNNAME_HC_Org_ID = "HC_Org_ID";

	/** Set HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID);

	/** Get HC Organization	  */
	public int getHC_Org_ID();

	public I_HC_Org getHC_Org() throws RuntimeException;

    /** Column name HC_PresentationDate */
    public static final String COLUMNNAME_HC_PresentationDate = "HC_PresentationDate";

	/** Set HC_PresentationDate	  */
	public void setHC_PresentationDate (Timestamp HC_PresentationDate);

	/** Get HC_PresentationDate	  */
	public Timestamp getHC_PresentationDate();

    /** Column name HC_PresentationDesc */
    public static final String COLUMNNAME_HC_PresentationDesc = "HC_PresentationDesc";

	/** Set HC_PresentationDesc	  */
	public void setHC_PresentationDesc (String HC_PresentationDesc);

	/** Get HC_PresentationDesc	  */
	public String getHC_PresentationDesc();

    /** Column name HC_PresentationScore */
    public static final String COLUMNNAME_HC_PresentationScore = "HC_PresentationScore";

	/** Set Nilai Presentasi	  */
	public void setHC_PresentationScore (String HC_PresentationScore);

	/** Get Nilai Presentasi	  */
	public String getHC_PresentationScore();

    /** Column name HC_Selection_ID */
    public static final String COLUMNNAME_HC_Selection_ID = "HC_Selection_ID";

	/** Set HC_Selection	  */
	public void setHC_Selection_ID (int HC_Selection_ID);

	/** Get HC_Selection	  */
	public int getHC_Selection_ID();

    /** Column name HC_Selection_UU */
    public static final String COLUMNNAME_HC_Selection_UU = "HC_Selection_UU";

	/** Set HC_Selection_UU	  */
	public void setHC_Selection_UU (String HC_Selection_UU);

	/** Get HC_Selection_UU	  */
	public String getHC_Selection_UU();

    /** Column name HC_TanggalAkhirTMT */
    public static final String COLUMNNAME_HC_TanggalAkhirTMT = "HC_TanggalAkhirTMT";

	/** Set Tanggal Akhir TMT	  */
	public void setHC_TanggalAkhirTMT (Timestamp HC_TanggalAkhirTMT);

	/** Get Tanggal Akhir TMT	  */
	public Timestamp getHC_TanggalAkhirTMT();

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

    /** Column name IsCatatanKhusus */
    public static final String COLUMNNAME_IsCatatanKhusus = "IsCatatanKhusus";

	/** Set IsCatatanKhusus	  */
	public void setIsCatatanKhusus (boolean IsCatatanKhusus);

	/** Get IsCatatanKhusus	  */
	public boolean isCatatanKhusus();

    /** Column name IsPassMedical1 */
    public static final String COLUMNNAME_IsPassMedical1 = "IsPassMedical1";

	/** Set IsPassMedical1	  */
	public void setIsPassMedical1 (boolean IsPassMedical1);

	/** Get IsPassMedical1	  */
	public boolean isPassMedical1();

    /** Column name IsPassMedical2 */
    public static final String COLUMNNAME_IsPassMedical2 = "IsPassMedical2";

	/** Set IsPassMedical2	  */
	public void setIsPassMedical2 (boolean IsPassMedical2);

	/** Get IsPassMedical2	  */
	public boolean isPassMedical2();

    /** Column name IsPassPresentation */
    public static final String COLUMNNAME_IsPassPresentation = "IsPassPresentation";

	/** Set IsPassPresentation	  */
	public void setIsPassPresentation (boolean IsPassPresentation);

	/** Get IsPassPresentation	  */
	public boolean isPassPresentation();

    /** Column name IsPassTheEvaluation */
    public static final String COLUMNNAME_IsPassTheEvaluation = "IsPassTheEvaluation";

	/** Set IsPassTheEvaluation	  */
	public void setIsPassTheEvaluation (boolean IsPassTheEvaluation);

	/** Get IsPassTheEvaluation	  */
	public boolean isPassTheEvaluation();

    /** Column name IsPresentation */
    public static final String COLUMNNAME_IsPresentation = "IsPresentation";

	/** Set IsPresentation	  */
	public void setIsPresentation (boolean IsPresentation);

	/** Get IsPresentation	  */
	public boolean isPresentation();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NomorSK */
    public static final String COLUMNNAME_NomorSK = "NomorSK";

	/** Set Nomor SK	  */
	public void setNomorSK (String NomorSK);

	/** Get Nomor SK	  */
	public String getNomorSK();

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

    /** Column name TanggalNomorSK */
    public static final String COLUMNNAME_TanggalNomorSK = "TanggalNomorSK";

	/** Set TanggalNomorSK	  */
	public void setTanggalNomorSK (Timestamp TanggalNomorSK);

	/** Get TanggalNomorSK	  */
	public Timestamp getTanggalNomorSK();

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
