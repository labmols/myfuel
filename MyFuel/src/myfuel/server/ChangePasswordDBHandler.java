package myfuel.server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

import myfuel.request.ChangePassRequest;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

/**
 * This DB Controller responsible for changing password request.
 * @author Maor
 *
 */
public class ChangePasswordDBHandler extends DBHandler {
	
	/**
	 * new Change Password DB Controller.
	 * @param server - MyFuel Server
	 * @param con - MySQL driver Connection
	 */
	public ChangePasswordDBHandler(MyFuelServer server, Connection con){
		super(server,con);
	}
	
	/**
	 * check if oldPass is correct with DB and use updatePassword method to update the password.
	 * @param id - userid number
	 * @param oldPass - the old password value for identify the user
	 * @param newPass - the new password value
	 * @return booleanResponse (true/false and a message for the user).
	 */
	public Response changePassword(int id, String oldPass, String newPass){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from customer where uid=? and pass=?");
			ps.setInt(1,id);
			ps.setString(2, oldPass);
			rs= ps.executeQuery();
		    if(rs.next()){
		    	updatePassword(id,newPass);
		    	return new booleanResponse(true, "Your Password updated successfuly!");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new booleanResponse(false, "Your Old Password is not correct.. Please try again!");
	}
	

/**
 * update password via DB Query. 
 * @param id - the userid number.
 * @param newPass - new Password value.
 */
	private void updatePassword(int id, String newPass) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("update customer SET pass=? where uid=?");
			ps.setString(1,newPass);
			ps.setInt(2, id);
			ps.executeUpdate();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * this method notified by the server when the appropriate request received.  
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof ChangePassRequest){
			ChangePassRequest request = (ChangePassRequest) arg;
			server.setResponse(changePassword(request.getUserid(),request.getOldPass(),request.getNewPass()));
		}
		
		
	}
	
	
	
		
	}

