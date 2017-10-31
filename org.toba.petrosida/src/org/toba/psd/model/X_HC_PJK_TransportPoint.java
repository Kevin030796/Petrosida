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

/** Generated Model for HC_PJK_TransportPoint
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_PJK_TransportPoint extends PO implements I_HC_PJK_TransportPoint, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171018L;

    /** Standard Constructor */
    public X_HC_PJK_TransportPoint (Properties ctx, int HC_PJK_TransportPoint_ID, String trxName)
    {
      super (ctx, HC_PJK_TransportPoint_ID, trxName);
      /** if (HC_PJK_TransportPoint_ID == 0)
        {
			setHC_PJK_TransportPoint_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_PJK_TransportPoint (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_PJK_TransportPoint[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set AddOn - Price.
		@param AddOnPrice AddOn - Price	  */
	public void setAddOnPrice (BigDecimal AddOnPrice)
	{
		set_Value (COLUMNNAME_AddOnPrice, AddOnPrice);
	}

	/** Get AddOn - Price.
		@return AddOn - Price	  */
	public BigDecimal getAddOnPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AddOnPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Address 1.
		@param Address1 
		Address line 1 for this location
	  */
	public void setAddress1 (String Address1)
	{
		set_ValueNoCheck (COLUMNNAME_Address1, Address1);
	}

	/** Get Address 1.
		@return Address line 1 for this location
	  */
	public String getAddress1 () 
	{
		return (String)get_Value(COLUMNNAME_Address1);
	}

	/** Set Address 2.
		@param Address2 
		Address line 2 for this location
	  */
	public void setAddress2 (String Address2)
	{
		set_ValueNoCheck (COLUMNNAME_Address2, Address2);
	}

	/** Get Address 2.
		@return Address line 2 for this location
	  */
	public String getAddress2 () 
	{
		return (String)get_Value(COLUMNNAME_Address2);
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

	/** Set HC_PJK_TransportPoint.
		@param HC_PJK_TransportPoint_ID HC_PJK_TransportPoint	  */
	public void setHC_PJK_TransportPoint_ID (int HC_PJK_TransportPoint_ID)
	{
		if (HC_PJK_TransportPoint_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PJK_TransportPoint_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PJK_TransportPoint_ID, Integer.valueOf(HC_PJK_TransportPoint_ID));
	}

	/** Get HC_PJK_TransportPoint.
		@return HC_PJK_TransportPoint	  */
	public int getHC_PJK_TransportPoint_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PJK_TransportPoint_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_PJK_TransportPoint_ID()));
    }

	/** Set HC_PJK_TransportPoint_UU.
		@param HC_PJK_TransportPoint_UU HC_PJK_TransportPoint_UU	  */
	public void setHC_PJK_TransportPoint_UU (String HC_PJK_TransportPoint_UU)
	{
		set_Value (COLUMNNAME_HC_PJK_TransportPoint_UU, HC_PJK_TransportPoint_UU);
	}

	/** Get HC_PJK_TransportPoint_UU.
		@return HC_PJK_TransportPoint_UU	  */
	public String getHC_PJK_TransportPoint_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_PJK_TransportPoint_UU);
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

	/** Set Time.
		@param Time1 Time	  */
	public void setTime1 (Timestamp Time1)
	{
		set_Value (COLUMNNAME_Time1, Time1);
	}

	/** Get Time.
		@return Time	  */
	public Timestamp getTime1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Time1);
	}

	/** Set Total Price.
		@param TotalPrice Total Price	  */
	public void setTotalPrice (BigDecimal TotalPrice)
	{
		set_ValueNoCheck (COLUMNNAME_TotalPrice, TotalPrice);
	}

	/** Get Total Price.
		@return Total Price	  */
	public BigDecimal getTotalPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Flight = Flight */
	public static final String TRANSPORTATIONTYPE_Flight = "Flight";
	/** Train = Train */
	public static final String TRANSPORTATIONTYPE_Train = "Train";
	/** Car Rent = Car Rent */
	public static final String TRANSPORTATIONTYPE_CarRent = "Car Rent";
	/** Set Transportation Type.
		@param TransportationType Transportation Type	  */
	public void setTransportationType (String TransportationType)
	{

		set_Value (COLUMNNAME_TransportationType, TransportationType);
	}

	/** Get Transportation Type.
		@return Transportation Type	  */
	public String getTransportationType () 
	{
		return (String)get_Value(COLUMNNAME_TransportationType);
	}

	/** Departure = Departure */
	public static final String TRAVELTYPE_Departure = "Departure";
	/** Arrival = Arrival */
	public static final String TRAVELTYPE_Arrival = "Arrival";
	/** In City = In City */
	public static final String TRAVELTYPE_InCity = "In City";
	/** Set Travel Type.
		@param TravelType Travel Type	  */
	public void setTravelType (String TravelType)
	{

		set_Value (COLUMNNAME_TravelType, TravelType);
	}

	/** Get Travel Type.
		@return Travel Type	  */
	public String getTravelType () 
	{
		return (String)get_Value(COLUMNNAME_TravelType);
	}
}