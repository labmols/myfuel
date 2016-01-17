package myfuel.GUIActions;

import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.GUI.MDGUI;
import myfuel.Request.LoginRequest;

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
	public MDActions(MyFuelClient client,LoginRequest lr) {
		super(client,lr);
		gui = new MDGUI(this);
		gui.setVisible(true);
		
		
	}
	

	/***
	 * Creates Promotion template window for the marketing delegate
	 */
	public void createPromotionTemplate()
	{
		new CPromotionTemplateActions(client,lr);
		changeFrame(gui,this);
		
	}
	@Override
	public void update(Observable o, Object arg) {
		
		
	}



	@Override
	public void backToMenu() {
		
		
		
	}

	/***
	 * Creates customer's confirmation  window for the marketing delegate
	 */

	public void createConfirmationWindow() 
	{
		new ConfirmationActions(client,lr);
		changeFrame(gui,this);
		
		
	}

	/***
	 * Creates Analytic System  window for the marketing delegate
	 */
	public void createAnalystic() 
	{

		changeFrame(gui,this);
		new AnalysticActions(client,lr);
		
	}


	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
	}

}
