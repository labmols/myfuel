package myfuel.client;

import java.io.Serializable;

/***
 * This class will has the purchase details that was in a stations during a specific quarter
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class QuarterStationPurchase implements Serializable{
	/***
	 * Purchase details
	 */
	private quarterStationIncome q;
	/***
	 * Fuel name
	 */
	private String fuelName;
	
	/***
	 * qStationPurchase constructor
	 * @param q - Purchase details
	 * @param fuelName - Fuel name
	 */
	public QuarterStationPurchase(quarterStationIncome q , String fuelName)
	{
		this.setQ(q);
		this.setFuelName(fuelName);
	}



	public String getFuelName() {
		return fuelName;
	}

	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}



	public quarterStationIncome getQ() {
		return q;
	}



	public void setQ(quarterStationIncome q) {
		this.q = q;
	}

}
