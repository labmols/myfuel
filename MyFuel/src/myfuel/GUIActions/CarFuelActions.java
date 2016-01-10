package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.client.CalcPrice;
import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.MyFuelClient;
import myfuel.client.Promotion;
import myfuel.client.Purchase;
import myfuel.client.Station;
import myfuel.client.StationInventory;
import myfuel.gui.CarFuelGUI;
import myfuel.request.FuelOrderRequest;
import myfuel.request.RequestEnum;
import myfuel.response.FuelOrderResponse;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.booleanResponse;

public class CarFuelActions extends GUIActions {
	private CarFuelGUI gui;
	private CustomerLoginResponse customerRes;
	private FuelOrderResponse infoRes;
	private Purchase fuelPurchase;
	
	public CarFuelActions(MyFuelClient client,CustomerLoginResponse res) {
		super(client);
		this.customerRes = res;
		infoRes = null;
		fuelPurchase = null;
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
		gui.createWaitDialog("Getting Details...");
		FuelOrderRequest req = new FuelOrderRequest (RequestEnum.Select,1,-1);
		client.handleMessageFromGUI(req);
	}
	
	private void insertInfo()
	{
		ArrayList<Station> stations = customerRes.getStations();
		ArrayList<Car> cars = customerRes.getUser().getCars();
		gui.checkType(customerRes.getUser().getToc());
		for(Station s: stations)
			gui.addStation(s.getName());
		for(Car c: cars)
			gui.addCar(c.getcid());
		
	}
	
	public void createPurchase()
	{
		try{
			FuelOrderRequest req = new FuelOrderRequest(RequestEnum.Insert,fuelPurchase,null);
			client.handleMessageFromGUI(req);
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			gui.showErrorMessage("Error!");
		}
	
	}
	
	public boolean verifyDetails(String amount, int fuelSelected, String dName ,String stationSelected,int cid)
	{
		float amountF=-1;
		String errors="";
		boolean check= true;
		boolean found = false;
		int stationID= -1;
		Customer customer = customerRes.getUser();
		ArrayList<Car> customerCars = customerRes.getUser().getCars();
		ArrayList<Integer> customerStations = customerRes.getUser().getStations();
		ArrayList<Station> stations = customerRes.getStations();

		try
		{
			amountF = Float.parseFloat(amount);
			if(amountF<0)
			{
				errors += "illegal amount value.\n";
				check = false;
			}
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			errors += "illegal amount value.\n";
			check = false;
		}
		
		
		for(Car c: customerCars)
		{
			if(c.getcid() == cid)
			{
				if(c.getfid() != fuelSelected)
				{
					errors += "This fuel pump doesn't match for your car.\n";
					check = false;
				}
				break;
			}
				
		}
		
		for(Station s: stations)
		{
			if(s.getName().equals(stationSelected))
			{
				for(Integer sid : customerStations)
				{
					stationID= sid;
					if(sid == s.getsid())
						found = true;
				}
				if(!found)
				{
				errors += "You don't have permission to fuel in this station.\n";
				check = false;
				}
				break;
			}
		}
		
		if(customer.getToc()==1 && (dName.equals("") || !isAlpha(dName)))
		{
					errors+= "illegal Driver name value.\n";
					check = false;
		}
		
		if(!check)
			gui.showErrorMessage(errors);
		else
		{
			Promotion prom = infoRes.getPromotion(fuelSelected);
			float totalPrice = CalcPrice.calcCarFuelOrder(customer.getSmodel(),infoRes.getRates(), amountF, infoRes.getFuels().get(fuelSelected-1).getMaxPrice(), infoRes.getPromotion(fuelSelected));
			fuelPurchase  = new Purchase(customer.getUserid(),0,stationID, fuelSelected, prom.getPid(),new Date(),totalPrice,amountF,dName);
		}
		return check;
		
		
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof FuelOrderResponse)
		{
			FuelOrderResponse res = (FuelOrderResponse) arg;
			this.infoRes = res;
			int modelid = customerRes.getUser().getSmodel();
			gui.setInfo(infoRes.getRates().get(modelid-1).getDiscount(),infoRes.getFuels());
			gui.setWaitPorgress();
		}
		else if(arg instanceof booleanResponse)
		{
			 booleanResponse res = (booleanResponse) arg;
			 if(res.getSuccess())
			 {
				 gui.showOKMessage("Thank you for choosing MyFuel!");
				 this.backToMenu();
			 }
			 else
			 {
				 gui.showErrorMessage("Error!");
			 }
		}
	}



	@Override
	public void backToMenu() {
		changeFrame(gui,new CustomerOptionsActions(client,customerRes),this);
		
	}


	public ArrayList <Fuel> getFuels() {
		return this.infoRes.getFuels();
	}


	public float getPrice(int fuelID) {
		Customer c = customerRes.getUser();
		return CalcPrice.calcCarFuelOrder(c.getSmodel(),infoRes.getRates(), 1, infoRes.getFuels().get(fuelID-1).getMaxPrice(), infoRes.getPromotion(fuelID));
		// TODO Auto-generated method stub
		
	}
	
	public Promotion getPromotion(int fuelID) {
		return infoRes.getPromotion(fuelID);
		// TODO Auto-generated method stub
		
	}
	
	private boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}
	
	public String getCustomerCC()
	{
		return customerRes.getUser().getCnumber();
	}


	

}
