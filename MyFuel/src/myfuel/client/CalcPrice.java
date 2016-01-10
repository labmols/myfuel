package myfuel.client;

import java.util.ArrayList;

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
			orderPrice = (float) ((price*1.02) * qty);
		
		else if(qty > 600 && qty <=800)
			orderPrice = (float) ((price * qty) * 0.97);
	
		else if(qty > 800)
			orderPrice = (float) ((price * qty) * 0.96);
		
		else 
			orderPrice = (float) (price * qty);
		
		if(p!=null)
			orderPrice *= (1-(p.getDiscount()/100));
		return orderPrice;
		
	}
	
	/**
	 * Calculate car fuel order price.
	 * @param modelid- The customer sale model.
	 * @param rates - List of the current rates in the company.
	 * @param qty - Order quantity (Liter).
	 * @param maxPrice - Max price of fuel for liter.
	 * @param p - Promotion details object(if exist) , if not it's null.
	 * @return
	 */
	public static float calcCarFuelOrder(int modelid, ArrayList <Rate> rates, float qty,float maxPrice, Promotion p)
	{
	   float modelDiscount = rates.get(modelid-1).getDiscount();
	   float discountMonthlyOne = rates.get(Rate.MonthlyOne-1).getDiscount();
	   float discountMonthlyFew = rates.get(Rate.MonthlyFew-1).getDiscount();;
	   float orderPrice;
	   
	   switch(modelid)
	   {
	   
	   case Rate.Occasional:
		   orderPrice = maxPrice * qty;
		   break;
	   case Rate.MonthlyOne:
		   orderPrice = maxPrice*(1-((float)modelDiscount/100)) * qty;
		   break;
	   case Rate.MonthlyFew:
		   //Like Monthly One.
		   orderPrice = maxPrice*(1-((float)discountMonthlyOne/100)) * qty;
		   //Monthly Few cars discount.
		   orderPrice *= (1-((float)modelDiscount/100));
		   break;
	   case Rate.FullyMonthly:
		   //Like Monthly Few
		   orderPrice = maxPrice*(1-((float)discountMonthlyOne/100)) * qty;
		   orderPrice *= (1-(discountMonthlyFew/100));
		   //Fully Monthly Discount
		   orderPrice *= (1-((float)modelDiscount/100));
		   break;
		default: orderPrice = -1;
	   }
	   
	   //if there is any promotion.
	   if(p != null)
		   orderPrice *= (1-((float)p.getDiscount()/100));
	   	
	   
		return orderPrice;
	}

}
