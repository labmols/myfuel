package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.CMGUI;

public class CMActions extends GUIActions {
	CMGUI gui ;
	public CMActions(MyFuelClient client) {
		super(client);
		gui = new CMGUI(this);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	public void ConfirmNewRatesWindow() 
	{
		changeFrame(gui,new ConfirmNewRatesActions(client),this);	
	}

	@Override
	public void backToMenu() {
		
		
	}

}
