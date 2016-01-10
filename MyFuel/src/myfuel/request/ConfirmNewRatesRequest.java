package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Rate;

@SuppressWarnings("serial")
public class ConfirmNewRatesRequest  implements Serializable
{
	private RequestEnum type;
	private ArrayList<Rate> approved;
	/***
	 * Will contain the request that will be sent to the Server
	 * @param type - type of request 
	 */
	public ConfirmNewRatesRequest(RequestEnum type) 
	{
		this.type = type;
	}
	/***
	 *  Will contain the request that will be sent to the Server
	 * @param type - type of request 
	 * @param approved - approved rates
	 */
	public ConfirmNewRatesRequest(RequestEnum type,ArrayList<Rate> approved) 
	{
		this.type = type;
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



	
	

}
