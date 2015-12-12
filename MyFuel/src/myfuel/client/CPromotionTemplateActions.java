package myfuel.client;

import java.util.Observable;

import myfuel.gui.*;


public class CPromotionTemplateActions extends GUIActions{
	
	CreatePromotionTemplateGUI gui ; 
	
	public CPromotionTemplateActions(MyFuelClient client) {
		super(client);
		gui = new CreatePromotionTemplateGUI(this);
		
		gui.setVisible(true);
		
	}
	/***
	 * create request object 
	 * @param p - contains the deatils of the new promotion template
	 */
	public void PromotionTemplate(Promotion p)
	{
		PromotionTemplateRequest rq  = new PromotionTemplateRequest(p);
		client.handleMessageFromGUI(rq);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
