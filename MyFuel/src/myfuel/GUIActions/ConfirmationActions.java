package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.*;
import myfuel.gui.ConfirmationGUI;
import myfuel.request.ConfirmationRequest;
import myfuel.response.ConfirmationResponse;

public class ConfirmationActions extends GUIActions {

	private ConfirmationGUI gui ; 
	private ConfirmationRequest request;
	private ArrayList<Customer> customers;
	public ConfirmationActions(MyFuelClient client)
	{
		super(client);
		gui = new ConfirmationGUI(this);
		 request = new ConfirmationRequest(1);
		 client.handleMessageFromGUI(request);
		gui.setVisible(true);
		
	}
	
	/***
	 * handle message from the server
	 */

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof ConfirmationResponse)
		{
				customers = ((ConfirmationResponse)arg1).getCustomers();
				gui.setDetails(customers.size(), customers);
			/*	for(Customer c : customers)
				{
					System.out.println(c.getUserid() +" "+ c.getFname());
				}*/
		}

	}

	
	@Override
	public void backToMenu() {
	
			changeFrame(gui,new MDActions(client),this);
	}

}
