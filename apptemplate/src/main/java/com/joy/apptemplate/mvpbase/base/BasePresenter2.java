package com.joy.apptemplate.mvpbase.base;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by joybar on 2018/7/25.
 */


public class BasePresenter2<V extends BaseView> {

	public static final String TAG = "BasePresenter";

	private WeakReference<V> weakReference;
	private V mProxyView;


	public void bindView(V view) {
		weakReference = new WeakReference<>(view);
		MvpViewHandler viewHandler = new MvpViewHandler(view);
		mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), viewHandler);
	}

	public boolean isBindView() {
		return weakReference != null && weakReference.get() != null;
	}

	public void unbindView() {
		if (isBindView()) {
			weakReference.clear();
			weakReference = null;
		}
	}

	public V getView() {
		return mProxyView;
	}

	private class MvpViewHandler implements InvocationHandler {
		private BaseView baseView;

		MvpViewHandler(BaseView mvpView) {
			this.baseView = mvpView;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Log.d(TAG,"111111");
			if (isBindView()) {
				Log.d(TAG,"222222");
				return method.invoke(baseView, args);
			}
			return null;
		}
	}


}