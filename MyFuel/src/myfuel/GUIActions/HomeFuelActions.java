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
		this.customer= customer;
		this.gui = new HomeFuelGUI(this);
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
	
	public void verifyDetails(Date shipDate, String qty, String shipAddr, boolean isUrgent)
	{
		String msg = "";
		boolean success= true;
		Date date = new Date();
		Float qtyF;
		
		
		try {
			qtyF = Float.parseFloat(qty);
			
			
		} catch(NumberFormatException e)
		{
			success = false;
			msg += "Quantity Value is illegal or Empty. \n";
		}
		
		if(shipAddr.equals(""))
		{
			success = false;
			msg += "Shipping Address is Empty.\n";
		}
		if(shipDate == null ){
			msg+="You have to pick ship date.\n";
			success = false;
		}
		else if(shipDate.before(date)){
				msg+= "Illegal Date.\n";
				success = false;
		}
		else { // new request
			
		}
		
		if(!success) gui.showMessage(msg);
			
	}
	
	public String getAddress()
	{
		return customer.getAddress();
	}


}
