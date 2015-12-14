package myfuel.response;

import java.io.Serializable;

import myfuel.client.Customer;
import myfuel.client.ErrorEnum;

@SuppressWarnings("serial")
public class UserLoginResponse extends Response {
	private ErrorEnum error;
	private Customer user;
	
	
	public UserLoginResponse(ErrorEnum error){
		setError(error);
		setUser(null);
	}
	
	public UserLoginResponse(int userid,String fname,String lname, String pass,String email,String cnumber){
			setUser(new Customer(userid,fname,lname,pass,email,cnumber,0,0,0,null,null));
			setError(ErrorEnum.NoError);
	}


	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public ErrorEnum getError() {
		return error;
	}

	public void setError(ErrorEnum error) {
		this.error = error;
	}
}
