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
	private SetNewRatesGUI gui;
	private SetNewRatesRequest request;
	private SetNewRatesResponse response;
	private ArrayList<Fuel> NewRates ;
	
	public SetNewRatesActions(MyFuelClient client) {
		super(client);
		gui = new SetNewRatesGUI(this);
		request=new SetNewRatesRequest(0);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);	
	}
	
	public void verifyDetails(String F95,String FDiesel,String FScooter,String FHomeFuel){
		float nF95=0,nFDiesel=0,nFScooter=0,nFHomeFuel=0;
		boolean success = true;
		String error="";
		error += "Input Errors \n\n";
		if(F95.equals("")||FDiesel.equals("")||FScooter.equals("")||FHomeFuel.equals(""))
		{
			success=false;
			error+="The filed is Empty\n";
		}
		else
		{
			nF95=Float.parseFloat(F95);
			nFDiesel=Float.parseFloat(FDiesel);
			nFScooter=Float.parseFloat(FScooter);
			nFHomeFuel=Float.parseFloat(FHomeFuel);
			if(nF95<=0||nFDiesel<=0||nFScooter<=0||nFHomeFuel<=0)
			{
				success=false;
				error+="Some of the fileds are Negative\n";
			}
			if((nF95>NewRates.get(0).getMaxPrice()||nFDiesel>NewRates.get(0).getMaxPrice()
					||nFScooter>NewRates.get(0).getMaxPrice()||nFHomeFuel>NewRates.get(0).getMaxPrice()))
			{
				success=false;
				error+="Some of the prices are bigger then the Maximal price\n";
			}	
		}
		if(!success) gui.showMessage(error);
		else
		{
		Fuel f;
		f=NewRates.get(0);
		f.setSuggPrice(nF95);
		f=NewRates.get(1);
		f.setSuggPrice(nFDiesel);
		f=NewRates.get(2);
		f.setSuggPrice(nFScooter);
		f=NewRates.get(3);
		f.setSuggPrice(nFHomeFuel);
		request = new SetNewRatesRequest(1,NewRates);
		client.handleMessageFromGUI(request);
		}
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

	@Override
	public void backToMenu() {
		changeFrame(gui,new MMActions(client),this);
		
	}

}
