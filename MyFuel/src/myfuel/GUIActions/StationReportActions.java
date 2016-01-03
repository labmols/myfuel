package myfuel.GUIActions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.client.ReportEnum;
import myfuel.gui.StationReportGUI;
import myfuel.request.SReportRequest;
import myfuel.response.SReportResponse;
import myfuel.response.booleanResponse;

public class StationReportActions extends GUIActions {

	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * Station Report GUI
	 */
	private StationReportGUI gui;
	
	
	public StationReportActions(MyFuelClient client,int sid) {
		super(client);
		this.sid  = sid;
		
		gui = new StationReportGUI(this);
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
			if(arg1 instanceof SReportResponse)
			{
				SReportResponse response = (SReportResponse)arg1;
				
				if(response.getReport_type() == ReportEnum.InventoryReport)
				{
					gui.setInventoryPanel(response.getInventory());
				}
				
				else if(response.getReport_type() == ReportEnum.PurchaseReport)
				{
					gui.setPurchasePanel(response.getP());
				}
				
				else if(response.getReport_type() == ReportEnum.IncomesReport)
				{
					gui.setIncomesPanel(response.getIncomes());
				}
			}
			
			else if(arg1 instanceof booleanResponse)
			{
				booleanResponse a = (booleanResponse)arg1;
				gui.showErrorMessage(a.getMsg());
				backToMenu();
			}

	}

	@Override
	public void backToMenu() {
		
			changeFrame(gui,new SMActions(client,sid),this);
	}



	public void getReport(int q, ReportEnum r) 
	{
		
		
		SReportRequest request = new SReportRequest(q,r,sid);
		client.handleMessageFromGUI(request);
		
	}

}
