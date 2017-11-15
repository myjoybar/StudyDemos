package com.joybar.appcommponentlib.router3;

import android.os.Bundle;

/**
 * Created by joybar on 12/11/2017.
 */

public interface IRouterManagerService {

    IRouterManagerService buildRule(Rule3 rule);
    IRouterManagerService withData(String key,Object value);
    IRouterManagerService withBundle(Bundle bundle);
    IRouterManagerService setCallback(ICallBack callback);
    void go();

}
