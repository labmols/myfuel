package myfuel.server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import myfuel.request.LoginRequest;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;
import myfuel.response.WorkerLoginResponse;

public class LoginDBHandler implements Observer {
	Connection con = null;
	MyFuelServer server;
	Object response;
	public LoginDBHandler(MyFuelServer server,Connection con){
		this.con = con;
		this.server=server;
		server.addObserver(this);
		
	}
	

	private Response workerLogin(LoginRequest request) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from worker where wid=? and pass =?");
			ps.setInt(1, request.getUserid());
			ps.setString(2, request.getPassword());
			System.out.println(""+request.getUserid() + " " +request.getPassword());
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new WorkerLoginResponse(false,-1);
		
		}
		try {
			if(rs.next()){
				return new WorkerLoginResponse(true,rs.getInt(6));
			}
		}
			
			catch(SQLException e){
				return new WorkerLoginResponse(false,-1);
			}
		
		return new WorkerLoginResponse(false,-1);
	}

	private Response userLogin(LoginRequest request){
		
		int userid;
		String fname;
		String lname;
		String pass;
		String email;
		String cnumber;
		int status;
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
		
			ps = con.prepareStatement("select * from customer where uid=? and pass =?");
			ps.setInt(1, request.getUserid());
			ps.setString(2, request.getPassword());
			//System.out.println(""+request.userid + " " +request.password);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				status = rs.getInt(10);
				if(status == 1) return new UserLoginResponse(2);
				userid = rs.getInt(1);
				fname = rs.getString(2);
				lname = rs.getString(3);
				pass = rs.getString(4);
				email = rs.getString(5);
				cnumber = rs.getString(6);
				ps = con.prepareStatement("update customer SET status=? where uid = ?");
				ps.setInt(1, 1);
				ps.setInt(2, userid);
				ps.executeUpdate();
				
				return new UserLoginResponse(userid,fname,lname,pass,email,cnumber);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new UserLoginResponse(1); // User not Found
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		if(arg instanceof LoginRequest){
			LoginRequest request =(LoginRequest)arg;
			if(request.getType()==0) server.setResponse(userLogin(request));
			else server.setResponse(workerLogin(request));
		}
		
	
}
	
	
}

