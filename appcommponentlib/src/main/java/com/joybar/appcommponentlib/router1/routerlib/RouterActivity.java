package com.joybar.appcommponentlib.router1.routerlib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.joybar.appcommponentlib.router1.exception.ActivityNotRouteException;

/**
 * Created by joybar on 04/11/2017.
 */

public class RouterActivity<T extends Activity> extends RouterBase<T> {

    public static final String ACTIVITY_SCHEME = "activity://";

    @Override
    public void throwException(String scheme) {
        throw new ActivityNotRouteException(scheme);
    }
}
