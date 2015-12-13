package myfuel.server;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Observable;
import java.util.Observer;
import java.sql.*;

import myfuel.client.Promotion;
import myfuel.request.PromotionTemplateRequest;
import myfuel.response.booleanResponse;

public class CPromotionTemplateDBHandler implements Observer {

	Connection con;
	MyFuelServer server;
	boolean answer;
	PromotionTemplateRequest request;
	/***
	 *  add the handler as an observable to MyFuelServer
	 * @param server  - MyFuelServer
	 * @param con - Connection to client
	 */
	public CPromotionTemplateDBHandler(MyFuelServer server,Connection con)
	{
		this.server = server;
		this.con = con;
		server.addObserver(this);
	}
	/***
	 * First this method will check if there is an existing promotion template with 
	 * the same details as the new one
	 * if there is the new one won't enter to the DB
	 * else it will insert it to the DB
	 */
	void insert_promotionTemplate()
	{
		ResultSet exist ;
		PreparedStatement ps = null;
		Promotion promotion = request.p;
		
	       
	       java.sql.Time stime = new java.sql.Time (request.p.startTime.getTime());
	       java.sql.Time etime = new java.sql.Time (request.p.endTime.getTime());
	       try {
	   		ps=con.prepareStatement("select p.tid from prom_temp p "
	   				+ "where p.pname = ? and p.shour=? and p.fhour=? and p.discount =?"
	   				+ "and ctype=? ");
	   		
	   		ps.setString(1, promotion.name);
	   		ps.setTime(2, stime);
	   		ps.setTime(3, etime);
	   		ps.setFloat(4, promotion.discount);
	   		ps.setInt(5, promotion.typeOfCustomer);
	   		
	   		exist =  ps.executeQuery();
	   		
	   		
	   		if(!exist.next())
	   		{
	   			insert_Query();
	   		}
	   		else
	   		{
	   			answer = false;
	   		}
	   		
	       } catch (SQLException e) {
	   			answer = false;
	   			e.printStackTrace();
	   		}  
	       
	}
	/***
	 * will insert the new promotion template to the DB with it's details
	 */
	void insert_Query()
	{
		
		PreparedStatement ps = null;
		Promotion promotion = request.p;
		java.sql.Time stime = new java.sql.Time (request.p.startTime.getTime());
	    java.sql.Time etime = new java.sql.Time (request.p.endTime.getTime());
		try {
			ps=con.prepareStatement("insert into prom_temp values(?,?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setString(2, promotion.name);
			ps.setTime(3, stime);
			ps.setTime(4, etime);
			ps.setFloat(5, promotion.discount);
			ps.setInt(6, promotion.typeOfCustomer);
			
			ps.executeUpdate();
			answer = true;
			
			} catch (SQLException e) {
				answer = false;
				e.printStackTrace();
			}
	}
/***
 *  will get a request from the server.
 *  if the request is instance of 	PromotionTemplateRequest than the request will be 
 *  handled.
 *  after the request is handled a response will be sent back to the client
 */
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if( arg1 instanceof  PromotionTemplateRequest)
		{
			 request = (PromotionTemplateRequest)arg1;
			insert_promotionTemplate();
			server.setResponse(new booleanResponse(answer));
		}
		
	}

}
