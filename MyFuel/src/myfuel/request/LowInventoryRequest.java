package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LowInventoryRequest implements Serializable {
	private int sid;
	private int NewLowInventory;
	
	public LowInventoryRequest(int sid,int NewLowInventory)
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
	public int getNewLowInventory() {
		return NewLowInventory;
	}
	public void setNewLowInventory(int newLowInventory) {
		NewLowInventory = newLowInventory;
	}

}
