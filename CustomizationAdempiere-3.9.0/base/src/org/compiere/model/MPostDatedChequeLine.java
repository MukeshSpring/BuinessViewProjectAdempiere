package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;

public final class MPostDatedChequeLine extends X_C_PostDatedChequeLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 376775728728643440L;
	
	public MPostDatedChequeLine(Properties ctx, int C_PostDatedCheque_ID, String trxName) {
		super(ctx, C_PostDatedCheque_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPostDatedChequeLine(Properties ctx, ResultSet rs, String trxName) {
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
		System.out.println("newRecord-------"+newRecord);
	// @Mukesh- Post Dated Payment Tender Type Checks... @20180928
		if(is_ValueChanged(MPostDatedChequeLine.COLUMNNAME_CheckDate) && getCheckDate()!=null)
		{
			Timestamp postDate=getCheckDate();
			Timestamp currDate=new Timestamp(System.currentTimeMillis());
			
			if(currDate.compareTo(postDate)>0)
			{	
				log.saveError("Error", "Can not entered pre or current PDC Check Date");
				return false;
			}
		}
		return true;
	}	//	beforeSave
	
	
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		@SuppressWarnings("unused")
		Boolean check=updatePDCCheckAmount();
		return success;
	}	//	afterSave
	
	protected boolean afterDelete (boolean success)
	{
		Boolean check=updatePDCCheckAmount();
		System.out.println(check+"=================");
		return success;
	} 	//	afterDelete
	
	protected boolean beforeDelete ()
	{
		return true;
	} 	//	beforeDelete
	
	
	private boolean updatePDCCheckAmount()
	{
		String sql="select sum(PAYAMT) from  C_PostDatedChequeLine where C_PostDatedCheque_ID=?";
		BigDecimal val=DB.getSQLValueBD(get_TrxName(), sql, new Object[]{getC_PostDatedCheque_ID()});
		MPostDatedCheque pdc=new MPostDatedCheque(getCtx(), getC_PostDatedCheque_ID(), get_TrxName());
		pdc.setPayAmt(val);
		if(pdc.save(get_TrxName()))
		{
			try {
				DB.commit(true, get_TrxName());
			} catch (IllegalStateException | SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
