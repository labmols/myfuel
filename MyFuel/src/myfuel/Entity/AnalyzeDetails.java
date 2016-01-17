package myfuel.Entity;

import java.io.Serializable;


/***
 * This class will has the details for the analystic system
 *
 */
@SuppressWarnings("serial")
public class AnalyzeDetails implements Serializable
{
	/***
	 *  rate of the value
	 */
	private int rate;
	/***
	 * Type of Customer
	 */
	private int saleModel;
	/***
	 * Fuel ID number
	 */
	private int fuelId;
	/***
	 * Hour of purchase 
	 */
	private String hour;
	
	/***
	 * Sale Model Name
	 */
	private String saleModelName;
	/***
	 * Fuel Name
	 */
	private String fuelName;
	/***
	 * AnalyzeDetails constructor
	 * @param saleModel - Type of Customer
	 * @param count - Number of purchases
	 * @param fuelId - Fuel ID
	 * @param fuelName - Fuel Name
	 * @param saleModelName - Sale Model Name
	 */
	public AnalyzeDetails(int saleModel,int rate ,int fuelId,String fuelName,String saleModelName)
	{
		this.saleModelName = saleModelName;
		this.setFuelName(fuelName);
		this.setSaleModel(saleModel);
		this.setRate(rate);
		this.setFuelId(fuelId);
	}
	
	/***
	 * AnalyzeDetails constructor
	  @param saleModel - Type of Customer
	 * @param saleModelName - Sale Model Name
	 * @param count - Number of purchases
	 * @param hour -  Hour of purchase 
	 */
	public AnalyzeDetails(int saleModel,String saleModelName,String hour,int rate)
	{
		this.setSaleModel(saleModel);
		this.saleModelName = saleModelName;
		this.setRate(rate);
		this.setHour(hour);
	}



	public int getSaleModel() {
		return saleModel;
	}

	public void setSaleModel(int saleModel) {
		this.saleModel = saleModel;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public int getFuelId() {
		return fuelId;
	}

	public void setFuelId(int fuelId) {
		this.fuelId = fuelId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getSaleModelName() {
		return saleModelName;
	}

	public void setSaleModelName(String saleModelName) {
		this.saleModelName = saleModelName;
	}

	public String getFuelName() {
		return fuelName;
	}

	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	

	
	
	
	
	
	
}
