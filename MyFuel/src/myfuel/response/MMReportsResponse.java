package myfuel.response;

import java.util.ArrayList;

import myfuel.client.PromotionReport;

@SuppressWarnings("serial")
public class MMReportsResponse extends Response {
	private ArrayList<PromotionReport> pr ;

	public MMReportsResponse(ArrayList<PromotionReport> pr )
	{
		this.pr = pr;
	}
	public ArrayList<PromotionReport> getPr() {
		return pr;
	}

	public void setPr(ArrayList<PromotionReport> pr) {
		this.pr = pr;
	}
	
	
}
