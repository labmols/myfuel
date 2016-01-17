package myfuel.Response;

import java.util.ArrayList;

import myfuel.Entity.Network;
import myfuel.Entity.Station;

/**
 * RegisterResponse class, contains all the register response details from the server.
 * @author Maor
 *
 */
public class RegisterResponse extends Response {
	/**
	 * List of all Stations in the company.
	 */
	private ArrayList <Network> networks;
	
	/**
	 * Create new Register Response object.
	 * @param networks - List of all the stations in the company.
	 */
	public RegisterResponse(ArrayList<Network> networks){
		this.setNetworks(networks);
	}

	public ArrayList <Network> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList <Network> networks) {
		this.networks = networks;
	}
	
	
	
}
