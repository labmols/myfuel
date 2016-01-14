package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MDGUI;

public class MDActions extends GUIActions{
	
	public MDGUI gui;
	public MDActions(MyFuelClient client) {
		super(client);
		gui = new MDGUI(this);
		gui.setVisible(true);
		
		
	}
	

	/***
	 * Creates Promotion template window for the marketing delegate
	 */
	public void createPromotionTemplate()
	{
		changeFrame(gui,this);
		new CPromotionTemplateActions(client);
	}
	@Override
	public void update(Observable o, Object arg) {
		
		
	}



	@Override
	public void backToMenu() {
		changeFrame(gui,this);
		new LoginActions(client);
		
	}

	/***
	 * Creates customer's confirmation  window for the marketing delegate
	 */

	public void createConfirmationWindow() 
	{
		changeFrame(gui,this);
		new ConfirmationActions(client);
		
	}


	public void createAnalystic() 
	{
		changeFrame(gui,this);
		new AnalysticActions(client);
		
	}

}
