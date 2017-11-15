package com.joybar.appcommponentlib.router3.interceptor;

import com.joybar.appcommponentlib.router3.RouterRequest;

/**
 * Created by joybar on 15/11/2017.
 */

public interface RouteInterceptor {
    boolean isIntercepted(RouterRequest routerRequest);
}
