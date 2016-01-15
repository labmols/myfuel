package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;

import myfuel.client.MyFuelClient;
import myfuel.client.Promotion;
import myfuel.client.PromotionTemplate;
import myfuel.client.TimeIgnoringComparator;
import myfuel.gui.MakeaPromotionGUI;
import myfuel.request.LoginRequest;
import myfuel.request.MakeaPromotionRequest;
import myfuel.request.PromotionTemplateRequest;
import myfuel.response.MakeaPromotionResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

/***
 * This class will be uses as a controller for MakeaPromotionGUI
 * @author karmo
 *
 */
public class MakeAPromotionActions extends GUIActions {
	/***
	 * This class will be used as a controller for this GUI object
	 */
	MakeaPromotionGUI gui;
	/***
	 * Details about the requested data from the server 
	 */
	MakeaPromotionRequest request;
	/***
	 * Response Object from the server
	 */
	private Response response;
	/***
	 * MakeAPromotionActions Constructor
	 * @param client - MyFuelClient
	 */
	public MakeAPromotionActions(MyFuelClient client,LoginRequest lr) {
		super(client,lr);
		gui = new MakeaPromotionGUI(this);
	
		request = new MakeaPromotionRequest(0);
		gui.createWaitDialog("Getting Promotion Templates...");
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
		if(arg1 instanceof MakeaPromotionResponse)
		{
			gui.setWaitProgress();
			response = (MakeaPromotionResponse)arg1;
			
			
			
			for(PromotionTemplate p: ((MakeaPromotionResponse)response).getTemplates())
			{
				gui.addElementToModel(p.getName());
			}

		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.setWaitProgress();
			booleanResponse resp = (booleanResponse)arg1;
			
			if(resp.getSuccess())
				gui.showOKMessage("Promotion has been created!");
			else
				gui.showErrorMessage(resp.getMsg());
			
		}

	}
	/***
	 * Getting the details about a specific promotion template
	 * @param index - index of the template
	 * @return Promotion Template Details
	 */
	public PromotionTemplate getPromotion(int index)
	{
		return ((MakeaPromotionResponse)response).getTemplates().get(index);
	}
	
	/***
	 * Verifying that the input from the user is legal
	 * @param start - Start Date of the promotion
	 * @param end - end Date of the Promotion
	 */
	public void verifyDetails(Date start,Date end)
	{
		
		Date date = new Date();
		
		if(start == null || end == null)
			gui.showErrorMessage("You have to pick start date and end date");
		else
		{	
			if(end.before(start))
				gui.showErrorMessage("End time can't be before Start time");
			
			else if(new TimeIgnoringComparator().compare(start, date) < 0)
				gui.showErrorMessage("Illegal Start Time!");
			
			else
			{
				PromotionTemplate p =  gui.getP();
				request = new MakeaPromotionRequest(1,p.getTid(),start,end);
				gui.createWaitDialog("Creating Promotion...");
				client.handleMessageFromGUI(request);
			}
		}
		
		
	}

	@Override
	public void backToMenu() {
		new MMActions(client,lr);
		changeFrame(gui,this);
		
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}
	

	

}
