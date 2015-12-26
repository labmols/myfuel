package myfuel.client;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PromotionReport implements Serializable {
	
	private String pname;
	private int uid;
	private String fname;
	private String lname;
	private float qty ;
	private int toc;
	private float bill;
	private String fuelType;
	private Date pdate;
	private int pid;
	
	/***
	 * Constructor of PromotionReport class
	 * @param pname - promotion id
	 * @param uid - user id
	 * @param fname - first name
	 * @param lname - last name
	 * @param qty - Quantity
	 * @param toc - Type of Customer
	 * @param bill - Bill of the purchase
	 * @param fuelType - Fuel Type (By name)
	 * @param pdate - Purchase Date
	 * @param pid - Promotion ID
	 */
	public PromotionReport(String pname,int uid,String fname,String lname,float qty,int toc,float bill, String fuelType,Date pdate,int pid)
	{
		this.pname = pname;
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.qty = qty;
		this.toc = toc;
		this.bill = bill;
		this.setFuelType(fuelType);
		this.setPdate(pdate);
		this.setPid(pid);
		
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

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
