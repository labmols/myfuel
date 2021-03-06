package myfuel.DBHandler;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.sql.*;

import myfuel.Entity.Car;
import myfuel.Entity.Customer;
import myfuel.Entity.Network;
import myfuel.Entity.Station;
import myfuel.Request.RegisterRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.RegisterResponse;
import myfuel.Response.Response;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;

/**
 * Register Database Handler, responsible for all the related register queries.
 */
public class RegisterDBHandler extends DBHandler {
	/**
	 * new RegisterDBHandler object 
	 * @param server - MyFuel Server
	 * @param con - JDBC driver connection
	 */
	public RegisterDBHandler(MyFuelServer server,Connection con){
		super(server,con);
	}
	
	/**
	 * Get all station networks details from the Database.
	 * @param request - the request from the client.
	 * @return RegisterResponse if there is not SQL Error, otherwise return booleanResponse(false).
	 */
	private Response getNetworks()
	{
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Network> networks = new ArrayList<Network>();
		int id;
		String name;
	
			try {
				st = con.createStatement();
				String query = "select * from network";
				rs = st.executeQuery(query);
				while(rs.next()){
					id = rs.getInt(1);
					name = rs.getString(2);
					networks.add(new Network(id,name));
					}
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new booleanResponse(false,"SQL Error");
			}

			return new RegisterResponse(networks);
		}
	
	
	 /**
	  * Check if User ID , Car ID, or E-mail already exist in the Database.
	  * @param request - the client request that contains the Customer object with all his register details.
	  * @return - booleanResponse (false) if User ID, Car ID or E-mail exist or SQL Error, otherwise return booleanResponse(true).
	  */
	private Response CheckAndInsert(RegisterRequest request){
		Customer customer = request.getCustomer();
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			
			ps=con.prepareStatement("select * from customer where uid=?");
			ps.setInt(1, customer.getUserid());
			rs = ps.executeQuery();
			if(rs.next()) return new booleanResponse(false, "This Customer ID already registered!");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			ps=con.prepareStatement("select email from customer where email=?");
			ps.setString(1, customer.getEmail());
			rs = ps.executeQuery();
			if(rs.next()) return new booleanResponse(false, "This email already exist!");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			for(Car car : customer.getCars()){
			ps=con.prepareStatement("select * from customer_car where cid=?");
			ps.setInt(1, car.getcid());
			rs = ps.executeQuery();
			if(rs.next()) return new booleanResponse(false, "this Car ID already exist!");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insertCustomer(customer);
		return new booleanResponse(true, "Register success! \n"
				+ "your login details is: \n"
				+ "UserID: " + customer.getUserid() 
				+"\nPassword: " + customer.getPass()
				+"\nNow you need to wait for Email confirmation from the Marketing Delegate.");
		
	}
	
	/**
	 * Insert new Customer to the Database.
	 * @param customer - the new Customer details object.
	 */
	private void insertCustomer(Customer customer)
	{
		PreparedStatement ps = null;
		
		try {
			ps=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, customer.getUserid());
			ps.setString(2, customer.getFname());
			ps.setString(3, customer.getLname());
			ps.setString(4, customer.getPass());
			ps.setString(5, customer.getEmail());
			ps.setString(6, customer.getAddress());
			ps.setString(7, customer.getCnumber());
			ps.setInt(8, customer.getAtype());
			ps.setInt(9, customer.getToc());
			ps.setInt(10, customer.getSmodel());
			ps.setInt(11, 0);
			ps.setInt(12, 0);
			ps.executeUpdate();
			ps.close();
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		try{
			for(Car car: customer.getCars()){
				ps=con.prepareStatement("insert into customer_car values(?,?,?)");
				ps.setInt(1,customer.getUserid());
				ps.setInt(2,car.getcid());
				ps.setInt(3, car.getfid());
				ps.executeUpdate();
				ps.close();
				}
			}catch (SQLException e){
				e.printStackTrace();
				
			}
		
			
			try
			{
				for(int sid: customer.getStations())
				{
					ps=con.prepareStatement("insert into customer_network values(?,?)");
					ps.setInt(1,customer.getUserid());
					ps.setInt(2,sid);
					ps.executeUpdate();
					ps.close();
				}
				}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
	
	/**
	 * notified by the server when a new client request received , return RegisterResponse
	 *  or booleanResponse, according to the current request and errors.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof RegisterRequest){
		RegisterRequest request = (RegisterRequest)arg;
		if(request.getType() == RequestEnum.Select) server.setResponse(getNetworks());
		else if(request.getType() == RequestEnum.Insert) server.setResponse(CheckAndInsert(request));
	}
}
}
