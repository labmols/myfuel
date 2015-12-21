package myfuel.client;

import java.io.Serializable;


@SuppressWarnings("serial")
public class FuelQty implements Serializable{
	private int fid;
	private String fname;
	private float qty;
	private float mqty;
	
	public FuelQty (int fid , float qty, float mqty)
	{
		setFid(fid);
		setQty(qty);
		setMqty(mqty);
	}
	
	public FuelQty(String fname,int fid , float qty )
	{
		this.fname = fname;
		this.fid = fid;
		this.qty = qty;
	}
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getMqty() {
		return mqty;
	}
	public void setMqty(float mqty) {
		this.mqty = mqty;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
}
