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
	
	public void verifyDetails(Date shipDate, String qty, String addr, boolean urgent)
	{
		boolean success = true;
		String msg = "";
		Date date = new Date();
		float qtyF;
		try {
			qtyF = Float.parseFloat(qty);
		}
		catch(NumberFormatException e)
		{
			success = false;
			msg += "Illegal Quantity value!\n";
		}
		
		if(addr.equals(""))
		{
			success = false;
			msg += "Address field is Empty!\n";
		}
		if(shipDate == null )
		{
			success = false;
			msg += "You have to pick ship date!\n";
		}
		else if(shipDate.before(date))
		{
			success = false;
			 msg += "Illegal Date!\n";
		}
		
		if(!success) gui.showErrorMessage(msg);
		else { // new request
			
		}
			
	}


}
