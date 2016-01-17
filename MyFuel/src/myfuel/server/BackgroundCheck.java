package myfuel.server;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class BackgroundCheck {
	public BackgroundCheck(Connection con)
	{
	CheckHomeStatus checkHomeThread = new CheckHomeStatus(con);
	new Thread(checkHomeThread).start();
	}
}

 class CheckHomeStatus implements Runnable
{
	 Connection con;
	 public CheckHomeStatus(Connection con)
	 {
		 this.con = con;
	 }
	@Override
	public void run() {
		try {
			while(!con.isClosed())
			{
			Statement st = null;
			
			try {
				st = con.createStatement();
				st.executeUpdate("update home_order SET status=1 where datediff(curdate(),sdate) > 0 or (datediff(curdate(),sdate)=0 and TIMESTAMPDIFF(HOUR,sdate,NOW()) >=6)");
				st.close();
				try {
					TimeUnit.HOURS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	class CheckPurchasePaid implements Runnable
	{
		 Connection con;
		 public CheckPurchasePaid(Connection con)
		 {
			 this.con = con;
		 }
		@Override
		public void run() {
			try {
				while(!con.isClosed())
				{
				Statement st = null;
				
				try {
					java.util.Date currDate = new java.util.Date();
					
					st = con.createStatement();
					st.executeUpdate("update ");
					st.close();
					try {
						TimeUnit.DAYS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
