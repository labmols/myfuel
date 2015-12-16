package myfuel.client;

import java.io.Serializable;

public class Fuel implements Serializable {
	private int fid;
	private float fprice;
	private float maxPrice;
	private int qty;
	public Fuel(int fid, float fprice){
		this.setFid(fid);
		this.setFprice(fprice);
	}
	
	public Fuel(int fid, float fprice,float maxPrice, int qty){
		this.setFid(fid);
		this.setFprice(fprice);
		this.setMaxPrice(maxPrice);
		this.setQty(qty);
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public float getFprice() {
		return fprice;
	}

	public void setFprice(float fprice) {
		this.fprice = fprice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}
