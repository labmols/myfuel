package myfuel.GUIActions;


import java.awt.Frame;
import java.util.Observable;
import java.util.Observer;

import myfuel.Client.MyFuelClient;
import myfuel.GUI.SuperGUI;
import myfuel.Request.LoginRequest;
import myfuel.Response.Response;


/**
 * GUI Controller class
 *
 */
public abstract class GUIActions implements Observer {
	
	/**
	 * the client object
	 */
	protected MyFuelClient client;
	/***
	 * Details of the user
	 */
	protected LoginRequest lr;
	/**
	 * create new GUI Controller
	 * @param client - the client object
	 */
	
	public GUIActions()
	{
		
	}
	public GUIActions(MyFuelClient client,LoginRequest lr)
	{
		client.addObserver(this);
		this.lr = lr;
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
		client.deleteObserver(currentActions);
		g.setVisible(false);
		g.dispose();
	}
	
	/**
	 * Abstract method that replace the current JFrame with the Main Menu JFrame.
	 */
	public abstract void backToMenu(); 
	
	/**
	 *Change status of user to "Not Connected" by sending request to server.
	 * @param g
	 * @param req
	 */
	public void LogOutRequest(SuperGUI g, LoginRequest req)
	{
		req.setChangeStatus(1);
		client.handleMessageFromGUI(req);
		changeFrame(g,this);
		new LoginActions(client);
	}
	
	/**
	 * Log out from the system.
	 */
	public abstract void LogOut() ;
		// TODO Auto-generated method stub
	
	/**
	 * This method notified by the client when received new response from server and 
	 * act according to the GUI controller implement.
	 */
	public abstract void update(Observable o, Object arg);

	
}
