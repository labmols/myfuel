package myfuel.request;

import java.io.Serializable;

import myfuel.client.ReportEnum;

@SuppressWarnings("serial")
/***
 * This class will be sent to the server in order to request specific report details
 *
 */
public class SReportRequest implements Serializable 
{
	/***
	 * Station ID
	 */
	private int sid;
	/***
	 * The selected Quarter
	 */
	private int q;
	/***
	 * The selected report type
	 */
	private ReportEnum report_type;
	
	/***
	 * SReportRequest Constructor
	 * @param sid 
	 * @param q2  - The selected Quarter
	 * @param r - The selected report type
	 */
	public  SReportRequest(int q, ReportEnum report_type, int sid)
	{
		this.setQ(q);
		this.setReport_type(report_type);
		this.setSid(sid);
	}

	public ReportEnum getReport_type() {
		return report_type;
	}

	public void setReport_type(ReportEnum report_type) {
		this.report_type = report_type;
	}

	public int getQ() {
		return q;
	}

	public void setQ(int q) {
		this.q = q;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	
	

}
