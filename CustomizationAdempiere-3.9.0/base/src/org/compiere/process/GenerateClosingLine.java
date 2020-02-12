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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.businessView.utils.UtilGeneral;
import org.businessView.utils.UtilPLClosing;
import org.compiere.model.MElementValue;
import org.compiere.model.MFactAcct;
import org.compiere.model.MPLClosing;
import org.compiere.model.MPLClosingLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MYear;
import org.compiere.util.DB;

/** Generated Process for (GenerateClosingLine)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class GenerateClosingLine extends GenerateClosingLineAbstract
{
	//private Timestamp currentDate=new Timestamp(System.currentTimeMillis());
	private Integer p_recordID=0;
	@Override
	protected void prepare()
	{
		super.prepare();
		p_recordID=getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		log.info("p_recordID "+p_recordID) ;
		
		if(p_recordID==0)
			log.log(Level.SEVERE, "PL Cloing is not exist" + p_recordID);
		List<Integer> factDetails=new LinkedList<Integer>();
		MPLClosing plClose=new MPLClosing(getCtx(), p_recordID, get_TrxName());
		if(plClose.getTypePL().equalsIgnoreCase(MPLClosing.TYPEPL_Period))
		{
			MPeriod period=new MPeriod(getCtx(), plClose.getC_Period_ID(), get_TrxName());
			String sql="select dateacct, fa.account_ID, fa.description,cev.value,cev.accounttype, fa.Fact_Acct_ID "
					+ " from adempiere.Fact_Acct fa "
					+ " inner join adempiere.C_ElementValue cev on (C_ElementValue_ID=account_ID) "
					+ " where cev.accounttype IN ('E', 'L') and dateacct between ? and ? order by DateTrx desc";
			//
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setTimestamp(1, period.getStartDate());
				pstmt.setTimestamp(2, period.getEndDate());
				
				log.info(period.getStartDate()+"  "+period.getEndDate());
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
				{
					Integer Fact_Acct_ID=rs.getInt("Fact_Acct_ID");
					log.info("Fact_Acct_ID : "+Fact_Acct_ID) ;
					factDetails.add(Fact_Acct_ID);
				}
				rs.close();
				pstmt.close();
				pstmt = null;
			}
			catch (SQLException ex)
			{
				log.log(Level.SEVERE, sql, ex);
			}
			//	Clean Up
			try
			{
				if (pstmt != null)
					pstmt.close();
			}
			catch (SQLException ex1)
			{
			}
			pstmt = null;
			
		}
		else if (plClose.getTypePL().equalsIgnoreCase(MPLClosing.TYPEPL_Year))
		{
			log.info("Applied logic to find first And Last date : C_Year_ID "+plClose.getC_Year_ID()) ;
			MYear year=new MYear(getCtx(), plClose.getC_Year_ID(), get_TrxName());
			Map<String,Timestamp> dateRange=UtilGeneral.getFirstAndLastDateOfYear(year.getFiscalYear());
			
			Timestamp StartDate=dateRange.get(UtilGeneral.FIRST_DATE_OF_YEAR);
			Timestamp EndDate=dateRange.get(UtilGeneral.LAST_DATE_OF_YEAR);
			String sql="select dateacct, fa.account_ID, fa.description,cev.value,cev.accounttype, fa.Fact_Acct_ID "
					+ " from adempiere.Fact_Acct fa "
					+ " inner join adempiere.C_ElementValue cev on (C_ElementValue_ID=account_ID) "
					+ " where cev.accounttype IN ('E', 'L') and dateacct between ? and ? order by DateTrx desc";
			//
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setTimestamp(1, StartDate);
				pstmt.setTimestamp(2, EndDate);
				
				log.info(StartDate+"  "+EndDate);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
				{
					Integer Fact_Acct_ID=rs.getInt("Fact_Acct_ID");
					log.info("Fact_Acct_ID : "+Fact_Acct_ID) ;
					factDetails.add(Fact_Acct_ID);
				}
				rs.close();
				pstmt.close();
				pstmt = null;
			}
			catch (SQLException ex){log.log(Level.SEVERE, sql, ex);}
			//	Clean Up
			try
			{
				if (pstmt != null)
					pstmt.close();
			}
			catch (SQLException ex1){}
			pstmt = null;
			
		}
		
		// Delete already Exist Closing lines 
		String sql="select count(*) from C_PLClosingLine where C_PLClosing_ID="+plClose.getC_PLClosing_ID();
		int plNo=DB.getSQLValue(get_TrxName(), sql);
		if(plNo>0)
		{
			String sqlD="DELETE C_PLClosingLine where C_PLClosing_ID="+plClose.getC_PLClosing_ID();
			int noOfDelete=DB.executeUpdateEx(sqlD, get_TrxName());
			log.info("No of record Delete "+noOfDelete);
			if(noOfDelete>0)
			{	
				try {
					DB.commit(true, get_TrxName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
		}
		
		/// Created Closing lines 
		if(factDetails!=null && factDetails.size()>0)
		{
			
		/// Iterate ArrayList using Lambda Expression
			factDetails.forEach(item->
			{
				MFactAcct factAct=new MFactAcct(getCtx(), item, get_TrxName());
				System.out.println(factAct.getDescription()+"  "+factAct.getDateAcct());
				/// C_ElementValue
				MElementValue elementValue=new MElementValue(getCtx(), factAct.getAccount_ID(), get_TrxName());
				MPLClosingLine closingLine=new MPLClosingLine(plClose);
				closingLine.setC_AcctSchema_ID(factAct.getC_AcctSchema_ID());
				closingLine.setC_PLDimension_ID(factAct.getC_PLDimension_ID());
				closingLine.setAccount_ID(factAct.getAccount_ID());
				closingLine.setLedgerCode(elementValue.getValue());
				closingLine.setLedgerName(elementValue.getName());
				closingLine.setC_BPartner_ID(factAct.getC_BPartner_ID());
				closingLine.setM_Product_ID(factAct.getM_Product_ID());
				closingLine.setC_Project_ID(factAct.getC_Project_ID());
				closingLine.setAD_OrgTrx_ID(factAct.getAD_OrgTrx_ID());
				closingLine.setC_Activity_ID(factAct.getC_Activity_ID());
				closingLine.setC_LocFrom_ID(factAct.getC_LocFrom_ID());
				closingLine.setC_LocTo_ID(factAct.getC_LocTo_ID());
				closingLine.setUser1_ID(factAct.getUser1_ID());
				closingLine.setUser2_ID(factAct.getUser2_ID());
				closingLine.setUserElement1_ID(factAct.getUserElement1_ID());
				closingLine.setUserElement2_ID(factAct.getUserElement2_ID());
				closingLine.setC_ProjectTask_ID(factAct.getC_ProjectTask_ID());
				closingLine.setC_ProjectPhase_ID(factAct.getC_ProjectPhase_ID());
				closingLine.setGL_Budget_ID(factAct.getGL_Budget_ID());
				closingLine.setC_Tax_ID(factAct.getC_Tax_ID());
				closingLine.setAmtAcctCr(factAct.getAmtAcctDr());
				closingLine.setAmtAcctDr(factAct.getAmtAcctCr());
				closingLine.setAmtSourceCr(factAct.getAmtSourceDr());
				closingLine.setAmtSourceDr(factAct.getAmtSourceCr());
				closingLine.save(get_TrxName());
				if(closingLine.save(get_TrxName()))
				{
					try {
						DB.commit(true, get_TrxName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}	
			); /// End of Loop 
			
			/// Calculate total Amount Credit and Debit...
			Map<String,BigDecimal> finalValue=UtilPLClosing.getPLClosingDrCrAmount(plClose);
			if(finalValue!=null && finalValue.size()>0)
			{
				Object[] params=new Object[5];
				params[0]=finalValue.get(UtilPLClosing.TOTAL_CR);
				params[1]=finalValue.get(UtilPLClosing.TOTAL_DR);
				params[2]=finalValue.get(UtilPLClosing.TOTAL_PROFIT_LOSS);
				params[3]=plClose.getC_PLClosing_ID();
				params[4]=plClose.getAD_Client_ID();		
				String sqlUp="update C_PLClosing set TOTALCR=?, TOTALDR=?,TOTALPROFITANDLOSS=? where C_PLCLOSING_ID=? and AD_CLIENT_ID=?";
				Integer returnVal=DB.executeUpdateEx(sqlUp, params, get_TrxName());
				log.info("No of record update in PLClosing window "+returnVal);
				if(returnVal>0)
					DB.commit(true, get_TrxName());
			}
		}
		
		return "Created #"+factDetails.size();
	}
}