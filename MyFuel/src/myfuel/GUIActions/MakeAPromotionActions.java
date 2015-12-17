package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;

import myfuel.client.MyFuelClient;
import myfuel.client.Promotion;
import myfuel.client.PromotionTemplate;
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
				gui.showMessage("Promotion has been created!");
			else
				gui.showMessage(resp.getMsg());
			
		}

	}
	
	public PromotionTemplate getPromotion(int index)
	{
		return ((MakeaPromotionResponse)response).getTemplates().get(index);
	}
	
	
	public void verifyDetails(Date start,Date end)
	{
		
		Date date = new Date();
		
		if(end.before(start))
			gui.showMessage("End time can't be before Start time");
		
		else if(start.before(date))
			gui.showMessage("Illegal Start Time!");
		
		else
		{
			PromotionTemplate p =  gui.getP();
			request = new MakeaPromotionRequest(1,p.getTid(),start,end);
			client.handleMessageFromGUI(request);
			
		}
		
	}

	@Override
	public void backToMenu() {
		changeFrame(gui,new MMActions(client),this);
		
	}
	

}
