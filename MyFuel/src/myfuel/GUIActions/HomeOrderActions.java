package myfuel.GUIActions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import myfuel.client.CalcPrice;
import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.client.HomeOrder;
import myfuel.client.MyFuelClient;
import myfuel.client.PromotionTemplate;
import myfuel.client.Purchase;
import myfuel.gui.HomeFuelGUI;
import myfuel.request.FuelOrderRequest;
import myfuel.request.MakeaPromotionRequest;
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
	private CustomerLoginResponse res;
	
	/**
	 *The home fuel order details
	 */
	private HomeOrder order;
	Timer timer;
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
	public HomeOrderActions(MyFuelClient client, CustomerLoginResponse res) {
		super(client);
		this.gui = new HomeFuelGUI(this);
		this.res= res;
		getInfo();
		
		  timer = new Timer(10000, new ActionListener() { // Get info from DB every 10 seconds
              @Override
              public void actionPerformed(ActionEvent e) {
                if(client.isConnected())getInfo();
                else timer.stop();
                System.out.println("time");
               
              }
          });
          timer.setRepeats(true);
          timer.setCoalesce(true);
          timer.setInitialDelay(0);
          timer.start();
        gui.getOrderPanel().setAddress(res.getUser().getAddress());
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
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
		}
		else if(arg instanceof booleanResponse)
		{
			booleanResponse res = (booleanResponse) arg;
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
	
	private boolean checkInventory(float qty) {
		ArrayList<FuelQty> f= response.getSi().get(3).getfQty();
		if(qty > f.get(0).getQty())
			return false;
		
		return true;
		
	}

	/**
	 * 
	 * @param shipDate
	 * @param qty
	 * @param addr
	 * @param urgent
	 */
	
	private void getInfo ()
	{
		FuelOrderRequest req = new FuelOrderRequest(RequestEnum.Select, Fuel.HomeFuelID);
		client.handleMessageFromGUI(req);
		
	}
	private void MakeHomeFuelRequest(Date shipDate, float qty, String addr, boolean urgent)
	{
		
		if(checkInventory(qty))
		{
			if(showConfirmOrder(qty, urgent))
			{
				Date pdate = new Date();
				int pid ;
				if(response.getProm() != null)
					pid = response.getProm().getPid();
				else pid = -1;
				if(urgent) shipDate = pdate;
				float bill = CalcPrice.calcTotalHomeOrder(urgent, qty,response.getFuels().get(3).getMaxPrice(), response.getProm());
				Purchase p = new Purchase (res.getUser().getUserid(),0, Fuel.HomeFuelID, Fuel.HomeFuelID, pid ,pdate , bill, qty);
				order = new HomeOrder(res.getUser().getUserid(), 0, qty , addr, shipDate, false, urgent);

				FuelOrderRequest req = new FuelOrderRequest (RequestEnum.Insert, qty,4,p,order);
				client.handleMessageFromGUI(req);
			
		  }
		
		}
		else
		{
			gui.showErrorMessage("Not enough fuel quantity!");
		}
	}

	private boolean showConfirmOrder(float qty, boolean urgent)
	{
		// TODO Auto-generated method stub
		float totalPrice = CalcPrice.calcTotalHomeOrder(urgent,qty,response.getFuels().get(3).getMaxPrice(), response.getProm());
		String total = new DecimalFormat("##.##").format(totalPrice);
		String liter =new DecimalFormat("##.##").format(totalPrice/qty);
		String promotion;
		if(response.getProm() == null)
		 promotion = "Promotion: no Promotion";
		else
		promotion = "Promotion : Discount of " +response.getProm().getDiscount() +"% from total order";
		String message = "" + promotion 
				+ "\n\nPrice for liter : " + liter +" NIS"
				+"\n\n Total Order Price : " + total+ " NIS"
				+ "\n\n Charge Credit Card no : " +res.getUser().getCnumber() +
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
		timer.stop();
		changeFrame(gui, new CustomerOptionsActions(client, res), this);
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
		Date date = new Date();
		float qtyF = 0;
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

	public Timer getTimer() {
		// TODO Auto-generated method stub
		return this.timer;
	}
	
	


}
