package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.Entity.AnalyzeDetails;
import myfuel.Request.AnalysticRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.AnalysticResponse;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;
import myfuel.Tools.AnalysticSystem;

/***
 * This class will be used to get / set Analyze details
 * @author karmo
 *
 */
public class AnalysticDBHandler extends DBHandler{

	/***
	 * Customer Types
	 */
	private ArrayList <AnalyzeDetails> cType;
	/***
	 * Fuel Types
	 */
	private ArrayList <AnalyzeDetails> fType;
	/***
	 * Customer type per hour
	 */
	private ArrayList <AnalyzeDetails> hType ;
	/***
	 * Analyzed Dates
	 */
	private ArrayList	<Date> dates;
	/***
	 * True if query is ok false otherwise
	 */
	private boolean answer ;
	/***
	 * Description of the action status
	 */
	private String str;
	/***
	 * AnalysticDBHandler COnstructor
	 * @param server - MyFUelServer
	 * @param con - JDBC
	 */
	public AnalysticDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}
	
	/***
	 * This method will get all the neccessery data from the DB and will return it into 3 ArrayLists
	 */
	private void select_data(Date date)
	{
		cType=new ArrayList <AnalyzeDetails>();
		fType=new ArrayList <AnalyzeDetails>();
		hType = new ArrayList <AnalyzeDetails>();
		ResultSet rs = null ;
		PreparedStatement ps = null;
		try {
			
			ps = con.prepareStatement("SELECT c.smodel,s.desc, COUNT( * ) as c " +" "
										+"   FROM purchase as p, customer_purchase as cp, customer as c , sale_model as s"+" "
										+"     WHERE p.pid = cp.pid"+" "
										+"       AND cp.uid = c.uid"+" "
										+"      AND DATEDIFF( ?, p.pdate ) <=7" +" " +
										"AND DATEDIFF( ? , p.pdate ) >=0" +" "
												+ "and c.smodel = s.modelid"+" "
										
										+"            GROUP BY c.smodel"+" "
										+"             ORDER BY c DESC ");
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ps.setDate(2, new java.sql.Date(date.getTime()));
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				cType.add(new AnalyzeDetails(rs.getInt(1),AnalysticSystem.analyze(rs.getInt(3)),0,null,rs.getString(2)));
			}
			
			ps = con.prepareStatement("SELECT p.fuelid,f.fname, COUNT( * ) AS c" +" " +
										"FROM purchase AS p, fuel_price as f" +" " +
										"WHERE DATEDIFF( ?, p.pdate ) <=7" +" " +
										"AND DATEDIFF( ? , p.pdate ) >=0" +" "
										+ "and p.fuelid = f.fuelid" +" "+
										"GROUP BY p.fuelid" +" " +
										"ORDER BY c DESC ");
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ps.setDate(2, new java.sql.Date(date.getTime()));
			rs = ps.executeQuery();
			while(rs.next())
			{
				fType.add(new AnalyzeDetails(0,AnalysticSystem.analyze(rs.getInt(3)),rs.getInt(1),rs.getString(2),null));
			}
			
			ps = con.prepareStatement("SELECT c.smodel ,s.desc,(CASE " + " "+
										"WHEN (HOUR( pdate ) >= 0 and  HOUR( pdate ) < 4 )" + " "+
										"THEN  '00:00 to 04:00'" + " "+
										"WHEN (HOUR( pdate ) >= 4 and  HOUR( pdate ) < 8 )" + " "+
										"THEN  '04:00 to 08:00'" + " "+
										"WHEN (HOUR( pdate ) >= 8 and  HOUR( pdate ) < 12)" + " "+
										"THEN  '08:00 to 12:00'" + " "+
										"WHEN (HOUR( pdate ) >= 12 and  HOUR( pdate ) < 16)" + " "+
										"THEN  '12:00 to 16:00'" + " "+ 
										"WHEN (HOUR( pdate ) >= 16 and  HOUR( pdate ) < 20)" + " "+
										"THEN  '16:00 to 20:00'" + " "+
										"WHEN( HOUR( pdate ) >= 20 and  HOUR( pdate ) <= 23 )" + " "+
										"THEN  '20:00 to 00:00'" + " "+
										"END) AS hour,count(*) as ctr" + " "+ 
										"from purchase as p , customer_purchase as cp , customer as c ,sale_model as s" + " "+
										"where p.pid = cp.pid and cp.uid = c.uid and " + " "+
										"DATEDIFF(? , p.pdate) <= 7 and  DATEDIFF(? , p.pdate) >=0" + " "
										+ "and c.smodel = s.modelid"+" "+
										"group by hour , c.smodel"+" "+
										 " ORDER BY ctr desc");
			
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ps.setDate(2, new java.sql.Date(date.getTime()));
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				hType.add(new AnalyzeDetails(rs.getInt(1),rs.getString(2),rs.getString(3),AnalysticSystem.analyze(rs.getInt(4))));
			}
			
		ps.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/***
	 * This method will save the date of the analyze  to the DB
	 */
	private void insert_data()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		
		try
		{
			ps = con.prepareStatement("select * from analyzed_dates where datediff(?,date) <=7 and datediff(?,date) >=0 ");
					ps.setDate(1, new java.sql.Date(new Date().getTime()));
					ps.setDate(2, new java.sql.Date(new Date().getTime()));
			rs = ps.executeQuery();
			
			if(rs.next())
			{
	
				answer = false;
				str = "You have already sent the analyzed details for this week!";
				return;
			} 
			ps = con.prepareStatement("insert into  analyzed_dates values (?)");
			
			ps.setDate(1, new java.sql.Date(new Date().getTime()));
			
			ps.executeUpdate();
			
			answer = true;
			str = "The analyzed details has been saved successfully!";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server!";
		}
		
	}
	
	/***
	 * This method will return any dates for the analyzed details
	 */
	private void getDates()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		dates = new ArrayList<Date>();
		try{
			ps = con.prepareStatement("select date from analyzed_dates");
			rs = ps.executeQuery();
			while(rs.next())
				dates.add(rs.getDate(1));
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		
		if(arg1 instanceof AnalysticRequest)
		{
			AnalysticRequest r = (AnalysticRequest)arg1;
			
			if(r.getType() == RequestEnum.Insert)
			{
				
				insert_data();
				server.setResponse(new booleanResponse(answer,str));
			}
			
			else if(r.getType() == RequestEnum.Select)
			{
				if(r.getDate() == null)
				{
					getDates();
					server.setResponse(new AnalysticResponse(0,dates));
				}
				else 
				{
					select_data(r.getDate());
					server.setResponse(new AnalysticResponse(1,cType,fType,hType));
				}
			}
		}
		
		
	}

}
