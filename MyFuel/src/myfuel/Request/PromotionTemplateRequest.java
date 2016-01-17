package myfuel.Request;

import java.io.Serializable;

import myfuel.Entity.Promotion;
import myfuel.Entity.PromotionTemplate;

/***
 * This request will contain the Promotion Template details
 * as entered from the user in the CreatePromotionTemplateGUI screen 
 * The request will be sent to the server 
 *
 */
@SuppressWarnings("serial")

public class PromotionTemplateRequest implements Serializable{
	private PromotionTemplate p;
	
	public PromotionTemplateRequest(PromotionTemplate p2 )
	{
		this.setP(p2);
	}

	public PromotionTemplate getP() {
		return p;
	}

	public void setP(PromotionTemplate p2) {
		this.p = p2;
	}
}
