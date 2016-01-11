package myfuel.client;

import java.io.Serializable;

public class Network implements Serializable {
	
	private int nid;
	
	private String name;
	
	public Network(int nid, String name)
	{
		this.setNid(nid);
		this.setName(name);
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
