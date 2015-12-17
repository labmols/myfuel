package myfuel.client;

import java.io.Serializable;

public class Fuel implements Serializable {
	private int fid;
	private float fprice;
	private float maxPrice;
	private float currPrice;
	private int qty;
	public Fuel(int fid, float fprice){
		this.setFid(fid);
		this.setFprice(fprice);
	}
	/***
	 *  This constructor will create the fuel type with its charetaristics
	 * @param fid  - fuel id 
	 * @param fprice - fuel price 
	 * @param maxPrice - maximal price
	 * @param qty - quantity
	 */
	public Fuel(int fid, float fprice,float maxPrice, int qty){
		this.setFid(fid);
		this.setFprice(fprice);
		this.setMaxPrice(maxPrice);
		this.setQty(qty);
	}
	/***
	 * This constructor will be used for bringing suggested rates from the DB
	 * @param fprice - suggested price
	 * @param currPrice - current price
	 * @param maxPrice - maximal price
	 */
	
	public Fuel(float fprice,float currPrice,float maxPrice)
	{
		this.fprice = fprice;
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

	public float getFprice() {
		return fprice;
	}
	
	/***
	 *  set fuel price
	 * @param fprice
	 */

	public void setFprice(float fprice) {
		this.fprice = fprice;
	}

	/**
	 * 
	 * @return quantity
	 */
	public int getQty() {
		return qty;
	}

	/***
	 * set quantity
	 * @param qty
	 */
	public void setQty(int qty) {
		this.qty = qty;
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
