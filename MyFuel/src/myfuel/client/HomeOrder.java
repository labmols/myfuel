package myfuel.client;

import java.io.Serializable;
import java.util.Date;

/**
 * This class contains all Home Order details.
 *
 *
 */
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
	 * Order Quantity
	 */
	private int qty;
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
	private int status;
	
	/**
	 * Create new Home Order instance.
	 * @param customerid - Customer ID.
	 * @param orderid - Order ID.
	 * @param qty - Order Quantity.
	 * @param address - Customer Address.
	 * @param shipDate - Shipping Date.
	 * @param status - Order Status.
	 */
	public HomeOrder(int customerid, int orderid, int qty, String address, Date shipDate, int status)
	{
		setCustomerid(customerid);
		setOrderid(orderid);
		setQty(qty);
		setAddress(address);
		setShipDate(shipDate);
		setStatus(status);
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
