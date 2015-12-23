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
import myfuel.gui.UpdateCustomerDetailsGUI;
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
	 * Contains all the user login details(including his personal details ,etc)
	 */
	private UserLoginResponse res;
	/**
	 * this object contains the user interface
	 */
	private UpdateCustomerDetailsGUI gui;
	/**
	 * this object saves the list of customer cars before the changes.
	 */
	private ArrayList <Car> origCars;
	
	/**
	 * list of the customer original stations , before update.
	 */
	private ArrayList <Integer> origStations;
	
	/**
	 * create new UpdateDetails controller , which working with the UpdateDetailsGUI.
	 * @param client - the client object for the communication.
	 * @param res - the UserLoginResponse object which contains all the required details.
	 */
	public UpdateDetailsActions(MyFuelClient client,UserLoginResponse res) {
		super(client);
		this.res = res;
		origCars = new ArrayList<Car>(res.getUser().getCars());
		origStations = new ArrayList<Integer> (res.getUser().getStations());
		gui = new UpdateCustomerDetailsGUI(this);
		gui.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	

	public Customer getUserDetails(){
		return res.getUser();
	}
	
	public ArrayList<Station> getStations(){
		return res.getStations();
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Customer user = res.getUser();
		if(arg instanceof booleanResponse)
		{
			booleanResponse res= (booleanResponse) arg;
			gui.showMessage(res.getMsg());
			if(!res.getSuccess()) {
				clearLists();
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
	 * 
	 * @param sname
	 */
	public void addStation(String sname, int access){
		ArrayList <Integer> userStations = res.getUser().getStations();
		if(access == 1 || access==0 && userStations.isEmpty())
		{
		for(Station s: res.getStations()){
			if(s.getName().equals(sname)){
				if(!userStations.contains(s.getsid())) {
					userStations.add(s.getsid());
					gui.showMessage("Station "+sname + " is added!");
				}
				else gui.showMessage("You already have this station!");
				}
			}
		}
		else gui.showMessage("You Can't add more then one stations in this access!");
	
		gui.updateCB(userStations,res.getStations());
		}
		
	/**
	 * 
	 * @param oldS
	 * @param newS
	 */
	public void changeStation(String oldS, String newS)
	{
		ArrayList <Integer> userStations = res.getUser().getStations();
		int index = -1;
		for(Station s: res.getStations())
		{
			if(s.getName().equals(oldS))
			{
					index =userStations.indexOf(s.getsid());
			}
		}
		for(Station s: res.getStations())
		{
			if(s.getName().equals(newS))
				{
					if(index!=-1 && !userStations.contains(s.getsid()))
					{
						userStations.set(index, s.getsid());
						gui.showMessage("Station "+oldS + " changed to " +newS);
					}
					else gui.showMessage("You already have this station!");
					
				}
			
		}
		
		gui.updateCB(userStations,res.getStations());
			
		}

		
	
	/**
	 * remove selected car from the user car list.
	 * @param cid - the car id number.
	 * @param index - index of this car in the list.
	 */
	public void removeCar(int cid, int index)
	{
		res.getUser().getCars().remove(index);
		gui.showMessage("Car "+cid+" "+ "removed!");
	}
	
	public void resetAccess()
	{
			ArrayList <Integer> userStations = res.getUser().getStations();
			userStations.clear();
			gui.updateCB(userStations, res.getStations());
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
			int toc,int smodel,int atype)
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
	
	private void clearLists()
	{
		Customer user = res.getUser();
		user.getCars().clear();
		user.setCars(new ArrayList<Car>(origCars));
		user.getStations().clear();
		user.setStations(new ArrayList<Integer>(origStations));
	}
	

	
	@Override
	public void backToMenu() {
		clearLists();
		changeFrame(gui,new CustomerOptionsActions(client,res),this);
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



	


}
