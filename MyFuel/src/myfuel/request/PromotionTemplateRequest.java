package myfuel.request;

import java.io.Serializable;

import myfuel.client.Promotion;
@SuppressWarnings("serial")

/***
 * This request will contain the Promotion Template details
 * as enered from the user in the CreatePromotionTemplateGUI screen 
 * The request will be sent to the server 
 *
 */
public class PromotionTemplateRequest implements Serializable{
	private Promotion p;
	
	public PromotionTemplateRequest(Promotion p )
	{
		this.setP(p);
	}

	public Promotion getP() {
		return p;
	}

	public void setP(Promotion p) {
		this.p = p;
	}
}
