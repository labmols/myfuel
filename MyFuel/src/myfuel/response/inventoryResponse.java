package myfuel.response;

import myfuel.client.InventoryOrder;

public class inventoryResponse extends Response {
	
	private InventoryOrder order;
	
	/***
	 * 
	 * @param order - Inventory Order details from the DB
	 */
	public inventoryResponse(InventoryOrder order)
	{
		this.setOrder(order);
	}
	/***
	 * 
	 * @return orders of the inventory orders
	 */
	public InventoryOrder getOrder() {
		return order;
	}
	
	/***
	 * 
	 * @param order - Inventory Order details
	 */
	public void setOrder(InventoryOrder order) {
		this.order = order;
	}

}
