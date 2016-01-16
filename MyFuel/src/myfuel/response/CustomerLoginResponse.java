package myfuel.response;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.Network;
import myfuel.client.Station;
import myfuel.client.StationInventory;

/***
 * This class will contain the details of a customer as retrieved from the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CustomerLoginResponse extends Response {
	/**
	 * this object contains all customer details
	 */
	private Customer user;
	/***
	 * Networks that this customers is listed to.
	 */
	private ArrayList<Network> networks;
	
	/***
	 * The car that the customer is fueling with the NFC chip
	 */
	private Car FastFuelCar;
	
	/***
	 * The station that the customer is fueling at with the NFC chip
	 */
	private Station FastStation;
	
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
	
	/***
	 * CustomerLoginResponse Constructor
	 * @param customer - Customer Details
	 * @param networks - Networks that this customers is listed to.
	 * @param fastFuelCar - The car that the customer is fueling with the NFC chip
	 * @param s - The station that the customer is fueling at with the NFC chip
	 */
	public CustomerLoginResponse(Customer customer ,ArrayList<Network> networks,Car fastFuelCar,Station s)
	{
		this.setUser(customer);
		this.setNetworks(networks);
		this.setFastFuelCar(fastFuelCar);
		this.setFastStation(s);
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

	public Station getFastStation() {
		return FastStation;
	}

	public void setFastStation(Station fastStation) {
		FastStation = fastStation;
	}



}
