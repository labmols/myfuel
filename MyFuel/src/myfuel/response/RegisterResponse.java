package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Station;

public class RegisterResponse extends Response {
	public ArrayList <Station> stations;
	public RegisterResponse(ArrayList<Station> stations){
		this.stations = stations;
	}
	
}
