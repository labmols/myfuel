package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.client.HomeOrder;
import myfuel.client.Promotion;
import myfuel.client.StationInventory;

/**
 * CarFuelResponse class , contains all the needed details from the server response.
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class FuelOrderResponse extends Response {
	
	/**
	 * List contains all the stations Inventory details.
	 */
	private ArrayList <StationInventory> si;
	
	/**
	 * List that contains all the Fuels details available in the company.
	 */
	private ArrayList <Fuel> fuels;
	
	/**
	 * Promotion object if exist(if not it will be null).
	 */
	private Promotion prom;
	
	/**
	 * List of customer home orders (if the fuel order was home fuel, if not it will be null).
	 */
	private ArrayList <HomeOrder> horders;
	/**
	 * create new Car Fuel response 
	 * @param si - the stations inventory list.
	 * @param fuels - the fuels details list.
	 */
	public FuelOrderResponse(ArrayList <StationInventory> si, ArrayList <Fuel> fuels, Promotion prom,ArrayList <HomeOrder> horders )
	{
		this.si = new ArrayList<StationInventory>(si);
		this.fuels = new ArrayList<Fuel>(fuels);
		this.prom = prom;
		this.setHorders(horders);
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


	public ArrayList <HomeOrder> getHorders() {
		return horders;
	}


	public void setHorders(ArrayList <HomeOrder> horders) {
		this.horders = horders;
	}
	

}
