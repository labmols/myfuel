package myfuel.Entity;

import java.io.Serializable;

/***
 * This class will contain network's basic details
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class Network implements Serializable {
	
	/***
	 * Network ID
	 */
	private int nid;
	
	/***
	 * Network Name
	 */
	private String name;
	
	/***
	 * Network Constructor
	 * @param nid - network ID
	 * @param name - Network Name
	 */
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
