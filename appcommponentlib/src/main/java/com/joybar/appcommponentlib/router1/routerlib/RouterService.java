package com.joybar.appcommponentlib.router1.routerlib;

import android.app.Activity;

import com.joybar.appcommponentlib.router1.exception.ServiceNotRouteException;

/**
 * Created by joybar on 04/11/2017.
 */

public class RouterService extends RouterBase<Activity> {

    public static final String SERVICE_SCHEME = "service ://";

    @Override
    public void throwException(String scheme) {
        throw new ServiceNotRouteException(scheme);
    }
}
