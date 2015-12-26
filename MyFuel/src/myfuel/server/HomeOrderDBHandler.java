package myfuel.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.PromotionTemplate;
import myfuel.client.Purchase;
import myfuel.request.HomeOrderRequest;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

/**
 * Home Fuel DB Controller class, Make all queries related to Home Fuel Options.
 * @author Maor
 *
 */
public class HomeOrderDBHandler extends DBHandler{

	public HomeOrderDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	
	private Response insertPurchase(Purchase p)
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		Statement stmt = null;
		
		
		
		try {
			rs = stmt.executeQuery("SHOW TABLE STATUS WHERE `Name` = 'purchase'");
			rs.next();
			String nextid = rs.getString("Auto_increment");
			
			/*/
			ps = con.prepareStatement("insert into purchase values(?,?,?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setInt(2, p.getSid());
			ps.setInt(3, p.getFuelid());
			ps.setInt(4, p.getPromid());
			ps.setDate(5, (Date) p.getPdate());
			ps.setFloat(6, p.getBill());
			ps.setFloat(7, p.getQty());
			
			ps.executeUpdate();
			/*/
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new booleanResponse(true, "f");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof HomeOrderRequest)
		{
			HomeOrderRequest req = (HomeOrderRequest) arg;
			insertPurchase(req.getPur());
		}
	}

}
