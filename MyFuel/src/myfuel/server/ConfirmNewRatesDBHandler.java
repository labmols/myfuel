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

/***
 * This class will be used as a DBHandler for getting and updating Company Rates
 * @author karmo
 *
 */
public class ConfirmNewRatesDBHandler extends DBHandler{
	/***
	 * Suggested Rates
	 */
	private ArrayList<saleModel> sModes;
	/***
	 * Current Rates 
	 */
	private ArrayList<saleModel> current;
	/***
	 * True if there was no exception and false otherwise
	 */
	private boolean answer ;
	/***
	 *  status of operation 
	 */
	private String str,nstr;
	
	/***
	 *  ConfirmNewRates DB Handler
	 * @param server  - MyFuelServer
	 * @param con - JDBC driver Connection
	 */
	public ConfirmNewRatesDBHandler(MyFuelServer server, Connection con) {
		super(server, con);
		
	}
/***
 * Getting the current discounts for type 
 * &
 * Getting the suggested discounts 
 */
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
					  current.add(new saleModel(rs.getInt(1),rs.getInt(3)));
			  }
			  
			   answer = true;
			   
		}catch(SQLException e)
		{
			answer = false;
			str = "There was an error with the server";
			e.printStackTrace();
		}
	}

/***
 *  Delete the suggested prices	
 */
	private void deleteSuggest() 
	{
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement("TRUNCATE TABLE suggest_rates");
			
			ps.executeUpdate();
			
			answer = true; 
			nstr = "Suggestion has been denied!";
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			answer = false;
			str ="There was an error with the server";
		}
		
	}
	/***
	 * Update Discounts for each sale model
	 * @param a - approved discounts
	 */
	private void updatePrices(ArrayList<saleModel> a)
	{
		PreparedStatement ps = null;
		
		try{
			
			for(saleModel s : a)
			{
			ps = con.prepareStatement("update price_to_type SET discount = ? where modelid = ?");
				ps.setInt(1,s.getDiscount());
				ps.setInt(2, s.getType());
				
				ps.executeUpdate();
			}
			
			answer = true;
			str = "Rates has been updated!";
			
			deleteSuggest();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			answer = false;
			str ="There was an error with the server";
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
			
			else if(r.getType() == RequestEnum.Delete)
			{
				deleteSuggest();
				deleteMsg();
				server.setResponse(new booleanResponse(answer,nstr));
			}
			
			else if(r.getType() == RequestEnum.Insert)
			{
				updatePrices(r.getApproved());
				deleteMsg();
				server.setResponse(new booleanResponse(answer,str));
			}
		}
		
	}
	
	/***
	 * Delete Message From the DB
	 */
	private void deleteMsg() 
	{
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement("DELETE from  message where sid = 4 and type =?");
			ps.setInt(1,0);
			ps.executeUpdate();
			
		}catch(SQLException e )
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server!";
		}
		
	}



}
