package myfuel.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;
import java.sql.PreparedStatement;

import myfuel.client.*;
import myfuel.request.RequestEnum;
import myfuel.request.SWRequest;
import myfuel.response.booleanResponse;
import myfuel.response.inventoryResponse;

/***
 * Station Worker DBHandler  - will get and set Inventory Order Details to the worker's station
 * @author karmo
 *
 */
public class SWDBHandler extends DBHandler{
	/***
	 * True if query is ok and false otherwise
	 */
	private boolean answer;
	/***
	 * String the explain the status of the action
	 */
	private String str;
	/***
	 * Order details for he worker's station
	 */
	private InventoryOrder order;
	/***
	 * SWDBHandler Constructor
	 * @param server - MyFUelServer
	 * @param con - JDBC
	 */
	public SWDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
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
			
			
			if(rq.getType() == RequestEnum.Select)
			{
				getNewOrders(rq.getSid());
				if(answer)
					server.setResponse(new inventoryResponse(order));
				else
					server.setResponse(new booleanResponse(answer,str));
			}
			
			else if (rq.getType() == RequestEnum.Insert)
			{
				
				addInventoryOrder(rq.getSid());
				server.setResponse(new booleanResponse(answer,str));
			}
			
		}
		
		
		
	}

	/***
	 * add fuel supply to the inventory of the station
	 * @param sid  - station ID
	 */
	private void addInventoryOrder(int sid)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<Float> qty = new ArrayList<Float>();
		ArrayList<Integer> id = new ArrayList<Integer>();
		
		try{
			ps = con.prepareStatement("select fuelid,qty from inventory_order where sid = ?");
			ps.setInt(1,sid);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				id.add(rs.getInt(1));
				qty.add(rs.getFloat(2));
				
			}
			
			for(int i=0;i < qty.size();i++)
			{
				ps = con.prepareStatement("update station_inventory SET fqty = fqty + ? where sid = ? and fuelid = ?");
				ps.setFloat(1,qty.get(i));
				ps.setInt(2, sid);
				ps.setInt(3, id.get(i));
				
				ps.executeUpdate();
			}
			
			for(int i : id)
			{
				ps = con.prepareStatement("DELETE from inventory_order where sid  = ? and fuelid = ? and status = 1 ");
				ps.setInt(1, sid);
				ps.setInt(2, i);
				
				ps.executeUpdate();
			}
			answer = true;
			str = "Inventory has been updated!";
			
		}catch(SQLException e)
		{
			answer = false;
			str = "There was an error with the server";
		}
		
	}


/***
 * get new orders from the DB
 * @param sid  - Station ID
 */
	private void getNewOrders(int sid) 
	{
		ResultSet rs ;
		PreparedStatement ps = null;
		Station s = null ;
		ArrayList<FuelQty> qty = new ArrayList<FuelQty>();
		try{
			
			ps = con.prepareStatement("select name from station_in_network where sid = ?");
			ps.setInt(1, sid);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				s = new Station(sid,rs.getString(1));
			}
			
			ps = con.prepareStatement("select f.fname,i.qty,i.fuelid"
									+ " from inventory_order as i ,fuel_price as f "
											+ "where i.sid = ? and i.status = 1 "
												+ "and f.fuelid = i.fuelid");
			ps.setInt(1, sid);
			
			rs = ps.executeQuery();
			
		if(!rs.next())
			{
				answer = false;
				str = "There are no new Inventory Orders!";
				return;
			} 
		
			rs.previous();
			
			while(rs.next())
			{
				qty.add(new FuelQty(rs.getString(1),rs.getInt(3),rs.getInt(2)));
			}
			
			 order = new InventoryOrder(s,qty);
			 answer = true;
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server";
		}
	}

}
