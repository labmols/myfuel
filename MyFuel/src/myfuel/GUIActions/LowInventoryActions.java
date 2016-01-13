package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.MessageForManager;
import myfuel.client.MyFuelClient;
import myfuel.gui.LowInventoryGUI;
import myfuel.request.LowInventoryRequest;
import myfuel.response.booleanResponse;
import myfuel.server.LowInventoryResponse;

public class LowInventoryActions extends GUIActions {

	private int sid;
	private LowInventoryGUI gui ; 
	private LowInventoryRequest request;
	private ArrayList<Integer> NewLowInventory ;
	private ArrayList<MessageForManager> msg;
	private int nid ; 
	
	public LowInventoryActions(MyFuelClient client, int sid,ArrayList<MessageForManager>msg, int nid) {
		
		super(client);
		this.msg = msg;
		this.sid = sid;
		this.nid = nid;
		
		LowInventoryRequest r = new LowInventoryRequest(sid,null);
		gui = new LowInventoryGUI(this);
		gui.createWaitDialog("Getting Details...");
		client.handleMessageFromGUI(r);
		
		
		gui.setVisible(true);
		NewLowInventory=new  ArrayList<Integer>();
	}

	public void verifyDetails(String LowFuel95,String LowFuelDiesel,String LowFuelScooter)
	{
		int nLowFuel95=0,nLowFuelDiesel=0,nLowFuelScooter=0;
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
			nLowFuel95=Integer.parseInt(LowFuel95);
			nLowFuelDiesel=Integer.parseInt(LowFuelDiesel);
			nLowFuelScooter=Integer.parseInt(LowFuelScooter);
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
			gui.setWaitPorgress();
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
			gui.setWaitPorgress();
			LowInventoryResponse r = (LowInventoryResponse)arg1;
			gui.setText(r.getQty());
			
		}
		
	}

	@Override
	public void backToMenu() {
		changeFrame(gui,new SMActions(client,this.sid,msg,nid),this);

	}

}
