package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Station;

/**
 * RegisterResponse class, contains all the register response details from the server.
 * @author Maor
 *
 */
public class RegisterResponse extends Response {
	/**
	 * List of all Stations in the company.
	 */
	private ArrayList <Station> stations;
	
	/**
	 * Create new Register Response object.
	 * @param stations - List of all the stations in the company.
	 */
	public RegisterResponse(ArrayList<Station> stations){
		this.setStations(stations);
	}
	
	public ArrayList <Station> getStations() {
		return stations;
	}
	
	public void setStations(ArrayList <Station> stations) {
		this.stations = stations;
	}
	
}
