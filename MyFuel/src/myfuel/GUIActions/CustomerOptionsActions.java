package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import com.alee.managers.notification.NotificationManager;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.Station;
import myfuel.gui.ChangePasswordGUI;
import myfuel.gui.UpdateCustomerDetailsGUI;
import myfuel.gui.CustomerOptionsGUI;
import myfuel.request.LoginRequest;
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
	public CustomerOptionsActions(MyFuelClient client , CustomerLoginResponse res, LoginRequest lr)
	{	
		
		super(client,lr);
		
		gui =new CustomerOptionsGUI(this);
		gui.setVisible(true);
		NotificationManager.setLocation(2);
		NotificationManager.showNotification (gui,"No new Messages" );
		this.res = res;
	}
	
	/**
	 * Open the Change Password Screen.
	 */
	public void changePasswordScreen()
	{

		changeFrame(gui,this);
		new ChangePassActions(client,res,lr);
		
		
	}
	
	/**
	 * Open the Update Details Screen.
	 */
	public void updateDetailsScreen()
	{

		changeFrame(gui, this);
		new UpdateDetailsActions(client,res,lr);
		
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
	
		new CarFuelActions(client,res,lr);
		changeFrame(gui, this);
		
	
		
	}


	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
	
		
		
	}

	/**
	 * Open the Home Fuel Screen.
	 */
	public void HomeFuelScreen() {
		// TODO Auto-generated method stub

		changeFrame(gui, this);
		new HomeOrderActions(client,res,lr);
		
	}

	@Override
	public void LogOut() {
	this.LogOutRequest(gui, lr);	
		
	}

	public void PurchaseHistory() {
		// TODO Auto-generated method stub

		changeFrame(gui, this);
		new PurchaseActions(client,res,lr);
	}
	





}
