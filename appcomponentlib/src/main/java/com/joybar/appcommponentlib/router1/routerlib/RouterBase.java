package com.joybar.appcommponentlib.router1.routerlib;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joybar on 04/11/2017.
 */

public abstract class RouterBase<T extends Class> implements Router<T, Intent> {

	private Map<Rule, T> ruleMap;

	@Override
	public void addRouter(Rule rule, T t) {
		if (ruleMap == null) {
			ruleMap = new HashMap(10);
		}
		if (ruleMap.get(rule) == null) {
			ruleMap.put(rule, t);
		}
	}

	@Override
	public Intent invokeRouter(Context context, Rule rule) {
		Class<T> klass = ruleMap.get(rule);
		if (klass == null) {
			throwException(rule);
		}
		return new Intent(context, klass);
	}

	public abstract void throwException(Rule rule);

}
