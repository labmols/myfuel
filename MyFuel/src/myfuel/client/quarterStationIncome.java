package myfuel.client;

import java.io.Serializable;

/***
 * This class will has the details about purchase that was in a station during a specific quarter 
 *
 */
@SuppressWarnings("serial")
public class quarterStationIncome implements Serializable{
	/***
	 * Purchase details
	 */
	private Purchase p;
	/***
	 * Station Name and ID
	 */
	private Station station;
	/***
	 * Quarter ID
	 */
	private int qid;
	
	/***
	 *  quarterStationIncome Constructor
	 * @param qid - quarter id
	 * @param station - stations Details {ID,NAME}
	 * @param p - Purchase details
	 */
	public quarterStationIncome(int qid,Station station,Purchase p)
	{
		this.setP(p);
		this.setQid(qid);
		this.setStation(station);
	}

	public Purchase getP() {
		return p;
	}

	public void setP(Purchase p) {
		this.p = p;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

}
