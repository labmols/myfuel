package myfuel.client;

import java.io.Serializable;

/****
 * This class will contain the discount for a Sale Model
 *
 */
@SuppressWarnings("serial")
public class saleModel implements Serializable{
	
	/***
	 * Discount amount
	 */
	private int discount;
	/***
	 * Type of Sale Model
	 */
	private int type;
	
	/***
	 * 
	 * @param type - type of sale model 
	 *   type = 1 -> occasional fueling
	 *   type = 2 -> Monthly Regular - one car
	 *   type = 3 -> Monthly Regular - Few Cars
	 *   type = 4 -> Fully Monthly  - one car
	 * @param discount - Discount amount
	 */
	public saleModel(int type , int discount)
	{
		this.setType(type);
		this.setDiscount(discount);
		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
