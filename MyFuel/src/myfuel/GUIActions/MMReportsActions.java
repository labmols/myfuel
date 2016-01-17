package myfuel.GUIActions;

import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.GUI.MMReportGUI;
import myfuel.Request.LoginRequest;
import myfuel.Request.MMRerportsRequest;
import myfuel.Response.MMReportsResponse;
import myfuel.Response.booleanResponse;
/***
 * This class will has methods that will assist to the GUI functioning 
 *
 */
public class MMReportsActions extends GUIActions {
	/***
	 * The GUI that this controller will control
	 */
	private MMReportGUI gui ; 
	/***
	 * Constructor for MMReportGUI class
	 * @param client - MyFuelClient
	 */
	public MMReportsActions(MyFuelClient client,LoginRequest lr)
	{
		super(client,lr);
		gui = new MMReportGUI(this);
		MMRerportsRequest request = new MMRerportsRequest();
		gui.createWaitDialog("Getting Reports Details...");
		client.handleMessageFromGUI(request);
		
		
		gui.setVisible(true);
		
	}

	
	/***
	 * Receive a response from the Server 
	 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof MMReportsResponse) // will return MMReportsResponse in case of success
		{
			gui.setWaitProgress();
			gui.setReports(((MMReportsResponse)arg1).getPr(),((MMReportsResponse)arg1).getNames(),((MMReportsResponse)arg1).getStations(),
					((MMReportsResponse)arg1).getCreport());
		}
		
		else if(arg1 instanceof booleanResponse)  // will return boolean response only in case of an error
		{
			gui.setWaitProgress();
			gui.showErrorMessage(((booleanResponse)arg1).getMsg());
		}
		
		

	}
/***
 * Return to previous screen
 */
	@Override
	public void backToMenu() 
	{

		changeFrame(gui,this);
		new MMActions(client,lr);
		

	}


@Override
public void LogOut() {
	// TODO Auto-generated method stub
	this.LogOutRequest(gui, lr);
	
}

}
