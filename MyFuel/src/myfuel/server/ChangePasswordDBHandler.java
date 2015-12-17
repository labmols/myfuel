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
 * 
 * 
 *
 */
public class ChangePasswordDBHandler extends DBHandler {
	public ChangePasswordDBHandler(MyFuelServer server, Connection con){
		super(server,con);
	}
	
	/**
	 * 
	 * @param id
	 * @param oldPass
	 * @param newPass
	 * @return
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
 * 
 * @param id
 * @param newPass
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof ChangePassRequest){
			ChangePassRequest request = (ChangePassRequest) arg;
			server.setResponse(changePassword(request.getUserid(),request.getOldPass(),request.getNewPass()));
		}
		
		
	}
	
	
	
		
	}

