package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.MessageForManager;
import myfuel.Entity.Rate;
import myfuel.GUI.ConfirmNewRatesGUI;
import myfuel.Request.ConfirmNewRatesRequest;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.ConfirmNewRatesResponse;
import myfuel.Response.booleanResponse;

/***
 * Controller for ConfirmNewRatesGUI
 * @author karmo
 *
 */
public class ConfirmNewRatesActions extends GUIActions {
	/***
	 * This class will be a Controller for this GUI
	 */
	private ConfirmNewRatesGUI gui ; 
	/***
	 * Messages for this user
	 */
	private ArrayList<MessageForManager> msg;
	
	/***
	 * Network ID
	 */
	private int nid;
	
	/***
	 *  ConfirmNewRatesActions Constructor
	 * @param client - MyFuelClient
	 * @param msg - messages
	 * @param nid - network ID
	 */
	public ConfirmNewRatesActions(MyFuelClient client,ArrayList<MessageForManager>msg,int nid,LoginRequest lr) {
		super(client,lr);
		
		this.msg = msg;
		this.nid = nid;
		
		ConfirmNewRatesRequest request = new ConfirmNewRatesRequest(RequestEnum.Select , nid);
		
		gui = new ConfirmNewRatesGUI(this);
		gui.createWaitDialog("Getting Rates...");
		client.handleMessageFromGUI(request);
		
		
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof ConfirmNewRatesResponse)
		{
			ArrayList<Rate> s = (((ConfirmNewRatesResponse)arg1).getsModes());
			ArrayList<Rate> c = (((ConfirmNewRatesResponse)arg1).getCurrent());
			
			gui.setDetails(s,c);
			
			gui.setWaitProgress();
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.setWaitProgress();
			
			MessageForManager temp = null;
			
			for(MessageForManager m : msg)
			{
				if(m.getType() == 0 && m.getNid() == nid)
				{
					temp = m; break; 
				}
			}
			
			if(temp!=null)
				msg.remove(msg.indexOf(temp));
			
			if(((booleanResponse)arg1).getSuccess())
			{
				gui.showOKMessage(((booleanResponse)arg1).getMsg());
				gui.clearTable();
				
			}
			else gui.showErrorMessage(((booleanResponse)arg1).getMsg());
			backToMenu() ;
		}

	}
/***
 * Return to Company manager menu
 */
	@Override
	public void backToMenu() 
	{

		changeFrame(gui,this);
		new CMActions(client,msg,nid,lr);
		
	}
/***
 *  Sending approved rates(if there is) to the DB
 * @param approved - all approved Rates
 */
	public void sendNewRates(ArrayList<Rate> approved)
	{
		
		if(approved.isEmpty())
		{
			gui.createWaitDialog("Canceling Suggestion...");
			client.handleMessageFromGUI(new ConfirmNewRatesRequest(RequestEnum.Delete , nid));
		}
		else 
		{
			gui.createWaitDialog("Setting New Rates ...");
			client.handleMessageFromGUI(new ConfirmNewRatesRequest(RequestEnum.Insert,approved , nid) );
		}
		
	}

@Override
public void LogOut() {
	// TODO Auto-generated method stub
	this.LogOutRequest(gui, lr);
	
}

}
