package myfuel.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChangePassRequest implements Serializable {
	public String newPass;
	public int userid;
	
	public ChangePassRequest(String newPass,int userid)
	{
		this.newPass = newPass;
		this.userid = userid;
	}
}
