package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.gui.ChangePasswordGUI;
import myfuel.request.ChangePassRequest;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;
import myfuel.response.booleanResponse;

public class ChangePassActions extends GUIActions {
	
	private Customer user;
	private ChangePasswordGUI gui;
	public ChangePassActions(MyFuelClient client, Customer user) {
		super(client);
		this.user=user;
		gui = new ChangePasswordGUI(this);
		gui.setVisible(true);
	}
	
	public void verifyDetails(char [] oldPass, char [] newPass1, char [] newPass2){
		
		String old = new String(oldPass);
		String nPass1 = new String (newPass1);
		String nPass2 = new String(newPass2);
		
		if(old.equals("") || nPass1.equals("") || nPass2.equals(""))
			gui.showMessage("One or more fields are missing!");
		else if(nPass1.equals(nPass2)){
			changePassword(old,nPass1);
		}
		else{
			gui.showMessage("Passwords are not Equal!");
		}
	}
	
	public void changePassword(String oldPass, String newPass)
	{
		ChangePassRequest request = new ChangePassRequest(newPass,user.getUserid(), oldPass);
		client.handleMessageFromGUI(request);
		
	}
	
	public void returnToMain(){
		UserOptionsActions actions = new UserOptionsActions(client, user);
		changeFrame(gui, actions,this);
	}



	@Override
	public void update(Observable o, Object arg) {
		
		booleanResponse res = (booleanResponse) arg;
		gui.showMessage(res.getMsg());
			
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}
	
	

}