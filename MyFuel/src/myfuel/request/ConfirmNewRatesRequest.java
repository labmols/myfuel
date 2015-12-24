package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ConfirmNewRatesRequest  implements Serializable
{
	private RequestEnum type;
	/***
	 * Will contain the request that will be sent to the Server
	 * @param type - type of request 
	 */
	public ConfirmNewRatesRequest(RequestEnum type) 
	{
		this.type = type;
	}
	public RequestEnum getType() {
		return type;
	}
	public void setType(RequestEnum type) {
		this.type = type;
	}

	
	

}
