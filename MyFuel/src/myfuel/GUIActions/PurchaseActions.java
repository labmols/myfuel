package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.Purchase;
import myfuel.GUI.CustomerOptionsGUI;
import myfuel.GUI.CustomerPurchaseGUI;
import myfuel.Request.LoginRequest;
import myfuel.Request.PurchaseRequest;
import myfuel.Response.CustomerLoginResponse;
import myfuel.Response.PurchaseResponse;
import myfuel.Response.booleanResponse;

/**
 * Purchase History GUI Controller, handle all the logic functionality and getting info from DB.
 * @author Maor
 *
 */
public class PurchaseActions extends GUIActions {
	/**
	 * Customer Purchase user interface.
	 */
	CustomerPurchaseGUI gui;
	
	/**
	 * Customer Login details.
	 */
	CustomerLoginResponse res;
	
	/**
	 * Create new Purchase History GUI Controller.
	 * @param client - MyFuelClient object(required for the communication with the server).
	 * @param lr - Login Request details(required for the customer ID).
	 */
	public PurchaseActions(MyFuelClient client,CustomerLoginResponse res, LoginRequest lr) {
		super(client, lr);
		this.res = res;
		gui = new CustomerPurchaseGUI(this);
		gui.setVisible(true);
		getPurchases();
	}
	
	/**
	 * Get the purchase history list for the specific customer.
	 */
	private void getPurchases()
	{
		PurchaseRequest req = new PurchaseRequest(res.getUser().getUserid());
		client.handleMessageFromGUI(req);
	}

	/**
	 * Get purchase response from the server(contains all the customer purchases list).
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof PurchaseResponse)
		{
			PurchaseResponse res = (PurchaseResponse) arg;
			ArrayList<Purchase> pList = new ArrayList<Purchase>(res.getCustomerPurchases());
			gui.Init(pList);
			gui.addDates(res.getDateList());
		}
		
		else if(arg instanceof booleanResponse)
		{
			booleanResponse res = (booleanResponse) arg;
			gui.showOKMessage(res.getMsg());
			this.backToMenu();
		}
		
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub

		changeFrame(gui,this);
		new CustomerOptionsActions(client, res, lr);
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);	
	}

}
