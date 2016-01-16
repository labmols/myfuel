package myfuel.request;

/***
 * This class will be used a request object for Login to MyFuel System. 
 * It will contain the type of user that is trying to login with its username and password.
 * For the case of Fast Fuel , username nor password needed so this class has a second constructor for saving only the type as Fast Fuel
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public class LoginRequest extends Request{
	
	/***
	 * Customer Login flag
	 */
	public static final int CustomerLogin = 0;
	/***
	 * Worker Login flag
	 */
	public static final int WorkerLogin = 1;
	/***
	 * Fast Fuel  flag
	 */
	public static final int FastFuel = 2;
	/**
	 * Type of User (0 - Customer, 1- Worker,2- FastFuel)
	 */
	private int type; 
	
	/**
	 * User ID
	 */
    private int userid;
    
    /**
     * User Password
     */
    private String password;
    
    /**
     * changeStatus flag , used for change status query
     */
    private int changeStatus;
    
    /**
     * Create new LoginRequest Object.
     * @param type - Type of User (Customer -0 , Worker -1 )
     * @param userid - User ID
     * @param password - User Password
     */
    public LoginRequest(int type,int userid, String password){
    	this.setType(type);
    	this.setUserid(userid);
    	this.setPassword(password);
    	setChangeStatus(0);
    }


    /***
     * Create new LoginRequest Object.
     * @param type - Type of user
     */
	public LoginRequest(int type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getChangeStatus() {
		return changeStatus;
	}


	public void setChangeStatus(int changeStatus) {
		this.changeStatus = changeStatus;
	}
	

}
