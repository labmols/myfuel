package myfuel.server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import myfuel.client.Car;
import myfuel.client.Station;
import myfuel.request.LoginRequest;
import myfuel.request.registerRequest;
import myfuel.response.ErrorEnum;
import myfuel.response.RegisterResponse;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;
import myfuel.response.WorkerLoginResponse;
import myfuel.response.booleanResponse;

public class LoginDBHandler extends DBHandler {
	
	public LoginDBHandler(MyFuelServer server,Connection con){
		super(server,con);	
	}
	
/**
 * 
 * @param request
 * @return
 */
	private Response workerLogin(LoginRequest request) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		int status;
		try {
			ps = con.prepareStatement("select * from worker where wid=? and pass =?");
			ps.setInt(1, request.getUserid());
			ps.setString(2, request.getPassword());
			System.out.println(""+request.getUserid() + " " +request.getPassword());
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new booleanResponse(false, "WorkerID or Password not correct!");
		
		}
		try {
			if(rs.next()){
				status = rs.getInt(7);
				if(status == 1) return new booleanResponse(false, "This Worker already logged in!");
				ps = con.prepareStatement("update worker SET status=? where wid = ?");
				ps.setInt(1, 1);
				ps.setInt(2, request.getUserid());
				ps.executeUpdate();
				return new WorkerLoginResponse(rs.getInt(6),rs.getInt(1),rs.getInt(8));
			}
		}
			
			catch(SQLException e){
				e.printStackTrace();
				return new booleanResponse(false, "SQL Error!");
			}
		return new booleanResponse(false, "SQL Error!");
		
		
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Response userLogin(LoginRequest request){
		
		String fname,lname,pass,email,cnumber,address;
		int userid,status,atype,smodel,toc;
		ArrayList<Integer> stations=new ArrayList<Integer>();
		ArrayList<Car> cars = new ArrayList<Car>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
		
			ps = con.prepareStatement("select * from customer where uid=? and pass =?");
			ps.setInt(1, request.getUserid());
			ps.setString(2, request.getPassword());
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				status = rs.getInt(11);
				if(status == 1) return new booleanResponse(false, "This Customer is already logged in!");
				userid = rs.getInt(1);
				fname = rs.getString(2);
				lname = rs.getString(3);
				pass = rs.getString(4);
				email = rs.getString(5);
				address = rs.getString(6);
				cnumber = rs.getString(7);
				atype = rs.getInt(8);
				toc = rs.getInt(9);
				smodel = rs.getInt(10);
				ps = con.prepareStatement("update customer SET status=? where uid = ?");
				ps.setInt(1, 1);
				ps.setInt(2, userid);
				ps.executeUpdate();
				
				ps = con.prepareStatement("select sid from customer_station where uid = ?");
				ps.setInt(1, userid);
				rs = ps.executeQuery();
				while(rs.next()){
					stations.add(rs.getInt(1));
				}
				
				ps = con.prepareStatement("select cid,fuelid from customer_car where uid=?");
				ps.setInt(1, userid);
				rs = ps.executeQuery();
				
				while(rs.next()){
					cars.add(new Car(rs.getInt(1), rs.getInt(2)));
				}
				
				ArrayList<Station> Allstations = getStations();
				return new UserLoginResponse(userid,fname,lname,pass,email,address
						,cnumber,toc,atype,smodel,cars,stations,Allstations);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new booleanResponse(false, "UserID or password not correct!"); // User not Found
	}
	
	private ArrayList<Station> getStations()
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
			
			return stations;
			
		}
	
	/**
	 * 
	 * @param request
	 */
	private void changeStatus(LoginRequest request) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int userid = request.getUserid();
		if(request.getType() ==0)
		{
		try {
			ps = con.prepareStatement("update customer SET status=? where uid =?");
			ps.setInt(1, 0);
			ps.setInt(2, userid);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			try {
				ps = con.prepareStatement("update worker SET status=? where wid =?");
				ps.setInt(1, 0);
				ps.setInt(2, userid);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof LoginRequest){
			LoginRequest request =(LoginRequest)arg;
			if(request.getChangeStatus()==1) changeStatus(request);
			else if(request.getType()==0) server.setResponse(userLogin(request));
			else if(request.getType()==1) server.setResponse(workerLogin(request));	 
		}
		
	
}



	
}

