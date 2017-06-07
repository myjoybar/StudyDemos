package com.example.joybar.myaskunagjia.demo.lifecycle;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.joybar.myaskunagjia.commom.L1;
import com.example.joybar.myaskunagjia.demo.lifecycle.Util.Util;
import com.example.joybar.myaskunagjia.demo.lifecycle.interfaces.Lifecycle;
import com.example.joybar.myaskunagjia.demo.lifecycle.interfaces.LifecycleListener;
import com.example.joybar.myaskunagjia.demo.lifecycle.interfaces.RequestManagerTreeNode;

/**
 * Created by joybar on 2017/6/6.
 */

public class RequestManager implements LifecycleListener {
	private final Context context;
	private final Lifecycle lifecycle;
	private final RequestManagerTreeNode treeNode;

	private final static  String TAG = "RequestManager";

	public RequestManager(Context context, final Lifecycle lifecycle, RequestManagerTreeNode treeNode) {
		this.context = context.getApplicationContext();
		this.lifecycle = lifecycle;
		this.treeNode = treeNode;

		if (Util.isOnBackgroundThread()) {
			new Handler(Looper.getMainLooper()).post(new Runnable() {
				@Override
				public void run() {
					L1.d(TAG,"RequestManager--addListener");
					lifecycle.addListener(RequestManager.this);
				}
			});
		} else {
			L1.d(TAG," out RequestManager--addListener");
			lifecycle.addListener(this);
		}
	}


	@Override
	public void onStart() {
		L1.d(TAG,"RequestManager--onStart");
	}

	@Override
	public void onStop() {
		L1.d(TAG,"RequestManager--onStop");
	}

	@Override
	public void onDestroy() {
		L1.d(TAG,"RequestManager--onDestroy");
	}

	public void onLowMemory() {
		//glide.clearMemory();
		Log.d(TAG,"RequestManager--onLowMemory");

	}
}
