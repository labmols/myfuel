package myfuel.request;

import java.io.Serializable;

/***
 * This abstract class will be used as a request object from the that will be sent client to the server.
 * Each time we want to send a request from the client to the server we will create new class that will extend this one.
 * @author karmo
 *
 */
@SuppressWarnings("serial")
public abstract class Request implements Serializable 
{
	
}
