package myfuel.server;

import myfuel.response.Response;


public interface RequestHandler{
	public Response handleRequest(Object msg);
	

}
