package myfuel.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

import javax.xml.crypto.dsig.keyinfo.PGPData;

import myfuel.Tools.TimeIgnoringComparator;
import myfuel.client.FuelQty;
import myfuel.client.Purchase;
import myfuel.client.ReportEnum;
import myfuel.client.Station;
import myfuel.client.StationInventory;
import myfuel.request.RequestEnum;
import myfuel.request.SReportRequest;
import myfuel.response.SReportResponse;
import myfuel.response.booleanResponse;

/***
 * Station Reports DBHandler - will get the specific report details from the DB and if possible will save them in the DB 
 *
 */
public class SReportsDBHandler extends DBHandler{

	/***
	 * Inventory Report Details
	 */
	private  ArrayList<FuelQty> inventory;
	/***
	 * Purchases Report Details
	 */
	private ArrayList<Purchase> p ;
	/***
	 * Incomes Report Details
	 */
	private ArrayList<Purchase> incomes;
	/***
	 * Status of the query. True if all good false otherwise
	 */
	private boolean answer ;
	/***
	 * Description for the action
	 */
	private  String str = "";
	/***
	 * Start Date of the quarter
	 */
	private java.sql.Timestamp sdate = null;
	/***
	 * End Date of the quarter
	 */
	private java.sql.Timestamp fdate = null;
	/***
	 * Details about date
	 */
	private  Calendar cal;
	
	/***
	 * SReportsDBHandler COnstructor
	 * @param server  - MyFUelServer
 	 * @param con - JDBC
	 */
	public SReportsDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		cal = Calendar.getInstance();
	}
	
	/***
	 * This method will get the details of the Inventory Report for the station for the specific station
	 * @param sid - Station ID
	 * @param q  - Quarter
	 */
	@SuppressWarnings("deprecation")
	private void getInventoryReport(int q, int sid,int nid)
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
	
		 inventory = new  ArrayList<FuelQty>();
		 
		 try{
			 
		
		if(q == this.getQuarter())
		{
			 
			 ps = con.prepareStatement("select fuelid,fqty,mqty from station_inventory where sid = ? ");
			 ps.setInt(1,sid);
			
			 
			rs = ps.executeQuery();
			
			 while(rs.next())
			 {
				inventory.add(new FuelQty(rs.getInt(1),rs.getFloat(2),rs.getFloat(3)));
			 }
		}
		
		else
		{
			 ps = con.prepareStatement("select fuelid,qty from inventory_report where sid = ? and qid = ? and year = ?");
			 ps.setInt(1, sid);
			 ps.setInt(2, q);
			 ps.setInt(3,cal.getTime().getYear() + 1900 );
			 rs = ps.executeQuery();
			 
			 while(rs.next())
			 {
				inventory.add(new FuelQty(rs.getInt(1),rs.getFloat(2),-1));
			 }
		}
 
			answer = true;
			ps.close();
		 }catch(Exception e)
		 {
			 e.printStackTrace();	
			 answer = false;
			 str = "There was an error with the server";
		 }
	}
	
	/***
	 * Inserts the details for the inventory report to the DB
	 * @param sid - Station ID
	 * @param nid - Network ID
	 * @param q - Quarter number
	 * @param inventory - Inventory Report Details
	 */
	@SuppressWarnings("deprecation")
	void insertInventoryReport(int sid , int nid, int q)
	{	
		this.getInventoryReport(q, sid, nid);
		
		PreparedStatement ps = null;
		try{
		 for(FuelQty f : inventory)
		 {
			 ps = con.prepareStatement("insert into inventory_report values(?,?,?,?,?,?)");
			 ps.setInt(1, sid);
			 ps.setInt(2, nid);
			 ps.setInt(3, q); // Quarter
			 ps.setInt(4, f.getFid());
			 ps.setFloat(5,f.getQty());
			 ps.setInt(6,cal.getTime().getYear() + 1900 );
			 
			 ps.executeUpdate();
		 }
		 
		 answer = true;
		ps.close();
		}catch(SQLException e)
		{
			e.printStackTrace();	
			 answer = false;
			 str = "There was an error with the server";
		}
	}
	
	/***
	 * This method will check in the DB if there is a registry of this station for
	 *  specific type of report for specific quarter
	 * @param rid  - report id
	 * @param sid  - Station ID
	 * @param q   - Quarter
	 */
	@SuppressWarnings("deprecation")
	private void check_exist(int rid,int sid, int q)
	{
		ResultSet rs = null ;
		 PreparedStatement ps = null;
		 try{
			 
		 if(rid == 0 )
		 {
			 ps = con.prepareStatement("select * from inventory_report where sid = ? and qid = ? and year = ?");
			 ps.setInt(1, sid);
			 ps.setInt(2, q);
			 ps.setInt(3,cal.getTime().getYear() + 1900 );
			 rs = ps.executeQuery();

			 if(rs.next())
			 {
				 answer = false;
				 str = "You have already filed a report for this quarter!";
				 ps.close();
				 return;
			 }
		 }
		 
		 else
		 {
			
				// check if there is a report for this quarter
				 ps = con.prepareStatement("select * from network_report where sid = ? and qid = ? and rid = ? and year = ?");
				 ps.setInt(1, sid);
				 ps.setInt(2, q);
				 ps.setInt(3, rid);
				 ps.setInt(4,cal.getTime().getYear() + 1900 );
				 rs = ps.executeQuery();
				 
				 if(rs.next())
				 {
					 str = "You have already filed a report for this quarter!";
					 ps.close();
					 answer = false;
					 return;
				 }
				 
		 }
		 
		 	answer = true;
		 	
		 }
		 catch(SQLException e)
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
			 		cal.set(cal.get(Calendar.YEAR),Calendar.MARCH,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 2:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.APRIL,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JUNE,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 3:
			 		cal.set(cal.get(Calendar.YEAR),Calendar.JULY,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.SEPTEMBER,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 	
			 	case 4: 
			 		cal.set(cal.get(Calendar.YEAR),Calendar.OCTOBER,1);
			 		sdate = new java.sql.Timestamp( cal.getTime().getTime());
			 		cal.set(cal.get(Calendar.YEAR),Calendar.DECEMBER,31);
			 		fdate =  new java.sql.Timestamp( cal.getTime().getTime());
			 		break;
			 			
			 }
			 answer = true;
		}catch(Exception e)
		{
			e.printStackTrace();
			answer = false;
			 str = "There was an error with the server";
		}
	}
	/***
	 * This method will get the details of the Purchase Report for the specific station
	 * @param rid - Report ID
	 * @param sid - Station ID
	 * @param q  - Quarter
	 */
	private void getPurchaseReport(int rid,int sid, int q , int nid)
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
		 p = new  ArrayList<Purchase>();
		 this.check_dates(q);
		 try{
			 
			 
		
			 
		 ps = con.prepareStatement("select c.uid,p.fuelid,p.bill,p.qty from customer_purchase as c, purchase as p  "
		 		+ "					where sid = ? and datediff(pdate,?) >=0 and datediff(pdate,?) <= 0 "
		 		+ "							and p.pid = c.pid");
		 ps.setInt(1, sid);
		 ps.setTimestamp(2, sdate);
		 ps.setTimestamp(3, fdate);
		 
		 rs = ps.executeQuery();
		 
		  
		 while(rs.next())
		 {
			 p.add(new Purchase(rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getFloat(4))); // uid , fuelid , bill , quantity
		 }
		 
		
		 
		 answer = true;
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 answer = false;
			 str = "There was an error with the server";
		 }
	}
	/***
	 * This method will get the details of the Incomes Report for the specific station
	 * @param rid - Report ID
	 * @param sid - Station ID
	 * @param q  - Quarter
	 */
	private void getIncomesReport(int rid,int sid, int q , int nid)
	{
		ResultSet rs = null ;
		 PreparedStatement ps = null;
	 try{ 
		 
	
			
		 check_dates(q);
		 ps = con.prepareStatement("select c.uid,p.fuelid,p.bill,p.qty from customer_purchase as c, purchase as p  "
		 		+ "					where sid = ? and datediff(pdate,?) >=0 and datediff(pdate,?) <= 0 "
		 		+ "							and p.pid = c.pid");
		 ps.setInt(1, sid);
		 ps.setTimestamp(2, sdate);
		 ps.setTimestamp(3, fdate);
		 
		 rs = ps.executeQuery();
		  incomes = new  ArrayList<Purchase>();
		  
		 while(rs.next())
		 {
			 incomes.add(new Purchase(rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getFloat(4))); // uid , fuelid , bill , quantity
		 }
		 
		 answer = true;
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 answer = false;
			 str = "There was an error with the server";
		 }
	}
	
	
	/***
	 * This method saves the details for the purchases report and incomes report in the DB
	 */
	private void insert_reports(int rid,int sid, int q , int nid)
	{
		PreparedStatement ps = null;
		if(rid == 0 )
		{
			
			this.insertInventoryReport(sid, nid, q);
		}
		
		else{
			 check_dates(q);
			 try{
				ps = con.prepareStatement("insert into network_report values (?,?,?,?,?) ");
				ps.setInt(1,rid);
				ps.setInt(2,sid);
				ps.setInt(3, nid);
				ps.setInt(4, q);
				ps.setInt(5,cal.getTime().getYear() + 1900 );
				ps.executeUpdate();
				 answer = true;
			 }catch(SQLException e )
			 {
				 e.printStackTrace();
				 answer = false;
				 str = "There was an error with the server";
			 }
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof SReportRequest)
		{
			SReportRequest request = (SReportRequest)arg1;
			
			if(request.getReport_type() == RequestEnum.Select)
			{
				getInventoryReport(request.getQ(),request.getSid(),request.getNid());
				getPurchaseReport(1,request.getSid(),request.getQ(),request.getNid());
				getIncomesReport(2,request.getSid(),request.getQ(),request.getNid());
				
				server.setResponse(new SReportResponse(inventory,p,incomes));
			}
			
			else if(request.getReport_type() == RequestEnum.Insert)
			{
				
				
				for(int i = 0 ; i < 3 ; i++)
				{
					this.check_exist(i, request.getSid(), request.getQ());
					
					if(!answer)
						break;
				}
				
				if(answer)
				{
						
					for(int i = 0 ; i < 3 ; i ++ )
					{
							this.insert_reports(i, request.getSid(), request.getQ(), request.getNid());
					}
				}
				
				server.setResponse(new booleanResponse(answer,str));
			}
				
			
			
		
			
		}
		
	}
	
	
	/***
	 * This method calculate the current Quarter
	 * @return the current quarter
	 */
	private int getQuarter()
	{
		Date s = null;
		Date f = null;
		Calendar c = Calendar.getInstance();
		
		c.set(c.get(Calendar.YEAR),Calendar.JANUARY,1);
 		s = new Date( c.getTime().getTime());
 		c.set(c.get(Calendar.YEAR),Calendar.MARCH,31);
 		f =  new Date( c.getTime().getTime());
		if(new TimeIgnoringComparator().compare(new java.util.Date(), s) >= 0 && new TimeIgnoringComparator().compare(new java.util.Date(), f) <=0 )
			return 1 ;
		
		c.set(c.get(Calendar.YEAR),Calendar.APRIL,1);
 		s = new Date( c.getTime().getTime());
 		c.set(c.get(Calendar.YEAR),Calendar.JUNE,31);
 		f =  new Date( c.getTime().getTime());
 		
		if(new TimeIgnoringComparator().compare(new java.util.Date(), s) >= 0 && new TimeIgnoringComparator().compare(new java.util.Date(), f) <=0)
		return 2;
		
		c.set(c.get(Calendar.YEAR),Calendar.JULY,1);
 		s = new Date( c.getTime().getTime());
 		c.set(c.get(Calendar.YEAR),Calendar.SEPTEMBER,31);
 		f =  new Date( c.getTime().getTime());
 		
		if(new TimeIgnoringComparator().compare(new java.util.Date(), s) >= 0 && new TimeIgnoringComparator().compare(new java.util.Date(), f) <=0)
			return 3;
		
		else
			return 4;
	
	
	}
	

}
