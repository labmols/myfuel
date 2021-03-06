package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

import myfuel.Entity.FuelQty;
import myfuel.Entity.Purchase;
import myfuel.Entity.QuarterStationIncome;
import myfuel.Entity.QuarterStationInventory;
import myfuel.Entity.QuarterStationPurchase;
import myfuel.Entity.Station;
import myfuel.Request.NetworkReportRequest;
import myfuel.Response.ComapnyReportsResponse;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;


/***
 * Company Reports DBHAndler  - will get the Stations reports from the DB
 *
 */
public class NetworkReportsDBHandler extends DBHandler {
	/***
	 * Will be true if there was no exception  and false otherwise
	 */
	private boolean answer ;
	/***
	 * Exception Message
	 */
	private String str= "";
	/***
	 * Start Date of the quarter
	 */
	private java.sql.Timestamp sdate = null;
	/***
	 * Final Date of the quarter
	 */
	private java.sql.Timestamp fdate = null;
	/***
	 * Variable for getting the date 
	 */
	private  Calendar cal;
	/***
	 * Contains the Station Inventory per quarter
	 */
	private ArrayList<QuarterStationInventory> qStationInventory = null;
	/***
	 * Contains the  Incomes per quarter
	 */
	private ArrayList<QuarterStationIncome> qStationIncome = null;
	/***
	 * Contains the  Purchases per quarter
	 */
	private ArrayList<QuarterStationPurchase> qStationPurchase = null;
	/***
	 * Contains the Years that documented at the DB
	 */
	private ArrayList<Integer> years = null;
	/***
	 *  NetworkReportsDBHandler constructor
	 * @param server - MyFuelServer
	 * @param con - JDBC
	 */
	public NetworkReportsDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}


	/***
	 * This method will get all the inventory reports for a specific year
	 * @param year - specific year for the report
	 */
	private void getCompanyInventoryReport(int year , int nid)
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
		 
		 try{
			// quarter , station id , station name , fuel name,fuel id , fuel quantity
			 ps = con.prepareStatement("select r.qid ,s.sid,s.name,f.fname,r.fuelid,r.qty "
			 		+ "					 from inventory_report as r , station_in_network as s , fuel_price as f"
			 		+ "						where s.sid = r.sid and f.fuelid = r.fuelid and r.year = ?"
			 		+ "								and r.nid = ?");
			 
			 ps.setInt(1, year);
			 ps.setInt(2, nid);
			 rs = ps.executeQuery();
			 
			 qStationInventory = new ArrayList<QuarterStationInventory>();
			 
			 while(rs.next())
			 {
				 qStationInventory.add(new QuarterStationInventory(rs.getInt(1),  // quarter id 
						 														new Station(rs.getInt(2),rs.getString(3)), // station 
						 														new FuelQty(rs.getString(4),rs.getInt(5),rs.getFloat(6))));// fuel 
						 													
			 }
			 answer = true;
		 }catch(Exception e )
		 {
			 answer = false;
			 str = "There was an error with the server!";
			 e.printStackTrace();
		 }
		
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof NetworkReportRequest)
		{
			NetworkReportRequest r = (NetworkReportRequest)arg1;
			if(r.getYear() == -1) // if there is no requested year for the reports
				getYearsOfReports(r.getNid());
			
			else  // if there is  a year for the reports
			{
				getCompanyInventoryReport(r.getYear(),r.getNid());
				getCompanyIncomesReport(r.getYear(),r.getNid());
				getCompanyPurchaseReport(r.getYear(),r.getNid()); 
			}
			
			
			if(answer)  // if all good
			{
				if(r.getYear() == -1)
					server.setResponse(new ComapnyReportsResponse(years,0));
					
				else 
					server.setResponse(new ComapnyReportsResponse(qStationInventory,qStationIncome,qStationPurchase,1));
			}
			
			else
				server.setResponse(new booleanResponse(answer,str));
		}
		
	}


	/***
	 * This Method will return all that years that documented in the DB
	 */
	private void getYearsOfReports(int nid)
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		years = new ArrayList<Integer>();
		
		try{
			
			ps = con.prepareStatement("select c.year from inventory_report as i , network_report as c where i.year = c.year and i.nid = c.nid");
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(!years.contains(rs.getInt(1)))
					years.add(rs.getInt(1));
			}
			
			answer = true;
			ps.close();
		}catch(SQLException e )
		{
			 e.printStackTrace();
			 answer = false;
			 str = "There was an error with the server";
		}
	}

/***
 * Thie method will return all the details for the Purchase report of MyFuel
 * @param Year - requested year 
 */
	private void getCompanyPurchaseReport(int Year , int nid) 
	{
		ResultSet rs = null ;
		 PreparedStatement ps = null;
		 
		 qStationPurchase = new  ArrayList<QuarterStationPurchase>();
		 ArrayList<Integer> quarters = new ArrayList<Integer> ();
		 try{
			 int qid;
			 ps = con.prepareStatement("select qid from network_report where rid = 1 and year = ? and nid = ?");   // checking what quarters exist for this report
			 ps.setInt(1, Year);
			 ps.setInt(2, nid);
			 rs=ps.executeQuery();
			 while(rs.next())
			 { 
				 qid = rs.getInt(1);
				if( !quarters.contains(qid))
					quarters.add(qid);
			 }
			
			 for(Integer q : quarters)  // will get the data for each quarter
			 {
				
				 		check_dates(q,Year); // will set the beginning and ending of each quarters into class attributes
				 		
					 ps = con.prepareStatement("select r.qid,s.sid,s.name,c.uid,p.fuelid,p.bill,p.qty,f.fname"
					 		+ "					 from customer_purchase as c, purchase as p ,station_in_network as s,  network_report as r , fuel_price as f"
						 		+ "					where p.sid = r.sid  and datediff(p.pdate,?) >=0 and datediff(p.pdate,?) <= 0 "
						 		+ "							and p.pid = c.pid and r.rid = 1"
						 		+ "							and s.sid = r.sid"
						 		+ "							and f.fuelid = p.fuelid"
						 		+ "							and r.qid = ?"
						 		+ "							and r.Year = ?"
						 		+ "							and r.nid = ?");
					 
						 
						 ps.setTimestamp(1, sdate);
						 ps.setTimestamp(2, fdate);
						 ps.setInt(3, q);
						 ps.setInt(4,Year);
						 ps.setInt(5, nid);
						 rs = ps.executeQuery();
						
						 while(rs.next())
						 {
							 qStationPurchase.add(new QuarterStationPurchase(  new QuarterStationIncome( rs.getInt(1),  // quarter
									 				new Station(rs.getInt(2),rs.getString(3)), // station id ,station name
									 				new Purchase(rs.getInt(4),rs.getInt(5),rs.getFloat(6),rs.getFloat(7))) // uid , fuelid , bill , qty
							 									,rs.getString(8))); // fuel name
							
						 } 
						 
					answer = true;
						 
			 }
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 answer = false;
			 str = "There was an error with the server";
		 }
		
	}

/***
 * Get all the Details for the Incomes Report from the DB
 * @param year - requested year
 */
	private void getCompanyIncomesReport(int year , int nid) 
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
		 
		 qStationIncome = new  ArrayList<QuarterStationIncome>();
		 ArrayList<Integer> quarters = new ArrayList<Integer> ();
		 try{
			 int qid;
			 ps = con.prepareStatement("select qid from network_report where rid = 2 and year = ? and nid = ?");   // checking what quarters exist for this report
			 ps.setInt(1, year);
			 ps.setInt(2,nid);
			 rs=ps.executeQuery();
			 while(rs.next())
			 { 
				 qid = rs.getInt(1);
				if( !quarters.contains(qid))
					quarters.add(qid);
			 }
			
			 for(Integer q : quarters)  // will get the data for each quarter
			 {
				 		check_dates(q,year); // will set the beginning and ending of each quarters into class attributes
				 		
					 ps = con.prepareStatement("select r.qid,s.sid,s.name,c.uid,p.fuelid,p.bill,p.qty"
					 		+ "					 from customer_purchase as c, purchase as p ,station_in_network as s,  network_report as r "
						 		+ "					where p.sid = r.sid  and datediff(p.pdate,?) >=0 and datediff(p.pdate,?) <= 0 "
						 		+ "							and p.pid = c.pid and r.rid = 2"
						 		+ "							and s.sid = r.sid and r.qid = ?"
						 		+ "							and r.year = ?"
						 		+ "							and r.nid = ?");
					 
						 
						 ps.setTimestamp(1, sdate);
						 ps.setTimestamp(2, fdate);
						 ps.setInt(3, q);
						 ps.setInt(4, year);
						 ps.setInt(5, nid);
						 rs = ps.executeQuery();
						 
						 while(rs.next())
						 {
							 qStationIncome.add(new QuarterStationIncome( rs.getInt(1),  // quarter
									 				new Station(rs.getInt(2),rs.getString(3)), // station id ,station name
									 				new Purchase(rs.getInt(4),rs.getInt(5),rs.getFloat(6),rs.getFloat(7))));// uid , fuelid , bill , qty
							 
							
						 } 
						 
					answer = true;
						 
			 }
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 answer = false;
			 str = "There was an error with the server";
		 }
	}



	
	
	
	
	/***
	 * This method will get a Quarter and will set the start date and end date of this quarter into the attributes sdate & fdate
	 * @param q  - Quarter NO.
	 * @param year - requested Year 
	 */
	private void check_dates(int q,int year)
	{
		try{
	 		 
			
			
			  cal = Calendar.getInstance();
			  
			 switch(q)
			 {
			 	case 1:
			 		cal.set(year,Calendar.JANUARY,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(year ,Calendar.MARCH,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 2:
			 		cal.set(year,Calendar.APRIL,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(year,Calendar.JUNE,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 3:
			 		
			 		cal.set(year,Calendar.JULY,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(year,Calendar.SEPTEMBER,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 4: 
			 		cal.set(year,Calendar.OCTOBER,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(year,Calendar.DECEMBER,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 			
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	
	

}
