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

public class SWDBHandler extends DBHandler{
	private int sid;
	private int[] q;
	private boolean answer;
	private String str;
	private InventoryOrder order;
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
			this.sid = rq.getSid();
			
			if(rq.getType() == RequestEnum.Select)
			{
				getNewOrders();
				if(answer)
					server.setResponse(new inventoryResponse(order));
				else
					server.setResponse(new booleanResponse(answer,str));
			}
			
			else if (rq.getType() == RequestEnum.Insert)
			{
				this.sid = rq.getSid();
				addInventoryOrder();
				server.setResponse(new booleanResponse(answer,str));
			}
			
		}
		
		
		
	}

	private void addInventoryOrder()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Float> qty = new ArrayList<Float>();
		ArrayList<Integer> id = new ArrayList<Integer>();
		
		try{
			ps = con.prepareStatement("select fuelid,qty from inventory_order where sid = ?");
			ps.setInt(1,this.sid);
			
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
				ps.setInt(2, this.sid);
				ps.setInt(3, id.get(i));
				
				ps.executeUpdate();
			}
			
			for(int i : id)
			{
				ps = con.prepareStatement("DELETE from inventory_order where sid  = ? and fuelid = ? and status = 1 ");
				ps.setInt(1, this.sid);
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



	private void getNewOrders() 
	{
		ResultSet rs ,check;
		PreparedStatement ps = null;
		Station s = null ;
		ArrayList<Float> qty = new ArrayList<Float>();
		ArrayList<String> fnames = new ArrayList<String>();
		try{
			
			ps = con.prepareStatement("select sname from station where sid = ?");
			ps.setInt(1, this.sid);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				s = new Station(this.sid,rs.getString(1));
			}
			
			ps = con.prepareStatement("select f.fname,i.qty"
									+ " from inventory_order as i ,fuel_price as f "
											+ "where i.sid = ? and i.status = 1 "
												+ "and f.fuelid = i.fuelid");
			ps.setInt(1, sid);
			
			rs = ps.executeQuery();
			
			if(!rs.first())
			{
				answer = false;
				str = "There are no new Inventory Orders!";
				return;
			}
			
			while(rs.next())
			{
				fnames.add(rs.getString(1));
				qty.add(rs.getFloat(2));
			}
			
			 order = new InventoryOrder(s,qty,fnames);
			 answer = true;
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server";
		}
	}

}
