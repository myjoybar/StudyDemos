package com.joybar.appcommponentlib.router1.routerlib;

import android.app.Service;

import com.joybar.appcommponentlib.router1.exception.NoRouterException;

/**
 * Created by joybar on 04/11/2017.
 */

public class RouterBroadcast<T extends Service> extends RouterBase {

    public static final String BROADCAST_PATTERN = "broadcast";

    @Override
    public void throwException(Rule rule) {
        throw new NoRouterException(rule);
    }
}
