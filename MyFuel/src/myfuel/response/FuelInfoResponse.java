package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.client.Promotion;
import myfuel.client.StationInventory;

/**
 * CarFuelResponse class , contains all the needed details from the server response.
 * @author Maor
 *
 */
public class FuelInfoResponse extends Response {
	
	/**
	 * List contains all the stations Inventory details.
	 */
	private ArrayList <StationInventory> si;
	
	/**
	 * List that contains all the Fuels details available in the company.
	 */
	private ArrayList <Fuel> fuels;
	
	private Promotion prom;
	/**
	 * create new Car Fuel response 
	 * @param si - the stations inventory list.
	 * @param fuels - the fuels details list.
	 */
	public FuelInfoResponse(ArrayList <StationInventory> si, ArrayList <Fuel> fuels, Promotion prom)
	{
		this.si = new ArrayList<StationInventory>(si);
		this.fuels = new ArrayList<Fuel>(fuels);
		this.prom = prom;
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


	public Promotion getProm() {
		return prom;
	}


	public void setProm(Promotion prom) {
		this.prom = prom;
	}
	

}
