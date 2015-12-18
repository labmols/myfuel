package myfuel.response;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Station;

@SuppressWarnings("serial")
public class UserLoginResponse extends Response {
	/**
	 * this object contains all customer details
	 */
	private Customer user;
	
	/**
	 * Array of stations 
	 */
	private ArrayList <Station> stations;
	
	public UserLoginResponse(int userid, String fname, String lname, String pass, String email, String address,String cnumber, int toc, int 
			atype, int smodel,ArrayList<Car> cars,ArrayList<Integer> stations,ArrayList <Station> Allstations){
			setUser(new Customer(userid,fname,lname,pass,email,address,cnumber,toc,atype,smodel,cars,stations));
			setStations(Allstations);
	}


	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}


	public ArrayList <Station> getStations() {
		return stations;
	}


	public void setStations(ArrayList <Station> stations) {
		this.stations = stations;
	}

}
