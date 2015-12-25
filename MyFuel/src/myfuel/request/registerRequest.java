package myfuel.request;

import java.io.Serializable;

import myfuel.client.Customer;

/**
 * registerRequest class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
public class registerRequest implements Serializable {
	
	/**
	 * New customer details object.
	 */
	private Customer customer;
	
	/**
	 * Request Query type (Select /Insert)
	 */
	private RequestEnum type;
	
	/**
	 * Create new Customer register request object.
	 * @param type - Request query type (Select / Insert).
	 * @param customer - New Customer details object.
	 */
	public registerRequest(RequestEnum type,Customer customer){
		this.customer =customer;
		this.type=type;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}


	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}
	
	
}
