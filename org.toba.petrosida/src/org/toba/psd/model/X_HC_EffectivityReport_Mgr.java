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

/** Generated Model for HC_EffectivityReport_Mgr
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_EffectivityReport_Mgr extends PO implements I_HC_EffectivityReport_Mgr, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171022L;

    /** Standard Constructor */
    public X_HC_EffectivityReport_Mgr (Properties ctx, int HC_EffectivityReport_Mgr_ID, String trxName)
    {
      super (ctx, HC_EffectivityReport_Mgr_ID, trxName);
      /** if (HC_EffectivityReport_Mgr_ID == 0)
        {
			setHC_EffectivityReport_Mgr_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_EffectivityReport_Mgr (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_EffectivityReport_Mgr[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
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

	/** Set EvaluasiMateri1.
		@param EvaluasiMateri1 EvaluasiMateri1	  */
	public void setEvaluasiMateri1 (int EvaluasiMateri1)
	{
		set_Value (COLUMNNAME_EvaluasiMateri1, Integer.valueOf(EvaluasiMateri1));
	}

	/** Get EvaluasiMateri1.
		@return EvaluasiMateri1	  */
	public int getEvaluasiMateri1 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EvaluasiMateri1);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EvaluasiMateri2.
		@param EvaluasiMateri2 EvaluasiMateri2	  */
	public void setEvaluasiMateri2 (int EvaluasiMateri2)
	{
		set_Value (COLUMNNAME_EvaluasiMateri2, Integer.valueOf(EvaluasiMateri2));
	}

	/** Get EvaluasiMateri2.
		@return EvaluasiMateri2	  */
	public int getEvaluasiMateri2 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EvaluasiMateri2);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EvaluasiMateri3.
		@param EvaluasiMateri3 EvaluasiMateri3	  */
	public void setEvaluasiMateri3 (int EvaluasiMateri3)
	{
		set_Value (COLUMNNAME_EvaluasiMateri3, Integer.valueOf(EvaluasiMateri3));
	}

	/** Get EvaluasiMateri3.
		@return EvaluasiMateri3	  */
	public int getEvaluasiMateri3 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EvaluasiMateri3);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EvaluasiMateri4.
		@param EvaluasiMateri4 EvaluasiMateri4	  */
	public void setEvaluasiMateri4 (int EvaluasiMateri4)
	{
		set_Value (COLUMNNAME_EvaluasiMateri4, Integer.valueOf(EvaluasiMateri4));
	}

	/** Get EvaluasiMateri4.
		@return EvaluasiMateri4	  */
	public int getEvaluasiMateri4 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EvaluasiMateri4);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EvaluasiMateri5.
		@param EvaluasiMateri5 EvaluasiMateri5	  */
	public void setEvaluasiMateri5 (int EvaluasiMateri5)
	{
		set_Value (COLUMNNAME_EvaluasiMateri5, Integer.valueOf(EvaluasiMateri5));
	}

	/** Get EvaluasiMateri5.
		@return EvaluasiMateri5	  */
	public int getEvaluasiMateri5 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EvaluasiMateri5);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_EffectivityReport_Mgr.
		@param HC_EffectivityReport_Mgr_ID HC_EffectivityReport_Mgr	  */
	public void setHC_EffectivityReport_Mgr_ID (int HC_EffectivityReport_Mgr_ID)
	{
		if (HC_EffectivityReport_Mgr_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EffectivityReport_Mgr_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EffectivityReport_Mgr_ID, Integer.valueOf(HC_EffectivityReport_Mgr_ID));
	}

	/** Get HC_EffectivityReport_Mgr.
		@return HC_EffectivityReport_Mgr	  */
	public int getHC_EffectivityReport_Mgr_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EffectivityReport_Mgr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_EffectivityReport_Mgr_ID()));
    }

	/** Set HC_EffectivityReport_Mgr_UU.
		@param HC_EffectivityReport_Mgr_UU HC_EffectivityReport_Mgr_UU	  */
	public void setHC_EffectivityReport_Mgr_UU (String HC_EffectivityReport_Mgr_UU)
	{
		set_Value (COLUMNNAME_HC_EffectivityReport_Mgr_UU, HC_EffectivityReport_Mgr_UU);
	}

	/** Get HC_EffectivityReport_Mgr_UU.
		@return HC_EffectivityReport_Mgr_UU	  */
	public String getHC_EffectivityReport_Mgr_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_EffectivityReport_Mgr_UU);
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

	/** Set NIK.
		@param HC_NIK NIK	  */
	public void setHC_NIK (String HC_NIK)
	{
		set_Value (COLUMNNAME_HC_NIK, HC_NIK);
	}

	/** Get NIK.
		@return NIK	  */
	public String getHC_NIK () 
	{
		return (String)get_Value(COLUMNNAME_HC_NIK);
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

	/** Set Label_EvaluasiMateri1.
		@param Label_EvaluasiMateri1 Label_EvaluasiMateri1	  */
	public void setLabel_EvaluasiMateri1 (String Label_EvaluasiMateri1)
	{
		set_ValueNoCheck (COLUMNNAME_Label_EvaluasiMateri1, Label_EvaluasiMateri1);
	}

	/** Get Label_EvaluasiMateri1.
		@return Label_EvaluasiMateri1	  */
	public String getLabel_EvaluasiMateri1 () 
	{
		return (String)get_Value(COLUMNNAME_Label_EvaluasiMateri1);
	}

	/** Set Label_EvaluasiMateri2.
		@param Label_EvaluasiMateri2 Label_EvaluasiMateri2	  */
	public void setLabel_EvaluasiMateri2 (String Label_EvaluasiMateri2)
	{
		set_ValueNoCheck (COLUMNNAME_Label_EvaluasiMateri2, Label_EvaluasiMateri2);
	}

	/** Get Label_EvaluasiMateri2.
		@return Label_EvaluasiMateri2	  */
	public String getLabel_EvaluasiMateri2 () 
	{
		return (String)get_Value(COLUMNNAME_Label_EvaluasiMateri2);
	}

	/** Set Label_EvaluasiMateri3.
		@param Label_EvaluasiMateri3 Label_EvaluasiMateri3	  */
	public void setLabel_EvaluasiMateri3 (String Label_EvaluasiMateri3)
	{
		set_ValueNoCheck (COLUMNNAME_Label_EvaluasiMateri3, Label_EvaluasiMateri3);
	}

	/** Get Label_EvaluasiMateri3.
		@return Label_EvaluasiMateri3	  */
	public String getLabel_EvaluasiMateri3 () 
	{
		return (String)get_Value(COLUMNNAME_Label_EvaluasiMateri3);
	}

	/** Set Label_EvaluasiMateri4.
		@param Label_EvaluasiMateri4 Label_EvaluasiMateri4	  */
	public void setLabel_EvaluasiMateri4 (String Label_EvaluasiMateri4)
	{
		set_Value (COLUMNNAME_Label_EvaluasiMateri4, Label_EvaluasiMateri4);
	}

	/** Get Label_EvaluasiMateri4.
		@return Label_EvaluasiMateri4	  */
	public String getLabel_EvaluasiMateri4 () 
	{
		return (String)get_Value(COLUMNNAME_Label_EvaluasiMateri4);
	}

	/** Set Label_EvaluasiMateri5.
		@param Label_EvaluasiMateri5 Label_EvaluasiMateri5	  */
	public void setLabel_EvaluasiMateri5 (String Label_EvaluasiMateri5)
	{
		set_Value (COLUMNNAME_Label_EvaluasiMateri5, Label_EvaluasiMateri5);
	}

	/** Get Label_EvaluasiMateri5.
		@return Label_EvaluasiMateri5	  */
	public String getLabel_EvaluasiMateri5 () 
	{
		return (String)get_Value(COLUMNNAME_Label_EvaluasiMateri5);
	}
}