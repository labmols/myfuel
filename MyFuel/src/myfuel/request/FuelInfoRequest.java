package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Fuel Request class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
public class FuelInfoRequest implements Serializable {
	/**
	 * Request Query Type (Insert, Select)
	 */
	private RequestEnum type;
	
	/**
	 * Create new Fuel Request 
	 * @param type - the Query type.
	 */
	public FuelInfoRequest (RequestEnum type)
	{
		setType(type);
	}

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}
	
}
