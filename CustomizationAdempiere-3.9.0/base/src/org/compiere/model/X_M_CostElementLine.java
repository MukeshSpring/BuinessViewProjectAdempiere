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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_CostElementLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_M_CostElementLine extends PO implements I_M_CostElementLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180902L;

    /** Standard Constructor */
    public X_M_CostElementLine (Properties ctx, int M_CostElementLine_ID, String trxName)
    {
      super (ctx, M_CostElementLine_ID, trxName);
      /** if (M_CostElementLine_ID == 0)
        {
			setM_CostElementLine_ID (0);
			setM_CostElement_ID (0);
			setName (null);
// N/A
        } */
    }

    /** Load Constructor */
    public X_M_CostElementLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_M_CostElementLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cost Element Line ID.
		@param M_CostElementLine_ID Cost Element Line ID	  */
	public void setM_CostElementLine_ID (int M_CostElementLine_ID)
	{
		if (M_CostElementLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_CostElementLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_CostElementLine_ID, Integer.valueOf(M_CostElementLine_ID));
	}

	/** Get Cost Element Line ID.
		@return Cost Element Line ID	  */
	public int getM_CostElementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_CostElementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_CostElement getM_CostElement() throws RuntimeException
    {
		return (org.compiere.model.I_M_CostElement)MTable.get(getCtx(), org.compiere.model.I_M_CostElement.Table_Name)
			.getPO(getM_CostElement_ID(), get_TrxName());	}

	/** Set Cost Element.
		@param M_CostElement_ID 
		Product Cost Element
	  */
	public void setM_CostElement_ID (int M_CostElement_ID)
	{
		if (M_CostElement_ID < 1) 
			set_Value (COLUMNNAME_M_CostElement_ID, null);
		else 
			set_Value (COLUMNNAME_M_CostElement_ID, Integer.valueOf(M_CostElement_ID));
	}

	/** Get Cost Element.
		@return Product Cost Element
	  */
	public int getM_CostElement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_CostElement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	public org.compiere.model.I_M_CostElement getRef_M_CostElement() throws RuntimeException
    {
		return (org.compiere.model.I_M_CostElement)MTable.get(getCtx(), org.compiere.model.I_M_CostElement.Table_Name)
			.getPO(getRef_M_CostElement_ID(), get_TrxName());	}

	/** Set Ref Cost Element.
		@param Ref_M_CostElement_ID Ref Cost Element	  */
	public void setRef_M_CostElement_ID (int Ref_M_CostElement_ID)
	{
		if (Ref_M_CostElement_ID < 1) 
			set_Value (COLUMNNAME_Ref_M_CostElement_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_M_CostElement_ID, Integer.valueOf(Ref_M_CostElement_ID));
	}

	/** Get Ref Cost Element.
		@return Ref Cost Element	  */
	public int getRef_M_CostElement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_M_CostElement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}