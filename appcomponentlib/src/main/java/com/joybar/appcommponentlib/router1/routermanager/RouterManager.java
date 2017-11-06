package com.joybar.appcommponentlib.router1.routermanager;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.joybar.appcommponentlib.router1.exception.NoRouterException;
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


	public void addActivityRouter(String module, String scheme, Class<? extends Activity>
			activityClass) {
		routerActivity.addRouter(new Rule(module, RouterActivity.ACTIVITY_PATTERN, scheme),
				activityClass);
	}

	public void addServiceRouter(String module, String scheme, Class<? extends Service>
			serviceClass) {

		routerService.addRouter(new Rule(module, RouterService.SERVICE_PATTERN, scheme),
				serviceClass);

	}

	public void addBroadcastRouter(String module, String scheme, Class<? extends
			BroadcastReceiver> broadClass) {
		routerBroadcast.addRouter(new Rule(module, RouterBroadcast.BROADCAST_PATTERN, scheme),
				broadClass);
	}

	public Intent invokeRouter(Context context, Rule rule) {

		if (RouterActivity.ACTIVITY_PATTERN.equals(rule.getPattern())) {
			return routerActivity.invokeRouter(context, rule);
		} else if (RouterActivity.ACTIVITY_PATTERN.equals(rule.getPattern())) {
			return routerActivity.invokeRouter(context, rule);
		} else if (RouterActivity.ACTIVITY_PATTERN.equals(rule.getPattern())) {
			return routerActivity.invokeRouter(context, rule);
		} else {
			throw new NoRouterException(rule);
		}

	}

}
