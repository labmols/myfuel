package myfuel.GUIActions;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Purchase;
import myfuel.server.DBHandler;
import myfuel.server.MyFuelServer;

public class PurchaseDBHandler extends DBHandler {

	public PurchaseDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}
	
	public ArrayList<Purchase> getPurchases(int customerID)
	{
		ArrayList<Purchase> pList= new ArrayList<Purchase>();
		
		
		return null;
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	

}
