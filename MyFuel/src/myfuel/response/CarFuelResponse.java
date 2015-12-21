package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.StationInventory;

public class CarFuelResponse extends Response {
	private ArrayList <StationInventory> si;
	private ArrayList <Fuel> fuels;
	
	
	public CarFuelResponse(ArrayList <StationInventory> si, ArrayList <Fuel> fuels)
	{
		this.si = new ArrayList<StationInventory>(si);
		this.fuels = new ArrayList<Fuel>(fuels);
	}


	public ArrayList <StationInventory> getSi() {
		return this.si;
	}


	public void setSi(ArrayList <StationInventory> si) {
		this.si = si;
	}


	public ArrayList <Fuel> getFuels() {
		return fuels;
	}


	public void setFuels(ArrayList <Fuel> fuels) {
		this.fuels = fuels;
	}
	

}
