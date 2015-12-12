package myfuel.client;

import java.io.Serializable;

import myfuel.gui.Promotion;
@SuppressWarnings("serial")

/***
 * This request will contain the Promotion Template details
 * as enered from the user in the CreatePromotionTemplateGUI screen 
 * The request will be sent to the server 
 *
 */
public class PromotionTemplateRequest implements Serializable{
	public Promotion p;
	
	PromotionTemplateRequest(Promotion p )
	{
		this.p = p;
	}
}
