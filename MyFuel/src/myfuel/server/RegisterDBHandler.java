package myfuel.server;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.sql.*;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Station;
import myfuel.request.RequestEnum;
import myfuel.request.registerRequest;
import myfuel.response.RegisterResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;


public class RegisterDBHandler extends DBHandler {
	RegisterDBHandler(MyFuelServer server,Connection con){
		super(server,con);
	}
	
	private Response showStations(registerRequest request)
	{
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Station> stations = new ArrayList<Station>();
		int id;
		String name;
	
			try {
				st = con.createStatement();
				String query = "select * from station";
				rs = st.executeQuery(query);
				while(rs.next()){
					id = rs.getInt(1);
					name = rs.getString(2);
					stations.add(new Station(id,name));
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new booleanResponse(false,"SQL Error");
			}

			return new RegisterResponse(stations);
		}
	
	private Response insertCustomer(registerRequest request)
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		Customer customer = request.getCustomer();
	
			try {
				ps=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, customer.getUserid());
				ps.setString(2, customer.getFname());
				ps.setString(3, customer.getLname());
				ps.setString(4, customer.getPass());
				ps.setString(5, customer.getEmail());
				ps.setString(6, customer.getCnumber());
				ps.setInt(7, customer.getAtype());
				ps.setInt(8, customer.getToc());
				ps.setInt(9, customer.getSmodel());
				ps.setInt(10, 0);
				ps.setInt(11, 0);
				ps.executeUpdate();
			
			}catch (SQLException e){
				e.printStackTrace();
				return new booleanResponse(false, "this Customer ID already registered!");
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
				return new booleanResponse(false, "this Car ID already exist!");
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
			}
		
			return new booleanResponse(true, "Register success! \n"
					+ "your login details is: \n"
					+ "UserID: " + customer.getUserid() 
					+"\nPassword: " + customer.getPass()
					+"\nNow you need to wait for the Marketing Delegate confirmation.");
					
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof registerRequest){
		registerRequest request = (registerRequest)arg;
		if(request.getType() == RequestEnum.Select) server.setResponse(showStations(request));
		else if(request.getType() == RequestEnum.Insert) server.setResponse(insertCustomer(request));
	}
}
}
