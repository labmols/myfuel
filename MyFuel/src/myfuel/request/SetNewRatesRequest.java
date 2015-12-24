package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.saleModel;

@SuppressWarnings("serial")

public class SetNewRatesRequest implements Serializable {
	private int type;
	private ArrayList<saleModel> NewRates ;

	
	
	public SetNewRatesRequest(int type)
	{
		this.setType(type);
	}
	
	public SetNewRatesRequest(int type, ArrayList<saleModel> Newrates)
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
	public ArrayList<saleModel> getNewRates() {
		return NewRates;
	}
	public void setNewRates(ArrayList<saleModel> NewRates){
		this.NewRates=NewRates;
	}
}
