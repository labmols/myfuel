package myfuel.GUIActions;


import java.awt.Frame;
import java.util.Observer;

import myfuel.client.MyFuelClient;
import myfuel.gui.SuperGUI;
import myfuel.response.Response;


/**
 * GUI Controller class
 *
 */
public abstract class GUIActions implements Observer {
	
	/**
	 * the client object
	 */
	protected MyFuelClient client;
	
	/**
	 * create new GUI Controller
	 * @param client - the client object
	 */
	public GUIActions(MyFuelClient client)
	{
		client.addObserver(this);
		this.client = client;
	}
	
	/**
	 * get client object 
	 * @return client object
	 */
	public MyFuelClient getClient()
	{
		return client;
	}
	
	/**
	 * replace the current JFrame with the next Frame.
	 * @param g - current JFrame 
	 * @param actions - next JFrame Controller
	 * @param currentActions - Current GUI Controller 
	 */
	public void changeFrame(SuperGUI g,GUIActions currentActions){
		g.setVisible(false);
		g.dispose();
		client.deleteObserver(currentActions);
	}
	
	/**
	 * Abstract method that replace the current JFrame with the Main Menu JFrame.
	 */
	public abstract void backToMenu(); 
	

	
}
