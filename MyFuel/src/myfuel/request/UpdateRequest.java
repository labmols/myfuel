package myfuel.request;

import java.io.Serializable;

import myfuel.client.Customer;

/**
 * UpdateRequest Class, used for contains all update details request that will be send to the server.
 * @author Maor
 *
 */
public class UpdateRequest implements Serializable {
	
	/**
	 * The updated customer details object.
	 */
	private Customer customer;
	
	/**
	 * Create new UpdateRequest object.
	 * @param customer - The updated customer details object.
	 */
	public UpdateRequest(Customer customer){
		setCustomer(customer);
	}
	


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}
