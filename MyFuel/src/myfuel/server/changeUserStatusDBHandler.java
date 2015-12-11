package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;






public class changeUserStatusDBHandler implements RequestHandler {
	Connection con;
	public changeUserStatusDBHandler(Connection con){
		this.con = con;
	}
	@Override
	public Response handleRequest(Object msg) {
		// TODO Auto-generated method stub
		
		int userid = (int)msg;
		System.out.print(userid);
			// TODO Auto-generated method stub
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement("update users SET status=? where uid =?");
				ps.setInt(1, 0);
				ps.setInt(2, userid);
				ps.executeUpdate();
				return new booleanResponse(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new booleanResponse(false);
	}

}
