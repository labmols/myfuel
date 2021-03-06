package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.Car;
import myfuel.Entity.Customer;
import myfuel.Entity.Fuel;
import myfuel.Entity.FuelQty;
import myfuel.Entity.NetworkRates;
import myfuel.Entity.Promotion;
import myfuel.Entity.Purchase;
import myfuel.Entity.Rate;
import myfuel.Entity.Station;
import myfuel.Entity.StationInventory;
import myfuel.GUI.CarFuelGUI;
import myfuel.Request.FuelOrderRequest;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.CustomerLoginResponse;
import myfuel.Response.FuelOrderResponse;
import myfuel.Response.Response;
import myfuel.Response.booleanResponse;
import myfuel.Tools.CalcPrice;

/**
 * Car Fuel GUI Controller, responsible for all logic functionality.
 * @author Maor
 *
 */
public class CarFuelActions extends GUIActions {
	/**
	 * Car Fuel User interface.
	 */
	protected CarFuelGUI gui;
	/**
	 * Customer Login response from server(contains all the customer details).
	 */
	protected CustomerLoginResponse customerRes;
	/**
	 * Fuel order response from server(contains all the information required for fuel order).
	 */
	protected FuelOrderResponse infoRes;
	/**
	 * Fuel Purchase details.
	 */
	protected Purchase fuelPurchase;
	
	/**
	 * Create new Car Fuel GUI Controller(for inherited class).
	 * @param client - MyFuel client object (for communication to the server).
	 * @param lr - Login Request object(contains the login details).
	 */
	public CarFuelActions(MyFuelClient client,LoginRequest lr)
	{
		super(client,lr);
	}
	
	public CarFuelActions(MyFuelClient client) {
		super(client,null);
		this.customerRes =null;
		this.infoRes = null;
		fuelPurchase = null;
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Create new Car Fuel GUI Controller and getting all the required information for creating an order.
	 * @param client -MyFuel client object (for communication to server).
	 * @param res - Customer Login Response(contains all the customer details).
	 * @param lr - Login Request object(contains the login details).
	 */
	public CarFuelActions(MyFuelClient client,CustomerLoginResponse res , LoginRequest lr) {
		super(client,lr);
		this.customerRes = res;
		infoRes = null;
		fuelPurchase = null;
		gui = new CarFuelGUI(this);
		gui.setVisible(true);
		getInfoRequest();
		
		// TODO Auto-generated constructor stub
	}

/**
 * Getting All the required information request from Server(including inventory status,stations details and rates).
 */
	protected void getInfoRequest() {
		// TODO Auto-generated method stub
		gui.createWaitDialog("Getting Details...");
		FuelOrderRequest req = new FuelOrderRequest (RequestEnum.Select,1,-1);
		client.handleMessageFromGUI(req);
	}
	
/**
 * Insert Customer cars and list of stations to the User interface.
 */
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

	
/**
 * Create new fuel Purchase that will send to the client.
 */
	public void createPurchase()
	{
		FuelOrderRequest req = new FuelOrderRequest(RequestEnum.Insert,fuelPurchase,null);
		client.handleMessageFromGUI(req);
	}

	/**
	 * Verify user input details.
	 * @param amount - Fuel Order amount.
	 * @param fuelSelected - Fuel type selected.
	 * @param dName - Driver name .
	 * @param stationSelected - Station selected.
	 * @param cid - Car ID Number.
	 * @param nid - Network ID number.
	 * @param limit - Limit type(0- Amount,1-Cash).
	 * @return true if all input details is correct and false otherwise.
	 */
	public boolean verifyDetails(String amount, int fuelSelected, String dName ,String stationSelected,int cid,int nid,int limit)
	{
		float amountF=-1;
		String errors="";
		boolean check= true;
		boolean found = false;
		float totalPrice;
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
			//e.printStackTrace();
			errors += "illegal amount value.\n";
			check = false;
		}
		
		if(limit == 0)
			totalPrice = this.getTotalPrice(fuelSelected, nid, amountF);
			else
			{
				amountF = amountF/ this.getPriceForLiter(fuelSelected, nid);
				totalPrice = this.getTotalPrice(fuelSelected, nid, amountF);
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
			return false;
		else
		{
			boolean paid = true;
			if(customer.getSmodel() == Rate.FullyMonthly) paid= false;
			int pid;
			Promotion prom = infoRes.getPromotion(fuelSelected);
			if(prom != null)
				pid = prom.getPid();
			else pid = -1;
			fuelPurchase  = new Purchase(customer.getUserid(),0,stationID, fuelSelected, pid,new Date(),totalPrice,amountF,dName,cid,paid);
		}
		return check;
		
		
	}
	
	/**
	 * Get total order price according to input.
	 * @param fuelSelected - Fuel type selected.
	 * @param nid - Network id number.
	 * @param amountF - Fuel amount.
	 * @return total price for current order.
	 */
	public float getTotalPrice(int fuelSelected, int nid, float amountF)
	{
		Customer customer = customerRes.getUser();
		return CalcPrice.calcCarFuelOrder(customer.getSmodel(),customer.getAtype(), infoRes.getNRates(nid), amountF, infoRes.getFuels().get(fuelSelected-1).getMaxPrice(), infoRes.getPromotion(fuelSelected));
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
			int accessType = customerRes.getUser().getAtype();
			insertInfo();
			gui.Initialize(modelid,accessType, infoRes);
			
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
		new CustomerOptionsActions(client,customerRes,lr);
		
	}


	public ArrayList <Fuel> getFuels() {
		return this.infoRes.getFuels();
	}

	/**
	 * Get specific fuel price for liter.
	 * @param fuelID - FuelID number.
	 * @param nid -Network id number.
	 * @return Fuel price for liter.
	 */
	public float getPriceForLiter(int fuelID, int nid) {
		NetworkRates rates = infoRes.getNRates(nid);
		float disc;
		if(customerRes.getUser().getSmodel() > 1)
		disc = rates.getModelDiscount(Rate.MonthlyOne);
		else disc=0;
		return infoRes.getFuels().get(fuelID-1).getMaxPrice()*(1-(disc/100));
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get promotion details (if exist)
	 * @param fuelID - Fuel ID number.
	 * @return the promotion details object.
	 */
	public Promotion getPromotion(int fuelID) {
		return infoRes.getPromotion(fuelID);
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Check inventory status for a specific fuel type. 
	 * @param qty - Fuel order amount
	 * @param fuelID - Fuel type id number.
	 * @param sid - Station id number
	 * @return true, if there is enough amount for current order, otherwise false.
	 */
	protected boolean checkInventory(float qty, int fuelID, int sid) {
		
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
	
	/**
	 * Check if string contains only chars.
	 * @param name - String value.
	 * @return true if all chars, otherwise false.
	 */
	protected boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}
	
	/**
	 * Get customer credit card number.
	 * @return the customer credit card number.
	 */
	public String getCustomerCC()
	{
		return customerRes.getUser().getCnumber();
	}

	@Override
	public void LogOut() {
		this.LogOutRequest(gui, lr);
		
	}

	/**
	 * Get customer model id.
	 * @return the customer model id.
	 */
	public int getCustomerModel() {
		// TODO Auto-generated method stub
		return customerRes.getUser().getSmodel();
	}
	
	public void setInfoRes(FuelOrderResponse res)
	{
		this.infoRes = res;
	}
	
	public FuelOrderResponse getInfoRes()
	{
		return this.infoRes;
	}
	
	public CustomerLoginResponse getCustomerRes()
	{
		return this.customerRes;
	}
	
	public void setCustomerRes(CustomerLoginResponse res)
	{
		this.customerRes = res;
	}



	

}
