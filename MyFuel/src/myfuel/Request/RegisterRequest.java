package myfuel.Request;



import myfuel.Entity.Customer;

/**
 * registerRequest class, contains all the request details that will be send to the server.
 * @author Maor
 *
 */
@SuppressWarnings("serial")
public class RegisterRequest extends Request {
	
	/**
	 * New customer details object.
	 */
	private Customer customer;
	
	/**
	 * Request Query type (Select /Insert)
	 */
	private RequestEnum type;
	
	/**
	 * Create new Customer register request object.
	 * @param type - Request query type (Select / Insert).
	 * @param customer - New Customer details object.
	 */
	public RegisterRequest(RequestEnum type,Customer customer){
		this.customer =customer;
		this.type=type;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}


	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}
	
	
}
