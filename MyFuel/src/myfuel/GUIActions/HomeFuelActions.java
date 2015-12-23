package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.gui.HomeFuelGUI;

public class HomeFuelActions extends GUIActions {
	
	HomeFuelGUI gui;
	Customer customer;
	public HomeFuelActions(MyFuelClient client, Customer customer) {
		super(client);
		this.gui = new HomeFuelGUI(this);
		this.customer= customer;
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}

}
