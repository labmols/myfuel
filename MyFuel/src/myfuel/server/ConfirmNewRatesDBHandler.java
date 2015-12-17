package myfuel.server;

import java.sql.Connection;
import java.util.Observable;

import myfuel.request.ConfirmNewRatesRequest;

public class ConfirmNewRatesDBHandler extends DBHandler{
	
	private ConfirmNewRatesRequest request;
	
	public ConfirmNewRatesDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		
		if(arg instanceof ConfirmNewRatesRequest)
		{
			request = (ConfirmNewRatesRequest)arg;
			
			if(request.getType() == 1)
			{
				
			}
			
			else if(request.getType() == 2)
			{
				
			}
			
			else
			{
				
			}
		}
		
	}

}
