package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.SWGUI;
import myfuel.request.SWRequest;
import myfuel.response.booleanResponse;

public class SWActions extends GUIActions {
	private int sid;
	private SWGUI gui;
	
	
	public SWActions(MyFuelClient client,int sid) {
		super(client);
		this.sid = sid;
		gui = new SWGUI(this);
		gui.setVisible(true);
		
	}

	
	public void verifyDetails(String f95,String diesel,String scooter)
	{
		if(f95.equals("") || diesel.equals("") || scooter.equals(""))
			gui.showMessage("Some of the fields are empty");
		else if(!isAllDigits(f95) || !isAllDigits(diesel) || !isAllDigits(scooter) )
			gui.showMessage("Quantity must be a number!");
		else
		{
			int[] q = new int[3];
			q[0] = Integer.parseInt(f95);
			q[1] = Integer.parseInt(diesel);
			q[2] = Integer.parseInt(scooter);
			
			SWRequest request = new SWRequest(sid,q);
			client.handleMessageFromGUI(request);
		}
	}
	
	private boolean isAllDigits(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	            return false;
	        }
	    } 
	    return true;
	    }
	@Override
	public void update(Observable o, Object arg) {
		
		if(arg instanceof booleanResponse)
		{
			gui.showMessage(((booleanResponse)arg).getMsg());
		}

	}

	@Override
	public void backToMenu() {
		

	}

}
