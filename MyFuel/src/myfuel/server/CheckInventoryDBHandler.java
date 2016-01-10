package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.FuelQty;
import myfuel.request.CheckInventoryRequest;
import myfuel.response.CheckInventoryResponse;
import myfuel.response.booleanResponse;

public class CheckInventoryDBHandler  extends DBHandler {
	private CheckInventoryRequest request;
	private ArrayList<FuelQty> NewOrder;
	private String msg;
	private boolean Answer;

	public CheckInventoryDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}

	public void getInventoryOrder()
	{
		NewOrder=new ArrayList<FuelQty> ();
		Answer=true;
		ResultSet rs = null ;
		PreparedStatement ps = null;
		try {
			
			ps = con.prepareStatement("select * from inventory_order where sid=?");
			ps.setInt(1, request.getSid());
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getInt(5)==0)
				{
					NewOrder.add(new FuelQty(rs.getInt(3),rs.getFloat(4),0));
				}
			}
			if(NewOrder.isEmpty())
			{
				Answer=false;
				msg="There are no Order waiting";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void UpdateInventory()
	{
		msg="";
		PreparedStatement ps = null;
		ArrayList <Integer> FuelIid=request.getFuelId();
		try{
			for(int i=1;i<4;i++)
			{
				if(FuelIid.contains(i))
				{
					ps=con.prepareStatement("update inventory_order SET status=1 where sid=? and fuelid=?");
					msg+="The inventory to Fuel id "+i+" was Confirm\n";
				}
				else
				{
					ps=con.prepareStatement("DELETE from inventory_order where sid=? and fuelid=? and status=0");
				}
				ps.setInt(1,request.getSid());
				ps.setInt(2, i);
				ps.executeUpdate();
			}
			
			ps = con.prepareStatement("DELETE from message where sid = ? and type = 1 ");
			ps.setInt(1,request.getSid());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof CheckInventoryRequest)
		{
			 setRequest((CheckInventoryRequest)arg1);
			 
			 if(request.getType() == 0)
			 {
				 getInventoryOrder();
				 if(!Answer)
					 server.setResponse(new booleanResponse(Answer,msg));
				 else
					 server.setResponse(new CheckInventoryResponse(NewOrder));
			 }
			 else if(request.getType() == 1)
			 {
				 UpdateInventory();
				 if(msg.equals(""))
					 msg="All the Orders Fuel are Denied";
				 server.setResponse(new booleanResponse(true,msg));
			 }
		}
	}
	
	public CheckInventoryRequest getRequest() {
		return request;
	}

	public void setRequest(CheckInventoryRequest request) {
		this.request = request;
	}

}
