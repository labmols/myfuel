package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.MyFuelClient;
import myfuel.client.Station;
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
	private ArrayList <Fuel> fuels;
	
	public CarFuelActions(MyFuelClient client,UserLoginResponse res) {
		super(client);
		gui = new CarFuelGUI(this);
		this.res = res;
		sInventory = null;
		fuels = null;
		getInventoryRequest();
		showStations();
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
	
	private void showStations()
	{
		ArrayList<Station> stations = res.getStations();
		for(Station s: stations)
		{
			gui.addStation(s.getName());
		}
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof CarFuelResponse)
		{
			CarFuelResponse res = (CarFuelResponse) arg;
			sInventory = new ArrayList <StationInventory>(res.getSi());
			fuels = new ArrayList<Fuel>(res.getFuels());
		}
	}



	@Override
	public void backToMenu() {
		changeFrame(gui,new CustomerOptionsActions(client,res),this);
		
	}


	public String getFuelPrice(int fuelSelected) {
		if(fuels == null) return "wait...";
		String price = "" + fuels.get(fuelSelected-1).getCurrPrice();
		return price;
		
	}

}
