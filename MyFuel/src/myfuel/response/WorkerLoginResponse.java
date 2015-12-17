package myfuel.response;

import java.io.Serializable;

import myfuel.client.Customer;
import myfuel.client.RoleEnum;

@SuppressWarnings("serial")
public class WorkerLoginResponse extends Response{
	private int wid;
	private RoleEnum role;
	private int sid;
	/** new Worker login response
	 * role 1 - Station Worker
	 * role 2- Station Manager
	 * role 3- Marketing Manager
	 * role 4- Marketing Delegate
	 * role 5- Company Manager
	 * @param workerExist
	 * @param role
	 */
	public WorkerLoginResponse(int roleid,int wid,int sid){
		this.wid=wid;
		this.sid = sid;
		switch(roleid){
		case 1: role = RoleEnum.StationWorker;
		break;
		case 2: role=RoleEnum.StationManager;
		break;
		case 3: role=RoleEnum.MarketingManager;
		break;
		case 4: role=RoleEnum.MarketingDelegate;
		break;
		case 5: role=RoleEnum.CompanyManager;
		break;
		}
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

	
	
	
}
