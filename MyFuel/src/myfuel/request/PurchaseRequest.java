package myfuel.request;

import java.io.Serializable;

/**
 * This class contain the required info for get 
 * all specific customer purchases from the DataBase.
 *
 */
@SuppressWarnings("serial")
public class PurchaseRequest implements Serializable {
	
	/**
	 * Customer ID Number.
	 */
	private int customerID;
	
	public PurchaseRequest(int customerID)
	{
		this.setCustomerID(customerID);
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	

}
