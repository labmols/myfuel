package myfuel.Response;

import java.util.ArrayList;

import myfuel.Entity.QuarterStationIncome;
import myfuel.Entity.QuarterStationInventory;
import myfuel.Entity.QuarterStationPurchase;

/***
 * Will has the response from the DB and has each report's details organized in ArrayLists
 *@author karmo
 */
@SuppressWarnings("serial")
public class ComapnyReportsResponse extends Response 
{
	/***
	 * Station Inventory in a Quarter
	 */
	private ArrayList<QuarterStationInventory> qStationInventory = null;
	/***
	 * Station Income in a Quarter
	 */
	private ArrayList<QuarterStationIncome> qStationIncome = null;
	/***
	 * Station Purchases in a Quarter
	 */
	private ArrayList<QuarterStationPurchase> qStationPurchase = null;
	
	/***
	 * Years that has reports in the DB
	 */
	private ArrayList<Integer> years;
	
	private int type;
	/***
	 * ComapnyReportsResponse Constructor
	 * @param qStationInventory - Station Inventory in a Quarter
	 * @param qStationIncome  - Station Income in a Quarter
	 * @param qStationPurchase  - Station Purchases in a Quarter
	 */
	
	public ComapnyReportsResponse(ArrayList<QuarterStationInventory> qStationInventory, ArrayList<QuarterStationIncome> qStationIncome
															, ArrayList<QuarterStationPurchase> qStationPurchase , int type)
	{
		this.setqStationInventory(qStationInventory);
		this.qStationIncome = qStationIncome;
		this.setqStationPurchase(qStationPurchase);
		this.setType(type);
	}
	
	/***
	 * ComapnyReportsResponse Constructor
	 * @param years - Years that has reports in the DB
	 */
	public ComapnyReportsResponse(ArrayList<Integer> years , int type)
	{
		this.setYears(years);
		this.setType(type);
	}
	
	public ArrayList<QuarterStationInventory> getqStationInventory() {
		return qStationInventory;
	}
	public void setqStationInventory(ArrayList<QuarterStationInventory> qStationInventory) {
		this.qStationInventory = qStationInventory;
	}


	public ArrayList<QuarterStationIncome> getqStationIncome() {
		return qStationIncome;
	}


	public void setqStationIncome(ArrayList<QuarterStationIncome> qStationIncome) {
		this.qStationIncome = qStationIncome;
	}


	public ArrayList<QuarterStationPurchase> getqStationPurchase() {
		return qStationPurchase;
	}


	public void setqStationPurchase(ArrayList<QuarterStationPurchase> qStationPurchase) {
		this.qStationPurchase = qStationPurchase;
	}

	public ArrayList<Integer> getYears() {
		return years;
	}

	public void setYears(ArrayList<Integer> years) {
		this.years = years;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
