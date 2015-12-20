package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.LowInventoryGUI;

import myfuel.request.LowInventoryRequest;
import myfuel.response.booleanResponse;

public class LowInventoryActions extends GUIActions {

	int sid;
	private LowInventoryGUI gui ; 
	LowInventoryRequest request;
	
	public LowInventoryActions(MyFuelClient client, int sid) {
		
		super(client);
		this.sid = sid;
		gui = new LowInventoryGUI(this);
		gui.setVisible(true);
	}

	public void verifyDetails(String NewLowInventory)
	{
		int LowInventory=0;
		boolean success = true;
		String error="";
		error += "Input Errors \n\n";
		if(NewLowInventory.equals(""))
		{
			success=false;
			error+="The filed is Empty\n";
		}
		else
		{
			LowInventory=Integer.parseInt(NewLowInventory);
			if(LowInventory<0)
			{
				success=false;
				error+="The input is Negative\n";
			}
		}
		if(!success) gui.showMessage(error);
		else
		{
			request=new LowInventoryRequest(this.sid,LowInventory);
			client.handleMessageFromGUI(request);
		}
	
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(gui.isActive() && arg1 instanceof booleanResponse)
		{
			booleanResponse resp = (booleanResponse)arg1;	
			gui.showMessage(resp.getMsg());
		}
	}

	@Override
	public void backToMenu() {
		changeFrame(gui,new SMActions(client,this.sid),this);

	}

}
