package myfuel.response;

import myfuel.client.FuelQty;

/***
 * This class contains details about the home fuel quantity and orders as retreived from the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class HomeQtyResponse extends Response {

		/***
		 * contains the current and minimal quantity of the Home Fuel 
		 */
		private FuelQty minimal;
		/***
		 * contains the details of Home Fuel inventory order (if exists)
		 */
		private FuelQty order;
		
		/***
		 * HomeQtyResponse Constructor
		 * @param minimal - this object contains the current and minimal quantity of the Home Fuel 
		 * @param order - this object contains the details of Home Fuel inventory order (if exists)
		 */
	public HomeQtyResponse(FuelQty minimal, FuelQty order) 
	{
		this.minimal = minimal;
		this.order = order;
	}

	public FuelQty getOrder() {
		return order;
	}

	public void setOrder(FuelQty order) {
		this.order = order;
	}
	
	
	public FuelQty getMinimal() {
		return minimal;
	}

	public void setMinimal(FuelQty minimal) {
		this.minimal = minimal;
	}

}
