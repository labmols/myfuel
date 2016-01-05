package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.*;
import myfuel.request.CompanyReportRequest;

import myfuel.response.ComapnyReportsResponse;
import myfuel.response.booleanResponse;

public class showReportsActions extends GUIActions {

	private ShowReportGUI gui ; 
	
	public showReportsActions(MyFuelClient client) 
	{
		super(client);
		
		CompanyReportRequest request = new CompanyReportRequest();
		client.handleMessageFromGUI(request);
		gui = new ShowReportGUI(this);
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		if (arg1 instanceof ComapnyReportsResponse)
		{
			
			ComapnyReportsResponse r = (ComapnyReportsResponse)arg1;
			gui.setInventoryPanel(r.getqStationInventory());
			gui.setIncomePanel(r.getqStationIncome());
			gui.setPurchasePanel(r.getqStationPurchase());
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.showErrorMessage(((booleanResponse)arg1).getMsg());
		}

	}

	@Override
	public void backToMenu() 
	{
		changeFrame(gui,new CMActions(client),this);

	}

}
