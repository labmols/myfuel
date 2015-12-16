package myfuel.server;

import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;

public abstract class DBHandler implements Observer{
	Connection con;
	MyFuelServer server;
	public DBHandler(MyFuelServer server, Connection con){
			this.con = con;
			this.server = server;
			server.addObserver(this);
		}

	

}
