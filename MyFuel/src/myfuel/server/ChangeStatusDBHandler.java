package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Observable;



public class ChangeStatusDBHandler extends DBHandler{
	
	public ChangeStatusDBHandler(MyFuelServer server, Connection con){
		super(server,con);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof Integer){
		int userid = (int)arg;
		System.out.print(userid);
			// TODO Auto-generated method stub
			PreparedStatement ps = null;
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
	}

}
