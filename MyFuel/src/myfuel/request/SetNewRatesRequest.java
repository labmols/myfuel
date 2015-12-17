package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;
import myfuel.client.Fuel;

@SuppressWarnings("serial")

public class SetNewRatesRequest implements Serializable {
	private int type;
	private ArrayList<Fuel> NewRates ;

	
	
	public SetNewRatesRequest(int type)
	{
		this.setType(type);
	}
	
	public SetNewRatesRequest(int type, ArrayList<Fuel> Newrates)
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
	public ArrayList<Fuel> getNewRates() {
		return NewRates;
	}
	public void setNewRates(ArrayList<Fuel> NewRates){
		this.NewRates=NewRates;
	}
}
