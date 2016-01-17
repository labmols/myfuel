package myfuel.Response;

import java.util.ArrayList;

import myfuel.Entity.FuelQty;

/***
 * This class will contain the details about the inventory for a specific station
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class LowInventoryResponse extends Response 
{
	/***
	 * Details about the inventory 
	 */
	private ArrayList<FuelQty> qty;
	
	/***
	 * LowInventoryResponse Constructor
	 * @param qty -  Details about the inventory  
	 */
	public LowInventoryResponse(ArrayList<FuelQty> qty)
	{
		this.setQty(new ArrayList<FuelQty>(qty));
	}
	
	
	public ArrayList<FuelQty> getQty() 
	{
		return qty;
	}
	public void setQty(ArrayList<FuelQty> qty) 
	{
		this.qty = qty;
	}

}
