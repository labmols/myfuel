package myfuel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SWRequest implements Serializable{
	
	private int sid;
	private int[] q;
	public SWRequest(int sid,int[] q)
	{
		this.setSid(sid);
		this.setQ(q);
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int[] getQ() {
		return q;
	}

	public void setQ(int[] q) {
		this.q = q;
	}



}
