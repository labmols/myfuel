package myfuel.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * This class will has customer details
 *
 */
@SuppressWarnings("serial")
public class Customer implements Serializable{
	/***
	 * User ID Number
	 */
	private int userid;
	/**
	 * First Name
	 */
	private String fname;
	/***
	 * Last Name
	 */
	private String lname;
	/***
	 * Password
	 */
	private String pass;
	/***
	 * Email Address
	 */
	private String email;
	/***
	 * Credit Card Number
	 */
	private String cnumber;
	/***
	 * User's Address
	 */
	private String address;
	/***
	 * Type of Customer {Company,Private}
	 */
	private int toc;
	/***
	 * Access Type 
	 */
	private int atype;
	/***
	 * Sale Model
	 */
	private int smodel;
	/***
	 * Cars that owned by this user
	 */
	private ArrayList<Car> cars;
	/***
	 * Stations that this user is signed to
	 */
	private ArrayList<Integer> stations;
	private int LoggedIn;
	
	/***
	 * Customer Constructor
	 * @param userid - User ID
	 * @param fname - FIrst Name
	 * @param lname - Last Name
	 * @param pass - Password
	 * @param email - Email Address
	 * @param address - User's Address
	 * @param cnumber  - user's credit card number
	 * @param toc - type of customer
	 * @param atype - access type
	 * @param smodel - sale model
	 * @param cars  - Cars that owned by this user
	 * @param stations - Stations that this user is signed to
	 */
	public Customer(int userid, String fname, String lname, String pass, String email,String address, String cnumber, int toc, int 
			atype, int smodel,ArrayList<Car> cars,ArrayList<Integer> stations){
		this.setUserid(userid);
		this.setFname(fname);
		this.setLname(lname);
		this.setPass(pass);
		this.setCnumber(cnumber);
		this.setEmail(email);
		this.setToc(toc);
		this.setAtype(atype);
		this.setSmodel(smodel);
		this.setCars(cars);
		this.setStations(stations);
		this.setAddress(address);
	}
	
		/***
		 * Customer constructor
		 * @param userid  - user id 
		 * @param fname  - first name
		 * @param lname  - last name
		 * @param toc  - type of customer
		 */
	public Customer(int userid, String fname, String lname,String email,String pass, int toc) 
	{
		this.userid = userid;
		this.fname = fname;
		this.lname = lname;
		this.toc = toc;
		this.email = email;
		this.pass = pass;
	}


	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnumber() {
		return cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public int getToc() {
		return toc;
	}

	public void setToc(int toc) {
		this.toc = toc;
	}

	public int getAtype() {
		return atype;
	}

	public void setAtype(int atype) {
		this.atype = atype;
	}

	public int getSmodel() {
		return smodel;
	}

	public void setSmodel(int smodel) {
		this.smodel = smodel;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public ArrayList<Integer> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Integer> stations) {
		this.stations = stations;
	}

	public int getLoggedIn() {
		return LoggedIn;
	}

	public void setLoggedIn(int loggedIn) {
		LoggedIn = loggedIn;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	
}
