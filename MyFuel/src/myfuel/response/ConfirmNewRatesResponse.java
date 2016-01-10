package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Rate;

@SuppressWarnings("serial")
public class ConfirmNewRatesResponse extends Response 
{
	private ArrayList<Rate> sModes;
	private ArrayList<Rate> current;
	/***
	 * Response from the DB 
	 * @param sModes - the sale modes with their discounts
	 */
	public ConfirmNewRatesResponse(ArrayList<Rate> sModes,ArrayList<Rate> current)
	{
		this.setCurrent(current);
		this.setsModes(sModes);
	}

	public ArrayList<Rate> getsModes() {
		return sModes;
	}

	public void setsModes(ArrayList<Rate> sModes) {
		this.sModes = sModes;
	}

	public ArrayList<Rate> getCurrent() {
		return current;
	}

	public void setCurrent(ArrayList<Rate> current) {
		this.current = current;
	}
}
