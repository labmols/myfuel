package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.Entity.Car;
import myfuel.Entity.Customer;
import myfuel.Entity.Station;
import myfuel.request.RequestEnum;
import myfuel.request.UpdateRequest;
import myfuel.request.RegisterRequest;
import myfuel.response.RegisterResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;
import myfuel.server.MyFuelServer;

/**
 * Update Details Database Handler, responsible for all the related update details queries.
 */
public class UpdateDetailsDBHandler extends DBHandler{
	
	/**
	 * create new Update Details interface controller.
	 * @param server - MyFuelServer object.
	 * @param con - JDBC driver connection;
	 */
	public UpdateDetailsDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * check if the cars added not exist in the Database.
	 * @param customer - the customer object with all his details.
	 * @return booleanResponse (true if update succeed , otherwise false).
	 */
	private Response checkAndUpdate(Customer customer){
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			for(Car car : customer.getCars()){
			ps=con.prepareStatement("select * from customer_car where cid=? and uid!=?");
			ps.setInt(1, car.getcid());
			ps.setInt(2, customer.getUserid());
			rs = ps.executeQuery();
			if(rs.next()) return new booleanResponse(false, "Update Failed! Car ID already exist!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new booleanResponse(false, "Update Failed! SQL Error!");
		}
		
		
		return updateDetails(customer);
		
		
		
	}
	
	
	
	/**
	 * Update all customer details in the Database.
	 * @param customer - the updated customer details object.
	 * @return booleanResponse (true if update succeed , otherwise false).
	 */
	private Response updateDetails(Customer customer){
		PreparedStatement ps = null;
		
		try {
			ps=con.prepareStatement("update customer set fname=?, lname=?, pass=?,email=?,address=?,cnumber=?,atype=?,toc=?,smodel=? where uid=?");
			ps.setInt(10, customer.getUserid());
			ps.setString(1, customer.getFname());
			ps.setString(2, customer.getLname());
			ps.setString(3, customer.getPass());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getCnumber());
			ps.setInt(7, customer.getAtype());
			ps.setInt(8, customer.getToc());
			ps.setInt(9, customer.getSmodel());
			ps.executeUpdate();
			
			ps= con.prepareStatement("DELETE FROM customer_car WHERE uid=?");
			ps.setInt(1, customer.getUserid());
			ps.executeUpdate();
			
				for(Car car: customer.getCars()){
					ps=con.prepareStatement("insert into customer_car values(?,?,?)");
					ps.setInt(1,customer.getUserid());
					ps.setInt(2,car.getcid());
					ps.setInt(3, car.getfid());
					ps.executeUpdate();
					ps.close();
					}
		
				ps= con.prepareStatement("DELETE FROM customer_network WHERE uid=?");
				ps.setInt(1, customer.getUserid());
				ps.executeUpdate();
				
					for(int sid: customer.getStations())
					{
						ps=con.prepareStatement("insert into customer_network values(?,?)");
						ps.setInt(1,customer.getUserid());
						ps.setInt(2,sid);
						ps.executeUpdate();
						ps.close();
					}
					
			ps.close();
			
			return new booleanResponse (true, "Update details successful!");
			
			
		
		}catch (SQLException e){
			e.printStackTrace();
			return new booleanResponse (false, "Update Failed! SQL Error!");
		}
		
		
			
			
			
	}
	
	/**
	 * notified by the server when a new client request received
	 *  and set the server response to be booleanResponse.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof UpdateRequest)
		{
			UpdateRequest request = (UpdateRequest) arg;
			server.setResponse(checkAndUpdate(request.getCustomer()));
			
		}
		
	}

}
