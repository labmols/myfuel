package myfuel.response;

import java.util.ArrayList;

import myfuel.client.QuarterStationPurchase;
import myfuel.client.quarterStationIncome;
import myfuel.client.quarterStationInventory;

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
	private ArrayList<quarterStationInventory> qStationInventory = null;
	/***
	 * Station Income in a Quarter
	 */
	private ArrayList<quarterStationIncome> qStationIncome = null;
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
	public ComapnyReportsResponse(ArrayList<quarterStationInventory> qStationInventory, ArrayList<quarterStationIncome> qStationIncome
															, ArrayList<QuarterStationPurchase> qStationPurchase)
	{
		this.setqStationInventory(qStationInventory);
		this.qStationIncome = qStationIncome;
		this.setqStationPurchase(qStationPurchase);
	}
	
	
	public ArrayList<quarterStationInventory> getqStationInventory() {
		return qStationInventory;
	}
	public void setqStationInventory(ArrayList<quarterStationInventory> qStationInventory) {
		this.qStationInventory = qStationInventory;
	}


	public ArrayList<quarterStationIncome> getqStationIncome() {
		return qStationIncome;
	}


	public void setqStationIncome(ArrayList<quarterStationIncome> qStationIncome) {
		this.qStationIncome = qStationIncome;
	}


	public ArrayList<QuarterStationPurchase> getqStationPurchase() {
		return qStationPurchase;
	}


	public void setqStationPurchase(ArrayList<QuarterStationPurchase> qStationPurchase) {
		this.qStationPurchase = qStationPurchase;
	}
}
