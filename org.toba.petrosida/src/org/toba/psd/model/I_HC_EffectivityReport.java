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

/** Generated Interface for HC_EffectivityReport
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
public interface I_HC_EffectivityReport 
{

    /** TableName=HC_EffectivityReport */
    public static final String Table_Name = "HC_EffectivityReport";

    /** AD_Table_ID=1000107 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name DateStart */
    public static final String COLUMNNAME_DateStart = "DateStart";

	/** Set Date Start.
	  * Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart);

	/** Get Date Start.
	  * Date Start for this Order
	  */
	public Timestamp getDateStart();

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

    /** Column name EvaluasiInstruktur1 */
    public static final String COLUMNNAME_EvaluasiInstruktur1 = "EvaluasiInstruktur1";

	/** Set EvaluasiInstruktur1	  */
	public void setEvaluasiInstruktur1 (int EvaluasiInstruktur1);

	/** Get EvaluasiInstruktur1	  */
	public int getEvaluasiInstruktur1();

    /** Column name EvaluasiInstruktur2 */
    public static final String COLUMNNAME_EvaluasiInstruktur2 = "EvaluasiInstruktur2";

	/** Set EvaluasiInstruktur2	  */
	public void setEvaluasiInstruktur2 (int EvaluasiInstruktur2);

	/** Get EvaluasiInstruktur2	  */
	public int getEvaluasiInstruktur2();

    /** Column name EvaluasiMateri1 */
    public static final String COLUMNNAME_EvaluasiMateri1 = "EvaluasiMateri1";

	/** Set EvaluasiMateri1	  */
	public void setEvaluasiMateri1 (int EvaluasiMateri1);

	/** Get EvaluasiMateri1	  */
	public int getEvaluasiMateri1();

    /** Column name EvaluasiMateri2 */
    public static final String COLUMNNAME_EvaluasiMateri2 = "EvaluasiMateri2";

	/** Set EvaluasiMateri2	  */
	public void setEvaluasiMateri2 (int EvaluasiMateri2);

	/** Get EvaluasiMateri2	  */
	public int getEvaluasiMateri2();

    /** Column name EvaluasiMateri3 */
    public static final String COLUMNNAME_EvaluasiMateri3 = "EvaluasiMateri3";

	/** Set EvaluasiMateri3	  */
	public void setEvaluasiMateri3 (int EvaluasiMateri3);

	/** Get EvaluasiMateri3	  */
	public int getEvaluasiMateri3();

    /** Column name EvaluasiPelaksana1 */
    public static final String COLUMNNAME_EvaluasiPelaksana1 = "EvaluasiPelaksana1";

	/** Set EvaluasiPelaksana1	  */
	public void setEvaluasiPelaksana1 (int EvaluasiPelaksana1);

	/** Get EvaluasiPelaksana1	  */
	public int getEvaluasiPelaksana1();

    /** Column name EvaluasiPelaksana2 */
    public static final String COLUMNNAME_EvaluasiPelaksana2 = "EvaluasiPelaksana2";

	/** Set EvaluasiPelaksana2	  */
	public void setEvaluasiPelaksana2 (String EvaluasiPelaksana2);

	/** Get EvaluasiPelaksana2	  */
	public String getEvaluasiPelaksana2();

    /** Column name EvaluasiPelaksana3 */
    public static final String COLUMNNAME_EvaluasiPelaksana3 = "EvaluasiPelaksana3";

	/** Set EvaluasiPelaksana3	  */
	public void setEvaluasiPelaksana3 (int EvaluasiPelaksana3);

	/** Get EvaluasiPelaksana3	  */
	public int getEvaluasiPelaksana3();

    /** Column name HC_EffectivityReport_ID */
    public static final String COLUMNNAME_HC_EffectivityReport_ID = "HC_EffectivityReport_ID";

	/** Set Effectivity Report	  */
	public void setHC_EffectivityReport_ID (int HC_EffectivityReport_ID);

	/** Get Effectivity Report	  */
	public int getHC_EffectivityReport_ID();

    /** Column name HC_EffectivityReport_UU */
    public static final String COLUMNNAME_HC_EffectivityReport_UU = "HC_EffectivityReport_UU";

	/** Set HC_EffectivityReport_UU	  */
	public void setHC_EffectivityReport_UU (String HC_EffectivityReport_UU);

	/** Get HC_EffectivityReport_UU	  */
	public String getHC_EffectivityReport_UU();

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

    /** Column name HC_NIK */
    public static final String COLUMNNAME_HC_NIK = "HC_NIK";

	/** Set NIK	  */
	public void setHC_NIK (String HC_NIK);

	/** Get NIK	  */
	public String getHC_NIK();

    /** Column name HC_Org_ID */
    public static final String COLUMNNAME_HC_Org_ID = "HC_Org_ID";

	/** Set HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID);

	/** Get HC Organization	  */
	public int getHC_Org_ID();

	public I_HC_Org getHC_Org() throws RuntimeException;

    /** Column name HC_TrainingClass_ID */
    public static final String COLUMNNAME_HC_TrainingClass_ID = "HC_TrainingClass_ID";

	/** Set Training Class	  */
	public void setHC_TrainingClass_ID (int HC_TrainingClass_ID);

	/** Get Training Class	  */
	public int getHC_TrainingClass_ID();

	public I_HC_TrainingClass getHC_TrainingClass() throws RuntimeException;

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

    /** Column name Label_EvaluasiInstruktur1 */
    public static final String COLUMNNAME_Label_EvaluasiInstruktur1 = "Label_EvaluasiInstruktur1";

	/** Set Label_EvaluasiInstruktur1	  */
	public void setLabel_EvaluasiInstruktur1 (String Label_EvaluasiInstruktur1);

	/** Get Label_EvaluasiInstruktur1	  */
	public String getLabel_EvaluasiInstruktur1();

    /** Column name Label_EvaluasiInstruktur2 */
    public static final String COLUMNNAME_Label_EvaluasiInstruktur2 = "Label_EvaluasiInstruktur2";

	/** Set Label_EvaluasiInstruktur2	  */
	public void setLabel_EvaluasiInstruktur2 (String Label_EvaluasiInstruktur2);

	/** Get Label_EvaluasiInstruktur2	  */
	public String getLabel_EvaluasiInstruktur2();

    /** Column name Label_EvaluasiMateri1 */
    public static final String COLUMNNAME_Label_EvaluasiMateri1 = "Label_EvaluasiMateri1";

	/** Set Label_EvaluasiMateri1	  */
	public void setLabel_EvaluasiMateri1 (String Label_EvaluasiMateri1);

	/** Get Label_EvaluasiMateri1	  */
	public String getLabel_EvaluasiMateri1();

    /** Column name Label_EvaluasiMateri2 */
    public static final String COLUMNNAME_Label_EvaluasiMateri2 = "Label_EvaluasiMateri2";

	/** Set Label_EvaluasiMateri2	  */
	public void setLabel_EvaluasiMateri2 (String Label_EvaluasiMateri2);

	/** Get Label_EvaluasiMateri2	  */
	public String getLabel_EvaluasiMateri2();

    /** Column name Label_EvaluasiMateri3 */
    public static final String COLUMNNAME_Label_EvaluasiMateri3 = "Label_EvaluasiMateri3";

	/** Set Label_EvaluasiMateri3	  */
	public void setLabel_EvaluasiMateri3 (String Label_EvaluasiMateri3);

	/** Get Label_EvaluasiMateri3	  */
	public String getLabel_EvaluasiMateri3();

    /** Column name Label_EvaluasiPelaksana1 */
    public static final String COLUMNNAME_Label_EvaluasiPelaksana1 = "Label_EvaluasiPelaksana1";

	/** Set Label_EvaluasiPelaksana1	  */
	public void setLabel_EvaluasiPelaksana1 (String Label_EvaluasiPelaksana1);

	/** Get Label_EvaluasiPelaksana1	  */
	public String getLabel_EvaluasiPelaksana1();

    /** Column name Label_EvaluasiPelaksana2 */
    public static final String COLUMNNAME_Label_EvaluasiPelaksana2 = "Label_EvaluasiPelaksana2";

	/** Set Label_EvaluasiPelaksana2	  */
	public void setLabel_EvaluasiPelaksana2 (String Label_EvaluasiPelaksana2);

	/** Get Label_EvaluasiPelaksana2	  */
	public String getLabel_EvaluasiPelaksana2();

    /** Column name Label_EvaluasiPelaksana3 */
    public static final String COLUMNNAME_Label_EvaluasiPelaksana3 = "Label_EvaluasiPelaksana3";

	/** Set Label_EvaluasiPelaksana3	  */
	public void setLabel_EvaluasiPelaksana3 (String Label_EvaluasiPelaksana3);

	/** Get Label_EvaluasiPelaksana3	  */
	public String getLabel_EvaluasiPelaksana3();

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
