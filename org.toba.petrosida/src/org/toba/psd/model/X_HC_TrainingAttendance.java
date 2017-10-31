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

/** Generated Model for HC_TrainingAttendance
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_TrainingAttendance extends PO implements I_HC_TrainingAttendance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171010L;

    /** Standard Constructor */
    public X_HC_TrainingAttendance (Properties ctx, int HC_TrainingAttendance_ID, String trxName)
    {
      super (ctx, HC_TrainingAttendance_ID, trxName);
      /** if (HC_TrainingAttendance_ID == 0)
        {
			setHC_Employee_ID (0);
			setHC_TrainingAttendance_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_TrainingAttendance (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_HC_TrainingAttendance[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date Start.
		@param DateStart 
		Date Start for this Order
	  */
	public void setDateStart (Timestamp DateStart)
	{
		set_ValueNoCheck (COLUMNNAME_DateStart, DateStart);
	}

	/** Get Date Start.
		@return Date Start for this Order
	  */
	public Timestamp getDateStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateStart);
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	public I_HC_Course getHC_Course() throws RuntimeException
    {
		return (I_HC_Course)MTable.get(getCtx(), I_HC_Course.Table_Name)
			.getPO(getHC_Course_ID(), get_TrxName());	}

	/** Set Training Course.
		@param HC_Course_ID Training Course	  */
	public void setHC_Course_ID (int HC_Course_ID)
	{
		if (HC_Course_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Course_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Course_ID, Integer.valueOf(HC_Course_ID));
	}

	/** Get Training Course.
		@return Training Course	  */
	public int getHC_Course_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Course_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employee Data.
		@param HC_Employee_ID Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID)
	{
		if (HC_Employee_ID < 1) 
			set_Value (COLUMNNAME_HC_Employee_ID, null);
		else 
			set_Value (COLUMNNAME_HC_Employee_ID, Integer.valueOf(HC_Employee_ID));
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

	/** Set Training Attendance.
		@param HC_TrainingAttendance_ID Training Attendance	  */
	public void setHC_TrainingAttendance_ID (int HC_TrainingAttendance_ID)
	{
		if (HC_TrainingAttendance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_TrainingAttendance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_TrainingAttendance_ID, Integer.valueOf(HC_TrainingAttendance_ID));
	}

	/** Get Training Attendance.
		@return Training Attendance	  */
	public int getHC_TrainingAttendance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_TrainingAttendance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_TrainingAttendance_ID()));
    }

	/** Set HC_TrainingAttendance_UU.
		@param HC_TrainingAttendance_UU HC_TrainingAttendance_UU	  */
	public void setHC_TrainingAttendance_UU (String HC_TrainingAttendance_UU)
	{
		set_Value (COLUMNNAME_HC_TrainingAttendance_UU, HC_TrainingAttendance_UU);
	}

	/** Get HC_TrainingAttendance_UU.
		@return HC_TrainingAttendance_UU	  */
	public String getHC_TrainingAttendance_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_TrainingAttendance_UU);
	}

	public I_HC_TrainingClass getHC_TrainingClass() throws RuntimeException
    {
		return (I_HC_TrainingClass)MTable.get(getCtx(), I_HC_TrainingClass.Table_Name)
			.getPO(getHC_TrainingClass_ID(), get_TrxName());	}

	/** Set Training Class.
		@param HC_TrainingClass_ID Training Class	  */
	public void setHC_TrainingClass_ID (int HC_TrainingClass_ID)
	{
		if (HC_TrainingClass_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_TrainingClass_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_TrainingClass_ID, Integer.valueOf(HC_TrainingClass_ID));
	}

	/** Get Training Class.
		@return Training Class	  */
	public int getHC_TrainingClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_TrainingClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cancelled.
		@param IsCancelled 
		The transaction was cancelled
	  */
	public void setIsCancelled (boolean IsCancelled)
	{
		set_ValueNoCheck (COLUMNNAME_IsCancelled, Boolean.valueOf(IsCancelled));
	}

	/** Get Cancelled.
		@return The transaction was cancelled
	  */
	public boolean isCancelled () 
	{
		Object oo = get_Value(COLUMNNAME_IsCancelled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}