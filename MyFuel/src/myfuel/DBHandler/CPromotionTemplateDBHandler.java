package myfuel.DBHandler;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Observable;
import java.util.Observer;
import java.sql.*;

import myfuel.Entity.Promotion;
import myfuel.Entity.PromotionTemplate;
import myfuel.request.PromotionTemplateRequest;
import myfuel.response.booleanResponse;
import myfuel.server.MyFuelServer;

/***
 * This class is responsible for updating the DB with the created Promotion Template
 * @author karmo
 *
 */
public class CPromotionTemplateDBHandler extends DBHandler {

	/***
	 * WIll indicate the status of the operations (true or false)
	 */
	boolean answer;
	/***
	 * The request as received from the client
	 */
	private PromotionTemplateRequest request;
	/***
	 * The promotion Template details
	 */
	private PromotionTemplate promotion;
	/***
	 * Start Time of the promotion
	 */
	private java.sql.Time stime;
	/***
	 * End Time of the promotion
	 */
	private  java.sql.Time etime ;
	/***
	 * Description of the operation
	 */
	private String str ;
	
	/***
	 *  Create Promotion Template DB Handler
	 * @param server  - MyFuelServer
	 * @param con - JDBC driver Connection
	 */
	public CPromotionTemplateDBHandler(MyFuelServer server,Connection con)
	{
		super(server,con);
	}
	
	
	/***
	 * First this method will check if there is an existing promotion template with 
	 * the same details as the new one
	 * if there is the new one won't enter to the DB
	 * else it will be insert to the DB
	 */
	void insert_promotionTemplate()
	{
		ResultSet exist ;
		PreparedStatement ps = null;
	 
	        stime = new java.sql.Time (promotion.getStartTime().getTime());
	       etime = new java.sql.Time (promotion.getEndTime().getTime());
 	
	       
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
