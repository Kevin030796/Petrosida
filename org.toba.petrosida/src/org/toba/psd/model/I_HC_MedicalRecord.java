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
package org.toba.psd.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.*;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;
import org.taowi.hcm.core.model.I_HC_Job;

/** Generated Interface for HC_MedicalRecord
 *  @author iDempiere (generated) 
 *  @version Release 3.1
 */
@SuppressWarnings("all")
public interface I_HC_MedicalRecord 
{

    /** TableName=HC_MedicalRecord */
    public static final String Table_Name = "HC_MedicalRecord";

    /** AD_Table_ID=1000170 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Age */
    public static final String COLUMNNAME_Age = "Age";

	/** Set Age	  */
	public void setAge (BigDecimal Age);

	/** Get Age	  */
	public BigDecimal getAge();

    /** Column name Akral */
    public static final String COLUMNNAME_Akral = "Akral";

	/** Set Akral	  */
	public void setAkral (String Akral);

	/** Get Akral	  */
	public String getAkral();

    /** Column name Albumin */
    public static final String COLUMNNAME_Albumin = "Albumin";

	/** Set Albumin	  */
	public void setAlbumin (String Albumin);

	/** Get Albumin	  */
	public String getAlbumin();

    /** Column name Alergi */
    public static final String COLUMNNAME_Alergi = "Alergi";

	/** Set Alergi	  */
	public void setAlergi (boolean Alergi);

	/** Get Alergi	  */
	public boolean isAlergi();

    /** Column name Ambien */
    public static final String COLUMNNAME_Ambien = "Ambien";

	/** Set Ambien	  */
	public void setAmbien (boolean Ambien);

	/** Get Ambien	  */
	public boolean isAmbien();

    /** Column name AsamUrat */
    public static final String COLUMNNAME_AsamUrat = "AsamUrat";

	/** Set Asam Urat	  */
	public void setAsamUrat (BigDecimal AsamUrat);

	/** Get Asam Urat	  */
	public BigDecimal getAsamUrat();

    /** Column name Asap_Rokok */
    public static final String COLUMNNAME_Asap_Rokok = "Asap_Rokok";

	/** Set Asap Rokok	  */
	public void setAsap_Rokok (boolean Asap_Rokok);

	/** Get Asap Rokok	  */
	public boolean isAsap_Rokok();

    /** Column name Asapor_Smoke_Nos */
    public static final String COLUMNNAME_Asapor_Smoke_Nos = "Asapor_Smoke_Nos";

	/** Set Asap/Smoke,NOS	  */
	public void setAsapor_Smoke_Nos (boolean Asapor_Smoke_Nos);

	/** Get Asap/Smoke,NOS	  */
	public boolean isAsapor_Smoke_Nos();

    /** Column name Asma */
    public static final String COLUMNNAME_Asma = "Asma";

	/** Set Asma	  */
	public void setAsma (boolean Asma);

	/** Get Asma	  */
	public boolean isAsma();

    /** Column name Athlete_Foot */
    public static final String COLUMNNAME_Athlete_Foot = "Athlete_Foot";

	/** Set Athlete Foot(Kaki Atlit)/Ring Worm	  */
	public void setAthlete_Foot (boolean Athlete_Foot);

	/** Get Athlete Foot(Kaki Atlit)/Ring Worm	  */
	public boolean isAthlete_Foot();

    /** Column name Berat_Badan */
    public static final String COLUMNNAME_Berat_Badan = "Berat_Badan";

	/** Set Berat Badan	  */
	public void setBerat_Badan (BigDecimal Berat_Badan);

	/** Get Berat Badan	  */
	public BigDecimal getBerat_Badan();

    /** Column name Bilirubin */
    public static final String COLUMNNAME_Bilirubin = "Bilirubin";

	/** Set Bilirubin	  */
	public void setBilirubin (String Bilirubin);

	/** Get Bilirubin	  */
	public String getBilirubin();

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name BMI */
    public static final String COLUMNNAME_BMI = "BMI";

	/** Set BMI	  */
	public void setBMI (String BMI);

	/** Get BMI	  */
	public String getBMI();

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

    /** Column name Carboniess_Paper */
    public static final String COLUMNNAME_Carboniess_Paper = "Carboniess_Paper";

	/** Set Carboniess Paper	  */
	public void setCarboniess_Paper (boolean Carboniess_Paper);

	/** Get Carboniess Paper	  */
	public boolean isCarboniess_Paper();

    /** Column name Cedera_Kepala */
    public static final String COLUMNNAME_Cedera_Kepala = "Cedera_Kepala";

	/** Set Cedera Kepala	  */
	public void setCedera_Kepala (boolean Cedera_Kepala);

	/** Get Cedera Kepala	  */
	public boolean isCedera_Kepala();

    /** Column name Cholesterol */
    public static final String COLUMNNAME_Cholesterol = "Cholesterol";

	/** Set Cholesterol	  */
	public void setCholesterol (BigDecimal Cholesterol);

	/** Get Cholesterol	  */
	public BigDecimal getCholesterol();

    /** Column name Cholinesterase */
    public static final String COLUMNNAME_Cholinesterase = "Cholinesterase";

	/** Set Cholinesterase	  */
	public void setCholinesterase (BigDecimal Cholinesterase);

	/** Get Cholinesterase	  */
	public BigDecimal getCholinesterase();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Creatinin */
    public static final String COLUMNNAME_Creatinin = "Creatinin";

	/** Set Creatinin	  */
	public void setCreatinin (BigDecimal Creatinin);

	/** Get Creatinin	  */
	public BigDecimal getCreatinin();

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name Debu_or_Dust_Nos */
    public static final String COLUMNNAME_Debu_or_Dust_Nos = "Debu_or_Dust_Nos";

	/** Set Debu/Dust,NOS	  */
	public void setDebu_or_Dust_Nos (boolean Debu_or_Dust_Nos);

	/** Get Debu/Dust,NOS	  */
	public boolean isDebu_or_Dust_Nos();

    /** Column name Debu_Tumbuhan */
    public static final String COLUMNNAME_Debu_Tumbuhan = "Debu_Tumbuhan";

	/** Set Debu Tumbuhan	  */
	public void setDebu_Tumbuhan (boolean Debu_Tumbuhan);

	/** Get Debu Tumbuhan	  */
	public boolean isDebu_Tumbuhan();

    /** Column name Demam_Thypoid */
    public static final String COLUMNNAME_Demam_Thypoid = "Demam_Thypoid";

	/** Set Demam Thypoid	  */
	public void setDemam_Thypoid (boolean Demam_Thypoid);

	/** Get Demam Thypoid	  */
	public boolean isDemam_Thypoid();

    /** Column name Diabetes */
    public static final String COLUMNNAME_Diabetes = "Diabetes";

	/** Set Diabetes	  */
	public void setDiabetes (boolean Diabetes);

	/** Get Diabetes	  */
	public boolean isDiabetes();

    /** Column name Diare_Kronik */
    public static final String COLUMNNAME_Diare_Kronik = "Diare_Kronik";

	/** Set Diare Kronik	  */
	public void setDiare_Kronik (boolean Diare_Kronik);

	/** Get Diare Kronik	  */
	public boolean isDiare_Kronik();

    /** Column name Diastole */
    public static final String COLUMNNAME_Diastole = "Diastole";

	/** Set Diastole	  */
	public void setDiastole (BigDecimal Diastole);

	/** Get Diastole	  */
	public BigDecimal getDiastole();

    /** Column name Diffcount */
    public static final String COLUMNNAME_Diffcount = "Diffcount";

	/** Set DiffCount	  */
	public void setDiffcount (String Diffcount);

	/** Get DiffCount	  */
	public String getDiffcount();

    /** Column name DuaJamPP */
    public static final String COLUMNNAME_DuaJamPP = "DuaJamPP";

	/** Set duaJamPP	  */
	public void setDuaJamPP (BigDecimal DuaJamPP);

	/** Get duaJamPP	  */
	public BigDecimal getDuaJamPP();

    /** Column name ECG */
    public static final String COLUMNNAME_ECG = "ECG";

	/** Set ECG	  */
	public void setECG (String ECG);

	/** Get ECG	  */
	public String getECG();

    /** Column name Erithrosit */
    public static final String COLUMNNAME_Erithrosit = "Erithrosit";

	/** Set Erithrosit	  */
	public void setErithrosit (BigDecimal Erithrosit);

	/** Get Erithrosit	  */
	public BigDecimal getErithrosit();

    /** Column name Faktor_Ergonomis */
    public static final String COLUMNNAME_Faktor_Ergonomis = "Faktor_Ergonomis";

	/** Set Faktor Ergonomis	  */
	public void setFaktor_Ergonomis (boolean Faktor_Ergonomis);

	/** Get Faktor Ergonomis	  */
	public boolean isFaktor_Ergonomis();

    /** Column name Formaldehye */
    public static final String COLUMNNAME_Formaldehye = "Formaldehye";

	/** Set Formaldehye	  */
	public void setFormaldehye (boolean Formaldehye);

	/** Get Formaldehye	  */
	public boolean isFormaldehye();

    /** Column name Foto_Thorax */
    public static final String COLUMNNAME_Foto_Thorax = "Foto_Thorax";

	/** Set Foto Thorax	  */
	public void setFoto_Thorax (String Foto_Thorax);

	/** Get Foto Thorax	  */
	public String getFoto_Thorax();

    /** Column name Gangguan_GinjalOr_Batu_Ginjal */
    public static final String COLUMNNAME_Gangguan_GinjalOr_Batu_Ginjal = "Gangguan_GinjalOr_Batu_Ginjal";

	/** Set Gangguan Ginjal/Batu Ginjal	  */
	public void setGangguan_GinjalOr_Batu_Ginjal (boolean Gangguan_GinjalOr_Batu_Ginjal);

	/** Get Gangguan Ginjal/Batu Ginjal	  */
	public boolean isGangguan_GinjalOr_Batu_Ginjal();

    /** Column name Gangguan_Haid */
    public static final String COLUMNNAME_Gangguan_Haid = "Gangguan_Haid";

	/** Set Gangguan Haid	  */
	public void setGangguan_Haid (boolean Gangguan_Haid);

	/** Get Gangguan Haid	  */
	public boolean isGangguan_Haid();

    /** Column name Gangguan_Kebidanan */
    public static final String COLUMNNAME_Gangguan_Kebidanan = "Gangguan_Kebidanan";

	/** Set Gangguan Kebidanan	  */
	public void setGangguan_Kebidanan (boolean Gangguan_Kebidanan);

	/** Get Gangguan Kebidanan	  */
	public boolean isGangguan_Kebidanan();

    /** Column name Gangguan_Pendengaran */
    public static final String COLUMNNAME_Gangguan_Pendengaran = "Gangguan_Pendengaran";

	/** Set Gangguan Pendengaran	  */
	public void setGangguan_Pendengaran (boolean Gangguan_Pendengaran);

	/** Get Gangguan Pendengaran	  */
	public boolean isGangguan_Pendengaran();

    /** Column name Gangguan_Penglihatan */
    public static final String COLUMNNAME_Gangguan_Penglihatan = "Gangguan_Penglihatan";

	/** Set Gangguan Penglihatan	  */
	public void setGangguan_Penglihatan (boolean Gangguan_Penglihatan);

	/** Get Gangguan Penglihatan	  */
	public boolean isGangguan_Penglihatan();

    /** Column name Gas_Alam */
    public static final String COLUMNNAME_Gas_Alam = "Gas_Alam";

	/** Set Gas Alam	  */
	public void setGas_Alam (boolean Gas_Alam);

	/** Get Gas Alam	  */
	public boolean isGas_Alam();

    /** Column name GatalGatalOrAlergi */
    public static final String COLUMNNAME_GatalGatalOrAlergi = "GatalGatalOrAlergi";

	/** Set Gatal Gatal/Alergi	  */
	public void setGatalGatalOrAlergi (boolean GatalGatalOrAlergi);

	/** Get Gatal Gatal/Alergi	  */
	public boolean isGatalGatalOrAlergi();

    /** Column name Gerak_Dada */
    public static final String COLUMNNAME_Gerak_Dada = "Gerak_Dada";

	/** Set Gerak Dada	  */
	public void setGerak_Dada (String Gerak_Dada);

	/** Get Gerak Dada	  */
	public String getGerak_Dada();

    /** Column name Gigi_Lubang */
    public static final String COLUMNNAME_Gigi_Lubang = "Gigi_Lubang";

	/** Set Gigi Lubang	  */
	public void setGigi_Lubang (boolean Gigi_Lubang);

	/** Get Gigi Lubang	  */
	public boolean isGigi_Lubang();

    /** Column name Gigitan_Nyamuk */
    public static final String COLUMNNAME_Gigitan_Nyamuk = "Gigitan_Nyamuk";

	/** Set Gigitan Nyamuk	  */
	public void setGigitan_Nyamuk (boolean Gigitan_Nyamuk);

	/** Get Gigitan Nyamuk	  */
	public boolean isGigitan_Nyamuk();

    /** Column name Ginjal_Kanan */
    public static final String COLUMNNAME_Ginjal_Kanan = "Ginjal_Kanan";

	/** Set Ginjal Kanan	  */
	public void setGinjal_Kanan (String Ginjal_Kanan);

	/** Get Ginjal Kanan	  */
	public String getGinjal_Kanan();

    /** Column name Ginjal_Kiri */
    public static final String COLUMNNAME_Ginjal_Kiri = "Ginjal_Kiri";

	/** Set Ginjal Kiri	  */
	public void setGinjal_Kiri (String Ginjal_Kiri);

	/** Get Ginjal Kiri	  */
	public String getGinjal_Kiri();

    /** Column name HBsAG */
    public static final String COLUMNNAME_HBsAG = "HBsAG";

	/** Set HBsAG	  */
	public void setHBsAG (BigDecimal HBsAG);

	/** Get HBsAG	  */
	public BigDecimal getHBsAG();

    /** Column name HC_Employee_ID */
    public static final String COLUMNNAME_HC_Employee_ID = "HC_Employee_ID";

	/** Set Employee Data	  */
	public void setHC_Employee_ID (int HC_Employee_ID);

	/** Get Employee Data	  */
	public int getHC_Employee_ID();

	public I_HC_Employee getHC_Employee() throws RuntimeException;

    /** Column name HC_Gender */
    public static final String COLUMNNAME_HC_Gender = "HC_Gender";

	/** Set Gender	  */
	public void setHC_Gender (String HC_Gender);

	/** Get Gender	  */
	public String getHC_Gender();

    /** Column name HC_Job_ID */
    public static final String COLUMNNAME_HC_Job_ID = "HC_Job_ID";

	/** Set Job	  */
	public void setHC_Job_ID (int HC_Job_ID);

	/** Get Job	  */
	public int getHC_Job_ID();

	public I_HC_Job getHC_Job() throws RuntimeException;

    /** Column name HC_MedicalRecord_ID */
    public static final String COLUMNNAME_HC_MedicalRecord_ID = "HC_MedicalRecord_ID";

	/** Set Medical Record	  */
	public void setHC_MedicalRecord_ID (int HC_MedicalRecord_ID);

	/** Get Medical Record	  */
	public int getHC_MedicalRecord_ID();

    /** Column name HC_MedicalRecord_UU */
    public static final String COLUMNNAME_HC_MedicalRecord_UU = "HC_MedicalRecord_UU";

	/** Set HC_MedicalRecord_UU	  */
	public void setHC_MedicalRecord_UU (String HC_MedicalRecord_UU);

	/** Get HC_MedicalRecord_UU	  */
	public String getHC_MedicalRecord_UU();

    /** Column name Hematocrit */
    public static final String COLUMNNAME_Hematocrit = "Hematocrit";

	/** Set Hematocrit	  */
	public void setHematocrit (BigDecimal Hematocrit);

	/** Get Hematocrit	  */
	public BigDecimal getHematocrit();

    /** Column name Hemoglobin */
    public static final String COLUMNNAME_Hemoglobin = "Hemoglobin";

	/** Set Hemoglobin	  */
	public void setHemoglobin (BigDecimal Hemoglobin);

	/** Get Hemoglobin	  */
	public BigDecimal getHemoglobin();

    /** Column name Hepatitisor_Sakit_Kuning */
    public static final String COLUMNNAME_Hepatitisor_Sakit_Kuning = "Hepatitisor_Sakit_Kuning";

	/** Set Hepatitis/Sakit Kuning	  */
	public void setHepatitisor_Sakit_Kuning (boolean Hepatitisor_Sakit_Kuning);

	/** Get Hepatitis/Sakit Kuning	  */
	public boolean isHepatitisor_Sakit_Kuning();

    /** Column name HidungOrnose_Konka */
    public static final String COLUMNNAME_HidungOrnose_Konka = "HidungOrnose_Konka";

	/** Set Hidung/Nose Konka	  */
	public void setHidungOrnose_Konka (String HidungOrnose_Konka);

	/** Get Hidung/Nose Konka	  */
	public String getHidungOrnose_Konka();

    /** Column name Hidungornose_septum */
    public static final String COLUMNNAME_Hidungornose_septum = "Hidungornose_septum";

	/** Set Hidung/Nose Septum	  */
	public void setHidungornose_septum (String Hidungornose_septum);

	/** Get Hidung/Nose Septum	  */
	public String getHidungornose_septum();

    /** Column name Hipertensi */
    public static final String COLUMNNAME_Hipertensi = "Hipertensi";

	/** Set Hipertensi	  */
	public void setHipertensi (boolean Hipertensi);

	/** Get Hipertensi	  */
	public boolean isHipertensi();

    /** Column name Hydrocarbons */
    public static final String COLUMNNAME_Hydrocarbons = "Hydrocarbons";

	/** Set Hydro Carbons	  */
	public void setHydrocarbons (boolean Hydrocarbons);

	/** Get Hydro Carbons	  */
	public boolean isHydrocarbons();

    /** Column name Hydrogen_Sulfide */
    public static final String COLUMNNAME_Hydrogen_Sulfide = "Hydrogen_Sulfide";

	/** Set Hydrogen Sulfide	  */
	public void setHydrogen_Sulfide (boolean Hydrogen_Sulfide);

	/** Get Hydrogen Sulfide	  */
	public boolean isHydrogen_Sulfide();

    /** Column name Infeksi_Saluran_Kencing */
    public static final String COLUMNNAME_Infeksi_Saluran_Kencing = "Infeksi_Saluran_Kencing";

	/** Set Infeksi Saluran Kencing	  */
	public void setInfeksi_Saluran_Kencing (boolean Infeksi_Saluran_Kencing);

	/** Get Infeksi Saluran Kencing	  */
	public boolean isInfeksi_Saluran_Kencing();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Jantung_Irama */
    public static final String COLUMNNAME_Jantung_Irama = "Jantung_Irama";

	/** Set Jantung Irama	  */
	public void setJantung_Irama (String Jantung_Irama);

	/** Get Jantung Irama	  */
	public String getJantung_Irama();

    /** Column name Jantung_Suara */
    public static final String COLUMNNAME_Jantung_Suara = "Jantung_Suara";

	/** Set Jantung Suara	  */
	public void setJantung_Suara (String Jantung_Suara);

	/** Get Jantung Suara	  */
	public String getJantung_Suara();

    /** Column name Jatuh */
    public static final String COLUMNNAME_Jatuh = "Jatuh";

	/** Set Jatuh	  */
	public void setJatuh (boolean Jatuh);

	/** Get Jatuh	  */
	public boolean isJatuh();

    /** Column name Jugular_Venous_Pressure */
    public static final String COLUMNNAME_Jugular_Venous_Pressure = "Jugular_Venous_Pressure";

	/** Set Jugular Venous Pressure	  */
	public void setJugular_Venous_Pressure (String Jugular_Venous_Pressure);

	/** Get Jugular Venous Pressure	  */
	public String getJugular_Venous_Pressure();

    /** Column name Kadar_Kolesterol_Tinggi */
    public static final String COLUMNNAME_Kadar_Kolesterol_Tinggi = "Kadar_Kolesterol_Tinggi";

	/** Set Kadar Kolesterol Tinggi	  */
	public void setKadar_Kolesterol_Tinggi (boolean Kadar_Kolesterol_Tinggi);

	/** Get Kadar Kolesterol Tinggi	  */
	public boolean isKadar_Kolesterol_Tinggi();

    /** Column name Kanker */
    public static final String COLUMNNAME_Kanker = "Kanker";

	/** Set Kanker	  */
	public void setKanker (boolean Kanker);

	/** Get Kanker	  */
	public boolean isKanker();

    /** Column name Karbon_Hitam */
    public static final String COLUMNNAME_Karbon_Hitam = "Karbon_Hitam";

	/** Set Karbon Hitam	  */
	public void setKarbon_Hitam (boolean Karbon_Hitam);

	/** Get Karbon Hitam	  */
	public boolean isKarbon_Hitam();

    /** Column name Keguguran */
    public static final String COLUMNNAME_Keguguran = "Keguguran";

	/** Set Keguguran	  */
	public void setKeguguran (boolean Keguguran);

	/** Get Keguguran	  */
	public boolean isKeguguran();

    /** Column name Kehamilan */
    public static final String COLUMNNAME_Kehamilan = "Kehamilan";

	/** Set Kehamilan	  */
	public void setKehamilan (boolean Kehamilan);

	/** Get Kehamilan	  */
	public boolean isKehamilan();

    /** Column name Kehilangan_Orbertambah_Bb */
    public static final String COLUMNNAME_Kehilangan_Orbertambah_Bb = "Kehilangan_Orbertambah_Bb";

	/** Set Kehilangan/Bertambah Berat Badan Dalam Waktu Singkat	  */
	public void setKehilangan_Orbertambah_Bb (boolean Kehilangan_Orbertambah_Bb);

	/** Get Kehilangan/Bertambah Berat Badan Dalam Waktu Singkat	  */
	public boolean isKehilangan_Orbertambah_Bb();

    /** Column name Kekurangan_Oxygen_Or_Hypoxia */
    public static final String COLUMNNAME_Kekurangan_Oxygen_Or_Hypoxia = "Kekurangan_Oxygen_Or_Hypoxia";

	/** Set Kekurangan Oxygen/Hypoxia	  */
	public void setKekurangan_Oxygen_Or_Hypoxia (boolean Kekurangan_Oxygen_Or_Hypoxia);

	/** Get Kekurangan Oxygen/Hypoxia	  */
	public boolean isKekurangan_Oxygen_Or_Hypoxia();

    /** Column name Kelembapan_Tinggi */
    public static final String COLUMNNAME_Kelembapan_Tinggi = "Kelembapan_Tinggi";

	/** Set Kelembapan Tinggi	  */
	public void setKelembapan_Tinggi (boolean Kelembapan_Tinggi);

	/** Get Kelembapan Tinggi	  */
	public boolean isKelembapan_Tinggi();

    /** Column name Kelenjar_Getah_Bening */
    public static final String COLUMNNAME_Kelenjar_Getah_Bening = "Kelenjar_Getah_Bening";

	/** Set Kelenjar Getah Bening	  */
	public void setKelenjar_Getah_Bening (String Kelenjar_Getah_Bening);

	/** Get Kelenjar Getah Bening	  */
	public String getKelenjar_Getah_Bening();

    /** Column name Ketthorax */
    public static final String COLUMNNAME_Ketthorax = "Ketthorax";

	/** Set Ketthorax	  */
	public void setKetthorax (String Ketthorax);

	/** Get Ketthorax	  */
	public String getKetthorax();

    /** Column name Kimia */
    public static final String COLUMNNAME_Kimia = "Kimia";

	/** Set Kimia	  */
	public void setKimia (boolean Kimia);

	/** Get Kimia	  */
	public boolean isKimia();

    /** Column name Kondisi_Umum */
    public static final String COLUMNNAME_Kondisi_Umum = "Kondisi_Umum";

	/** Set Kondisi Umum	  */
	public void setKondisi_Umum (String Kondisi_Umum);

	/** Get Kondisi Umum	  */
	public String getKondisi_Umum();

    /** Column name Konjungtivitis_Alergi */
    public static final String COLUMNNAME_Konjungtivitis_Alergi = "Konjungtivitis_Alergi";

	/** Set Konjungtivitis Alergi	  */
	public void setKonjungtivitis_Alergi (boolean Konjungtivitis_Alergi);

	/** Get Konjungtivitis Alergi	  */
	public boolean isKonjungtivitis_Alergi();

    /** Column name Kontrasepsi */
    public static final String COLUMNNAME_Kontrasepsi = "Kontrasepsi";

	/** Set Kontrasepsi	  */
	public void setKontrasepsi (boolean Kontrasepsi);

	/** Get Kontrasepsi	  */
	public boolean isKontrasepsi();

    /** Column name LainLain */
    public static final String COLUMNNAME_LainLain = "LainLain";

	/** Set Lain Lain	  */
	public void setLainLain (String LainLain);

	/** Get Lain Lain	  */
	public String getLainLain();

    /** Column name LainLainCentang */
    public static final String COLUMNNAME_LainLainCentang = "LainLainCentang";

	/** Set Lain Lain	  */
	public void setLainLainCentang (boolean LainLainCentang);

	/** Get Lain Lain	  */
	public boolean isLainLainCentang();

    /** Column name Led1 */
    public static final String COLUMNNAME_Led1 = "Led1";

	/** Set Led 1	  */
	public void setLed1 (BigDecimal Led1);

	/** Get Led 1	  */
	public BigDecimal getLed1();

    /** Column name Led2 */
    public static final String COLUMNNAME_Led2 = "Led2";

	/** Set Led 2	  */
	public void setLed2 (BigDecimal Led2);

	/** Get Led 2	  */
	public BigDecimal getLed2();

    /** Column name Lekosit */
    public static final String COLUMNNAME_Lekosit = "Lekosit";

	/** Set Lekosit	  */
	public void setLekosit (BigDecimal Lekosit);

	/** Get Lekosit	  */
	public BigDecimal getLekosit();

    /** Column name LienorLimpa */
    public static final String COLUMNNAME_LienorLimpa = "LienorLimpa";

	/** Set Lien/Limpa	  */
	public void setLienorLimpa (String LienorLimpa);

	/** Get Lien/Limpa	  */
	public String getLienorLimpa();

    /** Column name LiverorHati */
    public static final String COLUMNNAME_LiverorHati = "LiverorHati";

	/** Set Liver/Hati	  */
	public void setLiverorHati (String LiverorHati);

	/** Get Liver/Hati	  */
	public String getLiverorHati();

    /** Column name Malaria */
    public static final String COLUMNNAME_Malaria = "Malaria";

	/** Set Malaria	  */
	public void setMalaria (boolean Malaria);

	/** Get Malaria	  */
	public boolean isMalaria();

    /** Column name Mata_Anemia */
    public static final String COLUMNNAME_Mata_Anemia = "Mata_Anemia";

	/** Set Mata Anemia	  */
	public void setMata_Anemia (String Mata_Anemia);

	/** Get Mata Anemia	  */
	public String getMata_Anemia();

    /** Column name Mata_Icterus */
    public static final String COLUMNNAME_Mata_Icterus = "Mata_Icterus";

	/** Set Mata Icterus	  */
	public void setMata_Icterus (String Mata_Icterus);

	/** Get Mata Icterus	  */
	public String getMata_Icterus();

    /** Column name Material_Industri */
    public static final String COLUMNNAME_Material_Industri = "Material_Industri";

	/** Set Material Indusri	  */
	public void setMaterial_Industri (boolean Material_Industri);

	/** Get Material Indusri	  */
	public boolean isMaterial_Industri();

    /** Column name Menggunakan_Keyboard */
    public static final String COLUMNNAME_Menggunakan_Keyboard = "Menggunakan_Keyboard";

	/** Set Menggunakan Keyboard	  */
	public void setMenggunakan_Keyboard (boolean Menggunakan_Keyboard);

	/** Get Menggunakan Keyboard	  */
	public boolean isMenggunakan_Keyboard();

    /** Column name MengoperasikanAlatRingan */
    public static final String COLUMNNAME_MengoperasikanAlatRingan = "MengoperasikanAlatRingan";

	/** Set Mengoperasikan Kendaraan Ringan	  */
	public void setMengoperasikanAlatRingan (boolean MengoperasikanAlatRingan);

	/** Get Mengoperasikan Kendaraan Ringan	  */
	public boolean isMengoperasikanAlatRingan();

    /** Column name Menjalankan_Peralatan_Berat */
    public static final String COLUMNNAME_Menjalankan_Peralatan_Berat = "Menjalankan_Peralatan_Berat";

	/** Set Menjalankan Peralatan Berat	  */
	public void setMenjalankan_Peralatan_Berat (boolean Menjalankan_Peralatan_Berat);

	/** Get Menjalankan Peralatan Berat	  */
	public boolean isMenjalankan_Peralatan_Berat();

    /** Column name Menyusui */
    public static final String COLUMNNAME_Menyusui = "Menyusui";

	/** Set Menyusui	  */
	public void setMenyusui (boolean Menyusui);

	/** Get Menyusui	  */
	public boolean isMenyusui();

    /** Column name Mercury_Organic */
    public static final String COLUMNNAME_Mercury_Organic = "Mercury_Organic";

	/** Set Mercury Organic	  */
	public void setMercury_Organic (boolean Mercury_Organic);

	/** Get Mercury Organic	  */
	public boolean isMercury_Organic();

    /** Column name Merokok */
    public static final String COLUMNNAME_Merokok = "Merokok";

	/** Set Merokok	  */
	public void setMerokok (boolean Merokok);

	/** Get Merokok	  */
	public boolean isMerokok();

    /** Column name Motorik */
    public static final String COLUMNNAME_Motorik = "Motorik";

	/** Set Motorik	  */
	public void setMotorik (String Motorik);

	/** Get Motorik	  */
	public String getMotorik();

    /** Column name Nadi */
    public static final String COLUMNNAME_Nadi = "Nadi";

	/** Set Nadi	  */
	public void setNadi (BigDecimal Nadi);

	/** Get Nadi	  */
	public BigDecimal getNadi();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Nyeri_Dadaor_Gangguan_Jantung */
    public static final String COLUMNNAME_Nyeri_Dadaor_Gangguan_Jantung = "Nyeri_Dadaor_Gangguan_Jantung";

	/** Set Nyeri Dada/Gangguan Jantung	  */
	public void setNyeri_Dadaor_Gangguan_Jantung (boolean Nyeri_Dadaor_Gangguan_Jantung);

	/** Get Nyeri Dada/Gangguan Jantung	  */
	public boolean isNyeri_Dadaor_Gangguan_Jantung();

    /** Column name Nyeri_Ketok_Ginjal_Kanan */
    public static final String COLUMNNAME_Nyeri_Ketok_Ginjal_Kanan = "Nyeri_Ketok_Ginjal_Kanan";

	/** Set Nyeri Ketok Ginjal Kanan	  */
	public void setNyeri_Ketok_Ginjal_Kanan (String Nyeri_Ketok_Ginjal_Kanan);

	/** Get Nyeri Ketok Ginjal Kanan	  */
	public String getNyeri_Ketok_Ginjal_Kanan();

    /** Column name Nyeri_Ketok_Ginjal_Kiri */
    public static final String COLUMNNAME_Nyeri_Ketok_Ginjal_Kiri = "Nyeri_Ketok_Ginjal_Kiri";

	/** Set Nyeri Ketok Ginjal Kiri	  */
	public void setNyeri_Ketok_Ginjal_Kiri (String Nyeri_Ketok_Ginjal_Kiri);

	/** Get Nyeri Ketok Ginjal Kiri	  */
	public String getNyeri_Ketok_Ginjal_Kiri();

    /** Column name Nyeri_Pinggang */
    public static final String COLUMNNAME_Nyeri_Pinggang = "Nyeri_Pinggang";

	/** Set Nyeri Pinggang	  */
	public void setNyeri_Pinggang (boolean Nyeri_Pinggang);

	/** Get Nyeri Pinggang	  */
	public boolean isNyeri_Pinggang();

    /** Column name Nyeri_Punggung_Bawah */
    public static final String COLUMNNAME_Nyeri_Punggung_Bawah = "Nyeri_Punggung_Bawah";

	/** Set Nyeri Punggung Bawah	  */
	public void setNyeri_Punggung_Bawah (boolean Nyeri_Punggung_Bawah);

	/** Get Nyeri Punggung Bawah	  */
	public boolean isNyeri_Punggung_Bawah();

    /** Column name Nyeri_Sendi_Dan_Bengkak */
    public static final String COLUMNNAME_Nyeri_Sendi_Dan_Bengkak = "Nyeri_Sendi_Dan_Bengkak";

	/** Set Nyeri Sendi Dan Bengkak	  */
	public void setNyeri_Sendi_Dan_Bengkak (boolean Nyeri_Sendi_Dan_Bengkak);

	/** Get Nyeri Sendi Dan Bengkak	  */
	public boolean isNyeri_Sendi_Dan_Bengkak();

    /** Column name Olahraga */
    public static final String COLUMNNAME_Olahraga = "Olahraga";

	/** Set Olahraga	  */
	public void setOlahraga (boolean Olahraga);

	/** Get Olahraga	  */
	public boolean isOlahraga();

    /** Column name Panas */
    public static final String COLUMNNAME_Panas = "Panas";

	/** Set Panas	  */
	public void setPanas (boolean Panas);

	/** Get Panas	  */
	public boolean isPanas();

    /** Column name Pap_ */
    public static final String COLUMNNAME_Pap_ = "Pap_";

	/** Set PAP	  */
	public void setPap_ (String Pap_);

	/** Get PAP	  */
	public String getPap_();

    /** Column name Paru_Suara_Nafas */
    public static final String COLUMNNAME_Paru_Suara_Nafas = "Paru_Suara_Nafas";

	/** Set Paru Suara Nafas	  */
	public void setParu_Suara_Nafas (String Paru_Suara_Nafas);

	/** Get Paru Suara Nafas	  */
	public String getParu_Suara_Nafas();

    /** Column name Pembedahan_Or_Operasi */
    public static final String COLUMNNAME_Pembedahan_Or_Operasi = "Pembedahan_Or_Operasi";

	/** Set Pembedahan/Operasi	  */
	public void setPembedahan_Or_Operasi (boolean Pembedahan_Or_Operasi);

	/** Get Pembedahan/Operasi	  */
	public boolean isPembedahan_Or_Operasi();

    /** Column name Penangan_Makanan */
    public static final String COLUMNNAME_Penangan_Makanan = "Penangan_Makanan";

	/** Set Penanganan Makanan	  */
	public void setPenangan_Makanan (boolean Penangan_Makanan);

	/** Get Penanganan Makanan	  */
	public boolean isPenangan_Makanan();

    /** Column name Pengangkatan */
    public static final String COLUMNNAME_Pengangkatan = "Pengangkatan";

	/** Set Pengangkatan	  */
	public void setPengangkatan (boolean Pengangkatan);

	/** Get Pengangkatan	  */
	public boolean isPengangkatan();

    /** Column name Penyakit_Kronik_Lain */
    public static final String COLUMNNAME_Penyakit_Kronik_Lain = "Penyakit_Kronik_Lain";

	/** Set Penyakit Kronik Lain	  */
	public void setPenyakit_Kronik_Lain (boolean Penyakit_Kronik_Lain);

	/** Get Penyakit Kronik Lain	  */
	public boolean isPenyakit_Kronik_Lain();

    /** Column name Pernah_Pingsan_Epilepsi */
    public static final String COLUMNNAME_Pernah_Pingsan_Epilepsi = "Pernah_Pingsan_Epilepsi";

	/** Set Pernah Pingsan , Epilepsi	  */
	public void setPernah_Pingsan_Epilepsi (boolean Pernah_Pingsan_Epilepsi);

	/** Get Pernah Pingsan , Epilepsi	  */
	public boolean isPernah_Pingsan_Epilepsi();

    /** Column name Perpindahan_Yang_Penuh_Tenaga */
    public static final String COLUMNNAME_Perpindahan_Yang_Penuh_Tenaga = "Perpindahan_Yang_Penuh_Tenaga";

	/** Set Perpindahan Yang Penuh Tenaga	  */
	public void setPerpindahan_Yang_Penuh_Tenaga (boolean Perpindahan_Yang_Penuh_Tenaga);

	/** Get Perpindahan Yang Penuh Tenaga	  */
	public boolean isPerpindahan_Yang_Penuh_Tenaga();

    /** Column name Pestisida */
    public static final String COLUMNNAME_Pestisida = "Pestisida";

	/** Set Pestisida	  */
	public void setPestisida (boolean Pestisida);

	/** Get Pestisida	  */
	public boolean isPestisida();

    /** Column name pH */
    public static final String COLUMNNAME_pH = "pH";

	/** Set pH	  */
	public void setpH (BigDecimal pH);

	/** Get pH	  */
	public BigDecimal getpH();

    /** Column name Pharynor_faring */
    public static final String COLUMNNAME_Pharynor_faring = "Pharynor_faring";

	/** Set Pharyn/Faring	  */
	public void setPharynor_faring (String Pharynor_faring);

	/** Get Pharyn/Faring	  */
	public String getPharynor_faring();

    /** Column name Posture_anggota_badan_atas */
    public static final String COLUMNNAME_Posture_anggota_badan_atas = "Posture_anggota_badan_atas";

	/** Set Posture Anggota Badan Atas	  */
	public void setPosture_anggota_badan_atas (boolean Posture_anggota_badan_atas);

	/** Get Posture Anggota Badan Atas	  */
	public boolean isPosture_anggota_badan_atas();

    /** Column name Posture_body__dynamic */
    public static final String COLUMNNAME_Posture_body__dynamic = "Posture_body__dynamic";

	/** Set Posture Body-Dynamic	  */
	public void setPosture_body__dynamic (boolean Posture_body__dynamic);

	/** Get Posture Body-Dynamic	  */
	public boolean isPosture_body__dynamic();

    /** Column name Puasa */
    public static final String COLUMNNAME_Puasa = "Puasa";

	/** Set Puasa	  */
	public void setPuasa (BigDecimal Puasa);

	/** Get Puasa	  */
	public BigDecimal getPuasa();

    /** Column name Pusingor_vertigo */
    public static final String COLUMNNAME_Pusingor_vertigo = "Pusingor_vertigo";

	/** Set Pusing/Vertigo	  */
	public void setPusingor_vertigo (boolean Pusingor_vertigo);

	/** Get Pusing/Vertigo	  */
	public boolean isPusingor_vertigo();

    /** Column name Racun */
    public static final String COLUMNNAME_Racun = "Racun";

	/** Set Racun	  */
	public void setRacun (boolean Racun);

	/** Get Racun	  */
	public boolean isRacun();

    /** Column name Radiasi_ultraviolet */
    public static final String COLUMNNAME_Radiasi_ultraviolet = "Radiasi_ultraviolet";

	/** Set Radiasi Ultraviolet	  */
	public void setRadiasi_ultraviolet (boolean Radiasi_ultraviolet);

	/** Get Radiasi Ultraviolet	  */
	public boolean isRadiasi_ultraviolet();

    /** Column name Reduksi */
    public static final String COLUMNNAME_Reduksi = "Reduksi";

	/** Set Reduksi	  */
	public void setReduksi (String Reduksi);

	/** Get Reduksi	  */
	public String getReduksi();

    /** Column name Rekomendasi_kerja */
    public static final String COLUMNNAME_Rekomendasi_kerja = "Rekomendasi_kerja";

	/** Set Rekomendasi Kerja	  */
	public void setRekomendasi_kerja (String Rekomendasi_kerja);

	/** Get Rekomendasi Kerja	  */
	public String getRekomendasi_kerja();

    /** Column name Resiko_biologis */
    public static final String COLUMNNAME_Resiko_biologis = "Resiko_biologis";

	/** Set Resiko Biologis	  */
	public void setResiko_biologis (boolean Resiko_biologis);

	/** Get Resiko Biologis	  */
	public boolean isResiko_biologis();

    /** Column name Sakit_lambung */
    public static final String COLUMNNAME_Sakit_lambung = "Sakit_lambung";

	/** Set Sakit Lambung	  */
	public void setSakit_lambung (boolean Sakit_lambung);

	/** Get Sakit Lambung	  */
	public boolean isSakit_lambung();

    /** Column name Sampah */
    public static final String COLUMNNAME_Sampah = "Sampah";

	/** Set Sampah	  */
	public void setSampah (boolean Sampah);

	/** Get Sampah	  */
	public boolean isSampah();

    /** Column name Sampah_berbahaya */
    public static final String COLUMNNAME_Sampah_berbahaya = "Sampah_berbahaya";

	/** Set Sampah Berbahaya	  */
	public void setSampah_berbahaya (boolean Sampah_berbahaya);

	/** Get Sampah Berbahaya	  */
	public boolean isSampah_berbahaya();

    /** Column name Sed_epithel1 */
    public static final String COLUMNNAME_Sed_epithel1 = "Sed_epithel1";

	/** Set Sed Epithel 1	  */
	public void setSed_epithel1 (BigDecimal Sed_epithel1);

	/** Get Sed Epithel 1	  */
	public BigDecimal getSed_epithel1();

    /** Column name Sed_epithel2 */
    public static final String COLUMNNAME_Sed_epithel2 = "Sed_epithel2";

	/** Set Sed Epithel 2	  */
	public void setSed_epithel2 (BigDecimal Sed_epithel2);

	/** Get Sed Epithel 2	  */
	public BigDecimal getSed_epithel2();

    /** Column name Sed_erithrosit1 */
    public static final String COLUMNNAME_Sed_erithrosit1 = "Sed_erithrosit1";

	/** Set Sed Erithrosit 1	  */
	public void setSed_erithrosit1 (BigDecimal Sed_erithrosit1);

	/** Get Sed Erithrosit 1	  */
	public BigDecimal getSed_erithrosit1();

    /** Column name Sed_erithrosit2 */
    public static final String COLUMNNAME_Sed_erithrosit2 = "Sed_erithrosit2";

	/** Set Sed Erithrosit 2	  */
	public void setSed_erithrosit2 (BigDecimal Sed_erithrosit2);

	/** Get Sed Erithrosit 2	  */
	public BigDecimal getSed_erithrosit2();

    /** Column name Sed_kristal */
    public static final String COLUMNNAME_Sed_kristal = "Sed_kristal";

	/** Set Sed Kristal	  */
	public void setSed_kristal (String Sed_kristal);

	/** Get Sed Kristal	  */
	public String getSed_kristal();

    /** Column name Sed_lekosit1 */
    public static final String COLUMNNAME_Sed_lekosit1 = "Sed_lekosit1";

	/** Set Sed Lekosit 1	  */
	public void setSed_lekosit1 (BigDecimal Sed_lekosit1);

	/** Get Sed Lekosit 1	  */
	public BigDecimal getSed_lekosit1();

    /** Column name Sed_lekosit2 */
    public static final String COLUMNNAME_Sed_lekosit2 = "Sed_lekosit2";

	/** Set Sed Lekosit 2	  */
	public void setSed_lekosit2 (BigDecimal Sed_lekosit2);

	/** Get Sed Lekosit 2	  */
	public BigDecimal getSed_lekosit2();

    /** Column name Sengatan_serangga */
    public static final String COLUMNNAME_Sengatan_serangga = "Sengatan_serangga";

	/** Set Sengatan Serangga	  */
	public void setSengatan_serangga (boolean Sengatan_serangga);

	/** Get Sengatan Serangga	  */
	public boolean isSengatan_serangga();

    /** Column name Sensorik */
    public static final String COLUMNNAME_Sensorik = "Sensorik";

	/** Set Sensorik	  */
	public void setSensorik (String Sensorik);

	/** Get Sensorik	  */
	public String getSensorik();

    /** Column name Serbuk_or_pollen */
    public static final String COLUMNNAME_Serbuk_or_pollen = "Serbuk_or_pollen";

	/** Set Serbuk/Pollen	  */
	public void setSerbuk_or_pollen (boolean Serbuk_or_pollen);

	/** Get Serbuk/Pollen	  */
	public boolean isSerbuk_or_pollen();

    /** Column name SGOT */
    public static final String COLUMNNAME_SGOT = "SGOT";

	/** Set SGOT	  */
	public void setSGOT (BigDecimal SGOT);

	/** Get SGOT	  */
	public BigDecimal getSGOT();

    /** Column name SGPT */
    public static final String COLUMNNAME_SGPT = "SGPT";

	/** Set SGPT	  */
	public void setSGPT (BigDecimal SGPT);

	/** Get SGPT	  */
	public BigDecimal getSGPT();

    /** Column name Silica_amorphous */
    public static final String COLUMNNAME_Silica_amorphous = "Silica_amorphous";

	/** Set Silica Amorphous	  */
	public void setSilica_amorphous (boolean Silica_amorphous);

	/** Get Silica Amorphous	  */
	public boolean isSilica_amorphous();

    /** Column name Silica_crystalline */
    public static final String COLUMNNAME_Silica_crystalline = "Silica_crystalline";

	/** Set Silica Crystalline	  */
	public void setSilica_crystalline (boolean Silica_crystalline);

	/** Get Silica Crystalline	  */
	public boolean isSilica_crystalline();

    /** Column name Sistole */
    public static final String COLUMNNAME_Sistole = "Sistole";

	/** Set Sistole	  */
	public void setSistole (BigDecimal Sistole);

	/** Get Sistole	  */
	public BigDecimal getSistole();

    /** Column name SMEAR */
    public static final String COLUMNNAME_SMEAR = "SMEAR";

	/** Set SMEAR	  */
	public void setSMEAR (String SMEAR);

	/** Get SMEAR	  */
	public String getSMEAR();

    /** Column name Solvents */
    public static final String COLUMNNAME_Solvents = "Solvents";

	/** Set Solvents	  */
	public void setSolvents (boolean Solvents);

	/** Get Solvents	  */
	public boolean isSolvents();

    /** Column name Stress */
    public static final String COLUMNNAME_Stress = "Stress";

	/** Set Stress	  */
	public void setStress (boolean Stress);

	/** Get Stress	  */
	public boolean isStress();

    /** Column name Suhu_sangat_dingin */
    public static final String COLUMNNAME_Suhu_sangat_dingin = "Suhu_sangat_dingin";

	/** Set Suhu Sangat Dingin	  */
	public void setSuhu_sangat_dingin (boolean Suhu_sangat_dingin);

	/** Get Suhu Sangat Dingin	  */
	public boolean isSuhu_sangat_dingin();

    /** Column name Suhu_sangat_panas */
    public static final String COLUMNNAME_Suhu_sangat_panas = "Suhu_sangat_panas";

	/** Set Suhu Sangat Panas	  */
	public void setSuhu_sangat_panas (boolean Suhu_sangat_panas);

	/** Get Suhu Sangat Panas	  */
	public boolean isSuhu_sangat_panas();

    /** Column name Telinga_kanan_membran_timpani */
    public static final String COLUMNNAME_Telinga_kanan_membran_timpani = "Telinga_kanan_membran_timpani";

	/** Set Telinga Kanan Membran Timpani	  */
	public void setTelinga_kanan_membran_timpani (String Telinga_kanan_membran_timpani);

	/** Get Telinga Kanan Membran Timpani	  */
	public String getTelinga_kanan_membran_timpani();

    /** Column name Telinga_kiri_membran_timpani */
    public static final String COLUMNNAME_Telinga_kiri_membran_timpani = "Telinga_kiri_membran_timpani";

	/** Set Telinga Kiri Membran Timpani	  */
	public void setTelinga_kiri_membran_timpani (String Telinga_kiri_membran_timpani);

	/** Get Telinga Kiri Membran Timpani	  */
	public String getTelinga_kiri_membran_timpani();

    /** Column name Telingaorear_kanan_saluran */
    public static final String COLUMNNAME_Telingaorear_kanan_saluran = "Telingaorear_kanan_saluran";

	/** Set Telinga/Ear Kanan Saluran	  */
	public void setTelingaorear_kanan_saluran (String Telingaorear_kanan_saluran);

	/** Get Telinga/Ear Kanan Saluran	  */
	public String getTelingaorear_kanan_saluran();

    /** Column name Telingaorear_kiri_saluran */
    public static final String COLUMNNAME_Telingaorear_kiri_saluran = "Telingaorear_kiri_saluran";

	/** Set Telinga/Ear Kiri Saluran	  */
	public void setTelingaorear_kiri_saluran (String Telingaorear_kiri_saluran);

	/** Get Telinga/Ear Kiri Saluran	  */
	public String getTelingaorear_kiri_saluran();

    /** Column name Terpapar_bising */
    public static final String COLUMNNAME_Terpapar_bising = "Terpapar_bising";

	/** Set Terpapar Bising	  */
	public void setTerpapar_bising (boolean Terpapar_bising);

	/** Get Terpapar Bising	  */
	public boolean isTerpapar_bising();

    /** Column name Terpapar_debu */
    public static final String COLUMNNAME_Terpapar_debu = "Terpapar_debu";

	/** Set Terpapar Debu	  */
	public void setTerpapar_debu (boolean Terpapar_debu);

	/** Get Terpapar Debu	  */
	public boolean isTerpapar_debu();

    /** Column name Terpapar_getaran */
    public static final String COLUMNNAME_Terpapar_getaran = "Terpapar_getaran";

	/** Set Terpapar Getaran	  */
	public void setTerpapar_getaran (boolean Terpapar_getaran);

	/** Get Terpapar Getaran	  */
	public boolean isTerpapar_getaran();

    /** Column name Terpapar_radiasi */
    public static final String COLUMNNAME_Terpapar_radiasi = "Terpapar_radiasi";

	/** Set Terpapar Radiasi	  */
	public void setTerpapar_radiasi (boolean Terpapar_radiasi);

	/** Get Terpapar Radiasi	  */
	public boolean isTerpapar_radiasi();

    /** Column name Terpapar_zat_kimia */
    public static final String COLUMNNAME_Terpapar_zat_kimia = "Terpapar_zat_kimia";

	/** Set Terpapar Zat Kimia	  */
	public void setTerpapar_zat_kimia (boolean Terpapar_zat_kimia);

	/** Get Terpapar Zat Kimia	  */
	public boolean isTerpapar_zat_kimia();

    /** Column name Thrombosit */
    public static final String COLUMNNAME_Thrombosit = "Thrombosit";

	/** Set Thrombosit	  */
	public void setThrombosit (BigDecimal Thrombosit);

	/** Get Thrombosit	  */
	public BigDecimal getThrombosit();

    /** Column name Tinggi_badan */
    public static final String COLUMNNAME_Tinggi_badan = "Tinggi_badan";

	/** Set Tinggi Badan	  */
	public void setTinggi_badan (BigDecimal Tinggi_badan);

	/** Get Tinggi Badan	  */
	public BigDecimal getTinggi_badan();

    /** Column name Tinta */
    public static final String COLUMNNAME_Tinta = "Tinta";

	/** Set Tinta	  */
	public void setTinta (boolean Tinta);

	/** Get Tinta	  */
	public boolean isTinta();

    /** Column name Tiroid */
    public static final String COLUMNNAME_Tiroid = "Tiroid";

	/** Set Tiroid	  */
	public void setTiroid (String Tiroid);

	/** Get Tiroid	  */
	public String getTiroid();

    /** Column name Tonsilor_amandel */
    public static final String COLUMNNAME_Tonsilor_amandel = "Tonsilor_amandel";

	/** Set Tonsilor Amandel	  */
	public void setTonsilor_amandel (String Tonsilor_amandel);

	/** Get Tonsilor Amandel	  */
	public String getTonsilor_amandel();

    /** Column name Trachea */
    public static final String COLUMNNAME_Trachea = "Trachea";

	/** Set Trachea	  */
	public void setTrachea (String Trachea);

	/** Get Trachea	  */
	public String getTrachea();

    /** Column name Trichloroethane */
    public static final String COLUMNNAME_Trichloroethane = "Trichloroethane";

	/** Set 1,1,2-Trichloroethane	  */
	public void setTrichloroethane (boolean Trichloroethane);

	/** Get 1,1,2-Trichloroethane	  */
	public boolean isTrichloroethane();

    /** Column name Trigliserida */
    public static final String COLUMNNAME_Trigliserida = "Trigliserida";

	/** Set Trigliserida	  */
	public void setTrigliserida (BigDecimal Trigliserida);

	/** Get Trigliserida	  */
	public BigDecimal getTrigliserida();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Urobilinogen */
    public static final String COLUMNNAME_Urobilinogen = "Urobilinogen";

	/** Set Urobilinogen	  */
	public void setUrobilinogen (String Urobilinogen);

	/** Get Urobilinogen	  */
	public String getUrobilinogen();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name Varises */
    public static final String COLUMNNAME_Varises = "Varises";

	/** Set Varises	  */
	public void setVarises (boolean Varises);

	/** Get Varises	  */
	public boolean isVarises();

    /** Column name Vibrasi */
    public static final String COLUMNNAME_Vibrasi = "Vibrasi";

	/** Set Vibrasi	  */
	public void setVibrasi (boolean Vibrasi);

	/** Get Vibrasi	  */
	public boolean isVibrasi();

    /** Column name Vibrasi_seluruh_badan */
    public static final String COLUMNNAME_Vibrasi_seluruh_badan = "Vibrasi_seluruh_badan";

	/** Set Vibrasi Seluruh Badan	  */
	public void setVibrasi_seluruh_badan (boolean Vibrasi_seluruh_badan);

	/** Get Vibrasi Seluruh Badan	  */
	public boolean isVibrasi_seluruh_badan();

    /** Column name Visus */
    public static final String COLUMNNAME_Visus = "Visus";

	/** Set Visus	  */
	public void setVisus (String Visus);

	/** Get Visus	  */
	public String getVisus();
}
