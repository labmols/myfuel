package myfuel.client;

import java.io.Serializable;

/***
 * This class will has Details about the prices of some fuel
 *
 */
@SuppressWarnings("serial")
public class Fuel implements Serializable {
	
	/***
	 * Home Fuel ID Number
	 */
	public static int HomeFuelID = 4;
	/***
	 * Fuel 95 ID Number
	 */
	public static int Fuel95ID = 1;
	/***
	 * Fuel Diesel ID Number
	 */
	public static int FuelDiesel = 2;
	/***
	 * Fuel Scooter ID Number
	 */
	public static int FuelScooter = 3;
	/***
	 * Fuel  ID Number
	 */
	private int fid;
	/***
	 * Maximal Price for this fuel
	 */
	private float maxPrice;

	
	/**
	 * This constructor will create fuel with his ID and MaxPrice.
	 * @param fid - the id of the fuel.
	 * @param maxPrice - current price (for liter) of the fuel.
	 */
	public Fuel(int fid, float maxPrice){
		this.setFid(fid);
		this.setMaxPrice(maxPrice);
	}
	

	/***
	 *  
	 * @return fuel id 
	 */
	public int getFid() {
		return fid;
	}
	
	/***
	 *  set fuel id 
	 * @param fid
	 */
	public void setFid(int fid) {
		this.fid = fid;
	}
	

	/***
	 * 
	 * @return maximal price
	 */
	public float getMaxPrice() {
		return maxPrice;
	}
	
	/***
	 * set maximal price
	 * @param maxPrice
	 */
	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}


	
}
