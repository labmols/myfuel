package myfuel.response;

import myfuel.client.FuelQty;

@SuppressWarnings("serial")
public class HomeQtyResponse extends Response {

		private FuelQty minimal;
		private FuelQty order;
		
		/***
		 * 
		 * @param minimal - this object contains the current and minimal quantity of the Home Fuel 
		 * @param order - this object contains the details of Home Fuel inventory order (if exists)
		 */
	public HomeQtyResponse(FuelQty minimal, FuelQty order) 
	{
		this.minimal = minimal;
		this.order = order;
	}
	/***
	 * 
	 * @return   Home Fuel inventory order
	 */
	public FuelQty getOrder() {
		return order;
	}
	/***
	 *  sets   Home Fuel inventory order
	 * @param order
	 */
	public void setOrder(FuelQty order) {
		this.order = order;
	}
	
	/***
	 * 
	 * @return minimal quantity of the Home Fuel 
	 */
	public FuelQty getMinimal() {
		return minimal;
	}
	/***
	 * sets minimal quantity of the Home Fuel 
	 * @param minimal 
	 */
	public void setMinimal(FuelQty minimal) {
		this.minimal = minimal;
	}

}
