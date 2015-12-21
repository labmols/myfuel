package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.Station;
import myfuel.gui.ChangePasswordGUI;
import myfuel.gui.UpdateUserDetailsGUI;
import myfuel.gui.CustomerOptionsGUI;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;

public class UserOptionsActions extends GUIActions {
	
	
	private CustomerOptionsGUI gui;
	private UserLoginResponse res;
	
	public UserOptionsActions(MyFuelClient client , UserLoginResponse res)
	{	
		super(client);
		gui =new CustomerOptionsGUI(this);
		gui.setVisible(true);
		this.res = res;
	}
	
	
	public void changePasswordScreen()
	{
		changeFrame(gui,new ChangePassActions(client,res),this);
		
	}
	
	public void updateDetailsScreen()
	{
		changeFrame(gui, new UpdateDetailsActions(client,res),this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	public void carFuelScreen() {
		// TODO Auto-generated method stub
		changeFrame(gui, new CarFuelActions(client,res),this);
	}


	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}
	





}
