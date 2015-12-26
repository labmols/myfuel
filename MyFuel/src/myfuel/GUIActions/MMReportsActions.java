package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MMReportGUI;
import myfuel.request.MMRerportsRequest;
import myfuel.response.MMReportsResponse;

public class MMReportsActions extends GUIActions {
	private MMReportGUI gui ; 
	/***
	 * Constructor for MMReportGUI class
	 * @param client - MyFuelClient
	 */
	public MMReportsActions(MyFuelClient client)
	{
		super(client);
		gui = new MMReportGUI(this);
		MMRerportsRequest request = new MMRerportsRequest();
		client.handleMessageFromGUI(request);
		
		
		gui.setVisible(true);
		
	}

	
	/***
	 * Receive a response from the Server 
	 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof MMReportsResponse)
		{
			
			gui.setReports(((MMReportsResponse)arg1).getPr(),((MMReportsResponse)arg1).getNames());
		}

	}
/***
 * Return to previous screen
 */
	@Override
	public void backToMenu() 
	{
		changeFrame(gui,new MMActions(client),this);

	}

}
