package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.SMGUI;

public class SMActions extends GUIActions {
	private int sid;
	private SMGUI gui;
	
	public SMActions(MyFuelClient client,int sid) {
		super(client);
		this.setSid(sid);
		gui = new SMGUI(this);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	@Override
	public void backToMenu() {
		

	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

}
