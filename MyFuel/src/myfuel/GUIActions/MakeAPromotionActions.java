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
import myfuel.request.MakeaPromotionRequest;
import myfuel.request.PromotionTemplateRequest;
import myfuel.response.MakeaPromotionResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

public class MakeAPromotionActions extends GUIActions {
	MakeaPromotionGUI gui;
	MakeaPromotionRequest request;
	private Response response;
	public MakeAPromotionActions(MyFuelClient client) {
		super(client);
		gui = new MakeaPromotionGUI(this);
	
		request = new MakeaPromotionRequest(0);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
		if(arg1 instanceof MakeaPromotionResponse)
		{
			response = (MakeaPromotionResponse)arg1;
			
			
			
			for(PromotionTemplate p: ((MakeaPromotionResponse)response).getTemplates())
			{
				gui.addElementToModel(p.getName());
			}

		}
		
		else if(arg1 instanceof booleanResponse)
		{
			booleanResponse resp = (booleanResponse)arg1;
			
			if(resp.getSuccess())
				gui.showOKMessage("Promotion has been created!");
			else
				gui.showErrorMessage(resp.getMsg());
			
		}

	}
	
	public PromotionTemplate getPromotion(int index)
	{
		return ((MakeaPromotionResponse)response).getTemplates().get(index);
	}
	
	
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
				client.handleMessageFromGUI(request);
			}
		}
		
		
	}

	@Override
	public void backToMenu() {
		changeFrame(gui,new MMActions(client),this);
		
	}
	

	

}
