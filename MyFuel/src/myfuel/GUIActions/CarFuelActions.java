package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.MyFuelClient;
import myfuel.client.Station;
import myfuel.client.StationInventory;
import myfuel.gui.CarFuelGUI;
import myfuel.request.FuelOrderRequest;
import myfuel.request.RequestEnum;
import myfuel.response.FuelOrderResponse;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;

public class CarFuelActions extends GUIActions {
	private CarFuelGUI gui;
	private CustomerLoginResponse customerRes;
	private FuelOrderResponse infoRes;
	
	public CarFuelActions(MyFuelClient client,CustomerLoginResponse res) {
		super(client);
		this.customerRes = res;
		infoRes = null;
		gui = new CarFuelGUI(this);
		insertInfo();
		gui.setVisible(true);
		getInfoRequest();
		
		// TODO Auto-generated constructor stub
	}


    /**
     * 
     */
	private void getInfoRequest() {
		// TODO Auto-generated method stub
		gui.createWaitDialog();
		FuelOrderRequest req = new FuelOrderRequest (RequestEnum.Select,1,-1);
		client.handleMessageFromGUI(req);
	}
	
	private void insertInfo()
	{
		ArrayList<Station> stations = customerRes.getStations();
		ArrayList<Car> cars = customerRes.getUser().getCars();
		
		for(Station s: stations)
			gui.addStation(s.getName());
		for(Car c: cars)
			gui.addCar(c.getcid());
		
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof FuelOrderResponse)
		{
			FuelOrderResponse res = (FuelOrderResponse) arg;
			this.infoRes = res;
			gui.setPrices(infoRes.getFuels());
			gui.setWaitPorgress();
		}
	}



	@Override
	public void backToMenu() {
		changeFrame(gui,new CustomerOptionsActions(client,customerRes),this);
		
	}


	public ArrayList <Fuel> getFuels() {
		return this.infoRes.getFuels();
	}

}
