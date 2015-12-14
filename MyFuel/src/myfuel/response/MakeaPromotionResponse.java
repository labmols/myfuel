package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Promotion;

@SuppressWarnings("serial")
public class MakeaPromotionResponse extends Response{
	
	private ArrayList<Promotion> templates;
	
	public MakeaPromotionResponse(ArrayList<Promotion> templates)
	{
		this.setTemplates(templates);
	}

	public ArrayList<Promotion> getTemplates() {
		return templates;
	}

	public void setTemplates(ArrayList<Promotion> templates) {
		this.templates = templates;
	}

}
