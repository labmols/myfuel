package myfuel.client;

import java.util.Observable;

import myfuel.gui.MDGUI;

public class MDActions extends GUIActions{
	
	public MDGUI gui;
	public MDActions(MyFuelClient client) {
		super(client);
		gui = new MDGUI(this);
		gui.setVisible(true);
		
		// TODO Auto-generated constructor stub
	}
	


	public void createPromotionTemplate()
	{
		changeFrame(gui,new CPromotionTemplateActions(client));
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
