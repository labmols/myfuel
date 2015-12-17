package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.ConfirmNewRatesGUI;
import myfuel.request.ConfirmNewRatesRequest;

public class ConfirmNewRatesActions extends GUIActions{

	private ConfirmNewRatesGUI gui ;
	
	public ConfirmNewRatesActions(MyFuelClient client) {
		super(client);
		gui = new ConfirmNewRatesGUI(this);
		ConfirmNewRatesRequest request = new ConfirmNewRatesRequest(0);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}

}
