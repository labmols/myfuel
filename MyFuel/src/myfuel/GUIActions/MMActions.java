package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MMGUI;
import myfuel.request.LoginRequest;

public class MMActions extends GUIActions{

	private MMGUI gui ;
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
		this.changeFrame(gui, this);
		new SetNewRatesActions(client);
	}
	
	public void createMakeaPromotionWindow()
	{
		this.changeFrame(gui, this);
		new MakeAPromotionActions(client);
	}
	
	public void createShowReportsWindow()
	{
		this.changeFrame(gui,this);
		 new MMReportsActions(client);
	}

	@Override
	public void backToMenu() {
		this.changeFrame(gui,this);
		new LoginActions(client);
		
	}

	public void createaDetailsWindow() 
	{
		changeFrame(gui, this);
		new MMAnalyzedActions(client) ;
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		
	}

}
