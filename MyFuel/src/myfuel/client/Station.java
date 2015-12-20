package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable {
	private int sid;
	private String sname;
    private ArrayList<Fuel> fuels;
	private int minqty;

	public Station(int sid,String sname ){
		this.sid = sid;
		setFuels(null);
		this.sname = sname;
	}
	
	public Station(int sid, String sname, int minqty, ArrayList<Fuel> fuels) {
		this.sid = sid;
		this.sname = sname;
		this.minqty= minqty;
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

	public int getMinqty() {
		return minqty;
	}

	public void setMinqty(int minqty) {
		this.minqty = minqty;
	}
}


