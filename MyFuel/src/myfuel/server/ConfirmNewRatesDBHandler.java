package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.request.ConfirmNewRatesRequest;
import myfuel.response.ConfirmRatesResponse;
import myfuel.response.booleanResponse;

public class ConfirmNewRatesDBHandler extends DBHandler{
	
	private ConfirmNewRatesRequest request;
	private boolean answer;
	private ArrayList<Fuel> fuels;
	private String str;
	
	/***
	 *  Create Confirm New Rates  DB Handler
	 * @param server  - MyFuelServer
	 * @param con - Connection to client
	 */
	public ConfirmNewRatesDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}
	/***
	 * check if there are any suggested rates in the DB
	 * if there are it will create an ArrayList<Fuel>
	 * if not it will set null value for the return response
	 */
	void check_suggested_rates()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
	
		try{
			ps = con.prepareStatement("select * from suggest_fuel");
			rs = ps.executeQuery();
			
			if(!rs.next())
			{
				answer = false;
				str = "There are no suggested rates";
			}
			else
			{
				
				answer = true;
				get_prices();
			}
			
			
		}catch(SQLException e){
			answer = false;
			str = "There was an error with the server";
			e.printStackTrace();
		}
	}
	
	/***
	 * getting the fuel prices from the DB and put it in the ArrayList<Fuel> fuels
	 */
	
	void get_prices()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		fuels = new ArrayList<Fuel>();
		
		try{
			ps = con.prepareStatement("SELECT s.price,p.price,p.maxprice "
										+"FROM fuel_price as p , suggest_fuel as s "
										+"WHERE p.fuelid = s.fid");
			rs = ps.executeQuery();
			while(rs.next())
			{
				fuels.add(new Fuel(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3)));
			}
			
		}catch(SQLException e){
			answer = false;
			str = "There was an error with the server";
			e.printStackTrace();
		}
		
	}
	
	/***
	 * Setting the suggested rates as the current ones
	 * Delete the suggested rates from their table
	 */
	void set_new_rates()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		ArrayList<Float> price = new ArrayList<Float>();
		
		try {
			ps = con.prepareStatement("SELECT p.price FROM suggest_fuel as p");
			rs = ps.executeQuery();
			
			while(rs.next())
				price.add(rs.getFloat(1));
			
			delete_suggested_rates();
			
			for(int i=0;i<4;i++)
			{
					ps = con.prepareStatement("UPDATE fuel_price SET price = ? WHERE fuelid = ?");
					
					ps.setFloat(1, price.get(i));
					ps.setInt(2,i+1);
					
					ps.executeUpdate();
					
			}
			
			answer = true;
			str = "New rates has been set successfully";
		} catch (SQLException e) {
			answer = false;
			str = "There was an error with the server";
			e.printStackTrace();
		}
		
		
	}
	
	/***
	 * deleting the suggested rates from their table
	 */
	void delete_suggested_rates()
	{
		PreparedStatement ps = null;
		
		try{
		ps = con.prepareStatement("truncate suggest_fuel");
		ps.executeUpdate();
		answer = true ;
		str = "Suggested rates has been deleted";
		} catch(SQLException e)
		{
			answer = false;
			str = "There was an error with the server";
			e.printStackTrace();
		}
		
	}
	
	/***
	 * Handling request from the client
	 */
	
	@Override
	public void update(Observable o, Object arg) 
	{
		
		if(arg instanceof ConfirmNewRatesRequest)
		{
			request = (ConfirmNewRatesRequest)arg;
			
			if(request.getType() == 1)
			{
				check_suggested_rates();
				
				if(answer)
					server.setResponse(new ConfirmRatesResponse(fuels));
				else 
					server.setResponse(new booleanResponse(answer,str));
			}
			
			else if(request.getType() == 2)
			{
				set_new_rates();
				server.setResponse(new booleanResponse(answer,str));
			}
			
			else
			{
				delete_suggested_rates();
				server.setResponse(new booleanResponse(answer,str));
			}
		}
		
	}

}
