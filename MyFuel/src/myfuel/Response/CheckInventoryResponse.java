package myfuel.Response;

import java.util.ArrayList;

import myfuel.Entity.FuelQty;


/***
 * This class will contain Inventory Order details for  a station 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CheckInventoryResponse extends Response {

	/***
	 * The new inventory order for the station. 
	 * Listed by each fuel with its quantity as defined in the order.
	 */
	private ArrayList<FuelQty> NewOrder;

	/**
	 * CheckInventoryResponse Constructor
	 * @param NewOrder - New Inventory Order Details
	 */
	public CheckInventoryResponse(ArrayList<FuelQty> NewOrder)
	{
		setNewOrder(NewOrder);
	}
	public ArrayList<FuelQty> getNewOrder() {
		return NewOrder;
	}

	public void setNewOrder(ArrayList<FuelQty> NewOrder) {
		this.NewOrder = NewOrder;
	}
	
	
}
