package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.MessageForManager;
import myfuel.client.MyFuelClient;
import myfuel.gui.*;
import myfuel.request.CompanyReportRequest;
import myfuel.response.ComapnyReportsResponse;
import myfuel.response.booleanResponse;

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
	 * showReportsActions Constructor
	 * @param client - MyFuelClient
	 */
	public showReportsActions(MyFuelClient client,ArrayList<MessageForManager> msg) 
	{
		super(client);
		this.msg = msg;
		CompanyReportRequest request = new CompanyReportRequest(-1);
		client.handleMessageFromGUI(request);
		
		gui = new ShowReportGUI(this);
		gui.createWaitDialog("Getting Years That Listed In The DB...");
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (arg1 instanceof ComapnyReportsResponse)
		{
			
			ComapnyReportsResponse r = (ComapnyReportsResponse)arg1;
			gui.setWaitPorgress();
			if(r.getType() == 0)
				gui.setYears(r.getYears());
			
			else if(r.getType() == 1)
			{
				gui.setInventoryPanel(r.getqStationInventory());
				gui.setIncomePanel(r.getqStationIncome());
				gui.setPurchasePanel(r.getqStationPurchase());
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
		CompanyReportRequest request = new CompanyReportRequest(year);
		gui.createWaitDialog("Getting Reports for"+" "+year+"...");
		client.handleMessageFromGUI(request);
	}

	@Override
	public void backToMenu() 
	{
		//changeFrame(gui,new CMActions(client,msg),this);

	}

}
