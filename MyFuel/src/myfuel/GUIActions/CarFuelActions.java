package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.gui.CarFuelGUI;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;

public class CarFuelActions extends GUIActions {
	CarFuelGUI gui;
	UserLoginResponse res;
	
	public CarFuelActions(MyFuelClient client,UserLoginResponse res) {
		super(client);
		gui = new CarFuelGUI(this);
		this.res = res;
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void backToMenu() {
	//	changeFrame(gui,new UserOptionsActions(client,customer),this);
		
	}

}
