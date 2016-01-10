package myfuel.GUIActions;


import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.MyFuelClient;
import myfuel.client.Rate;
import myfuel.gui.SetNewRatesGUI;
import myfuel.request.SetNewRatesRequest;
import myfuel.response.SetNewRatesResponse;
import myfuel.response.booleanResponse;


public class SetNewRatesActions extends GUIActions {
	private SetNewRatesGUI gui;
	private SetNewRatesRequest request;
	private SetNewRatesResponse response;
	private ArrayList<Rate> NewRates ;
	
	public SetNewRatesActions(MyFuelClient client) {
		super(client);
		gui = new SetNewRatesGUI(this);
		request=new SetNewRatesRequest(0);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);	
	}
	
	public void verifyDetails(String SMRoneCar,String SMRfewCar,String SFMoneCar){
		int nSMRoneCar=0,nSMRfewCar=0,nSFMoneCarr=0;
		boolean success = true;
		String error="";
		error += "Input Errors \n\n";
	
		if(SMRoneCar.equals("")||SMRfewCar.equals("")||SFMoneCar.equals(""))
		{
			success=false;
			error+="Please fill all the fields\n";
		}
		else
		{
			try{
			nSMRoneCar=Integer.parseInt(SMRoneCar);
			nSMRfewCar=Integer.parseInt(SMRfewCar);
			nSFMoneCarr=Integer.parseInt(SFMoneCar);
			
			if(nSMRoneCar<=0||nSMRoneCar<=0||nSMRoneCar<=0 || nSMRoneCar>99||nSMRoneCar>99||nSMRoneCar>99 )
			{
				success=false;
				error+="Some of the fields has illegal value\n";
			}
			
			} catch(Exception e)
			{
				error+="Suggested rate has to be a number\n";
				success = false;
			}
			
		}
		if(!success) gui.showErrorMessage(error);
		else
		{
		Rate f;
		f=NewRates.get(1);
		f.setDiscount(nSMRoneCar);
		f=NewRates.get(2);
		f.setDiscount(nSMRfewCar);
		f=NewRates.get(3);
		f.setDiscount(nSFMoneCarr);
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
		
		else if(arg1 instanceof booleanResponse)
		{
			booleanResponse resp = (booleanResponse)arg1;
			if(resp.getSuccess())
			gui.showOKMessage(resp.getMsg());
			else gui.showErrorMessage(resp.getMsg());
			backToMenu();
		}
	}

	@Override
	public void backToMenu() {
		changeFrame(gui,new MMActions(client),this);
		
	}

}
