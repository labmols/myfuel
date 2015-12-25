package myfuel.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PromotionReport implements Serializable {
	
	private String pname;
	private int uid;
	private String fname;
	private String lname;
	private float qty ;
	private int toc;
	private float bill;
	
	public PromotionReport(String pname,int uid,String fname,String lname,float qty,int toc,float bill)
	{
		this.pname = pname;
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.qty = qty;
		this.toc = toc;
		this.bill = bill;
		
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
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
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public int getToc() {
		return toc;
	}
	public void setToc(int toc) {
		this.toc = toc;
	}
	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
