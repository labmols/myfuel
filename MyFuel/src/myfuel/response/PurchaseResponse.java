package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Purchase;

/**
 *This class contain the response details from DB for a specific customer purchases list.
 */
@SuppressWarnings("serial")
public class PurchaseResponse extends Response {
	
	/**
	 * List of Customer purchases.
	 */
	private ArrayList <Purchase> customerPurchases;
	
	public PurchaseResponse(ArrayList<Purchase> customerPurchases)
	{
		this.setCustomerPurchases(customerPurchases);
	}

	public ArrayList <Purchase> getCustomerPurchases() {
		return customerPurchases;
	}

	public void setCustomerPurchases(ArrayList <Purchase> customerPurchases) {
		this.customerPurchases = customerPurchases;
	}
	
	

}
