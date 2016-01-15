package myfuel.GUIActions;


import java.util.Date;
import java.util.Observable;


import myfuel.client.*;
import myfuel.gui.*;
import myfuel.request.PromotionTemplateRequest;
import myfuel.response.booleanResponse;

/***
 *  this class will has the actions for the CreatePromotionTemplateGUI class
 * 
 *
 */
public class CPromotionTemplateActions extends GUIActions{
	/***
	 * The GUI the this controller will control
	 */
	private CreatePromotionTemplateGUI gui ; 
	/***
	 * This request will be sent to the Server
	 */
	private PromotionTemplateRequest rq;
	/***
	 * Constructor for CPromotionTemplateActions class
	 * @param client - MyFuelClient
	 */
	public CPromotionTemplateActions(MyFuelClient client) {
		super(client);
		gui = new CreatePromotionTemplateGUI(this);
		
		gui.setVisible(true);
		
	}
	/***
	 * create request object 
	 * and send it to the server via the client's socket
	 * @param p - contains the deatils of the new promotion template
	 */
	public void PromotionTemplate(PromotionTemplate p)
	{
		 rq  = new PromotionTemplateRequest(p);
		 gui.createWaitDialog("Saving Promotion Template ...");
		client.handleMessageFromGUI(rq);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if( arg1 instanceof booleanResponse)
		{
			booleanResponse response = (booleanResponse)arg1;
			gui.setWaitProgress();
			if(response.getSuccess() == true)
				gui.showOKMessage("Promotion Template " +"\""+rq.getP().getName()+"\""+" Created!");
			else
				gui.showErrorMessage(response.getMsg());
		}
		
	}
	
	/***
	 * Verify the promotion template details
	 * @param p - promotion template
	 */
	public void verifyDetails(PromotionTemplate p)
	{
		boolean result = true;
		if(p.getDiscount() <= 0 || p.getDiscount() > 100)
		{
			gui.showErrorMessage("Input Error! Discount values is between 1-99.");
			result = false;
		}
		
	//	System.out.println(p.getStartTime());
	//	System.out.println(p.getEndTime());
		
	
		if(new DateIgnoreComparator().compare(p.getStartTime(), p.getEndTime()) > 0)
		{
			
			gui.showErrorMessage("Input Error! End time is eariler then the Start time.");
			result = false;
		}
		
		else if(new DateIgnoreComparator().compare(p.getStartTime(), p.getEndTime()) == 0)
		{
			gui.showErrorMessage("Input Error! End time can't be equal to Start time.");
			result = false;
		}
		
		if(result)
			PromotionTemplate(p);
	}
	
	@Override
	public void backToMenu() {
				new MDActions(client);
				changeFrame(gui,this);
				
		
	}
	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		
	}
	
}
