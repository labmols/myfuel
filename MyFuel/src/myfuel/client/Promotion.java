package myfuel.client;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Promotion implements Serializable{
	private int tid;
	private int pid;
	private String name;
	private float discount;
	private Date startTime;
	private Date endTime;
	private Date startDate;
	private Date endDate;
	private int typeOfCustomer;
	/***
	 * this constructor will be used for Promotion Template
	 * @param name of the promotion
	 * @param discount - discount amount decided by the Marketing Delegate
	 * @param startTime - The beginning time of the Promotion
	 * @param endTime - The ending time of the Promotion
	 * @param typeOfCustomer - The type of customer the promotion is belong to
	 */
	public Promotion(int tid,String name,float discount,Date startTime,Date endTime,int typeOfCustomer)
	{
		this.setTid(tid);
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
	public Promotion(int pid,String name,float discount,Date startTime,Date endTime,Date startDate,Date endDate,int typeOfCustomer)
	{
		this.setPid(pid);
		this.name = name;
		this.discount = discount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate  = startDate;
		this.endDate = endDate;
		this.typeOfCustomer = typeOfCustomer;
	}

	
	public String getName()
	{
		return name;
	}
	
	public float getDiscount()
	{
		return discount;
	}
	
	public Date getStartTime()
	{
		return startTime;
	}
	public Date getEndTime()
	{
		return endTime;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	
	public int getTypeOfCustomer()
	{
		return typeOfCustomer;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
