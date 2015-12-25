package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MMReportGUI;

public class MMReportsActions extends GUIActions {
	private MMReportGUI gui ; 
	
	public MMReportsActions(MyFuelClient client)
	{
		super(client);
		gui = new MMReportGUI(this);
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		

	}

	@Override
	public void backToMenu() 
	{
		changeFrame(gui,new MMActions(client),this);

	}

}
