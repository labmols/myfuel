package myfuel.client;

/***
 * This class will has a Static Method that will provide the ratings as needed
 * @author karmo
 *
 */
public class AnalysticSystem 
{
	static int min;
	static int d;
	
	/***
	 * This method will calculate the Rating of count that sent to it 
	 * @param n - the counter 
	 * @return - Rating to the counter value
	 */
	public static int analyze(int n)
	{
		if(n <= 3 )
			return 1;
		
		else if(n<=6 && n > 3)
			return 2;
		
		else if(n <= 9  && n > 6)
			return 3;
		
		else if(n<= 12 && n > 9)
			return 4;
		
		else if(n <= 15  && n > 12)
			return 5;
		
		else if(n<=18 && n > 15)
			return 6;
		
		else if(n <= 21  && n > 18)
			return 7;
		
		else if(n <= 24 && n > 21)
			return 8;
		
		else if(n <= 27  && n > 24)
			return 9;
		
		else  // n > 27
			return 10;
		
		
	}

}
