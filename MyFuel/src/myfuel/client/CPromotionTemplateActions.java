package myfuel.client;

import java.util.Observable;

import myfuel.gui.*;
import myfuel.server.booleanResponse;

/***
 *  this class will has the actions for the CreatePromotionTemplateGUI class
 * 
 *
 */
public class CPromotionTemplateActions extends GUIActions{
	
	CreatePromotionTemplateGUI gui ; 
	PromotionTemplateRequest rq;
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
	public void PromotionTemplate(Promotion p)
	{
		 rq  = new PromotionTemplateRequest(p);
		client.handleMessageFromGUI(rq);
	}
	
/***
 *  will receive a response from the server
 *  if CreatePromotionTemplateGUI is active than the response will be check
 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(gui.isActive())
		{
			booleanResponse response = (booleanResponse)arg1;
			if(response.success == true)
				gui.showMessage("Promotion Template " +"\""+rq.p.name+"\""+" Created!");
			else
				gui.showMessage("Creation failed");
		}
		
	}
	
	public void verifyDetails(Promotion p)
	{
		boolean result = true;
		
		if(p.discount <= 0 || p.discount > 100)
			result = false;
		if(p.startTime.before(p.endTime))
			result = false;
		
		if(result)
			PromotionTemplate(p);
		else
			gui.showMessage("Illegal input");
	}

}
