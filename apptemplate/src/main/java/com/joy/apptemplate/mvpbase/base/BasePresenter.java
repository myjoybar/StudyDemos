package com.joy.apptemplate.mvpbase.base;

import java.lang.ref.WeakReference;
import java.lang.reflect.Proxy;

/**
 * Created by joybar on 2018/7/25.
 */


public class BasePresenter<V extends BaseView> {

	public static final String TAG = "BasePresenter";

	private WeakReference<V> weakReference;
	private V mProxyView;
	ViewProxyHandler<V> vViewProxyHandler;


	public void bindView(V view) {
		vViewProxyHandler = new ViewProxyHandler<V>(view);
		mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), vViewProxyHandler);
	}

	public void unbindView() {
		vViewProxyHandler.unbind();
	}

	public V getView() {
		return mProxyView;
	}


}