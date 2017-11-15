package com.joybar.appcommponentlib.router3;

import android.os.Bundle;

import com.joybar.appcommponentlib.router3.interceptor.RouteInterceptor;

/**
 * Created by joybar on 12/11/2017.
 */

public interface IRouterManagerService {

    IRouterManagerService buildRule(Rule3 rule);

    IRouterManagerService withData(String key, Object value);

    IRouterManagerService withBundle(Bundle bundle);

    IRouterManagerService withInterceptorCallback(InterceptorCallback interceptorCallback);

    IRouterManagerService addInterceptor(RouteInterceptor routeInterceptor);

    boolean isIntercepted();

    void go();

    void goForResult(int requestCode);

}
