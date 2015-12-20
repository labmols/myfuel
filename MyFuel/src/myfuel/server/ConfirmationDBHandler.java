package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.*;
import myfuel.request.ConfirmationRequest;
import myfuel.response.ConfirmationResponse;
import myfuel.response.booleanResponse;

public class ConfirmationDBHandler extends DBHandler{
	
	private ArrayList<Customer> customers ;
	private String str;
	private boolean answer;
	public ConfirmationDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	/***
	 * Getting the unapproved customers from the DB
	 */
	void getCustomersFromDB()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		customers = new ArrayList<Customer>();
		try {
			ps = con.prepareStatement("select uid,fname,lname,toc from customer where app = 0");
	
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				customers.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}
			
		
		} catch (SQLException e) {
			
			str = "An error with the server connection";
			server.setResponse(new booleanResponse(false,str));
			e.printStackTrace();
		}
	}
	
	
	/***
	 * gets message from the client via the server
	 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof ConfirmationRequest)
		{
			ConfirmationRequest rq = (ConfirmationRequest)arg1;
			if(rq.getType() == 1)
			{
				getCustomersFromDB();
				server.setResponse(new ConfirmationResponse(customers));
			}
		}
		
	}

}
