package myfuel.client;

public class CalcPrice {
	
	public static float calcHomeOrder(boolean urgent, float qty, float price, Promotion p)
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
