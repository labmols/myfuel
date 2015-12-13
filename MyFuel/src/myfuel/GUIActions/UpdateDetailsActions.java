package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.gui.LogInGUI;
import myfuel.gui.UpdateUserDetailsGUI;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;

public class UpdateDetailsActions extends GUIActions {
	Customer user;
	UpdateUserDetailsGUI gui;
	public UpdateDetailsActions(MyFuelClient client,Customer user) {
		super(client);
		this.user=user;
		gui = new UpdateUserDetailsGUI(this);
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	
	public void setGUI(UpdateUserDetailsGUI gui){
		this.gui = gui;
	}
	
	public Customer getUserDetails(){
		return user;
	}
	
	public void returnToMain(){
		UserOptionsActions actions = new UserOptionsActions(client, user);
		changeFrame(gui, actions);
	}




	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


}
