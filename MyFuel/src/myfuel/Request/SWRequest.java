package myfuel.Request;



/***
 * This class will be used as a request object from the server. In order to get all approved inventory orders 
 * that need to be inserted to the station inventory and update the inventory if possible.
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class SWRequest extends Request{
	
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * Type of Request
	 */
	private RequestEnum type;
	
	/***
	 *  
	 * @param sid - Station id 
	 * @param type - type of request
	 */
	public SWRequest(int sid,RequestEnum type)
	{
		this.sid = sid;
		this.setType(type);
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}





}
