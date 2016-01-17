package myfuel.Request;



@SuppressWarnings("serial")
/**
 * ChangePassRequest class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
public class ChangePassRequest extends Request {
	
	/**
	 * New Password
	 */
	private String newPass;
	
	/**
	 * Old Password
	 */
	private String oldPass;
	
	/**
	 * Customer ID
	 */
	private int userid;
	
	/**
	 * Create new ChangePassRequest 
	 * @param newPass - New Password
	 * @param userid - User ID
	 * @param oldPass - Old Password
	 */
	public ChangePassRequest(String newPass,int userid,String oldPass)
	{
		this.setNewPass(newPass);
		this.setUserid(userid);
		this.setOldPass(oldPass);
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}
