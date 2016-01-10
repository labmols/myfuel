package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.FuelQty;
import myfuel.client.MessageForManager;
import myfuel.client.MyFuelClient;
import myfuel.gui.CMGUI;

public class CMActions extends GUIActions {
	private CMGUI gui ;
	private ArrayList<MessageForManager> msg;
	public CMActions(MyFuelClient client,ArrayList<MessageForManager> msg) {
		super(client);
		this.msg = msg;
		gui = new CMGUI(this,msg);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	public void ConfirmNewRatesWindow() 
	{
		changeFrame(gui,new ConfirmNewRatesActions(client,msg),this);	
	}

	@Override
	public void backToMenu() {
		
		
	}

	public void homeQuantity() 
	{
		changeFrame(gui,new homeQtyOrderActions(client,msg),this);	
		
	}

	public void showReportsWindows() 
	{
		changeFrame(gui,new showReportsActions(client,msg),this);	
		
	}

}
