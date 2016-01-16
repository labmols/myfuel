package myfuel.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Fuel;
import myfuel.client.Rate;
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
	private ArrayList<Rate> sModes;
	/***
	 * Current Rates 
	 */
	private ArrayList<Rate> current;
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
	private void getDetails(int nid)
	{
		ResultSet rs = null ;
		PreparedStatement ps = null;
		sModes = new ArrayList<Rate>(); 
		current = new ArrayList<Rate>(); 
		
		try{
			   ps = con.prepareStatement("select modelid,discount from suggest_rates where nid = ? and modelid != 1");
			   ps.setInt(1, nid);
			   rs = ps.executeQuery();
			   
			  while(rs.next())
			  {
				 sModes.add(new Rate(rs.getInt(1),rs.getFloat(2)));
			  }
			  
			  if(sModes.isEmpty())
			  {
				  answer = false;
				  str = "There are no new suggestions";
				  return;
			  }
			  
			  ps = con.prepareStatement("select r.modelid , s.desc , r.discount "
			  		+ "					 from network_rate as r , sale_model as s"
			  		+ "					 where nid = ? and r.modelid != 1"
			  		+ "						and r.modelid = s.modelid");
			  ps.setInt(1, nid);
			  rs = ps.executeQuery();
			  
			  while(rs.next())
			  {
					  current.add(new Rate(rs.getInt(1),rs.getString(2),rs.getFloat(3)));
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
 * @param nid 
 */
	private void deleteSuggest(int nid) 
	{
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement("DELETE from suggest_rates where nid = ?");
			ps.setInt(1, nid);
			
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
	private void updatePrices(ArrayList<Rate> a,int nid)
	{
		PreparedStatement ps = null;
		
		try{
			
			for(Rate s : a)
			{
			ps = con.prepareStatement("update network_rate SET discount = ? where modelid = ? and nid = ?");
				ps.setFloat(1,s.getDiscount());
				ps.setInt(2, s.getModelid());
				ps.setInt(3, nid);
				ps.executeUpdate();
			}
			
			answer = true;
			str = "Rates has been updated!";
			
			deleteSuggest(nid);
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
				getDetails(r.getNid());
				if(!answer)
					server.setResponse(new booleanResponse(answer,str));
				else
					server.setResponse(new ConfirmNewRatesResponse(sModes,current));
			}
			
			else if(r.getType() == RequestEnum.Delete)
			{
				deleteSuggest(r.getNid());
				deleteMsg(r.getNid());
				server.setResponse(new booleanResponse(answer,nstr));
			}
			
			else if(r.getType() == RequestEnum.Insert)
			{
				updatePrices(r.getApproved(),r.getNid());
				deleteMsg(r.getNid());
				server.setResponse(new booleanResponse(answer,str));
			}
		}
		
	}
	
	/***
	 * Delete Message From the DB
	 */
	private void deleteMsg(int nid) 
	{
		PreparedStatement ps = null;
		try{
			ps = con.prepareStatement("DELETE from  message where nid = ? and type =?");
			ps.setInt(1, nid);
			ps.setInt(2,0);
			ps.executeUpdate();
			
		}catch(SQLException e )
		{
			e.printStackTrace();
			answer = false;
			str = "There was an error with the server!";
		}
		
	}



}
