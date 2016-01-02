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
	 * Stations Constructor
	 * @param sid - station id 
	 * @param sname - station name
	 */
	public Station(int sid,String sname ){
		this.sid = sid;
		this.sname = sname;
	}
	

	public String getName(){
		return this.sname;
	}
	
	public int getsid(){
		return this.sid;
	}

	

}


