package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class LowInventoryRequest implements Serializable {
	private int sid;
	private ArrayList<Integer> NewLowInventory ;
	
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
