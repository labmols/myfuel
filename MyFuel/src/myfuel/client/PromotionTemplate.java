package myfuel.client;

import java.io.Serializable;
import java.sql.Time;


/***
 * This class will has the details of the Promotion template 
 *
 */
@SuppressWarnings("serial")
public class PromotionTemplate implements Serializable{
	
	/***
	 * Template ID
	 */
	private int tid;
	/***
	 * Template Name
	 */
	private String name;
	/***
	 * Template Discount
	 */
	private float discount;
	/***
	 * Template Start Time
	 */
	private Time startTime;
	/***
	 * Template End Time
	 */
	private Time endTime;
	/***
	 * Type of Customer for this Promotion Template
	 */
	private int typeOfCustomer;
	/***
	 * Type of Fuel for this Promotion Template
	 */
	private int typeOfFuel;
	/***
	 * Name of Fuel for this Promotion Template
	 */
	private String nameOfFuel;
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
	public PromotionTemplate(int tid,String name,float discount,Time startTime,Time endTime,int typeOfCustomer,int typeOfFuel)
	{
		this.setTid(tid);
		this.setName(name);
		this.setDiscount(discount);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setTypeOfCustomer(typeOfCustomer);
		this.setTypeOfFuel(typeOfFuel);
	}
	/***
	 * Promotion Template Constructor
	 * @param name - name of the promotion
 	 * @param nameOfFuel - name of the fuel
	 * @param discount - discount amount
	 */
	public PromotionTemplate(String name, String nameOfFuel, Float discount)
	{
		this.name = name;
		this.discount = discount;
		this.setNameOfFuel(nameOfFuel);
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


	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}


	public void setEndTime(Time endTime) {
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

public String getNameOfFuel() {
	return nameOfFuel;
}

public void setNameOfFuel(String nameOfFuel) {
	this.nameOfFuel = nameOfFuel;
}

}
