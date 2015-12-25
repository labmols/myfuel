package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.SWGUI;
import myfuel.request.RequestEnum;
import myfuel.request.SWRequest;
import myfuel.response.booleanResponse;
import myfuel.response.inventoryResponse;

public class SWActions extends GUIActions {
	private int sid;
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
		

	}



	public void ConfirmOrder() 
	{
		SWRequest request = new SWRequest(sid,RequestEnum.Insert);
		client.handleMessageFromGUI(request);
		
	}

}
