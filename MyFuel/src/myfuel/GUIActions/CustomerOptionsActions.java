package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.Station;
import myfuel.gui.ChangePasswordGUI;
import myfuel.gui.UpdateCustomerDetailsGUI;
import myfuel.gui.CustomerOptionsGUI;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;

/**
 * Customer Options Main Menu Controller class.
 * @author Maor
 *
 */
public class CustomerOptionsActions extends GUIActions {
	
	/**
	 * Customer Options Main Menu GUI JFrame.
	 */
	private CustomerOptionsGUI gui;
	
	/**
	 * The Customer Login details.
	 */
	private CustomerLoginResponse res;
	
	/**
	 * Create new Customer Options Main Menu controller.
	 * @param client - MyFuelClient object.
	 * @param res - Customer Login Details.
	 */
	public CustomerOptionsActions(MyFuelClient client , CustomerLoginResponse res)
	{	
		super(client);
		gui =new CustomerOptionsGUI(this);
		gui.setVisible(true);
		this.res = res;
	}
	
	/**
	 * Open the Change Password Screen.
	 */
	public void changePasswordScreen()
	{
		new ChangePassActions(client,res);
		changeFrame(gui,this);
		
		
	}
	
	/**
	 * Open the Update Details Screen.
	 */
	public void updateDetailsScreen()
	{
		new UpdateDetailsActions(client,res);
		changeFrame(gui, this);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Open the Car Fuel Screen.
	 */
	public void carFuelScreen() {
		// TODO Auto-generated method stub
		changeFrame(gui, this);
		new CarFuelActions(client,res);
		
	
		
	}


	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		new LoginActions(client);
		changeFrame(gui, this);
		
		
	}

	/**
	 * Open the Home Fuel Screen.
	 */
	public void HomeFuelScreen() {
		// TODO Auto-generated method stub
		new HomeOrderActions(client,res);
		changeFrame(gui, this);
		
	}
	





}
