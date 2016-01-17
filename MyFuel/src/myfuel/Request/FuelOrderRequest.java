package myfuel.Request;


import myfuel.Entity.HomeOrder;
import myfuel.Entity.Purchase;

@SuppressWarnings("serial")
/**
 * Fuel Request class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
public class FuelOrderRequest extends Request{
	/**
	 * Request Query Type (Insert, Select)
	 */
	private RequestEnum type;
	
	/**
	 * Type of Fuel
	 */
	private int FuelID;

	
	/** The Purchase details
	 */
	private Purchase pur;
	
	/**
	 * The Home Order details (if this Fuel is home)
	 */
	private HomeOrder order;
	
	/**
	 * Used for Home Order purchase.
	 */
	private int customerID;
	/**
	 * Create new Fuel Request 
	 * @param type - the Query type.
	 */
	
	/**
	 * Create new get info request (Get all details from the Database).
	 * @param type - in this case, type will be select query.
	 * @param FuelID - the FuelID number (for checking available promotion).
	 * @param customerID - get all home fuel orders for this specific customer.
	 */
	public FuelOrderRequest (RequestEnum type,  int FuelID, int customerID )
	{
		setType(type);
		setFuelID(FuelID);
		setPur(null);
		setHOrder(null);
		setCustomerID(customerID);
	}
	
	/**
	 * Create new insert purchase request (insert new purchase to the DB)
	 * @param type - In this case , type will be insert query.
	 * @param pur - The purchase details object.
	 * @param order - Home order details object (in case this purchase is home order
	 *  , need to insert the details into the home order table).
	 */
	public FuelOrderRequest (RequestEnum type, Purchase pur, HomeOrder order)
	{
		setType(type);
		setPur(pur);
		setHOrder(order);
	}
	

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}

	public int getFuelID() {
		return FuelID;
	}

	public void setFuelID(int FuelID) {
		this.FuelID = FuelID;
	}

	public Purchase getPur() {
		return pur;
	}

	public void setPur(Purchase pur) {
		this.pur = pur;
	}

	public HomeOrder getHOrder() {
		return order;
	}

	public void setHOrder(HomeOrder order) {
		this.order = order;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	
}
