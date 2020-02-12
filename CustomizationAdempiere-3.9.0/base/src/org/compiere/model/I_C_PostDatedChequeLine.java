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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_PostDatedChequeLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_C_PostDatedChequeLine 
{

    /** TableName=C_PostDatedChequeLine */
    public static final String Table_Name = "C_PostDatedChequeLine";

    /** AD_Table_ID=1000008 */
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

    /** Column name AccountName */
    public static final String COLUMNNAME_AccountName = "AccountName";

	/** Set Account Name.
	  * This is new field to add Account Name of Bank
	  */
	public void setAccountName (String AccountName);

	/** Get Account Name.
	  * This is new field to add Account Name of Bank
	  */
	public String getAccountName();

    /** Column name AccountNo */
    public static final String COLUMNNAME_AccountNo = "AccountNo";

	/** Set Account No.
	  * Account Number
	  */
	public void setAccountNo (String AccountNo);

	/** Get Account No.
	  * Account Number
	  */
	public String getAccountNo();

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name C_PostDatedChequeLine_ID */
    public static final String COLUMNNAME_C_PostDatedChequeLine_ID = "C_PostDatedChequeLine_ID";

	/** Set C_PostDatedChequeLine ID	  */
	public void setC_PostDatedChequeLine_ID (int C_PostDatedChequeLine_ID);

	/** Get C_PostDatedChequeLine ID	  */
	public int getC_PostDatedChequeLine_ID();

    /** Column name C_PostDatedCheque_ID */
    public static final String COLUMNNAME_C_PostDatedCheque_ID = "C_PostDatedCheque_ID";

	/** Set Post Dated Cheque	  */
	public void setC_PostDatedCheque_ID (int C_PostDatedCheque_ID);

	/** Get Post Dated Cheque	  */
	public int getC_PostDatedCheque_ID();

	public I_C_PostDatedCheque getC_PostDatedCheque() throws RuntimeException;

    /** Column name CheckDate */
    public static final String COLUMNNAME_CheckDate = "CheckDate";

	/** Set Check Date.
	  * New field date of check
	  */
	public void setCheckDate (Timestamp CheckDate);

	/** Get Check Date.
	  * New field date of check
	  */
	public Timestamp getCheckDate();

    /** Column name CheckNo */
    public static final String COLUMNNAME_CheckNo = "CheckNo";

	/** Set Check No.
	  * Check Number
	  */
	public void setCheckNo (String CheckNo);

	/** Get Check No.
	  * Check Number
	  */
	public String getCheckNo();

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

    /** Column name LineNo */
    public static final String COLUMNNAME_LineNo = "LineNo";

	/** Set Line.
	  * Line No
	  */
	public void setLineNo (int LineNo);

	/** Get Line.
	  * Line No
	  */
	public int getLineNo();

    /** Column name Micr */
    public static final String COLUMNNAME_Micr = "Micr";

	/** Set Micr.
	  * Combination of routing no, account and check no
	  */
	public void setMicr (String Micr);

	/** Get Micr.
	  * Combination of routing no, account and check no
	  */
	public String getMicr();

    /** Column name PayAmt */
    public static final String COLUMNNAME_PayAmt = "PayAmt";

	/** Set Payment amount.
	  * Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt);

	/** Get Payment amount.
	  * Amount being paid
	  */
	public BigDecimal getPayAmt();

    /** Column name PaymentStatus */
    public static final String COLUMNNAME_PaymentStatus = "PaymentStatus";

	/** Set Payment Status.
	  * This is new field to status of Payment
	  */
	public void setPaymentStatus (String PaymentStatus);

	/** Get Payment Status.
	  * This is new field to status of Payment
	  */
	public String getPaymentStatus();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

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

    /** Column name ValidMonth */
    public static final String COLUMNNAME_ValidMonth = "ValidMonth";

	/** Set Valid Month.
	  * This is new field to check valid month of check
	  */
	public void setValidMonth (int ValidMonth);

	/** Get Valid Month.
	  * This is new field to check valid month of check
	  */
	public int getValidMonth();
}
