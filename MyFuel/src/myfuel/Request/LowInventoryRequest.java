package myfuel.Request;


import java.util.ArrayList;

/***
 * This class will be used as a request object to the server for updating the DB with new Low inventory Levels
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class LowInventoryRequest extends Request{
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * New Low Inventory Level
	 */
	private ArrayList<Float> NewLowInventory ;
	
	/***
	 * LowInventoryRequest Constructor
	 * @param sid - Station ID
	 * @param NewLowInventory - New Low Inventory Level
	 */
	public LowInventoryRequest(int sid,ArrayList<Float> NewLowInventory)
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


	public ArrayList<Float> getNewLowInventory() {
		return NewLowInventory;
	}


	public void setNewLowInventory(ArrayList<Float> newLowInventory) {
		NewLowInventory = newLowInventory;
	}


}
