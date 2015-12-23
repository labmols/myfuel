package myfuel.request;

import java.io.Serializable;

import myfuel.client.FuelQty;

@SuppressWarnings("serial")
public class homeQtyOrderRequest implements Serializable{
	
	private RequestEnum type;
	private FuelQty order;
	private Float lowLvl;
	
	/***
	 * This Request will be used by the DBHandler to decide how to handle the DB 
	 * @param type - type of request
	 */
	public homeQtyOrderRequest(RequestEnum type)
	{
		this.type = type;
	}
	
/***
 * This Request will be used by the DBHandler to decide how to handle the DB 
 * @param type  - type of request 
 * @param lowLvl  - new low inventory level
 */
	public homeQtyOrderRequest(RequestEnum type , Float lowLvl)
	{
		this.type = type;
		this.setLowLvl(lowLvl);
	}
	/***
	 * 
	 * @return type of request
	 */
	public RequestEnum getType() {
		return type;
	}
/***
 * set type of request
 * @param type 
 */
	public void setType(RequestEnum type) {
		this.type = type;
	}

	/***
	 * 
	 * @return new low inventory level
	 */
	public Float getLowLvl() {
		return lowLvl;
	}
/***
 * set low inventory level
 * @param lowLvl 
 */
	public void setLowLvl(Float lowLvl) {
		this.lowLvl = lowLvl;
	}

	/***
	 * 
	 * @return Order
	 */
	public FuelQty getOrder() {
		return order;
	}
/***
 * set Order
 * @param order
 */
	public void setOrder(FuelQty order) {
		this.order = order;
	}

}
