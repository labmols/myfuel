package myfuel.response;

import java.io.Serializable;


@SuppressWarnings("serial")
public class booleanResponse extends Response {
	public boolean success;
	
	public booleanResponse(boolean success){
		this.success = success;
	}

}
