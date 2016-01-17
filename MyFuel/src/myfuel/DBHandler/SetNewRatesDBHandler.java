package myfuel.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import myfuel.Entity.Fuel;
import myfuel.Entity.Network;
import myfuel.Entity.NetworkRates;
import myfuel.Entity.Rate;
import myfuel.Request.SetNewRatesRequest;
import myfuel.Response.SetNewRatesResponse;
import myfuel.Response.booleanResponse;
import myfuel.Server.MyFuelServer;

/***
 * This class will Handle the update of the rates for the networks
 * @author karmo
 *
 */
public class SetNewRatesDBHandler extends DBHandler{
	/***
	 * Old rates details for each network
	 */
	private ArrayList<NetworkRates> OldRates;
	/***
	 * Rates details
	 */
	private ArrayList<Rate> rates;
	/***
	 * Network Details
	 */
	private ArrayList<Network> networks;
	/***
	 * New Rates details for each network
	 */
	private ArrayList<NetworkRates> newRates;
	/***
	 * Will be false if there was any error during executing the queries
	 */
	private boolean answer =true;
	/***
	 * Description of the error
	 */
	private String msg;
	/***
	 * SetNewRatesDBHandler constructor
	 * @param server - MyFuelServer
	 * @param con - JDBC
	 */
	public SetNewRatesDBHandler(MyFuelServer server, Connection con) {
		super(server, con);	
	}
	
	/***
	 * Getting Current Rates From the DB
	 */
	void getRates()
	{
		int nid = -1;
		int modelid;
		ResultSet rs = null ;
		PreparedStatement ps = null;
		OldRates = new ArrayList<NetworkRates>();
		rates = new ArrayList<Rate>();
		try {
			
			ps = con.prepareStatement("SELECT  r.nid,r.modelid,s.desc,r.discount" + " " +
									"	FROM  network_rate as r, sale_model as s" + " " +
									"	WHERE r.modelid = s.modelid" + " " +
									"	ORDER BY r.nid,r.modelid");
			rs = ps.executeQuery(); 
			while(rs.next())
			{
				nid = rs.getInt(1);
				modelid = rs.getInt(2);
				rates.add(new Rate(modelid,rs.getString(3),rs.getFloat(4)));
				
				if(modelid == 4)
				{
					OldRates.add(new NetworkRates(nid,new ArrayList<Rate>(rates)));
					rates.clear();
				}
								
			}
			networks = new ArrayList<Network> ();
			
			ps = con.prepareStatement("select * from network  ");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				networks.add(new Network(rs.getInt(1),rs.getString(2)));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/***
	 * Updating the DB with new rates
	 * After the updating it will send a message to the Network Manager
	 */
	void insertNewRates()
	{
		 ResultSet rs = null ;
		 PreparedStatement ps = null;
	
		 try {
			 	for(NetworkRates r : newRates)
			 	{
						ps = con.prepareStatement("select * from suggest_rates where nid = ? ");
						ps.setInt(1, r.getNid());
						rs = ps.executeQuery();
						
						if(rs.next())
						{
							ArrayList<Rate> suggestRates = new ArrayList<Rate>(r.getRates());
							for(Rate rate: suggestRates)
							{
								
								ps= con.prepareStatement("update suggest_rates SET discount = ? where nid = ? and modelid = ? ");
								ps.setInt(2, r.getNid());
								ps.setInt(3,rate.getModelid());
								ps.setFloat(1,rate.getDiscount());
								
								ps.executeUpdate();
							}
						}
						
						else 
						{
							ArrayList<Rate> suggestRates = new ArrayList<Rate>(r.getRates());
							for(Rate rate: suggestRates)
							{
								ps= con.prepareStatement("insert into suggest_rates values(?,?,?)");
								
								ps.setInt(1, r.getNid());
								ps.setInt(2,rate.getModelid());
								ps.setFloat(3,rate.getDiscount());
								
								ps.executeUpdate();
							}
							
							ps=con.prepareStatement("insert into message values(0,?,NULL,?,0)");
							ps.setInt(1, r.getNid());
							ps.setString(2,"New Rates Suggestion");
							
							ps.executeUpdate();
						}
						
					
			 	}
				
			 	answer = true;
			 	msg = "Rates has been updated succefully\nA message has been sent to the Network Managers";
				
		} catch (SQLException e) {
			answer = false;
			msg="There was an error with the server!";
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof SetNewRatesRequest)
		{
			
			SetNewRatesRequest request = (SetNewRatesRequest) arg1;
			
			 if(request.getType() == 0)
			 {
				 getRates();
				 server.setResponse(new SetNewRatesResponse(OldRates,networks));
			 }
			 
			 else if(request.getType() == 1)
			 {
				 newRates = new ArrayList<NetworkRates>(request.getNewRates());
				 insertNewRates();
				 server.setResponse(new booleanResponse(answer,msg));
			 }
		}
		
	}




}
