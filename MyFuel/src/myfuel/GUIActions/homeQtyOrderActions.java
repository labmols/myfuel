package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.homeQtyOrderGUI;

public class homeQtyOrderActions extends GUIActions {

	private homeQtyOrderGUI gui;
	
	public homeQtyOrderActions(MyFuelClient client) {
		super(client);
		gui = new homeQtyOrderGUI(this);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	@Override
	public void backToMenu() {
		changeFrame(gui,new CMActions(client),this);

	}

}
