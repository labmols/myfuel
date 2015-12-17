package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Promotion;
import myfuel.client.PromotionTemplate;

@SuppressWarnings("serial")
public class MakeaPromotionResponse extends Response{
	
	private ArrayList<PromotionTemplate> templates;
	
	public MakeaPromotionResponse(ArrayList<PromotionTemplate> templates)
	{
		this.setTemplates(templates);
	}

	public ArrayList<PromotionTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(ArrayList<PromotionTemplate> templates) {
		this.templates = templates;
	}

}
