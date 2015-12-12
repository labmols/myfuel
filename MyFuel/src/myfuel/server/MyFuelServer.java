package myfuel.server;

import java.io.IOException;
import java.sql.*;
import java.util.Observable;
import java.util.Observer;

import myfuel.ocsf.server.AbstractServer;
import myfuel.ocsf.server.ConnectionToClient;
import myfuel.ocsf.server.ObservableServer;


public class MyFuelServer extends ObservableServer{
	private Connection con;
	HandlerMap hmap;
	Object response;
	public MyFuelServer(int port) {
		super(port);
		try {
			con = DriverManager.getConnection("jdbc:mysql://23.244.69.163:3306/myfuel","myfuel","labmols1"
					+ "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hmap = new HandlerMap(con);
		new LoginDBHandler(this,con);
		new RegisterDBHandler(this,con);
		new CreatePromotionTemplateDBHandler(this,con);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub

		//RequestHandler handler= hmap.handlerByType.get(msg.getClass());
		//Response response = handler.handleRequest(msg);
		//if(response instanceof UserLoginResponse){
			//UserLoginResponse res = (UserLoginResponse) response;
			//if(res.errorCode==-1) client.setInfo("UserID", res.user.userid);
		//}
		setChanged();
		notifyObservers(msg);
		try {
			
			client.sendToClient(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	 protected synchronized void clientConnected(ConnectionToClient client) 
	  {
	    setChanged();
	    notifyObservers(CLIENT_CONNECTED);
	    System.out.println(client + " Connected!");
	  }
	
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) 
	  {
	    setChanged();
	    notifyObservers(CLIENT_DISCONNECTED);
	    if(client.getInetAddress()!=null)System.out.println(client + " Disconnected!");
	  }
	
	 synchronized public void setResponse(Object response){
		 this.response=response;
	 }



public static void main(String [] args)
{
	MyFuelServer server = new MyFuelServer(5555);
	
	 try 
	    {
	      server.listen(); //Start listening for connections
	      System.out.println("Server is up and listening to port 5555...");
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println("ERROR - Could not listen for clients!");
	    }
	  }


}

