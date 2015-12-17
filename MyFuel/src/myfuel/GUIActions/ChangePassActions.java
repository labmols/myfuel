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
	
	Customer user;
	ChangePasswordGUI gui;
	public ChangePassActions(MyFuelClient client, Customer user) {
		super(client);
		this.user=user;
		gui = new ChangePasswordGUI(this);
		gui.setVisible(true);
	}
	
	public void changePassword(char [] origPass, char [] newPass1, char [] newPass2)
	{
		String nPass1 = new String (newPass1);
		String nPass2 = new String(newPass2);
		if(!nPass1.equals(nPass2)){
			try{
				gui.displayMessage(false);
			}
			catch (NullPointerException e){
				e.printStackTrace();
			}
		}
		else {
		ChangePassRequest request = new ChangePassRequest(nPass1,user.getUserid());
		client.handleMessageFromGUI(request);
		}
	}
	
	public void returnToMain(){
		UserOptionsActions actions = new UserOptionsActions(client, user);
		changeFrame(gui, actions,this);
	}



	@Override
	public void update(Observable o, Object arg) {
		
		booleanResponse res = (booleanResponse) arg;
			if(res.getSuccess()){
				gui.displayMessage(true);
		}
		else gui.displayMessage(false);
		
		}

	@Override
	public void backToMenu() {
		changeFrame(gui,new UserOptionsActions(client,user),this);
		
	}
	
	

}
