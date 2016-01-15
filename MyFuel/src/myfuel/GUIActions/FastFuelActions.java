package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.FastFuelGUI;
import myfuel.request.LoginRequest;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.FuelOrderResponse;
import myfuel.response.booleanResponse;

public class FastFuelActions extends CarFuelActions {
	
	private FastFuelGUI guiF;
	int modelid;
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
	
	@Override
	protected void insertInfo()
	{
		
	}
	
	private void getCustomer()
	{
		LoginRequest req = new LoginRequest(LoginRequest.FastFuel);
		client.handleMessageFromGUI(req);
	}
	

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
			insertInfo();
			
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(customerRes == null){};
					modelid = customerRes.getUser().getSmodel();
					guiF.setNFC(customerRes.getFastFuelCar().getcid(), customerRes.getFastStation());
					gui.Initialize(modelid,infoRes);
					gui.setWaitProgress();
					
				}
			});
			t.start();
				
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
				 gui.showErrorMessage(res.getMsg());
			 }
		}
	}
	
	@Override
	public void backToMenu()
	{
		changeFrame(guiF,this);
		new LoginActions(client);
	}
	


}
