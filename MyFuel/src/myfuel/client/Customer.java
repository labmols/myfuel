package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Customer implements Serializable{
	public int userid;
	public String fname;
	public String lname;
	public String pass;
	public String email;
	public String cnumber;
	public int toc;
	public int atype;
	public int smodel;
	public ArrayList<Car> cars;
	public ArrayList<Integer> stations;
	public int LoggedIn;
	
	public Customer(int userid, String fname, String lname, String pass, String email, String cnumber, int toc, int 
			atype, int smodel,ArrayList<Car> cars,ArrayList<Integer> stations){
		this.userid = userid;
		this.fname = fname;
		this.lname = lname;
		this.pass = pass;
		this.cnumber = cnumber;
		this.email = email;
		this.toc=toc;
		this.atype=atype;
		this.smodel=smodel;
		this.cars=cars;
		this.stations=stations;;
	}

}
