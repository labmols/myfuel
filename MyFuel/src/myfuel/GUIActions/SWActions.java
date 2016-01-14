package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.SWGUI;
import myfuel.request.RequestEnum;
import myfuel.request.SWRequest;
import myfuel.response.booleanResponse;
import myfuel.response.inventoryResponse;

/***
 * Controller for SWGUI
 * @author karmo
 *
 */
public class SWActions extends GUIActions {
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * This GUI object will be controlled by this class
	 */
	private SWGUI gui;
	
	
	public SWActions(MyFuelClient client,int sid) {
		super(client);
		this.sid = sid;
		gui = new SWGUI(this);
		
		SWRequest request = new SWRequest(sid,RequestEnum.Select);
		client.handleMessageFromGUI(request);
		
		gui.setVisible(true);
		
	}

	

	@Override
	public void update(Observable o, Object arg) {
		
		if(arg instanceof inventoryResponse)
		{
			inventoryResponse r = (inventoryResponse)arg;
			gui.updateLables(r.getOrder());
		}
		else if(arg instanceof booleanResponse)
		{
			if(((booleanResponse)arg).getSuccess())
			{
				gui.showOKMessage(((booleanResponse)arg).getMsg());
				gui.deleteTable();
			}
			else gui.showErrorMessage(((booleanResponse)arg).getMsg());
		}

	}

	@Override
	public void backToMenu() {
		changeFrame(gui,this);
		new LoginActions(client);

	}


/***
 * Sending the order to the DB for updating the Station's inventory
 */
	public void ConfirmOrder() 
	{
		SWRequest request = new SWRequest(sid,RequestEnum.Insert);
		client.handleMessageFromGUI(request);
		
	}

}
