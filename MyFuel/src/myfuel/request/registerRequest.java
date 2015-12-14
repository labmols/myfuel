package myfuel.request;

import java.io.Serializable;

import myfuel.client.Customer;

public class registerRequest implements Serializable {
	private Customer customer;
	private int type;
	public registerRequest(int type,Customer customer){
		this.customer =customer;
		this.setType(type);
	}
	
	public Customer getCustomer(){
		return this.customer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
