package myfuel.GUIActions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.swing.JOptionPane;

import myfuel.client.CalcPrice;
import myfuel.client.Customer;
import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.client.HomeOrder;
import myfuel.client.MyFuelClient;
import myfuel.client.PromotionTemplate;
import myfuel.client.Purchase;
import myfuel.gui.HomeOrderGUI;
import myfuel.request.FuelInfoRequest;
import myfuel.request.HomeOrderRequest;
import myfuel.request.MakeaPromotionRequest;
import myfuel.request.RequestEnum;
import myfuel.response.FuelInfoResponse;
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
	private HomeOrderGUI gui;
	
	/**
	 * Customer details object.
	 */
	private Customer customer;
	
	private HomeOrder order;
	private FuelInfoResponse response; 
	
	/**
	 * Create new Home Fuel GUI Controller.
	 * @param client - MyFuelClient object.
	 * @param customer - Customer details object.
	 * @param fuels 
	 */
	public HomeOrderActions(MyFuelClient client, Customer customer) {
		super(client);
		this.gui = new HomeOrderGUI(this);
		this.customer= customer;
		getInfo();
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
		
		if(arg instanceof FuelInfoResponse)
		{
			FuelInfoResponse res = (FuelInfoResponse) arg;
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
			}
			else gui.showErrorMessage(res.getMsg());
		}
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
		FuelInfoRequest req = new FuelInfoRequest(RequestEnum.Select, 4);
		client.handleMessageFromGUI(req);
		
	}
	private void MakeHomeFuelRequest(Date shipDate, float qty, String addr, boolean urgent)
	{
		
		Date pdate = new Date();
		if(urgent) shipDate = pdate;
		float bill = CalcPrice.calcTotalHomeOrder(urgent, qty,response.getFuels().get(3).getMaxPrice(), response.getProm());
		Purchase p = new Purchase (customer.getUserid(),0, 4, 4, -1,pdate , bill, qty);
		order = new HomeOrder(customer.getUserid(), 0, qty , addr, shipDate, false, urgent);
		
		HomeOrderRequest req = new HomeOrderRequest(p, order,RequestEnum.Insert);
		
		client.handleMessageFromGUI(req);
	}

	@Override
	public void backToMenu() {
		// TODO Auto-generated method stub
		
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
		else { // new request
			float totalPrice = CalcPrice.calcTotalHomeOrder(urgent,qtyF,4, null);
			String total = new DecimalFormat("##.##").format(totalPrice);
			String liter =new DecimalFormat("##.##").format(totalPrice/qtyF);
			String message = "Price for liter : " + liter +" ₪"
					+"\n\n Total Order Price : " + total+ " ₪"
					+ "\n\n Charge Credit Card no : " + customer.getCnumber() +
					 "\n\n Do you approve this order?";
		    String title = "Confirm Order";
		    // display the JOptionPane showConfirmDialog
		    int reply = JOptionPane.showConfirmDialog(gui, message, title, JOptionPane.YES_NO_OPTION);
		    
		    if (reply == JOptionPane.YES_OPTION)
		    	MakeHomeFuelRequest(shipDate, qtyF, addr, urgent);
		}
			
	}


}
