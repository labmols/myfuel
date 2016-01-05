package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.FuelQty;
import myfuel.client.MyFuelClient;
import myfuel.gui.HomeQtyOrderGUI;
import myfuel.request.RequestEnum;
import myfuel.request.homeQtyOrderRequest;
import myfuel.response.HomeQtyResponse;
import myfuel.response.booleanResponse;

public class homeQtyOrderActions extends GUIActions {

	private HomeQtyOrderGUI gui;
	private Float level;    // current low level
	private Float current;  // current quantity
	private Float orderQty;
	/***
	 * This ActionsGUI class has the methods to control homeQtyOrderGUI
	 * @param client - MyFuelCLient
	 */
	public homeQtyOrderActions(MyFuelClient client) {
		super(client);
		gui = new HomeQtyOrderGUI(this);
		homeQtyOrderRequest request = new homeQtyOrderRequest(RequestEnum.HomeGet);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
	}
/***
 * Handling Message from the Server due to its type
 */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		
			if(arg1 instanceof HomeQtyResponse)
			{				 
				current = ((HomeQtyResponse)arg1).getMinimal().getQty();
				if(((HomeQtyResponse)arg1).getOrder()!= null)
					orderQty =  ((HomeQtyResponse)arg1).getOrder().getQty();
				gui.insertDetails(((HomeQtyResponse)arg1).getMinimal(),((HomeQtyResponse)arg1).getOrder());
			}
			
			else if(arg1 instanceof booleanResponse)
			{
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
/***
 * Return to main menu
 */
	@Override
	public void backToMenu() {
		changeFrame(gui,new CMActions(client),this);

	}
/***
 *  Sending Order details to the server
 * @param oDetails
 */
	public void setOrder() 
	{
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
					client.handleMessageFromGUI(new homeQtyOrderRequest(RequestEnum.HomeSetLow,low));
				}
			
			} catch(Exception e)
			{
				gui.showErrorMessage("Low Level has to be a number!");
			}
		}
		
	}

}
