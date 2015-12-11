package myfuel.client;


import java.util.Observer;

import myfuel.gui.SuperGUI;
import myfuel.server.Response;



public abstract class GUIActions implements Observer {
	MyFuelClient client;
	
	public GUIActions(MyFuelClient client)
	{
		client.addObserver(this);
		this.client = client;
	}
	
	
	public void changeFrame(SuperGUI g, GUIActions actions){
		g.setVisible(false);
		g.dispose();
	}
	

	
	
	
	
	
}
