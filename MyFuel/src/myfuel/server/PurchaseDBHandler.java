package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Purchase;
import myfuel.request.PurchaseRequest;
import myfuel.response.PurchaseResponse;

public class PurchaseDBHandler extends DBHandler{

	public PurchaseDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	
	private ArrayList<Purchase> getPurchases(int customerID) {
		// TODO Auto-generated method stub
		ArrayList<Purchase> pList = new ArrayList<Purchase>();
		ResultSet rs = null;
		PreparedStatement ps = null;

			try 
			{
				ps= con.prepareStatement("SELECT p.pid,c.cid,s.name,f.fname,p.pdate,p.qty,p.bill FROM purchase p,customer_purchase c,station_in_network s, fuel_price f where c.uid = ? and p.pid=c.pid and p.fuelid=f.fuelid and s.sid = p.sid" );
				ps.setInt(1, customerID);
				rs = ps.executeQuery();
				while(rs.next())
				{
						Purchase p = new Purchase(customerID,rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getFloat(6),rs.getFloat(7));
						pList.add(p);
				}
				ps.close();
				rs.close();
				return pList;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof PurchaseRequest)
		{
			PurchaseRequest req = (PurchaseRequest) arg;
			ArrayList<Purchase> customerPurchases = getPurchases(req.getCustomerID());
			PurchaseResponse res = new PurchaseResponse(customerPurchases);
			server.setResponse(res);
		}
	}
	

}
