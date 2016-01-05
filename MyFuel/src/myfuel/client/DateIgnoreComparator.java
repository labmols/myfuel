package myfuel.client;

import java.util.Comparator;
import java.util.Date;

/***
 * This class will be used as a comparator between 2 Date Objects only by their time
 *
 */
public class DateIgnoreComparator implements Comparator<Date>{

	/***
	 * This method will compare between 2 Date Objects by their time
	 * If there is a difference between them the method will return it.
	 * Otherwise it will return 0.
	 * 
	 * @param Date start - Start Date
	 * @param Date end - end Date
	 */
	public int compare(Date start, Date end)
	{
		if(start.getHours() != end.getHours())
			return start.getHours() - end.getHours();
		
		else if(start.getMinutes() != end.getMinutes() )
			return start.getMinutes() - end.getMinutes();
		
		return 0;
	}
	

	

}
