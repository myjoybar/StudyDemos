package com.joy.apptemplate.mvpbase.base;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by joybar on 2018/7/26.
 */

public class ViewProxyHandler<V> implements InvocationHandler {
	public static final String TAG = BasePresenter.TAG;
	private BaseView baseView;
	private WeakReference<V> weakReference;

	ViewProxyHandler(V mvpView) {
		this.baseView = (BaseView) mvpView;
		weakReference = new WeakReference<>(mvpView);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Log.d(TAG, "111111");
		if (isBindView()) {
			Log.d(TAG, "222222");
			return method.invoke(baseView, args);
		}
		return null;
	}

	public boolean isBindView() {
		return weakReference != null && weakReference.get() != null;
	}

	public void unbind(){
		if (isBindView()) {
			weakReference.clear();
			weakReference = null;
		}
	}
}