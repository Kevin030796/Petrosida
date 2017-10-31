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
package org.toba.psd.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.taowi.hcm.core.model.I_HC_Employee;
import org.taowi.hcm.core.model.I_HC_Job;

/** Generated Model for HC_MedicalRecord
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_HC_MedicalRecord extends PO implements I_HC_MedicalRecord, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170519L;

    /** Standard Constructor */
    public X_HC_MedicalRecord (Properties ctx, int HC_MedicalRecord_ID, String trxName)
    {
      super (ctx, HC_MedicalRecord_ID, trxName);
      /** if (HC_MedicalRecord_ID == 0)
        {
			setHC_Employee_ID (0);
			setHC_MedicalRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HC_MedicalRecord (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HC_MedicalRecord[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Age.
		@param Age Age	  */
	public void setAge (BigDecimal Age)
	{
		set_Value (COLUMNNAME_Age, Age);
	}

	/** Get Age.
		@return Age	  */
	public BigDecimal getAge () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Age);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Akral.
		@param Akral Akral	  */
	public void setAkral (String Akral)
	{
		set_Value (COLUMNNAME_Akral, Akral);
	}

	/** Get Akral.
		@return Akral	  */
	public String getAkral () 
	{
		return (String)get_Value(COLUMNNAME_Akral);
	}

	/** Set Albumin.
		@param Albumin Albumin	  */
	public void setAlbumin (String Albumin)
	{
		set_Value (COLUMNNAME_Albumin, Albumin);
	}

	/** Get Albumin.
		@return Albumin	  */
	public String getAlbumin () 
	{
		return (String)get_Value(COLUMNNAME_Albumin);
	}

	/** Set Alergi.
		@param Alergi Alergi	  */
	public void setAlergi (boolean Alergi)
	{
		set_Value (COLUMNNAME_Alergi, Boolean.valueOf(Alergi));
	}

	/** Get Alergi.
		@return Alergi	  */
	public boolean isAlergi () 
	{
		Object oo = get_Value(COLUMNNAME_Alergi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Ambien.
		@param Ambien Ambien	  */
	public void setAmbien (boolean Ambien)
	{
		set_Value (COLUMNNAME_Ambien, Boolean.valueOf(Ambien));
	}

	/** Get Ambien.
		@return Ambien	  */
	public boolean isAmbien () 
	{
		Object oo = get_Value(COLUMNNAME_Ambien);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Asam Urat.
		@param AsamUrat Asam Urat	  */
	public void setAsamUrat (BigDecimal AsamUrat)
	{
		set_Value (COLUMNNAME_AsamUrat, AsamUrat);
	}

	/** Get Asam Urat.
		@return Asam Urat	  */
	public BigDecimal getAsamUrat () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AsamUrat);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asap Rokok.
		@param Asap_Rokok Asap Rokok	  */
	public void setAsap_Rokok (boolean Asap_Rokok)
	{
		set_Value (COLUMNNAME_Asap_Rokok, Boolean.valueOf(Asap_Rokok));
	}

	/** Get Asap Rokok.
		@return Asap Rokok	  */
	public boolean isAsap_Rokok () 
	{
		Object oo = get_Value(COLUMNNAME_Asap_Rokok);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Asap/Smoke,NOS.
		@param Asapor_Smoke_Nos Asap/Smoke,NOS	  */
	public void setAsapor_Smoke_Nos (boolean Asapor_Smoke_Nos)
	{
		set_Value (COLUMNNAME_Asapor_Smoke_Nos, Boolean.valueOf(Asapor_Smoke_Nos));
	}

	/** Get Asap/Smoke,NOS.
		@return Asap/Smoke,NOS	  */
	public boolean isAsapor_Smoke_Nos () 
	{
		Object oo = get_Value(COLUMNNAME_Asapor_Smoke_Nos);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Asma.
		@param Asma Asma	  */
	public void setAsma (boolean Asma)
	{
		set_Value (COLUMNNAME_Asma, Boolean.valueOf(Asma));
	}

	/** Get Asma.
		@return Asma	  */
	public boolean isAsma () 
	{
		Object oo = get_Value(COLUMNNAME_Asma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Athlete Foot(Kaki Atlit)/Ring Worm.
		@param Athlete_Foot Athlete Foot(Kaki Atlit)/Ring Worm	  */
	public void setAthlete_Foot (boolean Athlete_Foot)
	{
		set_Value (COLUMNNAME_Athlete_Foot, Boolean.valueOf(Athlete_Foot));
	}

	/** Get Athlete Foot(Kaki Atlit)/Ring Worm.
		@return Athlete Foot(Kaki Atlit)/Ring Worm	  */
	public boolean isAthlete_Foot () 
	{
		Object oo = get_Value(COLUMNNAME_Athlete_Foot);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Berat Badan.
		@param Berat_Badan Berat Badan	  */
	public void setBerat_Badan (BigDecimal Berat_Badan)
	{
		set_Value (COLUMNNAME_Berat_Badan, Berat_Badan);
	}

	/** Get Berat Badan.
		@return Berat Badan	  */
	public BigDecimal getBerat_Badan () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Berat_Badan);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Bilirubin.
		@param Bilirubin Bilirubin	  */
	public void setBilirubin (String Bilirubin)
	{
		set_Value (COLUMNNAME_Bilirubin, Bilirubin);
	}

	/** Get Bilirubin.
		@return Bilirubin	  */
	public String getBilirubin () 
	{
		return (String)get_Value(COLUMNNAME_Bilirubin);
	}

	/** Set Birthday.
		@param Birthday 
		Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	/** Set BMI.
		@param BMI BMI	  */
	public void setBMI (String BMI)
	{
		set_Value (COLUMNNAME_BMI, BMI);
	}

	/** Get BMI.
		@return BMI	  */
	public String getBMI () 
	{
		return (String)get_Value(COLUMNNAME_BMI);
	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Carboniess Paper.
		@param Carboniess_Paper Carboniess Paper	  */
	public void setCarboniess_Paper (boolean Carboniess_Paper)
	{
		set_Value (COLUMNNAME_Carboniess_Paper, Boolean.valueOf(Carboniess_Paper));
	}

	/** Get Carboniess Paper.
		@return Carboniess Paper	  */
	public boolean isCarboniess_Paper () 
	{
		Object oo = get_Value(COLUMNNAME_Carboniess_Paper);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Cedera Kepala.
		@param Cedera_Kepala Cedera Kepala	  */
	public void setCedera_Kepala (boolean Cedera_Kepala)
	{
		set_Value (COLUMNNAME_Cedera_Kepala, Boolean.valueOf(Cedera_Kepala));
	}

	/** Get Cedera Kepala.
		@return Cedera Kepala	  */
	public boolean isCedera_Kepala () 
	{
		Object oo = get_Value(COLUMNNAME_Cedera_Kepala);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Cholesterol.
		@param Cholesterol Cholesterol	  */
	public void setCholesterol (BigDecimal Cholesterol)
	{
		set_Value (COLUMNNAME_Cholesterol, Cholesterol);
	}

	/** Get Cholesterol.
		@return Cholesterol	  */
	public BigDecimal getCholesterol () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cholesterol);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Cholinesterase.
		@param Cholinesterase Cholinesterase	  */
	public void setCholinesterase (BigDecimal Cholinesterase)
	{
		set_Value (COLUMNNAME_Cholinesterase, Cholinesterase);
	}

	/** Get Cholinesterase.
		@return Cholinesterase	  */
	public BigDecimal getCholinesterase () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cholinesterase);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Creatinin.
		@param Creatinin Creatinin	  */
	public void setCreatinin (BigDecimal Creatinin)
	{
		set_Value (COLUMNNAME_Creatinin, Creatinin);
	}

	/** Get Creatinin.
		@return Creatinin	  */
	public BigDecimal getCreatinin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Creatinin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Debu/Dust,NOS.
		@param Debu_or_Dust_Nos Debu/Dust,NOS	  */
	public void setDebu_or_Dust_Nos (boolean Debu_or_Dust_Nos)
	{
		set_Value (COLUMNNAME_Debu_or_Dust_Nos, Boolean.valueOf(Debu_or_Dust_Nos));
	}

	/** Get Debu/Dust,NOS.
		@return Debu/Dust,NOS	  */
	public boolean isDebu_or_Dust_Nos () 
	{
		Object oo = get_Value(COLUMNNAME_Debu_or_Dust_Nos);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Debu Tumbuhan.
		@param Debu_Tumbuhan Debu Tumbuhan	  */
	public void setDebu_Tumbuhan (boolean Debu_Tumbuhan)
	{
		set_Value (COLUMNNAME_Debu_Tumbuhan, Boolean.valueOf(Debu_Tumbuhan));
	}

	/** Get Debu Tumbuhan.
		@return Debu Tumbuhan	  */
	public boolean isDebu_Tumbuhan () 
	{
		Object oo = get_Value(COLUMNNAME_Debu_Tumbuhan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Demam Thypoid.
		@param Demam_Thypoid Demam Thypoid	  */
	public void setDemam_Thypoid (boolean Demam_Thypoid)
	{
		set_Value (COLUMNNAME_Demam_Thypoid, Boolean.valueOf(Demam_Thypoid));
	}

	/** Get Demam Thypoid.
		@return Demam Thypoid	  */
	public boolean isDemam_Thypoid () 
	{
		Object oo = get_Value(COLUMNNAME_Demam_Thypoid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Diabetes.
		@param Diabetes Diabetes	  */
	public void setDiabetes (boolean Diabetes)
	{
		set_Value (COLUMNNAME_Diabetes, Boolean.valueOf(Diabetes));
	}

	/** Get Diabetes.
		@return Diabetes	  */
	public boolean isDiabetes () 
	{
		Object oo = get_Value(COLUMNNAME_Diabetes);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Diare Kronik.
		@param Diare_Kronik Diare Kronik	  */
	public void setDiare_Kronik (boolean Diare_Kronik)
	{
		set_Value (COLUMNNAME_Diare_Kronik, Boolean.valueOf(Diare_Kronik));
	}

	/** Get Diare Kronik.
		@return Diare Kronik	  */
	public boolean isDiare_Kronik () 
	{
		Object oo = get_Value(COLUMNNAME_Diare_Kronik);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Diastole.
		@param Diastole Diastole	  */
	public void setDiastole (BigDecimal Diastole)
	{
		set_Value (COLUMNNAME_Diastole, Diastole);
	}

	/** Get Diastole.
		@return Diastole	  */
	public BigDecimal getDiastole () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Diastole);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set DiffCount.
		@param Diffcount DiffCount	  */
	public void setDiffcount (String Diffcount)
	{
		set_Value (COLUMNNAME_Diffcount, Diffcount);
	}

	/** Get DiffCount.
		@return DiffCount	  */
	public String getDiffcount () 
	{
		return (String)get_Value(COLUMNNAME_Diffcount);
	}

	/** Set duaJamPP.
		@param DuaJamPP duaJamPP	  */
	public void setDuaJamPP (BigDecimal DuaJamPP)
	{
		set_Value (COLUMNNAME_DuaJamPP, DuaJamPP);
	}

	/** Get duaJamPP.
		@return duaJamPP	  */
	public BigDecimal getDuaJamPP () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DuaJamPP);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ECG.
		@param ECG ECG	  */
	public void setECG (String ECG)
	{
		set_Value (COLUMNNAME_ECG, ECG);
	}

	/** Get ECG.
		@return ECG	  */
	public String getECG () 
	{
		return (String)get_Value(COLUMNNAME_ECG);
	}

	/** Set Erithrosit.
		@param Erithrosit Erithrosit	  */
	public void setErithrosit (BigDecimal Erithrosit)
	{
		set_Value (COLUMNNAME_Erithrosit, Erithrosit);
	}

	/** Get Erithrosit.
		@return Erithrosit	  */
	public BigDecimal getErithrosit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Erithrosit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Faktor Ergonomis.
		@param Faktor_Ergonomis Faktor Ergonomis	  */
	public void setFaktor_Ergonomis (boolean Faktor_Ergonomis)
	{
		set_Value (COLUMNNAME_Faktor_Ergonomis, Boolean.valueOf(Faktor_Ergonomis));
	}

	/** Get Faktor Ergonomis.
		@return Faktor Ergonomis	  */
	public boolean isFaktor_Ergonomis () 
	{
		Object oo = get_Value(COLUMNNAME_Faktor_Ergonomis);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Formaldehye.
		@param Formaldehye Formaldehye	  */
	public void setFormaldehye (boolean Formaldehye)
	{
		set_Value (COLUMNNAME_Formaldehye, Boolean.valueOf(Formaldehye));
	}

	/** Get Formaldehye.
		@return Formaldehye	  */
	public boolean isFormaldehye () 
	{
		Object oo = get_Value(COLUMNNAME_Formaldehye);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Foto Thorax.
		@param Foto_Thorax Foto Thorax	  */
	public void setFoto_Thorax (String Foto_Thorax)
	{
		set_Value (COLUMNNAME_Foto_Thorax, Foto_Thorax);
	}

	/** Get Foto Thorax.
		@return Foto Thorax	  */
	public String getFoto_Thorax () 
	{
		return (String)get_Value(COLUMNNAME_Foto_Thorax);
	}

	/** Set Gangguan Ginjal/Batu Ginjal.
		@param Gangguan_GinjalOr_Batu_Ginjal Gangguan Ginjal/Batu Ginjal	  */
	public void setGangguan_GinjalOr_Batu_Ginjal (boolean Gangguan_GinjalOr_Batu_Ginjal)
	{
		set_Value (COLUMNNAME_Gangguan_GinjalOr_Batu_Ginjal, Boolean.valueOf(Gangguan_GinjalOr_Batu_Ginjal));
	}

	/** Get Gangguan Ginjal/Batu Ginjal.
		@return Gangguan Ginjal/Batu Ginjal	  */
	public boolean isGangguan_GinjalOr_Batu_Ginjal () 
	{
		Object oo = get_Value(COLUMNNAME_Gangguan_GinjalOr_Batu_Ginjal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gangguan Haid.
		@param Gangguan_Haid Gangguan Haid	  */
	public void setGangguan_Haid (boolean Gangguan_Haid)
	{
		set_Value (COLUMNNAME_Gangguan_Haid, Boolean.valueOf(Gangguan_Haid));
	}

	/** Get Gangguan Haid.
		@return Gangguan Haid	  */
	public boolean isGangguan_Haid () 
	{
		Object oo = get_Value(COLUMNNAME_Gangguan_Haid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gangguan Kebidanan.
		@param Gangguan_Kebidanan Gangguan Kebidanan	  */
	public void setGangguan_Kebidanan (boolean Gangguan_Kebidanan)
	{
		set_Value (COLUMNNAME_Gangguan_Kebidanan, Boolean.valueOf(Gangguan_Kebidanan));
	}

	/** Get Gangguan Kebidanan.
		@return Gangguan Kebidanan	  */
	public boolean isGangguan_Kebidanan () 
	{
		Object oo = get_Value(COLUMNNAME_Gangguan_Kebidanan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gangguan Pendengaran.
		@param Gangguan_Pendengaran Gangguan Pendengaran	  */
	public void setGangguan_Pendengaran (boolean Gangguan_Pendengaran)
	{
		set_Value (COLUMNNAME_Gangguan_Pendengaran, Boolean.valueOf(Gangguan_Pendengaran));
	}

	/** Get Gangguan Pendengaran.
		@return Gangguan Pendengaran	  */
	public boolean isGangguan_Pendengaran () 
	{
		Object oo = get_Value(COLUMNNAME_Gangguan_Pendengaran);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gangguan Penglihatan.
		@param Gangguan_Penglihatan Gangguan Penglihatan	  */
	public void setGangguan_Penglihatan (boolean Gangguan_Penglihatan)
	{
		set_Value (COLUMNNAME_Gangguan_Penglihatan, Boolean.valueOf(Gangguan_Penglihatan));
	}

	/** Get Gangguan Penglihatan.
		@return Gangguan Penglihatan	  */
	public boolean isGangguan_Penglihatan () 
	{
		Object oo = get_Value(COLUMNNAME_Gangguan_Penglihatan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gas Alam.
		@param Gas_Alam Gas Alam	  */
	public void setGas_Alam (boolean Gas_Alam)
	{
		set_Value (COLUMNNAME_Gas_Alam, Boolean.valueOf(Gas_Alam));
	}

	/** Get Gas Alam.
		@return Gas Alam	  */
	public boolean isGas_Alam () 
	{
		Object oo = get_Value(COLUMNNAME_Gas_Alam);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gatal Gatal/Alergi.
		@param GatalGatalOrAlergi Gatal Gatal/Alergi	  */
	public void setGatalGatalOrAlergi (boolean GatalGatalOrAlergi)
	{
		set_Value (COLUMNNAME_GatalGatalOrAlergi, Boolean.valueOf(GatalGatalOrAlergi));
	}

	/** Get Gatal Gatal/Alergi.
		@return Gatal Gatal/Alergi	  */
	public boolean isGatalGatalOrAlergi () 
	{
		Object oo = get_Value(COLUMNNAME_GatalGatalOrAlergi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gerak Dada.
		@param Gerak_Dada Gerak Dada	  */
	public void setGerak_Dada (String Gerak_Dada)
	{
		set_Value (COLUMNNAME_Gerak_Dada, Gerak_Dada);
	}

	/** Get Gerak Dada.
		@return Gerak Dada	  */
	public String getGerak_Dada () 
	{
		return (String)get_Value(COLUMNNAME_Gerak_Dada);
	}

	/** Set Gigi Lubang.
		@param Gigi_Lubang Gigi Lubang	  */
	public void setGigi_Lubang (boolean Gigi_Lubang)
	{
		set_Value (COLUMNNAME_Gigi_Lubang, Boolean.valueOf(Gigi_Lubang));
	}

	/** Get Gigi Lubang.
		@return Gigi Lubang	  */
	public boolean isGigi_Lubang () 
	{
		Object oo = get_Value(COLUMNNAME_Gigi_Lubang);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Gigitan Nyamuk.
		@param Gigitan_Nyamuk Gigitan Nyamuk	  */
	public void setGigitan_Nyamuk (boolean Gigitan_Nyamuk)
	{
		set_Value (COLUMNNAME_Gigitan_Nyamuk, Boolean.valueOf(Gigitan_Nyamuk));
	}

	/** Get Gigitan Nyamuk.
		@return Gigitan Nyamuk	  */
	public boolean isGigitan_Nyamuk () 
	{
		Object oo = get_Value(COLUMNNAME_Gigitan_Nyamuk);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Ginjal Kanan.
		@param Ginjal_Kanan Ginjal Kanan	  */
	public void setGinjal_Kanan (String Ginjal_Kanan)
	{
		set_Value (COLUMNNAME_Ginjal_Kanan, Ginjal_Kanan);
	}

	/** Get Ginjal Kanan.
		@return Ginjal Kanan	  */
	public String getGinjal_Kanan () 
	{
		return (String)get_Value(COLUMNNAME_Ginjal_Kanan);
	}

	/** Set Ginjal Kiri.
		@param Ginjal_Kiri Ginjal Kiri	  */
	public void setGinjal_Kiri (String Ginjal_Kiri)
	{
		set_Value (COLUMNNAME_Ginjal_Kiri, Ginjal_Kiri);
	}

	/** Get Ginjal Kiri.
		@return Ginjal Kiri	  */
	public String getGinjal_Kiri () 
	{
		return (String)get_Value(COLUMNNAME_Ginjal_Kiri);
	}

	/** Set HBsAG.
		@param HBsAG HBsAG	  */
	public void setHBsAG (BigDecimal HBsAG)
	{
		set_Value (COLUMNNAME_HBsAG, HBsAG);
	}

	/** Get HBsAG.
		@return HBsAG	  */
	public BigDecimal getHBsAG () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HBsAG);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** HC_Gender AD_Reference_ID=300055 */
	public static final int HC_GENDER_AD_Reference_ID=300055;
	/** Male = M */
	public static final String HC_GENDER_Male = "M";
	/** Female = F */
	public static final String HC_GENDER_Female = "F";
	/** Set Gender.
		@param HC_Gender Gender	  */
	public void setHC_Gender (String HC_Gender)
	{

		set_Value (COLUMNNAME_HC_Gender, HC_Gender);
	}

	/** Get Gender.
		@return Gender	  */
	public String getHC_Gender () 
	{
		return (String)get_Value(COLUMNNAME_HC_Gender);
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

	/** Set Medical Record.
		@param HC_MedicalRecord_ID Medical Record	  */
	public void setHC_MedicalRecord_ID (int HC_MedicalRecord_ID)
	{
		if (HC_MedicalRecord_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_MedicalRecord_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_MedicalRecord_ID, Integer.valueOf(HC_MedicalRecord_ID));
	}

	/** Get Medical Record.
		@return Medical Record	  */
	public int getHC_MedicalRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_MedicalRecord_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HC_MedicalRecord_UU.
		@param HC_MedicalRecord_UU HC_MedicalRecord_UU	  */
	public void setHC_MedicalRecord_UU (String HC_MedicalRecord_UU)
	{
		set_ValueNoCheck (COLUMNNAME_HC_MedicalRecord_UU, HC_MedicalRecord_UU);
	}

	/** Get HC_MedicalRecord_UU.
		@return HC_MedicalRecord_UU	  */
	public String getHC_MedicalRecord_UU () 
	{
		return (String)get_Value(COLUMNNAME_HC_MedicalRecord_UU);
	}

	/** Set Hematocrit.
		@param Hematocrit Hematocrit	  */
	public void setHematocrit (BigDecimal Hematocrit)
	{
		set_Value (COLUMNNAME_Hematocrit, Hematocrit);
	}

	/** Get Hematocrit.
		@return Hematocrit	  */
	public BigDecimal getHematocrit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Hematocrit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Hemoglobin.
		@param Hemoglobin Hemoglobin	  */
	public void setHemoglobin (BigDecimal Hemoglobin)
	{
		set_Value (COLUMNNAME_Hemoglobin, Hemoglobin);
	}

	/** Get Hemoglobin.
		@return Hemoglobin	  */
	public BigDecimal getHemoglobin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Hemoglobin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Hepatitis/Sakit Kuning.
		@param Hepatitisor_Sakit_Kuning Hepatitis/Sakit Kuning	  */
	public void setHepatitisor_Sakit_Kuning (boolean Hepatitisor_Sakit_Kuning)
	{
		set_Value (COLUMNNAME_Hepatitisor_Sakit_Kuning, Boolean.valueOf(Hepatitisor_Sakit_Kuning));
	}

	/** Get Hepatitis/Sakit Kuning.
		@return Hepatitis/Sakit Kuning	  */
	public boolean isHepatitisor_Sakit_Kuning () 
	{
		Object oo = get_Value(COLUMNNAME_Hepatitisor_Sakit_Kuning);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Hidung/Nose Konka.
		@param HidungOrnose_Konka Hidung/Nose Konka	  */
	public void setHidungOrnose_Konka (String HidungOrnose_Konka)
	{
		set_Value (COLUMNNAME_HidungOrnose_Konka, HidungOrnose_Konka);
	}

	/** Get Hidung/Nose Konka.
		@return Hidung/Nose Konka	  */
	public String getHidungOrnose_Konka () 
	{
		return (String)get_Value(COLUMNNAME_HidungOrnose_Konka);
	}

	/** Set Hidung/Nose Septum.
		@param Hidungornose_septum Hidung/Nose Septum	  */
	public void setHidungornose_septum (String Hidungornose_septum)
	{
		set_Value (COLUMNNAME_Hidungornose_septum, Hidungornose_septum);
	}

	/** Get Hidung/Nose Septum.
		@return Hidung/Nose Septum	  */
	public String getHidungornose_septum () 
	{
		return (String)get_Value(COLUMNNAME_Hidungornose_septum);
	}

	/** Set Hipertensi.
		@param Hipertensi Hipertensi	  */
	public void setHipertensi (boolean Hipertensi)
	{
		set_Value (COLUMNNAME_Hipertensi, Boolean.valueOf(Hipertensi));
	}

	/** Get Hipertensi.
		@return Hipertensi	  */
	public boolean isHipertensi () 
	{
		Object oo = get_Value(COLUMNNAME_Hipertensi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Hydro Carbons.
		@param Hydrocarbons Hydro Carbons	  */
	public void setHydrocarbons (boolean Hydrocarbons)
	{
		set_Value (COLUMNNAME_Hydrocarbons, Boolean.valueOf(Hydrocarbons));
	}

	/** Get Hydro Carbons.
		@return Hydro Carbons	  */
	public boolean isHydrocarbons () 
	{
		Object oo = get_Value(COLUMNNAME_Hydrocarbons);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Hydrogen Sulfide.
		@param Hydrogen_Sulfide Hydrogen Sulfide	  */
	public void setHydrogen_Sulfide (boolean Hydrogen_Sulfide)
	{
		set_Value (COLUMNNAME_Hydrogen_Sulfide, Boolean.valueOf(Hydrogen_Sulfide));
	}

	/** Get Hydrogen Sulfide.
		@return Hydrogen Sulfide	  */
	public boolean isHydrogen_Sulfide () 
	{
		Object oo = get_Value(COLUMNNAME_Hydrogen_Sulfide);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Infeksi Saluran Kencing.
		@param Infeksi_Saluran_Kencing Infeksi Saluran Kencing	  */
	public void setInfeksi_Saluran_Kencing (boolean Infeksi_Saluran_Kencing)
	{
		set_Value (COLUMNNAME_Infeksi_Saluran_Kencing, Boolean.valueOf(Infeksi_Saluran_Kencing));
	}

	/** Get Infeksi Saluran Kencing.
		@return Infeksi Saluran Kencing	  */
	public boolean isInfeksi_Saluran_Kencing () 
	{
		Object oo = get_Value(COLUMNNAME_Infeksi_Saluran_Kencing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Jantung Irama.
		@param Jantung_Irama Jantung Irama	  */
	public void setJantung_Irama (String Jantung_Irama)
	{
		set_Value (COLUMNNAME_Jantung_Irama, Jantung_Irama);
	}

	/** Get Jantung Irama.
		@return Jantung Irama	  */
	public String getJantung_Irama () 
	{
		return (String)get_Value(COLUMNNAME_Jantung_Irama);
	}

	/** Set Jantung Suara.
		@param Jantung_Suara Jantung Suara	  */
	public void setJantung_Suara (String Jantung_Suara)
	{
		set_Value (COLUMNNAME_Jantung_Suara, Jantung_Suara);
	}

	/** Get Jantung Suara.
		@return Jantung Suara	  */
	public String getJantung_Suara () 
	{
		return (String)get_Value(COLUMNNAME_Jantung_Suara);
	}

	/** Set Jatuh.
		@param Jatuh Jatuh	  */
	public void setJatuh (boolean Jatuh)
	{
		set_Value (COLUMNNAME_Jatuh, Boolean.valueOf(Jatuh));
	}

	/** Get Jatuh.
		@return Jatuh	  */
	public boolean isJatuh () 
	{
		Object oo = get_Value(COLUMNNAME_Jatuh);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Jugular Venous Pressure.
		@param Jugular_Venous_Pressure Jugular Venous Pressure	  */
	public void setJugular_Venous_Pressure (String Jugular_Venous_Pressure)
	{
		set_Value (COLUMNNAME_Jugular_Venous_Pressure, Jugular_Venous_Pressure);
	}

	/** Get Jugular Venous Pressure.
		@return Jugular Venous Pressure	  */
	public String getJugular_Venous_Pressure () 
	{
		return (String)get_Value(COLUMNNAME_Jugular_Venous_Pressure);
	}

	/** Set Kadar Kolesterol Tinggi.
		@param Kadar_Kolesterol_Tinggi Kadar Kolesterol Tinggi	  */
	public void setKadar_Kolesterol_Tinggi (boolean Kadar_Kolesterol_Tinggi)
	{
		set_Value (COLUMNNAME_Kadar_Kolesterol_Tinggi, Boolean.valueOf(Kadar_Kolesterol_Tinggi));
	}

	/** Get Kadar Kolesterol Tinggi.
		@return Kadar Kolesterol Tinggi	  */
	public boolean isKadar_Kolesterol_Tinggi () 
	{
		Object oo = get_Value(COLUMNNAME_Kadar_Kolesterol_Tinggi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kanker.
		@param Kanker Kanker	  */
	public void setKanker (boolean Kanker)
	{
		set_Value (COLUMNNAME_Kanker, Boolean.valueOf(Kanker));
	}

	/** Get Kanker.
		@return Kanker	  */
	public boolean isKanker () 
	{
		Object oo = get_Value(COLUMNNAME_Kanker);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Karbon Hitam.
		@param Karbon_Hitam Karbon Hitam	  */
	public void setKarbon_Hitam (boolean Karbon_Hitam)
	{
		set_Value (COLUMNNAME_Karbon_Hitam, Boolean.valueOf(Karbon_Hitam));
	}

	/** Get Karbon Hitam.
		@return Karbon Hitam	  */
	public boolean isKarbon_Hitam () 
	{
		Object oo = get_Value(COLUMNNAME_Karbon_Hitam);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Keguguran.
		@param Keguguran Keguguran	  */
	public void setKeguguran (boolean Keguguran)
	{
		set_Value (COLUMNNAME_Keguguran, Boolean.valueOf(Keguguran));
	}

	/** Get Keguguran.
		@return Keguguran	  */
	public boolean isKeguguran () 
	{
		Object oo = get_Value(COLUMNNAME_Keguguran);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kehamilan.
		@param Kehamilan Kehamilan	  */
	public void setKehamilan (boolean Kehamilan)
	{
		set_Value (COLUMNNAME_Kehamilan, Boolean.valueOf(Kehamilan));
	}

	/** Get Kehamilan.
		@return Kehamilan	  */
	public boolean isKehamilan () 
	{
		Object oo = get_Value(COLUMNNAME_Kehamilan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kehilangan/Bertambah Berat Badan Dalam Waktu Singkat.
		@param Kehilangan_Orbertambah_Bb Kehilangan/Bertambah Berat Badan Dalam Waktu Singkat	  */
	public void setKehilangan_Orbertambah_Bb (boolean Kehilangan_Orbertambah_Bb)
	{
		set_Value (COLUMNNAME_Kehilangan_Orbertambah_Bb, Boolean.valueOf(Kehilangan_Orbertambah_Bb));
	}

	/** Get Kehilangan/Bertambah Berat Badan Dalam Waktu Singkat.
		@return Kehilangan/Bertambah Berat Badan Dalam Waktu Singkat	  */
	public boolean isKehilangan_Orbertambah_Bb () 
	{
		Object oo = get_Value(COLUMNNAME_Kehilangan_Orbertambah_Bb);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kekurangan Oxygen/Hypoxia.
		@param Kekurangan_Oxygen_Or_Hypoxia Kekurangan Oxygen/Hypoxia	  */
	public void setKekurangan_Oxygen_Or_Hypoxia (boolean Kekurangan_Oxygen_Or_Hypoxia)
	{
		set_Value (COLUMNNAME_Kekurangan_Oxygen_Or_Hypoxia, Boolean.valueOf(Kekurangan_Oxygen_Or_Hypoxia));
	}

	/** Get Kekurangan Oxygen/Hypoxia.
		@return Kekurangan Oxygen/Hypoxia	  */
	public boolean isKekurangan_Oxygen_Or_Hypoxia () 
	{
		Object oo = get_Value(COLUMNNAME_Kekurangan_Oxygen_Or_Hypoxia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kelembapan Tinggi.
		@param Kelembapan_Tinggi Kelembapan Tinggi	  */
	public void setKelembapan_Tinggi (boolean Kelembapan_Tinggi)
	{
		set_Value (COLUMNNAME_Kelembapan_Tinggi, Boolean.valueOf(Kelembapan_Tinggi));
	}

	/** Get Kelembapan Tinggi.
		@return Kelembapan Tinggi	  */
	public boolean isKelembapan_Tinggi () 
	{
		Object oo = get_Value(COLUMNNAME_Kelembapan_Tinggi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kelenjar Getah Bening.
		@param Kelenjar_Getah_Bening Kelenjar Getah Bening	  */
	public void setKelenjar_Getah_Bening (String Kelenjar_Getah_Bening)
	{
		set_Value (COLUMNNAME_Kelenjar_Getah_Bening, Kelenjar_Getah_Bening);
	}

	/** Get Kelenjar Getah Bening.
		@return Kelenjar Getah Bening	  */
	public String getKelenjar_Getah_Bening () 
	{
		return (String)get_Value(COLUMNNAME_Kelenjar_Getah_Bening);
	}

	/** Set Ketthorax.
		@param Ketthorax Ketthorax	  */
	public void setKetthorax (String Ketthorax)
	{
		set_Value (COLUMNNAME_Ketthorax, Ketthorax);
	}

	/** Get Ketthorax.
		@return Ketthorax	  */
	public String getKetthorax () 
	{
		return (String)get_Value(COLUMNNAME_Ketthorax);
	}

	/** Set Kimia.
		@param Kimia Kimia	  */
	public void setKimia (boolean Kimia)
	{
		set_Value (COLUMNNAME_Kimia, Boolean.valueOf(Kimia));
	}

	/** Get Kimia.
		@return Kimia	  */
	public boolean isKimia () 
	{
		Object oo = get_Value(COLUMNNAME_Kimia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kondisi Umum.
		@param Kondisi_Umum Kondisi Umum	  */
	public void setKondisi_Umum (String Kondisi_Umum)
	{
		set_Value (COLUMNNAME_Kondisi_Umum, Kondisi_Umum);
	}

	/** Get Kondisi Umum.
		@return Kondisi Umum	  */
	public String getKondisi_Umum () 
	{
		return (String)get_Value(COLUMNNAME_Kondisi_Umum);
	}

	/** Set Konjungtivitis Alergi.
		@param Konjungtivitis_Alergi Konjungtivitis Alergi	  */
	public void setKonjungtivitis_Alergi (boolean Konjungtivitis_Alergi)
	{
		set_Value (COLUMNNAME_Konjungtivitis_Alergi, Boolean.valueOf(Konjungtivitis_Alergi));
	}

	/** Get Konjungtivitis Alergi.
		@return Konjungtivitis Alergi	  */
	public boolean isKonjungtivitis_Alergi () 
	{
		Object oo = get_Value(COLUMNNAME_Konjungtivitis_Alergi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kontrasepsi.
		@param Kontrasepsi Kontrasepsi	  */
	public void setKontrasepsi (boolean Kontrasepsi)
	{
		set_Value (COLUMNNAME_Kontrasepsi, Boolean.valueOf(Kontrasepsi));
	}

	/** Get Kontrasepsi.
		@return Kontrasepsi	  */
	public boolean isKontrasepsi () 
	{
		Object oo = get_Value(COLUMNNAME_Kontrasepsi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lain Lain.
		@param LainLain Lain Lain	  */
	public void setLainLain (String LainLain)
	{
		set_Value (COLUMNNAME_LainLain, LainLain);
	}

	/** Get Lain Lain.
		@return Lain Lain	  */
	public String getLainLain () 
	{
		return (String)get_Value(COLUMNNAME_LainLain);
	}

	/** Set Lain Lain.
		@param LainLainCentang Lain Lain	  */
	public void setLainLainCentang (boolean LainLainCentang)
	{
		set_Value (COLUMNNAME_LainLainCentang, Boolean.valueOf(LainLainCentang));
	}

	/** Get Lain Lain.
		@return Lain Lain	  */
	public boolean isLainLainCentang () 
	{
		Object oo = get_Value(COLUMNNAME_LainLainCentang);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Led 1.
		@param Led1 Led 1	  */
	public void setLed1 (BigDecimal Led1)
	{
		set_Value (COLUMNNAME_Led1, Led1);
	}

	/** Get Led 1.
		@return Led 1	  */
	public BigDecimal getLed1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Led1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Led 2.
		@param Led2 Led 2	  */
	public void setLed2 (BigDecimal Led2)
	{
		set_Value (COLUMNNAME_Led2, Led2);
	}

	/** Get Led 2.
		@return Led 2	  */
	public BigDecimal getLed2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Led2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Lekosit.
		@param Lekosit Lekosit	  */
	public void setLekosit (BigDecimal Lekosit)
	{
		set_Value (COLUMNNAME_Lekosit, Lekosit);
	}

	/** Get Lekosit.
		@return Lekosit	  */
	public BigDecimal getLekosit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Lekosit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Lien/Limpa.
		@param LienorLimpa Lien/Limpa	  */
	public void setLienorLimpa (String LienorLimpa)
	{
		set_Value (COLUMNNAME_LienorLimpa, LienorLimpa);
	}

	/** Get Lien/Limpa.
		@return Lien/Limpa	  */
	public String getLienorLimpa () 
	{
		return (String)get_Value(COLUMNNAME_LienorLimpa);
	}

	/** Set Liver/Hati.
		@param LiverorHati Liver/Hati	  */
	public void setLiverorHati (String LiverorHati)
	{
		set_Value (COLUMNNAME_LiverorHati, LiverorHati);
	}

	/** Get Liver/Hati.
		@return Liver/Hati	  */
	public String getLiverorHati () 
	{
		return (String)get_Value(COLUMNNAME_LiverorHati);
	}

	/** Set Malaria.
		@param Malaria Malaria	  */
	public void setMalaria (boolean Malaria)
	{
		set_Value (COLUMNNAME_Malaria, Boolean.valueOf(Malaria));
	}

	/** Get Malaria.
		@return Malaria	  */
	public boolean isMalaria () 
	{
		Object oo = get_Value(COLUMNNAME_Malaria);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mata Anemia.
		@param Mata_Anemia Mata Anemia	  */
	public void setMata_Anemia (String Mata_Anemia)
	{
		set_Value (COLUMNNAME_Mata_Anemia, Mata_Anemia);
	}

	/** Get Mata Anemia.
		@return Mata Anemia	  */
	public String getMata_Anemia () 
	{
		return (String)get_Value(COLUMNNAME_Mata_Anemia);
	}

	/** Set Mata Icterus.
		@param Mata_Icterus Mata Icterus	  */
	public void setMata_Icterus (String Mata_Icterus)
	{
		set_Value (COLUMNNAME_Mata_Icterus, Mata_Icterus);
	}

	/** Get Mata Icterus.
		@return Mata Icterus	  */
	public String getMata_Icterus () 
	{
		return (String)get_Value(COLUMNNAME_Mata_Icterus);
	}

	/** Set Material Indusri.
		@param Material_Industri Material Indusri	  */
	public void setMaterial_Industri (boolean Material_Industri)
	{
		set_Value (COLUMNNAME_Material_Industri, Boolean.valueOf(Material_Industri));
	}

	/** Get Material Indusri.
		@return Material Indusri	  */
	public boolean isMaterial_Industri () 
	{
		Object oo = get_Value(COLUMNNAME_Material_Industri);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Menggunakan Keyboard.
		@param Menggunakan_Keyboard Menggunakan Keyboard	  */
	public void setMenggunakan_Keyboard (boolean Menggunakan_Keyboard)
	{
		set_Value (COLUMNNAME_Menggunakan_Keyboard, Boolean.valueOf(Menggunakan_Keyboard));
	}

	/** Get Menggunakan Keyboard.
		@return Menggunakan Keyboard	  */
	public boolean isMenggunakan_Keyboard () 
	{
		Object oo = get_Value(COLUMNNAME_Menggunakan_Keyboard);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mengoperasikan Kendaraan Ringan.
		@param MengoperasikanAlatRingan Mengoperasikan Kendaraan Ringan	  */
	public void setMengoperasikanAlatRingan (boolean MengoperasikanAlatRingan)
	{
		set_Value (COLUMNNAME_MengoperasikanAlatRingan, Boolean.valueOf(MengoperasikanAlatRingan));
	}

	/** Get Mengoperasikan Kendaraan Ringan.
		@return Mengoperasikan Kendaraan Ringan	  */
	public boolean isMengoperasikanAlatRingan () 
	{
		Object oo = get_Value(COLUMNNAME_MengoperasikanAlatRingan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Menjalankan Peralatan Berat.
		@param Menjalankan_Peralatan_Berat Menjalankan Peralatan Berat	  */
	public void setMenjalankan_Peralatan_Berat (boolean Menjalankan_Peralatan_Berat)
	{
		set_Value (COLUMNNAME_Menjalankan_Peralatan_Berat, Boolean.valueOf(Menjalankan_Peralatan_Berat));
	}

	/** Get Menjalankan Peralatan Berat.
		@return Menjalankan Peralatan Berat	  */
	public boolean isMenjalankan_Peralatan_Berat () 
	{
		Object oo = get_Value(COLUMNNAME_Menjalankan_Peralatan_Berat);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Menyusui.
		@param Menyusui Menyusui	  */
	public void setMenyusui (boolean Menyusui)
	{
		set_Value (COLUMNNAME_Menyusui, Boolean.valueOf(Menyusui));
	}

	/** Get Menyusui.
		@return Menyusui	  */
	public boolean isMenyusui () 
	{
		Object oo = get_Value(COLUMNNAME_Menyusui);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mercury Organic.
		@param Mercury_Organic Mercury Organic	  */
	public void setMercury_Organic (boolean Mercury_Organic)
	{
		set_Value (COLUMNNAME_Mercury_Organic, Boolean.valueOf(Mercury_Organic));
	}

	/** Get Mercury Organic.
		@return Mercury Organic	  */
	public boolean isMercury_Organic () 
	{
		Object oo = get_Value(COLUMNNAME_Mercury_Organic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Merokok.
		@param Merokok Merokok	  */
	public void setMerokok (boolean Merokok)
	{
		set_Value (COLUMNNAME_Merokok, Boolean.valueOf(Merokok));
	}

	/** Get Merokok.
		@return Merokok	  */
	public boolean isMerokok () 
	{
		Object oo = get_Value(COLUMNNAME_Merokok);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Motorik.
		@param Motorik Motorik	  */
	public void setMotorik (String Motorik)
	{
		set_Value (COLUMNNAME_Motorik, Motorik);
	}

	/** Get Motorik.
		@return Motorik	  */
	public String getMotorik () 
	{
		return (String)get_Value(COLUMNNAME_Motorik);
	}

	/** Set Nadi.
		@param Nadi Nadi	  */
	public void setNadi (BigDecimal Nadi)
	{
		set_Value (COLUMNNAME_Nadi, Nadi);
	}

	/** Get Nadi.
		@return Nadi	  */
	public BigDecimal getNadi () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Nadi);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Nyeri Dada/Gangguan Jantung.
		@param Nyeri_Dadaor_Gangguan_Jantung Nyeri Dada/Gangguan Jantung	  */
	public void setNyeri_Dadaor_Gangguan_Jantung (boolean Nyeri_Dadaor_Gangguan_Jantung)
	{
		set_Value (COLUMNNAME_Nyeri_Dadaor_Gangguan_Jantung, Boolean.valueOf(Nyeri_Dadaor_Gangguan_Jantung));
	}

	/** Get Nyeri Dada/Gangguan Jantung.
		@return Nyeri Dada/Gangguan Jantung	  */
	public boolean isNyeri_Dadaor_Gangguan_Jantung () 
	{
		Object oo = get_Value(COLUMNNAME_Nyeri_Dadaor_Gangguan_Jantung);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nyeri Ketok Ginjal Kanan.
		@param Nyeri_Ketok_Ginjal_Kanan Nyeri Ketok Ginjal Kanan	  */
	public void setNyeri_Ketok_Ginjal_Kanan (String Nyeri_Ketok_Ginjal_Kanan)
	{
		set_Value (COLUMNNAME_Nyeri_Ketok_Ginjal_Kanan, Nyeri_Ketok_Ginjal_Kanan);
	}

	/** Get Nyeri Ketok Ginjal Kanan.
		@return Nyeri Ketok Ginjal Kanan	  */
	public String getNyeri_Ketok_Ginjal_Kanan () 
	{
		return (String)get_Value(COLUMNNAME_Nyeri_Ketok_Ginjal_Kanan);
	}

	/** Set Nyeri Ketok Ginjal Kiri.
		@param Nyeri_Ketok_Ginjal_Kiri Nyeri Ketok Ginjal Kiri	  */
	public void setNyeri_Ketok_Ginjal_Kiri (String Nyeri_Ketok_Ginjal_Kiri)
	{
		set_Value (COLUMNNAME_Nyeri_Ketok_Ginjal_Kiri, Nyeri_Ketok_Ginjal_Kiri);
	}

	/** Get Nyeri Ketok Ginjal Kiri.
		@return Nyeri Ketok Ginjal Kiri	  */
	public String getNyeri_Ketok_Ginjal_Kiri () 
	{
		return (String)get_Value(COLUMNNAME_Nyeri_Ketok_Ginjal_Kiri);
	}

	/** Set Nyeri Pinggang.
		@param Nyeri_Pinggang Nyeri Pinggang	  */
	public void setNyeri_Pinggang (boolean Nyeri_Pinggang)
	{
		set_Value (COLUMNNAME_Nyeri_Pinggang, Boolean.valueOf(Nyeri_Pinggang));
	}

	/** Get Nyeri Pinggang.
		@return Nyeri Pinggang	  */
	public boolean isNyeri_Pinggang () 
	{
		Object oo = get_Value(COLUMNNAME_Nyeri_Pinggang);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nyeri Punggung Bawah.
		@param Nyeri_Punggung_Bawah Nyeri Punggung Bawah	  */
	public void setNyeri_Punggung_Bawah (boolean Nyeri_Punggung_Bawah)
	{
		set_Value (COLUMNNAME_Nyeri_Punggung_Bawah, Boolean.valueOf(Nyeri_Punggung_Bawah));
	}

	/** Get Nyeri Punggung Bawah.
		@return Nyeri Punggung Bawah	  */
	public boolean isNyeri_Punggung_Bawah () 
	{
		Object oo = get_Value(COLUMNNAME_Nyeri_Punggung_Bawah);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Nyeri Sendi Dan Bengkak.
		@param Nyeri_Sendi_Dan_Bengkak Nyeri Sendi Dan Bengkak	  */
	public void setNyeri_Sendi_Dan_Bengkak (boolean Nyeri_Sendi_Dan_Bengkak)
	{
		set_Value (COLUMNNAME_Nyeri_Sendi_Dan_Bengkak, Boolean.valueOf(Nyeri_Sendi_Dan_Bengkak));
	}

	/** Get Nyeri Sendi Dan Bengkak.
		@return Nyeri Sendi Dan Bengkak	  */
	public boolean isNyeri_Sendi_Dan_Bengkak () 
	{
		Object oo = get_Value(COLUMNNAME_Nyeri_Sendi_Dan_Bengkak);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Olahraga.
		@param Olahraga Olahraga	  */
	public void setOlahraga (boolean Olahraga)
	{
		set_Value (COLUMNNAME_Olahraga, Boolean.valueOf(Olahraga));
	}

	/** Get Olahraga.
		@return Olahraga	  */
	public boolean isOlahraga () 
	{
		Object oo = get_Value(COLUMNNAME_Olahraga);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Panas.
		@param Panas Panas	  */
	public void setPanas (boolean Panas)
	{
		set_Value (COLUMNNAME_Panas, Boolean.valueOf(Panas));
	}

	/** Get Panas.
		@return Panas	  */
	public boolean isPanas () 
	{
		Object oo = get_Value(COLUMNNAME_Panas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set PAP.
		@param Pap_ PAP	  */
	public void setPap_ (String Pap_)
	{
		set_Value (COLUMNNAME_Pap_, Pap_);
	}

	/** Get PAP.
		@return PAP	  */
	public String getPap_ () 
	{
		return (String)get_Value(COLUMNNAME_Pap_);
	}

	/** Set Paru Suara Nafas.
		@param Paru_Suara_Nafas Paru Suara Nafas	  */
	public void setParu_Suara_Nafas (String Paru_Suara_Nafas)
	{
		set_Value (COLUMNNAME_Paru_Suara_Nafas, Paru_Suara_Nafas);
	}

	/** Get Paru Suara Nafas.
		@return Paru Suara Nafas	  */
	public String getParu_Suara_Nafas () 
	{
		return (String)get_Value(COLUMNNAME_Paru_Suara_Nafas);
	}

	/** Set Pembedahan/Operasi.
		@param Pembedahan_Or_Operasi Pembedahan/Operasi	  */
	public void setPembedahan_Or_Operasi (boolean Pembedahan_Or_Operasi)
	{
		set_Value (COLUMNNAME_Pembedahan_Or_Operasi, Boolean.valueOf(Pembedahan_Or_Operasi));
	}

	/** Get Pembedahan/Operasi.
		@return Pembedahan/Operasi	  */
	public boolean isPembedahan_Or_Operasi () 
	{
		Object oo = get_Value(COLUMNNAME_Pembedahan_Or_Operasi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Penanganan Makanan.
		@param Penangan_Makanan Penanganan Makanan	  */
	public void setPenangan_Makanan (boolean Penangan_Makanan)
	{
		set_Value (COLUMNNAME_Penangan_Makanan, Boolean.valueOf(Penangan_Makanan));
	}

	/** Get Penanganan Makanan.
		@return Penanganan Makanan	  */
	public boolean isPenangan_Makanan () 
	{
		Object oo = get_Value(COLUMNNAME_Penangan_Makanan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pengangkatan.
		@param Pengangkatan Pengangkatan	  */
	public void setPengangkatan (boolean Pengangkatan)
	{
		set_Value (COLUMNNAME_Pengangkatan, Boolean.valueOf(Pengangkatan));
	}

	/** Get Pengangkatan.
		@return Pengangkatan	  */
	public boolean isPengangkatan () 
	{
		Object oo = get_Value(COLUMNNAME_Pengangkatan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Penyakit Kronik Lain.
		@param Penyakit_Kronik_Lain Penyakit Kronik Lain	  */
	public void setPenyakit_Kronik_Lain (boolean Penyakit_Kronik_Lain)
	{
		set_Value (COLUMNNAME_Penyakit_Kronik_Lain, Boolean.valueOf(Penyakit_Kronik_Lain));
	}

	/** Get Penyakit Kronik Lain.
		@return Penyakit Kronik Lain	  */
	public boolean isPenyakit_Kronik_Lain () 
	{
		Object oo = get_Value(COLUMNNAME_Penyakit_Kronik_Lain);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pernah Pingsan , Epilepsi.
		@param Pernah_Pingsan_Epilepsi Pernah Pingsan , Epilepsi	  */
	public void setPernah_Pingsan_Epilepsi (boolean Pernah_Pingsan_Epilepsi)
	{
		set_Value (COLUMNNAME_Pernah_Pingsan_Epilepsi, Boolean.valueOf(Pernah_Pingsan_Epilepsi));
	}

	/** Get Pernah Pingsan , Epilepsi.
		@return Pernah Pingsan , Epilepsi	  */
	public boolean isPernah_Pingsan_Epilepsi () 
	{
		Object oo = get_Value(COLUMNNAME_Pernah_Pingsan_Epilepsi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Perpindahan Yang Penuh Tenaga.
		@param Perpindahan_Yang_Penuh_Tenaga Perpindahan Yang Penuh Tenaga	  */
	public void setPerpindahan_Yang_Penuh_Tenaga (boolean Perpindahan_Yang_Penuh_Tenaga)
	{
		set_Value (COLUMNNAME_Perpindahan_Yang_Penuh_Tenaga, Boolean.valueOf(Perpindahan_Yang_Penuh_Tenaga));
	}

	/** Get Perpindahan Yang Penuh Tenaga.
		@return Perpindahan Yang Penuh Tenaga	  */
	public boolean isPerpindahan_Yang_Penuh_Tenaga () 
	{
		Object oo = get_Value(COLUMNNAME_Perpindahan_Yang_Penuh_Tenaga);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Pestisida.
		@param Pestisida Pestisida	  */
	public void setPestisida (boolean Pestisida)
	{
		set_Value (COLUMNNAME_Pestisida, Boolean.valueOf(Pestisida));
	}

	/** Get Pestisida.
		@return Pestisida	  */
	public boolean isPestisida () 
	{
		Object oo = get_Value(COLUMNNAME_Pestisida);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set pH.
		@param pH pH	  */
	public void setpH (BigDecimal pH)
	{
		set_Value (COLUMNNAME_pH, pH);
	}

	/** Get pH.
		@return pH	  */
	public BigDecimal getpH () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_pH);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pharyn/Faring.
		@param Pharynor_faring Pharyn/Faring	  */
	public void setPharynor_faring (String Pharynor_faring)
	{
		set_Value (COLUMNNAME_Pharynor_faring, Pharynor_faring);
	}

	/** Get Pharyn/Faring.
		@return Pharyn/Faring	  */
	public String getPharynor_faring () 
	{
		return (String)get_Value(COLUMNNAME_Pharynor_faring);
	}

	/** Set Posture Anggota Badan Atas.
		@param Posture_anggota_badan_atas Posture Anggota Badan Atas	  */
	public void setPosture_anggota_badan_atas (boolean Posture_anggota_badan_atas)
	{
		set_Value (COLUMNNAME_Posture_anggota_badan_atas, Boolean.valueOf(Posture_anggota_badan_atas));
	}

	/** Get Posture Anggota Badan Atas.
		@return Posture Anggota Badan Atas	  */
	public boolean isPosture_anggota_badan_atas () 
	{
		Object oo = get_Value(COLUMNNAME_Posture_anggota_badan_atas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Posture Body-Dynamic.
		@param Posture_body__dynamic Posture Body-Dynamic	  */
	public void setPosture_body__dynamic (boolean Posture_body__dynamic)
	{
		set_Value (COLUMNNAME_Posture_body__dynamic, Boolean.valueOf(Posture_body__dynamic));
	}

	/** Get Posture Body-Dynamic.
		@return Posture Body-Dynamic	  */
	public boolean isPosture_body__dynamic () 
	{
		Object oo = get_Value(COLUMNNAME_Posture_body__dynamic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Puasa.
		@param Puasa Puasa	  */
	public void setPuasa (BigDecimal Puasa)
	{
		set_Value (COLUMNNAME_Puasa, Puasa);
	}

	/** Get Puasa.
		@return Puasa	  */
	public BigDecimal getPuasa () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Puasa);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pusing/Vertigo.
		@param Pusingor_vertigo Pusing/Vertigo	  */
	public void setPusingor_vertigo (boolean Pusingor_vertigo)
	{
		set_Value (COLUMNNAME_Pusingor_vertigo, Boolean.valueOf(Pusingor_vertigo));
	}

	/** Get Pusing/Vertigo.
		@return Pusing/Vertigo	  */
	public boolean isPusingor_vertigo () 
	{
		Object oo = get_Value(COLUMNNAME_Pusingor_vertigo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Racun.
		@param Racun Racun	  */
	public void setRacun (boolean Racun)
	{
		set_Value (COLUMNNAME_Racun, Boolean.valueOf(Racun));
	}

	/** Get Racun.
		@return Racun	  */
	public boolean isRacun () 
	{
		Object oo = get_Value(COLUMNNAME_Racun);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Radiasi Ultraviolet.
		@param Radiasi_ultraviolet Radiasi Ultraviolet	  */
	public void setRadiasi_ultraviolet (boolean Radiasi_ultraviolet)
	{
		set_Value (COLUMNNAME_Radiasi_ultraviolet, Boolean.valueOf(Radiasi_ultraviolet));
	}

	/** Get Radiasi Ultraviolet.
		@return Radiasi Ultraviolet	  */
	public boolean isRadiasi_ultraviolet () 
	{
		Object oo = get_Value(COLUMNNAME_Radiasi_ultraviolet);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Reduksi.
		@param Reduksi Reduksi	  */
	public void setReduksi (String Reduksi)
	{
		set_Value (COLUMNNAME_Reduksi, Reduksi);
	}

	/** Get Reduksi.
		@return Reduksi	  */
	public String getReduksi () 
	{
		return (String)get_Value(COLUMNNAME_Reduksi);
	}

	/** Set Rekomendasi Kerja.
		@param Rekomendasi_kerja Rekomendasi Kerja	  */
	public void setRekomendasi_kerja (String Rekomendasi_kerja)
	{
		set_Value (COLUMNNAME_Rekomendasi_kerja, Rekomendasi_kerja);
	}

	/** Get Rekomendasi Kerja.
		@return Rekomendasi Kerja	  */
	public String getRekomendasi_kerja () 
	{
		return (String)get_Value(COLUMNNAME_Rekomendasi_kerja);
	}

	/** Set Resiko Biologis.
		@param Resiko_biologis Resiko Biologis	  */
	public void setResiko_biologis (boolean Resiko_biologis)
	{
		set_Value (COLUMNNAME_Resiko_biologis, Boolean.valueOf(Resiko_biologis));
	}

	/** Get Resiko Biologis.
		@return Resiko Biologis	  */
	public boolean isResiko_biologis () 
	{
		Object oo = get_Value(COLUMNNAME_Resiko_biologis);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sakit Lambung.
		@param Sakit_lambung Sakit Lambung	  */
	public void setSakit_lambung (boolean Sakit_lambung)
	{
		set_Value (COLUMNNAME_Sakit_lambung, Boolean.valueOf(Sakit_lambung));
	}

	/** Get Sakit Lambung.
		@return Sakit Lambung	  */
	public boolean isSakit_lambung () 
	{
		Object oo = get_Value(COLUMNNAME_Sakit_lambung);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sampah.
		@param Sampah Sampah	  */
	public void setSampah (boolean Sampah)
	{
		set_Value (COLUMNNAME_Sampah, Boolean.valueOf(Sampah));
	}

	/** Get Sampah.
		@return Sampah	  */
	public boolean isSampah () 
	{
		Object oo = get_Value(COLUMNNAME_Sampah);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sampah Berbahaya.
		@param Sampah_berbahaya Sampah Berbahaya	  */
	public void setSampah_berbahaya (boolean Sampah_berbahaya)
	{
		set_Value (COLUMNNAME_Sampah_berbahaya, Boolean.valueOf(Sampah_berbahaya));
	}

	/** Get Sampah Berbahaya.
		@return Sampah Berbahaya	  */
	public boolean isSampah_berbahaya () 
	{
		Object oo = get_Value(COLUMNNAME_Sampah_berbahaya);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sed Epithel 1.
		@param Sed_epithel1 Sed Epithel 1	  */
	public void setSed_epithel1 (BigDecimal Sed_epithel1)
	{
		set_Value (COLUMNNAME_Sed_epithel1, Sed_epithel1);
	}

	/** Get Sed Epithel 1.
		@return Sed Epithel 1	  */
	public BigDecimal getSed_epithel1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sed_epithel1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sed Epithel 2.
		@param Sed_epithel2 Sed Epithel 2	  */
	public void setSed_epithel2 (BigDecimal Sed_epithel2)
	{
		set_Value (COLUMNNAME_Sed_epithel2, Sed_epithel2);
	}

	/** Get Sed Epithel 2.
		@return Sed Epithel 2	  */
	public BigDecimal getSed_epithel2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sed_epithel2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sed Erithrosit 1.
		@param Sed_erithrosit1 Sed Erithrosit 1	  */
	public void setSed_erithrosit1 (BigDecimal Sed_erithrosit1)
	{
		set_Value (COLUMNNAME_Sed_erithrosit1, Sed_erithrosit1);
	}

	/** Get Sed Erithrosit 1.
		@return Sed Erithrosit 1	  */
	public BigDecimal getSed_erithrosit1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sed_erithrosit1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sed Erithrosit 2.
		@param Sed_erithrosit2 Sed Erithrosit 2	  */
	public void setSed_erithrosit2 (BigDecimal Sed_erithrosit2)
	{
		set_Value (COLUMNNAME_Sed_erithrosit2, Sed_erithrosit2);
	}

	/** Get Sed Erithrosit 2.
		@return Sed Erithrosit 2	  */
	public BigDecimal getSed_erithrosit2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sed_erithrosit2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sed Kristal.
		@param Sed_kristal Sed Kristal	  */
	public void setSed_kristal (String Sed_kristal)
	{
		set_Value (COLUMNNAME_Sed_kristal, Sed_kristal);
	}

	/** Get Sed Kristal.
		@return Sed Kristal	  */
	public String getSed_kristal () 
	{
		return (String)get_Value(COLUMNNAME_Sed_kristal);
	}

	/** Set Sed Lekosit 1.
		@param Sed_lekosit1 Sed Lekosit 1	  */
	public void setSed_lekosit1 (BigDecimal Sed_lekosit1)
	{
		set_Value (COLUMNNAME_Sed_lekosit1, Sed_lekosit1);
	}

	/** Get Sed Lekosit 1.
		@return Sed Lekosit 1	  */
	public BigDecimal getSed_lekosit1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sed_lekosit1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sed Lekosit 2.
		@param Sed_lekosit2 Sed Lekosit 2	  */
	public void setSed_lekosit2 (BigDecimal Sed_lekosit2)
	{
		set_Value (COLUMNNAME_Sed_lekosit2, Sed_lekosit2);
	}

	/** Get Sed Lekosit 2.
		@return Sed Lekosit 2	  */
	public BigDecimal getSed_lekosit2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sed_lekosit2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sengatan Serangga.
		@param Sengatan_serangga Sengatan Serangga	  */
	public void setSengatan_serangga (boolean Sengatan_serangga)
	{
		set_Value (COLUMNNAME_Sengatan_serangga, Boolean.valueOf(Sengatan_serangga));
	}

	/** Get Sengatan Serangga.
		@return Sengatan Serangga	  */
	public boolean isSengatan_serangga () 
	{
		Object oo = get_Value(COLUMNNAME_Sengatan_serangga);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sensorik.
		@param Sensorik Sensorik	  */
	public void setSensorik (String Sensorik)
	{
		set_Value (COLUMNNAME_Sensorik, Sensorik);
	}

	/** Get Sensorik.
		@return Sensorik	  */
	public String getSensorik () 
	{
		return (String)get_Value(COLUMNNAME_Sensorik);
	}

	/** Set Serbuk/Pollen.
		@param Serbuk_or_pollen Serbuk/Pollen	  */
	public void setSerbuk_or_pollen (boolean Serbuk_or_pollen)
	{
		set_Value (COLUMNNAME_Serbuk_or_pollen, Boolean.valueOf(Serbuk_or_pollen));
	}

	/** Get Serbuk/Pollen.
		@return Serbuk/Pollen	  */
	public boolean isSerbuk_or_pollen () 
	{
		Object oo = get_Value(COLUMNNAME_Serbuk_or_pollen);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SGOT.
		@param SGOT SGOT	  */
	public void setSGOT (BigDecimal SGOT)
	{
		set_Value (COLUMNNAME_SGOT, SGOT);
	}

	/** Get SGOT.
		@return SGOT	  */
	public BigDecimal getSGOT () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SGOT);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SGPT.
		@param SGPT SGPT	  */
	public void setSGPT (BigDecimal SGPT)
	{
		set_Value (COLUMNNAME_SGPT, SGPT);
	}

	/** Get SGPT.
		@return SGPT	  */
	public BigDecimal getSGPT () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SGPT);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Silica Amorphous.
		@param Silica_amorphous Silica Amorphous	  */
	public void setSilica_amorphous (boolean Silica_amorphous)
	{
		set_Value (COLUMNNAME_Silica_amorphous, Boolean.valueOf(Silica_amorphous));
	}

	/** Get Silica Amorphous.
		@return Silica Amorphous	  */
	public boolean isSilica_amorphous () 
	{
		Object oo = get_Value(COLUMNNAME_Silica_amorphous);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Silica Crystalline.
		@param Silica_crystalline Silica Crystalline	  */
	public void setSilica_crystalline (boolean Silica_crystalline)
	{
		set_Value (COLUMNNAME_Silica_crystalline, Boolean.valueOf(Silica_crystalline));
	}

	/** Get Silica Crystalline.
		@return Silica Crystalline	  */
	public boolean isSilica_crystalline () 
	{
		Object oo = get_Value(COLUMNNAME_Silica_crystalline);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sistole.
		@param Sistole Sistole	  */
	public void setSistole (BigDecimal Sistole)
	{
		set_Value (COLUMNNAME_Sistole, Sistole);
	}

	/** Get Sistole.
		@return Sistole	  */
	public BigDecimal getSistole () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sistole);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SMEAR.
		@param SMEAR SMEAR	  */
	public void setSMEAR (String SMEAR)
	{
		set_Value (COLUMNNAME_SMEAR, SMEAR);
	}

	/** Get SMEAR.
		@return SMEAR	  */
	public String getSMEAR () 
	{
		return (String)get_Value(COLUMNNAME_SMEAR);
	}

	/** Set Solvents.
		@param Solvents Solvents	  */
	public void setSolvents (boolean Solvents)
	{
		set_Value (COLUMNNAME_Solvents, Boolean.valueOf(Solvents));
	}

	/** Get Solvents.
		@return Solvents	  */
	public boolean isSolvents () 
	{
		Object oo = get_Value(COLUMNNAME_Solvents);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Stress.
		@param Stress Stress	  */
	public void setStress (boolean Stress)
	{
		set_Value (COLUMNNAME_Stress, Boolean.valueOf(Stress));
	}

	/** Get Stress.
		@return Stress	  */
	public boolean isStress () 
	{
		Object oo = get_Value(COLUMNNAME_Stress);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Suhu Sangat Dingin.
		@param Suhu_sangat_dingin Suhu Sangat Dingin	  */
	public void setSuhu_sangat_dingin (boolean Suhu_sangat_dingin)
	{
		set_Value (COLUMNNAME_Suhu_sangat_dingin, Boolean.valueOf(Suhu_sangat_dingin));
	}

	/** Get Suhu Sangat Dingin.
		@return Suhu Sangat Dingin	  */
	public boolean isSuhu_sangat_dingin () 
	{
		Object oo = get_Value(COLUMNNAME_Suhu_sangat_dingin);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Suhu Sangat Panas.
		@param Suhu_sangat_panas Suhu Sangat Panas	  */
	public void setSuhu_sangat_panas (boolean Suhu_sangat_panas)
	{
		set_Value (COLUMNNAME_Suhu_sangat_panas, Boolean.valueOf(Suhu_sangat_panas));
	}

	/** Get Suhu Sangat Panas.
		@return Suhu Sangat Panas	  */
	public boolean isSuhu_sangat_panas () 
	{
		Object oo = get_Value(COLUMNNAME_Suhu_sangat_panas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Telinga Kanan Membran Timpani.
		@param Telinga_kanan_membran_timpani Telinga Kanan Membran Timpani	  */
	public void setTelinga_kanan_membran_timpani (String Telinga_kanan_membran_timpani)
	{
		set_Value (COLUMNNAME_Telinga_kanan_membran_timpani, Telinga_kanan_membran_timpani);
	}

	/** Get Telinga Kanan Membran Timpani.
		@return Telinga Kanan Membran Timpani	  */
	public String getTelinga_kanan_membran_timpani () 
	{
		return (String)get_Value(COLUMNNAME_Telinga_kanan_membran_timpani);
	}

	/** Set Telinga Kiri Membran Timpani.
		@param Telinga_kiri_membran_timpani Telinga Kiri Membran Timpani	  */
	public void setTelinga_kiri_membran_timpani (String Telinga_kiri_membran_timpani)
	{
		set_Value (COLUMNNAME_Telinga_kiri_membran_timpani, Telinga_kiri_membran_timpani);
	}

	/** Get Telinga Kiri Membran Timpani.
		@return Telinga Kiri Membran Timpani	  */
	public String getTelinga_kiri_membran_timpani () 
	{
		return (String)get_Value(COLUMNNAME_Telinga_kiri_membran_timpani);
	}

	/** Set Telinga/Ear Kanan Saluran.
		@param Telingaorear_kanan_saluran Telinga/Ear Kanan Saluran	  */
	public void setTelingaorear_kanan_saluran (String Telingaorear_kanan_saluran)
	{
		set_Value (COLUMNNAME_Telingaorear_kanan_saluran, Telingaorear_kanan_saluran);
	}

	/** Get Telinga/Ear Kanan Saluran.
		@return Telinga/Ear Kanan Saluran	  */
	public String getTelingaorear_kanan_saluran () 
	{
		return (String)get_Value(COLUMNNAME_Telingaorear_kanan_saluran);
	}

	/** Set Telinga/Ear Kiri Saluran.
		@param Telingaorear_kiri_saluran Telinga/Ear Kiri Saluran	  */
	public void setTelingaorear_kiri_saluran (String Telingaorear_kiri_saluran)
	{
		set_Value (COLUMNNAME_Telingaorear_kiri_saluran, Telingaorear_kiri_saluran);
	}

	/** Get Telinga/Ear Kiri Saluran.
		@return Telinga/Ear Kiri Saluran	  */
	public String getTelingaorear_kiri_saluran () 
	{
		return (String)get_Value(COLUMNNAME_Telingaorear_kiri_saluran);
	}

	/** Set Terpapar Bising.
		@param Terpapar_bising Terpapar Bising	  */
	public void setTerpapar_bising (boolean Terpapar_bising)
	{
		set_Value (COLUMNNAME_Terpapar_bising, Boolean.valueOf(Terpapar_bising));
	}

	/** Get Terpapar Bising.
		@return Terpapar Bising	  */
	public boolean isTerpapar_bising () 
	{
		Object oo = get_Value(COLUMNNAME_Terpapar_bising);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Terpapar Debu.
		@param Terpapar_debu Terpapar Debu	  */
	public void setTerpapar_debu (boolean Terpapar_debu)
	{
		set_Value (COLUMNNAME_Terpapar_debu, Boolean.valueOf(Terpapar_debu));
	}

	/** Get Terpapar Debu.
		@return Terpapar Debu	  */
	public boolean isTerpapar_debu () 
	{
		Object oo = get_Value(COLUMNNAME_Terpapar_debu);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Terpapar Getaran.
		@param Terpapar_getaran Terpapar Getaran	  */
	public void setTerpapar_getaran (boolean Terpapar_getaran)
	{
		set_Value (COLUMNNAME_Terpapar_getaran, Boolean.valueOf(Terpapar_getaran));
	}

	/** Get Terpapar Getaran.
		@return Terpapar Getaran	  */
	public boolean isTerpapar_getaran () 
	{
		Object oo = get_Value(COLUMNNAME_Terpapar_getaran);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Terpapar Radiasi.
		@param Terpapar_radiasi Terpapar Radiasi	  */
	public void setTerpapar_radiasi (boolean Terpapar_radiasi)
	{
		set_Value (COLUMNNAME_Terpapar_radiasi, Boolean.valueOf(Terpapar_radiasi));
	}

	/** Get Terpapar Radiasi.
		@return Terpapar Radiasi	  */
	public boolean isTerpapar_radiasi () 
	{
		Object oo = get_Value(COLUMNNAME_Terpapar_radiasi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Terpapar Zat Kimia.
		@param Terpapar_zat_kimia Terpapar Zat Kimia	  */
	public void setTerpapar_zat_kimia (boolean Terpapar_zat_kimia)
	{
		set_Value (COLUMNNAME_Terpapar_zat_kimia, Boolean.valueOf(Terpapar_zat_kimia));
	}

	/** Get Terpapar Zat Kimia.
		@return Terpapar Zat Kimia	  */
	public boolean isTerpapar_zat_kimia () 
	{
		Object oo = get_Value(COLUMNNAME_Terpapar_zat_kimia);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Thrombosit.
		@param Thrombosit Thrombosit	  */
	public void setThrombosit (BigDecimal Thrombosit)
	{
		set_Value (COLUMNNAME_Thrombosit, Thrombosit);
	}

	/** Get Thrombosit.
		@return Thrombosit	  */
	public BigDecimal getThrombosit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Thrombosit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tinggi Badan.
		@param Tinggi_badan Tinggi Badan	  */
	public void setTinggi_badan (BigDecimal Tinggi_badan)
	{
		set_Value (COLUMNNAME_Tinggi_badan, Tinggi_badan);
	}

	/** Get Tinggi Badan.
		@return Tinggi Badan	  */
	public BigDecimal getTinggi_badan () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Tinggi_badan);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tinta.
		@param Tinta Tinta	  */
	public void setTinta (boolean Tinta)
	{
		set_Value (COLUMNNAME_Tinta, Boolean.valueOf(Tinta));
	}

	/** Get Tinta.
		@return Tinta	  */
	public boolean isTinta () 
	{
		Object oo = get_Value(COLUMNNAME_Tinta);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tiroid.
		@param Tiroid Tiroid	  */
	public void setTiroid (String Tiroid)
	{
		set_Value (COLUMNNAME_Tiroid, Tiroid);
	}

	/** Get Tiroid.
		@return Tiroid	  */
	public String getTiroid () 
	{
		return (String)get_Value(COLUMNNAME_Tiroid);
	}

	/** Set Tonsilor Amandel.
		@param Tonsilor_amandel Tonsilor Amandel	  */
	public void setTonsilor_amandel (String Tonsilor_amandel)
	{
		set_Value (COLUMNNAME_Tonsilor_amandel, Tonsilor_amandel);
	}

	/** Get Tonsilor Amandel.
		@return Tonsilor Amandel	  */
	public String getTonsilor_amandel () 
	{
		return (String)get_Value(COLUMNNAME_Tonsilor_amandel);
	}

	/** Set Trachea.
		@param Trachea Trachea	  */
	public void setTrachea (String Trachea)
	{
		set_Value (COLUMNNAME_Trachea, Trachea);
	}

	/** Get Trachea.
		@return Trachea	  */
	public String getTrachea () 
	{
		return (String)get_Value(COLUMNNAME_Trachea);
	}

	/** Set 1,1,2-Trichloroethane.
		@param Trichloroethane 1,1,2-Trichloroethane	  */
	public void setTrichloroethane (boolean Trichloroethane)
	{
		set_Value (COLUMNNAME_Trichloroethane, Boolean.valueOf(Trichloroethane));
	}

	/** Get 1,1,2-Trichloroethane.
		@return 1,1,2-Trichloroethane	  */
	public boolean isTrichloroethane () 
	{
		Object oo = get_Value(COLUMNNAME_Trichloroethane);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Trigliserida.
		@param Trigliserida Trigliserida	  */
	public void setTrigliserida (BigDecimal Trigliserida)
	{
		set_Value (COLUMNNAME_Trigliserida, Trigliserida);
	}

	/** Get Trigliserida.
		@return Trigliserida	  */
	public BigDecimal getTrigliserida () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Trigliserida);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Urobilinogen.
		@param Urobilinogen Urobilinogen	  */
	public void setUrobilinogen (String Urobilinogen)
	{
		set_Value (COLUMNNAME_Urobilinogen, Urobilinogen);
	}

	/** Get Urobilinogen.
		@return Urobilinogen	  */
	public String getUrobilinogen () 
	{
		return (String)get_Value(COLUMNNAME_Urobilinogen);
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

	/** Set Varises.
		@param Varises Varises	  */
	public void setVarises (boolean Varises)
	{
		set_Value (COLUMNNAME_Varises, Boolean.valueOf(Varises));
	}

	/** Get Varises.
		@return Varises	  */
	public boolean isVarises () 
	{
		Object oo = get_Value(COLUMNNAME_Varises);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Vibrasi.
		@param Vibrasi Vibrasi	  */
	public void setVibrasi (boolean Vibrasi)
	{
		set_Value (COLUMNNAME_Vibrasi, Boolean.valueOf(Vibrasi));
	}

	/** Get Vibrasi.
		@return Vibrasi	  */
	public boolean isVibrasi () 
	{
		Object oo = get_Value(COLUMNNAME_Vibrasi);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Vibrasi Seluruh Badan.
		@param Vibrasi_seluruh_badan Vibrasi Seluruh Badan	  */
	public void setVibrasi_seluruh_badan (boolean Vibrasi_seluruh_badan)
	{
		set_Value (COLUMNNAME_Vibrasi_seluruh_badan, Boolean.valueOf(Vibrasi_seluruh_badan));
	}

	/** Get Vibrasi Seluruh Badan.
		@return Vibrasi Seluruh Badan	  */
	public boolean isVibrasi_seluruh_badan () 
	{
		Object oo = get_Value(COLUMNNAME_Vibrasi_seluruh_badan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Visus.
		@param Visus Visus	  */
	public void setVisus (String Visus)
	{
		set_Value (COLUMNNAME_Visus, Visus);
	}

	/** Get Visus.
		@return Visus	  */
	public String getVisus () 
	{
		return (String)get_Value(COLUMNNAME_Visus);
	}
}