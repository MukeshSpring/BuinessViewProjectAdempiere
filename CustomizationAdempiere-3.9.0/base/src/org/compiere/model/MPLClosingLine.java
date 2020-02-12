package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MPLClosingLine extends X_C_PLClosingLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3785340854158483233L;

	public MPLClosingLine(Properties ctx, int C_PLClosingLine_ID, String trxName) {
		super(ctx, C_PLClosingLine_ID, trxName);
		
	}

	public MPLClosingLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	public MPLClosingLine (MPLClosing closing)
	{
		this (closing.getCtx(), 0, closing.get_TrxName());
		if (closing.get_ID() == 0)
			throw new IllegalArgumentException("Header not saved");
		setC_PLClosing_ID(closing.getC_PLClosing_ID());	//	parent
	}	//	MOrderLine
	
	protected boolean beforeSave(boolean newRecord)
	{
//		Get Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM C_PLClosingLine WHERE C_PLClosing_ID=?";
			int ii = DB.getSQLValue (get_TrxName(), sql, getC_PLClosing_ID());
			setLine (ii);
		}
		return true;
	}

	
}
