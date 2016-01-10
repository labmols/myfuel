package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.Rate;


@SuppressWarnings("serial")
public class SetNewRatesResponse extends Response{

	private ArrayList<Rate> oldRates;
	
	public SetNewRatesResponse(ArrayList<Rate> oldRates)
	{
		this.setoldRates(oldRates);
	}

	public ArrayList<Rate> getoldRates() {
		return oldRates;
	}

	public void setoldRates(ArrayList<Rate> oldRates) {
		this.oldRates = oldRates;
	}

}
