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

import myfuel.client.FuelQty;
import myfuel.client.Purchase;
import myfuel.client.ReportEnum;
import myfuel.client.Station;
import myfuel.client.StationInventory;
import myfuel.request.SReportRequest;
import myfuel.response.SReportResponse;
import myfuel.response.booleanResponse;

/***
 * Station Reports DBHandler - will get the specific report details from the DB
 *
 */
public class SReportsDBHandler extends DBHandler{

	private  ArrayList<FuelQty> inventory;
	private ArrayList<Purchase> p ;
	private ArrayList<Purchase> incomes;
	private boolean answer ;
	private  String str = "";
	private java.sql.Timestamp sdate = null;
	private java.sql.Timestamp fdate = null;
	private  Calendar cal;
	
	public SReportsDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}
	
	/***
	 * This method will get the details of the Inventory Report for the station for the specific station
	 * @param sid - Station ID
	 * @param q  - Quarter
	 */
	private void getInventoryReport(int q, int sid)
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
	
		 inventory = new  ArrayList<FuelQty>();
		 try{
			 
			// check if there is a report for this quarter
			 ps = con.prepareStatement("select * from inventory_report where sid = ? and qid = ?");
			 ps.setInt(1, sid);
			 ps.setInt(2, q);
			 
			 rs = ps.executeQuery();
			 
			 if(rs.next())
			 {
				 answer = false;
				 str = "You have already filed a report for this quarter!";
				 ps.close();
				 return;
			 }
			 
			 ps = con.prepareStatement("select fuelid,fqty,mqty from station_inventory where sid = ?");
			 ps.setInt(1,sid);
			 
			 
			rs = ps.executeQuery();
			
			 while(rs.next())
			 {
				inventory.add(new FuelQty(rs.getInt(1),rs.getFloat(2),rs.getFloat(3)));
			 }
			
				 for(FuelQty f : inventory)
				 {
					 ps = con.prepareStatement("insert into inventory_report values(?,?,?,?)");
					 ps.setInt(1, sid);
					 ps.setInt(2, q); // Quarter
					 ps.setInt(3, f.getFid());
					 ps.setFloat(4,f.getQty());
					 
					 ps.executeUpdate();
				 }
			 
			 
			answer = true;
			ps.close();
		 }catch(Exception e)
		 {
			 e.printStackTrace();		 
			 }
	}
	
	/***
	 * This method will check in the DB if there is a registry of this station for
	 *  specific type of report for specific quarter
	 * @param rid  - report id
	 * @param sid  - Station ID
	 * @param q   - Quarter
	 */
	private void check_exist(int rid,int sid, int q)
	{
		ResultSet rs = null ;
		 PreparedStatement ps = null;
		 try{
			 
		 
		// check if there is a report for this quarter
		 ps = con.prepareStatement("select * from company_report where sid = ? and qid = ? and rid = ?");
		 ps.setInt(1, sid);
		 ps.setInt(2, q);
		 ps.setInt(3, rid);
		 rs = ps.executeQuery();
		 
		 if(rs.next())
		 {
			 str = "You have already filed a report for this quarter!";
			 ps.close();
			 answer = false;
		 }
		 else 
			 answer = true;
		 
		
		 }
		 catch(SQLException e)
		 {
			 e.printStackTrace();
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
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/***
	 * This method will get the details of the Purchase Report for the specific station
	 * @param rid - Report ID
	 * @param sid - Station ID
	 * @param q  - Quarter
	 */
	private void getPurchaseReport(int rid,int sid, int q)
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
		 try{
			 
			 
			 check_dates(q);
			ps = con.prepareStatement("insert into company_report values (?,?,?) ");
			ps.setInt(1,rid);
			ps.setInt(2,sid);
			ps.setInt(3, q);
			
			ps.executeUpdate();
			
		 ps = con.prepareStatement("select c.uid,p.fuelid,p.bill,p.qty from customer_purchase as c, purchase as p  "
		 		+ "					where sid = ? and datediff(pdate,?) >=0 and datediff(pdate,?) <= 0 "
		 		+ "							and p.pid = c.pid");
		 ps.setInt(1, sid);
		 ps.setTimestamp(2, sdate);
		 ps.setTimestamp(3, fdate);
		 
		 rs = ps.executeQuery();
		  p = new  ArrayList<Purchase>();
		  
		 while(rs.next())
		 {
			 p.add(new Purchase(rs.getInt(1),rs.getInt(2),rs.getFloat(3),rs.getFloat(4))); // uid , fuelid , bill , quantity
		 }
		 
		
		 
		 answer = true;
		 
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	/***
	 * This method will get the details of the Incomes Report for the specific station
	 * @param rid - Report ID
	 * @param sid - Station ID
	 * @param q  - Quarter
	 */
	private void getIncomesReport(int rid,int sid, int q)
	{
		ResultSet rs = null ;
		 PreparedStatement ps = null;
	 try{ 
		 
			ps = con.prepareStatement("insert into company_report values (?,?,?) ");
			ps.setInt(1,rid);
			ps.setInt(2,sid);
			ps.setInt(3, q);
			
			ps.executeUpdate();
			
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
		 }
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if(arg1 instanceof SReportRequest)
		{
			SReportRequest request = (SReportRequest)arg1;
			
			if(request.getReport_type() == ReportEnum.InventoryReport)
			{
				getInventoryReport(request.getQ(),request.getSid());
				
				if(answer)
					server.setResponse(new SReportResponse(request.getReport_type(),inventory,null,null));
				else
					server.setResponse(new booleanResponse(answer,str));
			}
			
			else if(request.getReport_type() == ReportEnum.PurchaseReport) // rid = 1
			{
				check_exist(1,request.getSid(),request.getQ());
		
				if(answer)
				{
					getPurchaseReport(1,request.getSid(),request.getQ());
					server.setResponse(new SReportResponse(request.getReport_type(),null,p,null));
				}
				else
					server.setResponse(new booleanResponse(answer,str));
				
			}
			
			else if(request.getReport_type() == ReportEnum.IncomesReport) // rid = 2
			{
				
				check_exist(2,request.getSid(),request.getQ());
				if(answer)
				{
					getIncomesReport(2,request.getSid(),request.getQ());
					server.setResponse(new SReportResponse(request.getReport_type(),null,null,incomes));
				}
				else
					server.setResponse(new booleanResponse(answer,str));
			}
			
		}
		
	}
	

}
