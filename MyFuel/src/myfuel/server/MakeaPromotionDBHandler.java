package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.client.Promotion;
import myfuel.request.MakeaPromotionRequest;
import myfuel.response.MakeaPromotionResponse;
import myfuel.response.booleanResponse;

public class MakeaPromotionDBHandler extends DBHandler{
	private ArrayList<Promotion> templates;
	private MakeaPromotionRequest request;
	private boolean answer =true;
	MakeaPromotionDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}

	 void selectPTemplates()
	 {
		 	ResultSet rs = null ;
			PreparedStatement ps = null;
			templates = new ArrayList<Promotion>();
			try {
				ps = con.prepareStatement("select * from prom_temp");
				
				rs = ps.executeQuery();
				
				while(rs.next())
				{
					templates.add(new Promotion(rs.getInt(1),rs.getString(2),rs.getFloat(5),rs.getTime(3),rs.getTime(4),rs.getInt(6)));
					
				}
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	 }
	 
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
			System.out.println("check");
			
		} catch (SQLException e) {
			answer = false;
			e.printStackTrace();
		}
		 
	 }
	 
	 
	 
	 
	 
	 
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
				 insertNewPromotion();
				 server.setResponse(new booleanResponse(answer));
			 }
		}
		
	}

	public ArrayList<Promotion> getTemplates() {
		return templates;
	}

	public void setTemplates(ArrayList<Promotion> templates) {
		this.templates = templates;
	}

	public MakeaPromotionRequest getRequest() {
		return request;
	}

	public void setRequest(MakeaPromotionRequest request) {
		this.request = request;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

}
