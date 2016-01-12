package myfuel.client;

import java.io.Serializable;

/***
 * This class will contain message details for display
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class MessageForManager implements Serializable
{
	/***
	 * Message Content
	 */
	private String msg;
	
	/***
	 * Message ID
	 */
	private int id;
	
	/***
	 * Type of Message
	 */
	private int type;
	
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * MessageForManager Constructor
	 * @param id - Message ID
	 * @param msg - Message Content
	 * @param type  - type of Message 
	 *  type = 0 -> New Rates Confirmation
	 *  type = 1 -> New Inventory Order
	 */
	public MessageForManager(int id , String msg , int sid , int type)
	{
		this.setMsg(msg);
		this.setId(id);
		this.setType(type);
		this.setSid(sid);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}