package myfuel.server;

import java.util.ArrayList;

import myfuel.client.Station;

public class RegisterResponse extends Response {
	public ArrayList <Station> stations;
	RegisterResponse(ArrayList<Station> stations){
		this.stations = stations;
	}
	
}
