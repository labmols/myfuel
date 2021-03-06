package myfuel.Entity;

import java.io.Serializable;
import java.util.Date;

import myfuel.Tools.TimeIgnoringComparator;

/**
 * This class contains all Product Purchase details.
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class Purchase implements Serializable {
	/**
	 * Customer ID.
	 */
	private int customerid;
	
	/**
	 * Purchase ID (Key).
	 */
	private int pid;
	/**
	 * Station ID.
	 */
	private int sid;
	/**
	 * Fuel type ID.
	 */
	private int fuelid;
	/**
	 * Promotion ID(if promotion not used for this purchase it will be -1).
	 */
	private int promid;
	/**
	 * Purchase date and time.
	 */
	private Date pdate;
	/**
	 * Purchase total bill.
	 */
	private float bill;
	/**
	 * Fuel Quantity (Liter).
	 */
	private float qty;
	
	/**
	 * Customer car ID number.
	 */
	private int customerCarID;
	
	/**
	 * Driver name(for company customer).
	 */
	private String driverName;
	
	/**
	 * Station name
	 */
	private String sname;
	/**
	 * Fuel type name
	 */
	private String fuelName;
	
	/**
	 * Is the order has been paid or not.
	 */
	private boolean paid;
	
	public Purchase()
	{
		
	}
	/**
	 * Create new Purchase object with the following parameters.
	 * @param pid - Purchase ID.
	 * @param sid - Station ID.
	 * @param fuelid - Fuel ID.
	 * @param promid - Promotion ID.
	 * @param pdate - Purchase Date.
	 * @param bill - Total Bill value. 
	 * @param qty -Quantity (Liter).
	 */
	public Purchase (int customerid, int pid, int sid, int fuelid, int promid, Date pdate, float bill, float qty,  String driverName,int customerCarID,boolean paid)
	{
		this.setCustomerid(customerid);
		this.setPid(pid);
		this.setSid(sid);
		this.setFuelid(fuelid);
		this.setPromid(promid);
		this.setPdate(pdate);
		this.setBill(bill);
		this.setQty(qty);
		this.setDriverName(driverName);
		this.setCustomerCarID(customerCarID);
		this.setPaid(paid);
	}
	/***
	 * Create new Purchase object with the following parameters.
	 * @param customerid - customer ID
	 * @param pid - Purchase ID.
	 * @param cid - Car ID.
	 * @param sname - Station Name
	 * @param fname - First Name of the driver.
	 * @param pdate - Purchase Date.
	 * @param qty - Quantity.
	 * @param bill - Purchase total bill.
	 * @param paid - Is the order has been paid or not.
	 */
	public Purchase (int customerid, int pid, int cid, String sname, String fname, Date pdate,  float qty,  float bill,boolean paid)
	{
		this.setCustomerid(customerid);
		this.setPid(pid);
		this.setSname(sname);
		this.setFuelName(fname);
		this.setPdate(pdate);
		this.setBill(bill);
		this.setQty(qty);
		this.setCustomerCarID(cid);
		this.setPaid(paid);
	}
	
	/***
	 * Purchase Constructor 
	 * @param customerid - Customer ID
	 * @param fuelid - Fuel ID
	 * @param bill - Total Bill value
	 * @param qty  - Quantity (Liter)
	 */
	public Purchase(int customerid,int fuelid, float bill, float qty )
	{
		this.setCustomerid(customerid);
		this.setFuelid(fuelid);
		this.setBill(bill);
		this.setQty(qty);
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getFuelid() {
		return fuelid;
	}
	public void setFuelid(int fuelid) {
		this.fuelid = fuelid;
	}
	public int getPromid() {
		return promid;
	}
	public void setPromid(int promid) {
		this.promid = promid;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}
	
	public void setQty(float qty)
	{
		this.qty = qty;
	}
	
	public float getQty()
	{
		return qty;
	}



	public int getCustomerid() {
		return customerid;
	}



	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}



	public String getDriverName() {
		return driverName;
	}



	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public int getCustomerCarID() {
		return customerCarID;
	}
	public void setCustomerCarID(int customerCarID) {
		this.customerCarID = customerCarID;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getFuelName() {
		return fuelName;
	}

	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public boolean equals(Object obj)
	{
		Purchase p2 = (Purchase) obj;
		TimeIgnoringComparator compare = new TimeIgnoringComparator(); 
		return this.paid == p2.isPaid() && this.customerCarID == p2.getCustomerCarID()
				&& this.customerid == p2.getCustomerid()
				&& this.fuelid == p2.getFuelid()
				&& this.bill == p2.getBill()
				&& (compare.compare(this.pdate, p2.getPdate()) == 0 )
				&& this.promid == p2.getPromid()
				&& this.qty == p2.getQty() 
				&& this.sid == p2.getSid();
			
				
	}
	
	
	
}
