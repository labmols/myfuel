package myfuel.client;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Promotion implements Serializable{
	
	public String name;
	public float discount;
	public Date startTime;
	public Date endTime;
	public Date startDate;
	public Date endDate;
	public int typeOfCustomer;
	/***
	 * this constructor will be used for Promotion Template
	 * @param name of the promotion
	 * @param discount - discount amount decided by the Marketing Delegate
	 * @param startTime - The beginning time of the Promotion
	 * @param endTime- The ending time of the Promotion
	 * @param typeOfCustomer - The type of customer the promotion is belong to
	 */
	public Promotion(String name,float discount,Date startTime,Date endTime,int typeOfCustomer)
	{
		this.name = name;
		this.discount = discount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.typeOfCustomer = typeOfCustomer;
	}
	/***
	 * this constructor will be used for Promotion 
	 * @param name   - same as above 
	 * @param discount - same as above 
	 * @param startTime - same as above 
	 * @param endTime - same as above 
	 * @param startDate - The start date of the promotion as decided by the Marketing Manager
	 * @param endDate - The end date of the promotion as decided by the Marketing Manager
	 * @param typeOfCustomer - same as above
	 */
	public Promotion(String name,float discount,Date startTime,Date endTime,Date startDate,Date endDate,int typeOfCustomer)
	{
		this.name = name;
		this.discount = discount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate  = startDate;
		this.endDate = endDate;
		this.typeOfCustomer = typeOfCustomer;
	}

}
