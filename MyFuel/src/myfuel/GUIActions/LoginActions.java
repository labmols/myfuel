package myfuel.GUIActions;

import java.awt.Cursor;
import java.util.Observable;

import myfuel.response.*;

import java.util.Observer;

import com.alee.managers.notification.NotificationManager;

import myfuel.client.MyFuelClient;
import myfuel.gui.LogInGUI;
import myfuel.request.LoginRequest;
import myfuel.response.ErrorEnum;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.WorkerLoginResponse;

/**
 * 
 * @author Maor
 *
 */
public class LoginActions extends GUIActions {
	/**
	 * The Log In request , contains user id and password for identify.
	 */
	private LoginRequest lr;
	
	/**
	 * Log In JFrame , this is the Login User interface.
	 */
	private LogInGUI gui;
	
	/**
	 * Create new LoginGUI Controller.
	 * @param client - the client object.
	 */
	public LoginActions(MyFuelClient client){
		super(client);
		gui = new LogInGUI(this);
		gui.setVisible(true);
	}
	
	/**
	 * Send login request to the server.
	 * @param type - type of user (0-customer,1-worker)
	 * @param userid - user id
	 * @param password - user password
	 */
	public void sendRequest(int type, int userid, String password) 
	{
		lr = new LoginRequest(type,userid,password);
		gui.createWaitDialog("Verfiying your details...");
		client.handleMessageFromGUI(lr);
		
	}
	
	/**
	 * Verify Login details user input.
	 * @param type - Type chosen (Customer/Worker).
	 * @param userid - User ID String
	 * @param pass - User Password String
	 */
	public void verifyDetails(int type, String userid, String pass)
	{
		boolean error = false;
		String msg ="";
		int id=0;
		try {
			id = Integer.parseInt(userid);
		}
		catch(NumberFormatException e) {
			msg += "illegal UserID value!\n";
			error = true;
		}
		
		if(pass.equals("")) {
			error=true;
			msg += "Password field is empty!\n";
		}
		if(!error) sendRequest(type,id,pass);
		else gui.showErrorMessage(msg);
		
	}
	
	/**
	 * Handle Customer login response.
	 * @param response - The response object from server(CustomerLoginResponse or booleanResponse).
	 */
	private void customerResponse(Object response){
		if(response instanceof CustomerLoginResponse)
		{
		CustomerLoginResponse res = (CustomerLoginResponse) response;
		// NotificationManager.setLocation(1);
		 //NotificationManager.showNotification ( gui,"Connected" );
		 
			gui.showOKMessage("Welcome to MyFuel!");
			changeFrame(gui,new CustomerOptionsActions (client,res),this);
			
		}
		else
		{
		booleanResponse res = (booleanResponse) response;
		 gui.showErrorMessage(res.getMsg());
		
			}
			
		}
	
	
	
	/** Handle Worker Login Response.
	 * This function get the response from server and acting according to this response.
	 * Setting next JFrame According to the worker rule.
	 * @param response
	 */
	private void workerResponse(Object response){
		if(response instanceof WorkerLoginResponse)
		{
		WorkerLoginResponse res = (WorkerLoginResponse) response;
		
			 gui.showOKMessage("Welcome to MyFuel!"); 
			 switch(res.getRole()){
			 case MarketingManager: 
				 			changeFrame(gui,new MMActions(client),this);
				 			break;
			 case MarketingDelegate: 
							 changeFrame(gui, new MDActions(client),this); 
							 break;
			case CompanyManager:
				changeFrame(gui,new CMActions(client,res.getMsg(),res.getNid()),this);
				break;
			case StationManager:
				changeFrame(gui,new SMActions(client,((WorkerLoginResponse) response).getSid(),res.getMsg(),res.getNid()),this);
				break;
			case StationWorker:
				changeFrame(gui,new SWActions(client,((WorkerLoginResponse) response).getSid()),this);
				break;
			case HomeManager:
				changeFrame(gui,new homeQtyOrderActions(client),this);	
			 }
			
				 
		}
		
		else 
		{
			booleanResponse res = (booleanResponse) response;
			 gui.showErrorMessage(res.getMsg());
		}
	
		
	}
	
	/** 
	 *  Open register option screen.
	 */
	public void RegisterScreen(){
		changeFrame(gui,new RegisterActions(client),this);
	
	
	}
	
	/**
	 * Notified by the Client when a response recieved from the server , 
	 * handle this response according to the login type(Customer/Worker).
	 * If there is an error, notify the user.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof CustomerLoginResponse || arg instanceof WorkerLoginResponse || arg instanceof booleanResponse){
		// TODO Auto-generated method stub
			gui.setWaitPorgress();
				if(lr.getType() ==0) customerResponse(arg);
				else workerResponse(arg);	
		}
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}
	
}
