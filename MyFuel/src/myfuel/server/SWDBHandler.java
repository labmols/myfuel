package myfuel.server;

import java.sql.*;
import java.util.Observable;
import java.sql.PreparedStatement;

import myfuel.request.SWRequest;
import myfuel.response.booleanResponse;

public class SWDBHandler extends DBHandler{
	private int sid;
	private int[] q;
	private boolean answer;
	private String str;
	public SWDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	
	/***
	 * set new quantities in the DB to the worker's station
	 */
	void set_new_quantities()
	{
		PreparedStatement ps = null;
		try{
			for(int i=0; i < 3 ;i++)
			{
				ps = con.prepareStatement("update station_inventory SET fqty = ? WHERE sid = ? and fuelid = ?");
				ps.setInt(1,q[i]);
				ps.setInt(2, sid);
				ps.setInt(3, i+1);
				ps.executeUpdate();
			}
			answer = true;
			str = "Quantities has been updated succesfully";
		}catch(SQLException e)
		{
			answer = false;
			str = "There was an error with the server";
			e.printStackTrace();
		}
		
		
	}
	
	/***
	 * Handle request from the client
	 */
	
	@Override
	public void update(Observable arg0, Object arg1)
	{
		if(arg1 instanceof SWRequest)
		{
			SWRequest rq = (SWRequest)arg1;
			this.sid = rq.getSid();
			this.q = rq.getQ();
			set_new_quantities();
			server.setResponse(new booleanResponse(answer,str));
		}
		
	}

}
