package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.Rate;
import myfuel.request.SetNewRatesRequest;
import myfuel.response.SetNewRatesResponse;
import myfuel.response.booleanResponse;


public class SetNewRatesDBHandler extends DBHandler{
	private ArrayList<Rate> OldRates;
	private SetNewRatesRequest request;
	private boolean answer =true;
	private String msg;
	public SetNewRatesDBHandler(MyFuelServer server, Connection con) {
		super(server, con);	
	}
	void getRates()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		OldRates = new ArrayList<Rate>();
		try {
			
			ps = con.prepareStatement("select * from price_to_type");
			rs = ps.executeQuery();
			while(rs.next())
			{
				OldRates.add(new Rate(rs.getInt(1),rs.getString(2),rs.getFloat(3)));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	void insertNewRates()
	{
		msg="";
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
	
		 try {
				ps = con.prepareStatement("select * from suggest_rates");
				rs = ps.executeQuery();
				if(rs.next())
				{
					msg="There is One Suggested Rate wait";
				}
				else
				{
					for(Rate r: request.getNewRates()){
						ps= con.prepareStatement("insert into suggest_rates values(?,?)");
						ps.setInt(1,r.getModelid());
						ps.setFloat(2,r.getDiscount());
						ps.executeUpdate();
					}
					msg="The Suggested Rates are Send to confirm";
				}
		} catch (SQLException e) {
			answer = false;
			msg="The Update faild";
			e.printStackTrace();
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof SetNewRatesRequest)
		{
			 setRequest((SetNewRatesRequest)arg1);
			 
			 if(request.getType() == 0)
			 {
				 getRates();
				server.setResponse(new SetNewRatesResponse(OldRates));
			 }
			 else if(request.getType() == 1)
			 {
				 insertNewRates();
				 server.setResponse(new booleanResponse(answer,msg));
			 }
		}
		
	}


	public void setRequest(SetNewRatesRequest request) {
		this.request = request;
	}


}
