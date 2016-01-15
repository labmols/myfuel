package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.FuelQty;
import myfuel.client.MessageForManager;
import myfuel.client.MyFuelClient;
import myfuel.gui.CMGUI;
import myfuel.request.LoginRequest;

/***
 * Network Manager GUI Controller
 * @author karmo
 *
 */
public class CMActions extends GUIActions {
	/***
	 * This class will be a controller for this GUI
	 */
	private CMGUI gui ;
	/****
	 * This arrayList will contain any messages left for this manager
	 */
	private ArrayList<MessageForManager> msg;
	/***
	 * Network ID
	 */
	private int nid;
	/***
	 * CMActions Constructor
	 * @param client - MyFuelClient
	 * @param msg   - Messages
	 */
	public CMActions(MyFuelClient client,ArrayList<MessageForManager> msg, int nid,LoginRequest lr) {
		super(client,lr);
		this.msg = msg;
		this.nid = nid;
		gui = new CMGUI(this,msg);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		

	}

	/***
	 * Creates New Rates Confirmation Window
	 */
	public void ConfirmNewRatesWindow() 
	{
		new ConfirmNewRatesActions(client,msg,nid,lr);
		changeFrame(gui,this);	
		
	}

	@Override
	public void backToMenu() {
		
		
	}

	

	/***
	 * Creates Network Reports Menu
	 */
	public void showReportsWindows() 
	{
		new showReportsActions(client,msg,nid,lr);
		changeFrame(gui,this);	
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
