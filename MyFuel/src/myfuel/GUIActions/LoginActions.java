package myfuel.GUIActions;

import java.util.Observable;
import java.util.Observer;

import myfuel.client.MyFuelClient;
import myfuel.gui.LogInGUI;
import myfuel.request.LoginRequest;
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
		if(res.errorCode==-1)
		{
			gui.showMessage("Welcome to MyFuel!");
			changeFrame(gui,new UserOptionsActions (client,res.user));
			
		}
		else
		{
			switch(res.errorCode)
			{
			case 1: gui.showMessage("UserID or Password incorrect!");
			break;
			case 2: gui.showMessage("This user is already connected to System!");
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
		if(res.getWorkerExist()) {
			 gui.showMessage("Welcome!"); 
			 switch(res.getRole()){
			 case 4: changeFrame(gui, new MDActions(client));
			 }
		}
		else gui.showMessage("UserID or Password incorrect!");
	
		
	}
	
	public void Register(){
		RegisterActions actions = new RegisterActions(client);
		gui.setVisible(false);
		gui.dispose();
	
	}

	@Override
	public void update(Observable o, Object arg) {
		if(gui.isActive()){
		// TODO Auto-generated method stub
				if(lr.type ==0) userResponse(arg);
				else workerResponse(arg);	
		}
	}
	
}
