package myfuel.response;

import java.util.ArrayList;

import myfuel.client.FuelQty;
import myfuel.client.Purchase;
import myfuel.client.ReportEnum;

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
	 * Type of report
	 */
	private ReportEnum report_type;
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
	public SReportResponse(ReportEnum report_type,ArrayList<FuelQty> inventory,ArrayList<Purchase> p,ArrayList<Purchase> incomes)
	{
		this.setReport_type(report_type);
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

	public ReportEnum getReport_type() {
		return report_type;
	}

	public void setReport_type(ReportEnum report_type) {
		this.report_type = report_type;
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
