package org.businessView.utils;

import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MMessage;
import org.compiere.model.MOrderLine;
import org.compiere.util.Env;

public class UtilShipment {

	public static String checkReservedAndMovementQty(MInOutLine mInOutLine, int C_OrderLine_ID) {
		
		if(C_OrderLine_ID>0)
		{
			MInOut inout=new MInOut(Env.getCtx(), mInOutLine.getM_InOut_ID(), null);
			MDocType docType=new MDocType(Env.getCtx(), inout.getC_DocType_ID(), null);
			if(docType.getDocBaseType().equalsIgnoreCase(MDocType.DOCBASETYPE_MaterialDelivery))
			{
				MOrderLine mOLine=new MOrderLine(Env.getCtx(), C_OrderLine_ID, null);	
				if(mInOutLine.getMovementQty().compareTo(mOLine.getQtyReserved())>0)
				{
					return MMessage.get(Env.getCtx(), "BV_checkReservedAndMovementQty_1").getMsgText(); //"Movement Qty is more than reserved qty at Sale Order window";
				}
			}
			if(docType.getDocBaseType().equalsIgnoreCase(MDocType.DOCBASETYPE_MaterialReceipt))
			{
				MOrderLine mOLine=new MOrderLine(Env.getCtx(), C_OrderLine_ID, null);	
				if(mInOutLine.getMovementQty().compareTo(mOLine.getQtyOrdered())>0)
				{
					return MMessage.get(Env.getCtx(), "BV_checkReservedAndMovementQty_2").getMsgText(); //"Movement Qty is more than ordered qty at Purchase Order window";
				}
			}
			
		}
		return null;
	}	
}
