package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.MessageForManager;
import myfuel.GUI.CheckInventoryGUI;
import myfuel.Request.CheckInventoryRequest;
import myfuel.Request.LoginRequest;
import myfuel.Response.CheckInventoryResponse;
import myfuel.Response.booleanResponse;

/***
 * Controller for the CheckInventoryGUI
 * @author karmo
 *
 */
public class CheckInventoryActions extends GUIActions{

	/***
	 * This class will be used as a controller for this GUI object
	 */
	private CheckInventoryGUI gui ;
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * Details about the requested data from the server 
	 */
	private CheckInventoryRequest request;
	/***
	 * Response from the server
	 */
	private CheckInventoryResponse response;
	/***
	 * Messages for the Manager
	 */
	private ArrayList<MessageForManager> msg;
	/***
	 * Network ID
	 */
	private int nid;
	/***
	 * Station Name
	 */
	private String StationName;
	/***
	 * CheckInventoryActions Constructor
	 * @param client - MyFuelClient
	 * @param sid - Station ID
	 * @param msg - Message
	 * @param nid - Network ID
	 * @param StationName - Station name
	 */
	public CheckInventoryActions(MyFuelClient client,int sid,ArrayList<MessageForManager> msg , int nid,LoginRequest lr,String StationName) {
		super(client,lr);
		this.sid = sid;
		this.msg = msg;
		this.nid = nid;
		this.StationName = StationName;
		 gui = new CheckInventoryGUI(this,StationName) ;
		 request=new CheckInventoryRequest(0,this.sid);
		 gui.createWaitDialog("Getting Details... ");
		 client.handleMessageFromGUI(request);
		 gui.setVisible(true);
	}
	

	/***
	 * Updates the inventory with the Quantities as Ordered
	 * @param FuelId - Contains the the Quantities as Ordered for each Ordered Fuel
	 */
	public void UpdateInventory(ArrayList <Integer> FuelId)
	{
		request=new CheckInventoryRequest(1,this.sid,FuelId);
		gui.createWaitDialog("Updating Details... ");
		client.handleMessageFromGUI(request);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
		if(arg1 instanceof CheckInventoryResponse)
		{
			gui.setWaitProgress();
			response = (CheckInventoryResponse)arg1;
			gui.setDetails(response.getNewOrder());
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			MessageForManager temp = null;
			gui.setWaitProgress();
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
		new SMActions(client,sid,msg,nid,lr,StationName);
		
	}


	@Override
	public void LogOut() {
		this.LogOutRequest(gui, lr);
		
	}

}
