package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MMGUI;

public class MMActions extends GUIActions{

	MMGUI gui ;
	public MMActions(MyFuelClient client) {
		super(client);
		gui = new MMGUI(this);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}

}
