package myfuel.client;

import java.io.Serializable;

/***
 * This class will has the ID and Name of a station
 *
 */
@SuppressWarnings("serial")
public class Station implements Serializable {
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * Station name
	 */
	private String sname;
	
	/***
	 * The network this station is belong
	 */
	private Network network;
	
	
	/***
	 * Stations Constructor
	 * @param sid - station id 
	 * @param sname - station name
	 */
	public Station(int sid,String sname ){
		this.sid = sid;
		this.sname = sname;
	}
	
	/***
	 * Stations Constructor 
	 * @param sid - Station ID
	 * @param sname - Station Name
	 * @param network - Network Details
	 */
	public Station(int sid,String sname , Network network){
		this.sid = sid;
		this.sname = sname;
		this.network = network;
	}
	
	

	public String getName(){
		return this.sname;
	}
	
	public int getsid(){
		return this.sid;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	

}


