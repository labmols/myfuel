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
import myfuel.client.Promotion;
import myfuel.client.Station;
import myfuel.client.StationInventory;
import myfuel.request.FuelInfoRequest;
import myfuel.request.RequestEnum;
import myfuel.response.FuelInfoResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

/**
 * Car Fuel Database Handler, responsible for all the related Car fuel queries.
 * @author Maor
 *
 */
public class FuelInfoDBHandler extends DBHandler{

	FuelInfoDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		// TODO Auto-generated constructor stub
	}
	
	private Promotion getPromotion(int fid)
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
		
			ps= con.prepareStatement("SELECT p.tid, t.pname, t.discount, t.shour, t.fhour, p.sdate, p.fdate, t.ctype, t.fid, p.pid"
								     + " FROM promotion p, prom_temp t"
								     +" WHERE p.tid = t.tid AND t.fid = ?"
								     +" AND DATEDIFF( sdate, CURDATE( ) ) <0"
								     +" AND DATEDIFF( fdate, CURDATE( ) ) >0 "
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
	 * @return ArrayList<StationInventory> that contains all the stations Inventory details.
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
		if(arg instanceof FuelInfoRequest)
		{
			FuelInfoRequest req = (FuelInfoRequest) arg;
			if(req.getType() == RequestEnum.Select)
			{
				ArrayList <Fuel> fuels = getFuels();
				ArrayList <StationInventory> si = getInventory();
				Promotion p = getPromotion(req.getPromotionFuelID());
				FuelInfoResponse res = new FuelInfoResponse (si,fuels, p);
				server.setResponse(res);
			}
			
		}
	}

}
