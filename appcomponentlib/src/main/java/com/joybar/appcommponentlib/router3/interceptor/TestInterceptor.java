package com.joybar.appcommponentlib.router3.interceptor;

import com.joybar.appcommponentlib.router3.RouterRequest;

/**
 * Created by joybar on 15/11/2017.
 */

public class TestInterceptor implements RouteInterceptor {
    @Override
    public boolean isIntercepted(RouterRequest routerRequest) {
       // Toast.makeText(routerRequest.getContext(), "I am a test interceptor", Toast.LENGTH_LONG).show();
        return false;
    }
}
