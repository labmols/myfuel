package myfuel.server;

import java.sql.Connection;
import java.util.HashMap;

import myfuel.client.*;
import myfuel.response.ChangePassRequest;


public class HandlerMap {
    public  HashMap<Class <?>, RequestHandler> handlerByType;
    public HandlerMap(Connection con) {
        handlerByType = new HashMap<Class <?>,RequestHandler>();
        //handlerByType.put(LoginRequest.class,new LoginDBHandler(con)); 
        handlerByType.put(ChangePassRequest.class,new changePassDBHandler(con)); 
        //handlerByType.put(registerRequest.class,new RegisterDBHandler(con)); 
     
    }
}

 