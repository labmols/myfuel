package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.LowInventoryGUI;

public class LowInventoryActions extends GUIActions {

	private int sid;
	private LowInventoryGUI gui ; 
	
	public LowInventoryActions(MyFuelClient client, int sid) {
		
		super(client);
		this.sid = sid;
		gui = new LowInventoryGUI(this);
		gui.setVisible(true);
	}

	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	@Override
	public void backToMenu() {
		

	}

}
