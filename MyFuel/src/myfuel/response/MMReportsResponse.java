package myfuel.response;

import java.util.ArrayList;

import myfuel.client.Promotion;
import myfuel.client.PromotionReport;

@SuppressWarnings("serial")
public class MMReportsResponse extends Response {
	private ArrayList<PromotionReport> pr ;
	private ArrayList<Promotion> names;
	public MMReportsResponse(ArrayList<PromotionReport> pr, ArrayList<Promotion> names )
	{
		this.pr = pr;
		this.setNames(names);
	}
	public ArrayList<PromotionReport> getPr() {
		return pr;
	}

	public void setPr(ArrayList<PromotionReport> pr) {
		this.pr = pr;
	}
	public ArrayList<Promotion> getNames() {
		return names;
	}
	public void setNames(ArrayList<Promotion> names) {
		this.names = names;
	}

	
	
}
