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
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for HC_CourseCategory
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_CourseCategory extends PO implements I_HC_CourseCategory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170602L;

    /** Standard Constructor */
    public X_HC_CourseCategory (Properties ctx, int HC_CourseCategory_ID, String trxName)
    {
      super (ctx, HC_CourseCategory_ID, trxName);
      /** if (HC_CourseCategory_ID == 0)
        {
			setHC_CourseCategory_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HC_CourseCategory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_CourseCategory[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Course Category.
		@param HC_CourseCategory_ID Course Category	  */
	public void setHC_CourseCategory_ID (int HC_CourseCategory_ID)
	{
		if (HC_CourseCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_CourseCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_CourseCategory_ID, Integer.valueOf(HC_CourseCategory_ID));
	}

	/** Get Course Category.
		@return Course Category	  */
	public int getHC_CourseCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_CourseCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_CourseCategory_ID()));
    }

	/** Set HC_CourseCategory_UU.
		@param HC_CourseCategory_UU HC_CourseCategory_UU	  */
	public void setHC_CourseCategory_UU (String HC_CourseCategory_UU)
	{
		set_Value (COLUMNNAME_HC_CourseCategory_UU, HC_CourseCategory_UU);
	}

	/** Get HC_CourseCategory_UU.
		@return HC_CourseCategory_UU	  */
	public String getHC_CourseCategory_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_CourseCategory_UU);
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