package myfuel.client;

import java.io.Serializable;
import java.util.Date;

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
	
	private String driverName;
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
	public Purchase (int customerid, int pid, int sid, int fuelid, int promid, Date pdate, float bill, float qty,  String driverName)
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
	
	
	
	
	
}
