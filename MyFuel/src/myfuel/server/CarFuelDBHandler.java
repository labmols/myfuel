package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

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
	
	private Response getInventory()
	{
		ArrayList <StationInventory> sInventory = new ArrayList<StationInventory>();
		ArrayList <Float> fQty =null;
		ArrayList <Float> mQty =null;
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
				
			for(Station s : stations)
			{
			ps= con.prepareStatement("select fqty,mqty from station_inventory where sid=?");
			ps.setInt(1, s.getsid());
			rs= ps.executeQuery();
			while(rs.next()) // all the sid fuel qty
			{
				fQty = new ArrayList<Float>();
				mQty = new ArrayList<Float>();
				fQty.add(rs.getFloat(1));
				mQty.add(rs.getFloat(2));
				
			}
			
			StationInventory si = new StationInventory(s,fQty,mQty);
			sInventory.add(si);
			}
			
			return new CarFuelResponse(sInventory);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new booleanResponse(false, "SQL Error!");
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
				server.setResponse(getInventory());
			}
			
		}
	}

}
