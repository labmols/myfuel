package myfuel.client;


import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

@SuppressWarnings("serial")
public class Promotion extends PromotionTemplate{
	
	/***
	 * Start Date of the Promotion
	 */
	private Date startDate;
	/***
	 * End Date of the promotion
	 */
	private Date endDate;
	/***
	 * Promotion ID
	 */
	private int pid;
	/***
	 * Date Format - will be used to print the Date as dd/mm/yyyy format
	 */
	private DateFormat df;
/***
 *  Promotion Constructor
 * @param tid   - template ID
 * @param name  - name of the promotion 
 * @param discount  -  discount amount 
 * @param startTime -   start time of the promotion
 * @param endTime   -   end time of the promotion
 * @param startDate  - start date of the promotion
 * @param endDate  - end date of the promotion
 * @param typeOfCustomer - Type of Customer {Private,Company}
 * @param typeOfFuel - Type of Fuel (1-4)
 * @param pid  - Promotion ID
 */
	public Promotion(int tid,String name,float discount,Time startTime,Time endTime,Date startDate,Date endDate,int typeOfCustomer,int typeOfFuel,int pid)
	{
		super(tid,name,discount,startTime,endTime,typeOfCustomer,typeOfFuel);
		this.setPid(pid);
		this.startDate = startDate;
		this.endDate = endDate;
		 df = new SimpleDateFormat("dd/MM/yyyy ");
	}
	/***
	 * Promotion Constructor
	 * @param name - Promotion name
	 * @param startDate - start date of the promotion
	 * @param endDate   - end date of the promotion
	 * @param typeOfFuel  - name of the fuel that in the promotion
	 * @param pid  - promotion id
	 * @param discount  - discount 
	 */ 
	public Promotion(String name,Date startDate,Date endDate,String typeOfFuel, int pid,Float discount)
	{
		super(name,typeOfFuel,discount);
		this.startDate = startDate;
		this.endDate = endDate;
		this.pid = pid;
		df = new SimpleDateFormat("dd/MM/yyyy ");
		
	}
	
	
/***
 * 
 * @return startDate
 */
	public Date getStartDate()
	{
		return startDate;
	}
	/***
	 * 
	 * @return endDate
	 */
	public Date getEndDate()
	{
		return endDate;
	}

/***
 * 
 * @return pid
 */

	public int getPid() {
		return pid;
	}

/***
 * set pid 
 * @param pid
 */

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	/***
	 * Will return a string with the promotion details
	 */
	@Override
	public String toString()
	{
		return super.getName()+" "+" For: "+super.getNameOfFuel()+" "+" From: "+df.format(getStartDate())+" "+" To: "+df.format(getEndDate());
	}
	

	
}
