package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import com.mysql.*;

import myfuel.client.PromotionReport;
import myfuel.request.MMRerportsRequest;
import myfuel.response.MMReportsResponse;

public class MMReportDBHandler extends DBHandler{
private ArrayList<PromotionReport> pr = null;

	public MMReportDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	private void getReportsDetails()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		pr = new ArrayList<PromotionReport>();
		
		try{
			
			ps = con.prepareStatement(" select t.pname,c.uid,c.fname,c.lname,p.qty,c.toc,p.bill"
					+ "					from customer as c, purchase as p, customer_purchase as cp, promotion as pr, prom_temp as t"
					+ "					where c.uid = cp.uid and cp.pid = p.pid"
					+ "					and p.prid = pr.pid and pr.tid = t.tid ");
		
			rs = ps.executeQuery();
			while(rs.next())
			{
				pr.add(new PromotionReport(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getInt(6),rs.getFloat(7)));
			}
			
		}
		catch(SQLException e )
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
	
		if(arg1 instanceof MMRerportsRequest)
		{
			getReportsDetails();
			server.setResponse(new MMReportsResponse(pr));
		}
		
	}
	

}
