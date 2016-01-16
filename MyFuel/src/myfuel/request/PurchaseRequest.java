package myfuel.request;



/**
 * This class contain the required info for get 
 * all specific customer purchases from the DataBase.
 *
 */
@SuppressWarnings("serial")
public class PurchaseRequest extends Request{
	
	/**
	 * Customer ID Number.
	 */
	private int customerID;

/***
 * PurchaseRequest Constructor
 * @param customerID - Customer ID Number.
 */
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
