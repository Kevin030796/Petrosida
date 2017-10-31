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
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_Shift
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_Shift extends PO implements I_HC_Shift, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170615L;

    /** Standard Constructor */
    public X_HC_Shift (Properties ctx, int HC_Shift_ID, String trxName)
    {
      super (ctx, HC_Shift_ID, trxName);
      /** if (HC_Shift_ID == 0)
        {
			setHC_Shift_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_Shift (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_Shift[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Shift Ends Next Day.
		@param EndNextDay Shift Ends Next Day	  */
	public void setEndNextDay (boolean EndNextDay)
	{
		set_Value (COLUMNNAME_EndNextDay, Boolean.valueOf(EndNextDay));
	}

	/** Get Shift Ends Next Day.
		@return Shift Ends Next Day	  */
	public boolean isEndNextDay () 
	{
		Object oo = get_Value(COLUMNNAME_EndNextDay);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Shift Employee.
		@param HC_Shift_ID Shift Employee	  */
	public void setHC_Shift_ID (int HC_Shift_ID)
	{
		if (HC_Shift_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Shift_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Shift_ID, Integer.valueOf(HC_Shift_ID));
	}

	/** Get Shift Employee.
		@return Shift Employee	  */
	public int getHC_Shift_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Shift_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_Shift_ID()));
    }

	/** Set HC_Shift_UU.
		@param HC_Shift_UU HC_Shift_UU	  */
	public void setHC_Shift_UU (String HC_Shift_UU)
	{
		set_Value (COLUMNNAME_HC_Shift_UU, HC_Shift_UU);
	}

	/** Get HC_Shift_UU.
		@return HC_Shift_UU	  */
	public String getHC_Shift_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Shift_UU);
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

	/** Set Shift Ends.
		@param ShiftEnds Shift Ends	  */
	public void setShiftEnds (Timestamp ShiftEnds)
	{
		set_Value (COLUMNNAME_ShiftEnds, ShiftEnds);
	}

	/** Get Shift Ends.
		@return Shift Ends	  */
	public Timestamp getShiftEnds () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShiftEnds);
	}

	/** Set Shift Start.
		@param ShiftStart Shift Start	  */
	public void setShiftStart (Timestamp ShiftStart)
	{
		set_Value (COLUMNNAME_ShiftStart, ShiftStart);
	}

	/** Get Shift Start.
		@return Shift Start	  */
	public Timestamp getShiftStart () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShiftStart);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}