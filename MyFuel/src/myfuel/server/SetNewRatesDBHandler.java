package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.saleModel;
import myfuel.request.SetNewRatesRequest;
import myfuel.response.SetNewRatesResponse;
import myfuel.response.booleanResponse;


public class SetNewRatesDBHandler extends DBHandler{
	private ArrayList<saleModel> OldRates;
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
		OldRates = new ArrayList<saleModel>();
		try {
			
			ps = con.prepareStatement("select * from price_to_type");
			rs = ps.executeQuery();
			while(rs.next())
			{
				OldRates.add(new saleModel(rs.getInt(1),rs.getInt(3)));
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
					for(saleModel f: request.getNewRates()){
						ps= con.prepareStatement("insert into suggest_rates values(?,?)");
						ps.setInt(1,f.getType());
						ps.setInt(2,f.getDiscount());
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
	public ArrayList<saleModel> getOldRates() {
		return OldRates;
	}

	public void setOldRates(ArrayList<saleModel> OldRates) {
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
