package myfuel.request;


import java.util.ArrayList;


/***
 * This class will be used as a request object for 2 things:
 * 1.Request for getting all unapproved customers from the DB
 * 2.Request for updating the approved customers (depends if there was any customers waiting for approve)
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class ConfirmationRequest extends Request{
	
	/***
	 * Customers to be approved
	 */
	private ArrayList<Integer> approved;
	/***
	 * Type of Request
	 */
	private RequestEnum type;
	/***
	 *  ConfirmationRequest Constructor
	 * @param type - Select for getting unapproved customers from the DB
	 */
	public ConfirmationRequest (RequestEnum type)
	{
		this.setType(type);
		this.setApproved(null);
	}
	
	/***
	 * ConfirmationRequest Constructor
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
