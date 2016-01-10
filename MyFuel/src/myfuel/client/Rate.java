package myfuel.client;

import java.io.Serializable;

/**
 * This class contain Rate details(according to modelid).
 */
@SuppressWarnings("serial")
public class Rate implements Serializable{
	
	final static int Occasional = 1;
	final static int MonthlyOne = 2;
	final static int MonthlyFew = 3;
	final static int FullyMonthly= 4;
	/**
	 * Sale Model ID. 
	 */
	private int modelid;
	/**
	 * Sale Model description.
	 */
	private String modelDesc;
	/**
	 * Current discount for this model.
	 */
	private float discount;
	
	public Rate(int modelid, String modelDesc, float discount)
	{
		this.setModelid(modelid);
		this.setModelDesc(modelDesc);
		this.setDiscount(discount);
	}
	
	public int getModelid() {
		return modelid;
	}
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
	public String getModelDesc() {
		return modelDesc;
	}
	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	

}
