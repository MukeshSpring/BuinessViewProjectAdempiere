/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for C_PostDatedChequeLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_C_PostDatedChequeLine extends PO implements I_C_PostDatedChequeLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181007L;

    /** Standard Constructor */
    public X_C_PostDatedChequeLine (Properties ctx, int C_PostDatedChequeLine_ID, String trxName)
    {
      super (ctx, C_PostDatedChequeLine_ID, trxName);
      /** if (C_PostDatedChequeLine_ID == 0)
        {
			setC_PostDatedChequeLine_ID (0);
			setCheckDate (new Timestamp( System.currentTimeMillis() ));
			setCheckNo (null);
			setLineNo (0);
// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM C_PostDatedChequeLine WHERE C_PostDatedCheque_ID=@C_PostDatedCheque_ID@
        } */
    }

    /** Load Constructor */
    public X_C_PostDatedChequeLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_PostDatedChequeLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account Name.
		@param AccountName 
		This is new field to add Account Name of Bank
	  */
	public void setAccountName (String AccountName)
	{
		set_Value (COLUMNNAME_AccountName, AccountName);
	}

	/** Get Account Name.
		@return This is new field to add Account Name of Bank
	  */
	public String getAccountName () 
	{
		return (String)get_Value(COLUMNNAME_AccountName);
	}

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
	}

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_Value (COLUMNNAME_C_Payment_ID, null);
		else 
			set_Value (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_PostDatedChequeLine ID.
		@param C_PostDatedChequeLine_ID C_PostDatedChequeLine ID	  */
	public void setC_PostDatedChequeLine_ID (int C_PostDatedChequeLine_ID)
	{
		if (C_PostDatedChequeLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PostDatedChequeLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PostDatedChequeLine_ID, Integer.valueOf(C_PostDatedChequeLine_ID));
	}

	/** Get C_PostDatedChequeLine ID.
		@return C_PostDatedChequeLine ID	  */
	public int getC_PostDatedChequeLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PostDatedChequeLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_PostDatedCheque getC_PostDatedCheque() throws RuntimeException
    {
		return (I_C_PostDatedCheque)MTable.get(getCtx(), I_C_PostDatedCheque.Table_Name)
			.getPO(getC_PostDatedCheque_ID(), get_TrxName());	}

	/** Set Post Dated Cheque.
		@param C_PostDatedCheque_ID Post Dated Cheque	  */
	public void setC_PostDatedCheque_ID (int C_PostDatedCheque_ID)
	{
		if (C_PostDatedCheque_ID < 1) 
			set_Value (COLUMNNAME_C_PostDatedCheque_ID, null);
		else 
			set_Value (COLUMNNAME_C_PostDatedCheque_ID, Integer.valueOf(C_PostDatedCheque_ID));
	}

	/** Get Post Dated Cheque.
		@return Post Dated Cheque	  */
	public int getC_PostDatedCheque_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PostDatedCheque_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Check Date.
		@param CheckDate 
		New field date of check
	  */
	public void setCheckDate (Timestamp CheckDate)
	{
		set_Value (COLUMNNAME_CheckDate, CheckDate);
	}

	/** Get Check Date.
		@return New field date of check
	  */
	public Timestamp getCheckDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CheckDate);
	}

	/** Set Check No.
		@param CheckNo 
		Check Number
	  */
	public void setCheckNo (String CheckNo)
	{
		set_Value (COLUMNNAME_CheckNo, CheckNo);
	}

	/** Get Check No.
		@return Check Number
	  */
	public String getCheckNo () 
	{
		return (String)get_Value(COLUMNNAME_CheckNo);
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Micr.
		@param Micr 
		Combination of routing no, account and check no
	  */
	public void setMicr (String Micr)
	{
		set_Value (COLUMNNAME_Micr, Micr);
	}

	/** Get Micr.
		@return Combination of routing no, account and check no
	  */
	public String getMicr () 
	{
		return (String)get_Value(COLUMNNAME_Micr);
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** PaymentStatus AD_Reference_ID=1000002 */
	public static final int PAYMENTSTATUS_AD_Reference_ID=1000002;
	/** PDC Received = 0 */
	public static final String PAYMENTSTATUS_PDCReceived = "0";
	/** Realization In-Progress = 1 */
	public static final String PAYMENTSTATUS_RealizationIn_Progress = "1";
	/** PDC Realized = 2 */
	public static final String PAYMENTSTATUS_PDCRealized = "2";
	/** Bounced = 3 */
	public static final String PAYMENTSTATUS_Bounced = "3";
	/** Replaced = 4 */
	public static final String PAYMENTSTATUS_Replaced = "4";
	/** Set Payment Status.
		@param PaymentStatus 
		This is new field to status of Payment
	  */
	public void setPaymentStatus (String PaymentStatus)
	{

		set_Value (COLUMNNAME_PaymentStatus, PaymentStatus);
	}

	/** Get Payment Status.
		@return This is new field to status of Payment
	  */
	public String getPaymentStatus () 
	{
		return (String)get_Value(COLUMNNAME_PaymentStatus);
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

	/** Set Valid Month.
		@param ValidMonth 
		This is new field to check valid month of check
	  */
	public void setValidMonth (int ValidMonth)
	{
		set_Value (COLUMNNAME_ValidMonth, Integer.valueOf(ValidMonth));
	}

	/** Get Valid Month.
		@return This is new field to check valid month of check
	  */
	public int getValidMonth () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ValidMonth);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}