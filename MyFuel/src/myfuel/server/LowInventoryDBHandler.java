package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Observable;

import myfuel.request.LowInventoryRequest;
import myfuel.request.SetNewRatesRequest;
import myfuel.response.SetNewRatesResponse;
import myfuel.response.booleanResponse;

public class LowInventoryDBHandler  extends DBHandler {
	private LowInventoryRequest request;
	private boolean answer =true;
	private String msg;

	public LowInventoryDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
	}

	
	public void UpdateLowInventory()
	{
		PreparedStatement ps = null;
		try{
			for(int i=0; i < 3 ;i++)
			{
				ps = con.prepareStatement("update station_inventory SET mqty = ? WHERE sid = ? and fuelid = ?");
				ps.setInt(1,request.getNewLowInventory());
				ps.setInt(2,request.getSid());
				ps.setInt(3, i+1);
				ps.executeUpdate();
			}
		answer = true;
		msg = "Minimal Quantity has been updated succesfully";
			}
		catch(SQLException e)
		{
			answer = false;
			msg = "There was an error with the server";
			e.printStackTrace();
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
		if(arg1 instanceof LowInventoryRequest)
		{
			setLowInventoryRequest((LowInventoryRequest)arg1);
			UpdateLowInventory();
			 server.setResponse(new booleanResponse(answer,msg));
		}
	}
	
	public LowInventoryRequest getLowInventoryRequest()
	{
		return this.request;
	}
	public void setLowInventoryRequest(LowInventoryRequest request) 
	{
		this.request=request;
	}


	public boolean isAnswer() {
		return answer;
	}


	public void setAnswer(boolean answer) {
		this.answer = answer;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
