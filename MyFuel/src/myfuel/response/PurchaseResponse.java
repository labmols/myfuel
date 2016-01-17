package myfuel.response;

import java.util.ArrayList;
import java.util.Date;

import myfuel.Entity.Purchase;

/**
 *This class contain the response details from DB for a specific customer purchases list.
 */
@SuppressWarnings("serial")
public class PurchaseResponse extends Response {
	
	/**
	 * List of Customer purchases.
	 */
	private ArrayList <Purchase> customerPurchases;
	/**
	 * List of sorted months list for showing the purchase according month.
	 */
	private ArrayList <Date> dateList;
	
	public PurchaseResponse(ArrayList<Purchase> customerPurchases,ArrayList <Date> dateList)
	{
		this.setCustomerPurchases(customerPurchases);
		this.setDateList(dateList);
	}

	public ArrayList <Purchase> getCustomerPurchases() {
		return customerPurchases;
	}

	public void setCustomerPurchases(ArrayList <Purchase> customerPurchases) {
		this.customerPurchases = customerPurchases;
	}

	public ArrayList <Date> getDateList() {
		return dateList;
	}

	public void setDateList(ArrayList <Date> dateList) {
		this.dateList = dateList;
	}
	
	

}
