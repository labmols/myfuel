package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Station;
import myfuel.request.RequestEnum;
import myfuel.request.UpdateRequest;
import myfuel.request.registerRequest;
import myfuel.response.RegisterResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

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
		try{
			ps= con.prepareStatement("delete from customer where uid=? limit 1");
			ps.setInt(1, customer.getUserid());
			ps.executeUpdate();
		} catch(SQLException e){
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
		
		}catch (SQLException e){
			e.printStackTrace();
			return new booleanResponse (false, "Update Failed! SQL Error!");
		}
		
		try{
			for(Car car: customer.getCars()){
				ps=con.prepareStatement("insert into customer_car values(?,?,?)");
				ps.setInt(1,customer.getUserid());
				ps.setInt(2,car.getcid());
				ps.setInt(3, car.getfid());
				ps.executeUpdate();
				}
			}catch (SQLException e){
				e.printStackTrace();
				return new booleanResponse (false, "Update Failed! SQL Error!");
			}
		
			
			try{
			for(int sid: customer.getStations())
			{
			ps=con.prepareStatement("insert into customer_station values(?,?)");
			ps.setInt(1,customer.getUserid());
			ps.setInt(2,sid);
			ps.executeUpdate();
			}
			}catch (SQLException e){
				e.printStackTrace();
				return new booleanResponse (false, "Update Failed! SQL Error!");
			}
			
			return new booleanResponse (true, "Update details successful!");
			
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
