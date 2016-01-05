package myfuel.client;

/**
 * This class has static methods that calculate all orders total price(according to promotions and sale model).
 * @author Maor
 *
 */

public class CalcPrice {

	/**
	 * Calculate Home Order total price.
	 * @param urgent - Is the order urgent or not.
	 * @param qty - Fuel Quantity (Liter).
	 * @param price - Regular Price for Liter(received from DB).
	 * @param p - Promotion object (used for get discount) , if there is not appropriate promotion it will be null .
	 * @return
	 */
	public static float calcTotalHomeOrder(boolean urgent, float qty, float price, Promotion p)
	{
		float orderPrice;
		
		if(urgent)
		{
			orderPrice = (float) ((price*1.02) * qty);
			if(p!=null)
				orderPrice *= (1-(p.getDiscount()/100));
			return orderPrice;
		}
		
		else if(qty > 600 && qty <=800)
		{
			orderPrice = (float) ((price * qty) * 0.97);
			if(p!=null)
				orderPrice *= (1-(p.getDiscount()/100));
			return orderPrice;
		}
		
		else if(qty > 800)
		{
			orderPrice = (float) ((price * qty) * 0.96);
			if(p!=null)
				orderPrice *= (1-(p.getDiscount()/100));
			return orderPrice;
		}
		
		else 
		{
			orderPrice = (float) (price * qty);
			if(p!=null)
				orderPrice *= (1-(p.getDiscount()/100));
			return orderPrice;
		}
		
		
	}

}
