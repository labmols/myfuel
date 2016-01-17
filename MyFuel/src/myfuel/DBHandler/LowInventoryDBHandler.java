package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.Entity.FuelQty;
import myfuel.request.LowInventoryRequest;
import myfuel.request.SetNewRatesRequest;
import myfuel.response.LowInventoryResponse;
import myfuel.response.SetNewRatesResponse;
import myfuel.response.booleanResponse;
import myfuel.server.MyFuelServer;

/***
 * Updates / Gets The details about the quantities in a station
 * @author karmo
 *
 */
public class LowInventoryDBHandler  extends DBHandler {
	/***
	 * The request that sent from the client
	 */
	private LowInventoryRequest request;
	/***
	 * Will be false if there was an error with the Query
	 */
	private boolean answer =true;
	/***
	 * Message about the status of the update
	 */
	private String msg;
	/***
	 * Current Quantities in the station
	 */
	private ArrayList<FuelQty> qty;

	/***
	 * LowInventoryDBHandler Constructor
	 * @param server - MyFuelServer
	 * @param con - JDBC
	 */
	public LowInventoryDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}

	/***
	 * Updating New Low Inventory Level
	 */
	public void UpdateLowInventory()
	{
		PreparedStatement ps = null;
		try{
			
			for(int i=0; i < 3 ;i++)
			{
				ps = con.prepareStatement("update station_inventory SET mqty = ? WHERE sid = ? and fuelid = ?");
				ps.setFloat(1,request.getNewLowInventory().get(i));
				ps.setInt(2,request.getSid());
				ps.setInt(3, i+1);
				ps.executeUpdate();
			}
			
		answer = true;
		msg = "Minimal Quantity has been updated succesfully";
			}
		catch(SQLException e)
		{
			answer = false;
			msg = "There was an error with the server";
			e.printStackTrace();
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
		if(arg1 instanceof LowInventoryRequest)
		{
			
			this.request = ((LowInventoryRequest)arg1);
			if(request.getNewLowInventory() == null)
			{
				getLevels();
				
				if(answer)
				{
					server.setResponse(new LowInventoryResponse(qty));
				}
				else
					server.setResponse(new booleanResponse(answer,msg));
				
			}
			
			else
			{	
			 UpdateLowInventory();
			 server.setResponse(new booleanResponse(answer,msg));
			}
		}
	}


	/**
	 * Getting Inventory Details for a Station
	 */
	private void getLevels() 
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			
			ps = con.prepareStatement("select fuelid,fqty,mqty from station_inventory where sid = ?");
			ps.setInt(1, request.getSid());
			rs= ps.executeQuery();
			qty = new ArrayList<FuelQty>();
			while(rs.next())
			{
				
				qty.add(new FuelQty(rs.getInt(1),rs.getFloat(2),rs.getFloat(3)));
			}
			
			answer = true;
			
		}catch(SQLException e )
		{
			e.printStackTrace();
			answer = false;
			msg = "There was an error with the server";
		}
		
	}
	







}
