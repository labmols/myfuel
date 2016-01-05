package myfuel.client;

import java.util.Comparator;
import java.util.Date;

/***
 * This class will be used as a comparator of 2 Date Objects by considering only their date 
 *
 */
public class TimeIgnoringComparator implements Comparator<Date> {
	/***
	 * This method will compare between 2 Date Objects only by their Date Value (Ignoring Time value)
	 * It will return the difference between this 2 dates
	 * @param d1 - Start Date 
	 * @param d2 - End Date 
	 */
	  public int compare(Date d1, Date d2) {
	    if (d1.getYear() != d2.getYear()) 
	        return d1.getYear() - d2.getYear();
	    if (d1.getMonth() != d2.getMonth()) 
	        return d1.getMonth() - d2.getMonth();
	    return d1.getDate() - d2.getDate();
	  }
	}