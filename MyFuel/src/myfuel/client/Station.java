package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable {
	private int sid;
	private String sname;
	private ArrayList<Fuel> fuels;
	public Station(int sid,String sname, ArrayList<Fuel> fuels ){
		this.sid = sid;
		this.setFuels(fuels);
		this.sname = sname;
	}
	
	public Station(int sid,String sname ){
		this.sid = sid;
		setFuels(null);
		this.sname = sname;
	}
	
	public String getName(){
		return this.sname;
	}
	
	public int getsid(){
		return this.sid;
	}

	public ArrayList<Fuel> getFuels() {
		return fuels;
	}

	public void setFuels(ArrayList<Fuel> fuels) {
		this.fuels = fuels;
	}
}


