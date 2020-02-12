package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;

public class MPLClosing extends X_C_PLClosing implements DocAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1352026977918293093L;
	/**	Process Message 			*/
	private String	m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;
	
	public MPLClosing(Properties ctx, int C_PLClosing_ID, String trxName) {
		super(ctx, C_PLClosing_ID, trxName);
		
	}

	public MPLClosing(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	protected boolean beforeSave(boolean newRecord)
	{
			if(is_ValueChanged(MPLClosing.COLUMNNAME_C_Year_ID) && getTypePL().equalsIgnoreCase(MPLClosing.TYPEPL_Year))
			{	
				log.info("Before Save in MPLDimension....");
				MQuery whereClause=new MQuery(MPLClosing.Table_Name);
				whereClause.addRestriction(MPLClosing.COLUMNNAME_AD_Client_ID, MQuery.EQUAL, getAD_Client_ID());
				whereClause.addRestriction(MPLClosing.COLUMNNAME_C_Year_ID, MQuery.EQUAL, getC_Year_ID());
				Query listOfProdCat =new Query(getCtx(), MPLClosing.Table_Name, whereClause.getWhereClause(), get_TrxName());
				List<MPLClosing> closing=listOfProdCat.list();
				log.info(closing.size()+"");
				if(closing!=null && closing.size()>0)
				{
					log.saveError("Error", "Year is already exist for PL Closing");
					return false;
				}
			}
			else if((is_ValueChanged(MPLClosing.COLUMNNAME_C_Year_ID) || is_ValueChanged(MPLClosing.COLUMNNAME_C_Period_ID)) && getTypePL().equalsIgnoreCase(MPLClosing.TYPEPL_Period))
			{
				MQuery whereClause=new MQuery(MPLClosing.Table_Name);
				whereClause.addRestriction(MPLClosing.COLUMNNAME_AD_Client_ID, MQuery.EQUAL, getAD_Client_ID());
				whereClause.addRestriction(MPLClosing.COLUMNNAME_C_Period_ID, MQuery.EQUAL, getC_Period_ID());
				whereClause.addRestriction(MPLClosing.COLUMNNAME_C_Year_ID, MQuery.EQUAL, getC_Year_ID());
				Query listOfDimen =new Query(getCtx(), MPLClosing.Table_Name, whereClause.getWhereClause(), get_TrxName());
				List<MPLClosing> closing=listOfDimen.list();
				log.info(closing.size()+"");
				if(closing!=null && closing.size()>0)
				{
					log.saveError("Error", "Year and Period is already exist for PL Closing");
					return false;
				}
			}
		return true;
	}

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
		// TODO Auto-generated method stub
		return false;
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
		return m_processMsg;
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
			+ "' WHERE C_PLClosing_ID=" + getC_PLClosing_ID();
		int noLine = DB.executeUpdateEx("UPDATE C_PLClosingLine " + set, get_TrxName());
		log.fine("setProcessed - " + processed + " - Lines=" + noLine);
	}	//	setProcessed
	
	
}
