package myfuel.client;

import java.util.Comparator;
import java.util.Date;

public class DateIgnoreComparator implements Comparator<Date>{

	public int compare(Date start, Date end)
	{
		if(start.getHours() != end.getHours())
			return start.getHours() - end.getHours();
		
		else if(start.getMinutes() != end.getMinutes() )
			return start.getMinutes() - end.getMinutes();
		
		return 0;
	}
	

	

}
