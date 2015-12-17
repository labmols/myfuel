package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Fuel;

@SuppressWarnings("serial")
public class ConfirmRatesResponse extends Response {
	private ArrayList<Fuel> fuels;
	
	/***
	 * In the class the fuel prices will be sent to the client from the DB
	 * @param fuels - fuels prices
	 */
	public ConfirmRatesResponse(ArrayList<Fuel> fuels)
	{
		this.fuels = fuels;
	}
	
	/***
	 *  
	 * @return ArrayList of the fuel prices
	 */
	public ArrayList<Fuel> getFuels() {
		return fuels;
	}
	
	/***
	 *  sets fuel prices ArrayList
	 * @param fuels 
	 */
	public void setFuels(ArrayList<Fuel> fuels) {
		this.fuels = fuels;
	}

}
