package myfuel.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * This class contains all Home Order details.
 *
 *
 */
@SuppressWarnings("serial")
public class HomeOrder implements Serializable {
	
	/**
	 * Customer ID.
	 */
	private int customerid;
	/**
	 * Order ID.
	 */
	private int orderid;

	/**
	 * Customer Address
	 */
	private String address;
	/**
	 * Desired Shipping date.
	 */
	private Date shipDate;
	/**
	 * Order status
	 */
	private boolean status;
	
	private Purchase homeP;
	
	/**
	 * Is this order urgent ? (within 6 hours from now).
	 */
	private boolean urgent;
	
	/**
	 * Create new Home Order instance.
	 * @param customerid - Customer ID.
	 * @param orderid - Order ID.
	 * @param qty - Order Quantity.
	 * @param address - Customer Address.
	 * @param shipDate - Shipping Date.
	 * @param status - Order Status.
	 */
	public HomeOrder(int customerid, int orderid, String address, Date shipDate,boolean status, boolean urgent,Purchase homeP)
	{
		setCustomerid(customerid);
		setOrderid(orderid);
		setAddress(address);
		setShipDate(shipDate);
		setStatus(status);
		setUrgent(urgent);
		setHomeP(homeP);
	}
	
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public Purchase getHomeP() {
		return homeP;
	}

	public void setHomeP(Purchase homeP) {
		this.homeP = homeP;
	}


	
}
