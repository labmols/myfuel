package myfuel.request;

import java.io.Serializable;

import myfuel.client.FuelQty;

@SuppressWarnings("serial")
/**
 * Fuel Request class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
public class FuelInfoRequest implements Serializable {
	/**
	 * Request Query Type (Insert, Select)
	 */
	private RequestEnum type;
	
	private int promotionFuelID;
	
	private FuelQty newInventory;
	/**
	 * Create new Fuel Request 
	 * @param type - the Query type.
	 */
	public FuelInfoRequest (RequestEnum type,  int promotionFuelID)
	{
		setType(type);
		this.promotionFuelID = promotionFuelID;
	}

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}

	public int getPromotionFuelID() {
		return promotionFuelID;
	}

	public void setPromotionFuelID(int promotionFuelID) {
		this.promotionFuelID = promotionFuelID;
	}
	
}
