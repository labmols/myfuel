package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.CheckInventoryGUI;

public class CheckInventoryActions extends GUIActions{

	private CheckInventoryGUI gui ;
	private int sid;
	
	public CheckInventoryActions(MyFuelClient client,int sid) {
		super(client);
		this.sid = sid;
		
		 gui = new CheckInventoryGUI(this) ;
		 gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}

	@Override
	public void backToMenu() {
		this.changeFrame(gui,new SMActions(client,sid),this);
	}

}
