package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.Station;
import myfuel.gui.LogInGUI;
import myfuel.gui.UpdateUserDetailsGUI;
import myfuel.request.RequestEnum;
import myfuel.request.UpdateRequest;
import myfuel.response.Response;
import myfuel.response.UserLoginResponse;
import myfuel.response.booleanResponse;

/**
 * Update details Controller
 * @author Maor
 *
 */
public class UpdateDetailsActions extends GUIActions {
	/**
	 * this object contains all customer details
	 */
	Customer user;
	/**
	 * this object contains the user interface
	 */
	UpdateUserDetailsGUI gui;
	/**
	 * this object saves the list of customer cars before the changes.
	 */
	ArrayList <Car> origCars;
	/**
	 * this object contains all the current stations available 
	 */
	ArrayList <Station> stations;
	
	/**
	 * list of the customer original stations , before update.
	 */
	ArrayList <Integer> origStations;
	
	public UpdateDetailsActions(MyFuelClient client,Customer user,ArrayList <Station> stations) {
		super(client);
		this.user=user;
		this.stations = new ArrayList<Station>(stations);
		origCars = new ArrayList<Car>(user.getCars());
		origStations = new ArrayList<Integer> (user.getStations());
		gui = new UpdateUserDetailsGUI(this);
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	

	public Customer getUserDetails(){
		return user;
	}
	
	public ArrayList<Station> getStations(){
		return stations;
	}
	



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof booleanResponse)
		{
			booleanResponse res= (booleanResponse) arg;
			gui.showMessage(res.getMsg());
			if(!res.getSuccess()) {
				user.getCars().clear();
				user.setCars(new ArrayList<Car>(origCars));
				user.getStations().clear();
				user.setStations(new ArrayList<Integer>(origStations));
			}
		  gui.clearAll(user);
		}
	}



	public void verifyCar(String cid, int fid) {
		// TODO Auto-generated method stub
		Car c = null;
		if(!cid.equals("") && cid.length()==7){
		try {
		c = new Car(Integer.parseInt(cid),fid);
		if(!checkCar(Integer.parseInt(cid))){
			user.getCars().add(c);
			gui.showMessage("Car "+cid+" "+ "added!");
		}
		else gui.showMessage("You already have this car!");
		}
		catch (NumberFormatException e){
			gui.showMessage("illegal Car id value!");
		}
	
		}
		else gui.showMessage("illegal Car id value!");
	}
	
	public boolean checkCar(int cid)
	{
		for(Car c: user.getCars())
			if(c.getcid() == cid) return true;
		return false;
	}
	
	public void addStation(String sname, int access){
		if(access == 1){
		for(Station s: stations){
			if(s.getName().equals(sname)){
				if(!user.getStations().contains(s.getsid())) {
					user.getStations().add(s.getsid());
					gui.showMessage("Station "+sname + " is added!");
				}
				else gui.showMessage("You already have this station!");
				}
			}
		}
		else if(access == 0)
		{
			for(Station s: stations){
				if(s.getName().equals(sname)){
						user.getStations().clear();
						user.getStations().add(s.getsid());
						gui.showMessage("Your Station changed to "+sname );
					
					}
				}
		}
		}
	
	public void removeCar(int cid, int index)
	{
		user.getCars().remove(index);
		gui.showMessage("Car "+cid+" "+ "removed!");
	}
	
	
	/**
	 * verify all input textfields values and if all ok send register request to the server.
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
			int toc,int smodel,int atype)
	{
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
		
		if(!success) gui.showMessage(errors);
		else  
		{
			user.setFname(fname);
			user.setLname(lname);
			user.setAddress(address);
			user.setCnumber(cnumber);
			user.setEmail(email);
			user.setAtype(atype);
			user.setSmodel(smodel);
			user.setToc(toc);
			UpdateRequest request = new UpdateRequest(user);
			client.handleMessageFromGUI(request);
		}

		
	}
	


	@Override
	public void backToMenu() {
		changeFrame(gui,new UserOptionsActions(client,user,stations),this);

		
	}
	
	private boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}



}
