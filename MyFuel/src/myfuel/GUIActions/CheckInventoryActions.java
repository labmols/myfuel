package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.MessageForManager;
import myfuel.client.MyFuelClient;
import myfuel.gui.CheckInventoryGUI;
import myfuel.request.CheckInventoryRequest;
import myfuel.response.CheckInventoryResponse;
import myfuel.response.booleanResponse;

public class CheckInventoryActions extends GUIActions{

	private CheckInventoryGUI gui ;
	private int sid;
	private CheckInventoryRequest request;
	private CheckInventoryResponse response;
	private ArrayList<MessageForManager> msg;
	private int nid;
	
	public CheckInventoryActions(MyFuelClient client,int sid,ArrayList<MessageForManager> msg , int nid) {
		super(client);
		this.sid = sid;
		this.msg = msg;
		this.nid = nid;
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
		
		else if(arg1 instanceof booleanResponse)
		{
			MessageForManager temp = null;
			
			for(MessageForManager m : msg)
			{
				if(m.getSid() == sid && m.getType() == 1)
				{
					temp = m;
					break;
				}
			}
			if(temp != null)
				msg.remove(msg.indexOf(temp));
				
			booleanResponse resp = (booleanResponse)arg1;
			if(!resp.getSuccess())
			gui.showErrorMessage(resp.getMsg());
			else gui.showOKMessage(resp.getMsg());
			backToMenu();
		}
	}

	@Override
	public void backToMenu() {
		this.changeFrame(gui,this);
		new SMActions(client,sid,msg,nid);
	}

}
