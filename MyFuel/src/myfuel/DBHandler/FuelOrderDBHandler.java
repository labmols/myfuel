package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.Client.*;
import myfuel.Entity.Fuel;
import myfuel.Entity.FuelQty;
import myfuel.Entity.HomeOrder;
import myfuel.Entity.Network;
import myfuel.Entity.NetworkRates;
import myfuel.Entity.Promotion;
import myfuel.Entity.Purchase;
import myfuel.Entity.Rate;
import myfuel.Entity.Station;
import myfuel.Entity.StationInventory;
import myfuel.Request.FuelOrderRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.FuelOrderResponse;
import myfuel.Response.Response;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;
import myfuel.Tools.MailThread;

/**
 * Car Fuel Database Handler, responsible for all the related Car fuel queries.
 * @author Maor
 *
 */
public class FuelOrderDBHandler extends DBHandler{
	
	/***
	 * The factor for inventory order
	 */
	private static final float factor = 5;
	/**
	 * Contains the customer purchase id.
	 */
	private int CustomerP;

	/**
	 * Create new Car Fuel Database controller.
	 * @param server - My Fuel Server.
	 * @param con - JDBC Driver connection.
	 */
	public FuelOrderDBHandler(MyFuelServer server, Connection con) {
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

			try {
				//Get all home orders
				ps= con.prepareStatement("select t2.uid, t1.orid , t1.adr, t1.sdate, t1.status,t1.urgent,t1.pid from home_order t1, customer_home_order t2 where t2.uid = ? and t1.orid = t2.orid");
				ps.setInt(1, customerID);
				rs1 = ps.executeQuery();
				while(rs1.next())
				{
				ps = con.prepareStatement("select * from purchase where pid = ?");
				ps.setInt(1, rs1.getInt(7));
				rs2 = ps.executeQuery();
				rs2.next();
				Purchase pur = new Purchase(rs1.getInt(1),rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getTimestamp(5),rs2.getFloat(6),rs2.getFloat(7),null,-1,rs2.getBoolean(8));
				horders.add(new HomeOrder (rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getBoolean(5), rs1.getBoolean(6),pur));
				}
				ps.close();
				rs1.close();
				if(rs2!=null) rs2.close();
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
	 * Get all stations details available from the Database.
	 * @return - ArrayList<Station> that contains all the stations info.
	 */
	private ArrayList<Station> getStations()
	{
		ResultSet rs = null;
		ResultSet rs2 = null;
		Statement st = null;
		PreparedStatement ps = null;
		ArrayList<Station> stations = new ArrayList<Station>();
		int id;
		String name;
		
			try {
				st = con.createStatement();
				String query = "select * from station_in_network";
				rs = st.executeQuery(query);
				while(rs.next())
				{
					ps = con.prepareStatement("select * from network where sid = ?");
					ps.setInt(1, rs.getInt(2));
					rs2 = ps.executeQuery();
					if(rs2.next())
					{
						id = rs.getInt(1);
						name = rs.getString(3);
						stations.add(new Station(id,name,new Network(rs2.getInt(1),rs2.getString(2))));
					}
					
				}
				
				rs.close();
				rs2.close();
				ps.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return stations;
			
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
			ps = con.prepareStatement("insert into home_order values(?,?,?,?,?,?)");
			ps.setInt(1, nextid);
			ps.setInt(2, CustomerP);
			ps.setString(3, order.getAddress());
			ps.setTimestamp(4, new java.sql.Timestamp(order.getShipDate().getTime()));
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
			if(p.getSid() == -1)
	        ps.setNull(2,java.sql.Types.INTEGER);
			else ps.setInt(2, p.getSid());
			ps.setInt(3, p.getFuelid());
			if(p.getPromid() != -1)
			ps.setInt(4, p.getPromid());
			else ps.setNull(4,java.sql.Types.INTEGER);
			ps.setTimestamp(5, new java.sql.Timestamp(p.getPdate().getTime()));
			ps.setFloat(6, p.getBill());
			ps.setFloat(7, p.getQty());
			ps.setBoolean(8, p.isPaid());
			ps.executeUpdate();
			ps.close();
			
			//Insert into customer_purchase table
			ps = con.prepareStatement("insert into customer_purchase values(?,?,?,?)");
			ps.setInt(1, p.getCustomerid());
			ps.setInt(2, nextid);
			if(p.getCustomerCarID() == -1)
			ps.setNull(3, java.sql.Types.INTEGER);
			else ps.setInt(3, p.getCustomerCarID());
			if(p.getDriverName() == null)
				ps.setNull(4, java.sql.Types.VARCHAR);
				else ps.setString(4, p.getDriverName());
			ps.executeUpdate();
			ps.close();
			
			if(p.getSid() != -1)
			updateInventory(p.getSid(), p.getFuelid(), p.getQty());
			else updateHomeInventory(p.getQty());
		
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
			
			ps= con.prepareStatement("select fqty,mqty from station_inventory where sid=? and fuelid=?");
			ps.setInt(1, sid);
			ps.setInt(2, FuelID);
			rs = ps.executeQuery();
			rs.next();
			fqty = rs.getFloat(1);
			mqty = rs.getFloat(2);
			//if the current qty < minimalQty and there is no order create new inventory order
			insertInventoryOrder(sid,FuelID, fqty, mqty);
			
			ps.close();
			rs.close();
			
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
	 * Update specific station inventory after fuel purchase.
	 * @param si
	 */
	private void updateHomeInventory(float qty)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		float mqty,fqty;
		
		try {
			ps= con.prepareStatement("update home_inventory SET fqty=fqty-?");
			ps.setFloat(1,qty);
			ps.executeUpdate();
			
			ps= con.prepareStatement("select fqty,mqty from home_inventory");
			rs = ps.executeQuery();
			rs.next();
			fqty = rs.getFloat(1);
			mqty = rs.getFloat(2);
			//if the current qty < minimalQty and there is no order create new inventory order
			insertInventoryOrder(-1,Fuel.HomeFuelID, fqty, mqty);
			
			ps.close();
			rs.close();
			
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
	 * Insert new inventory order for the specific Station and Fuel type.
	 * @param sid - Station id number.
	 * @param FuelID - Fuel ID.
	 * @param fqty - Fuel order amount.
	 * @param mqty - Minimal fuel amount.
	 */
	private void insertInventoryOrder(int sid, int FuelID, float fqty, float mqty)
	{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String fuelType="";
		try {
			if(sid != -1)
			{
			ps= con.prepareStatement("select * from inventory_order where sid=? and fuelid=?");
			ps.setInt(1, sid);
			ps.setInt(2, FuelID);
			rs= ps.executeQuery();
			}
			else
			{
				ps= con.prepareStatement("select * from inventory_order where sid is null");
				rs= ps.executeQuery();
			}
			
			if(fqty < mqty && !rs.next())
			{
				ps= con.prepareStatement("insert into inventory_order values(?,?,?,?,?)");
				ps.setInt(1, 0);
				if(sid != -1)
				ps.setInt(2, sid);
				else ps.setNull(2, java.sql.Types.INTEGER);
				ps.setInt(3, FuelID);
				ps.setFloat(4, factor*mqty);
				ps.setInt(5, 0);
				ps.executeUpdate();
				
				
				ps= con.prepareStatement("select fname,lname,email from worker where (role=8 or role=6) and sid=?");
				ps.setInt(1, sid);
				rs= ps.executeQuery();
				if(rs.next())
				{
				String fname = rs.getString(1);
				String lname = rs.getString(2);
				String email = rs.getString(3);
				fuelType="";
				
				
				if(FuelID== Fuel.Fuel95ID) fuelType= "95";
					else if(FuelID ==Fuel.FuelDiesel) fuelType="Diesel";
						else if (FuelID ==Fuel.FuelScooter) fuelType="Scooter";
							else if(FuelID == Fuel.HomeFuelID) fuelType="Home Fuel";
				
				String subject="New Inventory Order";
				String content= "Dear " + fname + " " +lname + ",\n"
						+ "New inventory order has been created: "
						+ "\n\n" + "Fuel Type: " + fuelType + 
						  "\n"   +"Amount: " + factor*mqty + " Liters" 
						+ "\n\n\n" +"MyFuel System.";
				
				MailThread mailT = new MailThread(email,subject,content);
				new Thread(mailT).start();
				}
				
				ps=con.prepareStatement("insert into message values(0,NULL,?,?,1)");
				if(sid == -1)
					  ps.setNull(1, java.sql.Types.INTEGER);
			       ps.setInt(1, sid);
			       ps.setString(2,"New Inventory Order for Fuel type "+ fuelType);
			       ps.executeUpdate();
				 
			}
	
			ps.close();
			rs.close();
			
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
	private ArrayList<Promotion> getPromotions()
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Promotion> promList = new ArrayList<Promotion>();
		
		try {
		
			ps= con.prepareStatement("SELECT p.tid, t.pname, t.discount, t.shour, t.fhour, p.sdate, p.fdate, t.ctype, t.fid, p.pid"
								     + " FROM promotion p, prom_temp t"
								     +" WHERE p.tid = t.tid"
								     +" AND DATEDIFF( sdate, CURDATE( ) ) <=0"
								     +" AND DATEDIFF( fdate, CURDATE( ) ) >=0 "
								     + " AND CURTIME() > t.shour"
								     + " AND CURTIME() < t.fhour"
								     + " ORDER BY t.discount desc");
			rs= ps.executeQuery();
		
			while(rs.next()) 
				promList.add(new Promotion(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getTime(4), 
					rs.getTime(5), rs.getDate(6), rs.getDate(7), rs.getInt(8),rs.getInt(9),rs.getInt(10)));
			ps.close();
			rs.close();
			return promList;
			
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
	 * Get all current rates(according to sale model) from the Database.
	 * @return List of all the current rates.
	 */
	private ArrayList <NetworkRates> getRates()
	{
		ArrayList<NetworkRates> networkRates = new ArrayList<NetworkRates>();
		ArrayList<Rate> rates = new ArrayList<Rate>();
		ResultSet rs = null;
		ResultSet rs2 = null;
		Statement st = null;
		PreparedStatement ps = null;
		NetworkRates netRates;
		Network n;
		String sql;
		
		try {
			st= con.createStatement();
			sql = "select * from network";
			rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				rates = new ArrayList<Rate>();
				n = new Network (rs.getInt(1), rs.getString(2));
				ps= con.prepareStatement("select * from network_rate where nid=?");
				ps.setInt(1, n.getNid());
				rs2 = ps.executeQuery();
				while(rs2.next())
				{
				Rate r = new Rate (rs2.getInt(2), rs2.getFloat(3));
				rates.add(r);
				}
				netRates = new NetworkRates(n.getNid(), rates);
				networkRates.add(netRates);
			}
			return networkRates;
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
			sql = "select sid,name from station_in_network";
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
	
	private FuelQty getHomeInventory() {
		// TODO Auto-generated method stub
		FuelQty homeInventory=null;
		ResultSet rs = null;
		Statement st = null;
		String sql;
		
		try {
			st= con.createStatement();
			sql = "select fqty,mqty from home_inventory";
			rs = st.executeQuery(sql);
			
			if(rs.next()) 
				homeInventory = new FuelQty(Fuel.HomeFuelID, rs.getFloat(1), rs.getFloat(2));
			rs.close();
			st.close();
			return homeInventory;
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
				ArrayList <NetworkRates> networkRates = getRates();
				ArrayList<Promotion> promList = getPromotions();
				ArrayList <HomeOrder> horders = null;
				ArrayList<Station> stations ;
				FuelQty homeInventory=null ;
				
				if(req.getFuelID() == Fuel.HomeFuelID) 
				{
				 horders = getHomeOrders(req.getCustomerID());
				 stations = null;
				 homeInventory = getHomeInventory();
				}
				
				else stations = getStations();
				FuelOrderResponse res = new FuelOrderResponse (si,fuels, promList ,horders,networkRates,stations,homeInventory);
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
