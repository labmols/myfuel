package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CheckInventoryRequest implements Serializable {
	private int Type;
	private int sid;
	private ArrayList <Integer> FuelId;
	
	
	public CheckInventoryRequest (int Type,int sid)
	{
		setType(Type);
		setSid(sid);
	}
	public CheckInventoryRequest (int Type,int sid,ArrayList <Integer> FuelId)
	{
		setType(Type);
		setSid(sid);
		setFuelId(FuelId);
	}
	public int getType() {
		return Type;
	}

	public void setType(int Type) {
		this.Type = Type;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public ArrayList <Integer> getFuelId() {
		return this.FuelId;
	}
	public void setFuelId(ArrayList <Integer> FuelId) {
		this.FuelId = FuelId;
	}
}
