package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.SetNewRatesGUI;

public class SetNewRatesActions extends GUIActions {
	SetNewRatesGUI gui;
	public SetNewRatesActions(MyFuelClient client) {
		super(client);
		
		gui = new SetNewRatesGUI(this);
		gui.setVisible(true);
		
	}

	
	
	@Override
	public void update(Observable arg0, Object arg1) {
	

	}

}
