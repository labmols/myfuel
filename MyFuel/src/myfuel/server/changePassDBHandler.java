package myfuel.server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import myfuel.client.ChangePassRequest;

public class changePassDBHandler implements RequestHandler {
	Connection con = null;
	public changePassDBHandler(Connection con){
		this.con = con;
	}
	
	@Override
	public Response handleRequest(Object msg) {
		
		boolean b;
		ChangePassRequest request = (ChangePassRequest)msg;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update users SET pass=? where uid = ?");
			ps.setString(1, request.newPass);
			ps.setInt(2, request.userid);
			ps.executeUpdate();
			b= true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b=false;
			e.printStackTrace();
			
		}
		
		return new booleanResponse(b);
		
	}
	
	
	
		
	}

