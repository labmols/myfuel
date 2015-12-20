package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.request.SetNewRatesRequest;
import myfuel.response.SetNewRatesResponse;
import myfuel.response.booleanResponse;


public class SetNewRatesDBHandler extends DBHandler{
	private ArrayList<Fuel> OldRates;
	private SetNewRatesRequest request;
	private boolean answer =true;
	public SetNewRatesDBHandler(MyFuelServer server, Connection con) {
		super(server, con);	
	}

	
	
	void getRates()
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		OldRates = new ArrayList<Fuel>();
		try {
			
			ps = con.prepareStatement("select * from fuel_price");
			rs = ps.executeQuery();
			while(rs.next())
			{
				OldRates.add(new Fuel(rs.getInt(1),rs.getFloat(3),rs.getFloat(4)));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	void insertNewRates()
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
	
		 try {
				ps = con.prepareStatement("select * from suggest_fuel");
				rs = ps.executeQuery();
				if(rs.next())
				{
				
				}
				else
				{
					for(Fuel f: request.getNewRates()){
						ps= con.prepareStatement("insert into suggest_fuel values(?,?)");
						ps.setInt(1,f.getFid());
						ps.setFloat(2,f.getSuggPrice());
						ps.executeUpdate();
					}
				}
		} catch (SQLException e) {
			answer = false;
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
				 server.setResponse(new booleanResponse(answer));
			 }
		}
		
	}
	public ArrayList<Fuel> getOldRates() {
		return OldRates;
	}

	public void setOldRates(ArrayList<Fuel> OldRates) {
		this.OldRates = OldRates;
	}

	public SetNewRatesRequest getRequest() {
		return request;
	}

	public void setRequest(SetNewRatesRequest request) {
		this.request = request;
	}
	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

}
