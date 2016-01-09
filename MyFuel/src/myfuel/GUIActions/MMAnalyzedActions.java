package myfuel.GUIActions;

import java.util.Date;
import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MMAnalyzedGUI;
import myfuel.request.AnalysticRequest;
import myfuel.request.RequestEnum;
import myfuel.response.AnalysticResponse;
import myfuel.server.AnalysticDBHandler;
/***
 * This class is a controller for MMAnalyzedGUI
 * @author karmo
 *
 */
public class MMAnalyzedActions extends GUIActions {

	/***
	 * GUI that this class controls
	 */
	private MMAnalyzedGUI gui;
	/***
	 * MMAnalyzedActions Constructor
	 * @param client - MyFuelClient
	 */
	public MMAnalyzedActions(MyFuelClient client) {
		super(client);
		AnalysticRequest request = new AnalysticRequest(RequestEnum.Select,null);
		client.handleMessageFromGUI(request);
		gui = new MMAnalyzedGUI(this);
		gui.createWaitDialog("Getting Analysis Dates...");
		gui.setVisible(true);
		
	}
	
	

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if(arg1 instanceof AnalysticResponse)
		{
			AnalysticResponse r = (AnalysticResponse)arg1;
			if(r.getType() == 0)
				gui.updateCombobox(r.getDates());
			else if(r.getType() == 1)
			{
				gui.updateCustomerPanel(r.getcType());
				 gui.updateFuelsPanel(r.getfType());
				 gui.updateHoursPanel(r.gethType());
			}
			
			gui.setWaitPorgress();
		}

	}

	@Override
	public void backToMenu()
	{
		changeFrame(gui,new MMActions(client),this);

	}



	/***
	 * This method will request all analyzed details from the DB
	 * @param date - picked date
	 */
	public void getDetails(Date date) 
	{
		AnalysticRequest request = new AnalysticRequest(RequestEnum.Select,date);
		gui.createWaitDialog("Getting Analysis Data for"+" "+date);
		client.handleMessageFromGUI(request);
		
	}

}
