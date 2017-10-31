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

/** Generated Interface for HC_EmployeeLeaveBalance
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
public interface I_HC_EmployeeLeaveBalance 
{

    /** TableName=HC_EmployeeLeaveBalance */
    public static final String Table_Name = "HC_EmployeeLeaveBalance";

    /** AD_Table_ID=1000077 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name AllocatedAmt */
    public static final String COLUMNNAME_AllocatedAmt = "AllocatedAmt";

	/** Set Allocated Amountt.
	  * Amount allocated to this document
	  */
	public void setAllocatedAmt (int AllocatedAmt);

	/** Get Allocated Amountt.
	  * Amount allocated to this document
	  */
	public int getAllocatedAmt();

    /** Column name CBDatePeriod */
    public static final String COLUMNNAME_CBDatePeriod = "CBDatePeriod";

	/** Set CB Date Period.
	  * Cuti Besar Date Period
	  */
	public void setCBDatePeriod (Timestamp CBDatePeriod);

	/** Get CB Date Period.
	  * Cuti Besar Date Period
	  */
	public Timestamp getCBDatePeriod();

    /** Column name CBDatePeriodTo */
    public static final String COLUMNNAME_CBDatePeriodTo = "CBDatePeriodTo";

	/** Set CB Date Period To	  */
	public void setCBDatePeriodTo (Timestamp CBDatePeriodTo);

	/** Get CB Date Period To	  */
	public Timestamp getCBDatePeriodTo();

    /** Column name CBGetDate */
    public static final String COLUMNNAME_CBGetDate = "CBGetDate";

	/** Set CB Get Date.
	  * Cuti Besar Get Date
	  */
	public void setCBGetDate (Timestamp CBGetDate);

	/** Get CB Get Date.
	  * Cuti Besar Get Date
	  */
	public Timestamp getCBGetDate();

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

    /** Column name CTDatePeriod */
    public static final String COLUMNNAME_CTDatePeriod = "CTDatePeriod";

	/** Set CT Date Period.
	  * Cuti Tahunan Period
	  */
	public void setCTDatePeriod (Timestamp CTDatePeriod);

	/** Get CT Date Period.
	  * Cuti Tahunan Period
	  */
	public Timestamp getCTDatePeriod();

    /** Column name CTDatePeriodTo */
    public static final String COLUMNNAME_CTDatePeriodTo = "CTDatePeriodTo";

	/** Set CT Date Period To	  */
	public void setCTDatePeriodTo (Timestamp CTDatePeriodTo);

	/** Get CT Date Period To	  */
	public Timestamp getCTDatePeriodTo();

    /** Column name CTGetDate */
    public static final String COLUMNNAME_CTGetDate = "CTGetDate";

	/** Set CT Get Date.
	  * Cuti Tahunan Get Date
	  */
	public void setCTGetDate (Timestamp CTGetDate);

	/** Get CT Get Date.
	  * Cuti Tahunan Get Date
	  */
	public Timestamp getCTGetDate();

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

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_EmployeeLeaveBalance_ID */
    public static final String COLUMNNAME_HC_EmployeeLeaveBalance_ID = "HC_EmployeeLeaveBalance_ID";

	/** Set Employee Leave Balance	  */
	public void setHC_EmployeeLeaveBalance_ID (int HC_EmployeeLeaveBalance_ID);

	/** Get Employee Leave Balance	  */
	public int getHC_EmployeeLeaveBalance_ID();

    /** Column name HC_EmployeeLeaveBalance_UU */
    public static final String COLUMNNAME_HC_EmployeeLeaveBalance_UU = "HC_EmployeeLeaveBalance_UU";

	/** Set HC_EmployeeLeaveBalance_UU	  */
	public void setHC_EmployeeLeaveBalance_UU (String HC_EmployeeLeaveBalance_UU);

	/** Get HC_EmployeeLeaveBalance_UU	  */
	public String getHC_EmployeeLeaveBalance_UU();

    /** Column name HC_LeaveType_ID */
    public static final String COLUMNNAME_HC_LeaveType_ID = "HC_LeaveType_ID";

	/** Set Leave Type	  */
	public void setHC_LeaveType_ID (int HC_LeaveType_ID);

	/** Get Leave Type	  */
	public int getHC_LeaveType_ID();

	public I_HC_LeaveType getHC_LeaveType() throws RuntimeException;

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

    /** Column name LeaveBalancePeriod */
    public static final String COLUMNNAME_LeaveBalancePeriod = "LeaveBalancePeriod";

	/** Set Leave Balance Period	  */
	public void setLeaveBalancePeriod (String LeaveBalancePeriod);

	/** Get Leave Balance Period	  */
	public String getLeaveBalancePeriod();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name SaldoCutiBesar */
    public static final String COLUMNNAME_SaldoCutiBesar = "SaldoCutiBesar";

	/** Set Saldo Cuti Besar.
	  * Saldo Cuti Besar Karyawan ybs
	  */
	public void setSaldoCutiBesar (int SaldoCutiBesar);

	/** Get Saldo Cuti Besar.
	  * Saldo Cuti Besar Karyawan ybs
	  */
	public int getSaldoCutiBesar();

    /** Column name SaldoCutiTahunan */
    public static final String COLUMNNAME_SaldoCutiTahunan = "SaldoCutiTahunan";

	/** Set Saldo Cuti Tahunan.
	  * Saldo Cuti Tahunan Karyawan ybs
	  */
	public void setSaldoCutiTahunan (int SaldoCutiTahunan);

	/** Get Saldo Cuti Tahunan.
	  * Saldo Cuti Tahunan Karyawan ybs
	  */
	public int getSaldoCutiTahunan();

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

    /** Column name UsedCutiBesar */
    public static final String COLUMNNAME_UsedCutiBesar = "UsedCutiBesar";

	/** Set Used Cuti Besar	  */
	public void setUsedCutiBesar (int UsedCutiBesar);

	/** Get Used Cuti Besar	  */
	public int getUsedCutiBesar();

    /** Column name UsedCutiTahunan */
    public static final String COLUMNNAME_UsedCutiTahunan = "UsedCutiTahunan";

	/** Set Used Cuti Tahunan	  */
	public void setUsedCutiTahunan (int UsedCutiTahunan);

	/** Get Used Cuti Tahunan	  */
	public int getUsedCutiTahunan();
}
