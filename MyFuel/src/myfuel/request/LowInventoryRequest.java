package myfuel.request;


import java.util.ArrayList;

@SuppressWarnings("serial")
/***
 * This class will be used as a request object to the server for updating the DB with new Low inventory Levels
 * @author karmo
 *
 */
public class LowInventoryRequest extends Request{
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * New Low Inventory Level
	 */
	private ArrayList<Integer> NewLowInventory ;
	
	/***
	 * LowInventoryRequest Constructor
	 * @param sid - Station ID
	 * @param NewLowInventory - New Low Inventory Level
	 */
	public LowInventoryRequest(int sid,ArrayList<Integer> NewLowInventory)
	{
		setSid(sid);
		setNewLowInventory(NewLowInventory);
	}
	
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public ArrayList<Integer> getNewLowInventory()
	{
		return this.NewLowInventory;
	}
	public void setNewLowInventory(ArrayList<Integer> NewLowInventory)
	{
		this.NewLowInventory=NewLowInventory;
	}

}
