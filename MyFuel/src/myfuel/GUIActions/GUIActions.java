package myfuel.GUIActions;


import java.util.Observer;

import myfuel.client.MyFuelClient;
import myfuel.gui.SuperGUI;
import myfuel.response.Response;



public abstract class GUIActions implements Observer {
	protected MyFuelClient client;
	
	public GUIActions(MyFuelClient client)
	{
		client.addObserver(this);
		this.client = client;
	}
	
	public MyFuelClient getClient()
	{
		return client;
	}
	
	public void changeFrame(SuperGUI g, GUIActions actions){
		g.setVisible(false);
		g.dispose();
	}
	

	
	
	
	
	
}
