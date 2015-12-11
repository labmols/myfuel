package myfuel.client;

import java.util.Observable;

import myfuel.gui.MDGUI;

public class MDActions extends GUIActions{
	
	MDGUI gui;
	public MDActions(MyFuelClient client) {
		super(client);
		gui = new MDGUI(this);
		gui.setVisible(true);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
