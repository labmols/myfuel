package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.gui.ChangePasswordGUI;
import myfuel.request.ChangePassRequest;
import myfuel.request.LoginRequest;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.booleanResponse;

/**
 * Change Password GUI Controller class.
 * @author Maor
 *
 */
public class ChangePassActions extends GUIActions {
	
	/**
	 * The response from the server.
	 */
	private CustomerLoginResponse res;
	
	/**
	 * The GUI object.
	 */
	private ChangePasswordGUI gui;
	
	/**
	 * Create new Change Password Controller.
	 * @param client - MyFuelClient object.
	 * @param res - The Customer Login Response(Contains Customer details and Stations).
	 */
	public ChangePassActions(MyFuelClient client, CustomerLoginResponse res,LoginRequest lr) {
		super(client,lr);
		this.res = res;
		gui = new ChangePasswordGUI(this);
		gui.setVisible(true);
	}
	
	/**
	 * Verify all input text from the user.
	 * @param oldPass - Old Password value.
	 * @param newPass1 - New Password value.
	 * @param newPass2 - Repeat New Password value.
	 */
	public void verifyDetails(char [] oldPass, char [] newPass1, char [] newPass2){
		
		String old = new String(oldPass);
		String nPass1 = new String (newPass1);
		String nPass2 = new String(newPass2);
		
		if(old.equals("") || nPass1.equals("") || nPass2.equals(""))
			gui.showErrorMessage("One or more fields are missing!");
		else if(nPass1.equals(nPass2)){
			changePassword(old,nPass1);
		}
		else{
			gui.showErrorMessage("Passwords are not Equal!");
		}
	}
	
	/**
	 * Create Change Password Request.
	 * @param oldPass - Old Password value.
	 * @param newPass - New Password value.
	 */
	public void changePassword(String oldPass, String newPass)
	{
		ChangePassRequest request = new ChangePassRequest(newPass,res.getUser().getUserid(), oldPass);
		client.handleMessageFromGUI(request);
		
	}
	

	/**
	 * notified by the Client when new response from server received 
	 * and continue handling this response.
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		booleanResponse res = (booleanResponse) arg;
		if(!res.getSuccess())
		gui.showErrorMessage(res.getMsg());
		else gui.showOKMessage(res.getMsg());
			
	}
	
	
	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
	
		changeFrame(gui, this);
		new CustomerOptionsActions(client, res,lr);
		 
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
	}
	
	

}