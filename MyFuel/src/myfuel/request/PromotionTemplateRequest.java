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
	public Promotion p;
	
	public PromotionTemplateRequest(Promotion p )
	{
		this.p = p;
	}
}
