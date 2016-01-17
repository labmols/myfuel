package myfuel.response;

import java.util.ArrayList;

import myfuel.Entity.FuelQty;
import myfuel.Entity.Purchase;


/***
 * This class contains all the details for the Station Report as retreived from the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class SReportResponse extends Response 
{
	/***
	 * Inventory Report Details
	 */
	private ArrayList<FuelQty> inventory;

	/***
	 * Purchases Report
	 */
	private ArrayList<Purchase> p;
	/***
	 * Incomes Report
	 */
	private ArrayList<Purchase> incomes;
	
	/***
	 * SReportResponse Constructor
	 * @param report_type - type of report
	 * @param inventory - Inventory Report Details
	 * @param p - Purchases Report Details
 	 * @param incomes - Incomes Report Details
	 */
	public SReportResponse(ArrayList<FuelQty> inventory,ArrayList<Purchase> p,ArrayList<Purchase> incomes)
	{
		
		this.setInventory(inventory);
		this.setP(p);
		this.setIncomes(incomes);
	}

	
	public ArrayList<FuelQty> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<FuelQty> inventory) {
		this.inventory = inventory;
	}




	public ArrayList<Purchase> getP() {
		return p;
	}


	public void setP(ArrayList<Purchase> p) {
		this.p = p;
	}


	public ArrayList<Purchase> getIncomes() {
		return incomes;
	}


	public void setIncomes(ArrayList<Purchase> incomes) {
		this.incomes = incomes;
	}
}
