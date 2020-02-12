package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MPostDatedCheque;

public class Doc_PostDatedChequeLATER extends Doc {

	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_PostDatedChequeLATER (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MPostDatedCheque.class, rs, null, trxName);
	}	//	Doc_Order

	@Override
	protected String loadDocumentDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as) {
		// TODO Auto-generated method stub
		return null;
	}

}
