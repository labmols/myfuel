package myfuel.Tools;

/***
 * This class will has a Static Method that will provide the ratings as needed
 * @author karmo
 *
 */
public class AnalysticSystem 
{
	/**
	 * Initial purchases value for calculate the rating.
	 */
	private final static int factor = 3;
	
	/***
	 * This method will calculate the Rating of count that sent to it 
	 * @param n - the counter 
	 * @return - Rating to the counter value
	 */
	public static int analyze(int n)
	{
		if(n <= factor )
			return 1;
		
		else if(n<=2*factor && n > factor)
			return 2;
		
		else if(n <= 3*factor  && n > 2*factor)
			return 3;
		
		else if(n<= 4*factor && n > 3*factor)
			return 4;
		
		else if(n <= 5*factor  && n > 4*factor)
			return 5;
		
		else if(n<=6*factor && n > 5*factor)
			return 6;
		
		else if(n <= 7*factor  && n > 6*factor)
			return 7;
		
		else if(n <= 8*factor && n > 7*factor)
			return 8;
		
		else if(n <= 9*factor  && n > 8*factor)
			return 9;
		
		else  // n > 9*factor
			return 10;
		
		
	}

}
