package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.saleModel;

@SuppressWarnings("serial")
public class ConfirmNewRatesRequest  implements Serializable
{
	private RequestEnum type;
	private ArrayList<saleModel> approved;
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
	public ConfirmNewRatesRequest(RequestEnum type,ArrayList<saleModel> approved) 
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

	public ArrayList<saleModel> getApproved() {
		return approved;
	}

	public void setApproved(ArrayList<saleModel> approved) {
		this.approved = approved;
	}



	
	

}
