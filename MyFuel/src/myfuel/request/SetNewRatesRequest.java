package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.NetworkRates;
import myfuel.client.Rate;

/***
 * This class will contain the new rates suggestion and will be sent to the server 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class SetNewRatesRequest implements Serializable {
	/***
	 * Type of request
	 */
	private int type;
	/***
	 * New rates suggestion
	 */
	private ArrayList<NetworkRates> NewRates ;

	
	/***
	 * SetNewRatesRequest GUI
	 * @param type - type of request
	 */
	public SetNewRatesRequest(int type)
	{
		this.setType(type);
	}
	
	/***
	 * SetNewRatesRequest GUI
	 * @param type - type of request
	 * @param Newrates - New rates suggestion
	 */
	public SetNewRatesRequest(int type, ArrayList<NetworkRates> Newrates)
	{
		this.setType(type);
		this.setNewRates(Newrates);
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public ArrayList<NetworkRates> getNewRates() {
		return NewRates;
	}

	public void setNewRates(ArrayList<NetworkRates> newRates) {
		NewRates = newRates;
	}

}
