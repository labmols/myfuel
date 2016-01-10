package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.client.HomeOrder;
import myfuel.client.Promotion;
import myfuel.client.Rate;
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
	private ArrayList<Promotion> promList;
	
	/**
	 * List of customer home orders (if the fuel order was home fuel, if not it will be null).
	 */
	private ArrayList <HomeOrder> horders;
	
	/**
	 * List of current rates.
	 */
	private ArrayList<Rate> rates;
	
	/**
	 * create new Car Fuel response 
	 * @param si - the stations inventory list.
	 * @param fuels - the fuels details list.
	 */
	public FuelOrderResponse(ArrayList <StationInventory> si, ArrayList <Fuel> fuels,  ArrayList<Promotion> promList , ArrayList <HomeOrder> horders,ArrayList<Rate> rates )
	{
		this.si = new ArrayList<StationInventory>(si);
		this.fuels = new ArrayList<Fuel>(fuels);
		this.setPromList(new ArrayList<Promotion>(promList));
		this.setHorders(horders);
		this.rates= new ArrayList<Rate>(rates);
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

	public ArrayList <HomeOrder> getHorders() {
		return horders;
	}


	public void setHorders(ArrayList <HomeOrder> horders) {
		this.horders = horders;
	}


	public ArrayList<Rate> getRates() {
		return rates;
	}


	public void setRates(ArrayList<Rate> rates) {
		this.rates = rates;
	}


	public ArrayList<Promotion> getPromList() {
		return promList;
	}
	
	public Promotion getPromotion(int fuelID)
	{
		for(Promotion p: this.promList)
		{
			if(p.getTypeOfFuel() == fuelID)
				return p;
		}
		return null;
	}


	public void setPromList(ArrayList<Promotion> promList) {
		this.promList = promList;
	}
	

}
