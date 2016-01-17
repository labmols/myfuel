package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.Entity.Promotion;
import myfuel.Entity.PromotionTemplate;
import myfuel.request.MakeaPromotionRequest;
import myfuel.response.MakeaPromotionResponse;
import myfuel.response.booleanResponse;
import myfuel.server.MyFuelServer;

/**
 * This class is responsible for Updating the DB with new Promotion
 * @author karmo
 *
 */
public class MakeaPromotionDBHandler extends DBHandler{
	/***
	 * List of all Promotion Templates  with their details
	 */
	private ArrayList<PromotionTemplate> templates;
	/***
	 * The request object as received from the client
	 */
	private MakeaPromotionRequest request;
	/***
	 * Indicates the status of the operation
	 */
	private boolean answer =true;
	/***
	 * Description of the operation
	 */
	private String str;
	
	/***
	 * MakeaPromotionDBHandler Constructor
	 * @param server - MyFuelServer
 	 * @param con - JDBC
	 */
	public MakeaPromotionDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}
	
		/***
		 * Get promotion templates from the DB
		 */
	 void selectPTemplates()
	 {
		 	ResultSet rs = null ;
			PreparedStatement ps = null;
			templates = new ArrayList<PromotionTemplate>();
			try {
				ps = con.prepareStatement("select * from prom_temp");
				
				rs = ps.executeQuery();
				
				while(rs.next())
				{
					templates.add(new PromotionTemplate(rs.getInt(1),rs.getString(2),rs.getFloat(5),rs.getTime(3),rs.getTime(4),rs.getInt(6),rs.getInt(7)));
					
				}
			
			} catch (SQLException e) {
				
				str = "An error with the server connection";
				e.printStackTrace();
			}
		
	 }
	 
	 
	 /***
	  * Checks if there is already a promotion with those details
	  * If exists the new promotion won't be insert to the DB
	  * else the DB will be updated with the new promotion
	  */
	 void checkExist()
	 {
		 	ResultSet exist = null ;
			PreparedStatement ps = null;
			
				
		
		       try {
		   		ps=con.prepareStatement("SELECT  pid from promotion where tid = ? and sdate = ? and fdate = ?");
		   		
		   		ps.setInt(1,request.getTid());
		   		ps.setDate(2, new java.sql.Date(request.getStart().getTime()));
				ps.setDate(3, new java.sql.Date(request.getEnd().getTime())); 
		   		exist =  ps.executeQuery();
		   		
		   		
		   		
		   		if(!exist.next())
		   		{
		   		
		   			insertNewPromotion();
		   			answer = true;
		   		}
		   		else
		   		{
		   			answer = false;
		   			str = "This promotion already exist!";
		   		}
		   		
		       } catch (SQLException e) {
		   			answer = false;
		   			str = "An error with the server connection";
		   			e.printStackTrace();
		   		}  
	 }
	 /***
	  * Insert the promotion details to the DB
	  */
	 void insertNewPromotion()
	 {
		 PreparedStatement ps = null;
		
		 
		 try {
			 
			ps= con.prepareStatement("insert into promotion values(?,?,?,?)");
			
			ps.setInt(1,0);
			ps.setInt(2, request.getTid());
			ps.setDate(3, new java.sql.Date(request.getStart().getTime()));
			ps.setDate(4, new java.sql.Date(request.getEnd().getTime()));
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			str = "An error with the server connection";
			answer = false;
			e.printStackTrace();
		}
		 
	 }
	 
	 
	 

	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof MakeaPromotionRequest)
		{
			this.request = ((MakeaPromotionRequest)arg1);
			 
			 if(request.getType() == 0)
			 {
				 selectPTemplates();
				 server.setResponse(new MakeaPromotionResponse(templates));
			 }
			 else if(request.getType() == 1)
			 {
				 checkExist();
				 server.setResponse(new booleanResponse(answer,str));
			 }
		}
		
	}


}
