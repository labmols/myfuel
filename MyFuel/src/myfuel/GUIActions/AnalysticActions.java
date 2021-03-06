package myfuel.GUIActions;

import java.util.Date;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.GUI.AnalysticGUI;
import myfuel.Request.AnalysticRequest;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.AnalysticResponse;
import myfuel.Response.booleanResponse;

/***
 * Controller for the AnalysticGUI
 * @author karmo
 *
 */
public class AnalysticActions extends GUIActions {

	/***
	 * The GUI that this class use to control
	 */
	private AnalysticGUI gui;

	/***
	 * AnalysticActions Constructor
	 * @param client - MyFuelClient
	 */
	public AnalysticActions(MyFuelClient client,LoginRequest lr) 
	{
		super(client,lr);
	
		AnalysticRequest  request = new AnalysticRequest(RequestEnum.Select,new Date());
		gui = new AnalysticGUI(this);
		gui.createWaitDialog("Getting Analysis ...");
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if( arg1 instanceof AnalysticResponse)
		{
			AnalysticResponse r = (AnalysticResponse)arg1;
			
			
			 gui.updateCustomerPanel(r.getcType());
			 gui.updateFuelsPanel(r.getfType());
			 gui.updateHoursPanel(r.gethType());
			 gui.setWaitProgress();
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.setWaitProgress();
			booleanResponse r = (booleanResponse)arg1;
			if(!r.getSuccess())
				gui.showErrorMessage(r.getMsg());
			else
				gui.showOKMessage(r.getMsg());
		}
		

	}

	@Override
	public void backToMenu()
	{
		
		changeFrame(gui,this);
		new MDActions(client,lr);
		 
	}
	
	/***
	 * This method will Save the Details in the DB
	 */
	public void saveToDB() 
	{
		gui.createWaitDialog("Saving Analysis ...");
		AnalysticRequest  request = new AnalysticRequest(RequestEnum.Insert,new Date());
		client.handleMessageFromGUI(request);
		
	}

	@Override
	public void LogOut() {
		this.LogOutRequest(gui, lr);
		
	}

}
