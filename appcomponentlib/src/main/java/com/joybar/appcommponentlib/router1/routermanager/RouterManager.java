package com.joybar.appcommponentlib.router1.routermanager;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.joybar.appcommponentlib.router1.routerlib.RouterActivity;
import com.joybar.appcommponentlib.router1.routerlib.RouterBroadcast;
import com.joybar.appcommponentlib.router1.routerlib.RouterService;
import com.joybar.appcommponentlib.router1.routerlib.Rule;

/**
 * Created by joybar on 04/11/2017.
 */

public class RouterManager {


    RouterActivity routerActivity;
    RouterBroadcast routerBroadcast;
    RouterService routerService;


    public RouterManager() {
        routerActivity = new RouterActivity();
        routerBroadcast = new RouterBroadcast();
        routerService = new RouterService();

    }

    private static class RouterManagerHolder {
        public static RouterManager INSTANCE = new RouterManager();
    }

    public static RouterManager getInstance() {
        return RouterManagerHolder.INSTANCE;
    }


    public void addActivityRouter(String scheme, Class<? extends Activity> activityClass) {
        routerActivity.addRouter(new Rule(RouterActivity.ACTIVITY_SCHEME, scheme, activityClass));
    }

    public void addServiceRouter(String scheme, Class<? extends Service> activityClass) {
        routerService.addRouter(new Rule(RouterService.SERVICE_SCHEME, scheme, activityClass));
    }

    public void addBroadcastRouter(String scheme, Class<? extends BroadcastReceiver> broadClass) {
        routerBroadcast.addRouter(new Rule(RouterBroadcast.BROADCAST_SCHEME, scheme, broadClass));
    }

    public Intent invokeRouter(Context context, String pattern, String scheme) {

        if (RouterActivity.ACTIVITY_SCHEME.equals(pattern)) {
            return routerActivity.invokeRouter(context, pattern, scheme);
        } else if (RouterActivity.ACTIVITY_SCHEME.equals(pattern)) {
            return routerActivity.invokeRouter(context, pattern, scheme);
        } else if (RouterActivity.ACTIVITY_SCHEME.equals(pattern)) {
            return routerActivity.invokeRouter(context, pattern, scheme);
        } else {
            throw new NullPointerException(String.format("%s cannot be resolved, have you declared it in your Router?", pattern));
        }

    }

}
