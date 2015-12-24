package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.FuelQty;
import myfuel.client.Station;
import myfuel.client.StationInventory;
import myfuel.request.CarFuelRequest;
import myfuel.request.RequestEnum;
import myfuel.response.CarFuelResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

public class CarFuelDBHandler extends DBHandler{

	CarFuelDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
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
	
	}
	
	/**
	 * 
	 * @return
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
			sql = "select sid,sname from station where sid!=4";
			rs = st.executeQuery(sql);
			while(rs.next()) //Add all stations
			{
				stations.add(new Station(rs.getInt(1), rs.getString(2)));
			}
				
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
			
			return sInventory;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof CarFuelRequest)
		{
			CarFuelRequest req = (CarFuelRequest) arg;
			if(req.getType() == RequestEnum.Select)
			{
				ArrayList <Fuel> fuels = getFuels();
				ArrayList <StationInventory> si = getInventory();
				CarFuelResponse res = new CarFuelResponse (si,fuels);
				server.setResponse(res);
			}
			
		}
	}

}
