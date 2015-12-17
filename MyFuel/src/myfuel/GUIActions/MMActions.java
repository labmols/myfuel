package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MMGUI;

public class MMActions extends GUIActions{

	MMGUI gui ;
	public MMActions(MyFuelClient client) {
		super(client);
		gui = new MMGUI(this);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	
	public void createSetNewRatesWindow()
	{
		this.changeFrame(gui, new SetNewRatesActions(client),this);
	}
	
	public void createMakeaPromotionWindow()
	{
		this.changeFrame(gui, new MakeAPromotionActions(client),this);
	}
	
	public void createShowReportsWindow()
	{
		
	}

}
