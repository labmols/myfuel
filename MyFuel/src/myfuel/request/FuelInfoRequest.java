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
		setPromotionFuelID(promotionFuelID);
	}
	
	public FuelInfoRequest (RequestEnum type,  FuelQty newInventory)
	{
		setType(type);
		setNewInventory(newInventory);
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

	public FuelQty getNewInventory() {
		return newInventory;
	}

	public void setNewInventory(FuelQty newInventory) {
		this.newInventory = newInventory;
	}
	
}
