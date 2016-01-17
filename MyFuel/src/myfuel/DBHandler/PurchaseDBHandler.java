package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import myfuel.Entity.Purchase;
import myfuel.Request.PurchaseRequest;
import myfuel.Response.PurchaseResponse;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;

/**
 * This DB Handler handle all the queries related to the customer purchase user interface.
 * @author Maor
 *
 */
public class PurchaseDBHandler extends DBHandler{

	/**
	 * Create new Purchase DB Handler.
	 * @param server - MyFuelServer object.
	 * @param con - JDBC Connection driver connection.
	 */
	public PurchaseDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	
	/**
	 * Get the Purchases list from the Database for a specific customer.
	 * @param customerID - Customer ID number.
	 * @return
	 */
	private PurchaseResponse getAll(int customerID) {
		// TODO Auto-generated method stub
		ArrayList<Purchase> pList = new ArrayList<Purchase>();
		ArrayList <Date> datesList = new ArrayList<Date>();
		ResultSet rs = null;
		PreparedStatement ps = null;

			try 
			{
				ps= con.prepareStatement("SELECT p.pid,c.cid,s.name,f.fname,p.pdate,p.qty,p.bill,p.status"
						+ " FROM purchase p,customer_purchase c,station_in_network s, fuel_price f where c.uid = ? and p.pid=c.pid and p.fuelid=f.fuelid and s.sid = p.sid" );
				ps.setInt(1, customerID);
				rs = ps.executeQuery();
				while(rs.next())
				{
						Purchase p = new Purchase(customerID,rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),rs.getFloat(6),rs.getFloat(7),rs.getBoolean(8));
						pList.add(p);
				}
				ps= con.prepareStatement("SELECT DISTINCT STR_TO_DATE( DATE_FORMAT( pdate,  '%c,01,%Y' ) ,'%m,%d,%Y') as date "+
										 " FROM purchase p,customer_purchase c where p.pid = c.pid and c.uid = ? "
										 + "ORDER BY date desc ");
				ps.setInt(1, customerID);
				rs = ps.executeQuery();
				while(rs.next())
				{
					datesList.add(rs.getDate(1));
				}
				ps.close();
				rs.close();
				return new PurchaseResponse(pList, datesList);
				
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

	/**
	 * Notified by the server when a new Request is received.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof PurchaseRequest)
		{
			PurchaseRequest req = (PurchaseRequest) arg;
			PurchaseResponse res = getAll(req.getCustomerID());
			if(res != null)
			server.setResponse(res);
			else server.setResponse(new booleanResponse(false, "SQL Error"));
		}
	}
	

}
