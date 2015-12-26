package myfuel.request;

import myfuel.client.Purchase;

/**
 * This class will contain all the home fuel request details.
 * @author Maor
 *
 */
public class HomeOrderRequest {
	/**
	 * Contains all the Home Fuel purchase details.
	 */
	private Purchase pur;
	
	/**
	 * Create new Home Fuel Request object.
	 * @param pur - The purchase details object.
	 */
	public HomeOrderRequest(Purchase pur)
	{
		this.setPur(pur);
	}

	public Purchase getPur() {
		return pur;
	}

	public void setPur(Purchase pur) {
		this.pur = pur;
	}
	
}
