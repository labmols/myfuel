package myfuel.Response;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.Entity.Customer;
import myfuel.Entity.FuelQty;
import myfuel.Entity.MessageForManager;
import myfuel.Tools.RoleEnum;

/***
 * This class contains all the details of the Worker that Logged in to the System
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class WorkerLoginResponse extends Response{
	/**
	 * Worker ID
	 */
	private int wid;
	
	/**
	 * Worker Role
	 */
	private RoleEnum role;
	
	/**
	 * Worker station ( when needed)
	 */
	private int sid;
	
	/***
	 * Network ID
	 */
	private int nid;
	
	/***
	 * Messages for the user
	 */
	private ArrayList<MessageForManager> msg;
	/****
	 * Name of the station that this worker belongs to
	 */
	private String StationName;
	
	
	/***
	 * Network name
	 */
	private String netName;
	
	/** new Worker login response constructor
	 * role 1 - Station Worker
	 * role 2- Station Manager
	 * role 3- Marketing Manager
	 * role 4- Marketing Delegate
	 * role 5- Company Manager
	 * role 6 - Home Fuel Manager
	 * @param wid - worker id
	 * @param nid - network id
	 * @param sid - station id
	 * @param msg - messages for the worker
	 * @param StationName - station name
	 * @param netName - network name
	 */
	
	public WorkerLoginResponse(int roleid,int wid,int nid,int sid,ArrayList<MessageForManager> msg,String StationName,String netName){
		this.wid=wid;
		this.sid = sid;
		switch(roleid){
		case 1: role = RoleEnum.StationWorker;
		break;
		case 8: role=RoleEnum.StationManager;
		break;
		case 3: role=RoleEnum.MarketingManager;
		break;
		case 4: role=RoleEnum.MarketingDelegate;
		break;
		case 5: role=RoleEnum.CompanyManager;
		break;
		case 6: role = RoleEnum.HomeManager;
		}
		
		this.setMsg(msg);
		this.setNid(nid);
		
		this.setStationName(StationName);
		this.setNetName(netName);
	}
	
	
	public RoleEnum getRole(){
		return this.role;
	}
	
	
	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	public ArrayList<MessageForManager> getMsg() {
		return msg;
	}


	public void setMsg(ArrayList<MessageForManager> msg) {
		this.msg = msg;
	}


	public int getNid() {
		return nid;
	}


	public void setNid(int nid) {
		this.nid = nid;
	}


	public String getStationName() {
		return StationName;
	}


	public void setStationName(String stationName) {
		StationName = stationName;
	}


	public String getNetName() {
		return netName;
	}


	public void setNetName(String netName) {
		this.netName = netName;
	}





	
	
	
}
