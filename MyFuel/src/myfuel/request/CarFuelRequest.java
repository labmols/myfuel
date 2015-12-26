package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * CarFuelRequest class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
public class CarFuelRequest implements Serializable {
	/**
	 * Request Query Type (Insert, Select)
	 */
	private RequestEnum type;
	
	/**
	 * Create new CarFuelRequest 
	 * @param type - the Query type.
	 */
	public CarFuelRequest (RequestEnum type)
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
