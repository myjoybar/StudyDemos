package com.example.joybar.myaskunagjia.demo.lifecycle2;

import android.os.Bundle;

import com.example.joybar.myaskunagjia.demo.lifecycle2.interfaces.Lifecycle;
import com.example.joybar.myaskunagjia.demo.lifecycle2.interfaces.LifecycleListener;
import com.example.joybar.myaskunagjia.demo.lifecycle2.util.Util;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Created by joybar on 2017/6/6.
 */

public class ActivityFragmentLifecycle implements Lifecycle {

	private final Set<LifecycleListener> lifecycleListeners = Collections.newSetFromMap(new WeakHashMap<LifecycleListener, Boolean>());

	@Override
	public void addListener(LifecycleListener listener) {
		lifecycleListeners.add(listener);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onCreate(savedInstanceState);
		}
	}

	public void onStart() {
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onStart();
		}

	}

	public void onResume() {
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onResume();
		}
	}

	public void onPause() {
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onPause();
		}
	}


	public void onStop() {
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onStop();
		}
	}


	public void onDestroy() {
		for (LifecycleListener lifecycleListener : Util.getSnapshot(lifecycleListeners)) {
			lifecycleListener.onDestroy();
		}

	}


	private void recycleListener(LifecycleListener listener) {
		lifecycleListeners.remove(listener);
		listener = null;
	}


}
