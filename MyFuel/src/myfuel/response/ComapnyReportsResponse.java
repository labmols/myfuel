package myfuel.response;

import java.util.ArrayList;

import myfuel.client.QuarterStationPurchase;
import myfuel.client.QuarterStationIncome;
import myfuel.client.QuarterStationInventory;

/***
 * Will has the response from the DB and has each report's details organized in ArrayLists
 *
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
	 * ComapnyReportsResponse Constructor
	 * @param qStationInventory - Station Inventory in a Quarter
	 * @param qStationIncome  - Station Income in a Quarter
	 * @param qStationPurchase  - Station Purchases in a Quarter
	 */
	public ComapnyReportsResponse(ArrayList<QuarterStationInventory> qStationInventory, ArrayList<QuarterStationIncome> qStationIncome
															, ArrayList<QuarterStationPurchase> qStationPurchase)
	{
		this.setqStationInventory(qStationInventory);
		this.qStationIncome = qStationIncome;
		this.setqStationPurchase(qStationPurchase);
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
}
