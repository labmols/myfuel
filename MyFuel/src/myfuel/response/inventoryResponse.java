package myfuel.response;

import myfuel.client.InventoryOrder;

/***
 * This class contains the details of inventory Order as retrieved from the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class inventoryResponse extends Response {
	
	/***
	 * Inventory Order Details
	 */
	private InventoryOrder order;
	
	/***
	 * inventoryResponse Constructor
	 * @param order - Inventory Order details from the DB
	 */
	public inventoryResponse(InventoryOrder order)
	{
		this.setOrder(order);
	}
	
	public InventoryOrder getOrder() {
		return order;
	}
	
	
	public void setOrder(InventoryOrder order) {
		this.order = order;
	}

}
