package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ConfirmNewRatesRequest implements Serializable {
	
	private int type;
	/***
	 * Create a request that will sent to the Server
	 * @param type - type of the request 
	 * 			type = 1 -> Check if there are any new prices waiting for confirmation 
	 * 			type = 2 -> Confirm suggested rates and set it as the current rates
	 * 			type = 3 -> Deny Suggested Rates
	 */
	public ConfirmNewRatesRequest(int type)
	{
		this.setType(type);
	}
	
	/**
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}
	
	/***
	 * set type
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

}
