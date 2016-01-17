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
import myfuel.client.Customer;
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
				
				if(role == 5 )
				{
						msg = new ArrayList<MessageForManager>();
						ps = con.prepareStatement("select * from message where nid = ? and type = 0");
						ps.setInt(1, nid);
						rs = ps.executeQuery();
						
						while(rs.next())
						{
							msg.add(new MessageForManager(rs.getInt(1),rs.getString(4),rs.getInt(2),rs.getInt(3),rs.getInt(5)));
						}
				}
				
				else if(role == 8)
				{
						ps.close();
						rs.close();
						msg = new ArrayList<MessageForManager>();
						ps = con.prepareStatement("select * from message where sid  = ? ");
						ps.setInt(1, sid);
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
	
	private booleanResponse checkCustomer(LoginRequest request)
	{
		int approved, status;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("select * from customer where uid=? and pass =?");
			ps.setInt(1, request.getUserid());
			ps.setString(2, request.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()){
				status = rs.getInt(11);
				approved = rs.getInt(12);
				if(status == 1) return new booleanResponse(false, "This Customer is already logged in!");
				else if(approved == 0) return new booleanResponse(false, "You are not approved yet!");
			}
			else
			{
				return new booleanResponse(false, "UserID or password incorrect!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  new booleanResponse(true, "");
		
		
	}

	/**
	 * Verify Customer login details, including UserID and Password, and his status.
	 * @param request - the Login request that contains the User ID and Password.
	 * @return - CustomerLoginResponse if current customer exist in DB and he not already connected,
	 * otherwise return booleanResponse(false).
	 */
	private Customer getCustomer(int customerID, boolean fast){
		
		String fname,lname,pass,email,cnumber,address;
		int userid,atype,smodel,toc;
		ArrayList<Integer> stations=new ArrayList<Integer>();
		ArrayList<Car> cars = new ArrayList<Car>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
		
			ps = con.prepareStatement("select * from customer where uid=?");
			ps.setInt(1, customerID);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
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
				if(!fast)
				{
					ps = con.prepareStatement("update customer SET status=? where uid = ?");
					ps.setInt(1, 1);
					ps.setInt(2, userid);
					ps.executeUpdate();
				}
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
				
				Customer customer = new Customer(userid,fname,lname,pass,email,address,cnumber,toc,atype,smodel,cars,stations);
				return customer;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
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
	 * Fast Fuel method, getting Random Car and Station from DB and his related Customer details
	 * For simulate NFC sensor action.
	 * @return
	 */
	private Response FastFuel()
	{
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		Statement st = null;
		PreparedStatement ps = null;
		int nid;
		Station s = null;
	
			try {
				st = con.createStatement();
				String query = "select * from customer_car order by rand() limit 1";
				rs = st.executeQuery(query);
				if(rs.next())
				{
					//Random network
					query = "select sid from customer_network where uid = ? order by rand() limit 1";
					ps = con.prepareStatement(query);
					ps.setInt(1, rs.getInt(1));
					rs2=ps.executeQuery();
					if(rs2.next())
						nid = rs2.getInt(1);
					else return new booleanResponse(false, "Network SQL Error");
					
					//Random station
					query = "select * from station_in_network where nid = ? order by rand() limit 1";
					ps = con.prepareStatement(query);
					ps.setInt(1, nid);
					rs3=ps.executeQuery();
					if(rs3.next())
						s= new Station(rs3.getInt(1),rs3.getString(3),new Network(nid,null));
					else return new booleanResponse(false, "Station SQL Error");
					
					Customer customer = this.getCustomer(rs.getInt(1),true);
					Car FastFuelCar = new Car(rs.getInt(2), rs.getInt(3));
					ArrayList<Network> networks = this.getNetworks();
					rs.close();
					st.close();
					return new CustomerLoginResponse(customer, networks, FastFuelCar,s);
				}
				else 
				{
					rs.close();
					st.close();
					return new booleanResponse(false, "Customer Car SQL Error");
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			 catch (Exception e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			
		
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
			if(request.getChangeStatus()==1) 
			{
				changeStatus(request);
				server.setResponse(null);
			}
			else if(request.getType()==LoginRequest.CustomerLogin)
			{
				booleanResponse res = this.checkCustomer(request);
				if(res.getSuccess())
				{
					Customer customer = this.getCustomer(request.getUserid(),false);
					ArrayList<Network> networks= this.getNetworks();
					server.setResponse(new CustomerLoginResponse(customer,networks));
				}
				else server.setResponse(res);
			}
			else if(request.getType()==LoginRequest.WorkerLogin) server.setResponse(workerLogin(request));	
			else if(request.getType() == LoginRequest.FastFuel) server.setResponse(FastFuel());	
		}
		
	
}



	
}

