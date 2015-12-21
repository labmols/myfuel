package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class InventoryOrder implements Serializable{
	
	private Station station; 
	private ArrayList<FuelQty> fQty;
	/***
	 * 
	 * @param station  - stations id and name
	 * @param qty   - fuel quantities
	 * @param fnames - fuel name
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
