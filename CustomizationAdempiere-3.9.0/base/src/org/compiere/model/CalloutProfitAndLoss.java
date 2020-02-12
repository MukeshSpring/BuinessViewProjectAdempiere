package org.compiere.model;

import java.util.Properties;

import org.compiere.util.Env;

public class CalloutProfitAndLoss extends CalloutEngine {

	
	/**
	 * Update Currency
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * org.compiere.model.CalloutProfitAndLoss.accountingSchema
	 */
		public String accountingSchema(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value)
		{
			Integer C_AcctSchema_ID=(Integer)value;
			if (isCalloutActive () // assuming it is resetting value
					|| C_AcctSchema_ID == null || C_AcctSchema_ID.intValue () == 0)
					return "";
			
			MAcctSchema acctSch=new MAcctSchema(Env.getCtx(), C_AcctSchema_ID, null);
			if(acctSch!=null)
				mTab.setValue ("C_Currency_ID", acctSch.getC_Currency_ID());
			
			return "";
		}
	/**
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 * org.compiere.model.CalloutProfitAndLoss.profitDimension
	 */
		public String profitDimension(Properties ctx, int WindowNo, GridTab mTab,
				GridField mField, Object value)
			{
				Integer C_PLDimension_ID=(Integer)value;
				if (isCalloutActive () // assuming it is resetting value
						|| C_PLDimension_ID == null || C_PLDimension_ID.intValue () == 0)
						return "";
				
				MPLDimension plDimension=new MPLDimension(Env.getCtx(), C_PLDimension_ID, null);
				if(plDimension!=null)
				{
					mTab.setValue ("TypePL", plDimension.getTypePL());
					mTab.setValue ("C_Year_ID", plDimension.getC_Year_ID());
					if(plDimension.getC_Period_ID()>0)
					mTab.setValue ("C_Period_ID", plDimension.getC_Period_ID());
				}
				return "";
			}
		
		
		
}
