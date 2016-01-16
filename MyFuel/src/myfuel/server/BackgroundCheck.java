package myfuel.server;

import java.sql.*;

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
					Thread.sleep(1000*60);
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
					st = con.createStatement();
					st.executeUpdate("update ");
					st.close();
					try {
						Thread.sleep(1000*60*60);
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
