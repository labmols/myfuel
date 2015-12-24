package myfuel.response;

import java.util.ArrayList;

import myfuel.client.saleModel;

@SuppressWarnings("serial")
public class ConfirmNewRatesResponse extends Response 
{
	private ArrayList<saleModel> sModes;
	private ArrayList<saleModel> current;
	/***
	 * Response from the DB 
	 * @param sModes - the sale modes with their discounts
	 */
	public ConfirmNewRatesResponse(ArrayList<saleModel> sModes,ArrayList<saleModel> current)
	{
		this.setCurrent(current);
		this.setsModes(sModes);
	}

	public ArrayList<saleModel> getsModes() {
		return sModes;
	}

	public void setsModes(ArrayList<saleModel> sModes) {
		this.sModes = sModes;
	}

	public ArrayList<saleModel> getCurrent() {
		return current;
	}

	public void setCurrent(ArrayList<saleModel> current) {
		this.current = current;
	}
}
