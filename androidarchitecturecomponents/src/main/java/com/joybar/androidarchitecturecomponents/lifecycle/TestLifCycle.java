package com.joybar.androidarchitecturecomponents.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Created by joybar on 2018/1/22.
 */

public class TestLifCycle {

	public class MyObserver implements LifecycleObserver {

		@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
		public void onResume() {
		}

		@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
		public void onPause() {
		}
	}

}
