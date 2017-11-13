package com.joybar.appcommponentlib.router3;

/**
 * Created by joybar on 12/11/2017.
 */

public interface IRouterManagerService {

    IRouterManagerService withRule(Rule rule);
    IRouterManagerService withContext(Object obj);
    IRouterManagerService withData(String key,Object value);
    void go();

}
