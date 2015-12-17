package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChangePassRequest implements Serializable {
	private String newPass;
	private String oldPass;
	private int userid;
	
	public ChangePassRequest(String newPass,int userid,String oldPass)
	{
		this.setNewPass(newPass);
		this.setUserid(userid);
		this.setOldPass(oldPass);
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}
