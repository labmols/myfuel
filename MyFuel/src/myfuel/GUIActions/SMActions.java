package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.FuelQty;
import myfuel.client.MessageForManager;
import myfuel.client.MyFuelClient;
import myfuel.gui.SMGUI;

/***
 * Controller for SMGUI
 * @author karmo
 *
 */
public class SMActions extends GUIActions {
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * This class will be a controller to this GUI
	 */
	private SMGUI gui;
	/***
	 * Will contain the messages for this manager 
	 */
	private ArrayList<MessageForManager> msg;
	
	/***
	 * Network ID
	 */
	
	private int nid;
	
	/***
	 * SMActions Constructor
	 * @param client - MyFuelClient
 	 * @param sid - Station ID
	 * @param msg  - Messages for this manager
	 */
	public SMActions(MyFuelClient client,int sid,ArrayList<MessageForManager> msg,int nid) {
		super(client);
		this.setSid(sid);
		this.msg = msg;
		this.nid = nid;
		gui = new SMGUI(this,msg);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	@Override
	public void backToMenu() {
		new LoginActions(client);
		changeFrame(gui,this);
		

	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	/***
	 * This method will create The Inventory Order Controller
	 */
	public void CreateCheckInventoryWindow() 
	{
		new CheckInventoryActions(client,sid,msg,nid);
		changeFrame(gui,this);
		
		
	}
	
	/***
	 * This method will create The Low Inventory Level Controller
	 */
	
	public void CreateLowInventoryWindow()
	{
		new LowInventoryActions(client,sid,msg,nid);
		changeFrame(gui,this);
		
	}
	
	/***
	 * This method will create The Report Controller
	 */

	public void CreateReports() 
	{
		new StationReportActions(client,sid,msg,nid);
		changeFrame(gui,this);
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		
	}

}
