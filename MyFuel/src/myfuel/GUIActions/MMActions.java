package myfuel.GUIActions;

import java.util.Observable;

import myfuel.Client.MyFuelClient;
import myfuel.GUI.MMGUI;
import myfuel.Request.LoginRequest;

/***
 * MMGUI Controller
 * @author karmo
 *
 */
public class MMActions extends GUIActions{

	/***
	 * This class will be used as a controller to this GUI object
	 */
	private MMGUI gui ;
	/***
	 * MMActions Constructor
	 * @param client - MyFuelCLient
	 * @param lr - LoginRequest with user details
	 */
	public MMActions(MyFuelClient client,LoginRequest lr) {
		super(client,lr);
		gui = new MMGUI(this);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	/***
	 * Creating New Rates Windows
	 */
	public void createSetNewRatesWindow()
	{
		new SetNewRatesActions(client,lr);
		this.changeFrame(gui, this);
		
	}
	
	/***
	 * Creating Promotion Making Window
	 */
	public void createMakeaPromotionWindow()
	{
		new MakeAPromotionActions(client,lr);
		this.changeFrame(gui, this);
		
	}
	
	/***
	 * Creating Station Report Menu
	 */
	public void createShowReportsWindow()
	{
		 new MMReportsActions(client,lr);
		this.changeFrame(gui,this);
		
	}

	@Override
	public void backToMenu() {
		
		
	}

	/***
	 * Create Analytic System Menu
	 */
	public void createaDetailsWindow() 
	{
	
		changeFrame(gui, this);
		new MMAnalyzedActions(client,lr) ;
		
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
