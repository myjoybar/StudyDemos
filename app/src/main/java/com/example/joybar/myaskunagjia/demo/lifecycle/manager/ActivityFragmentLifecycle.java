package com.example.joybar.myaskunagjia.demo.lifecycle.manager;

import com.example.joybar.myaskunagjia.commom.L1;
import com.example.joybar.myaskunagjia.demo.lifecycle.Util.Util;
import com.example.joybar.myaskunagjia.demo.lifecycle.interfaces.Lifecycle;
import com.example.joybar.myaskunagjia.demo.lifecycle.interfaces.LifecycleListener;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Created by joybar on 2017/6/5.
 */

public class ActivityFragmentLifecycle implements Lifecycle {

	private final static   String TAG = "ActivityFragment";

	private final Set<LifecycleListener> lifecycleListeners =
			Collections.newSetFromMap(new WeakHashMap<LifecycleListener, Boolean>());
	private boolean isStarted;
	private boolean isDestroyed;

	public void addListener(LifecycleListener listener) {

		L1.d(TAG,"ActivityFragmentLifecycle--addListener");
		lifecycleListeners.add(listener);

		if (isDestroyed) {
			L1.d(TAG,"ActivityFragmentLifecycle--isDestroyed");
			listener.onDestroy();
		} else if (isStarted) {
			L1.d(TAG,"ActivityFragmentLifecycle--isStarted");
			listener.onStart();
		} else {
			L1.d(TAG,"ActivityFragmentLifecycle--isStop");
			listener.onStop();
		}
	}
	public void onStart() {
		L1.d(TAG,"ActivityFragmentLifecycle--onStart");
		isStarted = true;
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onStart();
		}

	}

	public void onStop() {
		L1.d(TAG,"ActivityFragmentLifecycle--onStop");
		isStarted = false;
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onStop();
		}
	}

	public void onDestroy() {
		L1.d(TAG,"ActivityFragmentLifecycle--onDestroy");
		isDestroyed = true;
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onDestroy();
		}

	}
}
