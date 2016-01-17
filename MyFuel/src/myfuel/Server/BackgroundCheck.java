package myfuel.Server;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * Server checking status threads in background.
 * @author Maor
 *
 */
public class BackgroundCheck {
	
	/**
	 * Create new BackgroundCheck object.
	 * @param con - JDBC Connection driver.
	 */
	public BackgroundCheck(Connection con)
	{
	CheckHomeStatus checkHomeThread = new CheckHomeStatus(con);
	CheckPurchasePaid checkPurchaseThread = new CheckPurchasePaid(con);
	new Thread(checkHomeThread).start();
	new Thread(checkPurchaseThread).start();
	}
}

/**
 * Check Home Status Thread.
 * Checking (every hour) if home order delivered to the customer according to the shipping date and current date and time.
 * @author Maor
 *
 */
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
					//Sleep for an hour
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
}
	
/**
 * Check Purchase paid status.	
 * This thread checking(every hour) if current day of month is the first day of month , and 
 * simulate charging Fully monthly customers for their purchases of the last month.
 * @author Maor
 *
 */
	class CheckPurchasePaid implements Runnable
	{
		 Connection con;
		 public CheckPurchasePaid(Connection con)
		 {
			 this.con = con;
		 }
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			try {
				while(!con.isClosed())
				{
				Statement st = null;
				
				try {
					java.util.Date currDate = new java.util.Date();
					if(currDate.getDay() == 1)
					{
					st = con.createStatement();
					//Update all the last month purchases status to paid 
					st.executeUpdate("update purchase set status=1 where status = 0 and YEAR(pdate) = YEAR(curdate()) AND MONTH(pdate)+1=MONTH(curdate()) ");
					st.close();
					}
					try {
						//Sleep for an hour
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
}

