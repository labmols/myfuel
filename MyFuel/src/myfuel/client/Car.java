package myfuel.client;

import java.io.Serializable;

public class Car implements Serializable{
	private int cid;
	private int fid;
	
	public Car(int cid, int fid){
			this.cid = cid;
			this.fid = fid;
		}
	
	public int getcid(){
		return cid;
	}
	
	public int getfid(){
		return fid;
	}
}
