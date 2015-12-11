package myfuel.server;



import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.sql.*;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.Station;
import myfuel.client.registerRequest;


public class RegisterDBHandler implements Observer {
	Connection con;
	MyFuelServer server;
	RegisterDBHandler(MyFuelServer server,Connection con){
		this.con = con;
		this.server=server;
		server.addObserver(this);
	}
	
	private void showStations(registerRequest request)
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
			}

			server.setResponse(new RegisterResponse(stations));
		}
	
	private void insertCustomer(registerRequest request)
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		Customer customer = request.getCustomer();
	
			try {
				ps=con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, customer.userid);
				ps.setString(2, customer.fname);
				ps.setString(3, customer.lname);
				ps.setString(4, customer.pass);
				ps.setString(5, customer.email);
				ps.setString(6, customer.cnumber);
				ps.setInt(7, customer.atype);
				ps.setInt(8, customer.toc);
				ps.setInt(9, customer.smodel);
				ps.setInt(10, 0);
				ps.setInt(11, 0);
			ps.executeUpdate();
			
			for(Car car: customer.cars){
				ps=con.prepareStatement("insert into customer_car values(?,?,?)");
				ps.setInt(1,customer.userid);
				ps.setInt(2,car.getcid());
				ps.setInt(3, car.getfid());
				ps.executeUpdate();
				}
			
			for(int sid: customer.stations)
			{
			ps=con.prepareStatement("insert into customer_station values(?,?)");
			ps.setInt(1,customer.userid);
			ps.setInt(2,sid);
			ps.executeUpdate();
			}
			
		
			
			server.setResponse(new booleanResponse(true));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				server.setResponse(new booleanResponse(false));
				e.printStackTrace();
			}	
	}
	



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof registerRequest){
		registerRequest request = (registerRequest)arg;
		if(request.type == 1) showStations(request);
		else insertCustomer(request);
	}
}
}
