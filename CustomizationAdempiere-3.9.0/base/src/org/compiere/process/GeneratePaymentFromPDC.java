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

package org.compiere.process;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.businessView.utils.UtilPDCPayment;
import org.compiere.model.MBankAccount;
import org.compiere.model.MPayment;
import org.compiere.model.MPostDatedCheque;
import org.compiere.model.MPostDatedChequeLine;
import org.compiere.model.MQuery;
import org.compiere.model.Query;
import org.compiere.util.DB;

import test.functional.MPaymentTest;

/** Generated Process for (GeneratePaymentFromPDC)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class GeneratePaymentFromPDC extends GeneratePaymentFromPDCAbstract
{
	private Integer p_C_DocType_ID=0;
	private Integer p_C_BankAccount_ID=0;
	private Integer p_recordID=0;
	private Integer p_ad_client_ID=0;
	private Timestamp currentDate=new Timestamp(System.currentTimeMillis());
	
	@Override
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_DocType_ID"))
				p_C_DocType_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BankAccount_ID")) // BF [ 1754889 ] Create Package error
				p_C_BankAccount_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		p_recordID=getRecord_ID();
		p_ad_client_ID=getAD_Client_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		String returnMessage="PaymentCreated-";
		log.info("p_C_DocType_ID "+p_C_DocType_ID+" p_C_BankAccount_ID  "+p_C_BankAccount_ID+" p_recordID "+p_recordID);
		
		MPostDatedCheque pdc=new MPostDatedCheque(getCtx(), p_recordID, get_TrxName());
		
		if(p_C_BankAccount_ID==0)
			p_C_BankAccount_ID=pdc.getC_BankAccount_ID();
		Boolean checks=UtilPDCPayment.checksBeforePaymentCreate(pdc);
		if(checks)
		{
			if(pdc.isMultiCheque()) /// Create multiple Payments according to Check Date
			{
				StringBuffer returnMess=new StringBuffer("[");
				MQuery whereClause=new MQuery(MPostDatedChequeLine.Table_Name);
				whereClause.addRestriction(MPostDatedChequeLine.COLUMNNAME_AD_Client_ID, MQuery.EQUAL, getAD_Client_ID());
				whereClause.addRestriction(MPostDatedChequeLine.COLUMNNAME_C_PostDatedCheque_ID, MQuery.EQUAL, pdc.getC_PostDatedCheque_ID());
				whereClause.addRestriction(MPostDatedChequeLine.COLUMNNAME_IsActive, MQuery.EQUAL, true);
				whereClause.addRestriction(MPostDatedChequeLine.COLUMNNAME_CheckDate, MQuery.LESS, currentDate);
				Query listOfPDCLine =new Query(getCtx(), MPostDatedChequeLine.Table_Name, whereClause.getWhereClause(), get_TrxName());
				List<MPostDatedChequeLine> pdcl=listOfPDCLine.list();
				if(pdcl!=null && pdcl.size()>0)
				{
					for(MPostDatedChequeLine pdcLine:pdcl)
					{
						pdc.setPayAmt(pdcLine.getPayAmt());
						pdc.setCheckDate(pdcLine.getCheckDate());
						pdc.setCheckNo(pdcLine.getCheckNo());
						pdc.setValidMonth(String.valueOf(pdcLine.getValidMonth()));
						pdc.setAccountNo(pdcLine.getAccountNo());
						pdc.setAccountName(pdcLine.getAccountName());
						pdc.setMicr(pdcLine.getMicr());
						MPayment payment=createPaymentFromPDC(pdc,p_C_BankAccount_ID,p_C_DocType_ID);
						if(payment!=null)
						{
							returnMess.append(payment.getDocumentNo()+",");
							pdcLine.setC_Payment_ID(payment.getC_Payment_ID());
							if(pdcLine.save(get_TrxName()))
							DB.commit(true, get_TrxName());
							log.info("PDC Line record ::: "+pdcLine.toString());
						}
					}
				}
				returnMess.append("]");
				returnMessage=returnMessage+returnMess.toString();
			}
			else
			{				
				Boolean checkRefPdc=checkAndupdateOldPDCPaymentStatus(pdc);				
				MPayment payment=createPaymentFromPDC(pdc,p_C_BankAccount_ID,p_C_DocType_ID);
				if(payment!=null)
				{
					returnMessage=returnMessage+payment.getDocumentNo();
					pdc.setC_Payment_ID(payment.getC_Payment_ID());
//					if(checkRefPdc) /// it is use for Complete button 
					pdc.setPaymentStatus(MPostDatedCheque.PAYMENTSTATUS_RealizationIn_Progress);
//					else /// it is use for Complete button
//					pdc.setPaymentStatus(MPostDatedCheque.PAYMENTSTATUS_PDCReveivedPDCIssued);	/// it is use for Complete button
					if(pdc.save(get_TrxName()))
					DB.commit(true, get_TrxName());
				}
				else
					return "Payment is not created";
			}
		}
		return returnMessage;
	}
	
	/**
	 * Check and update Old PDC Payment Status
	 * @param pdc
	 * @return
	 */
	private Boolean checkAndupdateOldPDCPaymentStatus(MPostDatedCheque pdc) 
	{
		if(pdc.getPDC_REF_PAYMENT()>0)
		{
			/// Convert This thing in Sql update Query to save the object
			String SQLupdate="update C_PostDatedCheque set PaymentStatus=? where C_PostDatedCheque_ID=? where Ad_Client_ID=?";
			int updateRet=DB.executeUpdateEx(SQLupdate, new Object[]{MPostDatedCheque.PAYMENTSTATUS_Bounced,pdc.getPDC_REF_PAYMENT(),pdc.getAD_Client_ID()}, get_TrxName());
			if(updateRet>0)
			return false;
			else
			return true;	
		}
		else
		return true;
	}

	/**
	 * @Mukesh : create Payment from PDC
	 * @param pdc
	 * @param p_C_BankAccount_ID
	 * @param p_C_DocType_ID
	 * @param map 
	 * @return
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	private MPayment createPaymentFromPDC(MPostDatedCheque pdc, Integer p_C_BankAccount_ID, Integer p_C_DocType_ID) throws IllegalStateException, SQLException
	{		
		MPayment payment=new MPayment(getCtx(), 0, get_TrxName()); /// For new Payment Created
		payment.setAD_Org_ID(pdc.getAD_Org_ID());
		payment.setC_BankAccount_ID(p_C_BankAccount_ID);
		payment.setC_DocType_ID(p_C_DocType_ID);
		payment.setDateTrx(currentDate);
		payment.setDateAcct(currentDate);
		payment.setC_BPartner_ID(pdc.getC_BPartner_ID());
		payment.setC_Order_ID(pdc.getC_Order_ID());
		payment.setC_Invoice_ID(pdc.getC_Invoice_ID());
		if(pdc.getC_Charge_ID()>0)
		{
			payment.setC_Charge_ID(pdc.getC_Charge_ID());
			payment.setC_Tax_ID(pdc.getC_Tax_ID());
			payment.setTaxAmount(pdc.getTaxAmount());
		}
		payment.setPayAmt(pdc.getPayAmt());		
		payment.setC_Currency_ID(pdc.getC_Currency_ID());
		payment.setDiscountAmt(pdc.getDiscountAmt());
		payment.setWriteOffAmt(pdc.getWriteOffAmt());
		payment.setTenderType(MPayment.TENDERTYPE_Check);
		payment.setCheckDate(pdc.getCheckDate());
		payment.setCheckNo(pdc.getCheckNo());
		payment.setValidMonth(String.valueOf(pdc.getValidMonth()));
		payment.setMicr(pdc.getMicr());
		payment.setAccountNo(pdc.getAccountNo());
		payment.setA_Name(pdc.getAccountName());
		payment.setC_PostDatedCheque_ID(pdc.getC_PostDatedCheque_ID());
		if(payment.save(get_TrxName()))
		{
			DB.commit(true, get_TrxName());
			return payment;
		}
		else
			return null;
	}
}