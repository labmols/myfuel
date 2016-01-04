package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.client.HomeOrder;
import myfuel.client.Promotion;
import myfuel.client.Purchase;
import myfuel.client.Station;
import myfuel.client.StationInventory;
import myfuel.request.FuelOrderRequest;
import myfuel.request.RequestEnum;
import myfuel.response.FuelOrderResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

/**
 * Car Fuel Database Handler, responsible for all the related Car fuel queries.
 * @author Maor
 *
 */
public class FuelOrderDBHandler extends DBHandler{
	
	/**
	 * Contains the customer purchase id.
	 */
	private int CustomerP;

	/**
	 * Create new Car Fuel Database controller.
	 * @param server - My Fuel Server.
	 * @param con - JDBC Driver connection.
	 */
	FuelOrderDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get all home fuel orders for a specific customer id.
	 * @param customerID - The customer id number.
	 * @return List of all the customer home orders.
	 */
	private ArrayList<HomeOrder> getHomeOrders(int customerID) {
		// TODO Auto-generated method stub
		ArrayList<HomeOrder> horders = new ArrayList<HomeOrder>();
		ResultSet rs1,rs2 = null;
		PreparedStatement ps = null;
		Statement st;

			try {
				//Update order status query
				st = con.createStatement();
				st.executeUpdate("update home_order SET status=1 where datediff(curdate(),sdate) > 0 or (datediff(curdate(),sdate)=0 and TIMESTAMPDIFF(HOUR,sdate,NOW()) >=6)");
				st.close();
				//Get all home orders query after status update.
				ps= con.prepareStatement("select t2.uid, t1.orid, t1.qty, t1.adr, t1.sdate, t1.status,t1.urgent,t1.pid from home_order t1, customer_home_order t2 where t2.uid = ? and t1.orid = t2.orid");
				ps.setInt(1, customerID);
				rs1 = ps.executeQuery();
				while(rs1.next())
				{
				ps = con.prepareStatement("select * from purchase where pid = ?");
				ps.setInt(1, rs1.getInt(8));
				rs2 = ps.executeQuery();
				rs2.next();
				Purchase pur = new Purchase(rs1.getInt(1),rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getTimestamp(5),rs2.getFloat(6),rs2.getFloat(7),rs2.getString(8));
				horders.add(new HomeOrder (rs1.getInt(1), rs1.getInt(2), rs1.getFloat(3), rs1.getString(4), rs1.getTimestamp(5), rs1.getBoolean(6), rs1.getBoolean(7),pur));
				}
				ps.close();
				rs1.close();
				rs2.close();
				return horders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Insert new Home Fuel Order to the Database.
	 * @param order - The new Order object.
	 * @return - true if the insertion succeed and false otherwise(SQL Error, etc).
	 */
	private boolean insertHomeOrder(HomeOrder order, int CustomerP)
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
			ps = con.prepareStatement("insert into home_order values(?,?,?,?,?,?,?)");
			ps.setInt(1, nextid);
			ps.setInt(2, CustomerP);
			ps.setFloat(3, order.getQty());
			ps.setString(4, order.getAddress());
			ps.setTimestamp(5, new java.sql.Timestamp(order.getShipDate().getTime()));
			ps.setBoolean(6, order.isUrgent());
			ps.setBoolean(7, order.getStatus());
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
	
	/**
	 * Insert new Purchase to the Database.
	 * @param p - The new purchase object that contains all the purchase details.
	 * @return - true if the insertion succeed or false otherwise(SQL Error, etc).
	 */ 
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
			this.CustomerP = nextid;
			stmt.close();
			rs.close();
			
			//Insert into purchase table
			ps = con.prepareStatement("insert into purchase values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, nextid);
			ps.setInt(2, p.getSid());
			ps.setInt(3, p.getFuelid());
			if(p.getPromid() != -1)
			ps.setInt(4, p.getPromid());
			else ps.setNull(4,java.sql.Types.INTEGER);
			ps.setTimestamp(5, new java.sql.Timestamp(p.getPdate().getTime()));
			ps.setFloat(6, p.getBill());
			ps.setFloat(7, p.getQty());
			if(p.getDriverName() == null)
			ps.setNull(8, java.sql.Types.VARCHAR);
			else ps.setString(8, p.getDriverName());
			ps.executeUpdate();
			ps.close();
			
			//Insert into customer_purchase table
			ps = con.prepareStatement("insert into customer_purchase values(?,?,?)");
			ps.setInt(1, p.getCustomerid());
			ps.setInt(2, nextid);
			ps.setNull(3, java.sql.Types.INTEGER);
			ps.executeUpdate();
			ps.close();
			
			updateInventory(p.getSid(), p.getFuelid(), p.getQty());
			
		
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

	
	/**
	 * Update specific station inventory after fuel purchase.
	 * @param si
	 */
	private void updateInventory(int sid, int FuelID, float qty)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		float mqty,fqty;
		
		try {
			ps= con.prepareStatement("update station_inventory SET fqty=fqty-? where sid=? and fuelid=?");
			ps.setFloat(1,qty);
			ps.setInt(2, sid);
			ps.setInt(3, FuelID);
			ps.executeUpdate();
			ps.close();
			
			ps= con.prepareStatement("select fqty,mqty from station_inventory where sid=? and fuelid=?");
			ps.setInt(1, sid);
			ps.setInt(2, FuelID);
			rs = ps.executeQuery();
			rs.next();
			fqty = rs.getFloat(1);
			mqty = rs.getFloat(2);
			ps.close();
			rs.close();
			//if the current qty < minimal qty create new inventory order
			if(fqty < mqty)
			{
				ps= con.prepareStatement("insert into inventory_order values(?,?,?,?,?)");
				ps.setInt(1, 0);
				ps.setInt(2, sid);
				ps.setInt(3, FuelID);
				ps.setFloat(4, 5*mqty);
				ps.setInt(5, 0);
				ps.executeUpdate();
				ps.close();	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Get current promotion if exist(according to current time and fuelid).
	 * @param fid - Fuel type id.
	 * @return The promotion details object if exist(if not it will be null).
	 */
	private Promotion getPromotion(int fid)
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
		
			ps= con.prepareStatement("SELECT p.tid, t.pname, t.discount, t.shour, t.fhour, p.sdate, p.fdate, t.ctype, t.fid, p.pid"
								     + " FROM promotion p, prom_temp t"
								     +" WHERE p.tid = t.tid AND t.fid = ?"
								     +" AND DATEDIFF( sdate, CURDATE( ) ) <=0"
								     +" AND DATEDIFF( fdate, CURDATE( ) ) >=0 "
								     + " AND CURTIME() > t.shour"
								     + " AND CURTIME() < t.fhour");
			ps.setInt(1, fid);
			rs= ps.executeQuery();
		
			if(rs.next()) return new Promotion(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getTime(4), 
					rs.getTime(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getInt(9),rs.getInt(10));
			ps.close();
			rs.close();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * Get all fuels available in the Database.
	 * @return ArrayList<Fuel> that contains all the fuels details.
	 */
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
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	/**
	 * Get all stations inventory from the Database.
	 * @return List that contains all the stations Inventory details.
	 */
	private ArrayList <StationInventory> getInventory()
	{
		ArrayList <StationInventory> sInventory = new ArrayList<StationInventory>();
		ArrayList <FuelQty> fQty =null;
		ArrayList <Station> stations = new ArrayList<Station>();
		
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
		String sql;
		
		try {
			st= con.createStatement();
			sql = "select sid,sname from station";
			rs = st.executeQuery(sql);
			while(rs.next()) //Add all stations
			{
				stations.add(new Station(rs.getInt(1), rs.getString(2)));
			}
			st.close();
			rs.close();
			for(Station s : stations)
			{
			fQty = new ArrayList<FuelQty>();
			ps= con.prepareStatement("select fuelid,fqty,mqty from station_inventory where sid=?");
			ps.setInt(1, s.getsid());
			rs= ps.executeQuery();
			while(rs.next())
			{
				FuelQty fq = new FuelQty(rs.getInt(1), rs.getFloat(2), rs.getFloat(3));
				fQty.add(fq);
			}
			
			StationInventory si = new StationInventory(s,fQty);
			sInventory.add(si);
			}
			ps.close();
			rs.close();
			return sInventory;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/**
	 * This method notified by the server when a new client request received, 
	 * Sets the server response to booleanResponse or CarFuelResponse , according to the
	 * client request and SQL Errors.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof FuelOrderRequest)
		{
			FuelOrderRequest req = (FuelOrderRequest) arg;
			if(req.getType() == RequestEnum.Select)
			{
				ArrayList <Fuel> fuels = getFuels();
				ArrayList <StationInventory> si = getInventory();
				Promotion p = getPromotion(req.getFuelID());
				ArrayList <HomeOrder> horders = null;
				if(req.getFuelID() == Fuel.HomeFuelID) 
				 horders = getHomeOrders(req.getCustomerID());
				FuelOrderResponse res = new FuelOrderResponse (si,fuels, p,horders);
				server.setResponse(res);
			}
			
			else if(req.getType() == RequestEnum.Insert)
			{
				Purchase p = req.getPur();
				HomeOrder horder = req.getHOrder();
				boolean s = true;
				if(horder == null) // not home order
					s = insertPurchase(p);
				else 
					s = insertPurchase(p) && insertHomeOrder(horder,CustomerP);
				if(s)
				server.setResponse(new booleanResponse (true,"Success"));
				else 
				server.setResponse(new booleanResponse(false,"Falied"));
				
			}
			
			
		}
	}

	

}
