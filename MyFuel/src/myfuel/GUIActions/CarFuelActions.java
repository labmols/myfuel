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
import myfuel.request.FuelInfoRequest;
import myfuel.request.RequestEnum;
import myfuel.response.FuelInfoResponse;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;

public class CarFuelActions extends GUIActions {
	private CarFuelGUI gui;
	private CustomerLoginResponse res;
	private ArrayList <StationInventory> sInventory;
	private ArrayList <Fuel> fuels;
	
	public CarFuelActions(MyFuelClient client,CustomerLoginResponse res) {
		super(client);
		this.res = res;
		sInventory = null;
		fuels = null;
		gui = new CarFuelGUI(this);
		insertInfo();
		getInventoryRequest();
		gui.setVisible(true);
		
		// TODO Auto-generated constructor stub
	}


    /**
     * 
     */
	private void getInventoryRequest() {
		// TODO Auto-generated method stub
		FuelInfoRequest req = new FuelInfoRequest (RequestEnum.Select,1);
		client.handleMessageFromGUI(req);
	}
	
	private void insertInfo()
	{
		ArrayList<Station> stations = res.getStations();
		ArrayList<Car> cars = res.getUser().getCars();
		
		for(Station s: stations)
			gui.addStation(s.getName());
		for(Car c: cars)
			gui.addCar(c.getcid());
		
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof FuelInfoResponse)
		{
			FuelInfoResponse res = (FuelInfoResponse) arg;
			sInventory = new ArrayList <StationInventory>(res.getSi());
			fuels = new ArrayList<Fuel>(res.getFuels());
			gui.setPrices(fuels);
		}
	}



	@Override
	public void backToMenu() {
		changeFrame(gui,new CustomerOptionsActions(client,res),this);
		
	}


	public ArrayList <Fuel> getFuels() {
		return this.fuels;
	}

}
