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
		changeFrame(gui,new CPromotionTemplateActions(client),this);
	}
	@Override
	public void update(Observable o, Object arg) {
		
		
	}



	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}

	/***
	 * Creates customer's confirmation  window for the marketing delegate
	 */

	public void createConfirmationWindow() 
	{
		changeFrame(gui,new ConfirmationActions(client),this);
		
	}

}
