package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.ConfirmNewRatesGUI;
import myfuel.request.ConfirmNewRatesRequest;
import myfuel.response.ConfirmRatesResponse;
import myfuel.response.booleanResponse;
import myfuel.server.ConfirmNewRatesDBHandler;

public class ConfirmNewRatesActions extends GUIActions{

	private ConfirmNewRatesGUI gui ;
	private ConfirmRatesResponse response;
	/***
	 * Create actions class for the ConfirmNewRatesGUI
	 * @param client - MyFuelClient
	 */
	public ConfirmNewRatesActions(MyFuelClient client) {
		super(client);
		gui = new ConfirmNewRatesGUI(this);
		ConfirmNewRatesRequest request = new ConfirmNewRatesRequest(1);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
		
	}
	/***
	 * if the company manager wants to confirm the new rates
	 */
	public void confirmRates()
	{
		ConfirmNewRatesRequest request = new ConfirmNewRatesRequest(2);
		client.handleMessageFromGUI(request);
	}
	
	/***
	 * if the company manager wants to deny the new rates
	 */
	public void denyRates()
	{
		ConfirmNewRatesRequest request = new ConfirmNewRatesRequest(3);
		client.handleMessageFromGUI(request);
	}
	
	/***
	 * handling the response from the server
	 */

	@Override
	public void update(Observable o, Object arg) {
		
		if(arg instanceof ConfirmRatesResponse)
		{
			response =(ConfirmRatesResponse)arg;
			gui.setLabels(response.getFuels());
		}
		
		else if(arg instanceof booleanResponse)
		{
			gui.showMessage(((booleanResponse)arg).getMsg());
			if(((booleanResponse)arg).getMsg().equals("There are no suggested rates"));
			{
				changeFrame(gui,new CMActions(client),this);
			}
		}
		
	}

}
