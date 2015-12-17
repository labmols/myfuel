package myfuel.server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Observable;

import myfuel.request.ChangePassRequest;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

public class ChangePasswordDBHandler extends DBHandler {
	Connection con = null;
	public ChangePasswordDBHandler(MyFuelServer server, Connection con){
		super(server,con);
	}
	


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		ChangePassRequest request = (ChangePassRequest)arg;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update users SET pass=? where uid = ?");
			ps.setString(1, request.newPass);
			ps.setInt(2, request.userid);
			ps.executeUpdate();
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			server.setResponse(new booleanResponse(false,"SQL Error!"));
			
		}
		
		server.setResponse(new booleanResponse(true,"Change Password Success!"));
		
		
		
	}
	
	
	
		
	}

