package myfuel.client;

import java.io.Serializable;

/***
 * This class will has the Inventory details of a station in a specific quarter
 *
 */
@SuppressWarnings("serial")
public class QuarterStationInventory  implements Serializable
{
	/***
	 * Quarter ID
	 */
	private int qid ;
	/***
	 * Station Details
	 */
	private Station station;
	/***
	 * Fuel Details
	 */
	private FuelQty fuel;
	
	/***
	 *  quarterStationInventory constructor
	 * @param qid - quarter id
	 * @param station - stations details
	 * @param fuel - fuel details
	 */
	public QuarterStationInventory(int qid,Station station,FuelQty fuel)
	{
		this.setQid(qid);
		this.setStation(station);
		this.setFuel(fuel);
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public FuelQty getFuel() {
		return fuel;
	}

	public void setFuel(FuelQty fuel) {
		this.fuel = fuel;
	}

}
