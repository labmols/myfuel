package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.*;
import myfuel.gui.ConfirmationGUI;
import myfuel.request.ConfirmationRequest;
import myfuel.request.RequestEnum;
import myfuel.response.ConfirmationResponse;
import myfuel.response.booleanResponse;

public class ConfirmationActions extends GUIActions {

	private ConfirmationGUI gui ; 
	
	private ArrayList<Customer> customers;

	private ArrayList<Integer> approved;
	public ConfirmationActions(MyFuelClient client)
	{
		super(client);
		gui = new ConfirmationGUI(this);
		ConfirmationRequest request = new ConfirmationRequest(RequestEnum.Select);
		 client.handleMessageFromGUI(request);
		gui.setVisible(true);
		approved =new ArrayList<Integer>();
		
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
				if(customers.isEmpty())
				{
					gui.showMessage("There are no customers waiting for confirmation!");
					this.backToMenu();
				}
				else
					gui.setDetails(customers.size(), customers);
		
		}
		
		else if(arg1 instanceof booleanResponse)
		{
		    booleanResponse res  = ((booleanResponse)arg1);
			gui.showMessage(res.getMsg());
			if(res.getSuccess())
			{
			gui.updateTable(approved);
			for(Customer c: customers)
			{
				if(approved.contains(c.getUserid()))
					sendMail(c);
			}
			
			}
		}

	}
	
	private void sendMail(Customer c) {
		// TODO Auto-generated method stub
		String subject = "Welcome to MyFuel!";
		String content= "Now you can sign in with your login details: "
				+ "\n" + "UserID: " + c.getUserid() + 
				  "\n"   +"Password: " + c.getPass();
		
		SendMailTLS.sendMail(c.getEmail() , subject, content);
	}

	public void sendApproved(ArrayList<Integer> approved)
	{
		this.approved = approved;
		ConfirmationRequest request = new ConfirmationRequest(RequestEnum.Insert,approved);
		client.handleMessageFromGUI(request);
	}

	
	@Override
	public void backToMenu() {
	
			changeFrame(gui,new MDActions(client),this);
	}

}
