package myfuel.GUIActions;

import java.util.Date;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.DBHandler.AnalysticDBHandler;
import myfuel.GUI.MMAnalyzedGUI;
import myfuel.Request.AnalysticRequest;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.AnalysticResponse;
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
	public MMAnalyzedActions(MyFuelClient client,LoginRequest lr) {
		super(client , lr);
		AnalysticRequest request = new AnalysticRequest(RequestEnum.Select,null);
		gui = new MMAnalyzedGUI(this);
		gui.createWaitDialog("Getting Analysis Dates...");
		client.handleMessageFromGUI(request);
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
			
			gui.setWaitProgress();
		}

	}

	@Override
	public void backToMenu()
	{
		changeFrame(gui,this);
		new MMActions(client, lr);

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



	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
