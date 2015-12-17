package myfuel.GUIActions;


import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.MyFuelClient;
import myfuel.gui.SetNewRatesGUI;
import myfuel.request.SetNewRatesRequest;
import myfuel.response.SetNewRatesResponse;
import myfuel.response.booleanResponse;


public class SetNewRatesActions extends GUIActions {
	SetNewRatesGUI gui;
	SetNewRatesRequest request;
	SetNewRatesResponse response;
	ArrayList<Fuel> NewRates ;
	
	public SetNewRatesActions(MyFuelClient client) {
		super(client);
		gui = new SetNewRatesGUI(this);
		request=new SetNewRatesRequest(0);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);	
	}
	
	public void verifyDetails(float F95,float FDiesel,float FScooter,float FHomeFuel){
		Fuel f;
		f=NewRates.get(0);
		f.setFprice(F95);
		f=NewRates.get(1);
		f.setFprice(FDiesel);
		f=NewRates.get(2);
		f.setFprice(FScooter);
		f=NewRates.get(3);
		f.setFprice(FHomeFuel);
		request = new SetNewRatesRequest(1,NewRates);
		client.handleMessageFromGUI(request);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
	
		
		if(arg1 instanceof SetNewRatesResponse)
		{
			response = (SetNewRatesResponse)arg1;
			NewRates=response.getoldRates();
			gui.SetNewDetails(response.getoldRates());
		}
		
		else if(gui.isActive() && arg1 instanceof booleanResponse)
		{
	
			booleanResponse resp = (booleanResponse)arg1;
			
			if(resp.getSuccess())
				gui.showMessage("The Suggestion has been sent to the Company Manger ");
			else
				gui.showMessage("The send is failed");
			
		}
	}

}
