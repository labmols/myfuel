package myfuel.response;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Station;

@SuppressWarnings("serial")
public class UserLoginResponse extends Response {
	private Customer user;
	
	
	public UserLoginResponse(int userid, String fname, String lname, String pass, String email, String cnumber, int toc, int 
			atype, int smodel,ArrayList<Car> cars,ArrayList<Integer> stations){
			setUser(new Customer(userid,fname,lname,pass,email,cnumber,toc,atype,smodel,cars,stations));
	}


	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

}
