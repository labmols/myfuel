package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Station;

public class RegisterResponse extends Response {
	private ArrayList <Station> stations;
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
