package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginRequest implements Serializable{
	private int type; //0-user, 1-worker
    private int userid;
    private String password;
    private int changeStatus;
    
    public LoginRequest(int type,int userid, String password){
    	this.setType(type);
    	this.setUserid(userid);
    	this.setPassword(password);
    	setChangeStatus(0);
    }


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getChangeStatus() {
		return changeStatus;
	}


	public void setChangeStatus(int changeStatus) {
		this.changeStatus = changeStatus;
	}
}
