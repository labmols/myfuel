package myfuel.GUIActions;


import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.Fuel;
import myfuel.Entity.NetworkRates;
import myfuel.Entity.Rate;
import myfuel.GUI.SetNewRatesGUI;
import myfuel.Request.LoginRequest;
import myfuel.Request.SetNewRatesRequest;
import myfuel.Response.SetNewRatesResponse;
import myfuel.Response.booleanResponse;

/***
 * This class will be used as a controller to SetNewRatesGUI
 * @author karmo
 *
 */
public class SetNewRatesActions extends GUIActions {
	/***
	 * This class will control this GUI
	 */
	private SetNewRatesGUI gui;
	/***
	 * Request Object that will be send to the server
	 */
	private SetNewRatesRequest request;
	/***
	 * Will save the response from the server
	 */
	private SetNewRatesResponse response;
	/***
	 * Will has the old rates details
	 */
	private ArrayList<NetworkRates> OldRates ;
	
	/***
	 * SetNewRatesActions Constructor
	 * @param client - MyFuelClient
	 */
	public SetNewRatesActions(MyFuelClient client,LoginRequest lr) 
	{
		super(client,lr);
		gui = new SetNewRatesGUI(this);
		request=new SetNewRatesRequest(0);
		gui.createWaitDialog("Getting Networks...");
		client.handleMessageFromGUI(request);
		gui.setVisible(true);	
	}
	
	/***
	 * This method gets 3 Strings from the GUI and checks if they are legal
	 * @param SMRoneCar - First Suggestion
	 * @param SMRfewCar - Second Suggestion
	 * @param SFMoneCar - Third Suggestion
	 */
	public void verifyDetails(String SMRoneCar,String SMRfewCar,String SFMoneCar){
		float nSMRoneCar=0,nSMRfewCar=0,nSFMoneCarr=0;
		boolean success = true;
		String error="";
		error += "Input Errors \n\n";
	
		if(SMRoneCar.equals("")||SMRfewCar.equals("")||SFMoneCar.equals(""))
		{
			gui.setLegal(false);
			error+="Please fill all the fields\n";
		}
		else
		{
			try{
			nSMRoneCar=Float.parseFloat(SMRoneCar);
			nSMRfewCar=Float.parseFloat(SMRfewCar);
			nSFMoneCarr=Float.parseFloat(SFMoneCar);
			
			if(nSMRoneCar<=0||nSMRoneCar<=0||nSMRoneCar<=0 || nSMRoneCar>99||nSMRoneCar>99||nSMRoneCar>99 )
			{
				gui.setLegal(false);
				error+="Some of the fields has illegal value\n";
			}
			else
				gui.setLegal(true);
			
			} catch(Exception e)
			{
				error+="Suggested rate has to be a number\n";
				gui.setLegal(false);
			}
			
		}

	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
	
		
		if(arg1 instanceof SetNewRatesResponse)
		{
			gui.setWaitProgress();
			response = (SetNewRatesResponse)arg1;
			OldRates=response.getOldRates();
			gui.SetNewDetails(response.getOldRates(),response.getNetworks());
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.setWaitProgress();
			booleanResponse resp = (booleanResponse)arg1;
			if(resp.getSuccess())
			gui.showOKMessage(resp.getMsg());
			else gui.showErrorMessage(resp.getMsg());
			backToMenu();
		}
	}

	@Override
	public void backToMenu() {
	
		changeFrame(gui,this);
		new MMActions(client,lr);
		
		
	}

	/***
	 * Receive the new Rate suggestion from the GUI and send it to the server
	 * @param newRates - the suggestion
	 */
	public void sendSuggestion(ArrayList<NetworkRates> newRates) 
	{
		SetNewRatesRequest r = new SetNewRatesRequest(1,newRates);
		gui.createWaitDialog("Updating Rates...");
		client.handleMessageFromGUI(r);
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
