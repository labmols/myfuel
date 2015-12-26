package myfuel.response;

import java.util.ArrayList;

import myfuel.client.CustomerReport;
import myfuel.client.Promotion;
import myfuel.client.PromotionReport;
import myfuel.client.Station;

/***
 * 
 * This class will be used as a response from the server with the reports details for the Marketing Manager
 *
 */
@SuppressWarnings("serial")
public class MMReportsResponse extends Response {
	/***
	 * Will contain the details of the customers the had bought in a promotion
	 */
	private ArrayList<PromotionReport> pr ;
	/***
	 * Will contain the promotion names and details that exist in MyFuel DB
	 */
	private ArrayList<Promotion> names;
	/***
	 * Will contain the details for each station that exist in MyFuel DB
	 */
	private ArrayList<Station> stations;
	/***
	 * Will Contain the details of each customer's behavior for each station 
	 */
	private ArrayList<CustomerReport> creport;
	/***
	 * constructor for MMReportsResponse class
	 * @param pr - Will contain the details of the customers the had bought in a promotion
	 * @param names - Will contain the promotion names and details that exist in MyFuel DB
	 * @param stations - Will contain the details for each station that exist in MyFuel DB
	 * @param creport - Will Contain the details of each customer's behavior for each station 
	 */
	public MMReportsResponse(ArrayList<PromotionReport> pr, ArrayList<Promotion> names ,ArrayList<Station> stations,ArrayList<CustomerReport> creport)
	{
		this.pr = pr;
		this.setNames(names);
		this.setStations(stations);
		this.creport = creport;
	}
	public ArrayList<PromotionReport> getPr() {
		return pr;
	}

	public void setPr(ArrayList<PromotionReport> pr) {
		this.pr = pr;
	}
	public ArrayList<Promotion> getNames() {
		return names;
	}
	public void setNames(ArrayList<Promotion> names) {
		this.names = names;
	}
	public ArrayList<Station> getStations() {
		return stations;
	}
	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}
	public ArrayList<CustomerReport> getCreport() {
		return creport;
	}
	public void setCreport(ArrayList<CustomerReport> creport) {
		this.creport = creport;
	}

	
	
}
