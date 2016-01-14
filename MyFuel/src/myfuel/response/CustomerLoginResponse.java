package myfuel.response;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.Network;
import myfuel.client.Station;
import myfuel.client.StationInventory;

@SuppressWarnings("serial")
public class CustomerLoginResponse extends Response {
	/**
	 * this object contains all customer details
	 */
	private Customer user;
	
	private ArrayList<Network> networks;
	
	private Car FastFuelCar;
	
	/**
	 * create new Customer Login Response .
	 * @param userid - customer id.
	 * @param fname - customer first name.
	 * @param lname - customer last name.
	 * @param pass - customer password.
	 * @param email - customer e-mail.
	 * @param address - customer address.
	 * @param cnumber - customer credit card.
	 * @param toc - type of customer (0 - Private, 1- Company)
	 * @param atype - Access Type.
	 * @param smodel - Station Model.
	 * @param cars - List of customer cars.
	 * @param stations - List of customer stations.
	 * @param Allstations - List of all Stations in the company.
	 */
	public CustomerLoginResponse(Customer customer ,ArrayList<Network> networks){
			//setUser();
			setUser(customer);
			this.setNetworks(networks);
			
	}
	
	public CustomerLoginResponse(Customer customer ,ArrayList<Network> networks,Car fastFuelCar)
	{
		this.setUser(customer);
		this.setNetworks(networks);
		this.setFastFuelCar(fastFuelCar);
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}


	public ArrayList<Network> getNetworks() {
		return networks;
	}


	public void setNetworks(ArrayList<Network> networks) {
		this.networks = networks;
	}




	public Car getFastFuelCar() {
		return FastFuelCar;
	}




	public void setFastFuelCar(Car fastFuelCar) {
		FastFuelCar = fastFuelCar;
	}



}
