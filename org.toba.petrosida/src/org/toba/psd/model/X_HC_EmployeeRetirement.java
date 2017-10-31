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
import org.taowi.hcm.core.model.I_HC_Org;

/** Generated Model for HC_EmployeeRetirement
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_EmployeeRetirement extends PO implements I_HC_EmployeeRetirement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171024L;

    /** Standard Constructor */
    public X_HC_EmployeeRetirement (Properties ctx, int HC_EmployeeRetirement_ID, String trxName)
    {
      super (ctx, HC_EmployeeRetirement_ID, trxName);
      /** if (HC_EmployeeRetirement_ID == 0)
        {
			setHC_EmployeeRetirement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_EmployeeRetirement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_EmployeeRetirement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_ValueNoCheck (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
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

	/** Set HC_EmployeeRetirement.
		@param HC_EmployeeRetirement_ID HC_EmployeeRetirement	  */
	public void setHC_EmployeeRetirement_ID (int HC_EmployeeRetirement_ID)
	{
		if (HC_EmployeeRetirement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeRetirement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeRetirement_ID, Integer.valueOf(HC_EmployeeRetirement_ID));
	}

	/** Get HC_EmployeeRetirement.
		@return HC_EmployeeRetirement	  */
	public int getHC_EmployeeRetirement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeRetirement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_EmployeeRetirement_ID()));
    }

	/** Set HC_EmployeeRetirement_UU.
		@param HC_EmployeeRetirement_UU HC_EmployeeRetirement_UU	  */
	public void setHC_EmployeeRetirement_UU (String HC_EmployeeRetirement_UU)
	{
		set_Value (COLUMNNAME_HC_EmployeeRetirement_UU, HC_EmployeeRetirement_UU);
	}

	/** Get HC_EmployeeRetirement_UU.
		@return HC_EmployeeRetirement_UU	  */
	public String getHC_EmployeeRetirement_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_EmployeeRetirement_UU);
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

	public I_HC_Org getHC_Org() throws RuntimeException
    {
		return (I_HC_Org)MTable.get(getCtx(), I_HC_Org.Table_Name)
			.getPO(getHC_Org_ID(), get_TrxName());	}

	/** Set HC Organization.
		@param HC_Org_ID HC Organization	  */
	public void setHC_Org_ID (int HC_Org_ID)
	{
		if (HC_Org_ID < 1) 
			set_Value (COLUMNNAME_HC_Org_ID, null);
		else 
			set_Value (COLUMNNAME_HC_Org_ID, Integer.valueOf(HC_Org_ID));
	}

	/** Get HC Organization.
		@return HC Organization	  */
	public int getHC_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set WorkStartDate.
		@param HC_WorkStartDate WorkStartDate	  */
	public void setHC_WorkStartDate (Timestamp HC_WorkStartDate)
	{
		set_Value (COLUMNNAME_HC_WorkStartDate, HC_WorkStartDate);
	}

	/** Get WorkStartDate.
		@return WorkStartDate	  */
	public Timestamp getHC_WorkStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_WorkStartDate);
	}

	/** Set Masa Persiapan Pensiun.
		@param MasaPersiapanPensiun Masa Persiapan Pensiun	  */
	public void setMasaPersiapanPensiun (Timestamp MasaPersiapanPensiun)
	{
		set_Value (COLUMNNAME_MasaPersiapanPensiun, MasaPersiapanPensiun);
	}

	/** Get Masa Persiapan Pensiun.
		@return Masa Persiapan Pensiun	  */
	public Timestamp getMasaPersiapanPensiun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MasaPersiapanPensiun);
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

	/** Set Pensiun Date.
		@param PensiunDate Pensiun Date	  */
	public void setPensiunDate (Timestamp PensiunDate)
	{
		set_Value (COLUMNNAME_PensiunDate, PensiunDate);
	}

	/** Get Pensiun Date.
		@return Pensiun Date	  */
	public Timestamp getPensiunDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PensiunDate);
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

	/** Set Retirement Date.
		@param RetirementDate Retirement Date	  */
	public void setRetirementDate (Timestamp RetirementDate)
	{
		set_Value (COLUMNNAME_RetirementDate, RetirementDate);
	}

	/** Get Retirement Date.
		@return Retirement Date	  */
	public Timestamp getRetirementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_RetirementDate);
	}

	/** Due to Criminal Sanction = RC */
	public static final String RETIREMENTTYPE_DueToCriminalSanction = "RC";
	/** Due to Death = RD */
	public static final String RETIREMENTTYPE_DueToDeath = "RD";
	/** Due to Disability = RA */
	public static final String RETIREMENTTYPE_DueToDisability = "RA";
	/** Pension = PE */
	public static final String RETIREMENTTYPE_Pension = "PE";
	/** Resignment = RE */
	public static final String RETIREMENTTYPE_Resignment = "RE";
	/** Set Retirement Type.
		@param RetirementType Retirement Type	  */
	public void setRetirementType (String RetirementType)
	{

		set_Value (COLUMNNAME_RetirementType, RetirementType);
	}

	/** Get Retirement Type.
		@return Retirement Type	  */
	public String getRetirementType () 
	{
		return (String)get_Value(COLUMNNAME_RetirementType);
	}

	/** Drafted = Drf */
	public static final String STATUS_Drafted = "Drf";
	/** Employee Deactivation = Edc */
	public static final String STATUS_EmployeeDeactivation = "Edc";
	/** Pension Payment = Ppm */
	public static final String STATUS_PensionPayment = "Ppm";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_ValueNoCheck (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Transaction Time.
		@param TimeTrx Transaction Time	  */
	public void setTimeTrx (Timestamp TimeTrx)
	{
		set_Value (COLUMNNAME_TimeTrx, TimeTrx);
	}

	/** Get Transaction Time.
		@return Transaction Time	  */
	public Timestamp getTimeTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TimeTrx);
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