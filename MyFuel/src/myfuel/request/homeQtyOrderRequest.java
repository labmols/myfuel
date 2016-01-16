package myfuel.request;


import myfuel.client.FuelQty;

/***
 * This class will be used as a request object for managing home fuel quantity and inventory level.
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class homeQtyOrderRequest extends Request{
	
	/***
	 * Type of request
	 */
	private RequestEnum type;
	/***
	 * Home Fuel Inventory Order 
	 */
	private FuelQty order;
	/***
	 * Low inventory Level
	 */
	private Float lowLvl;
	
	/***
	 * homeQtyOrderRequest Constructor
	 * @param type - type of request
	 */
	public homeQtyOrderRequest(RequestEnum type)
	{
		this.type = type;
	}
	
	/***
	 * homeQtyOrderRequest Constructor
	 * @param type  - type of request 
	 * @param lowLvl  - new low inventory level
	 */
	public homeQtyOrderRequest(RequestEnum type , Float lowLvl)
	{
		this.type = type;
		this.setLowLvl(lowLvl);
	}
	
	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}


	public Float getLowLvl() {
		return lowLvl;
	}

	public void setLowLvl(Float lowLvl) {
		this.lowLvl = lowLvl;
	}

	
	public FuelQty getOrder() {
		return order;
	}

	public void setOrder(FuelQty order) {
		this.order = order;
	}

}
