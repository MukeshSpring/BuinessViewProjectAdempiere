package org.businessView.utils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.compiere.model.MPLClosing;
import org.compiere.util.DB;

public class UtilPLClosing {

	/**
	 * Calculated PL Closing Dr and Cr Amount
	 * @param factDetails
	 * @return
	 */
	public static final String TOTAL_DR="TOTAL_DR";
	public static final String TOTAL_CR="TOTAL_CR";
	public static final String TOTAL_PROFIT_LOSS="TOTAL_PROFIT_LOSS";
	public static Map<String, BigDecimal> getPLClosingDrCrAmount(MPLClosing plClose) 
	{
		Map<String,BigDecimal> plclosingData=new HashMap<String, BigDecimal>(); 
		String sql=" SELECT SUM(cpc.amtacctdr) as amtacctdr, "+
		  " SUM(cpc.amtacctcr) as amtacctcr, "+
		 " SUM(cpc.amtsourcedr), "+
		 " SUM(cpc.amtsourcecr), "+
		 " (SUM(cpc.amtacctcr)- SUM(cpc.amtacctdr)) AS TotalRevenue "+
		" FROM C_PLClosingLine cpc "+
		" WHERE cpc.C_PLClosing_ID=? and cpc.AD_CLIENT_ID=?";
		
		//
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, plClose.get_TrxName());
			pstmt.setInt(1, plClose.getC_PLClosing_ID());
			pstmt.setInt(2, plClose.getAD_Client_ID());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				plclosingData.put(TOTAL_DR, rs.getBigDecimal("amtacctdr"));
				plclosingData.put(TOTAL_CR, rs.getBigDecimal("amtacctcr"));
				plclosingData.put(TOTAL_PROFIT_LOSS, rs.getBigDecimal("TotalRevenue"));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex){}
		//	Clean Up
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1){}
		pstmt = null;
		
		return plclosingData;
	}

}
