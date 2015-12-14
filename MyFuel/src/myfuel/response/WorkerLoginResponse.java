package myfuel.response;

import java.io.Serializable;

import myfuel.client.Customer;

@SuppressWarnings("serial")
public class WorkerLoginResponse extends Response{
	private boolean workerExist;
	private int role;
	public WorkerLoginResponse(boolean workerExist,int role){
		this.workerExist = workerExist;
		this.role=role;
	}
	
	public int getRole(){
		return this.role;
	}
	
	public boolean getWorkerExist(){
		return this.workerExist;
	}
	
	
	
}
