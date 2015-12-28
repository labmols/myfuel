package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.client.Promotion;
import myfuel.client.PromotionTemplate;
import myfuel.request.MakeaPromotionRequest;
import myfuel.response.MakeaPromotionResponse;
import myfuel.response.booleanResponse;

public class MakeaPromotionDBHandler extends DBHandler{
	private ArrayList<PromotionTemplate> templates;
	private MakeaPromotionRequest request;
	private boolean answer =true;
	private String str;
	MakeaPromotionDBHandler(MyFuelServer server, Connection con) {
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
	  */
	 void checkExist()
	 {
		 	ResultSet exist = null ;
			PreparedStatement ps = null;
			
				
 
		       try {
		   		ps=con.prepareStatement("SELECT p.pid"+
		   			"	FROM promotion as p"+
		   			"	WHERE "+
		   			"	( p.tid = ? AND(   (p.sdate <= ? AND p.fdate >= ?) "
		   			+ "OR (p.sdate <=? AND p.fdate >= ? AND p.fdate <=?))"
		   			+ "OR ((p.sdate <= ? AND p.fdate >= ? AND p.sdate >= ?))"
		   			+ "OR (p.fdate <= ? AND p.sdate >= ?))");
		   		
		   		ps.setInt(1,request.getTid());
		   		ps.setDate(2, new java.sql.Date(request.getStart().getTime()));
				ps.setDate(3, new java.sql.Date(request.getEnd().getTime())); 
				ps.setDate(4, new java.sql.Date(request.getStart().getTime()));
				ps.setDate(5, new java.sql.Date(request.getStart().getTime()));
				ps.setDate(6, new java.sql.Date(request.getEnd().getTime())); 
				ps.setDate(7, new java.sql.Date(request.getEnd().getTime())); 
				ps.setDate(8, new java.sql.Date(request.getEnd().getTime())); 
				ps.setDate(9, new java.sql.Date(request.getStart().getTime()));
				ps.setDate(10, new java.sql.Date(request.getEnd().getTime())); 
				ps.setDate(11, new java.sql.Date(request.getStart().getTime()));
		   		exist =  ps.executeQuery();
		   		
		   		
		   		
		   		if(!exist.next())
		   		{
		   			//System.out.println(exist.next());
		   			insertNewPromotion();
		   			answer = true;
		   		}
		   		else
		   		{
		   			answer = false;
		   			str = "Promotion already exist!";
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
	 
	 
	 
	/***
	 * Gets the response from the server 
	 * Processing the message due to the functions in this class
	 * Send appropriate response to the Client 
	 */
	 
	 
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof MakeaPromotionRequest)
		{
			 setRequest((MakeaPromotionRequest)arg1);
			 
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
/***
 * 
 * @return Promotion templates arraylist
 */
	public ArrayList<PromotionTemplate> getTemplates() {
		return templates;
	}
/**
 *  sets promotion templates arraylist
 * @param templates
 */
	public void setTemplates(ArrayList<PromotionTemplate> templates) {
		this.templates = templates;
	}
/***
 * getting the request
 * @return
 */
	public MakeaPromotionRequest getRequest() {
		return request;
	}
/***
 * setting the request
 * @param request
 */
	public void setRequest(MakeaPromotionRequest request) {
		this.request = request;
	}
/***
 * 
 * @return answer
 */
	public boolean isAnswer() {
		return answer;
	}
/***
 * set answer
 * @param answer
 */
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

}
