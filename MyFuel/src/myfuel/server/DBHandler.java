package myfuel.server;

import java.sql.Connection;
import java.util.Observable;
import java.util.Observer;

public class DBHandler implements Observer{
	Connection con;
	MyFuelServer server;
		DBHandler(MyFuelServer server, Connection con){
			this.con = con;
			this.server = server;
			server.addObserver(this);
		}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
