package myfuel.server;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Observable;
import java.util.Observer;
import java.sql.*;

import myfuel.client.PromotionTemplateRequest;
import myfuel.gui.Promotion;

public class CreatePromotionTemplateDBHandler implements Observer {

	Connection con;
	MyFuelServer server;
	
	
	public CreatePromotionTemplateDBHandler(MyFuelServer server,Connection con)
	{
		this.server = server;
		this.con = con;
		server.addObserver(this);
	}
	
	void insert_promotionTemplate(PromotionTemplateRequest request)
	{
		ResultSet rs = null;
		PreparedStatement ps = null;
		Promotion promotion = request.p;
		 GregorianCalendar gc = new GregorianCalendar();
	        gc.setTime(request.p.startTime);
	       GregorianCalendar gc2 = new GregorianCalendar();
	       
	       java.sql.Time stime = new java.sql.Time (request.p.startTime.getTime());
	       java.sql.Time etime = new java.sql.Time (request.p.startTime.getTime());
	        gc.setTime(request.p.endTime); 
		try {
			ps=con.prepareStatement("insert into prom_temp values(?,?,?,?,?,?)");
		ps.setInt(1, 0);
		ps.setString(2, promotion.name);
		ps.setFloat(3, promotion.discount);
		ps.setTime(4, stime);
		ps.setTime(5, etime);
		ps.setInt(6, promotion.typeOfCustomer);
		
		ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if( arg1 instanceof  PromotionTemplateRequest)
		{
			PromotionTemplateRequest request = (PromotionTemplateRequest)arg1;
			insert_promotionTemplate(request);
		}
		
	}

}
