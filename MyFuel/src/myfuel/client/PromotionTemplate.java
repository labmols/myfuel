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

/***
 * set template id
 * @param tid2
 */
	public void setTid(int tid2) {
		this.tid = tid2;
		
	}
	/***
	 * 
	 * @return tid
	 */
	public int getTid() {
		return this.tid;
		
	}
	
	/***
	 * 
	 * @return name
	 */

	public String getName() {
		return name;
	}

	/***
	 * set name of the promotion
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
/***
 * 
 * @return discount amount
 */

	public float getDiscount() {
		return discount;
	}

/***
 *  set discount amount
 * @param discount
 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

/***
 *  return start time
 * @return
 */
	public Date getStartTime() {
		return startTime;
	}
/***
 * 
 * @param startTime
 */

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
/***
 * 
 * @return endTime
 */

	public Date getEndTime() {
		return endTime;
	}
/***
 * 
 * @param endTime
 */

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

/***
 * 
 * @return type of customer
 */
	public int getTypeOfCustomer() {
		return typeOfCustomer;
	}
/***
 * set type of customer
 * @param typeOfCustomer
 */

	public void setTypeOfCustomer(int typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}
/***
 * 
 * @return type of fuel
 */

	public int getTypeOfFuel() {
		return typeOfFuel;
	}
/***
 * set type of fuel
 * @param typeOfFuel
 */

	public void setTypeOfFuel(int typeOfFuel) {
		this.typeOfFuel = typeOfFuel;
	}

}
