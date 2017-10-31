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

/** Generated Interface for HC_Shift
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
public interface I_HC_Shift 
{

    /** TableName=HC_Shift */
    public static final String Table_Name = "HC_Shift";

    /** AD_Table_ID=1100125 */
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

    /** Column name EndNextDay */
    public static final String COLUMNNAME_EndNextDay = "EndNextDay";

	/** Set Shift Ends Next Day	  */
	public void setEndNextDay (boolean EndNextDay);

	/** Get Shift Ends Next Day	  */
	public boolean isEndNextDay();

    /** Column name HC_Shift_ID */
    public static final String COLUMNNAME_HC_Shift_ID = "HC_Shift_ID";

	/** Set Shift Employee	  */
	public void setHC_Shift_ID (int HC_Shift_ID);

	/** Get Shift Employee	  */
	public int getHC_Shift_ID();

    /** Column name HC_Shift_UU */
    public static final String COLUMNNAME_HC_Shift_UU = "HC_Shift_UU";

	/** Set HC_Shift_UU	  */
	public void setHC_Shift_UU (String HC_Shift_UU);

	/** Get HC_Shift_UU	  */
	public String getHC_Shift_UU();

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
