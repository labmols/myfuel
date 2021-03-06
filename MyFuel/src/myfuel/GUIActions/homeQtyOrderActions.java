package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.FuelQty;
import myfuel.Entity.MessageForManager;
import myfuel.GUI.HomeQtyOrderGUI;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Request.homeQtyOrderRequest;
import myfuel.Response.HomeQtyResponse;
import myfuel.Response.booleanResponse;

/***
 * Controller for HomeQtyOrderGUI
 * @author karmo
 *
 */
public class homeQtyOrderActions extends GUIActions {

	/***
	 * THis class will be controller for this gui
	 */
	private HomeQtyOrderGUI gui;
	/***
	 * Current low inventory level
	 */
	private Float level;    // current low level
	/***
	 * Current Home Fuel quantity
	 */
	private Float current;  // current quantity
	/***
	 * The amount that specified in the inventory order
	 */
	private Float orderQty;
	
	/***
	 * This ActionsGUI class has the methods to control homeQtyOrderGUI
	 * @param client - MyFuelCLient
	 * @param order  - Inventory Order for home fuel
	 * @param msg - Messages for this user
	 */
	public homeQtyOrderActions(MyFuelClient client,LoginRequest lr) {
		super(client,lr);
		
		gui = new HomeQtyOrderGUI(this);
		homeQtyOrderRequest request = new homeQtyOrderRequest(RequestEnum.HomeGet);
		gui.createWaitDialog("Getting Details...");
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		
			if(arg1 instanceof HomeQtyResponse)
			{	
				gui.setWaitProgress();
				current = ((HomeQtyResponse)arg1).getMinimal().getQty();
				if(((HomeQtyResponse)arg1).getOrder()!= null)
					orderQty =  ((HomeQtyResponse)arg1).getOrder().getQty();
				gui.insertDetails(((HomeQtyResponse)arg1).getMinimal(),((HomeQtyResponse)arg1).getOrder());
			}
			
			else if(arg1 instanceof booleanResponse)
			{
				gui.setWaitProgress();
				
					if(((booleanResponse)arg1).getSuccess())
						gui.showOKMessage(((booleanResponse)arg1).getMsg());
					else
						gui.showErrorMessage(((booleanResponse)arg1).getMsg());
				
				if(((booleanResponse)arg1).getMsg().equals("New level has been updated!"))
					gui.setCurrMinQty(this.level);
				
				else if(((booleanResponse)arg1).getMsg().equals("New order has been added to inventory!"))
				{
					gui.clearTable();
					gui.setCurrQty(current + orderQty);
				}
				
				
			}
			
	}

	@Override
	public void backToMenu() {
		
	}
/***
 *  Sending Order details to the server
 * @param oDetails
 */
	public void setOrder() 
	{
		gui.createWaitDialog("Updating Inventory Quantity...");
		client.handleMessageFromGUI(new homeQtyOrderRequest(RequestEnum.HomeSetOrder) );
		
	}

	/***
	 * Verification of Low Inventory Level 
	 * @param str - low inventory level has recieved from the user
	 */
	public void setLowLevel(String str) 
	{
		Float low = null;

		if(str.isEmpty())
		{
			gui.showErrorMessage("Please enter a value to set new low level");
			return;
		}
		
		else
		{
			try{
				low = Float.parseFloat(str);
				if(low < 0 )
				{
					gui.showErrorMessage("Low level can't be negative!");
				}
				
				else
				{
					this.level = low;
					gui.createWaitDialog("Updating Low Level Limit...");
					client.handleMessageFromGUI(new homeQtyOrderRequest(RequestEnum.HomeSetLow,low));
				}
			
			} catch(Exception e)
			{
				gui.showErrorMessage("Low Level has to be a number!");
			}
		}
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
