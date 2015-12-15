package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Customer implements Serializable{
	private int userid;
	private String fname;
	private String lname;
	private String pass;
	private String email;
	private String cnumber;
	private int toc;
	private int atype;
	private int smodel;
	private ArrayList<Car> cars;
	private ArrayList<Integer> stations;
	private ArrayList<Station> stationsDetails;
	private int LoggedIn;
	
	public Customer(int userid, String fname, String lname, String pass, String email, String cnumber, int toc, int 
			atype, int smodel,ArrayList<Car> cars,ArrayList<Integer> stations, ArrayList<Station> stationsD){
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
		this.setStationsDetails(stationsD);
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

	public ArrayList<Station> getStationsDetails() {
		return stationsDetails;
	}

	public void setStationsDetails(ArrayList<Station> stationsDetails) {
		this.stationsDetails = stationsDetails;
	}

}
