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

/** Generated Model for C_PLDimension
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_C_PLDimension extends PO implements I_C_PLDimension, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190512L;

    /** Standard Constructor */
    public X_C_PLDimension (Properties ctx, int C_PLDimension_ID, String trxName)
    {
      super (ctx, C_PLDimension_ID, trxName);
      /** if (C_PLDimension_ID == 0)
        {
			setC_PLDimension_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_PLDimension (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_C_PLDimension[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set P&L Dimension ID.
		@param C_PLDimension_ID P&L Dimension ID	  */
	public void setC_PLDimension_ID (int C_PLDimension_ID)
	{
		if (C_PLDimension_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PLDimension_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PLDimension_ID, Integer.valueOf(C_PLDimension_ID));
	}

	/** Get P&L Dimension ID.
		@return P&L Dimension ID	  */
	public int getC_PLDimension_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PLDimension_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Year getC_Year() throws RuntimeException
    {
		return (org.compiere.model.I_C_Year)MTable.get(getCtx(), org.compiere.model.I_C_Year.Table_Name)
			.getPO(getC_Year_ID(), get_TrxName());	}

	/** Set Year.
		@param C_Year_ID 
		Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID)
	{
		if (C_Year_ID < 1) 
			set_Value (COLUMNNAME_C_Year_ID, null);
		else 
			set_Value (COLUMNNAME_C_Year_ID, Integer.valueOf(C_Year_ID));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getC_Year_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Year_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** TypePL AD_Reference_ID=1000006 */
	public static final int TYPEPL_AD_Reference_ID=1000006;
	/** Period = P */
	public static final String TYPEPL_Period = "P";
	/** Year = Y */
	public static final String TYPEPL_Year = "Y";
	/** Set Type.
		@param TypePL 
		This is new field for PROFIT & LOSS CLOSING window
	  */
	public void setTypePL (String TypePL)
	{

		set_Value (COLUMNNAME_TypePL, TypePL);
	}

	/** Get Type.
		@return This is new field for PROFIT & LOSS CLOSING window
	  */
	public String getTypePL () 
	{
		return (String)get_Value(COLUMNNAME_TypePL);
	}
}