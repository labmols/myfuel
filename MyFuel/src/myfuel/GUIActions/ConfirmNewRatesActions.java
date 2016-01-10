package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.MessageForManager;
import myfuel.client.MyFuelClient;
import myfuel.client.saleModel;
import myfuel.gui.ConfirmNewRatesGUI;
import myfuel.request.ConfirmNewRatesRequest;
import myfuel.request.RequestEnum;
import myfuel.response.ConfirmNewRatesResponse;
import myfuel.response.booleanResponse;

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
	 *  ConfirmNewRatesActions Constructor
	 * @param client - MyFuelClient
	 */
	public ConfirmNewRatesActions(MyFuelClient client,ArrayList<MessageForManager>msg) {
		super(client);
		this.msg = msg;
		ConfirmNewRatesRequest request = new ConfirmNewRatesRequest(RequestEnum.Select);
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
			ArrayList<saleModel> s = (((ConfirmNewRatesResponse)arg1).getsModes());
			ArrayList<saleModel> c = (((ConfirmNewRatesResponse)arg1).getCurrent());
			
			gui.setDetails(s,c);
			
			gui.setWaitPorgress();
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.setWaitPorgress();
			
			MessageForManager temp = null;
			
			for(MessageForManager m : msg)
			{
				if(m.getType() == 0 && m.getSid() == 4)
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
		changeFrame(gui,new CMActions(client,msg),this);
	}
/***
 *  Sending approved rates(if there is) to the DB
 * @param approved - all approved Rates
 */
	public void sendNewRates(ArrayList<saleModel> approved)
	{
		
		if(approved.isEmpty())
		{
			gui.createWaitDialog("Canceling Suggestion...");
			client.handleMessageFromGUI(new ConfirmNewRatesRequest(RequestEnum.Delete));
		}
		else 
		{
			gui.createWaitDialog("Setting New Rates ...");
			client.handleMessageFromGUI(new ConfirmNewRatesRequest(RequestEnum.Insert,approved) );
		}
		
	}

}
