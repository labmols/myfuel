package myfuel.server;

import java.io.Serializable;

import myfuel.client.Customer;

@SuppressWarnings("serial")
public class UserLoginResponse extends Response {
	public int errorCode=-1;
	public Customer user;
	
	
	public UserLoginResponse(int errorCode){
		this.errorCode=errorCode;
		user = null;
	}
	
	public UserLoginResponse(int userid,String fname,String lname, String pass,String email,String cnumber){
			user = new Customer(userid,fname,lname,pass,email,cnumber,0,0,0,null,null);
		
	}
}
