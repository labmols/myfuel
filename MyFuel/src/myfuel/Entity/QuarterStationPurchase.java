package myfuel.Entity;

import java.io.Serializable;

/***
 * This class will has the purchase details that was in a stations during a specific quarter
 *
 */
@SuppressWarnings("serial")
public class QuarterStationPurchase implements Serializable{
	/***
	 * Purchase details
	 */
	private QuarterStationIncome q;
	/***
	 * Fuel name
	 */
	private String fuelName;
	
	/***
	 * qStationPurchase constructor
	 * @param q - Purchase details
	 * @param fuelName - Fuel name
	 */
	public QuarterStationPurchase(QuarterStationIncome q , String fuelName)
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



	public QuarterStationIncome getQ() {
		return q;
	}



	public void setQ(QuarterStationIncome q) {
		this.q = q;
	}

}
