package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * Rates in specific network
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class NetworkRates implements Serializable{
	
	/***
	 * Network ID
	 */
	private int nid;
	
	/***
	 * Rates in the network
	 */
	private ArrayList<Rate> rates;
	
	/***
	 * NetworkRates Constructor
	 * @param nid - Network ID
	 * @param rates -  Rates in the network
	 */
	public NetworkRates(int nid, ArrayList<Rate> rates)
	{
		this.setNid(nid);
		this.setRates(rates);
	}

	public ArrayList<Rate> getRates() {
		return rates;
	}
	
	/***
	 * This method gets modelid and return its rate in the network
	 * @param modelid - the wanted modelid
	 * @return the rate in this network (if not found returns -1)
	 */
	public float getModelDiscount(int modelid)
	{
		for(Rate r: rates)
		{
			if(r.getModelid() == modelid)
				return r.getDiscount();
		}
		return -1;
	}

	public void setRates(ArrayList<Rate> rates) {
		this.rates = rates;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}
	
	

}
