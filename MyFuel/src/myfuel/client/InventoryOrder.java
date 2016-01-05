package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This Class will has the details about an inventory order for a specific station
 *
 */
@SuppressWarnings("serial")
public class InventoryOrder implements Serializable{
	
	/***
	 * Station Details
	 */
	private Station station; 
	/***
	 * The Fuels in the station with their quantities
	 */
	private ArrayList<FuelQty> fQty;
	/***
	 * InventoryOrder Constructor
	 * @param station - Station Details
	 * @param fQty - The Fuels in the station with their quantities
	 */
	public InventoryOrder(Station station, ArrayList<FuelQty>fQty )
	{
		this.setfQty(fQty);
		this.setStation(station);
		 
	}


	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}


	public ArrayList<FuelQty> getfQty() {
		return fQty;
	}


	public void setfQty(ArrayList<FuelQty> fQty) {
		this.fQty = fQty;
	}





}
