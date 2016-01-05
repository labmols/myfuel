package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * This class will specify the inventory of specific Station
 *
 */
@SuppressWarnings("serial")
public class StationInventory implements Serializable {
	/***
	 * Station Details
	 */
	private Station s;
	/***
	 * Fuel Quantities
	 */
	private ArrayList <FuelQty> fQty;
	/***
	 * StationInventory Constructor
	 * @param s - Station Details
	 * @param fQty - Fuel Quantities
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
