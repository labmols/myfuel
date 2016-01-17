package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import myfuel.Client.*;
import myfuel.Entity.Customer;
import myfuel.GUI.ConfirmationGUI;
import myfuel.Request.ConfirmationRequest;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.ConfirmationResponse;
import myfuel.Response.booleanResponse;
import myfuel.Tools.MailThread;

/***
 * This Class will be a controller for the ConfirmationGUI
 * @author karmo
 *
 */
public class ConfirmationActions extends GUIActions {

	/***
	 * This class will be a controller for this GUI
	 */
	private ConfirmationGUI gui ; 
	
	/***
	 * Customer Details
	 */
	private ArrayList<Customer> customers;

	/***
	 * Approved Customers ID's
	 */
	private ArrayList<Integer> approved;
	/***
	 * ConfirmationActions Constructor
	 * @param client - MyFuelCLient
	 */
	public ConfirmationActions(MyFuelClient client,LoginRequest lr)
	{
		super(client,lr);
		gui = new ConfirmationGUI(this);
		ConfirmationRequest request = new ConfirmationRequest(RequestEnum.Select);
		gui.createWaitDialog("Getting Customer Details...");
		 client.handleMessageFromGUI(request);
		gui.setVisible(true);
		approved =new ArrayList<Integer>();
		
	}
	


	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof ConfirmationResponse)
		{
				customers = ((ConfirmationResponse)arg1).getCustomers();
				
				gui.setWaitProgress();
				
				if(customers.isEmpty())
				{
					gui.showErrorMessage("There are no customers waiting for confirmation!");
					this.backToMenu();
				}
				else
					gui.setDetails(customers.size(), customers);
		
		}
		
		else if(arg1 instanceof booleanResponse)
		{
			gui.setWaitProgress();
		    booleanResponse res  = ((booleanResponse)arg1);
			if(res.getSuccess())
			{
			gui.updateTable(approved);
			for(Customer c: customers)
			{
				if(approved.contains(c.getUserid()))
				sendMail(c);
			}
			
			gui.showOKMessage("Customers approved successfully and confirmation mails have beent sent!");
			//backToMenu();
			}
			else gui.showErrorMessage(res.getMsg());
		}

	}
	
	/***
	 * Sending Mails For approved Customers
	 * @param c - Approved Customer
	 */
	private void sendMail(Customer c) {
		// TODO Auto-generated method stub
		String subject = "Welcome to MyFuel!";
		String content= "Dear " + c.getFname() + ",\n"
				+ "Now you can sign in with the following login details: "
				+ "\n\n" + "UserID: " + c.getUserid() + 
				  "\n"   +"Password: " + c.getPass() 
				+ "\n\n\n" +"Marketing Department , MyFuel.";
		
		MailThread mailT = new MailThread(c.getEmail(),subject,content);
		new Thread(mailT).start();
	}

	/***
	 * Sending the Approved Customers to the DB
	 * @param approved - approved Customers ID's
	 */
	public void sendApproved(ArrayList<Integer> approved)
	{
		this.approved = approved;
		ConfirmationRequest request = new ConfirmationRequest(RequestEnum.Insert,approved);
		gui.createWaitDialog("Updating Details...");
		client.handleMessageFromGUI(request);
	}

	
	@Override
	public void backToMenu() {
	
		
			changeFrame(gui,this);
			new MDActions(client,lr);
			
	}



	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

}
