package myfuel.client;

import java.io.Serializable;

public class registerRequest implements Serializable {
	private Customer customer;
	public int type;
	public registerRequest(int type,Customer customer){
		this.customer =customer;
		this.type=type;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	
}
