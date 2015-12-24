package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SWRequest implements Serializable{
	
	private int sid;
	private RequestEnum type;
	
	/***
	 *  
	 * @param sid - Station id 
	 * @param type - type of request
	 */
	public SWRequest(int sid,RequestEnum type)
	{
		this.sid = sid;
		this.setType(type);
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}





}
