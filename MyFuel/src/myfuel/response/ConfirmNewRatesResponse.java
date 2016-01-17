package myfuel.response;

import java.util.ArrayList;

import myfuel.Entity.Rate;


/***
 * This class contains details about the rates of specific company as received from the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class ConfirmNewRatesResponse extends Response 
{
	/***
	 * Suggested rates
	 */
	private ArrayList<Rate> sModes;
	/***
	 * Current Rates
	 */
	private ArrayList<Rate> current;
	
	
	/***
	 * ConfirmNewRatesResponse Constructor
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
