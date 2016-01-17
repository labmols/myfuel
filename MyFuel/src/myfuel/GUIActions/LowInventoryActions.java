package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.Fuel;
import myfuel.Entity.MessageForManager;
import myfuel.GUI.LowInventoryGUI;
import myfuel.Request.LoginRequest;
import myfuel.Request.LowInventoryRequest;
import myfuel.Response.LowInventoryResponse;
import myfuel.Response.booleanResponse;

/***
 * Controller for  LowInventoryGUI
 * @author karmo
 *
 */
public class LowInventoryActions extends GUIActions {

	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * This class will be used as a controller for this GUI object
	 */
	private LowInventoryGUI gui ; 
	/***
	 * Details about the requested data from the server 
	 */
	private LowInventoryRequest request;
	/***
	 * Contains the new low inventory Level as set by the manager
	 */
	private ArrayList<Float> NewLowInventory ;
	/***
	 * Messages for the manager
	 */
	private ArrayList<MessageForManager> msg;
	/***
	 * Network ID
	 */
	private int nid ; 
	
	/***
	 * LowInventoryActions Constructor
	 * @param client - MyFuelClient
	 * @param sid - Station ID
	 * @param msg - Messages for the manager
	 * @param nid  - Network ID
	 */
	public LowInventoryActions(MyFuelClient client, int sid,ArrayList<MessageForManager>msg, int nid,LoginRequest lr) {
		
		super(client,lr);
		this.msg = msg;
		this.sid = sid;
		this.nid = nid;
		
		LowInventoryRequest r = new LowInventoryRequest(sid,null);
		gui = new LowInventoryGUI(this);
		gui.createWaitDialog("Getting Details...");
		client.handleMessageFromGUI(r);
		
		
		gui.setVisible(true);
		NewLowInventory=new  ArrayList<Float>();
	}

	/***
	 * This method will check if the details that the user set are legal
	 * @param LowFuel95 - New Low inventory level for 95 
	 * @param LowFuelDiesel - New Low inventory level for diesel
	 * @param LowFuelScooter - New Low inventory level for Scooter 
	 */
	public void verifyDetails(String LowFuel95,String LowFuelDiesel,String LowFuelScooter)
	{
		float nLowFuel95=0,nLowFuelDiesel=0,nLowFuelScooter=0;
		boolean success = true;
		String error="";
		error += "Input Errors \n\n";
		if(LowFuel95.equals("")||LowFuelDiesel.equals("")||LowFuelScooter.equals(""))
		{
			success=false;
			error+="One of the Fields is Empty\n";
		}
		else 
		{
			try{
			nLowFuel95=Float.parseFloat(LowFuel95);
			nLowFuelDiesel=Float.parseFloat(LowFuelDiesel);
			nLowFuelScooter=Float.parseFloat(LowFuelScooter);
			} catch(Exception e)
			{
				success = false;
				error+="Input must be a number\n";
			}
			
			if(nLowFuel95 < 0||nLowFuelDiesel<0||nLowFuelScooter<0)
			{
				success=false;
				error+="Input can't be  Negative\n";
			}
		}
		if(!success) gui.showErrorMessage(error);
		else
		{
			NewLowInventory.add(nLowFuel95);
			NewLowInventory.add(nLowFuelDiesel);
			NewLowInventory.add(nLowFuelScooter);
			
			request=new LowInventoryRequest(this.sid,NewLowInventory);
			gui.createWaitDialog("Updating ...");
			client.handleMessageFromGUI(request);
		}
	
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof booleanResponse)
		{
			gui.setWaitProgress();
			booleanResponse resp = (booleanResponse)arg1;	
			if(!resp.getSuccess())
				gui.showErrorMessage(resp.getMsg());
			else 
			{
				gui.updateNew(NewLowInventory);
				gui.showOKMessage(resp.getMsg());
			}
		}
		
		else if(arg1 instanceof LowInventoryResponse)
		{
			gui.setWaitProgress();
			LowInventoryResponse r = (LowInventoryResponse)arg1;
			gui.setText(r.getQty());
			
		}
		
	}

	@Override
	public void backToMenu() {

		changeFrame(gui,this);
		new SMActions(client,this.sid,msg,nid,lr);
		

	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
	}

}
