package myfuel.response;

import java.io.Serializable;

import myfuel.client.Customer;

@SuppressWarnings("serial")
public class WorkerLoginResponse extends Response{
	public boolean workerExist;
	public int role;
	public WorkerLoginResponse(boolean workerExist,int role){
		this.workerExist = workerExist;
		this.role=role;
	}
	
	
	
}
