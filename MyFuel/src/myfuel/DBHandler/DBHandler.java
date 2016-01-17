package myfuel.DBHandler;

import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;

import myfuel.Server.MyFuelServer;

/**
 * Abstract class that implements Observer ,
 *  which means that when a new response received from the server , 
 * the update method notified.
 *  this is the basic logic for Database controller.
 * @author Maor
 *
 */
public abstract class DBHandler implements Observer{
	
	/**
	 * JDBC Driver connection.
	 */
	Connection con;
	
	/**
	 * MyFuel Server object.
	 */
	MyFuelServer server;
	/**
	 * new Database controller, responsible for SQL queries.
	 * @param server - MyFuel Server
	 * @param con - JDBC driver connection.
	 */
	public DBHandler(MyFuelServer server, Connection con){
			this.con = con;
			this.server = server;
			server.addObserver(this);
		}

	

}
