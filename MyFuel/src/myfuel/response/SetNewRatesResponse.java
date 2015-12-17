package myfuel.response;

import java.util.ArrayList;
import myfuel.client.Fuel;


@SuppressWarnings("serial")
public class SetNewRatesResponse extends Response{

	private ArrayList<Fuel> oldRates;
	
	public SetNewRatesResponse(ArrayList<Fuel> oldRates)
	{
		this.setoldRates(oldRates);
	}

	public ArrayList<Fuel> getoldRates() {
		return oldRates;
	}

	public void setoldRates(ArrayList<Fuel> oldRates) {
		this.oldRates = oldRates;
	}

}
