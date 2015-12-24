package myfuel.response;

import java.util.ArrayList;

import myfuel.client.FuelQty;



@SuppressWarnings("serial")
public class CheckInventoryResponse extends Response {

	private ArrayList<FuelQty> NewOrder;

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
