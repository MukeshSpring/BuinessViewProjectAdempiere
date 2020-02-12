package org.businessView.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.compiere.util.Env;

public class UtilPayment {

	public static BigDecimal getActualTaxAmountAccordingToRate(BigDecimal PayAmt, BigDecimal TaxRate) 
	{
		if(TaxRate.compareTo(Env.ZERO)>0)
		{	
			BigDecimal taxRatePerCentage=TaxRate.divide(Env.ONEHUNDRED).add(Env.ONE);
			BigDecimal payAmtWithoutTax=PayAmt.divide(taxRatePerCentage,2,RoundingMode.HALF_UP);
			BigDecimal actualtaxAmount=PayAmt.subtract(payAmtWithoutTax);
			return actualtaxAmount;
		}
		return Env.ZERO;
	}
}
