package myfuel.Response;

import java.util.ArrayList;

import myfuel.Entity.Fuel;
import myfuel.Entity.Network;
import myfuel.Entity.NetworkRates;
import myfuel.Entity.Rate;

/***
 * Response from the server with the Old Rates and Networks Details
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class SetNewRatesResponse extends Response{
	
	/***
	 * Old Rates linked to network
	 */
	private ArrayList<NetworkRates> oldRates;
	/***
	 * Networks Details
	 */
	private ArrayList<Network> networks;
	
	/***
	 * SetNewRatesResponse Constructor
	 * @param oldRates - Old Rates linked to network
	 * @param networks - Networks Details
	 */
	public SetNewRatesResponse(ArrayList<NetworkRates> oldRates,ArrayList<Network> networks)
	{
		this.setNetworks(networks);
		this.setOldRates(oldRates);
	}

	public ArrayList<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<Network> networks) {
		this.networks = networks;
	}

	public ArrayList<NetworkRates> getOldRates() {
		return oldRates;
	}

	public void setOldRates(ArrayList<NetworkRates> oldRates) {
		this.oldRates = oldRates;
	}



}
