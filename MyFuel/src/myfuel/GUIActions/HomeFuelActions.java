package myfuel.GUIActions;

import java.util.Date;
import java.util.Observable;

import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.PromotionTemplate;
import myfuel.gui.HomeFuelGUI;
import myfuel.request.MakeaPromotionRequest;

public class HomeFuelActions extends GUIActions {
	
	HomeFuelGUI gui;
	Customer customer;
	public HomeFuelActions(MyFuelClient client, Customer customer) {
		super(client);
		this.gui = new HomeFuelGUI(this);
		this.customer= customer;
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
	}
	
	public void verifyDetails(Date shipDate)
	{
		
		Date date = new Date();
		
		if(shipDate == null )
			gui.showMessage("You have to pick ship date!");
		else if(shipDate.before(date))
				gui.showMessage("Illegal Date!");
		else { // new request
			
		}
			
	}


}
