package myfuel.client;

import java.io.Serializable;

import myfuel.gui.Promotion;
@SuppressWarnings("serial")
public class PromotionTemplateRequest implements Serializable{
	public Promotion p;
	
	PromotionTemplateRequest(Promotion p )
	{
		this.p = p;
	}
}
