package myfuel.server;

import java.io.IOException;
import java.sql.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.alee.laf.WebLookAndFeel;

import myfuel.gui.ConnectDialog;
import myfuel.gui.ServerGUI;
import myfuel.ocsf.server.AbstractServer;
import myfuel.ocsf.server.ConnectionToClient;
import myfuel.ocsf.server.ObservableServer;
import myfuel.request.LoginRequest;
import myfuel.response.ErrorEnum;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.WorkerLoginResponse;


public class MyFuelServer extends ObservableServer{
	
	/**
	 * JDBC Connection driver.
	 */
	private Connection con;
	/**
	 * Response object (send to client).
	 */
	private Response response;
	/**
	 * Server User interface.
	 */
	private ServerGUI gui;

	/**
	 * Create new Server.
	 * @param port - The port value.
	 * @param gui- Server User interface.
	 */
	public MyFuelServer(int port,ServerGUI gui) {
		super(port);
		this.gui = gui;
	
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		setChanged();
		String className = msg.getClass().getSimpleName();
		gui.printMsg("<Recieved>: "+className);
		notifyObservers(msg);
		try {
			if(response != null)
			{
			client.sendToClient(response);
			className = response.getClass().getSimpleName();
			gui.printMsg("<Sent To Client>: "+className);
			}
			setClientInfo(client,msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	 protected synchronized void clientConnected(ConnectionToClient client) 
	  {
	    setChanged();
	    gui.printMsg("<ClientConnected>:"+ client + " Connected!");
	  }
	
	/**
	 * Called when a client disconnected and change his status to "Not connected" in DB.
	 */
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) 
	  {
	    setChanged();
	    LoginRequest l = (LoginRequest)(client.getInfo("Info"));
	    notifyObservers(l);
	    if(client.getInetAddress()!=null) 
	    {
	    if(l!=null)
	    	gui.printMsg("Client with ID: " + l.getUserid() +" " + client+ " Disconnected!");
	    else gui.printMsg("<ClientDisconnected>:"+ client + " Disconnected!");
	    }
	  }
	
	 synchronized public void setResponse(Response response){
		 this.response=response;
	 }
	 
	 /**
	  * Set client info when connecting.
	  * @param client - The client object.
	  * @param msg - the client info(Login details).
	  */
	 private void setClientInfo(ConnectionToClient client, Object msg) {
			// TODO Auto-generated method stub
			if(msg instanceof LoginRequest){
				LoginRequest req = (LoginRequest) msg;
				req.setChangeStatus(1);
				if(response instanceof CustomerLoginResponse || response instanceof WorkerLoginResponse){
					client.setInfo("Info", req);
				}
			}
	}

	 /**
	  * Close MySQL Conncetion
	  */
 public void closeDBConnection()
 {
	try {
		if(con != null)
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
 }
 
 @Override
 public void serverClosed()
 {
	 closeDBConnection(); 
	 this.deleteObservers();
 }

 /**
  * Initialize variables and DBControllers and established MySQL connection.
  * @param add - MySQL server address.
  * @param USER -MySQL server userName.
  * @param PASS - MySQL server password.
  * @throws SQLException
  */
 public void InitServer (String add, String USER, String PASS) throws SQLException {
	// TODO Auto-generated method stub
	
	String DB_URL = "jdbc:mysql://"+add+"/myfuel";
	
		con = DriverManager.getConnection(DB_URL,USER,PASS);

	new LoginDBHandler(this,con);
	new RegisterDBHandler(this,con);
	new CPromotionTemplateDBHandler(this,con);
	new MakeaPromotionDBHandler(this,con);
	new ConfirmNewRatesDBHandler(this,con);
	new SetNewRatesDBHandler(this,con);
	new ChangePasswordDBHandler(this,con);
	new UpdateDetailsDBHandler(this,con);
	new SWDBHandler(this,con);
	new ConfirmationDBHandler(this,con);
	new LowInventoryDBHandler(this,con);
	new CheckInventoryDBHandler(this,con);
	new FuelOrderDBHandler(this,con);
	new HomeControlDBHandler(this,con);
	new MMReportDBHandler(this,con);
	new SReportsDBHandler(this,con);
	new NetworkReportsDBHandler(this,con);
	new AnalysticDBHandler(this,con);
	new PurchaseDBHandler(this, con);
	new BackgroundCheck(con);
	
}

public static void main(String [] args){
	
	 SwingUtilities.invokeLater ( new Runnable ()
     {
         public void run ()
         {
        	 // Set java look and feel.
         	WebLookAndFeel.install ();
         	JFrame serverFrame = new ServerGUI(null);
        	serverFrame.setVisible(true);
        	
         }
     } );
	
}
	

}

