package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Fuel;
import myfuel.client.saleModel;


@SuppressWarnings("serial")
public class SetNewRatesResponse extends Response{

	private ArrayList<saleModel> oldRates;
	
	public SetNewRatesResponse(ArrayList<saleModel> oldRates)
	{
		this.setoldRates(oldRates);
	}

	public ArrayList<saleModel> getoldRates() {
		return oldRates;
	}

	public void setoldRates(ArrayList<saleModel> oldRates) {
		this.oldRates = oldRates;
	}

}
