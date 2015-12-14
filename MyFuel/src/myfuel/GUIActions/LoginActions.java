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
	
	public LoginActions(MyFuelClient client){
		super(client);
		gui = new LogInGUI(this);
		gui.setVisible(true);
	}
	
	public void sendRequest(int type, int userid, String password) 
	{
		lr = new LoginRequest(type,userid,password);
		client.handleMessageFromGUI(lr);
		
	}
	
	
	private void userResponse(Object response){
		UserLoginResponse res = (UserLoginResponse) response;
		if(res.getError()== ErrorEnum.NoError)
		{
			gui.showMessage("Welcome to MyFuel!");
			changeFrame(gui,new UserOptionsActions (client,res.getUser()));
			
		}
		else
		{
			switch(res.getError())
			{
			case UserNotExist: gui.showMessage("UserID or Password incorrect!");
			break;
			case AlreadyLoggedIn: gui.showMessage("This user is already connected to System!");
			break;
			}
			
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
		WorkerLoginResponse res = (WorkerLoginResponse) response;
		if(res.getError()==ErrorEnum.NoError) {
			 gui.showMessage("Welcome to MyFuel!"); 
			 switch(res.getRole()){
			 case MarketingManager: 
				 			changeFrame(gui,new MMActions(client));
				 			break;
			 case MarketingDelegate: 
							 changeFrame(gui, new MDActions(client)); 
							 break;
			case CompanyManager:
				break;
			case StationManager:
				break;
			case StationWorker:
				break;
			 }
		}
		else switch(res.getError())
		{
		case UserNotExist: gui.showMessage("UserID or Password incorrect!");
		break;
		case AlreadyLoggedIn: gui.showMessage("This user is already connected to System!");
		break;
		}
	
		
	}
	
	public void Register(){
		changeFrame(gui,new RegisterActions(client));
	
	}

	@Override
	public void update(Observable o, Object arg) {
		if(gui.isActive()){
		// TODO Auto-generated method stub
				if(lr.getType() ==0) userResponse(arg);
				else workerResponse(arg);	
		}
	}
	
}
