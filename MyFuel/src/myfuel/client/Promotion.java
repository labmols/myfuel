package myfuel.client;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Promotion extends PromotionTemplate{
	
	private Date startDate;
	private Date endDate;

	/***
	 * this constructor will be used for Promotion Template
	 * @param name of the promotion
	 * @param discount - discount amount decided by the Marketing Delegate
	 * @param startTime - The beginning time of the Promotion
	 * @param endTime - The ending time of the Promotion
	 * @param typeOfCustomer - The type of customer the promotion is belong to
	 */
	public Promotion(int tid,String name,float discount,Date startTime,Date endTime,Date startDate,Date endDate,int typeOfCustomer,int typeOfFuel)
	{
		super(tid,name,discount,startTime,endTime,typeOfCustomer,typeOfFuel);
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	

	public Date getStartDate()
	{
		return startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	

	
}
