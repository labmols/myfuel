package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class StationInventory implements Serializable {
	private Station s;
	private ArrayList <FuelQty> fQty;
	/**
	 * 
	 * @param s
	 * @param fQty
	 * @param mQty
	 */
		public StationInventory(Station s, ArrayList <FuelQty> fQty)
		{
			this.setfQty(new ArrayList<FuelQty>(fQty));
			this.s = s;
		}

	
	public Station getS() {
		return s;
	}

	public void setS(Station s) {
		this.s = s;
	}


	public ArrayList <FuelQty> getfQty() {
		return fQty;
	}


	public void setfQty(ArrayList <FuelQty> fQty) {
		this.fQty = fQty;
	}



	
}
