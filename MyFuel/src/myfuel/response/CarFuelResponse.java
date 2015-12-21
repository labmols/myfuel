package myfuel.response;

import java.util.ArrayList;

import myfuel.client.StationInventory;

public class CarFuelResponse extends Response {
	private ArrayList <StationInventory> si;
	
	
	public CarFuelResponse(ArrayList <StationInventory> si)
	{
		si = new ArrayList<StationInventory>(si);
	}


	public ArrayList <StationInventory> getSi() {
		return si;
	}


	public void setSi(ArrayList <StationInventory> si) {
		this.si = si;
	}
	

}
