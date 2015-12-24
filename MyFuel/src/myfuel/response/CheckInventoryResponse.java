package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Order;


@SuppressWarnings("serial")
public class CheckInventoryResponse extends Response {

	private ArrayList<Order> NewOrder;

	public CheckInventoryResponse(ArrayList<Order> NewOrder)
	{
		setNewOrder(NewOrder);
	}
	public ArrayList<Order> getNewOrder() {
		return NewOrder;
	}

	public void setNewOrder(ArrayList<Order> NewOrder) {
		this.NewOrder = NewOrder;
	}
	
	
}
