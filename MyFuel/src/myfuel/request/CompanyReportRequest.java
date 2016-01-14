package myfuel.request;

import java.io.Serializable;

/***
 * This object will be used as a request object from the DB
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class CompanyReportRequest implements Serializable
{
	/***
	 * The requested year to be presented
	 */
	private int year;
	
	/***
	 * Network ID
	 */
	private int nid;
	
	/***
	 * CompanyReportRequest Constructor
	 * @param year - The requested year to be presented
	 */
	public CompanyReportRequest(int year,int nid)
	{
		this.setYear(year);
		this.setNid(nid);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}
	
	
}
