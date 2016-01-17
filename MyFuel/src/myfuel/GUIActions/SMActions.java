package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.FuelQty;
import myfuel.Entity.MessageForManager;
import myfuel.GUI.SMGUI;
import myfuel.Request.LoginRequest;

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
	 * Station Name that this Manager manage
	 */
	private String StationName;
	/***
	 * SMActions Constructor
	 * @param client - MyFuelClient
 	 * @param sid - Station ID
	 * @param msg  - Messages for this manager
	 */
	public SMActions(MyFuelClient client,int sid,ArrayList<MessageForManager> msg,int nid,LoginRequest lr,String StationName) {
		super(client,lr);
		this.setSid(sid);
		this.msg = msg;
		this.nid = nid;
		this.StationName = StationName;
		gui = new SMGUI(this,msg,this.StationName);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	@Override
	public void backToMenu() {
	

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
		new CheckInventoryActions(client,sid,msg,nid,lr,StationName);
		changeFrame(gui,this);
		
		
	}
	
	/***
	 * This method will create The Low Inventory Level Controller
	 */
	
	public void CreateLowInventoryWindow()
	{
	
		changeFrame(gui,this);
		new LowInventoryActions(client,sid,msg,nid,lr,StationName);
		
	}
	
	/***
	 * This method will create The Report Controller
	 */

	public void CreateReports() 
	{

		changeFrame(gui,this);
		new StationReportActions(client,sid,msg,nid,lr,StationName);
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
