package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.MyFuelClient;
import myfuel.gui.LowInventoryGUI;
import myfuel.request.LowInventoryRequest;
import myfuel.response.booleanResponse;

public class LowInventoryActions extends GUIActions {

	int sid;
	private LowInventoryGUI gui ; 
	private LowInventoryRequest request;
	private ArrayList<Integer> NewLowInventory ;
	
	public LowInventoryActions(MyFuelClient client, int sid) {
		
		super(client);
		this.sid = sid;
		gui = new LowInventoryGUI(this);
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
			error+="The filed is Empty\n";
		}
		else
		{
			nLowFuel95=Integer.parseInt(LowFuel95);
			nLowFuelDiesel=Integer.parseInt(LowFuelDiesel);
			nLowFuelScooter=Integer.parseInt(LowFuelScooter);
			
			if(nLowFuel95<0||nLowFuelDiesel<0||nLowFuelScooter<0)
			{
				success=false;
				error+="The input is Negative\n";
			}
		}
		if(!success) gui.showErrorMessage(error);
		else
		{
			NewLowInventory.add(nLowFuel95);
			NewLowInventory.add(nLowFuelDiesel);
			NewLowInventory.add(nLowFuelScooter);
			request=new LowInventoryRequest(this.sid,NewLowInventory);
			client.handleMessageFromGUI(request);
		}
	
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof booleanResponse)
		{
			booleanResponse resp = (booleanResponse)arg1;	
			if(!resp.getSuccess())
			gui.showErrorMessage(resp.getMsg());
			else gui.showOKMessage(resp.getMsg());
		}
	}

	@Override
	public void backToMenu() {
		changeFrame(gui,new SMActions(client,this.sid),this);

	}

}
