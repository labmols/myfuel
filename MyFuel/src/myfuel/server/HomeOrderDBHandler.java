package myfuel.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.HomeOrder;
import myfuel.client.PromotionTemplate;
import myfuel.client.Purchase;
import myfuel.request.HomeOrderRequest;
import myfuel.request.RequestEnum;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

/**
 * Home Fuel DB Controller class, Make all queries related to Home Fuel Options.
 * @author Maor
 *
 */
public class HomeOrderDBHandler extends DBHandler{

	public HomeOrderDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	
	private boolean insertHomeOrder(HomeOrder order)
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SHOW TABLE STATUS WHERE `Name` = 'home_order'");
			rs.next();
			int nextid = Integer.parseInt(rs.getString("Auto_increment"));
			stmt.close();
			rs.close();
			
			//Insert into purchase table
			ps = con.prepareStatement("insert into home_order values(?,?,?,?,?,?)");
			ps.setInt(1, nextid);
			ps.setFloat(2, order.getQty());
			ps.setString(3, order.getAddress());
			if(!order.isUrgent())
			ps.setDate(4, new java.sql.Date(order.getShipDate().getTime()));
			else ps.setNull(4, java.sql.Types.DATE);
			ps.setBoolean(5, order.isUrgent());
			ps.setBoolean(6, order.getStatus());
			ps.executeUpdate();
			ps.close();
			
			//Insert into customer_purchase table
			ps = con.prepareStatement("insert into customer_home_order values(?,?)");
			ps.setInt(1, order.getCustomerid());
			ps.setInt(2, nextid);
			
			ps.executeUpdate();
			ps.close();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		 catch (Exception e) {
				e.printStackTrace();
				return false;
				}
		
		return true;
	}
	
	private boolean insertPurchase(Purchase p)
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SHOW TABLE STATUS WHERE `Name` = 'purchase'");
			rs.next();
			int nextid = Integer.parseInt(rs.getString("Auto_increment"));
			stmt.close();
			rs.close();
			
			//Insert into purchase table
			ps = con.prepareStatement("insert into purchase values(?,?,?,?,?,?,?)");
			ps.setInt(1, nextid);
			ps.setInt(2, p.getSid());
			ps.setInt(3, p.getFuelid());
			if(p.getPromid() != -1)
			ps.setInt(4, p.getPromid());
			else ps.setNull(4,java.sql.Types.INTEGER);
			ps.setDate(5, new java.sql.Date(p.getPdate().getTime()));
			ps.setFloat(6, p.getBill());
			ps.setFloat(7, p.getQty());
			
			ps.executeUpdate();
			ps.close();
			
			//Insert into customer_purchase table
			ps = con.prepareStatement("insert into customer_purchase values(?,?,?)");
			ps.setInt(1, p.getCustomerid());
			ps.setInt(2, nextid);
			ps.setNull(3, java.sql.Types.INTEGER);
			ps.executeUpdate();
			ps.close();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		 catch (Exception e) {
		e.printStackTrace();
		return false;
		}
		
		return true;
	}
	
	private ArrayList <Fuel> getFuels()
	{
		ArrayList<Fuel> fuels = new ArrayList<Fuel>();
		ResultSet rs = null;
		Statement st = null;
		String sql;
		
		try {
			st= con.createStatement();
			sql = "select fuelid,maxprice from fuel_price";
			rs = st.executeQuery(sql);
			
			while(rs.next())
				fuels.add(new Fuel (rs.getInt(1), rs.getFloat(2)));
			return fuels;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof HomeOrderRequest)
		{
			HomeOrderRequest req = (HomeOrderRequest) arg;
			if(req.getType() == RequestEnum.Insert)
			{
			if(insertPurchase(req.getPur()) && insertHomeOrder(req.getOrder()))
					server.setResponse(new booleanResponse (true, "Success!"));	
			else server.setResponse(new booleanResponse (false, "SQL Error!"));	
			}
			else 
			{
				
			}
		}
	}

}
