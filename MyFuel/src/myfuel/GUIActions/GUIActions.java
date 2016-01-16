package myfuel.GUIActions;


import java.awt.Frame;
import java.util.Observer;

import myfuel.client.MyFuelClient;
import myfuel.gui.SuperGUI;
import myfuel.request.LoginRequest;
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
	/***
	 * Details of the user
	 */
	protected LoginRequest lr;
	/**
	 * create new GUI Controller
	 * @param client - the client object
	 */
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
		
	

	
}
