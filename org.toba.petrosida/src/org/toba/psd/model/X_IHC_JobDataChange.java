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
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.taowi.hcm.core.model.I_HC_Employee;
import org.taowi.hcm.core.model.I_HC_EmployeeGrade;
import org.taowi.hcm.core.model.I_HC_EmployeeJob;
import org.taowi.hcm.core.model.I_HC_Job;
import org.taowi.hcm.core.model.I_HC_JobDataChange;
import org.taowi.hcm.core.model.I_HC_Org;
import org.taowi.hcm.core.model.I_HC_PayGroup;
import org.taowi.hcm.core.model.I_HC_Reason;

/** Generated Model for IHC_JobDataChange
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_IHC_JobDataChange extends PO implements I_IHC_JobDataChange, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170815L;

    /** Standard Constructor */
    public X_IHC_JobDataChange (Properties ctx, int IHC_JobDataChange_ID, String trxName)
    {
      super (ctx, IHC_JobDataChange_ID, trxName);
      /** if (IHC_JobDataChange_ID == 0)
        {
			setIHC_JobDataChange_ID (0);
        } */
    }

    /** Load Constructor */
    public X_IHC_JobDataChange (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_IHC_JobDataChange[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BPJS Registration Date.
		@param BPJSRegistrationDate BPJS Registration Date	  */
	public void setBPJSRegistrationDate (Timestamp BPJSRegistrationDate)
	{
		set_ValueNoCheck (COLUMNNAME_BPJSRegistrationDate, BPJSRegistrationDate);
	}

	/** Get BPJS Registration Date.
		@return BPJS Registration Date	  */
	public Timestamp getBPJSRegistrationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BPJSRegistrationDate);
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

	/** Set Description New.
		@param DescriptionNew Description New	  */
	public void setDescriptionNew (String DescriptionNew)
	{
		set_Value (COLUMNNAME_DescriptionNew, DescriptionNew);
	}

	/** Get Description New.
		@return Description New	  */
	public String getDescriptionNew () 
	{
		return (String)get_Value(COLUMNNAME_DescriptionNew);
	}

	/** Set Compensation 1.
		@param HC_Compensation1 Compensation 1	  */
	public void setHC_Compensation1 (BigDecimal HC_Compensation1)
	{
		set_Value (COLUMNNAME_HC_Compensation1, HC_Compensation1);
	}

	/** Get Compensation 1.
		@return Compensation 1	  */
	public BigDecimal getHC_Compensation1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_Compensation1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Compensation 2.
		@param HC_Compensation2 Compensation 2	  */
	public void setHC_Compensation2 (BigDecimal HC_Compensation2)
	{
		set_Value (COLUMNNAME_HC_Compensation2, HC_Compensation2);
	}

	/** Get Compensation 2.
		@return Compensation 2	  */
	public BigDecimal getHC_Compensation2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_Compensation2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Compensation 3.
		@param HC_Compensation3 Compensation 3	  */
	public void setHC_Compensation3 (BigDecimal HC_Compensation3)
	{
		set_ValueNoCheck (COLUMNNAME_HC_Compensation3, HC_Compensation3);
	}

	/** Get Compensation 3.
		@return Compensation 3	  */
	public BigDecimal getHC_Compensation3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_Compensation3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Compensation 4.
		@param HC_Compensation4 Compensation 4	  */
	public void setHC_Compensation4 (BigDecimal HC_Compensation4)
	{
		set_ValueNoCheck (COLUMNNAME_HC_Compensation4, HC_Compensation4);
	}

	/** Get Compensation 4.
		@return Compensation 4	  */
	public BigDecimal getHC_Compensation4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HC_Compensation4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Effective Sequence.
		@param HC_EffectiveSeq Effective Sequence	  */
	public void setHC_EffectiveSeq (int HC_EffectiveSeq)
	{
		set_Value (COLUMNNAME_HC_EffectiveSeq, Integer.valueOf(HC_EffectiveSeq));
	}

	/** Get Effective Sequence.
		@return Effective Sequence	  */
	public int getHC_EffectiveSeq () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EffectiveSeq);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_HC_EmployeeGrade getHC_EmployeeGrade() throws RuntimeException
    {
		return (I_HC_EmployeeGrade)MTable.get(getCtx(), I_HC_EmployeeGrade.Table_Name)
			.getPO(getHC_EmployeeGrade_ID(), get_TrxName());	}

	/** Set Employee Grade.
		@param HC_EmployeeGrade_ID Employee Grade	  */
	public void setHC_EmployeeGrade_ID (int HC_EmployeeGrade_ID)
	{
		if (HC_EmployeeGrade_ID < 1) 
			set_Value (COLUMNNAME_HC_EmployeeGrade_ID, null);
		else 
			set_Value (COLUMNNAME_HC_EmployeeGrade_ID, Integer.valueOf(HC_EmployeeGrade_ID));
	}

	/** Get Employee Grade.
		@return Employee Grade	  */
	public int getHC_EmployeeGrade_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeGrade_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_EmployeeGrade getHC_EmployeeGrade2() throws RuntimeException
    {
		return (I_HC_EmployeeGrade)MTable.get(getCtx(), I_HC_EmployeeGrade.Table_Name)
			.getPO(getHC_EmployeeGrade2_ID(), get_TrxName());	}

	/** Set Employee Grade To.
		@param HC_EmployeeGrade2_ID Employee Grade To	  */
	public void setHC_EmployeeGrade2_ID (int HC_EmployeeGrade2_ID)
	{
		if (HC_EmployeeGrade2_ID < 1) 
			set_Value (COLUMNNAME_HC_EmployeeGrade2_ID, null);
		else 
			set_Value (COLUMNNAME_HC_EmployeeGrade2_ID, Integer.valueOf(HC_EmployeeGrade2_ID));
	}

	/** Get Employee Grade To.
		@return Employee Grade To	  */
	public int getHC_EmployeeGrade2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_EmployeeGrade2_ID);
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

	/** HC_JobAction AD_Reference_ID=300059 */
	public static final int HC_JOBACTION_AD_Reference_ID=300059;
	/** Hire = HIR */
	public static final String HC_JOBACTION_Hire = "HIR";
	/** Data Change = DTC */
	public static final String HC_JOBACTION_DataChange = "DTC";
	/** Transfer = TRF */
	public static final String HC_JOBACTION_Transfer = "TRF";
	/** Promotion = PRO */
	public static final String HC_JOBACTION_Promotion = "PRO";
	/** Demotion = DMO */
	public static final String HC_JOBACTION_Demotion = "DMO";
	/** Salary Change = SLC */
	public static final String HC_JOBACTION_SalaryChange = "SLC";
	/** Terminate = TMN */
	public static final String HC_JOBACTION_Terminate = "TMN";
	/** Rehire = RHR */
	public static final String HC_JOBACTION_Rehire = "RHR";
	/** Set Job Action.
		@param HC_JobAction Job Action	  */
	public void setHC_JobAction (String HC_JobAction)
	{

		set_Value (COLUMNNAME_HC_JobAction, HC_JobAction);
	}

	/** Get Job Action.
		@return Job Action	  */
	public String getHC_JobAction () 
	{
		return (String)get_Value(COLUMNNAME_HC_JobAction);
	}

	public I_HC_JobDataChange getHC_JobDataChange() throws RuntimeException
    {
		return (I_HC_JobDataChange)MTable.get(getCtx(), I_HC_JobDataChange.Table_Name)
			.getPO(getHC_JobDataChange_ID(), get_TrxName());	}

	/** Set Job Data Change.
		@param HC_JobDataChange_ID Job Data Change	  */
	public void setHC_JobDataChange_ID (int HC_JobDataChange_ID)
	{
		if (HC_JobDataChange_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_JobDataChange_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_JobDataChange_ID, Integer.valueOf(HC_JobDataChange_ID));
	}

	/** Get Job Data Change.
		@return Job Data Change	  */
	public int getHC_JobDataChange_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_JobDataChange_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Employee getHC_Manager() throws RuntimeException
    {
		return (I_HC_Employee)MTable.get(getCtx(), I_HC_Employee.Table_Name)
			.getPO(getHC_Manager_ID(), get_TrxName());	}

	/** Set Manager Name.
		@param HC_Manager_ID Manager Name	  */
	public void setHC_Manager_ID (int HC_Manager_ID)
	{
		if (HC_Manager_ID < 1) 
			set_Value (COLUMNNAME_HC_Manager_ID, null);
		else 
			set_Value (COLUMNNAME_HC_Manager_ID, Integer.valueOf(HC_Manager_ID));
	}

	/** Get Manager Name.
		@return Manager Name	  */
	public int getHC_Manager_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Manager_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Employee getHC_ManagerTo() throws RuntimeException
    {
		return (I_HC_Employee)MTable.get(getCtx(), I_HC_Employee.Table_Name)
			.getPO(getHC_ManagerTo_ID(), get_TrxName());	}

	/** Set Manager To ID.
		@param HC_ManagerTo_ID Manager To ID	  */
	public void setHC_ManagerTo_ID (int HC_ManagerTo_ID)
	{
		if (HC_ManagerTo_ID < 1) 
			set_Value (COLUMNNAME_HC_ManagerTo_ID, null);
		else 
			set_Value (COLUMNNAME_HC_ManagerTo_ID, Integer.valueOf(HC_ManagerTo_ID));
	}

	/** Get Manager To ID.
		@return Manager To ID	  */
	public int getHC_ManagerTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_ManagerTo_ID);
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

	public I_HC_Org getHC_Org2() throws RuntimeException
    {
		return (I_HC_Org)MTable.get(getCtx(), I_HC_Org.Table_Name)
			.getPO(getHC_Org2_ID(), get_TrxName());	}

	/** Set HC Organization To.
		@param HC_Org2_ID HC Organization To	  */
	public void setHC_Org2_ID (int HC_Org2_ID)
	{
		if (HC_Org2_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Org2_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Org2_ID, Integer.valueOf(HC_Org2_ID));
	}

	/** Get HC Organization To.
		@return HC Organization To	  */
	public int getHC_Org2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Org2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_PayGroup getHC_PayGroup() throws RuntimeException
    {
		return (I_HC_PayGroup)MTable.get(getCtx(), I_HC_PayGroup.Table_Name)
			.getPO(getHC_PayGroup_ID(), get_TrxName());	}

	/** Set Payroll Group.
		@param HC_PayGroup_ID Payroll Group	  */
	public void setHC_PayGroup_ID (int HC_PayGroup_ID)
	{
		if (HC_PayGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_PayGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_PayGroup_ID, Integer.valueOf(HC_PayGroup_ID));
	}

	/** Get Payroll Group.
		@return Payroll Group	  */
	public int getHC_PayGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PayGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Job getHC_PreviousJob() throws RuntimeException
    {
		return (I_HC_Job)MTable.get(getCtx(), I_HC_Job.Table_Name)
			.getPO(getHC_PreviousJob_ID(), get_TrxName());	}

	/** Set Job Sekarang.
		@param HC_PreviousJob_ID Job Sekarang	  */
	public void setHC_PreviousJob_ID (int HC_PreviousJob_ID)
	{
		if (HC_PreviousJob_ID < 1) 
			set_Value (COLUMNNAME_HC_PreviousJob_ID, null);
		else 
			set_Value (COLUMNNAME_HC_PreviousJob_ID, Integer.valueOf(HC_PreviousJob_ID));
	}

	/** Get Job Sekarang.
		@return Job Sekarang	  */
	public int getHC_PreviousJob_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_PreviousJob_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_HC_Reason getHC_Reason() throws RuntimeException
    {
		return (I_HC_Reason)MTable.get(getCtx(), I_HC_Reason.Table_Name)
			.getPO(getHC_Reason_ID(), get_TrxName());	}

	/** Set HC Reason.
		@param HC_Reason_ID HC Reason	  */
	public void setHC_Reason_ID (int HC_Reason_ID)
	{
		if (HC_Reason_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HC_Reason_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HC_Reason_ID, Integer.valueOf(HC_Reason_ID));
	}

	/** Get HC Reason.
		@return HC Reason	  */
	public int getHC_Reason_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HC_Reason_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** HC_Status AD_Reference_ID=300058 */
	public static final int HC_STATUS_AD_Reference_ID=300058;
	/** Active = A */
	public static final String HC_STATUS_Active = "A";
	/** Terminate = T */
	public static final String HC_STATUS_Terminate = "T";
	/** Pending = P */
	public static final String HC_STATUS_Pending = "P";
	/** Leave = L */
	public static final String HC_STATUS_Leave = "L";
	/** InActive = IA */
	public static final String HC_STATUS_InActive = "IA";
	/** Set HC Status.
		@param HC_Status HC Status	  */
	public void setHC_Status (String HC_Status)
	{

		set_Value (COLUMNNAME_HC_Status, HC_Status);
	}

	/** Get HC Status.
		@return HC Status	  */
	public String getHC_Status () 
	{
		return (String)get_Value(COLUMNNAME_HC_Status);
	}

	/** Set Work End Date.
		@param HC_WorkEndDate Work End Date	  */
	public void setHC_WorkEndDate (Timestamp HC_WorkEndDate)
	{
		set_Value (COLUMNNAME_HC_WorkEndDate, HC_WorkEndDate);
	}

	/** Get Work End Date.
		@return Work End Date	  */
	public Timestamp getHC_WorkEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_WorkEndDate);
	}

	/** Set WorkPeriodDate.
		@param HC_WorkPeriodDate WorkPeriodDate	  */
	public void setHC_WorkPeriodDate (String HC_WorkPeriodDate)
	{
		set_Value (COLUMNNAME_HC_WorkPeriodDate, HC_WorkPeriodDate);
	}

	/** Get WorkPeriodDate.
		@return WorkPeriodDate	  */
	public String getHC_WorkPeriodDate () 
	{
		return (String)get_Value(COLUMNNAME_HC_WorkPeriodDate);
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

	/** Set Work Start Date Baru.
		@param HC_WorkStartDate2 Work Start Date Baru	  */
	public void setHC_WorkStartDate2 (Timestamp HC_WorkStartDate2)
	{
		set_Value (COLUMNNAME_HC_WorkStartDate2, HC_WorkStartDate2);
	}

	/** Get Work Start Date Baru.
		@return Work Start Date Baru	  */
	public Timestamp getHC_WorkStartDate2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HC_WorkStartDate2);
	}

	/** Set IHC_JobDayaChange.
		@param IHC_JobDataChange_ID IHC_JobDayaChange	  */
	public void setIHC_JobDataChange_ID (int IHC_JobDataChange_ID)
	{
		if (IHC_JobDataChange_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IHC_JobDataChange_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IHC_JobDataChange_ID, Integer.valueOf(IHC_JobDataChange_ID));
	}

	/** Get IHC_JobDayaChange.
		@return IHC_JobDayaChange	  */
	public int getIHC_JobDataChange_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IHC_JobDataChange_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getIHC_JobDataChange_ID()));
    }

	/** Set IHC_JobDataChange_UU.
		@param IHC_JobDataChange_UU IHC_JobDataChange_UU	  */
	public void setIHC_JobDataChange_UU (String IHC_JobDataChange_UU)
	{
		set_Value (COLUMNNAME_IHC_JobDataChange_UU, IHC_JobDataChange_UU);
	}

	/** Get IHC_JobDataChange_UU.
		@return IHC_JobDataChange_UU	  */
	public String getIHC_JobDataChange_UU () 
	{
		return (String)get_Value(COLUMNNAME_IHC_JobDataChange_UU);
	}

	/** Set Cancelled.
		@param IsCancelled 
		The transaction was cancelled
	  */
	public void setIsCancelled (boolean IsCancelled)
	{
		set_ValueNoCheck (COLUMNNAME_IsCancelled, Boolean.valueOf(IsCancelled));
	}

	/** Get Cancelled.
		@return The transaction was cancelled
	  */
	public boolean isCancelled () 
	{
		Object oo = get_Value(COLUMNNAME_IsCancelled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Nomor SK Baru.
		@param NomorSK2 Nomor SK Baru	  */
	public void setNomorSK2 (String NomorSK2)
	{
		set_Value (COLUMNNAME_NomorSK2, NomorSK2);
	}

	/** Get Nomor SK Baru.
		@return Nomor SK Baru	  */
	public String getNomorSK2 () 
	{
		return (String)get_Value(COLUMNNAME_NomorSK2);
	}

	/** Set Original Service Date.
		@param OriginalServiceData Original Service Date	  */
	public void setOriginalServiceData (Timestamp OriginalServiceData)
	{
		set_Value (COLUMNNAME_OriginalServiceData, OriginalServiceData);
	}

	/** Get Original Service Date.
		@return Original Service Date	  */
	public Timestamp getOriginalServiceData () 
	{
		return (Timestamp)get_Value(COLUMNNAME_OriginalServiceData);
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