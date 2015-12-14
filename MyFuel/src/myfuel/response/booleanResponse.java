package myfuel.response;

import java.io.Serializable;


@SuppressWarnings("serial")
public class booleanResponse extends Response {
	private boolean success;
	
	public booleanResponse(boolean success){
		this.success = success;
	}
	
	public boolean getSuccess()
	{
		return success;
	}

}
