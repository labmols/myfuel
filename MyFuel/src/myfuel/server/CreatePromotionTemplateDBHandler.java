package myfuel.server;

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
		
		try {
			ps=con.prepareStatement("insert into prom_temp values(?,?,?,?,?)");
		
		ps.setString(1, promotion.name);
		ps.setFloat(2, promotion.discount);
		ps.setDate(3, promotion.startTime);
		ps.setString(4, promotion.endTime);
		ps.setInt(5, promotion.typeOfCustomer);
		
		ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
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
