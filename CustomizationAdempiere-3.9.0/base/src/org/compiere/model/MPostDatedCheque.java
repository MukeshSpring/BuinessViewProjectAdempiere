package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.util.DB;
import org.compiere.util.Trx;

public final class MPostDatedCheque extends X_C_PostDatedCheque implements DocAction, ProcessCall {

	/**
	 * 
	 */
	private static final long serialVersionUID = 376775728728643440L;
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;
	
	public MPostDatedCheque(Properties ctx, int C_PostDatedCheque_ID, String trxName) {
		super(ctx, C_PostDatedCheque_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPostDatedCheque(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 	Called before Save for Pre-Save Operation
	 * 	@param newRecord new record
	 *	@return true if record can be saved
	 */
	protected boolean beforeSave(boolean newRecord)
	{
		System.out.println("newRecord"+newRecord);
		
		// @Mukesh- Post Dated Payment Tender Type Checks... @20180925
		if(is_ValueChanged(MPostDatedCheque.COLUMNNAME_CheckDate) && getCheckDate()!=null)
		{
			Timestamp postDate=getCheckDate();
			Timestamp currDate=new Timestamp(System.currentTimeMillis());
			
			if(currDate.compareTo(postDate)>0)
			{	
				log.saveError("Error", "Can not entered pre or current PDC Check Date");
				return false;
			}
		}
		
		// Check Duplicate check no for PDP document type
		
		if(is_ValueChanged(MPostDatedCheque.COLUMNNAME_CheckNo) || is_ValueChanged(MPostDatedCheque.COLUMNNAME_C_DocType_ID))
		{
			MDocType docTyp=new MDocType(getCtx(), getC_DocType_ID(), get_TrxName());
			if(docTyp.getDocBaseType().equalsIgnoreCase(MDocType.DOCBASETYPE_PDCPayable))
			{
				System.out.println(getCheckNo());
				MQuery whereClause=new MQuery(MPostDatedCheque.Table_Name);
				whereClause.addRestriction(MPostDatedCheque.COLUMNNAME_AD_Client_ID, MQuery.EQUAL, getAD_Client_ID());
//				whereClause.addRestriction(MPayment.COLUMNNAME_C_BankAccount_ID, MQuery.EQUAL, getC_BankAccount_ID());
				whereClause.addRestriction(MPostDatedCheque.COLUMNNAME_CheckNo, MQuery.LIKE, getCheckNo());
				Query listOfProdCat =new Query(getCtx(), MPostDatedCheque.Table_Name, whereClause.getWhereClause(), get_TrxName());
				List<MPostDatedCheque> payments=listOfProdCat.list();
				if(payments!=null && payments.size()>0)
				{
					System.out.println(payments.size());
					log.saveError("Error", "Check No is already exist for PDC Payable payment");
					return false;
				}
			}			
		}

		return true;
	}	//	beforeSave

	@Override
	public boolean processIt(String processAction) throws Exception {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String prepareIt() {
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		setPaymentStatus(MPostDatedCheque.PAYMENTSTATUS_PDCReveivedPDCIssued);
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean approveIt() {
		log.info(toString());
		setIsApproved(true);
		return true;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String completeIt() {
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		//
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}

	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reActivateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		String set = "SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE "+MPostDatedCheque.Table_ID+"=" + getC_Order_ID();
		int noLine = DB.executeUpdateEx("UPDATE "+MPostDatedCheque.Table_Name+" "+ set, get_TrxName());
		log.fine("setProcessed - " + processed + " - Lines=" + noLine);
	}	//	setProcessed

	@Override
	public boolean startProcess(Properties ctx, ProcessInfo pi, Trx trx) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
