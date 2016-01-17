package myfuel.Request;


import java.util.Date;

/***
 * This class will be sent to the server as Request Object and will be used in the correct DBHandler
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class AnalysticRequest extends Request
{
	/***
	 * Type of Request
	 */
	private RequestEnum type;
	
	/***
	 * Date to be analyzed
	 */
	private Date date;
	
	/***
	 * AnalysticRequest Constructor
	 * @param type - Type of Request
	 * @param date 
	 */
	public AnalysticRequest(RequestEnum type, Date date)
	{
		this.date = date;
		this.setType(type);
	}

	public RequestEnum getType() {
		return type;
	}

	public void setType(RequestEnum type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
