package com.joybar.appcommponentlib.router1.routerlib;

import android.app.Activity;

import com.joybar.appcommponentlib.router1.exception.BroadcastNotRouteException;

/**
 * Created by joybar on 04/11/2017.
 */

public class RouterBroadcast extends RouterBase<Activity> {

    public static final String BROADCAST_SCHEME = "broadcast://";

    @Override
    public void throwException(String scheme) {
        throw new BroadcastNotRouteException(scheme);
    }
}
