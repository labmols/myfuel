package myfuel.client;
import java.util.Date;

@SuppressWarnings("serial")
public class Promotion extends PromotionTemplate{
	
	private Date startDate;
	private Date endDate;
	private int pid;
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
	public Promotion(int tid,String name,float discount,Date startTime,Date endTime,Date startDate,Date endDate,int typeOfCustomer,int typeOfFuel,int pid)
	{
		super(tid,name,discount,startTime,endTime,typeOfCustomer,typeOfFuel);
		this.setPid(pid);
		this.startDate = startDate;
		this.endDate = endDate;
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
	

	
}
