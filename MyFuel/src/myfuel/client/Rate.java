package myfuel.client;

import java.io.Serializable;

/**
 * This class contain Rate details(according to modelid).
 */
@SuppressWarnings("serial")
public class Rate implements Serializable{
	
	/**
	 * final id value of Occasional sale model.
	 */
	public final static int Occasional = 1;
	/**
	 * final id value of MonthlyOne sale model.
	 */
	public final static int MonthlyOne = 2;
	/**
	 * final id value of MonthlyFew sale model.
	 */
	public final static int MonthlyFew = 3;
	/**
	 * final id value of FullyMonthly sale model.
	 */
	public final static int FullyMonthly= 4;
	
	/**
	 * Sale Model ID. 
	 */
	private int modelID;
	/**
	 * Sale Model description.
	 */
	private String modelDesc;
	/**
	 * Current discount for this model.
	 */
	private float discount;
	
	/**
	 * Create new Rate with all the details.
	 * @param modelid - Sale model id number.
	 * @param modelDesc - Sale model description.
	 * @param discount- Sale model discount.
	 */
	public Rate(int modelid, String modelDesc, float discount)
	{
		this.setModelid(modelid);
		this.setModelDesc(modelDesc);
		this.setDiscount(discount);
	}
	
	/**
	 * Create new Rate without description.
	 * @param modelid - Sale model id number.
	 * @param discount- Sale model discount.
	 */
	public Rate(int modelid,  float discount)
	{
		this.setModelid(modelid);
		this.setDiscount(discount);
		this.setModelDesc(null);
	}
	
	
	
	public int getModelid() {
		return modelID;
	}
	public void setModelid(int modelid) {
		this.modelID = modelid;
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
