package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;











import myfuel.Entity.CustomerReport;
import myfuel.Entity.Promotion;
import myfuel.Entity.PromotionReport;
import myfuel.Entity.Station;
import myfuel.Request.MMRerportsRequest;
import myfuel.Response.MMReportsResponse;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;
/***
 * This class will has the necessary queries and methods to get the details for the Marketing Manager reports
 *
 */
public class MMReportDBHandler extends DBHandler{
	/***
	 * Will contain the details of the customers the had bought in a promotion
	 */
private ArrayList<PromotionReport> pr = null;
/***
 * Will contain the promotion names and details that exist in MyFuel DB
 */
private ArrayList<Promotion> names = null;
/***
 * Will contain the details for each station that exist in MyFuel DB
 */
private ArrayList<Station> stations = null;
/***
 * Will Contain the details of each customer's behavior for each station 
 */
private ArrayList<CustomerReport> creport= null;
/***
 * will be false if the query failed and true otherwise
 */
private boolean answer ;
/***
 * will contain an error message in case of an exception
 */
private String str;
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
	 * The details will be set into 2 ArrayList which defined as  Attributes in this class
	 * 
	 */
	private void getPromotionReportDetails()
	{
		try{
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
			ps = con.prepareStatement("select t.pname,p.sdate,p.fdate,f.fname,p.pid,t.discount from prom_temp as t , promotion as p,fuel_price as f where p.tid = t.tid and f.fuelid = t.fid"
					+ "					order by p.sdate desc");
			rs = ps.executeQuery();
			
			while(rs.next())
				names.add(new Promotion(rs.getString(1),rs.getDate(2),rs.getDate(3),rs.getString(4),rs.getInt(5),rs.getFloat(6)));
			
			answer = true;
			 str = "";
			 ps.close();
			
		}
		catch(SQLException e )
		{
			answer = false;
			e.printStackTrace();
			str = "There was an error with the server!";
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	/***
	 *  This method will get the Marketing Manager Customer Characterization Report details
	 */
	private void getCustomerReportDetails()
	{

		try{
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		 stations = new ArrayList<Station>();
		 
		 try{
			 
			 ps = con.prepareStatement("select sid,name from station_in_network ");
			 rs = ps.executeQuery();
			 
			 while(rs.next())
					 stations.add(new Station(rs.getInt(1),rs.getString(2)));
			 
			 try{
			 ps = con.prepareStatement("DROP VIEW myview");
			 ps.executeUpdate();
			 ps.close();
		 	} catch (SQLException e)
		 	{	}
		 		
			 
			 ps = con.prepareStatement("CREATE VIEW myview as (SELECT cp.uid as uid, p.sid as sid, COUNT( * ) AS c, SUM( p.bill ) as sum, SUM( p.qty ) as qty"
					+" FROM purchase p, customer_purchase cp"
					+" WHERE cp.pid = p.pid"
					+" GROUP BY cp.uid, p.sid"
					+" ORDER BY c DESC)");
			 
			 ps.executeUpdate();
			 
			 ps = con.prepareStatement(" SELECT v.uid, c.fname, c.lname, v.sid, v.c, v.sum ,v.qty"
					+" from myview v, customer c"
					+" where v.uid = c.uid"
					+ " order by v.c DESC");
			 rs = ps.executeQuery();

					
			 creport = new ArrayList<CustomerReport>();
			 while(rs.next())
				 creport.add(new CustomerReport(rs.getInt(1),rs.getString(2)+" "+rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6),rs.getFloat(7)));
				
			 
			 answer = true;
			 str = "";
			 ps.close();
		 }catch(SQLException e)
		 {		
			 	
			 	answer = false;
				e.printStackTrace();
				str = "There was an error with the server!";
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

	
	@Override
	public void update(Observable arg0, Object arg1) {
	
		if(arg1 instanceof MMRerportsRequest)
		{
			getPromotionReportDetails();
			getCustomerReportDetails();
			if(answer)
				server.setResponse(new MMReportsResponse(pr,names,stations,creport));
			else
				server.setResponse(new booleanResponse(answer,str));
		}
		
	}
	

}
