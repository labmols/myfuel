package myfuel.response;

import java.io.Serializable;

import myfuel.client.Customer;

@SuppressWarnings("serial")
public class WorkerLoginResponse extends Response{
	private boolean workerExist;
	private Role role;
	/** new Worker login response
	 * role 1 - Station Worker
	 * role 2- Station Manager
	 * role 3- Marketing Manager
	 * role 4- Marketing Delegate
	 * role 5- Company Manager
	 * @param workerExist
	 * @param role
	 */
	public WorkerLoginResponse(boolean workerExist,int roleid){
		this.workerExist = workerExist;
		switch(roleid){
		case 1: role = Role.CompanyManager;
		break;
		case 2: role=Role.StationManager;
		break;
		case 3: role=Role.MarketingManager;
		break;
		case 4: role=Role.MarketingDelegate;
		break;
		case 5: role=Role.CompanyManager;
		break;
		}
	}
	
	public Role getRole(){
		return this.role;
	}
	
	public boolean getWorkerExist(){
		return this.workerExist;
	}
	
	
	
}
