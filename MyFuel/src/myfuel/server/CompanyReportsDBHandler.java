package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

import myfuel.client.FuelQty;
import myfuel.client.Purchase;
import myfuel.client.QuarterStationPurchase;
import myfuel.client.Station;
import myfuel.client.quarterStationIncome;
import myfuel.client.quarterStationInventory;
import myfuel.request.CompanyReportRequest;
import myfuel.request.CompanyReportRequest;
import myfuel.response.ComapnyReportsResponse;
import myfuel.response.booleanResponse;


/***
 * Company Reports DBHAndler  - will get the Stations reports from the DB
 *
 */
public class CompanyReportsDBHandler extends DBHandler {
	private boolean answer ;
	private String str= "";
	private java.sql.Timestamp sdate = null;
	private java.sql.Timestamp fdate = null;
	private  Calendar cal;
	private ArrayList<quarterStationInventory> qStationInventory = null;
	private ArrayList<quarterStationIncome> qStationIncome = null;
	private ArrayList<QuarterStationPurchase> qStationPurchase = null;
	/***
	 *  CompanyReportsDBHandler constructor
	 * @param server - MyFuelServer
	 * @param con - JDBC
	 */
	public CompanyReportsDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}


	private void getCompanyInventoryReport()
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
		 
		 try{
			// quarter , station id , station name , fuel name,fuel id , fuel quantity
			 ps = con.prepareStatement("select r.qid ,s.sid,s.sname,f.fname,r.fuelid,r.qty "
			 		+ "					 from inventory_report as r , station as s , fuel_price as f"
			 		+ "						where s.sid = r.sid and f.fuelid = r.fuelid");
			 
			 rs = ps.executeQuery();
			 
			 qStationInventory = new ArrayList<quarterStationInventory>();
			 
			 while(rs.next())
			 {
				 qStationInventory.add(new quarterStationInventory(rs.getInt(1),  // quarter id 
						 														new Station(rs.getInt(2),rs.getString(3)), // station 
						 														new FuelQty(rs.getString(4),rs.getInt(5),rs.getFloat(6)))); // fuel 
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
		if(arg1 instanceof CompanyReportRequest)
		{
			
			getCompanyInventoryReport();
			getCompanyIncomesReport();
			getCompanyPurchaseReport();
			if(answer)
				server.setResponse(new ComapnyReportsResponse(qStationInventory,qStationIncome,qStationPurchase));
			else
				server.setResponse(new booleanResponse(answer,str));
		}
		
	}


	
	private void getCompanyPurchaseReport() 
	{
		ResultSet rs = null ;
		 PreparedStatement ps = null;
		 
		 qStationPurchase = new  ArrayList<QuarterStationPurchase>();
		 ArrayList<Integer> quarters = new ArrayList<Integer> ();
		 try{
			 
			 ps = con.prepareStatement("select qid from company_report where rid = 1");   // checking what quarters exist for this report
			 rs=ps.executeQuery();
			 while(rs.next())
				 quarters.add(rs.getInt(1));
			
			 for(Integer q : quarters)  // will get the data for each quarter
			 {
				
				 		check_dates(q); // will set the beginning and ending of each quarters into class attributes
				 		
					 ps = con.prepareStatement("select r.qid,s.sid,s.sname,c.uid,p.fuelid,p.bill,p.qty,f.fname"
					 		+ "					 from customer_purchase as c, purchase as p ,station as s,  company_report as r , fuel_price as f"
						 		+ "					where p.sid = r.sid  and datediff(p.pdate,?) >=0 and datediff(p.pdate,?) <= 0 "
						 		+ "							and p.pid = c.pid and r.rid = 1"
						 		+ "							and s.sid = r.sid"
						 		+ "							and f.fuelid = p.fuelid");
					 
						 
						 ps.setTimestamp(1, sdate);
						 ps.setTimestamp(2, fdate);
						 
						 rs = ps.executeQuery();
						
						 while(rs.next())
						 {
							 qStationPurchase.add(new QuarterStationPurchase(  new quarterStationIncome( rs.getInt(1),  // quarter
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


	private void getCompanyIncomesReport() 
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
		 
		 qStationIncome = new  ArrayList<quarterStationIncome>();
		 ArrayList<Integer> quarters = new ArrayList<Integer> ();
		 try{
			 
			 ps = con.prepareStatement("select qid from company_report where rid = 2");   // checking what quarters exist for this report
			 rs=ps.executeQuery();
			 while(rs.next())
				 quarters.add(rs.getInt(1));
			
			 for(Integer q : quarters)  // will get the data for each quarter
			 {
				 		check_dates(q); // will set the beginning and ending of each quarters into class attributes
				 		
					 ps = con.prepareStatement("select r.qid,s.sid,s.sname,c.uid,p.fuelid,p.bill,p.qty"
					 		+ "					 from customer_purchase as c, purchase as p ,station as s,  company_report as r "
						 		+ "					where p.sid = r.sid  and datediff(p.pdate,?) >=0 and datediff(p.pdate,?) <= 0 "
						 		+ "							and p.pid = c.pid and r.rid = 1"
						 		+ "							and s.sid = r.sid");
					 
						 
						 ps.setTimestamp(1, sdate);
						 ps.setTimestamp(2, fdate);
						 
						 rs = ps.executeQuery();
						 
						 while(rs.next())
						 {
							 qStationIncome.add(new quarterStationIncome( rs.getInt(1),  // quarter
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
	 */
	private void check_dates(int q)
	{
		try{
	 		 
			  cal = Calendar.getInstance();
			 switch(q)
			 {
			 	case 1:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JANUARY,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.APRIL,1);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 2:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.APRIL,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JULY,1);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 3:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JULY,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.OCTOBER,1);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 4: 
			 		cal.set(cal.get(Calendar.YEAR),Calendar.OCTOBER,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JANUARY,1);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 			
			 }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}



	
	

}
