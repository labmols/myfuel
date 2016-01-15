package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.Promotion;
import myfuel.client.Purchase;
import myfuel.client.Station;
import myfuel.gui.FastFuelGUI;
import myfuel.request.LoginRequest;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.FuelOrderResponse;
import myfuel.response.booleanResponse;

/**
 * Fast Fuel GUI Controller, handle all the logic functionality.
 */
public class FastFuelActions extends CarFuelActions {
	
	/**
	 * Fast Fuel GUI Interface.
	 */
	private FastFuelGUI guiF;
	/**
	 * Random customer model number.
	 */
	int modelid;
	
	/**
	 * Create new Fast Fuel GUI Controller and get all the required information including random Car and customer from DB.
	 * @param client - MyFuelClient object.
	 */
	public FastFuelActions(MyFuelClient client) {
		super(client);
		infoRes = null;
		fuelPurchase = null;
		guiF = new FastFuelGUI(this);
		gui = guiF;
		gui.setVisible(true);
		getCustomer();
		getInfoRequest();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get Random Car and station and the owner (The Customer) from the DataBase.
	 */
	private void getCustomer()
	{
		LoginRequest req = new LoginRequest(LoginRequest.FastFuel);
		client.handleMessageFromGUI(req);
	}
	
	/**
	 * Notified when new response recieved from the server, in this case this method handle CustomerLoginResponse 
	 * and FuelOrderResponse.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		
		if(arg instanceof CustomerLoginResponse)
		{
			CustomerLoginResponse res= (CustomerLoginResponse) arg;
			this.customerRes = res;
		}
		
		if(arg instanceof FuelOrderResponse)
		{
			FuelOrderResponse res = (FuelOrderResponse) arg;
			this.infoRes = res;
			
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(customerRes == null){};
					insertInfo();
					modelid = customerRes.getUser().getSmodel();
					guiF.setNFC(customerRes.getFastFuelCar().getcid(), customerRes.getFastStation());
					gui.Initialize(modelid,infoRes);
					gui.checkType(customerRes.getUser().getToc());
					gui.setWaitProgress();
					
				}
			});
			t.start();
				
		}
		else if(arg instanceof booleanResponse)
		{
			gui.setWaitProgress();
			 booleanResponse res = (booleanResponse) arg;
			 if(res.getSuccess())
			 {
			
				 gui.showOKMessage("Thank you for choosing MyFuel!");
				 this.backToMenu();
			 }
			 else
			 {
				 gui.showErrorMessage(res.getMsg());
			 }
		}
	}
	

	/**
	 * Verify Customer input, if all details are correct create new Purchase that will be send to the server.
	 * @param amount - Fuel amount or Price limit.
	 * @param fuelSelected - Fuel ID number.
	 * @param dName - Driver name(If its company customer).
	 * @param nid -Station Network ID.
	 * @param limit - 0- Amount limit, 1- Price limit.
	 * @return true- if all details correct, false- if one of the input details is wrong.
	 */
	public boolean verifyDetails(String amount, int fuelSelected, String dName ,int nid,int limit)
	{
		int cid = customerRes.getFastFuelCar().getcid();
		int fid = customerRes.getFastFuelCar().getfid();
		int stationID= customerRes.getFastStation().getsid();
		float amountF=-1;
		String errors="";
		boolean check= true;

		Customer customer = customerRes.getUser();
		
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

		if(fid != fuelSelected)
		{
			errors += "This fuel pump doesn't match for your car.\n";
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
			float totalPrice;
			Promotion prom = infoRes.getPromotion(fuelSelected);
			if(limit == 0)
				totalPrice = this.getTotalPrice(fuelSelected, nid, amountF);
				else
				{
					amountF = amountF/ this.getPriceForLiter(fuelSelected, nid);
					totalPrice = this.getTotalPrice(fuelSelected, nid, amountF);
				}
			if(prom != null)
				pid = prom.getPid();
			else pid = -1;
			fuelPurchase  = new Purchase(customer.getUserid(),0,stationID, fuelSelected, pid,new Date(),totalPrice,amountF,dName,cid);
		}
		return check;
		
		
	}
	
	/**
	 * Back to Login screen.
	 */
	@Override
	public void backToMenu()
	{
		new LoginActions(client);
		changeFrame(guiF,this);
		
	}
	


}
