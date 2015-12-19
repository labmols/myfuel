package myfuel.GUIActions;

import java.util.Observable;

import myfuel.response.*;

import java.util.Observer;

import myfuel.client.MyFuelClient;
import myfuel.gui.LogInGUI;
import myfuel.request.LoginRequest;
import myfuel.response.ErrorEnum;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;
import myfuel.response.WorkerLoginResponse;

public class LoginActions extends GUIActions {
	private LoginRequest lr;
	private LogInGUI gui;
	
	/**
	 * create new LoginGUI Controller.
	 * @param client - the client object.
	 */
	public LoginActions(MyFuelClient client){
		super(client);
		gui = new LogInGUI(this);
		gui.setVisible(true);
	}
	
	/**
	 * send login request to the server.
	 * @param type - type of user (0-customer,1-worker)
	 * @param userid - user id
	 * @param password - user password
	 */
	public void sendRequest(int type, int userid, String password) 
	{
		lr = new LoginRequest(type,userid,password);
		client.handleMessageFromGUI(lr);
		
	}
	
	public void verifyDetails(int type, String userid, String pass)
	{
		boolean error = false;
		int id=0;
		try {
			id = Integer.parseInt(userid);
		}
		catch(NumberFormatException e) {
			gui.showMessage("illegal UserID value!");
			error = true;
		}
		
		if(pass.equals("")) {
			error=true;
			gui.showMessage("Password field is empty!");
		}
		if(!error) sendRequest(type,id,pass);
		
	}
	
	/**
	 * 
	 * @param response
	 */
	private void userResponse(Object response){
		if(response instanceof UserLoginResponse)
		{
		UserLoginResponse res = (UserLoginResponse) response;

			gui.showMessage("Welcome to MyFuel!");
			changeFrame(gui,new UserOptionsActions (client,res.getUser(),res.getStations()),this);
			
		}
		else
		{
		booleanResponse res = (booleanResponse) response;
		 gui.showMessage(res.getMsg());
		
			}
			
		}
	
	
	
	/** 
	 * this function get the response from server and acting according to this response.
	 * role 1 - Station Worker
	 * role 2- Station Manager
	 * role 3- Marketing Manager
	 * role 4- Marketing Delegate
	 * role 5- Company Manager
	 * @param response
	 */
	private void workerResponse(Object response){
		if(response instanceof WorkerLoginResponse)
		{
		WorkerLoginResponse res = (WorkerLoginResponse) response;
		
			 gui.showMessage("Welcome to MyFuel!"); 
			 switch(res.getRole()){
			 case MarketingManager: 
				 			changeFrame(gui,new MMActions(client),this);
				 			break;
			 case MarketingDelegate: 
							 changeFrame(gui, new MDActions(client),this); 
							 break;
			case CompanyManager:
				changeFrame(gui,new CMActions(client),this);
				break;
			case StationManager:
				changeFrame(gui,new SMActions(client,((WorkerLoginResponse) response).getSid()),this);
				break;
			case StationWorker:
				changeFrame(gui,new SWActions(client,((WorkerLoginResponse) response).getSid()),this);
				break;
			 }
		}
		
		else 
		{
			booleanResponse res = (booleanResponse) response;
			 gui.showMessage(res.getMsg());
		}
	
		
	}
	
	/**
	 * change to register JFrame 
	 */
	public void RegisterScreen(){
		changeFrame(gui,new RegisterActions(client),this);
	
	
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof UserLoginResponse || arg instanceof WorkerLoginResponse || arg instanceof booleanResponse){
		// TODO Auto-generated method stub
				if(lr.getType() ==0) userResponse(arg);
				else workerResponse(arg);	
		}
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}
	
}
