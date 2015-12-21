package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class InventoryOrder implements Serializable{
	
	private Station station; 
	private ArrayList<Float> qty ;
	private ArrayList<String> fnames;
	/***
	 * 
	 * @param station  - stations id and name
	 * @param qty   - fuel quantities
	 * @param fnames - fuel name
	 */
	public InventoryOrder(Station station, ArrayList<Float> qty, ArrayList<String> fnames )
	{
		this.setFnames(fnames);
		this.setStation(station);
		this.setQty(qty) ; 
	}

	public ArrayList<Float> getQty() {
		return qty;
	}

	public void setQty(ArrayList<Float> qty) {
		this.qty = qty;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public ArrayList<String> getFnames() {
		return fnames;
	}

	public void setFnames(ArrayList<String> fnames) {
		this.fnames = fnames;
	}
	

}
