package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import com.mysql.*;

import myfuel.client.Promotion;
import myfuel.client.PromotionReport;
import myfuel.request.MMRerportsRequest;
import myfuel.response.MMReportsResponse;

public class MMReportDBHandler extends DBHandler{
private ArrayList<PromotionReport> pr = null;
private ArrayList<Promotion> names = null;
/***
 * Constructor for  MMReportDBHandler class
 * @param server - MyFuelServer
 * @param con - JDBC
 */
	public MMReportDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	/***
	 * This method will get the Marketing Manager Promotion Report details
	 * The details will be set into 2 ArrayList that are Attributes in this class
	 * 
	 */
	private void getPromotionReportDetails()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		pr = new ArrayList<PromotionReport>();
		
		try{
			
			ps = con.prepareStatement(" select t.pname,c.uid,c.fname,c.lname,p.qty,c.toc,p.bill,f.fname,p.pdate,p.prid"
					+ "					from customer as c, purchase as p, customer_purchase as cp, promotion as pr, prom_temp as t ,fuel_price as f"
					+ "					where c.uid = cp.uid and cp.pid = p.pid"
					+ "					and p.prid = pr.pid and pr.tid = t.tid"
					+ "					and f.fuelid = p.fuelid ");
		
			rs = ps.executeQuery();
			while(rs.next())
			{
				pr.add(new PromotionReport(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getInt(6),rs.getFloat(7),rs.getString(8),rs.getDate(9),rs.getInt(10)));
			}
			
			names = new ArrayList<Promotion>();
			ps = con.prepareStatement("select t.pname,p.sdate,p.fdate,f.fname,p.pid,t.discount from prom_temp as t , promotion as p,fuel_price as f where p.tid = t.tid and f.fuelid = t.fid");
			rs = ps.executeQuery();
			
			while(rs.next())
				names.add(new Promotion(rs.getString(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getInt(5),rs.getFloat(6)));
			
		}
		catch(SQLException e )
		{
			e.printStackTrace();
		}
		
	}
	
	
	/***
	 * Will receive a request from the client and handle 
	 * After it will Handle the request the Response will be sent back
	 */
	
	@Override
	public void update(Observable arg0, Object arg1) {
	
		if(arg1 instanceof MMRerportsRequest)
		{
			getPromotionReportDetails();
			server.setResponse(new MMReportsResponse(pr,names));
		}
		
	}
	

}
