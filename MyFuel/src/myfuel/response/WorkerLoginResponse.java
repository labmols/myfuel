package myfuel.response;

import java.io.Serializable;

import myfuel.client.Customer;
import myfuel.client.RoleEnum;

@SuppressWarnings("serial")
public class WorkerLoginResponse extends Response{
	private int wid;
	private ErrorEnum error;
	private RoleEnum role;
	/** new Worker login response
	 * role 1 - Station Worker
	 * role 2- Station Manager
	 * role 3- Marketing Manager
	 * role 4- Marketing Delegate
	 * role 5- Company Manager
	 * @param workerExist
	 * @param role
	 */
	public WorkerLoginResponse(int roleid,int wid){
		this.error = ErrorEnum.NoError;
		this.wid=wid;
		switch(roleid){
		case 1: role = RoleEnum.CompanyManager;
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
	
	public WorkerLoginResponse(ErrorEnum error){
		setError(error);
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

	public ErrorEnum getError() {
		return error;
	}

	public void setError(ErrorEnum error) {
		this.error = error;
	}
	
	
	
}
