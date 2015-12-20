package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ConfirmationRequest implements Serializable{
	
	private int type;
	/***
	 *  Create a request object 
	 * @param type - 1 for getting unapproved customers from the DB
	 */
	public ConfirmationRequest (int type)
	{
		this.setType(type);
	}
	
	/***
	 * 
	 * @return type of reuqest
	 */
	public int getType() {
		return type;
	}
	
	/***
	 *  set type 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

}
