package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public final class MPLDimension extends X_C_PLDimension {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1081092244202459416L;

	public MPLDimension(Properties ctx, int C_PLDimension_ID, String trxName) {
		super(ctx, C_PLDimension_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MPLDimension(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	protected boolean beforeSave(boolean newRecord)
	{
		if(getTypePL().equalsIgnoreCase(MPLDimension.TYPEPL_Year) && (is_ValueChanged(MPLDimension.COLUMNNAME_C_Year_ID) || is_ValueChanged(MPLDimension.COLUMNNAME_TypePL)) )
		{	
			log.info("Before Save in MPLDimension....");
			MQuery whereClause=new MQuery(MPLDimension.Table_Name);
			whereClause.addRestriction(MPLDimension.COLUMNNAME_AD_Client_ID, MQuery.EQUAL, getAD_Client_ID());
			whereClause.addRestriction(MPLDimension.COLUMNNAME_TypePL, MQuery.EQUAL, MPLDimension.TYPEPL_Year);
			whereClause.addRestriction(MPLDimension.COLUMNNAME_C_Year_ID, MQuery.EQUAL, getC_Year_ID());
			Query listOfProdCat =new Query(getCtx(), MPLDimension.Table_Name, whereClause.getWhereClause(), get_TrxName());
			List<MPLDimension> dimension=listOfProdCat.list();
			log.info(dimension.size()+"");
			if(dimension!=null && dimension.size()>0)
			{
				
				log.saveError("Error", "Year is already exist for PL Dimension");
				return false;
			}
			
		}
		else if(getTypePL().equalsIgnoreCase(MPLDimension.TYPEPL_Period) && (is_ValueChanged(MPLDimension.COLUMNNAME_C_Period_ID) || is_ValueChanged(MPLDimension.COLUMNNAME_C_Year_ID) || is_ValueChanged(MPLDimension.COLUMNNAME_TypePL)))
		{
			MQuery whereClause=new MQuery(MPLDimension.Table_Name);
			whereClause.addRestriction(MPLDimension.COLUMNNAME_AD_Client_ID, MQuery.EQUAL, getAD_Client_ID());
			whereClause.addRestriction(MPLDimension.COLUMNNAME_TypePL, MQuery.EQUAL, MPLDimension.TYPEPL_Period);
			whereClause.addRestriction(MPLDimension.COLUMNNAME_C_Period_ID, MQuery.EQUAL, getC_Period_ID());
			whereClause.addRestriction(MPLDimension.COLUMNNAME_C_Year_ID, MQuery.EQUAL, getC_Year_ID());
			Query listOfDimen =new Query(getCtx(), MPLDimension.Table_Name, whereClause.getWhereClause(), get_TrxName());
			List<MPLDimension> dimension=listOfDimen.list();
			log.info(dimension.size()+"");
			if(dimension!=null && dimension.size()>0)
			{
				log.saveError("Error", "Year and Period is already exist for PL Dimension");
				return false;
			}
		}
		return true;
	}
	
}
