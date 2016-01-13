package myfuel.response;

import java.io.Serializable;
import java.util.ArrayList;

import myfuel.client.Customer;
import myfuel.client.FuelQty;
import myfuel.client.MessageForManager;
import myfuel.client.RoleEnum;

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
	
	/** new Worker login response
	 * role 1 - Station Worker
	 * role 2- Station Manager
	 * role 3- Marketing Manager
	 * role 4- Marketing Delegate
	 * role 5- Company Manager
	 * role 6 - Home Fuel Manager
	 * @param workerExist
	 * @param role
	 * @param msg
	 */
	public WorkerLoginResponse(int roleid,int wid,int nid,int sid,ArrayList<MessageForManager> msg){
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





	
	
	
}
