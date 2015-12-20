package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable {
	private int sid;
	private String sname;
	
	public Station(int sid,String sname ){
		this.sid = sid;
		this.sname = sname;
	}
	

	public String getName(){
		return this.sname;
	}
	
	public int getsid(){
		return this.sid;
	}

	

}


