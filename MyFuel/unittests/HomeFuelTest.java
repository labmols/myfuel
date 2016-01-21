import static org.junit.Assert.*;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import junit.framework.TestCase;
import myfuel.Entity.Customer;
import myfuel.Entity.Fuel;
import myfuel.Entity.FuelQty;
import myfuel.Entity.HomeOrder;
import myfuel.Entity.Promotion;
import myfuel.Entity.Purchase;
import myfuel.GUIActions.HomeOrderActions;
import myfuel.Request.FuelOrderRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.CustomerLoginResponse;
import myfuel.Response.FuelOrderResponse;
import myfuel.Tools.CalcPrice;



public class HomeFuelTest extends TestCase{

	/**
	 * Create Home Order GUI Controller 
	 */
	private HomeOrderActions actions;
	/**
	 * Calendar object for generating dates
	 */
	private Calendar cal;
	/**
	 * Customer details object
	 */
	private Customer customer;
	/**
	 * Simulate fuel order request
	 */
	private FuelOrderRequest req;
	/**
	 * Simulate current info form DB.
	 */
	private FuelOrderResponse res;
	/**
	 * Home order object;
	 */
	private HomeOrder order;
	/**
	 * List of promotions
	 */
	private ArrayList<Promotion> promList;
	/**
	 * Purchase object details
	 */
	private Purchase p;
	/**
	 * Current home inventory details
	 */
	private FuelQty homeInventory;
	/**
	 * Customer details
	 */
	private CustomerLoginResponse customerLogin;
	
	/**
	 * Tracking orders table model for checking the table
	 */
	private DefaultTableModel tableModel; 
	
	/**
	 * Simulate List of customer orders
	 */
	private ArrayList<HomeOrder> horders;
	
	public HomeFuelTest(String name) {
		super(name);
	}
	
	
	protected void setUp() throws Exception
	{
		customer = new Customer(1, "Maor", "Ohana","maoroh2@gmail.com","1234",0);
		customerLogin = new CustomerLoginResponse(customer,null);
		cal = Calendar.getInstance();
		Fuel home = new Fuel(4, 4);
		promList = new ArrayList<Promotion>();
		homeInventory = new FuelQty (4, 1000, 2000);
		horders = new ArrayList<HomeOrder>();
		HomeOrder order1,order2;
		
		//Create order1
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		Date pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 30);
		Date shipDate = cal.getTime();
		boolean urgent = false;
		float qty = 400;
		float totalPrice = 1600;
		Purchase p = new Purchase (customer.getUserid(),0, -1, Fuel.HomeFuelID,-1,pdate , totalPrice, qty ,null,-1, true);
		order1 = new HomeOrder(customer.getUserid(), 1, "Lotem Afula", shipDate, false, urgent,p);
		
		//Create order2
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 30);
		shipDate = cal.getTime();
		urgent = true;
		qty = 500;
		totalPrice = 2040;
		p = new Purchase (customer.getUserid(),0, -1, Fuel.HomeFuelID,-1,pdate , totalPrice, qty ,null,-1, true);
		order2 = new HomeOrder(customer.getUserid(), 2, "Lotem Afula", shipDate, true, urgent,p);
		
		horders.add(order1);
		horders.add(order2);
		res = new FuelOrderResponse(home,promList, horders, homeInventory);
		actions = new HomeOrderActions(customerLogin, res);
		tableModel = actions.getTrackingTable();
		
		
		
		
	}
	
	public void testVerify()
	{
		boolean expected;
		boolean result;
		Date d;
		
		/* Test1 Description 
		 * Correct input.
		 */
		cal.set(2016, Calendar.JANUARY, 30); //Year, month and day of month
		d = cal.getTime();
		expected = true;
		result=actions.verifyDetails(d, "200", "Afula", true);
		assertEquals(expected,result);
		
		/* Test2 Description 
		 * Shipping Date before current date.
		 */
		cal.set(2016, Calendar.JANUARY, 19); //Year, month and day of month
		d = cal.getTime();
		expected = false;
		result=actions.verifyDetails(d, "200", "Afula", false);
		assertEquals(expected,result);
		
		/* Test3 Description 
		 * Negative Amount  .
		 */
		cal.set(2016, Calendar.JANUARY, 30); //Year, month and day of month
		d = cal.getTime();
		expected = false;
		result=actions.verifyDetails(d, "-50", "Afula", false);
		assertEquals(expected,result);
		
		/* Test4 Description 
		 * Missed Address  .
		 */
		cal.set(2016, Calendar.JANUARY, 30); //Year, month and day of month
		d = cal.getTime();
		expected = false;
		result=actions.verifyDetails(d, "200", "", false);
		assertEquals(expected,result);
		
		
		/* Test5 Description 
		 * Not number format Amount  .
		 */
		cal.set(2016, Calendar.JANUARY, 30); //Year, month and day of month
		d = cal.getTime();
		expected = false;
		result=actions.verifyDetails(d, "aa", "Afula", false);
		assertEquals(expected,result);
		

		/* Test6 Description 
		 * Missed date and urgent order(not need to pick a date)  .
		 */
		cal.set(2016, Calendar.JANUARY, 30); //Year, month and day of month
		d = cal.getTime();
		expected = true;
		result=actions.verifyDetails(null, "200", "Afula", true);
		assertEquals(expected,result);
		
		
		/* Test7 Description 
		 * Missed date and not urgent order(need to pick a date).
		 */
		cal.set(2016, Calendar.JANUARY, 30); //Year, month and day of month
		d = cal.getTime();
		expected = false;
		result=actions.verifyDetails(null, "200", "Afula", false);
		assertEquals(expected,result);
		
		
		/* Test8 Description 
		 * Missed Amount  .
		 */
		cal.set(2016, Calendar.JANUARY, 30); //Year, month and day of month
		d = cal.getTime();
		expected = false;
		result=actions.verifyDetails(d, "", "Afula", false);
		assertEquals(expected,result);
		
		
	
		
		
	}
	
	public void testMakeRequest()
	{
		float price = res.getHomeFuelPrice().getMaxPrice();
		
		/*Test1 Description
		 * Create new regular Home fuel request(not urgent) with purchase date 20/01/16 and shipping address 26/01/16
		 * 
		 */
		FuelOrderRequest req2 = null;
		boolean urgent = false;
		float qty = 200;
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		Date pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 26);
		Date shipDate = cal.getTime();
		float totalPrice = CalcPrice.calcTotalHomeOrder( urgent, qty, price, res.getPromotion(Fuel.HomeFuelID));
		//Check Total Price
		assertTrue(totalPrice == (float)((price*qty)));
		p = new Purchase (customer.getUserid(),0, -1, Fuel.HomeFuelID, -1 ,pdate , totalPrice, qty ,null,-1, true);
		order = new HomeOrder(customer.getUserid(), 0, "Lotem Afula", shipDate, false, urgent,p);
		// Expected request
		req = new FuelOrderRequest(RequestEnum.Insert,p,order);
	
		req2 =actions.createOrder(shipDate,pdate, qty, "Lotem Afula", urgent,totalPrice);
		
		assertTrue(req.equals(req2));
		/*/End of Test1
		
		/*Test2 Description
		 * Create new regular Home fuel request(not urgent) with purchase date 20/01/16 and shipping address 26/01/16
		 * But now with amount > current inventory amount
		 */
		req2 = null;
		urgent = false;
		qty = 1001;
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 26);
		shipDate = cal.getTime();
		req2 =actions.createOrder(shipDate,pdate, qty, "Lotem Afula", urgent,totalPrice);
		
		assertTrue(req2==null);
		
		/*End of Test2
		 
	
		/*Test3 Description
		 * Create Urgent Home fuel request with purchase date 20/01/16 and shipping address 26/01/16
		 * In this case we check that 2% fee is calculated.
		 */
		req2 = null;
		urgent = true;
		qty = 200;
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 26);
		shipDate = cal.getTime();
		totalPrice = CalcPrice.calcTotalHomeOrder( urgent, qty,  price, res.getPromotion(Fuel.HomeFuelID));
		//Check Total Price
		assertTrue(totalPrice ==   (float)((price*qty)*1.02));
		p = new Purchase (customer.getUserid(),0, -1, Fuel.HomeFuelID, -1 ,pdate , totalPrice, qty ,null,-1, true);
		order = new HomeOrder(customer.getUserid(), 0, "Lotem Afula", pdate, false, urgent,p);
		// Expected request
		req = new FuelOrderRequest(RequestEnum.Insert,p,order);
	
		req2 =actions.createOrder(shipDate,pdate, qty, "Lotem Afula", urgent,totalPrice);
		
		assertTrue(req.equals(req2));
		/*End of Test3/*/
		
		/*Test4 Description
		 * Create Regular Home fuel(not urgent) request with purchase date 20/01/16 and shipping address 26/01/16
		 * Amount is 700 Liter now
		 * In this case we check that 3% discount is calculated.
		 */
		req2 = null;
		urgent = false;
		qty = 700;
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 26);
		shipDate = cal.getTime();
		totalPrice = CalcPrice.calcTotalHomeOrder( urgent, qty,  price, res.getPromotion(Fuel.HomeFuelID));
		//Check Total Price
		assertTrue(totalPrice ==   (float)((price*qty)*0.97));
		p = new Purchase (customer.getUserid(),0, -1, Fuel.HomeFuelID, -1 ,pdate , totalPrice, qty ,null,-1, true);
		order = new HomeOrder(customer.getUserid(), 0, "Lotem Afula", shipDate, false, urgent,p);
		// Expected request
		req = new FuelOrderRequest(RequestEnum.Insert,p,order);
	
		req2 =actions.createOrder(shipDate,pdate, qty, "Lotem Afula", urgent,totalPrice);
		
		assertTrue(req.equals(req2));
		/*End of Test4/*/
		
		/*Test5 Description
		 * Create Regular Home fuel(not urgent) request with purchase date 20/01/16 and shipping address 26/01/16
		 * Amount is > 800 Liters now
		 * In this case we check that 4% discount is calculated.
		 */
		req2 = null;
		urgent = false;
		qty = 851;
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 26);
		shipDate = cal.getTime();
		totalPrice = CalcPrice.calcTotalHomeOrder(urgent, qty,  price, res.getPromotion(Fuel.HomeFuelID));
		//Check Total Price
		assertTrue(totalPrice ==  (float)((price*qty)*0.96));
		p = new Purchase (customer.getUserid(),0, -1, Fuel.HomeFuelID, -1 ,pdate , totalPrice, qty ,null,-1, true);
		order = new HomeOrder(customer.getUserid(), 0, "Lotem Afula", shipDate, false, urgent,p);
		// Expected request
		req = new FuelOrderRequest(RequestEnum.Insert,p,order);
	
		req2 =actions.createOrder(shipDate,pdate, qty, "Lotem Afula", urgent,totalPrice);
		
		//Check Request object
		assertTrue(req.equals(req2));
		/*End of Test5/*/
		
		/*Test6 Description
		 * Create Urgent Home fuel request with purchase date 20/01/16 and shipping address 26/01/16
		 * In this case there is a promotion discount of 6% and urgent fee of 2%.
		 */
		
		promList.add(new Promotion(1,"Promotion",6, null,null,null,null,0,Fuel.HomeFuelID, 1));
		req2 = null;
		urgent = true;
		qty = 200;
		cal.set(2016, Calendar.JANUARY, 20); //Year, month and day of month
		pdate = cal.getTime();
		cal.set(2016, Calendar.JANUARY, 26);
		shipDate = cal.getTime();
		totalPrice = CalcPrice.calcTotalHomeOrder( urgent, qty,  price, res.getPromotion(Fuel.HomeFuelID));
		//Check Total Price
		assertTrue(totalPrice ==   (float)((price*qty)*1.02*0.94));
		p = new Purchase (customer.getUserid(),0, -1, Fuel.HomeFuelID, res.getPromotion(Fuel.HomeFuelID).getPid() ,pdate , totalPrice, qty ,null,-1, true);
		order = new HomeOrder(customer.getUserid(), 0, "Lotem Afula", pdate, false, urgent,p);
		// Expected request
		req = new FuelOrderRequest(RequestEnum.Insert,p,order);
	
		req2 =actions.createOrder(shipDate,pdate, qty, "Lotem Afula", urgent,totalPrice);
		
		assertTrue(req.equals(req2));
		/*End of Test6/*/
		
		
	}
	
	public void testTrackingOrder()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		HomeOrder order1 = horders.get(0);
		HomeOrder order2 = horders.get(1);

		/*Test1 Check Order 1 details
		 * OrderID:1
		 * Amount: 400 L
		 * Price: 1600 NIS
		 * Address: Lotem Afula
		 * Ship Date : 30/01/16
		 * Urgent : false
		 * Status: On delivery
		 */
		assertTrue((int)tableModel.getValueAt(0, 0) == 1);
		assertTrue(((String)(tableModel.getValueAt(0, 1))).equals(""+400+"L"));
		assertTrue(((String)(tableModel.getValueAt(0, 2))).equals(""+1600));
		assertTrue(((String)(tableModel.getValueAt(0, 3))).equals("Lotem Afula"));
		assertTrue(((String)(tableModel.getValueAt(0, 4))).equals(format.format(order1.getShipDate())));
		assertTrue(((String)(tableModel.getValueAt(0, 5))).equals("No"));
		assertTrue(((String)(tableModel.getValueAt(0, 6))).equals("On delivery"));
		
		/*Test2 Check Order 2 details
		 * OrderID:2
		 * Amount: 500 L
		 * Price: 2000 NIS
		 * Address: Lotem Afula
		 * Ship Date : 20/01/16
		 * Urgent : true
		 * Status: Delivered
		 */
		assertTrue((int)tableModel.getValueAt(1, 0) == 2);
		assertTrue(((String)(tableModel.getValueAt(1, 1))).equals(""+500+"L"));
		assertTrue(((String)(tableModel.getValueAt(1, 2))).equals(""+2040));
		assertTrue(((String)(tableModel.getValueAt(1, 3))).equals("Lotem Afula"));
		assertTrue(((String)(tableModel.getValueAt(1, 4))).equals(format.format(order2.getShipDate())));
		assertTrue(((String)(tableModel.getValueAt(1, 5))).equals("Yes"));
		assertTrue(((String)(tableModel.getValueAt(1, 6))).equals("Delivered"));
		
			
	}
	
	


}
