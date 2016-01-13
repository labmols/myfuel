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
import myfuel.client.Fuel;
import myfuel.client.MessageForManager;
import myfuel.client.Network;
import myfuel.client.Station;
import myfuel.request.LoginRequest;
import myfuel.request.RegisterRequest;
import myfuel.response.ErrorEnum;
import myfuel.response.RegisterResponse;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.WorkerLoginResponse;
import myfuel.response.booleanResponse;

/**
 * Log In Database Controller, responsible for Log In queries.
 * @author Maor
 *
 */
public class LoginDBHandler extends DBHandler {
	
	public LoginDBHandler(MyFuelServer server,Connection con){
		super(server,con);	
	}
	
/**
 * Verify worker login details, including UserID and Password, and his status.
 * @param request - the Login request that contains the User ID and Password.
 * @return - WorkerLoginResponse if current worker exist in DB and he not already connected,
 * otherwise return booleanResponse(false).
 */
	private Response workerLogin(LoginRequest request) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		int status,sid,role,wid,nid;
		ArrayList<MessageForManager> msg = null;
		try {
			ps = con.prepareStatement("select * from worker where wid=? and pass =?");
			ps.setInt(1, request.getUserid());
			ps.setString(2, request.getPassword());
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		try {
			if(rs.next()){
				status = rs.getInt(7);
				if(status == 1) return new booleanResponse(false, "This Worker already logged in!");
				ps = con.prepareStatement("update worker SET status=? where wid = ?");
				ps.setInt(1, 1);
				ps.setInt(2, request.getUserid());
				ps.executeUpdate();
				nid = rs.getInt(8);
				sid = rs.getInt(9);
				role = rs.getInt(6);
				wid = rs.getInt(1);
				if(role == 5 || role == 8)
				{
						msg = new ArrayList<MessageForManager>();
						ps = con.prepareStatement("select * from message where nid = ? and sid = ? ");
						ps.setInt(1, nid);
						ps.setInt(2,sid);
						rs = ps.executeQuery();
						
						while(rs.next())
						{
							msg.add(new MessageForManager(rs.getInt(1),rs.getString(4),rs.getInt(2),rs.getInt(3),rs.getInt(5)));
						}
				}
				return new WorkerLoginResponse(role,wid,nid,sid,msg);
			}
			else return new booleanResponse(false, "WorkerID or Password not correct!");
		}
			
			catch(SQLException e){
				e.printStackTrace();
				return new booleanResponse(false, "SQL Error!");
			}
		
	}

	/**
	 * Verify Customer login details, including UserID and Password, and his status.
	 * @param request - the Login request that contains the User ID and Password.
	 * @return - CustomerLoginResponse if current customer exist in DB and he not already connected,
	 * otherwise return booleanResponse(false).
	 */
	private Response customerLogin(LoginRequest request){
		
		String fname,lname,pass,email,cnumber,address;
		int userid,status,atype,smodel,toc,approved;
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
				approved = rs.getInt(12);
				if(status == 1) return new booleanResponse(false, "This Customer is already logged in!");
				else if(approved == 0) return new booleanResponse(false, "You are not approved yet!");
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
				
				ps = con.prepareStatement("select sid from customer_network where uid = ?");
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
				
				ArrayList<Network> networks = this.getNetworks();
				return new CustomerLoginResponse(userid,fname,lname,pass,email,address
						,cnumber,toc,atype,smodel,cars,stations,networks);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new booleanResponse(false, "UserID or password not correct!"); // User not Found
	}
	
	
	
	/**
	 * Update user login status in the system in Login and Exit.
	 * @param request - the Log in request that contains the User details.
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
	
	/**
	 * Get all station networks from DB.
	 * @return list of all stations networks.
	 */
	private ArrayList<Network> getNetworks()
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
				return null;
			}

			return networks;
		}
	
	

	/**
	 * this method notified by the server when a new client request received,
	 * return CustomerLoginResponse (for Customer Login), WorkerLoginResponse(for Worker Login) or booleanResponse(in Error) according
	 * to the SQL Errors and the request. 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof LoginRequest){
			LoginRequest request =(LoginRequest)arg;
			if(request.getChangeStatus()==1) changeStatus(request);
			else if(request.getType()==0) server.setResponse(customerLogin(request));
			else if(request.getType()==1) server.setResponse(workerLogin(request));	 
		}
		
	
}



	
}

