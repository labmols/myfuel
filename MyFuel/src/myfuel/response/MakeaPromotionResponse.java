package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Promotion;
import myfuel.client.PromotionTemplate;

/***
 * This class contains the details about the Promotion Templates That listed in the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class MakeaPromotionResponse extends Response{
	
	/***
	 * All Promotion Templates with its details 
	 */
	private ArrayList<PromotionTemplate> templates;
	
	/***
	 * MakeaPromotionResponse Constructor
	 * @param templates - All Promotion Templates with its details 
	 */
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
