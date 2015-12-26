package myfuel.request;

import java.io.Serializable;

import myfuel.client.HomeOrder;
import myfuel.client.Purchase;

/**
 * This class will contain all the home fuel request details.
 * @author Maor
 *
 */
public class HomeOrderRequest implements Serializable{
	/**
	 * Contains all the Home Fuel purchase details.
	 */
	private RequestEnum type;
	private Purchase pur;
	private HomeOrder order;
	
	/**
	 * Create new Home Fuel Request object.
	 * @param pur - The Purchase details object.
	 * @param order - the Order details object.
	 */
	public HomeOrderRequest(Purchase pur , HomeOrder order, RequestEnum type)
	{
		this.setPur(pur);
		this.setOrder(order);
		this.setType(type);
	}

	public Purchase getPur() {
		return pur;
	}

	public void setPur(Purchase pur) {
		this.pur = pur;
	}

	public HomeOrder getOrder() {
		return order;
	}

	public void setOrder(HomeOrder order) {
		this.order = order;
	}

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}
	
}
