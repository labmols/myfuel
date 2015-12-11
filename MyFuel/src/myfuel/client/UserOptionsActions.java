package myfuel.client;

import java.util.Observable;

import myfuel.gui.ChangePasswordGUI;
import myfuel.gui.UpdateUserDetailsGUI;
import myfuel.gui.UserOptionsGUI;
import myfuel.server.Response;
import myfuel.server.UserLoginResponse;

public class UserOptionsActions extends GUIActions {
	
	Customer user;
	UserOptionsGUI gui;
	
	public UserOptionsActions(MyFuelClient client , Customer user)
	{	
		super(client);
		gui =new UserOptionsGUI(this);
		gui.setVisible(true);
		this.user = user;
	}
	
	
	public void changePasswordScreen()
	{
		changeFrame(gui,new ChangePassActions(client,user));
		
	}
	
	public void updateDetailsScreen()
	{
		changeFrame(gui, new UpdateDetailsActions(client,user));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	





}
