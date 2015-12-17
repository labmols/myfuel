package myfuel.request;

import java.io.Serializable;

import myfuel.client.Customer;

public class UpdateRequest implements Serializable {
	private Customer customer;
	
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
