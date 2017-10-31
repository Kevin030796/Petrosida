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
import org.taowi.hcm.core.model.I_HC_EmployeeClass;
import org.taowi.hcm.core.model.I_HC_EmployeeJob;
import org.taowi.hcm.core.model.I_HC_Org;

/** Generated Model for HC_Selection
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_HC_Selection extends PO implements I_HC_Selection, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171022L;

    /** Standard Constructor */
    public X_HC_Selection (Properties ctx, int HC_Selection_ID, String trxName)
    {
      super (ctx, HC_Selection_ID, trxName);
      /** if (HC_Selection_ID == 0)
        {
			setHC_Selection_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_Selection (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_Selection[")
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

	/** Set Catatan Evaluasi.
		@param HC_CattEvaluasi Catatan Evaluasi	  */
	public void setHC_CattEvaluasi (String HC_CattEvaluasi)
	{
		set_Value (COLUMNNAME_HC_CattEvaluasi, HC_CattEvaluasi);
	}

	/** Get Catatan Evaluasi.
		@return Catatan Evaluasi	  */
	public String getHC_CattEvaluasi () 
	{
		return (String)get_Value(COLUMNNAME_HC_CattEvaluasi);
	}

	/** Set Catatan Khusus.
		@param HC_CattKhusus Catatan Khusus	  */
	public void setHC_CattKhusus (String HC_CattKhusus)
	{
		set_Value (COLUMNNAME_HC_CattKhusus, HC_CattKhusus);
	}

	/** Get Catatan Khusus.
		@return Catatan Khusus	  */
	public String getHC_CattKhusus () 
	{
		return (String)get_Value(COLUMNNAME_HC_CattKhusus);
	}

	/** Set Catatan Medis I.
		@param HC_CattMedical1 Catatan Medis I	  */
	public void setHC_CattMedical1 (String HC_CattMedical1)
	{
		set_Value (COLUMNNAME_HC_CattMedical1, HC_CattMedical1);
	}

	/** Get Catatan Medis I.
		@return Catatan Medis I	  */
	public String getHC_CattMedical1 () 
	{
		return (String)get_Value(COLUMNNAME_HC_CattMedical1);
	}

	/** Set Catatan Medis 2.
		@param HC_CattMedical2 Catatan Medis 2	  */
	public void setHC_CattMedical2 (String HC_CattMedical2)
	{
		set_Value (COLUMNNAME_HC_CattMedical2, HC_CattMedical2);
	}

	/** Get Catatan Medis 2.
		@return Catatan Medis 2	  */
	public String getHC_CattMedical2 () 
	{
		return (String)get_Value(COLUMNNAME_HC_CattMedical2);
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

	public I_HC_EmployeeClass getHC_EmployeeClass() throws RuntimeException
    {
		return (I_HC_EmployeeClass)MTable.get(getCtx(), I_HC_EmployeeClass.Table_Name)
			.getPO(getHC_EmployeeClass_ID(), get_TrxName());	}

	/** Set Employee Class.
		@param HC_EmployeeClass_ID Employee Class	  */
	public void setHC_EmployeeClass_ID (int HC_EmployeeClass_ID)
	{
		if (HC_EmployeeClass_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeClass_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeClass_ID, Integer.valueOf(HC_EmployeeClass_ID));
	}

	/** Get Employee Class.
		@return Employee Class	  */
	public int getHC_EmployeeClass_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeClass_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_EmployeeClass getHC_EmployeeClassTo() throws RuntimeException
    {
		return (I_HC_EmployeeClass)MTable.get(getCtx(), I_HC_EmployeeClass.Table_Name)
			.getPO(getHC_EmployeeClassTo_ID(), get_TrxName());	}

	/** Set Employee Class To.
		@param HC_EmployeeClassTo_ID Employee Class To	  */
	public void setHC_EmployeeClassTo_ID (int HC_EmployeeClassTo_ID)
	{
		if (HC_EmployeeClassTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeClassTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeClassTo_ID, Integer.valueOf(HC_EmployeeClassTo_ID));
	}

	/** Get Employee Class To.
		@return Employee Class To	  */
	public int getHC_EmployeeClassTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeClassTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_EmployeeJob getHC_EmployeeJob() throws RuntimeException
    {
		return (I_HC_EmployeeJob)MTable.get(getCtx(), I_HC_EmployeeJob.Table_Name)
			.getPO(getHC_EmployeeJob_ID(), get_TrxName());	}

	/** Set Employee Job Data.
		@param HC_EmployeeJob_ID Employee Job Data	  */
	public void setHC_EmployeeJob_ID (int HC_EmployeeJob_ID)
	{
		if (HC_EmployeeJob_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeJob_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_EmployeeJob_ID, Integer.valueOf(HC_EmployeeJob_ID));
	}

	/** Get Employee Job Data.
		@return Employee Job Data	  */
	public int getHC_EmployeeJob_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeJob_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_EvalDate.
		@param HC_EvalDate HC_EvalDate	  */
	public void setHC_EvalDate (Timestamp HC_EvalDate)
	{
		set_Value (COLUMNNAME_HC_EvalDate, HC_EvalDate);
	}

	/** Get HC_EvalDate.
		@return HC_EvalDate	  */
	public Timestamp getHC_EvalDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_EvalDate);
	}

	/** Set HC_EvalScore.
		@param HC_EvalScore HC_EvalScore	  */
	public void setHC_EvalScore (String HC_EvalScore)
	{
		set_Value (COLUMNNAME_HC_EvalScore, HC_EvalScore);
	}

	/** Get HC_EvalScore.
		@return HC_EvalScore	  */
	public String getHC_EvalScore () 
	{
		return (String)get_Value(COLUMNNAME_HC_EvalScore);
	}

	/** Set HC_MedicalDate1.
		@param HC_MedicalDate1 HC_MedicalDate1	  */
	public void setHC_MedicalDate1 (Timestamp HC_MedicalDate1)
	{
		set_Value (COLUMNNAME_HC_MedicalDate1, HC_MedicalDate1);
	}

	/** Get HC_MedicalDate1.
		@return HC_MedicalDate1	  */
	public Timestamp getHC_MedicalDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_MedicalDate1);
	}

	/** Set HC_MedicalDate2.
		@param HC_MedicalDate2 HC_MedicalDate2	  */
	public void setHC_MedicalDate2 (Timestamp HC_MedicalDate2)
	{
		set_Value (COLUMNNAME_HC_MedicalDate2, HC_MedicalDate2);
	}

	/** Get HC_MedicalDate2.
		@return HC_MedicalDate2	  */
	public Timestamp getHC_MedicalDate2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_MedicalDate2);
	}

	/** Memenuhi Syarat = MS */
	public static final String HC_MEDICALSCORE1_MemenuhiSyarat = "MS";
	/** Tidak Memenuhi Syarat = TMS */
	public static final String HC_MEDICALSCORE1_TidakMemenuhiSyarat = "TMS";
	/** Set HC_MedicalScore1.
		@param HC_MedicalScore1 HC_MedicalScore1	  */
	public void setHC_MedicalScore1 (String HC_MedicalScore1)
	{

		set_Value (COLUMNNAME_HC_MedicalScore1, HC_MedicalScore1);
	}

	/** Get HC_MedicalScore1.
		@return HC_MedicalScore1	  */
	public String getHC_MedicalScore1 () 
	{
		return (String)get_Value(COLUMNNAME_HC_MedicalScore1);
	}

	/** Memenuhi Syarat = MS */
	public static final String HC_MEDICALSCORE2_MemenuhiSyarat = "MS";
	/** Tidak Memenuhi Syarat = TMS */
	public static final String HC_MEDICALSCORE2_TidakMemenuhiSyarat = "TMS";
	/** Set HC_MedicalScore2.
		@param HC_MedicalScore2 HC_MedicalScore2	  */
	public void setHC_MedicalScore2 (String HC_MedicalScore2)
	{

		set_Value (COLUMNNAME_HC_MedicalScore2, HC_MedicalScore2);
	}

	/** Get HC_MedicalScore2.
		@return HC_MedicalScore2	  */
	public String getHC_MedicalScore2 () 
	{
		return (String)get_Value(COLUMNNAME_HC_MedicalScore2);
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

	/** Set HC_PresentationDate.
		@param HC_PresentationDate HC_PresentationDate	  */
	public void setHC_PresentationDate (Timestamp HC_PresentationDate)
	{
		set_Value (COLUMNNAME_HC_PresentationDate, HC_PresentationDate);
	}

	/** Get HC_PresentationDate.
		@return HC_PresentationDate	  */
	public Timestamp getHC_PresentationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_PresentationDate);
	}

	/** Set HC_PresentationDesc.
		@param HC_PresentationDesc HC_PresentationDesc	  */
	public void setHC_PresentationDesc (String HC_PresentationDesc)
	{
		set_Value (COLUMNNAME_HC_PresentationDesc, HC_PresentationDesc);
	}

	/** Get HC_PresentationDesc.
		@return HC_PresentationDesc	  */
	public String getHC_PresentationDesc () 
	{
		return (String)get_Value(COLUMNNAME_HC_PresentationDesc);
	}

	/** Set Nilai Presentasi.
		@param HC_PresentationScore Nilai Presentasi	  */
	public void setHC_PresentationScore (String HC_PresentationScore)
	{
		set_Value (COLUMNNAME_HC_PresentationScore, HC_PresentationScore);
	}

	/** Get Nilai Presentasi.
		@return Nilai Presentasi	  */
	public String getHC_PresentationScore () 
	{
		return (String)get_Value(COLUMNNAME_HC_PresentationScore);
	}

	/** Set HC_Selection.
		@param HC_Selection_ID HC_Selection	  */
	public void setHC_Selection_ID (int HC_Selection_ID)
	{
		if (HC_Selection_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Selection_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Selection_ID, Integer.valueOf(HC_Selection_ID));
	}

	/** Get HC_Selection.
		@return HC_Selection	  */
	public int getHC_Selection_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Selection_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHC_Selection_ID()));
    }

	/** Set HC_Selection_UU.
		@param HC_Selection_UU HC_Selection_UU	  */
	public void setHC_Selection_UU (String HC_Selection_UU)
	{
		set_Value (COLUMNNAME_HC_Selection_UU, HC_Selection_UU);
	}

	/** Get HC_Selection_UU.
		@return HC_Selection_UU	  */
	public String getHC_Selection_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_Selection_UU);
	}

	/** Set Tanggal Akhir TMT.
		@param HC_TanggalAkhirTMT Tanggal Akhir TMT	  */
	public void setHC_TanggalAkhirTMT (Timestamp HC_TanggalAkhirTMT)
	{
		set_Value (COLUMNNAME_HC_TanggalAkhirTMT, HC_TanggalAkhirTMT);
	}

	/** Get Tanggal Akhir TMT.
		@return Tanggal Akhir TMT	  */
	public Timestamp getHC_TanggalAkhirTMT () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_TanggalAkhirTMT);
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

	/** Set IsCatatanKhusus.
		@param IsCatatanKhusus IsCatatanKhusus	  */
	public void setIsCatatanKhusus (boolean IsCatatanKhusus)
	{
		set_Value (COLUMNNAME_IsCatatanKhusus, Boolean.valueOf(IsCatatanKhusus));
	}

	/** Get IsCatatanKhusus.
		@return IsCatatanKhusus	  */
	public boolean isCatatanKhusus () 
	{
		Object oo = get_Value(COLUMNNAME_IsCatatanKhusus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPassMedical1.
		@param IsPassMedical1 IsPassMedical1	  */
	public void setIsPassMedical1 (boolean IsPassMedical1)
	{
		set_Value (COLUMNNAME_IsPassMedical1, Boolean.valueOf(IsPassMedical1));
	}

	/** Get IsPassMedical1.
		@return IsPassMedical1	  */
	public boolean isPassMedical1 () 
	{
		Object oo = get_Value(COLUMNNAME_IsPassMedical1);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPassMedical2.
		@param IsPassMedical2 IsPassMedical2	  */
	public void setIsPassMedical2 (boolean IsPassMedical2)
	{
		set_Value (COLUMNNAME_IsPassMedical2, Boolean.valueOf(IsPassMedical2));
	}

	/** Get IsPassMedical2.
		@return IsPassMedical2	  */
	public boolean isPassMedical2 () 
	{
		Object oo = get_Value(COLUMNNAME_IsPassMedical2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPassPresentation.
		@param IsPassPresentation IsPassPresentation	  */
	public void setIsPassPresentation (boolean IsPassPresentation)
	{
		set_Value (COLUMNNAME_IsPassPresentation, Boolean.valueOf(IsPassPresentation));
	}

	/** Get IsPassPresentation.
		@return IsPassPresentation	  */
	public boolean isPassPresentation () 
	{
		Object oo = get_Value(COLUMNNAME_IsPassPresentation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPassTheEvaluation.
		@param IsPassTheEvaluation IsPassTheEvaluation	  */
	public void setIsPassTheEvaluation (boolean IsPassTheEvaluation)
	{
		set_Value (COLUMNNAME_IsPassTheEvaluation, Boolean.valueOf(IsPassTheEvaluation));
	}

	/** Get IsPassTheEvaluation.
		@return IsPassTheEvaluation	  */
	public boolean isPassTheEvaluation () 
	{
		Object oo = get_Value(COLUMNNAME_IsPassTheEvaluation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsPresentation.
		@param IsPresentation IsPresentation	  */
	public void setIsPresentation (boolean IsPresentation)
	{
		set_Value (COLUMNNAME_IsPresentation, Boolean.valueOf(IsPresentation));
	}

	/** Get IsPresentation.
		@return IsPresentation	  */
	public boolean isPresentation () 
	{
		Object oo = get_Value(COLUMNNAME_IsPresentation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set TanggalNomorSK.
		@param TanggalNomorSK TanggalNomorSK	  */
	public void setTanggalNomorSK (Timestamp TanggalNomorSK)
	{
		set_Value (COLUMNNAME_TanggalNomorSK, TanggalNomorSK);
	}

	/** Get TanggalNomorSK.
		@return TanggalNomorSK	  */
	public Timestamp getTanggalNomorSK () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TanggalNomorSK);
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