package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.client.Purchase;
import myfuel.gui.CustomerOptionsGUI;
import myfuel.gui.CustomerPurchaseGUI;
import myfuel.request.LoginRequest;
import myfuel.request.PurchaseRequest;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.PurchaseResponse;

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

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof PurchaseResponse)
		{
			PurchaseResponse res = (PurchaseResponse) arg;
			ArrayList<Purchase> pList = new ArrayList<Purchase>(res.getCustomerPurchases());
			gui.showPurchases(pList);
		}
		
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		new CustomerOptionsActions(client, res, lr);
		changeFrame(gui,this);
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);	
	}

}
