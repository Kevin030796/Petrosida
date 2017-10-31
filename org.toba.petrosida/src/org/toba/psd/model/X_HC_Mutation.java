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
import org.taowi.hcm.core.model.I_HC_Job;

/** Generated Model for HC_Mutation
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_Mutation extends PO implements I_HC_Mutation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170614L;

    /** Standard Constructor */
    public X_HC_Mutation (Properties ctx, int HC_Mutation_ID, String trxName)
    {
      super (ctx, HC_Mutation_ID, trxName);
      /** if (HC_Mutation_ID == 0)
        {
			setHC_Mutation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_Mutation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_Mutation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set TMT.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get TMT.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
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

	public I_HC_Job getHC_Job() throws RuntimeException
    {
		return (I_HC_Job)MTable.get(getCtx(), I_HC_Job.Table_Name)
			.getPO(getHC_Job_ID(), get_TrxName());	}

	/** Set Job.
		@param HC_Job_ID Job	  */
	public void setHC_Job_ID (int HC_Job_ID)
	{
		if (HC_Job_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Job_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Job_ID, Integer.valueOf(HC_Job_ID));
	}

	/** Get Job.
		@return Job	  */
	public int getHC_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Histori Mutasi Jabatan.
		@param HC_Mutation_ID Histori Mutasi Jabatan	  */
	public void setHC_Mutation_ID (int HC_Mutation_ID)
	{
		if (HC_Mutation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Mutation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Mutation_ID, Integer.valueOf(HC_Mutation_ID));
	}

	/** Get Histori Mutasi Jabatan.
		@return Histori Mutasi Jabatan	  */
	public int getHC_Mutation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Mutation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_Mutation_ID()));
    }

	/** Set HC_Mutation_UU.
		@param HC_Mutation_UU HC_Mutation_UU	  */
	public void setHC_Mutation_UU (String HC_Mutation_UU)
	{
		set_Value (COLUMNNAME_HC_Mutation_UU, HC_Mutation_UU);
	}

	/** Get HC_Mutation_UU.
		@return HC_Mutation_UU	  */
	public String getHC_Mutation_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Mutation_UU);
	}

	/** Set Nomor SK.
		@param NomorSK Nomor SK	  */
	public void setNomorSK (String NomorSK)
	{
		set_Value (COLUMNNAME_NomorSK, NomorSK);
	}

	/** Get Nomor SK.
		@return Nomor SK	  */
	public String getNomorSK () 
	{
		return (String)get_Value(COLUMNNAME_NomorSK);
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