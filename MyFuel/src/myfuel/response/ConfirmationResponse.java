package myfuel.response;
import java.util.ArrayList;

import myfuel.Entity.Customer;
import myfuel.client.*;



/***
 * This class will contains the details of the customers that needs to be approved as received from the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class ConfirmationResponse extends Response{
	
	/***
	 * Customers that need to be approved
	 */
	private ArrayList<Customer> customers;
	
	
	/***
	 * ConfirmationResponse Constructor
	 * @param customers  - An arrayList that has the unapproved customers
	 */
	public ConfirmationResponse(ArrayList<Customer> customers)
	{
		this.setCustomers(customers);
	}

	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	

}
