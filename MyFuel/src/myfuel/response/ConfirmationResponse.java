package myfuel.response;
import java.util.ArrayList;

import myfuel.client.*;
@SuppressWarnings("serial")
public class ConfirmationResponse extends Response{
	
	private ArrayList<Customer> customers;
	/***
	 * Constructor that create a response object for the client
	 * @param customers  - An arrayList the has the unapproved customers
	 */
	public ConfirmationResponse(ArrayList<Customer> customers)
	{
		this.setCustomers(customers);
	}

	/***
	 * 
	 * @return the unapproved customers
	 */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/***
	 *  set the arraylist of the unapproved customers
	 * @param customers
	 */
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	

}
