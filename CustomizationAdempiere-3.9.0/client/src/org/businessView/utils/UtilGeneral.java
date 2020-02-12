package org.businessView.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UtilGeneral {

	public static final String FIRST_DATE_OF_YEAR="FIRST_DATE_OF_YEAR";
	public static final String LAST_DATE_OF_YEAR="LAST_DATE_OF_YEAR";

	/**
	 * Get First and Last date of Any Year
	 * @param yearString
	 * @return
	 */
	public static Map<String, Timestamp> getFirstAndLastDateOfYear(String yearString) {
		
		Map<String,Timestamp> firstAndLast=new HashMap<String, Timestamp>();
		
		// suppose that I have the following variable as input
	    int year=Integer.parseInt(yearString);
	    Calendar calendarStart=Calendar.getInstance();
	    calendarStart.set(Calendar.YEAR,year);
	    calendarStart.set(Calendar.MONTH,0);
	    calendarStart.set(Calendar.DAY_OF_MONTH,1);
	    // returning the first date
	    Date startDate=calendarStart.getTime();

	    Calendar calendarEnd=Calendar.getInstance();
	    calendarEnd.set(Calendar.YEAR,year);
	    calendarEnd.set(Calendar.MONTH,11);
	    calendarEnd.set(Calendar.DAY_OF_MONTH,31);

	    // returning the last date
	    Date endDate=calendarEnd.getTime();
		
	    Timestamp tsstartDate=new Timestamp(startDate.getTime());  
	    Timestamp tsendDate=new Timestamp(endDate.getTime());  

	    firstAndLast.put(FIRST_DATE_OF_YEAR, tsstartDate);
	    firstAndLast.put(LAST_DATE_OF_YEAR, tsendDate);
	    
	    System.out.println(firstAndLast);
	    
		return firstAndLast;
	}

}
