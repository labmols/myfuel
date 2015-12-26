package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.SendMailTLS;
import myfuel.client.Station;
import myfuel.gui.RegisterGUI;
import myfuel.request.RequestEnum;
import myfuel.request.registerRequest;
import myfuel.response.RegisterResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

/**
 * Register GUI Controller.
 * @author Maor
 *
 */
public class RegisterActions extends GUIActions {
	/**
	 * The Register GUI object.
	 */
	private RegisterGUI gui;
	/**
	 * List of all stations in the company, received from Database.
	 */
	private ArrayList <Station> stations;
	/**
	 * List of all the new customer stations chosen.
	 */
	private ArrayList <Integer> cstations;
	/**
	 * List of all the new customer cars.
	 */
	private ArrayList <Car> cars;
	/**
	 * Will contain all customer details.
	 */
	private Customer customer;
	/**
	 * the registerRequest object that will be send to the server.
	 */
	private registerRequest request;
	
	/**
	 * Create new Register GUI Controller.
	 * @param client - the client object for this controller
	 */
	public RegisterActions(MyFuelClient client) {
		super(client);
		stations = new ArrayList <Station>();
		cstations = new ArrayList <Integer>();
		cars = new ArrayList <Car>();
		gui = new RegisterGUI(this);
		showStations();
		gui.setVisible(true);
	}
	
	/**
	 * make new Register Request and send it to the server.
	 * @param userid - the id number of the user
	 * @param fname - first name of user
	 * @param lname -last name of user 
	 * @param pass - password 
	 * @param email - email
	 * @param cnumber - credit card number
	 * @param toc - type of customer(private or company)
	 * @param atype - access type (one station or more)
	 * @param smodel - sale model (monthly,etc)
	 */
	public void registerRequest(int userid, String fname, String lname, char [] pass, String email,String address, String cnumber, int toc, int 
			atype, int smodel){
		String password= new String(pass);
		customer = new Customer(userid,fname,lname,password,email,address,cnumber,toc,atype,smodel,cars,cstations);
		request = new registerRequest(RequestEnum.Insert,customer);
		client.handleMessageFromGUI(request);
	}
	
	/**
	 * Request for all the stations from DB.
	 */
	public void showStations(){
		request = new registerRequest(RequestEnum.Select,null);
		client.handleMessageFromGUI(request);
	}
	
	/**
	 * notified by the Client when a new response received from the server and handle the request.
	 * Show message to the customer if the register success or not according to this response.
	 */
	@Override
	public void update(Observable o, Object arg) {
	
		if(arg instanceof RegisterResponse || arg instanceof booleanResponse){
		if(request.getType()== RequestEnum.Select){
		if(arg instanceof RegisterResponse){
		RegisterResponse res = (RegisterResponse)arg;
		addStations(res);
		
		}
		else {
			booleanResponse res2 = (booleanResponse) arg;
			gui.showErrorMessage(res2.getMsg());
		}
		}
		else if(request.getType()== RequestEnum.Insert){
			booleanResponse res2 = (booleanResponse) arg;
			checkRegister(res2);
			}
		}
}
	/**
	 * add all current available stations to the gui comboBox
	 * @param res - the response from the server(contains the stations values).
	 */
	public void addStations(RegisterResponse res){
		this.stations = res.getStations();
		for(Station st : stations){
			gui.addStation(st.getName());
		}
	}
	
	/**
	 * check if register complete or not.
	 * @param res - this is boolean response (true- complete, false- there is an sql error).
	 */
	public void checkRegister(booleanResponse res){
		if(res.getSuccess()) {
			
			gui.showOKMessage(res.getMsg());
			changeFrame(gui,new LoginActions(client),this);

		}
		else gui.showErrorMessage(res.getMsg());
	}
	
	/** 
	 * add new car to the ArrayList cars of the user.
	 * @param car , the car object.
	 */
	public void addCar(String cid, int fid){
		if(cid.length()==7 && isAllDigits(cid) ){
			int carid = Integer.parseInt(cid);
			Car car = new Car(carid,fid);
			if(!cars.contains(car))
			{
			cars.add(car);
			gui.showOKMessage("Car "+cid + " is added!");
			}
			else gui.showErrorMessage("You already have this car!");
		}
		else gui.showErrorMessage("Car number value is illegal!");
		
	}
	
	/**
	 * add new station to the ArrayList stations of the user.
	 * @param station, the station object
	 */
	public void addStation(String sname, int access){
		if(access != 0 || cstations.isEmpty()){
		for(Station s: stations){
			if(s.getName().equals(sname)){
				if(!cstations.contains(s.getsid())) {
					cstations.add(s.getsid());
					gui.showOKMessage("Station "+sname + " is added!");
				}
				else gui.showErrorMessage("You already have this station!");
				}
			}
		}
		else gui.showErrorMessage("You choose one station access type!");
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
	
	public void verifyDetails(String userid, String fname, String lname, char [] pass,char [] repass, String email,String address, String cnumber,int toc, int 
			atype, int smodel)
	{
		String errors="";
		errors += "Input Errors \n\n";
		Pattern pattern = Pattern.compile("^.+@.+\\..+$");
		Matcher matcher = pattern.matcher(email);
		boolean success = true;
		if(userid.length()>9 || userid.equals("")){ 
			success = false;
			errors+="ID length > 9 or Empty.\n";
		}
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
		
		if(!(new String(pass)).equals(new String (repass))){
			success = false;
			errors+="Passwords are not Equal.\n";
		}
		
		if(pass.length<4){ 
			success = false;
			errors+="Password length must be > 4.\n";
		}
		
		if(!matcher.matches()){
			success= false;
			errors+= "illegal Email value. \n";
		}
		if(cars.isEmpty()){
			success= false;
			errors+= "Please add your cars. \n";
		}
		
		if(cstations.isEmpty()){
			success= false;
			errors+= "Please add your Stations. \n";
		}
		if(!success) gui.showErrorMessage(errors);
		else registerRequest(Integer.parseInt(userid), fname, lname, pass, email,address,cnumber,toc, atype, smodel);
	}
	
	/** 
	 * check if the string contains only letters
	 * @param name - the string value
	 * @return
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
	
	/**
	 * check if the string contains only digits
	 * @param name - the string value
	 * @return boolean
	 */
	private boolean isAllDigits(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    return true;
	}

	/**
	 * remove all cstations array contents, because the user replace the access.
	 */
	public void resetStations() {
		cstations.clear();
		
	}

	/**
	 * Back to the Login screen.
	 */
	@Override
	public void backToMenu() {
		changeFrame(gui,new LoginActions(client),this);
		
	}
	
	/**
	 * reset all new customer lists.
	 */
	public void resetAll() {
		// TODO Auto-generated method stub
		cstations.clear();
		cars.clear();
		
	}
		
	}
	


	

