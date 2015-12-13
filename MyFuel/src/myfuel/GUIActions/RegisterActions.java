package myfuel.GUIActions;

import java.util.ArrayList;
import java.util.Observable;

import myfuel.client.Car;
import myfuel.client.Customer;
import myfuel.client.MyFuelClient;
import myfuel.client.Station;
import myfuel.gui.RegisterGUI;
import myfuel.request.registerRequest;
import myfuel.response.RegisterResponse;
import myfuel.response.Response;
import myfuel.response.booleanResponse;

public class RegisterActions extends GUIActions {
	private RegisterGUI gui;
	private ArrayList <Station> stations;
	private ArrayList <Integer> cstations;
	private ArrayList <Car> cars;
	Customer customer;
	registerRequest request;
	
	/**
	 * new register gui controller
	 * @param client - the client object for this controller
	 */
	public RegisterActions(MyFuelClient client) {
		super(client);
		stations = new ArrayList <Station>();
		cstations = new ArrayList <Integer>();
		cars = new ArrayList <Car>();
		gui = new RegisterGUI(this);
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
	public void registerRequest(int userid, String fname, String lname, char [] pass, String email, String cnumber, int toc, int 
			atype, int smodel){
		String password= new String(pass);
		customer = new Customer(userid,fname,lname,password,email,cnumber,toc,atype,smodel,cars,cstations);
		request = new registerRequest(2,customer);
		client.handleMessageFromGUI(request);
	}
	
	/**
	 * request for the stations from DB.
	 */
	public void showStations(){
		request = new registerRequest(1,null);
		client.handleMessageFromGUI(request);
	}
	
	public void returnToMain(){
		LoginActions actions = new LoginActions(client);
		changeFrame(gui, actions);
	}


	/**
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(gui.isActive()){
		if(request.type==1){
		RegisterResponse res = (RegisterResponse)arg;
		this.stations = res.stations;
		for(Station st : stations){
			gui.addStation(st.getName());
		}
		}
		else {
			booleanResponse res2 = (booleanResponse) arg;
			if(res2.success) {
				gui.showMessage("Register success! \n"
						+ "your login details is: \n"
						+ "UserID: " + customer.userid 
						+"\nPassword: " + customer.pass
						+"\nNow you need to wait for the Marketing Delegate confirmation.");
				changeFrame(gui,new LoginActions(client));

			}
			else gui.showMessage("Register failed!");
		}
	}
		

}
	/** 
	 * add new car to the ArrayList cars of the user.
	 * @param car , the car object.
	 */
	public void addCar(int cid, int fid){
		Car car = new Car(cid,fid);
		if(!cars.contains(car))
		{
		
		cars.add(car);
		gui.showMessage("Car "+cid + " is added!");
		}
	}
	
	/**
	 * add new station to the ArrayList stations of the user.
	 * @param station, the station object
	 */
	public void addStation(String sname){
		for(Station s: stations){
			if(s.getName().equals(sname)){
				if(!cstations.contains(s.getsid())) {
					cstations.add(s.getsid());
					gui.showMessage("Station "+sname + " is added!");
				}
				}
			}
		}
	


	}

