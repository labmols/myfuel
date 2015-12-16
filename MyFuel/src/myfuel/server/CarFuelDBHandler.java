package myfuel.server;

import java.sql.Connection;
import java.util.Observable;

public class CarFuelDBHandler extends DBHandler{

	CarFuelDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
