package myfuel.response;

import java.io.Serializable;


@SuppressWarnings("serial")
public class booleanResponse extends Response {
	private boolean success;
	private String msg;
	
	public booleanResponse(boolean success, String msg){
		this.success = success;
		this.setMsg(msg);
	}
	
	public booleanResponse(boolean success){
		this.success=success;
	}
	
	public boolean getSuccess()
	{
		return success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
