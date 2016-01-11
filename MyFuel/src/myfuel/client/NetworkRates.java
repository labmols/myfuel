package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

public class NetworkRates implements Serializable{
	
	private int nid;
	
	private ArrayList<Rate> rates;
	
	public NetworkRates(int nid, ArrayList<Rate> rates)
	{
		this.setNid(nid);
		this.setRates(rates);
	}

	public ArrayList<Rate> getRates() {
		return rates;
	}
	
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
