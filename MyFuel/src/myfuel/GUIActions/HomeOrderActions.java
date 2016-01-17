package myfuel.GUIActions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.swing.JOptionPane;

import myfuel.Entity.Fuel;
import myfuel.Entity.FuelQty;
import myfuel.Entity.HomeOrder;
import myfuel.Entity.Purchase;
import myfuel.Entity.StationInventory;
import myfuel.Tools.CalcPrice;
import myfuel.client.MyFuelClient;
import myfuel.gui.HomeFuelGUI;
import myfuel.request.FuelOrderRequest;
import myfuel.request.LoginRequest;
import myfuel.request.RequestEnum;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.FuelOrderResponse;
import myfuel.response.booleanResponse;

/**
 * Home Fuel GUI Controller.
 * @author Maor
 *
 */
public class HomeOrderActions extends GUIActions {
	/**
	 * Home Fuel GUI object(JFrame).
	 */
	private HomeFuelGUI gui;
	
	/**
	 * Customer details object.
	 */
	private CustomerLoginResponse LoginRes;
	
	/**
	 *The home fuel order details
	 */
	private HomeOrder order;

	/**
	 * The response from server (include the fuel inventory and price).
	 */
	private FuelOrderResponse response; 
	
	/**
	 * Create new Home Fuel GUI Controller.
	 * @param client - MyFuelClient object.
	 * @param customer - Customer details object.
	 * @param fuels 
	 */
	public HomeOrderActions(MyFuelClient client, CustomerLoginResponse res,LoginRequest lr) {
		super(client,lr);
		this.gui = new HomeFuelGUI(this);
		this.LoginRes= res;
		gui.setVisible(true);
		getInfo();
        gui.getOrderPanel().setAddress(res.getUser().getAddress());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Make new Home Fuel order request and sent it to the server.
	 * @param shipDate - Chosen Shipping date.
	 * @param qty - Order Quantity(Liters).
	 * @param addr - Customer address.
	 * @param urgent - Is the order urgent or not.
	 */
	private void MakeHomeFuelRequest(Date shipDate, float qty, String addr, boolean urgent)
	{
		
		if(checkInventory(qty))
		{
			float totalPrice = CalcPrice.calcTotalHomeOrder(urgent, qty,response.getFuels().get(3).getMaxPrice(), response.getPromotion(Fuel.HomeFuelID));
			if(showConfirmOrder(qty, urgent,totalPrice))
			{
				Date pdate = new Date();
				int pid ;
				if(response.getPromotion(Fuel.HomeFuelID) != null)
					pid = response.getPromotion(Fuel.HomeFuelID).getPid();
				else pid = -1;
				if(urgent) 
					shipDate = pdate;
				
				Purchase p = new Purchase (LoginRes.getUser().getUserid(),0, -1, Fuel.HomeFuelID, pid ,pdate , totalPrice, qty,null,-1,true);
				order = new HomeOrder(LoginRes.getUser().getUserid(), 0, addr, shipDate, false, urgent,p);
				gui.createWaitDialog("Sending your order...");
				FuelOrderRequest req = new FuelOrderRequest (RequestEnum.Insert,p,order);
				client.handleMessageFromGUI(req);
			
		  }
		
		}
		else
		{
			gui.showErrorMessage("Not enough fuel quantity!");
		}
	}
	
	/**
	 * notified by the client when response received from the server
	 * show message to the user if the new order create succeed or not.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof FuelOrderResponse)
		{
			FuelOrderResponse res = (FuelOrderResponse) arg;
			this.response = res;
			gui.setWaitProgress();
			 gui.getTrackingPanel().updateTable(response.getHorders());
			
		}
		else if(arg instanceof booleanResponse)
		{
			booleanResponse res = (booleanResponse) arg;
			gui.setWaitProgress();
			if(res.getSuccess()) 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); 
				gui.showOKMessage("Your order success! \n" +
									"You will recieve your order in " + sdf.format(order.getShipDate())
									+ "\n Thank you for choosing MyFuel!");
				backToMenu();
			}
			else gui.showErrorMessage(res.getMsg());
		}
	}
	
	/**
	 * Check if there is enough fuel inventory for the order(compared to the value from DB).
	 * @param qty - The order quantity.
	 * @return
	 */
	private boolean checkInventory(float qty) {
		
		FuelQty f = response.getHomeInventory();
		if(qty > f.getQty()) return false;
		else return true;
		
	}

	/**
	 * Get all the required info from DB. 
	 * Including the current home fuel inventory and current promotions(if exist).
	 */
	private void getInfo ()
	{
		gui.createWaitDialog("Getting your orders Details...");
		FuelOrderRequest req = new FuelOrderRequest(RequestEnum.Select, Fuel.HomeFuelID, LoginRes.getUser().getUserid());
		client.handleMessageFromGUI(req);
		
	}
	

	
	/**
	 * Confirm Order JOptionPane message, Show total order details , including promotion info , total price and quantity.
	 * @param qty - Quantity (Liter).
	 * @param urgent - Is the order is urgent or not.
	 * @return
	 */
	private boolean showConfirmOrder(float qty, boolean urgent, float totalPrice)
	{
		// TODO Auto-generated method stub
		String total = new DecimalFormat("##.##").format(totalPrice);
		String liter =new DecimalFormat("##.##").format(totalPrice/qty);
		String promotion;
		if(response.getPromotion(Fuel.HomeFuelID) == null)
		 promotion = "Promotion: No Promotion";
		else
		promotion = "Promotion : Discount of " +response.getPromotion(Fuel.HomeFuelID).getDiscount() +"% from total order";
		String message = "" + promotion 
				+"\n\n Total Order Price : " + total+ " NIS"
				+ "\n\nPrice for liter : " + liter +" NIS"
				+ "\n\n Charge Credit Card no : " +LoginRes.getUser().getCnumber() +
				 "\n\n Do you approve this order?";
	    String title = "Confirm Order";
	    // display the JOptionPane showConfirmDialog
	    int reply = JOptionPane.showConfirmDialog(gui, message, title, JOptionPane.YES_NO_OPTION);
	    
	    if (reply == JOptionPane.YES_OPTION) return true;
	    else return false;
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
		changeFrame(gui, this);
		new CustomerOptionsActions(client, LoginRes,lr);
		 
	}
	
	
	/**
	 * Verify Home Fuel order user input.
	 * @param shipDate - Shipping date.
	 * @param qty - Fuel quantity (Liters)
	 * @param addr - Customer address.
	 * @param urgent - Is this order urgent or not.
	 */
	public void verifyDetails(Date shipDate, String qty, String addr, boolean urgent)
	{
		boolean success = true;
		String msg = "";
		Date date = new Date(); //Current date
		float qtyF = 0;
		try {
			qtyF = Float.parseFloat(qty);
			if(qtyF<0)
			{
				success = false;
				msg += "Illegal Amount value!\n";
			}
		}
		catch(NumberFormatException e)
		{
			success = false;
			msg += "Illegal Amount value!\n";
		}
		
		if(addr.equals(""))
		{
			success = false;
			msg += "Address field is Empty!\n";
		}
		if(shipDate == null && !urgent )
		{
			success = false;
			msg += "You have to pick ship date!\n";
		}
		else if(!urgent && shipDate.before(date))
		{
			success = false;
			 msg += "Illegal Date!\n";
		}
		
		if(!success) gui.showErrorMessage(msg);
		else if(response != null){ // new request

		    	MakeHomeFuelRequest(shipDate, qtyF, addr, urgent);
		}
			
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}

	


}
