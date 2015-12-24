package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.client.saleModel;
import myfuel.gui.ConfirmNewRatesGUI;
import myfuel.request.ConfirmNewRatesRequest;
import myfuel.request.RequestEnum;
import myfuel.response.ConfirmNewRatesResponse;
import myfuel.response.booleanResponse;

public class ConfirmNewRatesActions extends GUIActions {
	private ConfirmNewRatesGUI gui ; 
	/***
	 *  Controller for ConfirmNewRatesGUI
	 * @param client - MyFuelClient
	 */
	public ConfirmNewRatesActions(MyFuelClient client) {
		super(client);
		ConfirmNewRatesRequest request = new ConfirmNewRatesRequest(RequestEnum.Select);
		client.handleMessageFromGUI(request);
		
		gui = new ConfirmNewRatesGUI(this);
		gui.setVisible(true);
		
	}
/***
 * Get a message from the server and handling it duw to its type
 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof ConfirmNewRatesResponse)
		{
			ArrayList<saleModel> s = (((ConfirmNewRatesResponse)arg1).getsModes());
			ArrayList<saleModel> c = (((ConfirmNewRatesResponse)arg1).getCurrent());
			
			gui.setDetails(s,c);
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.showMessage(((booleanResponse)arg1).getMsg());
			if(((booleanResponse)arg1).getSuccess())
			{
				gui.clearTable();
				
			}
			backToMenu() ;
		}

	}
/***
 * Return to Company manager menu
 */
	@Override
	public void backToMenu() 
	{
		changeFrame(gui,new CMActions(client),this);
	}
/***
 *  Sending approved rates(if there is) to the DB
 * @param approved - all approved Rates
 */
	public void sendNewRates(ArrayList<saleModel> approved)
	{
		if(approved.isEmpty())
		{
			client.handleMessageFromGUI(new ConfirmNewRatesRequest(RequestEnum.Delete));
		}
		else 
			client.handleMessageFromGUI(new ConfirmNewRatesRequest(RequestEnum.Insert,approved) );
		
	}

}
