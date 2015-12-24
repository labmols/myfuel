package myfuel.client;


import java.io.Serializable;

@SuppressWarnings("serial")

public class saleModel implements Serializable {
	
	private int discount;
	private int type;
	
	/***
	 * 
	 * @param type - type of sale model 
	 *   type = 1 -> occasional fueling
	 *   type = 2 -> Monthly Regular - one car
	 *   type = 3 -> Monthly Regular - Few Cars
	 *   type = 4 -> Fully Monthly  - one car
	 * @param discount
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
