package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Msg;

public class MCostElementLine extends X_M_CostElementLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6680577785288680154L;
	
	public MCostElementLine(Properties ctx, int M_CostElementLine_ID, String trxName) {
		super(ctx, M_CostElementLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCostElementLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		// remove duplicate  
		if(newRecord)
		{	
			List<Object> params=new ArrayList<Object>();
			params.add(this.getRef_M_CostElement_ID());
			params.add(this.getM_CostElement_ID());
			params.add(this.getAD_Client_ID());
			String sql="select count(*) from M_CostElementLine where Ref_M_CostElement_ID=? and M_CostElement_ID=? and ad_client_id=?";
			Integer test=DB.getSQLValue(get_TrxName(), sql, params);
			if(test.compareTo(0)>0)
			{
				log.saveError("AlreadyExists", Msg.getElement(getCtx(), MCostElementLine.COLUMNNAME_Ref_M_CostElement_ID));
				return false;
			}
		}		
		if (getAD_Org_ID() != 0)
			setAD_Org_ID(0);
		return true;
	}
}
