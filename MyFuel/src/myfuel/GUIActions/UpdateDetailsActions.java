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

public class UpdateDetailsActions extends GUIActions {
	Customer user;
	UpdateUserDetailsGUI gui;
	ArrayList <Car> origCars;
	ArrayList <Station> stations;
	
	public UpdateDetailsActions(MyFuelClient client,Customer user,ArrayList <Station> stations) {
		super(client);
		this.user=user;
		this.stations = new ArrayList<Station>(stations);
		origCars = new ArrayList<Car>(user.getCars());
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
				user.setCars(origCars);
			}
		}
	}



	public void verifyCar(String cid, int fid) {
		// TODO Auto-generated method stub
		if(!cid.equals("") && cid.length()==7){
		user.getCars().add(new Car(Integer.parseInt(cid),fid));
		gui.showMessage("Car "+cid+" "+ "added!");
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
