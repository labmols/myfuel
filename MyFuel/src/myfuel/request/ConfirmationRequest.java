package myfuel.request;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ConfirmationRequest implements Serializable{
	private ArrayList<Integer> approved;
	private RequestEnum type;
	/***
	 *  Create a request object 
	 * @param type - Select for getting unapproved customers from the DB
	 */
	public ConfirmationRequest (RequestEnum type)
	{
		this.setType(type);
		this.setApproved(null);
	}
	
	/***
	 * 
	 * @param type - Insert , updating the approved customers in the DB
	 * @param approved - The id's of the approved customers
	 */
	public ConfirmationRequest (RequestEnum type,ArrayList<Integer> approved)
	{
		this.approved = new ArrayList<Integer>(approved);
		this.type = type;
	}
	
	public RequestEnum getType() {
		return type;
	}
	public void setType(RequestEnum type) {
		this.type = type;
	}

	public ArrayList<Integer> getApproved() {
		return approved;
	}

	public void setApproved(ArrayList<Integer> approved) {
		this.approved = approved;
	}
	
	

}
