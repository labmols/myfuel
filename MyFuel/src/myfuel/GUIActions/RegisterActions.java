package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myfuel.Client.MyFuelClient;
import myfuel.Entity.Car;
import myfuel.Entity.Customer;
import myfuel.Entity.Network;
import myfuel.Entity.Station;
import myfuel.GUI.RegisterGUI;
import myfuel.Request.LoginRequest;
import myfuel.Request.RegisterRequest;
import myfuel.Request.RequestEnum;
import myfuel.Response.RegisterResponse;
import myfuel.Response.Response;
import myfuel.Response.booleanResponse;
import myfuel.Tools.SendMailTLS;

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
	 * List of all networks in the company, received from Database.
	 */
	private ArrayList <Network> networks;
	/**
	 * List of all the new customer stations chosen.
	 */
	private ArrayList <Integer> cnetworks;
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
	private RegisterRequest request;
	
	
	/**
	 * Create new Register GUI Controller.
	 * @param client - the client object for this controller
	 */
	public RegisterActions(MyFuelClient client,LoginRequest lr) {
		super(client,lr);
		networks = new ArrayList <Network>();
		cnetworks = new ArrayList <Integer>();
		cars = new ArrayList <Car>();
		gui = new RegisterGUI(this);
		gui.setVisible(true);
		showNetworks();
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
		customer = new Customer(userid,fname,lname,password,email,address,cnumber,toc,atype,smodel,cars,cnetworks);
		request = new RegisterRequest(RequestEnum.Insert,customer);
		gui.createWaitDialog("Sending register request...");
		client.handleMessageFromGUI(request);
	}
	
	/**
	 * Request for all the networks from DB.
	 */
	public void showNetworks(){
		gui.createWaitDialog("Getting Details...");
		request = new RegisterRequest(RequestEnum.Select,null);
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
		addNetworks(res);
		gui.setWaitProgress();
		
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
	 * add all current available networks to the gui comboBox
	 * @param res - the response from the server(contains the stations values).
	 */
	public void addNetworks(RegisterResponse res){
		this.networks = res.getNetworks();
		for(Network n : networks){
			gui.addNetwork(n.getName());
		}
	}
	
	/**
	 * check if register complete or not.
	 * @param res - this is boolean response (true- complete, false- there is an sql error).
	 */
	public void checkRegister(booleanResponse res){
		if(res.getSuccess()) {
			gui.setWaitProgress();
			gui.showOKMessage(res.getMsg());
			new LoginActions(client);
			changeFrame(gui,this);
			

		}
		else 
		{
			gui.setWaitProgress();
			gui.showErrorMessage(res.getMsg());
			this.resetCars();
		}
	}
	
	/** 
	 * add new car to the ArrayList cars of the user.
	 * @param car , the car object.
	 */
	public void addCar(String cid, int fid, int saleModel){
		
		if(cars.isEmpty() || (saleModel != 2 && saleModel !=4))
		{
		if(cid.length()==7 && isAllDigits(cid) ){
			int carid = Integer.parseInt(cid);
			Car car = new Car(carid,fid);
			if(!checkCar(car.getcid()))
			{
			cars.add(car);
			gui.showOKMessage("Car "+cid + " is added!");
			}
			else gui.showErrorMessage("You already have this car!");
		}
		else gui.showErrorMessage("Car number value is illegal!");
		}
		else gui.showErrorMessage("You choose one car sale Model!");
		
	}
	
	public boolean checkCar(int cid)
	{
		for(Car c: cars)
			if(c.getcid() == cid) return true;
		return false;
	}
	
	/**
	 * Add new network to the user list of networks.
	 * @param netName - network name.
	 * @param access - customer access type.
	 */
	public void addNetwork(String netName, int access){
		if(access != 0 || cnetworks.isEmpty()){
		for(Network n: networks){
			if(n.getName().equals(netName)){
				if(!cnetworks.contains(n.getNid())) {
					cnetworks.add(n.getNid());
					gui.showOKMessage("Network "+netName + " is added!");
				}
				else gui.showErrorMessage("You already have this network!");
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
		if(userid.length()<9 || userid.equals("") || !this.isAllDigits(userid)){ 
			success = false;
			errors+="ID length < 9 or illegal.\n";
		}
		if(address.equals("")){
			success=false;
			errors+="address field empty.\n";
		}
		
		if(cnumber.equals("") || cnumber.length() < 8)
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
		
		if(cnetworks.isEmpty()){
			success= false;
			errors+= "Please add your Networks. \n";
		}
		
		if(toc == 1 && (smodel == 4 || smodel == 2))
		{
			success= false;
			errors+= "Company customer can't have this sale model!. \n";
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
	 * remove all customer stations list contents, because the user replace the access.
	 */
	public void resetNetworks() {
		gui.showOKMessage("Now add your networks!");
		cnetworks.clear();
	}
	
	/**
	 * remove all cars list contents, because the user replace the sale model.
	 */
	public void resetCars() {
		gui.showOKMessage("Now add your cars!");
		cars.clear();

	}

	/**
	 * Back to the Login screen.
	 */
	@Override
	public void backToMenu() {
	
		changeFrame(gui,this);
		new LoginActions(client);
		
		
	}
	
	/**
	 * reset all new customer lists.
	 */
	public void resetAll() {
		// TODO Auto-generated method stub
		cnetworks.clear();
		cars.clear();
		
	}

	@Override
	public void LogOut() {
		// TODO Auto-generated method stub
		
	}
		
	}
	


	

