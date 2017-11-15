package com.joybar.appcommponentlib.router3;

import android.content.Context;
import android.support.annotation.Nullable;

import com.joybar.appcommponentlib.router3.utils.CheckUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joybar on 2017/11/13.
 */

public class RouterManager3 {
	public static Map<Rule3, Rule3> ruleMap = new HashMap<>();

	public static void registerRouter(Rule3 rule) {
		ruleMap.put(rule,rule);
	}

	public static IRouterManagerService with(@Nullable Context context) {
		CheckUtils.checkNotNull(context);
		RouterService routerService = new RouterService(context);
		return routerService;
	}

}
