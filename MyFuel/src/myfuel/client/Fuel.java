package myfuel.client;

import java.io.Serializable;

public class Fuel implements Serializable {
	private int fid;
	private float fprice;
	
	public Fuel(int fid, float fprice){
		this.fid=fid;
		this.fprice = fprice;
	}
	
}
