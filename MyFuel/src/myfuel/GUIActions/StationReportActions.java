package myfuel.GUIActions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.MessageForManager;
import myfuel.GUI.StationReportGUI;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Request.SReportRequest;
import myfuel.Response.SReportResponse;
import myfuel.Response.booleanResponse;
import myfuel.Tools.ReportEnum;
import myfuel.Tools.TimeIgnoringComparator;

/***
 * Controller for StationReportGUI
 * @author karmo
 *
 */
public class StationReportActions extends GUIActions {

	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * Station Report GUI
	 */
	private StationReportGUI gui;
	/***
	 * Message for the manager
	 */
	private ArrayList<MessageForManager> msg;
	
	/***
	 * Network ID
	 */
	private int nid;
	/***
	 * Station Name
	 */
	private String StationName;
	/***
	 * StationReportActions Constructor
	 * @param client - MyFuelClient
	 * @param sid - Station ID
	 * @param msg - Messages
	 */
	public StationReportActions(MyFuelClient client,int sid , ArrayList<MessageForManager> msg,int nid,LoginRequest lr,String StationName) {
		super(client,lr);
		this.sid  = sid;
		this.msg = msg;
		this.nid = nid;
		this.StationName = StationName;
		gui = new StationReportGUI(this,StationName);
		gui.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
			if(arg1 instanceof SReportResponse)
			{
				SReportResponse response = (SReportResponse)arg1;
				gui.setWaitProgress();
				
					gui.setInventoryPanel(response.getInventory());			
					gui.setPurchasePanel(response.getP());	
					gui.setIncomesPanel(response.getIncomes());
				
			}
			
			else if(arg1 instanceof booleanResponse)
			{
				gui.setWaitProgress();
				booleanResponse a = (booleanResponse)arg1;
				
				if(a.getSuccess())
					gui.showOKMessage("Reports has been saved");
				else
					gui.showErrorMessage(a.getMsg());
				
				backToMenu();
			}

	}

	@Override
	public void backToMenu() {
		
		
			changeFrame(gui,this);
			new SMActions(client,sid,msg,nid,lr,StationName);
			
	}


	/***
	 * This method will send a request to save the report details in the DB if possible
	 *  and will Show the details for the Station Manager
	 * @param q - Quarter
	 * @param r - Report Type
	 */
	public void setReport(int q) 
	{
		
		
		if(this.checkQuarter(q))
		{
			SReportRequest request = new SReportRequest(q,RequestEnum.Insert,sid,nid);
			gui.createWaitDialog("Saving Reports Details...");
			client.handleMessageFromGUI(request);
		}
		else
			gui.showErrorMessage("You can not send a report for this quarter!");
		
	}
	
	/***
	 * This method will get all reports details for this quarter to this station
	 * @param q - quarter selected
	 */
	public void showReport(int q)
	{
		if(this.getQuarter() >= q )
		{
		SReportRequest request = new SReportRequest(q,RequestEnum.Select,sid,nid);
		gui.createWaitDialog("Getting Reports Details...");
		client.handleMessageFromGUI(request);
		}
		
		else
		{
			gui.showErrorMessage("You can not check this quarter");
		}
		
	}
	
	/***
	 * This method will check if the Quarter that the User choose is the current Quarter
	 * @param q - Quarter picked by the user
	 * @return true is this is the current Quarter and false otherwise
	 */
	boolean checkQuarter(int q)
	{
		Date sdate = null;
		Date fdate = null;
		Calendar cal = Calendar.getInstance();
			 switch(q)
			  {
			 	case 1:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JANUARY,1);
			 		sdate = new Date( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.MARCH,31);
			 		fdate =  new Date( cal.getTime().getTime());
			 		break;
			 	
			 	case 2:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.APRIL,1);
			 		sdate = new Date( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JUNE,31);
			 		fdate =  new Date( cal.getTime().getTime());
			 		break;
			 	
			 	case 3:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JULY,1);
			 		sdate = new Date( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.SEPTEMBER,31);
			 		fdate =  new Date( cal.getTime().getTime());
			 		break;
			 	
			 	case 4: 
			 		cal.set(cal.get(Calendar.YEAR),Calendar.OCTOBER,1);
			 		sdate = new Date (cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.DECEMBER,31);
			 		fdate =  new Date( cal.getTime().getTime());
			 		break;
			 			
			 }
			 
			return( new TimeIgnoringComparator().compare(new Date(), sdate) >= 0 && new TimeIgnoringComparator().compare(new Date(), fdate) <=0 ); 
			 
			 
	}
	
	/***
	 * This method calculate the current Quarter number
	 * @return the current quarter
	 */
	private int getQuarter()
	{
		Date s = null;
		Date f = null;
		Calendar c = Calendar.getInstance();
		
		c.set(c.get(Calendar.YEAR),Calendar.JANUARY,1);
 		s = new Date( c.getTime().getTime());
 		c.set(c.get(Calendar.YEAR),Calendar.MARCH,31);
 		f =  new Date( c.getTime().getTime());
		if(new TimeIgnoringComparator().compare(new java.util.Date(), s) >= 0 && new TimeIgnoringComparator().compare(new java.util.Date(), f) <=0 )
			return 1 ;
		
		c.set(c.get(Calendar.YEAR),Calendar.APRIL,1);
 		s = new Date( c.getTime().getTime());
 		c.set(c.get(Calendar.YEAR),Calendar.JUNE,31);
 		f =  new Date( c.getTime().getTime());
 		
		if(new TimeIgnoringComparator().compare(new java.util.Date(), s) >= 0 && new TimeIgnoringComparator().compare(new java.util.Date(), f) <=0)
		return 2;
		
		c.set(c.get(Calendar.YEAR),Calendar.JULY,1);
 		s = new Date( c.getTime().getTime());
 		c.set(c.get(Calendar.YEAR),Calendar.SEPTEMBER,31);
 		f =  new Date( c.getTime().getTime());
 		
		if(new TimeIgnoringComparator().compare(new java.util.Date(), s) >= 0 && new TimeIgnoringComparator().compare(new java.util.Date(), f) <=0)
			return 3;
		
		else
			return 4;
	
	
	}
	
	
	
	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
