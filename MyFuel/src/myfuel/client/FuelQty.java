package myfuel.client;

import java.io.Serializable;

/***
 * This class will has details about the quantities of a specific fuel
 *
 */
@SuppressWarnings("serial")
public class FuelQty implements Serializable{
	
	/***
	 * Fuel ID Number
	 */
	private int fid;
	/***
	 * Fuel Name
	 */
	private String fname;
	/***
	 * Fuel Quantity
	 */
	private float qty;
	/***
	 * Fuel Minimal Quantity
	 */
	private float mqty;
	
	/***
	 * FuelQty constructor
	 * @param fid - fuel id
	 * @param qty - fuel quantity
	 * @param mqty - fuel minimal quantity
	 */
	public FuelQty (int fid , float qty, float mqty)
	{
		setFid(fid);
		setQty(qty);
		setMqty(mqty);
	}
	/***
	 *  FuelQty constructor
	 * @param fname - fuel name
	 * @param fid - fuel id
	 * @param qty - fuel quantity
	 */
	public FuelQty(String fname,int fid , float qty )
	{
		this.fname = fname;
		this.fid = fid;
		this.qty = qty;
	}
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getMqty() {
		return mqty;
	}
	public void setMqty(float mqty) {
		this.mqty = mqty;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
}
