package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.client.Order;
import myfuel.gui.CheckInventoryGUI;
import myfuel.request.CheckInventoryRequest;
import myfuel.response.CheckInventoryResponse;
import myfuel.response.booleanResponse;

public class CheckInventoryActions extends GUIActions{

	private CheckInventoryGUI gui ;
	private int sid;
	private CheckInventoryRequest request;
	private CheckInventoryResponse response;
	
	public CheckInventoryActions(MyFuelClient client,int sid) {
		super(client);
		this.sid = sid;
		 gui = new CheckInventoryGUI(this) ;
		 request=new CheckInventoryRequest(0,this.sid);
		 client.handleMessageFromGUI(request);
		 gui.setVisible(true);
	}
	
	public void UpdateInventory(ArrayList <Integer> FuelId)
	{
		request=new CheckInventoryRequest(1,this.sid,FuelId);
		client.handleMessageFromGUI(request);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
		if(arg1 instanceof CheckInventoryResponse)
		{
			response = (CheckInventoryResponse)arg1;
			gui.setDetails(response.getNewOrder());
		}
		
		else if(gui.isActive() && arg1 instanceof booleanResponse)
		{
	
			booleanResponse resp = (booleanResponse)arg1;
			gui.showMessage(resp.getMsg());
			backToMenu();
		}
	}

	@Override
	public void backToMenu() {
		this.changeFrame(gui,new SMActions(client,sid),this);
	}

}
