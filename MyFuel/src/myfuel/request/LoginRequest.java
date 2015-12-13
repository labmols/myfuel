package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginRequest implements Serializable{
	public int type; //0-user, 1-worker
    public int userid;
    public String password;
    
    
    public LoginRequest(int type,int userid, String password){
    	this.type = type;
    	this.userid = userid;
    	this.password = password;
    }
}
