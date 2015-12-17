package myfuel.client;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PromotionTemplate implements Serializable{
	
	private int tid;
	private String name;
	private float discount;
	private Date startTime;
	private Date endTime;
	private int typeOfCustomer;
	private int typeOfFuel;
	/***
	 * 
	 * Promotion Template Constructor
	 * @param tid   - template ID
	 * @param name  - name of the promotion 
	 * @param discount  -  discount amount 
	 * @param startTime -   start time of the promotion
	 * @param endTime   -   end time of the promotion
	 * @param typeOfCustomer - Type of Customer {Private,Company}
	 * @param typeOfFuel - Type of Fuel (1-4)
	 */
	public PromotionTemplate(int tid,String name,float discount,Date startTime,Date endTime,int typeOfCustomer,int typeOfFuel)
	{
		this.setTid(tid);
		this.setName(name);
		this.setDiscount(discount);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setTypeOfCustomer(typeOfCustomer);
		this.setTypeOfFuel(typeOfFuel);
	}


	public void setTid(int tid2) {
		this.tid = tid2;
		
	}
	
	public int getTid() {
		return this.tid;
		
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getDiscount() {
		return discount;
	}


	public void setDiscount(float discount) {
		this.discount = discount;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public int getTypeOfCustomer() {
		return typeOfCustomer;
	}


	public void setTypeOfCustomer(int typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}


	public int getTypeOfFuel() {
		return typeOfFuel;
	}


	public void setTypeOfFuel(int typeOfFuel) {
		this.typeOfFuel = typeOfFuel;
	}

}
