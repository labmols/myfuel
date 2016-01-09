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
	 * CompanyReportRequest Constructor
	 * @param year - The requested year to be presented
	 */
	public CompanyReportRequest(int year)
	{
		this.setYear(year);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
