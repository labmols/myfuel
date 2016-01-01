package myfuel.server;

import java.io.IOException;
import java.sql.*;
import java.util.Observable;
import java.util.Observer;

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
	 * 
	 */
	private Connection con;
	Response response;
	
	/**
	 * 
	 * @param port
	 */
	public MyFuelServer(int port) {
		super(port);
		try {
			con = DriverManager.getConnection("jdbc:mysql://23.244.69.163:3306/myfuel","myfuel","labmols1"
					+ "");
		//	con = DriverManager.getConnection("jdbc:mysql://localhost/myfuel2","root",""
		//		+ "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		setChanged();
		notifyObservers(msg);
		try {
			client.sendToClient(response);
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
	    notifyObservers(CLIENT_CONNECTED);
	    System.out.println(client + " Connected!");
	  }
	
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) 
	  {
	    setChanged();
	    LoginRequest l = (LoginRequest)(client.getInfo("Info"));
	    notifyObservers(l);
	    if(client.getInetAddress()!=null)System.out.println("Client with ID: " + l.getUserid() +" " + client+ " Disconnected!");
	  }
	
	 synchronized public void setResponse(Response response){
		 this.response=response;
	 }
	 
	 private void setClientInfo(ConnectionToClient client, Object msg) {
			// TODO Auto-generated method stub
			if(msg instanceof LoginRequest ){
				LoginRequest req = (LoginRequest) msg;
				req.setChangeStatus(1);
				if(response instanceof CustomerLoginResponse || response instanceof WorkerLoginResponse){
					client.setInfo("Info", req);
				}
			}
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

