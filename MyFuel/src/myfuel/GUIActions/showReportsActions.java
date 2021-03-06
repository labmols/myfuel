package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.MessageForManager;
import myfuel.GUI.*;
import myfuel.Request.LoginRequest;
import myfuel.Request.NetworkReportRequest;
import myfuel.Response.ComapnyReportsResponse;
import myfuel.Response.booleanResponse;

/***
 * Controller for ShowReportGUI
 * @author karmo
 *
 */
public class showReportsActions extends GUIActions {

	/***
	 * This class will be a controller for this GUI
	 */
	private ShowReportGUI gui ; 
	/**
	 * Messages for this User
	 */
	private ArrayList<MessageForManager> msg;
	
	/***
	 * Network ID
	 */
	private int nid;
	
	/***
	 * Network Name
	 */
	private String netName;
	/***
	 * showReportsActions Constructor
	 * @param client - MyFuelClient
	 */
	public showReportsActions(MyFuelClient client,ArrayList<MessageForManager> msg,int nid,LoginRequest lr,String netName) 
	{
		super(client,lr);
		this.msg = msg;
		this.nid = nid;
		this.netName = netName;
		gui = new ShowReportGUI(this,this.netName);
		gui.createWaitDialog("Getting Reported Years...");
		
		NetworkReportRequest request = new NetworkReportRequest(-1,nid);
		client.handleMessageFromGUI(request);
		
		
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (arg1 instanceof ComapnyReportsResponse)
		{
			
			ComapnyReportsResponse r = (ComapnyReportsResponse)arg1;
			gui.setWaitProgress();
			if(r.getType() == 0)
			{
				if(r.getYears().isEmpty())
				{
					gui.showErrorMessage("There are no reports for you");
					this.backToMenu();
				}
				
				else
					gui.setYears(r.getYears());
			}
			else if(r.getType() == 1)
			{
				gui.setInventoryPanel(r.getqStationInventory());
				gui.setIncomePanel(r.getqStationIncome());
				gui.setPurchasePanel(r.getqStationPurchase());
				gui.setVisibleThings();
			}
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.showErrorMessage(((booleanResponse)arg1).getMsg());
		}

	}
	
	/***
	 * Get Reports details from the server for a specific year
	 * @param year - Requested Year
	 */
	public void getDetails(int year)
	{
		NetworkReportRequest request = new NetworkReportRequest(year,nid);
		gui.createWaitDialog("Getting Reports for"+" "+year+"...");
		client.handleMessageFromGUI(request);
	}

	@Override
	public void backToMenu() 
	{

		changeFrame(gui,this);
		new CMActions(client,msg,nid,lr,this.netName);
		

	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
