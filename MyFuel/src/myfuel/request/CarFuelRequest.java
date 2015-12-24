package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CarFuelRequest implements Serializable {
	private RequestEnum type;
	
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
