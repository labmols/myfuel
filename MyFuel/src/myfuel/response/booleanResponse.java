package myfuel.response;

/**
 * This Response indicate if the request completed successful on the server or not.
 * Include Message to the user that gives more information about what was wrong or success message.
 */
@SuppressWarnings("serial")
public class booleanResponse extends Response {
	/**
	 * Indicate if the request completed successfully or not.
	 */
	private boolean success;
	/**
	 * Error or Success message.
	 */
	private String msg;
	
	/**
	 * Create new boolean response.
	 * @param success - Request Success/Not Success.
	 * @param msg - Error/Success message.
	 */
	public booleanResponse(boolean success, String msg){
		this.success = success;
		this.setMsg(msg);
	}
	
	public boolean getSuccess()
	{
		return success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
