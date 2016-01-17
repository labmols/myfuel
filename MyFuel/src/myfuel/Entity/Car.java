package myfuel.Entity;

import java.io.Serializable;

/***
 * Will has the details of a car
 *
 */
@SuppressWarnings("serial")
public class Car implements Serializable{
	/***
	 * Car ID Number
	 */
	private int cid;
	/***
	 * Fuel ID Number
	 */
	private int fid;
	
	/***
	 * Constructor for Car
	 * @param cid - Car ID Number
	 * @param fid - Fuel ID Number
	 */
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
