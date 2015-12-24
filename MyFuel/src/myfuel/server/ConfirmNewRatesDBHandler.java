package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.saleModel;
import myfuel.request.ConfirmNewRatesRequest;
import myfuel.request.RequestEnum;
import myfuel.response.ConfirmNewRatesResponse;
import myfuel.response.booleanResponse;

public class ConfirmNewRatesDBHandler extends DBHandler{
	private ArrayList<saleModel> sModes;
	private ArrayList<saleModel> current;
	private boolean answer ;
	private String str;
	public ConfirmNewRatesDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}

	private void getDetails()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		sModes = new ArrayList<saleModel>(); 
		current = new ArrayList<saleModel>(); 
		
		try{
			   ps = con.prepareStatement("select * from suggest_rates");
			   
			   rs = ps.executeQuery();
			   
			  while(rs.next())
			  {
				  if(rs.getInt(1) != 1)
					  sModes.add(new saleModel(rs.getInt(1),rs.getInt(2)));
			  }
			  
			  if(sModes.isEmpty())
			  {
				  answer = false;
				  str = "There are no new suggestions";
				  return;
			  }
			  
			  ps = con.prepareStatement("select * from price_to_type");
			  rs = ps.executeQuery();
			  
			  while(rs.next())
			  {
				  if(rs.getInt(1)!= 1)
					  current.add(new saleModel(rs.getInt(1),rs.getInt(2)));
			  }
			  
			   answer = true;
			   
		}catch(SQLException e)
		{
			answer = false;
			str = "There was an error with the server";
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof ConfirmNewRatesRequest)
		{
			ConfirmNewRatesRequest r = (ConfirmNewRatesRequest)arg;
			if(r.getType() == RequestEnum.Select)
			{
				getDetails();
				if(!answer)
					server.setResponse(new booleanResponse(answer,str));
				else
					server.setResponse(new ConfirmNewRatesResponse(sModes,current));
			}
		}
		
	}

}
