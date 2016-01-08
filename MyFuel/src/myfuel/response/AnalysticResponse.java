package myfuel.response;

import java.util.ArrayList;
import java.util.Date;

import myfuel.client.AnalyzeDetails;

/***
 * This class will has the response from the server to the AnalysticDBHandler 
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class AnalysticResponse extends Response 
{
	/***
	 * Customer Type with its Ratings
	 */
	private ArrayList <AnalyzeDetails> cType;
	/***
	 * Fuel Types with its Ratings
	 */
	private ArrayList <AnalyzeDetails> fType;
	/***
	 * Customer Type to Hour with rating
	 */
	private  ArrayList <AnalyzeDetails> hType;
	
	
	/***
	 * Dates for the saved analyzed details
	 */
	private ArrayList <Date> dates;
	
	/***
	 * Type of response  (0 - dates , 1 - details)
	 */
	private int type;
	/***
	 * AnalysticResponse constructor
	 * @param cType - Customer Type with its Ratings
	 * @param fType - Fuel Types with its Ratings
	 * @param hType - Customer Type to Hour with rating
	 */
	public AnalysticResponse (int type,ArrayList <AnalyzeDetails> cType , ArrayList <AnalyzeDetails> fType , ArrayList <AnalyzeDetails> hType)
	{
		this.setType(type);
		this.setcType(cType) ;
		this.setfType(fType);
		this.sethType(hType);
		
	}
	
	/***
	 * AnalysticResponse constructor
	 * @param dates - Dates for the saved analyzed details
	 */
	public AnalysticResponse(int type,ArrayList <Date> dates )
	{
		this.setType(type);
		this.dates = dates;
	}

	public ArrayList <AnalyzeDetails> getcType() {
		return cType;
	}

	public void setcType(ArrayList <AnalyzeDetails> cType) {
		this.cType = cType;
	}

	public ArrayList <AnalyzeDetails> getfType() {
		return fType;
	}

	public void setfType(ArrayList <AnalyzeDetails> fType) {
		this.fType = fType;
	}

	public ArrayList <AnalyzeDetails> gethType() {
		return hType;
	}

	public void sethType(ArrayList <AnalyzeDetails> hType) {
		this.hType = hType;
	}

	public ArrayList <Date> getDates() {
		return dates;
	}

	public void setDates(ArrayList <Date> dates) {
		this.dates = dates;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
