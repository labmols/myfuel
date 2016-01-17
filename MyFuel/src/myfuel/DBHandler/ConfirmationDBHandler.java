package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.Client.*;
import myfuel.Entity.Customer;
import myfuel.Request.ConfirmationRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.ConfirmationResponse;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;

/***
 * This class is responsible for getting and setting Customers that needs to be approved 
 * @author karmo
 *
 */
public class ConfirmationDBHandler extends DBHandler{
	
	/**
	 * List of Customers that needs to be approved
	 */
	private ArrayList<Customer> customers ;
	/***
	 * Description of the OPeration
	 */
	private String str;
	/***
	 * WIll indicate if operations went good or bad (true or false)
	 */
	private boolean answer;
	/***
	 * ConfirmationDBHandler Constructor
	 * @param server - MyFuelServer
	 * @param con - JDBC
	 */
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
			ps = con.prepareStatement("select uid,fname,lname,email,pass,toc from customer where app = 0");
	
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				customers.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
			}
			
			answer = true;
		} catch (SQLException e) {
			
			str = "An error with the server connection";
			answer = false;
			e.printStackTrace();
		}
	}
	
	/***
	 * Approving customers
	 * @param approved - The list of approved customers
	 */
	private void updateApproved(ArrayList<Integer> approved) 
	{
		PreparedStatement ps = null;
		try {
			for(int id : approved)
			{
			ps = con.prepareStatement("update customer SET app = 1 where uid = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			}
			answer = true;
			str = "Customers approved successfully!";
		} catch (SQLException e) {
			
			str = "An error with the server connection";
			answer = false;
			e.printStackTrace();
		}
		
		
	}
	
	

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof ConfirmationRequest)
		{
			ConfirmationRequest rq = (ConfirmationRequest)arg1;
			
			if(rq.getType() == RequestEnum.Select)
			{
				getCustomersFromDB();
				if(answer)
					server.setResponse(new ConfirmationResponse(customers));
				else
					server.setResponse(new booleanResponse(answer,str));
			}
			
			else if(rq.getType() == RequestEnum.Insert)
			{
				
				updateApproved(rq.getApproved());
				server.setResponse(new booleanResponse(answer,str));
			}
		}
		
	}


}
