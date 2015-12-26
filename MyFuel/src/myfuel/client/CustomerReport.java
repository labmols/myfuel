package myfuel.client;

import java.io.Serializable;
/***
 * This class will represent the user's behavior  in a specific station
 * @author ORON
 *
 */
public class CustomerReport implements Serializable {

	/***
	 * name of the customer
	 */
	private String name ;
	/***
	 * User id
	 */
	private int uid ;
	/***
	 * Station ID
	 */
	private int sid;
	/**
	 * Number of times the customer has bought in the Station (represented by this.sid) 
	 */
	private int times; 
	/***
	 * Total price that the customer has spent in the Station (represented by this.sid) 
	 */
	private float price;
	/***
	 * Total quantity that the customer has bought in the Station (represented by this.sid) 
	 */
	private float qty;
	
	
	public CustomerReport(int uid,String name, int sid,int times,float price,float qty)
	{
		this.setUid(uid);
		this.setName(name);
		this.setSid(sid);
		this.setTimes(times);
		this.setPrice(price) ;
		this.setQty(qty);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	public int getTimes() {
		return times;
	}


	public void setTimes(int times) {
		this.times = times;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public float getQty() {
		return qty;
	}


	public void setQty(float qty) {
		this.qty = qty;
	}
}
