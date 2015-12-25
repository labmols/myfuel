package myfuel.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Fuel implements Serializable {
	private int fid;
	private float suggPrice;
	private float maxPrice;
	private float currPrice;
	
	/**
	 * this constructor will create fuel with his id and maxprice.
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
