package myfuel.request;

import java.io.Serializable;

import myfuel.client.Customer;

public class registerRequest implements Serializable {
	private Customer customer;
	private RequestEnum type;
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
