package myfuel.Request;


import java.util.ArrayList;

/***
 * This class will be used as a request from the server for 2 things:
 * 1. Request for checking if there is any inventory order for specific station
 * 2. Request for updating station inventory (depends if there was any inventory order)
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CheckInventoryRequest extends Request {
	/***
	 * Type of request
	 */
	private int Type;
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * FuelID that its Quantities need to be updated
	 */
	private ArrayList <Integer> FuelId;
	
	/***
	 * CheckInventoryRequest Constructor
	 * @param Type - type of request
	 * @param sid - Station ID
	 */
	public CheckInventoryRequest (int Type,int sid)
	{
		setType(Type);
		setSid(sid);
	}
	
	/***
	 * CheckInventoryRequest Constructor
	 * @param Type - type of request 
	 * @param sid - Station ID
	 * @param FuelId - FuelID that its Quantities need to be updated
	 */
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
