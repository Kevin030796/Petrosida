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
/** Generated Model - DO NOT CHANGE */

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;

/** Generated Model for HC_EmployeeLeaveBalance
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_EmployeeLeaveBalance extends PO implements I_HC_EmployeeLeaveBalance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171010L;

    /** Standard Constructor */
    public X_HC_EmployeeLeaveBalance (Properties ctx, int HC_EmployeeLeaveBalance_ID, String trxName)
    {
      super (ctx, HC_EmployeeLeaveBalance_ID, trxName);
      /** if (HC_EmployeeLeaveBalance_ID == 0)
        {
			setHC_EmployeeLeaveBalance_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_EmployeeLeaveBalance (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HC_EmployeeLeaveBalance[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Allocated Amountt.
		@param AllocatedAmt 
		Amount allocated to this document
	  */
	public void setAllocatedAmt (int AllocatedAmt)
	{
		set_Value (COLUMNNAME_AllocatedAmt, Integer.valueOf(AllocatedAmt));
	}

	/** Get Allocated Amountt.
		@return Amount allocated to this document
	  */
	public int getAllocatedAmt () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AllocatedAmt);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CB Date Period.
		@param CBDatePeriod 
		Cuti Besar Date Period
	  */
	public void setCBDatePeriod (Timestamp CBDatePeriod)
	{
		set_Value (COLUMNNAME_CBDatePeriod, CBDatePeriod);
	}

	/** Get CB Date Period.
		@return Cuti Besar Date Period
	  */
	public Timestamp getCBDatePeriod () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CBDatePeriod);
	}

	/** Set CB Date Period To.
		@param CBDatePeriodTo CB Date Period To	  */
	public void setCBDatePeriodTo (Timestamp CBDatePeriodTo)
	{
		set_Value (COLUMNNAME_CBDatePeriodTo, CBDatePeriodTo);
	}

	/** Get CB Date Period To.
		@return CB Date Period To	  */
	public Timestamp getCBDatePeriodTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CBDatePeriodTo);
	}

	/** Set CB Get Date.
		@param CBGetDate 
		Cuti Besar Get Date
	  */
	public void setCBGetDate (Timestamp CBGetDate)
	{
		set_Value (COLUMNNAME_CBGetDate, CBGetDate);
	}

	/** Get CB Get Date.
		@return Cuti Besar Get Date
	  */
	public Timestamp getCBGetDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CBGetDate);
	}

	/** Set CT Date Period.
		@param CTDatePeriod 
		Cuti Tahunan Period
	  */
	public void setCTDatePeriod (Timestamp CTDatePeriod)
	{
		set_Value (COLUMNNAME_CTDatePeriod, CTDatePeriod);
	}

	/** Get CT Date Period.
		@return Cuti Tahunan Period
	  */
	public Timestamp getCTDatePeriod () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CTDatePeriod);
	}

	/** Set CT Date Period To.
		@param CTDatePeriodTo CT Date Period To	  */
	public void setCTDatePeriodTo (Timestamp CTDatePeriodTo)
	{
		set_Value (COLUMNNAME_CTDatePeriodTo, CTDatePeriodTo);
	}

	/** Get CT Date Period To.
		@return CT Date Period To	  */
	public Timestamp getCTDatePeriodTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CTDatePeriodTo);
	}

	/** Set CT Get Date.
		@param CTGetDate 
		Cuti Tahunan Get Date
	  */
	public void setCTGetDate (Timestamp CTGetDate)
	{
		set_Value (COLUMNNAME_CTGetDate, CTGetDate);
	}

	/** Get CT Get Date.
		@return Cuti Tahunan Get Date
	  */
	public Timestamp getCTGetDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CTGetDate);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public I_HC_Employee getHC_Employee() throws RuntimeException
    {
		return (I_HC_Employee)MTable.get(getCtx(), I_HC_Employee.Table_Name)
			.getPO(getHC_Employee_ID(), get_TrxName());	}

	/** Set Employee Data.
		@param HC_Employee_ID Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID)
	{
		if (HC_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Employee_ID, Integer.valueOf(HC_Employee_ID));
	}

	/** Get Employee Data.
		@return Employee Data	  */
	public int getHC_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employee Leave Balance.
		@param HC_EmployeeLeaveBalance_ID Employee Leave Balance	  */
	public void setHC_EmployeeLeaveBalance_ID (int HC_EmployeeLeaveBalance_ID)
	{
		if (HC_EmployeeLeaveBalance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeLeaveBalance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeLeaveBalance_ID, Integer.valueOf(HC_EmployeeLeaveBalance_ID));
	}

	/** Get Employee Leave Balance.
		@return Employee Leave Balance	  */
	public int getHC_EmployeeLeaveBalance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeLeaveBalance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_EmployeeLeaveBalance_ID()));
    }

	/** Set HC_EmployeeLeaveBalance_UU.
		@param HC_EmployeeLeaveBalance_UU HC_EmployeeLeaveBalance_UU	  */
	public void setHC_EmployeeLeaveBalance_UU (String HC_EmployeeLeaveBalance_UU)
	{
		set_Value (COLUMNNAME_HC_EmployeeLeaveBalance_UU, HC_EmployeeLeaveBalance_UU);
	}

	/** Get HC_EmployeeLeaveBalance_UU.
		@return HC_EmployeeLeaveBalance_UU	  */
	public String getHC_EmployeeLeaveBalance_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_EmployeeLeaveBalance_UU);
	}

	public I_HC_LeaveType getHC_LeaveType() throws RuntimeException
    {
		return (I_HC_LeaveType)MTable.get(getCtx(), I_HC_LeaveType.Table_Name)
			.getPO(getHC_LeaveType_ID(), get_TrxName());	}

	/** Set Leave Type.
		@param HC_LeaveType_ID Leave Type	  */
	public void setHC_LeaveType_ID (int HC_LeaveType_ID)
	{
		if (HC_LeaveType_ID < 1) 
			set_Value (COLUMNNAME_HC_LeaveType_ID, null);
		else 
			set_Value (COLUMNNAME_HC_LeaveType_ID, Integer.valueOf(HC_LeaveType_ID));
	}

	/** Get Leave Type.
		@return Leave Type	  */
	public int getHC_LeaveType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_LeaveType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leave Balance Period.
		@param LeaveBalancePeriod Leave Balance Period	  */
	public void setLeaveBalancePeriod (String LeaveBalancePeriod)
	{
		set_Value (COLUMNNAME_LeaveBalancePeriod, LeaveBalancePeriod);
	}

	/** Get Leave Balance Period.
		@return Leave Balance Period	  */
	public String getLeaveBalancePeriod () 
	{
		return (String)get_Value(COLUMNNAME_LeaveBalancePeriod);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Saldo Cuti Besar.
		@param SaldoCutiBesar 
		Saldo Cuti Besar Karyawan ybs
	  */
	public void setSaldoCutiBesar (int SaldoCutiBesar)
	{
		set_Value (COLUMNNAME_SaldoCutiBesar, Integer.valueOf(SaldoCutiBesar));
	}

	/** Get Saldo Cuti Besar.
		@return Saldo Cuti Besar Karyawan ybs
	  */
	public int getSaldoCutiBesar () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SaldoCutiBesar);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Saldo Cuti Tahunan.
		@param SaldoCutiTahunan 
		Saldo Cuti Tahunan Karyawan ybs
	  */
	public void setSaldoCutiTahunan (int SaldoCutiTahunan)
	{
		set_Value (COLUMNNAME_SaldoCutiTahunan, Integer.valueOf(SaldoCutiTahunan));
	}

	/** Get Saldo Cuti Tahunan.
		@return Saldo Cuti Tahunan Karyawan ybs
	  */
	public int getSaldoCutiTahunan () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SaldoCutiTahunan);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Used Cuti Besar.
		@param UsedCutiBesar Used Cuti Besar	  */
	public void setUsedCutiBesar (int UsedCutiBesar)
	{
		set_Value (COLUMNNAME_UsedCutiBesar, Integer.valueOf(UsedCutiBesar));
	}

	/** Get Used Cuti Besar.
		@return Used Cuti Besar	  */
	public int getUsedCutiBesar () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UsedCutiBesar);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Used Cuti Tahunan.
		@param UsedCutiTahunan Used Cuti Tahunan	  */
	public void setUsedCutiTahunan (int UsedCutiTahunan)
	{
		set_Value (COLUMNNAME_UsedCutiTahunan, Integer.valueOf(UsedCutiTahunan));
	}

	/** Get Used Cuti Tahunan.
		@return Used Cuti Tahunan	  */
	public int getUsedCutiTahunan () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UsedCutiTahunan);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}