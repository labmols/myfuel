package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MakeaPromotionGUI;
import myfuel.request.MakeaPromotionRequest;
import myfuel.request.PromotionTemplateRequest;

public class MakeAPromotionActions extends GUIActions {
	MakeaPromotionGUI gui;
	MakeaPromotionRequest request;
	
	public MakeAPromotionActions(MyFuelClient client) {
		super(client);
		gui = new MakeaPromotionGUI(this);
		/****
		 * getting the Templates from the DB
		 */
		request = new MakeaPromotionRequest(0);
		client.handleMessageFromGUI(request);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
