package myfuel.client;

import java.io.Serializable;

/**
 * This class contain Rate details(according to modelid).
 */
@SuppressWarnings("serial")
public class Rate implements Serializable{
	
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
	private int discount;
	
	public Rate(int modelid, String modelDesc, int discount)
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
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	

}
