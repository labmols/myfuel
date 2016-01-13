package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Rate;

/***
 * Request object for getting any rates suggestion and setting the rates by the Network Manager Decision
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class ConfirmNewRatesRequest  implements Serializable
{
	/***
	 * Type of request
	 */
	private RequestEnum type;
	/***
	 * will contain the approved rates 
	 */
	private ArrayList<Rate> approved;
	/***
	 * Network ID
	 */
	private int nid;
	/***
	 * Will contain the request that will be sent to the Server
	 * @param type - type of request 
	 */
	public ConfirmNewRatesRequest(RequestEnum type , int nid) 
	{
		this.type = type;
		this.setNid(nid);
	}
	/***
	 *  Will contain the request that will be sent to the Server
	 * @param type - type of request 
	 * @param approved - approved rates
	 */
	public ConfirmNewRatesRequest(RequestEnum type,ArrayList<Rate> approved,int nid) 
	{
		this.type = type;
		this.setNid(nid);
		this.setApproved(approved);
	}
	public RequestEnum getType() {
		return type;
	}
	public void setType(RequestEnum type) {
		this.type = type;
	}

	public ArrayList<Rate> getApproved() {
		return approved;
	}

	public void setApproved(ArrayList<Rate> approved) {
		this.approved = approved;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}



	
	

}
