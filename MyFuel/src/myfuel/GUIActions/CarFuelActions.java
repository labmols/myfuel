package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.client.CalcPrice;
import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.FuelQty;
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
	protected CarFuelGUI gui;
	protected CustomerLoginResponse customerRes;
	protected FuelOrderResponse infoRes;
	protected Purchase fuelPurchase;
	
	public CarFuelActions(MyFuelClient client)
	{
		super(client);
	}
	
	public CarFuelActions(MyFuelClient client,CustomerLoginResponse res) {
		super(client);
		this.customerRes = res;
		infoRes = null;
		fuelPurchase = null;
		gui = new CarFuelGUI(this);
		gui.setVisible(true);
		getInfoRequest();
		
		// TODO Auto-generated constructor stub
	}


    /**
     * 
     */
	protected void getInfoRequest() {
		// TODO Auto-generated method stub
		gui.createWaitDialog("Getting Details...");
		FuelOrderRequest req = new FuelOrderRequest (RequestEnum.Select,1,-1);
		client.handleMessageFromGUI(req);
	}
	
	protected void insertInfo()
	{
		ArrayList<Station> stations = infoRes.getStations();
		ArrayList<Car> cars = customerRes.getUser().getCars();

		gui.checkType(customerRes.getUser().getToc());
		for(Station s: stations)
			gui.addStation(s);
		for(Car c: cars)
			gui.addCar(c.getcid());
		
	}
	
	public void createPurchase()
	{
		try{
			FuelOrderRequest req = new FuelOrderRequest(RequestEnum.Insert,fuelPurchase,null);
			gui.createWaitDialog("Create your purchase...");
			client.handleMessageFromGUI(req);
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			gui.showErrorMessage("Error!");
		}
	
	}
	
	public boolean verifyDetails(String amount, int fuelSelected, String dName ,String stationSelected,int cid,int nid)
	{
		float amountF=-1;
		String errors="";
		boolean check= true;
		boolean found = false;
		int stationID= -1;
		Customer customer = customerRes.getUser();
		ArrayList<Car> customerCars = customerRes.getUser().getCars();
		ArrayList<Integer> customerStations = customerRes.getUser().getStations();
		ArrayList<Station> stations = infoRes.getStations();

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
					stationID= s.getsid();
					if(sid == s.getNetwork().getNid())
						found = true;
				}
				
			}
		}
		
		if(!found)
		{
		errors += "You don't have permission to fuel in this station.\n";
		check = false;
		
		}
		
		if(customer.getToc()==1 && (dName.equals("") || !isAlpha(dName)))
		{
					errors+= "illegal Driver name value.\n";
					check = false;
		}
		
		if(!checkInventory(amountF, fuelSelected, stationID))
		{
			errors+= "Not enough fuel quantity.\n";
			check = false;
		}
		
		if(!check)
			gui.showErrorMessage(errors);
		else
		{
			int pid;
			Promotion prom = infoRes.getPromotion(fuelSelected);
			float totalPrice = CalcPrice.calcCarFuelOrder(customer.getSmodel(),infoRes.getNRates(nid), amountF, infoRes.getFuels().get(fuelSelected-1).getMaxPrice(), infoRes.getPromotion(fuelSelected));
			if(prom != null)
				pid = prom.getPid();
			else pid = -1;
			fuelPurchase  = new Purchase(customer.getUserid(),0,stationID, fuelSelected, pid,new Date(),totalPrice,amountF,dName,cid);
		}
		return check;
		
		
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		gui.setWaitProgress();
		if(arg instanceof FuelOrderResponse)
		{
			FuelOrderResponse res = (FuelOrderResponse) arg;
			this.infoRes = res;
			int modelid = customerRes.getUser().getSmodel();
			insertInfo();
			gui.setInfo(modelid,infoRes.getRates(), infoRes.getFuels());
			
			gui.setWaitProgress();
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
		changeFrame(gui,this);
		new CustomerOptionsActions(client,customerRes);
	}


	public ArrayList <Fuel> getFuels() {
		return this.infoRes.getFuels();
	}


	public float getPrice(int fuelID, int nid) {
		Customer c = customerRes.getUser();
		return CalcPrice.calcCarFuelOrder(c.getSmodel(),infoRes.getNRates(nid), 1, infoRes.getFuels().get(fuelID-1).getMaxPrice(), infoRes.getPromotion(fuelID));
		// TODO Auto-generated method stub
		
	}
	
	public Promotion getPromotion(int fuelID) {
		return infoRes.getPromotion(fuelID);
		// TODO Auto-generated method stub
		
	}
	
	
	private boolean checkInventory(float qty, int fuelID, int sid) {
		
		for(StationInventory s: infoRes.getSi())
		{
			if(s.getS().getsid() == sid)
			{
				for(FuelQty f: s.getfQty())
				{
					if(f.getFid() == fuelID)
						if(qty > f.getQty())
							return false;
						else return true;
				}
			}
		
		}
		return false;
		
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
