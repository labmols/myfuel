package myfuel.client;

import java.io.Serializable;

public class Fuel implements Serializable {
	private int fid;
	private float suggPrice;
	private float maxPrice;
	private float currPrice;
	
	/**
	 * this constructor will create fuel with his id and currprice.
	 * @param fid - the id of the fuel.
	 * @param currPrice - current price (for liter) of the fuel.
	 */
	public Fuel(int fid, float currPrice){
		this.setFid(fid);
		this.setCurrPrice(currPrice);
	}
	
	/***
	 *  This constructor will create the fuel type with its values
	 * @param fid  - fuel id 
	 * @param suggPrice - fuel price 
	 * @param maxPrice - maximal price
	 * @param qty - quantity
	 */
	public Fuel(int fid, float suggPrice,float maxPrice){
		this.setFid(fid);
		this.setSuggPrice(suggPrice);
		this.setMaxPrice(maxPrice);
	}
	/***
	 * This constructor will be used for bringing suggested rates from the DB
	 * @param suggPrice - suggested price
	 * @param currPrice - current price
	 * @param maxPrice - maximal price
	 */
	
	public Fuel(float suggPrice,float currPrice,float maxPrice)
	{
		this.suggPrice = suggPrice;
		this.currPrice = currPrice;
		this.maxPrice = maxPrice;
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
	 *  return fuel price
	 * @return
	 */

	public float getSuggPrice() {
		return suggPrice;
	}
	
	/***
	 *  set fuel price
	 * @param fprice
	 */

	public void setSuggPrice(float fprice) {
		this.suggPrice = fprice;
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

	/***
	 * 
	 * @return current price
	 */
	public float getCurrPrice() {
		return currPrice;
	}
	
	/***
	 *  set current price
	 * @param currPrice
	 */
	public void setCurrPrice(float currPrice) {
		this.currPrice = currPrice;
	}

	
}
