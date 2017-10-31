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

/** Generated Interface for HC_ManpowerPlanning
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
public interface I_HC_ManpowerPlanning 
{

    /** TableName=HC_ManpowerPlanning */
    public static final String Table_Name = "HC_ManpowerPlanning";

    /** AD_Table_ID=1000195 */
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

    /** Column name HC_ManpowerPlanning_ID */
    public static final String COLUMNNAME_HC_ManpowerPlanning_ID = "HC_ManpowerPlanning_ID";

	/** Set Manpower Calculation	  */
	public void setHC_ManpowerPlanning_ID (int HC_ManpowerPlanning_ID);

	/** Get Manpower Calculation	  */
	public int getHC_ManpowerPlanning_ID();

    /** Column name HC_ManpowerPlanning_UU */
    public static final String COLUMNNAME_HC_ManpowerPlanning_UU = "HC_ManpowerPlanning_UU";

	/** Set HC_ManpowerPlanning_UU	  */
	public void setHC_ManpowerPlanning_UU (String HC_ManpowerPlanning_UU);

	/** Get HC_ManpowerPlanning_UU	  */
	public String getHC_ManpowerPlanning_UU();

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

    /** Column name Processing2 */
    public static final String COLUMNNAME_Processing2 = "Processing2";

	/** Set Processing 2	  */
	public void setProcessing2 (String Processing2);

	/** Get Processing 2	  */
	public String getProcessing2();

    /** Column name TotalEmployee */
    public static final String COLUMNNAME_TotalEmployee = "TotalEmployee";

	/** Set Total Employee	  */
	public void setTotalEmployee (BigDecimal TotalEmployee);

	/** Get Total Employee	  */
	public BigDecimal getTotalEmployee();

    /** Column name TotalIdeaKaryawan */
    public static final String COLUMNNAME_TotalIdealKaryawan = "TotalIdealKaryawan";

	/** Set Total Idea Karyawan	  */
	public void setTotalIdealKaryawan (BigDecimal TotalIdealKaryawan);

	/** Get Total Idea Karyawan	  */
	public BigDecimal getTotalIdealKaryawan();

    /** Column name TotalPensionEmployee */
    public static final String COLUMNNAME_TotalPensionEmployee = "TotalPensionEmployee";

	/** Set Total Pension Employee	  */
	public void setTotalPensionEmployee (BigDecimal TotalPensionEmployee);

	/** Get Total Pension Employee	  */
	public BigDecimal getTotalPensionEmployee();

    /** Column name TotalRequirement */
    public static final String COLUMNNAME_TotalRequirement = "TotalRequirement";

	/** Set TotalRequirement	  */
	public void setTotalRequirement (BigDecimal TotalRequirement);

	/** Get TotalRequirement	  */
	public BigDecimal getTotalRequirement();

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
