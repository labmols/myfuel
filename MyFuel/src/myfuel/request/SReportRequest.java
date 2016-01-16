package myfuel.request;


import myfuel.client.ReportEnum;

@SuppressWarnings("serial")
/***
 * This class will be sent to the server in order to request specific report details
 *
 */
public class SReportRequest extends Request
{
	/***
	 * Station ID
	 */
	private int sid;
	
	/***
	 * Network ID
	 */
	private int nid;
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
	public  SReportRequest(int q, ReportEnum report_type, int sid,int nid)
	{
		this.setQ(q);
		this.setReport_type(report_type);
		this.setSid(sid);
		this.setNid(nid);
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

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	
	

}
