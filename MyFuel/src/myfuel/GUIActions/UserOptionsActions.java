package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.gui.ChangePasswordGUI;
import myfuel.gui.UpdateUserDetailsGUI;
import myfuel.gui.UserOptionsGUI;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;

public class UserOptionsActions extends GUIActions {
	
	Customer customer;
	UserOptionsGUI gui;
	
	public UserOptionsActions(MyFuelClient client , Customer customer)
	{	
		super(client);
		gui =new UserOptionsGUI(this);
		gui.setVisible(true);
		this.customer = customer;
	}
	
	
	public void changePasswordScreen()
	{
		changeFrame(gui,new ChangePassActions(client,customer),this);
		
	}
	
	public void updateDetailsScreen()
	{
		changeFrame(gui, new UpdateDetailsActions(client,customer),this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	public void carFuelScreen() {
		// TODO Auto-generated method stub
		changeFrame(gui, new CarFuelActions(client,customer),this);
	}
	





}
