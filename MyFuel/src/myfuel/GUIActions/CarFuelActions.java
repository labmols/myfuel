package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.StationInventory;
import myfuel.gui.CarFuelGUI;
import myfuel.request.CarFuelRequest;
import myfuel.request.RequestEnum;
import myfuel.response.CarFuelResponse;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;

public class CarFuelActions extends GUIActions {
	private CarFuelGUI gui;
	private UserLoginResponse res;
	private ArrayList <StationInventory> sInventory;
	public CarFuelActions(MyFuelClient client,UserLoginResponse res) {
		super(client);
		gui = new CarFuelGUI(this);
		this.res = res;
		sInventory = null;
		getInventoryRequest();
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}


    /**
     * 
     */
	private void getInventoryRequest() {
		// TODO Auto-generated method stub
		CarFuelRequest req = new CarFuelRequest (RequestEnum.Select);
		client.handleMessageFromGUI(req);
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof CarFuelResponse)
		{
			CarFuelResponse res = (CarFuelResponse) arg;
			sInventory = new ArrayList <StationInventory>(res.getSi());
		}
	}



	@Override
	public void backToMenu() {
		changeFrame(gui,new UserOptionsActions(client,res),this);
		
	}

}
