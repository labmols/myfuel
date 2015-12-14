package myfuel.request;

import java.io.Serializable;

import myfuel.client.Promotion;

@SuppressWarnings("serial")

public class MakeaPromotionRequest implements Serializable{
	
	private int type;
	private Promotion p;
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

}
