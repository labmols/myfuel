package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myfuel.Entity.Car;
import myfuel.Entity.Customer;
import myfuel.Entity.Network;
import myfuel.Entity.Station;
import myfuel.client.MyFuelClient;
import myfuel.gui.LogInGUI;
import myfuel.gui.UpdateCustomerDetailsGUI;
import myfuel.request.LoginRequest;
import myfuel.request.RequestEnum;
import myfuel.request.UpdateRequest;
import myfuel.response.Response;
import myfuel.response.CustomerLoginResponse;
import myfuel.response.booleanResponse;

/**
 * Update details Controller
 * @author Maor
 *
 */
public class UpdateDetailsActions extends GUIActions {
	
	/**
	 * Contains all the user login details(including his personal details ,etc)
	 */
	private CustomerLoginResponse res;
	/**
	 * this object contains the user interface
	 */
	private UpdateCustomerDetailsGUI gui;
	/**
	 * this object saves the list of customer cars before the changes.
	 */
	private ArrayList <Car> origCars;
	
	/**
	 * list of the customer original station networks , before update.
	 */
	private ArrayList <Integer> origNetworks;
	
	boolean isConfirmed=false;
	/**
	 * create new UpdateDetails controller , which working with the UpdateDetailsGUI.
	 * @param client - the client object for the communication.
	 * @param res - the UserLoginResponse object which contains all the required details.
	 */
	public UpdateDetailsActions(MyFuelClient client,CustomerLoginResponse res,LoginRequest lr) {
		super(client,lr);
		this.res = res;
		origCars = new ArrayList<Car>(res.getUser().getCars());
		origNetworks = new ArrayList<Integer> (res.getUser().getStations());
		gui = new UpdateCustomerDetailsGUI(this);
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	

	public Customer getUserDetails(){
		return res.getUser();
	}
	
	public ArrayList<Network> getNetworks(){
		return res.getNetworks();
	}

	/**
	 * this method notified by the Observable client and receives the response.
	 * the user notified according the response.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Customer user = res.getUser();
		if(arg instanceof booleanResponse)
		{
			booleanResponse res2= (booleanResponse) arg;
			if(!res2.getSuccess()) {
				gui.showErrorMessage(res2.getMsg());
				clearLists();
			}
			else
			{
				isConfirmed = true;
				gui.showOKMessage(res2.getMsg());
				
			}
		  gui.clearAll(user);
		}
	}


	/**
	 * Verify add car input and add new car to the user list.
	 * @param cid - car id number
	 * @param fid - fuel id number
	 */
	public void verifyCar(String cid, int fid) {
		// TODO Auto-generated method stub
		Car c = null;
		Customer user = res.getUser();
		if(!cid.equals("") && cid.length()==7){
		try {
		c = new Car(Integer.parseInt(cid),fid);
		if(!checkCar(Integer.parseInt(cid))){
			user.getCars().add(c);
			gui.showOKMessage("Car "+cid+" "+ "added!");
			gui.updateCarsCB(Integer.parseInt(cid));
		}
		else gui.showErrorMessage("You already have this car!");
		}
		catch (NumberFormatException e){
			gui.showErrorMessage("illegal Car id value!");
		}
	
		}
		else gui.showErrorMessage("illegal Car id value!");
	}
	
	/**
	 * check if this car already in the list.
	 * @param cid - the car id number.
	 * @return true if exist , otherwise false.
	 */
	public boolean checkCar(int cid)
	{
		Customer user = res.getUser();
		for(Car c: user.getCars())
			if(c.getcid() == cid) return true;
		return false;
	}
	
	/**
	 * Add new station network to the list regardless he have it already or 
	 * he have one station network access type.
	 * @param sname - the name of station network that chosen in the ComboBox.
	 */
	public void addNetwork(String sname, int access){
		
		ArrayList <Integer> userNetworks = res.getUser().getStations();
		if(access == 1 || (access==0 && userNetworks.isEmpty()))
		{
		for(Network s: res.getNetworks()){
			if(s.getName().equals(sname)){
				if(!userNetworks.contains(s.getNid())) {
					userNetworks.add(s.getNid());
					gui.showOKMessage("Network "+sname + " is added!");
					isConfirmed = false;
				}
				else gui.showErrorMessage("You already have this network !");
				}
			}
		}
		else gui.showErrorMessage("You Can't add more then one station networks in this access!");
	
		gui.updateStationCB(userNetworks,res.getNetworks());
		}
		
	/**
	 * Change station to another in the customer station list.
	 * @param oldS - Old Station name .
	 * @param newS - New station name.
	 */
	public void changeStation(String oldS, String newS)
	{
		ArrayList <Integer> userStations = res.getUser().getStations();
		int index = -1;
		for(Network s: res.getNetworks())
		{
			if(s.getName().equals(oldS))
			{
					index =userStations.indexOf(s.getNid());
			}
		}
		for(Network s: res.getNetworks())
		{
			if(s.getName().equals(newS))
				{
					if(index!=-1 && !userStations.contains(s.getNid()))
					{
						userStations.set(index, s.getNid());
						gui.showOKMessage("Station Network "+oldS + " changed to " +newS);
						isConfirmed = false;
					}
					else gui.showErrorMessage("You already have this station network!");
					
				}
			
		}
		
		gui.updateStationCB(userStations,res.getNetworks());
			
		}

		
	
	/**
	 * remove selected car from the user car list.
	 * @param cid - the car id number.
	 * @param index - index of this car in the list.
	 */
	public void removeCar(int cid, int index)
	{
		res.getUser().getCars().remove(index);
		gui.showErrorMessage("Car "+cid+" "+ "removed!");
		isConfirmed = false;
	}
	
	public void resetAccess()
	{
			ArrayList <Integer> userStations = res.getUser().getStations();
			userStations.clear();
			gui.updateStationCB(userStations, res.getNetworks());
	}
	
	
	/**
	 * verify all input textfields values and if all ok send update details request to the server.
	 * @param userid - userid value
	 * @param fname - first name value
	 * @param lname - last name value
	 * @param pass - password value
	 * @param repass - retype password value
	 * @param email - email value
	 * @param cnumber - credit card value
	 * @param toc - type of customer (0- for private,1-for company)
	 * @param atype - access type value (0-Unique station, 1- 1 or more stations)
	 * @param smodel - sale model value
	 */
	
	public void verifyDetails(String fname, String lname, String email,String address, String cnumber,
			int smodel,int atype)
	{
		Customer user = res.getUser();
		String errors="";
		errors += "Input Errors \n\n";
		Pattern pattern = Pattern.compile("^.+@.+\\..+$");
		Matcher matcher = pattern.matcher(email);
		boolean success = true;
		
		if(address.equals("")){
			success=false;
			errors+="address field empty.\n";
		}
		
		if(cnumber.equals(""))
		{
			success=false;
			errors+="illegal Credit Card value.\n";
		}
		
		if(fname.equals("") || !isAlpha(fname)){ 
			success = false;
			errors+="illegal First name value.\n";
		}
		
		if(lname.equals("") || !isAlpha(lname)){ 
			success = false;
			errors+="illegal Last name value.\n";
		}
		
		
		if(!matcher.matches()){
			success= false;
			errors+= "illegal Email value. \n";
		}
		
		if((smodel == 2 || smodel==4 ) && user.getCars().size() > 1)
		{
			success = false;
			errors+="You can't have more then one car in this sale model!\n";
		}
		
		
		if(!success) gui.showErrorMessage(errors);
		else  
		{
			user.setFname(fname);
			user.setLname(lname);
			user.setAddress(address);
			user.setCnumber(cnumber);
			user.setEmail(email);
			user.setAtype(atype);
			user.setSmodel(smodel);
			UpdateRequest request = new UpdateRequest(user);
			client.handleMessageFromGUI(request);
		}

		
	}
	
	/**
	 * clear all customer temporary lists.
	 */
	private void clearLists()
	{
		Customer user = res.getUser();
		user.getCars().clear();
		user.setCars(new ArrayList<Car>(origCars));
		user.getStations().clear();
		user.setStations(new ArrayList<Integer>(origNetworks));
	}
	

	
	@Override
	public void backToMenu() {
		if(!isConfirmed) clearLists();
		changeFrame(gui,this);
		new CustomerOptionsActions(client,res,lr);
		
	}
	
	 /**
	  * check if the string contains only letters 'a-z'
	  * @param name
	  * @return true if the input OK, otherwise false.
	  */
	private boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}



	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		this.LogOutRequest(gui, lr);
		
	}



	


}
