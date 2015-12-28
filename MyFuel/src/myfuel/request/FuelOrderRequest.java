package myfuel.request;

import java.io.Serializable;

import myfuel.client.FuelQty;
import myfuel.client.HomeOrder;
import myfuel.client.Purchase;

@SuppressWarnings("serial")
/**
 * Fuel Request class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
public class FuelOrderRequest implements Serializable {
	/**
	 * Request Query Type (Insert, Select)
	 */
	private RequestEnum type;
	
	/**
	 * Type of Fuel
	 */
	private int FuelID;
	
	/**
	 * The fueling station 
	 */
	private int sid;
	
	/**
	 * The fuel quantity ordered.
	 */
	private float FuelQty;
	
	/**
	 * The Purchase details
	 */
	private Purchase pur;
	
	/**
	 * The Home Order details (if this Fuel is home)
	 */
	private HomeOrder order;
	
	/**
	 * Create new Fuel Request 
	 * @param type - the Query type.
	 */
	public FuelOrderRequest (RequestEnum type,  int FuelID )
	{
		setType(type);
		setFuelID(FuelID);
		setPur(null);
		setHOrder(null);
		setSid(-1);
		setFuelQty(-1);
	}
	
	public FuelOrderRequest (RequestEnum type,  float FuelQty ,int sid, Purchase pur, HomeOrder order)
	{
		setType(type);
		setFuelID(pur.getFuelid());
		setFuelQty(FuelQty);
		setPur(pur);
		setHOrder(order);
		setSid(sid);
	}
	
	public FuelOrderRequest (RequestEnum type,  float FuelQty , int sid, Purchase pur)
	{
		setType(type);
		setFuelQty(FuelQty);
		setFuelID(pur.getFuelid());
		setSid(sid);
		setPur(pur);
		setHOrder(null);
	}

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}

	public int getFuelID() {
		return FuelID;
	}

	public void setFuelID(int FuelID) {
		this.FuelID = FuelID;
	}


	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}


	public Purchase getPur() {
		return pur;
	}

	public void setPur(Purchase pur) {
		this.pur = pur;
	}

	public HomeOrder getHOrder() {
		return order;
	}

	public void setHOrder(HomeOrder order) {
		this.order = order;
	}

	public float getFuelQty() {
		return FuelQty;
	}

	public void setFuelQty(float fuelQty) {
		FuelQty = fuelQty;
	}


	
}
