package myfuel.request;


import java.util.Date;

import myfuel.client.Promotion;


/***
 * This request object will be used for 2 things : 
 * 1. Requesting all promotion templates that listed in the DB.
 * 2. Request to save new Promotion in the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")

public class MakeaPromotionRequest extends Request{
	
	/***
	 * Type of Request
	 */
	private int type;
	/***
	 * Promotion Details
	 */
	private Promotion p;
	/***
	 * Template ID
	 */
	private int tid;
	/***
	 * Start Date of the Promotion
	 */
	private Date start;
	/***
	 * End Date of the promotion
	 */
	private Date end;
	/***
	 * The request represented by this constructor will only bring the template
	 * from the DB
	 * @param type - will be 0 for bringing the templates from the DB
	 */
	public MakeaPromotionRequest(int type)
	{
		this.setType(type);
	}
	/****
	 * The request represented by this constructor will send the new promotion
	 * to the DB
	 * @param type - will be 1 for sending the new promotion to the DB
	 * @param p - will contain the new promotion details
	 */
	public MakeaPromotionRequest(int type,Promotion p)
	{
		this.setP(p);
		this.setType(type);
	}
	
	/***
	 * when creating a new promotion from an existing template we need only the 
	 * next parameters:
	 * @param type  - type of request
	 * @param tid  - template id
	 * @param start  - start date
	 * @param end   - end date
	 */
	public MakeaPromotionRequest(int type,int tid,Date start,Date end)
	{
		this.type = type;
		this.setTid(tid);
		this.setStart(start);
		this.setEnd(end);
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public Promotion getP() {
		return p;
	}

	public void setP(Promotion p) {
		this.p = p;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

}
