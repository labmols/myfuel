package myfuel.GUIActions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.Fuel;
import myfuel.Entity.FuelQty;
import myfuel.Entity.HomeOrder;
import myfuel.Entity.Purchase;
import myfuel.Entity.StationInventory;
import myfuel.GUI.HomeFuelGUI;
import myfuel.Request.FuelOrderRequest;
import myfuel.Request.LoginRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.CustomerLoginResponse;
import myfuel.Response.FuelOrderResponse;
import myfuel.Response.booleanResponse;
import myfuel.Tools.CalcPrice;

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
	 * The request that will send to server.
	 */
	private FuelOrderRequest request;
	
	private String errorMsg ;
	
	public HomeOrderActions(CustomerLoginResponse res,FuelOrderResponse response) {
		super();
		this.LoginRes = res;
		this.request = null;
		this.response = response;
		this.gui = new HomeFuelGUI(this);
		gui.setVisible(false);
		gui.getTrackingPanel().updateTable(response.getHorders());
		// TODO Auto-generated constructor stub
	}
	
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
		this.request = null;
		gui.setVisible(true);
		getInfo();
        gui.getOrderPanel().setAddress(res.getUser().getAddress());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Create new home fuel order request
	 * @param shipDate - Chosen Shipping date.
	 * @param qty - Order Quantity(Liters).
	 * @param addr - Customer address.
	 * @param urgent - Is the order urgent or not.
	 * @param totalPrice - total order price
	 * @return
	 */
	public FuelOrderRequest createOrder(Date shipDate,Date pdate, float qty, String addr, boolean urgent,float totalPrice)
	{
		int pid ;
		
		if(checkInventory(qty))
		{
		if(response.getPromotion(Fuel.HomeFuelID) != null)
			pid = response.getPromotion(Fuel.HomeFuelID).getPid();
		else pid = -1;
		if(urgent) 
			shipDate = pdate;
		
		Purchase p = new Purchase (LoginRes.getUser().getUserid(),0, -1, Fuel.HomeFuelID, pid ,pdate , totalPrice, qty,null,-1,true);
		order = new HomeOrder(LoginRes.getUser().getUserid(), 0, addr, shipDate, false, urgent,p);
		FuelOrderRequest req = new FuelOrderRequest (RequestEnum.Insert,p,order);
		return req;
		}
		else return null;
	}
	
	/**
	 * Make new Home Fuel order request and sent it to the server.
	 * @param shipDate - Chosen Shipping date.
	 * @param qty - Order Quantity(Liters).
	 * @param addr - Customer address.
	 * @param urgent - Is the order urgent or not.
	 */
	public void MakeHomeFuelRequest(Date shipDate,Date pdate, float qty, String addr, boolean urgent)
	{
		float totalPrice = CalcPrice.calcTotalHomeOrder(urgent, qty,response.getHomeFuelPrice().getMaxPrice(), response.getPromotion(Fuel.HomeFuelID));
		FuelOrderRequest req = createOrder(shipDate,pdate,qty,addr,urgent,totalPrice); 
			
			if(req!=null)
			{
			if(showConfirmOrder(qty, urgent,totalPrice))
			{
				gui.createWaitDialog("Sending your order...");
				client.handleMessageFromGUI(req);
			}
			}
			else gui.showErrorMessage("Not enough fuel quantity!");	
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
	public boolean verifyDetails(Date shipDate, String qty, String addr, boolean urgent)
	{
		boolean success = true;
		this.setErrorMsg("");
		Date date = new Date(); //Current date
		float qtyF = 0;
		try {
			qtyF = Float.parseFloat(qty);
			if(qtyF<0)
			{
				success = false;
				setErrorMsg(getErrorMsg() + "Illegal Amount value!\n");
			}
		}
		catch(NumberFormatException e)
		{
			success = false;
			setErrorMsg(getErrorMsg() + "Illegal Amount value!\n");
		}
		
		if(addr.equals(""))
		{
			success = false;
			setErrorMsg(getErrorMsg() + "Address field is Empty!\n");
		}
		if(shipDate == null && !urgent )
		{
			success = false;
			setErrorMsg(getErrorMsg() + "You have to pick ship date!\n");
		}
		else if(!urgent && shipDate.before(date))
		{
			success = false;
			 setErrorMsg(getErrorMsg() + "Illegal Date!\n");
		}
		
		if(!success) 
			return false;
		else return true;
				
	
			
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}
	

	public FuelOrderRequest getRequest() {
		return request;
	}

	public void setRequest(FuelOrderRequest request) {
		this.request = request;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public DefaultTableModel getTrackingTable()
	{
		return gui.getTrackingPanel().getModel();
	}

	
	


}
