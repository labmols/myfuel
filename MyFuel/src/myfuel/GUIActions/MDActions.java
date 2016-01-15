package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MDGUI;

/***
 * Controller for the MDGUI
 * @author karmo
 *
 */
public class MDActions extends GUIActions{
	
	/***
	 * This class will be used as a controller for this GUI object
	 */
	public MDGUI gui;
	/***
	 * MDActions Constructor
	 * @param client - MyFuelClient
	 */
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
		new CPromotionTemplateActions(client);
		changeFrame(gui,this);
		
	}
	@Override
	public void update(Observable o, Object arg) {
		
		
	}



	@Override
	public void backToMenu() {
		new LoginActions(client);
		changeFrame(gui,this);
		
		
	}

	/***
	 * Creates customer's confirmation  window for the marketing delegate
	 */

	public void createConfirmationWindow() 
	{
		new ConfirmationActions(client);
		changeFrame(gui,this);
		
		
	}

	/***
	 * Creates Analytic System  window for the marketing delegate
	 */
	public void createAnalystic() 
	{
		new AnalysticActions(client);
		changeFrame(gui,this);
		
	}

}
