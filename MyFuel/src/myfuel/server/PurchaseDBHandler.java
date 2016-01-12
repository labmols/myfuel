package myfuel.server;

import java.sql.Connection;
import java.util.Observable;

public class PurchaseDBHandler extends DBHandler{

	public PurchaseDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}
	
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	

}
