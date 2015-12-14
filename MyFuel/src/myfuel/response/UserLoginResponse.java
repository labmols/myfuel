package myfuel.response;

import java.io.Serializable;

import myfuel.client.Customer;

@SuppressWarnings("serial")
public class UserLoginResponse extends Response {
	private int errorCode=-1;
	private Customer user;
	
	
	public UserLoginResponse(int errorCode){
		this.setErrorCode(errorCode);
		setUser(null);
	}
	
	public UserLoginResponse(int userid,String fname,String lname, String pass,String email,String cnumber){
			setUser(new Customer(userid,fname,lname,pass,email,cnumber,0,0,0,null,null));
		
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}
}
