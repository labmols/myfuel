package Fixtures.MyFuel;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.Customer;
import myfuel.Entity.Fuel;
import myfuel.Entity.FuelQty;
import myfuel.Entity.Network;
import myfuel.Entity.NetworkRates;
import myfuel.Entity.Promotion;
import myfuel.Entity.Purchase;
import myfuel.Entity.Station;
import myfuel.Entity.StationInventory;
import myfuel.GUI.ServerGUI;
import myfuel.GUIActions.CarFuelActions;
import myfuel.Request.CheckInventoryRequest;
import myfuel.Request.FuelOrderRequest;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.CheckInventoryResponse;
import myfuel.Response.CustomerLoginResponse;
import myfuel.Response.FuelOrderResponse;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;
import fit.ActionFixture;

public class CarFuel extends ActionFixture implements Observer   {
	private CarFuelActions actions;
	private MyFuelClient client;
	private String stationName="";
	private int limit;
	private int fid;
	private int cid;
	private int sid;
	private int nid ;
	private String amount;
	public final Object lock = new Object();
	FuelQty beforePur ;
	FuelQty afterPur ;
	private CheckInventoryResponse inventoryOrders=null;

	
	public void startFuel()
	{
		LoginRequest LoginReq = new LoginRequest(LoginRequest.CustomerLogin, 203921838, "12345");
		try {
			client = new MyFuelClient("localhost",5555);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actions = new CarFuelActions(client);
		client.deleteObserver(actions);
		client.addObserver(this);
		FuelOrderRequest infoReq = new FuelOrderRequest (RequestEnum.Select,1,-1);
		client.handleMessageFromGUI(LoginReq);
		client.handleMessageFromGUI(infoReq);
		synchronized (lock) {
			while(actions.getCustomerRes() == null || actions.getInfoRes() == null)
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		

		
	}
	
	public void setStation(String station)
	{
		this.stationName = new String(station);
	
		for(Station s : actions.getInfoRes().getStations())
		{
			if(s.getName().equals(stationName))
			{
				this.sid= s.getsid();
				this.nid = s.getNetwork().getNid();
			}
		}
	}
	
	public void setFuelType(int fid)
	{
		this.fid = fid;
	}
	
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	
	public void setAmount(String amount)
	{
		this.amount = amount;
	}
	
	public void setCarId(int cid)
	{
		this.cid = cid;
	}
	
	public boolean verifyDetails()
	{
		
		return actions.verifyDetails(amount, fid, "", stationName, cid, nid, limit);
	}
	
	public void sendOrder()
	{
		actions.createPurchase();
		StationInventory s = actions.getInfoRes().getStationInventory(sid);
		beforePur = s.getfQty().get(fid-1);
	}
	
	public boolean inventoryUpdateCheck()
	{
		float expectedInventory;
		float before = beforePur.getQty();
		float amountF = Float.parseFloat(amount);
		
		if(limit == 1)
	    expectedInventory = before - amountF/ (actions.getPriceForLiter(fid, nid));
		else
			expectedInventory = before - amountF;
		
		FuelOrderRequest infoReq = new FuelOrderRequest (RequestEnum.Select,1,-1);
		client.handleMessageFromGUI(infoReq);
		actions.setInfoRes(null);
		synchronized (lock) {
			while(actions.getInfoRes() == null)
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		float currentQty = actions.getInfoRes().getStationInventory(sid).getfQty().get(fid-1).getQty();
		return (expectedInventory  == currentQty);
		
		
	}
	
	public boolean inventoryOrderCheck()
	{
		
		CheckInventoryRequest req = new CheckInventoryRequest(0, sid);
		client.handleMessageFromGUI(req);
		
		synchronized (lock) {
			while(inventoryOrders == null)
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		ArrayList<FuelQty> newOrders = inventoryOrders.getNewOrder() ;
		if(newOrders.isEmpty()) return false;
		else
		{
			for(FuelQty f: newOrders)
			{
				if(f.getFid() == fid)
					return true;
			}
		}
		return false;
		
	}
	
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof CustomerLoginResponse)
		{
			synchronized (lock)
			{
				CustomerLoginResponse res = (CustomerLoginResponse) arg;
				actions.setCustomerRes(res);
				lock.notify();
			}
			
				
		}
		
		if(arg instanceof FuelOrderResponse)
		{
			synchronized (lock)
			{
			FuelOrderResponse res = (FuelOrderResponse) arg;
			actions.setInfoRes(res);
			lock.notify();
			}
			
		}
		
		if(arg instanceof CheckInventoryResponse)
		{
			synchronized (lock)
			{
			CheckInventoryResponse res = (CheckInventoryResponse) arg;
			this.inventoryOrders = res;
			lock.notify();
			}
			
		}
		
		if(arg instanceof booleanResponse)
		{
			synchronized (lock)
			{
			
			this.inventoryOrders = new CheckInventoryResponse(new ArrayList<FuelQty>());
			lock.notify();
			}
			
		}
	}
	
	


	
	}



	
	
	
	

