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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_OtherPoint
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_OtherPoint extends PO implements I_HC_OtherPoint, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171013L;

    /** Standard Constructor */
    public X_HC_OtherPoint (Properties ctx, int HC_OtherPoint_ID, String trxName)
    {
      super (ctx, HC_OtherPoint_ID, trxName);
      /** if (HC_OtherPoint_ID == 0)
        {
			setHC_OtherPoint_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_OtherPoint (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_OtherPoint[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
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

	/** Set Other Point (Multiple Other).
		@param HC_OtherPoint_ID Other Point (Multiple Other)	  */
	public void setHC_OtherPoint_ID (int HC_OtherPoint_ID)
	{
		if (HC_OtherPoint_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_OtherPoint_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_OtherPoint_ID, Integer.valueOf(HC_OtherPoint_ID));
	}

	/** Get Other Point (Multiple Other).
		@return Other Point (Multiple Other)	  */
	public int getHC_OtherPoint_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_OtherPoint_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_OtherPoint_ID()));
    }

	/** Set HC_OtherPoint_UU.
		@param HC_OtherPoint_UU HC_OtherPoint_UU	  */
	public void setHC_OtherPoint_UU (String HC_OtherPoint_UU)
	{
		set_Value (COLUMNNAME_HC_OtherPoint_UU, HC_OtherPoint_UU);
	}

	/** Get HC_OtherPoint_UU.
		@return HC_OtherPoint_UU	  */
	public String getHC_OtherPoint_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_OtherPoint_UU);
	}

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}