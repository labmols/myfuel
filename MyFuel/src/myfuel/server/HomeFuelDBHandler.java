package myfuel.server;

import java.sql.Connection;
import java.util.Observable;

/**
 * Home Fuel DB Controller class, Make all queries related to Home Fuel Options.
 * @author Maor
 *
 */
public class HomeFuelDBHandler extends DBHandler{

	public HomeFuelDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
