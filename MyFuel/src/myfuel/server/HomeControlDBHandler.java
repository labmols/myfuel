package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.request.RequestEnum;
import myfuel.request.homeQtyOrderRequest;
import myfuel.response.HomeQtyResponse;
import myfuel.response.booleanResponse;

public class HomeControlDBHandler extends DBHandler
{
	private FuelQty minimal ; 
	private FuelQty order ; 
	private boolean answer ; 
	private String str = "";
	/***
	 *  Create HomeControl DB Handler
	 * @param server  - MyFuelServer
	 * @param con - JDBC driver Connection
	 */
	public HomeControlDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	/***
	 * Getting the details From the DB
	 * Home FUel Order (If Exist)
	 * Current Home FUel Quantity
	 * Current Low level Value
	 */
	private void getDetails()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		try{
			
			ps = con.prepareStatement("select fqty,mqty from station_inventory where sid = 4");
			
			rs = ps.executeQuery();
			while(rs.next())
				minimal = new FuelQty(4,rs.getFloat(1),rs.getFloat(2)); // save minimal and current level
			
			ps = con.prepareStatement("select fuelid,qty from inventory_order where sid = 4");
			rs = ps.executeQuery();
			
			while(rs.next())
				order = new FuelQty(rs.getInt(1),rs.getFloat(2),-1); // save fuel id and quantity in the order	
			
			answer = true;
			
		}catch(SQLException e )
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server";
		}
	}
	
	/***
	 * Setting new low inventory level from home fuel
	 * @param lvl - New Low Inventory Level
	 */
	private void setLow(Float lvl) 
	{
		PreparedStatement ps = null;
		
		try{
			ps = con.prepareStatement("update  station_inventory SET mqty = ? where sid = 4 and fuelid = 4 ");
			ps.setFloat(1,lvl);
			
			ps.executeUpdate();
			
			answer = true;
			str = "New level has been updated!";
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server";
		}
		
	}
	/***
	 * Adding order Quantity to Home Fuel Inventory
	 */
	private void setOrder() 
	{
		PreparedStatement ps = null;
		try{
			String sql = "(select qty from inventory_order where sid = 4 and fuelid = 4 )";
			ps = con.prepareStatement("update  station_inventory SET fqty = fqty + " + sql
												+ "where sid = 4 and fuelid = 4 ");
	
			ps.executeUpdate();
			
			ps = con.prepareStatement("DELETE from inventory_order where sid  = 4 and fuelid =4  ");
			
			ps.executeUpdate();
			
			answer = true;
			str = "New order has been added to inventory!";
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server";
		}
	}

	
	/***
	 * Getting the request object from the client and handling it due to request's type
	 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof homeQtyOrderRequest)
		{
			order = null;
			homeQtyOrderRequest request = (homeQtyOrderRequest)arg1;
			if(request.getType() == RequestEnum.HomeGet)
			{
				getDetails();
				if(answer)
					server.setResponse(new HomeQtyResponse(minimal,order));
				else
					server.setResponse(new booleanResponse(answer,str));
				
			}
			
			else if(request.getType() == RequestEnum.HomeSetLow)
			{
				setLow(request.getLowLvl());
				server.setResponse(new booleanResponse(answer,str));
			}
			
			else if(request.getType() == RequestEnum.HomeSetOrder)
			{
				setOrder();
				server.setResponse(new booleanResponse(answer,str));
				
			}
		}
		
	}
	
	

}
