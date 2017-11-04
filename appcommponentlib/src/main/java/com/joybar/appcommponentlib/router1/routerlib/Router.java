package com.joybar.appcommponentlib.router1.routerlib;

import android.content.Context;

import static android.R.attr.scheme;

/**
 * Created by joybar on 04/11/2017.
 */

public interface Router<T,V> {

    void addRouter(Rule rule);

    V invokeRouter(Context ctx, String pattern,String scheme);

}
