package myfuel.server;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Observable;
import java.util.Observer;
import java.sql.*;

import myfuel.client.Promotion;
import myfuel.client.PromotionTemplate;
import myfuel.request.PromotionTemplateRequest;
import myfuel.response.booleanResponse;

public class CPromotionTemplateDBHandler extends DBHandler {

	boolean answer;
	PromotionTemplateRequest request;
	PromotionTemplate promotion;
	java.sql.Time stime;
	 java.sql.Time etime ;
	private String str ;
	/***
	 *  add the handler as an observable to MyFuelServer
	 * @param server  - MyFuelServer
	 * @param con - Connection to client
	 */
	public CPromotionTemplateDBHandler(MyFuelServer server,Connection con)
	{
		super(server,con);
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
	 
	        stime = new java.sql.Time (promotion.getStartTime().getTime());
	       etime = new java.sql.Time (promotion.getEndTime().getTime());
	       
	       /*	ps=con.prepareStatement("select p.tid from prom_temp p "
	   				+ "where p.pname = ? and p.shour=? and p.fhour=? and p.discount =?"
	   				+ "and ctype=?  and fid=?");
	
	   		ps.setString(1, promotion.getName());
	   		ps.setTime(2, stime);
	   		ps.setTime(3, etime);
	   		ps.setFloat(4, promotion.getDiscount());
	   		ps.setInt(5, promotion.getTypeOfCustomer());
	   		ps.setInt(6,promotion.getTypeOfFuel());
	   		*/
	       
	       try {
	    	  
	   		ps=con.prepareStatement("select p.tid from prom_temp p "
	   				+ "where p.pname = ? ");
	
	   		ps.setString(1, promotion.getName());
	   	
	   		
	   		exist =  ps.executeQuery();
	   		
	   		
	   		if(!exist.next())
	   		{
	   			insert_Query();
	   		}
	   		else
	   		{
	   			str = "Name already exist";
	   			answer = false;
	   		}
	   		
	       } catch (Exception e) {
	   			answer = false;
	   			str = "An error with the server connection";
	   			e.printStackTrace();
	   		}  
	       
	}
	/***
	 * will insert the new promotion template to the DB with it's details
	 */
	void insert_Query()
	{
		
		PreparedStatement ps = null;
	      
		try {
			ps=con.prepareStatement("insert into prom_temp values(?,?,?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setString(2, promotion.getName());
			ps.setTime(3, stime);
			ps.setTime(4, etime);
			ps.setFloat(5, promotion.getDiscount());
			ps.setInt(6, promotion.getTypeOfCustomer());
			ps.setInt(7,promotion.getTypeOfFuel());
			ps.executeUpdate();
			answer = true;
			
			} catch (Exception e) {
				answer = false;
				str = "An error with the server connection";
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
			  this.promotion = request.getP();
			insert_promotionTemplate();
			
			server.setResponse(new booleanResponse(answer,str));
		}
		
	}

}
