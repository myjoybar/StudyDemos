package com.joybar.appcommponentlib.router3;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by joybar on 12/11/2017.
 */

public class RouterRequest {

	private Context context;
	private Rule3 rule;
	private InterceptorCallback interceptorCallback;
	private Bundle bundle;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Rule3 getRule() {
		return rule;
	}

	public void setRule(Rule3 rule) {
		this.rule = rule;
	}

	public InterceptorCallback getInterceptorCallback() {
		return interceptorCallback;
	}

	public void setInterceptorCallback(InterceptorCallback interceptorCallback) {
		this.interceptorCallback = interceptorCallback;
	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}


}
