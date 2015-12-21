package myfuel.client;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class StationInventory implements Serializable {
	private Station s;
	private ArrayList <Float> fQty;
	private ArrayList <Float> mQty;
	
	/**
	 * 
	 * @param s
	 * @param fQty
	 * @param mQty
	 */
		public StationInventory(Station s, ArrayList <Float> fQty, ArrayList <Float> mQty)
		{
			this.fQty = new ArrayList<Float>(fQty);
			this.mQty = new ArrayList<Float>(mQty);
			this.s = s;
		}

	public ArrayList <Float> getfQty() {
		return fQty;
	}

	public void setfQty(ArrayList <Float> fQty) {
		this.fQty = fQty;
	}

	public ArrayList <Float> getmQty() {
		return mQty;
	}

	public void setmQty(ArrayList <Float> mQty) {
		this.mQty = mQty;
	}

	public Station getS() {
		return s;
	}

	public void setS(Station s) {
		this.s = s;
	}

	
}
