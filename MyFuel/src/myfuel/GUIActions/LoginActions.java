package myfuel.GUIActions;

import java.awt.Cursor;
import java.util.Observable;
import java.util.Observer;

import com.alee.managers.notification.NotificationManager;

import myfuel.Client.MyFuelClient;
import myfuel.GUI.LogInGUI;
import myfuel.Request.LoginRequest;
import myfuel.Response.*;

/**
 * Log In GUI Controller, responsible for all related login queries.
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
		super(client,null);
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
		else 
		{
			gui.showErrorMessage(msg);
			gui.setButton(true);
		}
		
	}
	
	/**
	 * Handle Customer login response.
	 * @param response - The response object from server(CustomerLoginResponse or booleanResponse).
	 */
	private void customerResponse(Object response){
		if(response instanceof CustomerLoginResponse)
		{
		CustomerLoginResponse res = (CustomerLoginResponse) response;
		//NotificationManager.setLocation(1);
		 
		 
			gui.showOKMessage("Welcome to MyFuel!");
			new CustomerOptionsActions (client,res,lr);
			changeFrame(gui,this);
			
			
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
				 			new MMActions(client,lr);
				 			changeFrame(gui,this);	
				 			break;
			 case MarketingDelegate: 
				 			  new MDActions(client,lr);
							 changeFrame(gui, this); 
							 break;
			case CompanyManager:
				new CMActions(client,res.getMsg(),res.getNid(),lr,res.getNetName());
				changeFrame(gui,this);
				break;
			case StationManager:
				new SMActions(client,((WorkerLoginResponse) response).getSid(),res.getMsg(),res.getNid(),lr,res.getStationName());
				changeFrame(gui,this);
				break;
			case StationWorker:
				new SWActions(client,((WorkerLoginResponse) response).getSid(),lr);
				changeFrame(gui,this);	
				break;
			case HomeManager:
				new homeQtyOrderActions(client,lr);
				changeFrame(gui,this);	
				break;
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
		changeFrame(gui,this);
		new RegisterActions(client,lr);
		
	
	
	}
	
	/**
	 * Notified by the Client when a response recieved from the server , 
	 * handle this response according to the login type(Customer/Worker).
	 * If there is an error, notify the user.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof CustomerLoginResponse || arg instanceof WorkerLoginResponse || arg instanceof booleanResponse) {
		// TODO Auto-generated method stub
			gui.setWaitProgress();
				if(lr.getType() ==0)
				{
					customerResponse(arg);
					gui.setButton(true);
				}
				else
				{
					workerResponse(arg);
					gui.setButton(true);
				}
		}
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Move to Fast Fuel option screen.
	 */
	public void FastFuel() {
		// TODO Auto-generated method stub
		changeFrame(gui,this);
		new FastFuelActions(client,lr);
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		
	}
	
}
