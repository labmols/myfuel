package myfuel.gui;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Promotion implements Serializable{
	
	public String name;
	public float discount;
	public Date startTime;
	public Date endTime;
	public Date startDate;
	public Date endDate;
	public int typeOfCustomer;
	
	public Promotion(String name,float discount,Date startTime,Date endTime,int typeOfCustomer)
	{
		this.name = name;
		this.discount = discount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.typeOfCustomer = typeOfCustomer;
	}
	
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
