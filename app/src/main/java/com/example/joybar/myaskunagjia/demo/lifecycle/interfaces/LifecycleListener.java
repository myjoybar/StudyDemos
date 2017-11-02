package com.example.joybar.myaskunagjia.demo.lifecycle.interfaces;

/**
 * Created by joybar on 2017/6/5.
 */

public interface LifecycleListener {

	/**
	 * Callback for when {@link android.app.Fragment#onStart()}} or {@link android.app.Activity#onStart()} is called.
	 */
	void onStart();

	/**
	 * Callback for when {@link android.app.Fragment#onStop()}} or {@link android.app.Activity#onStop()}} is called.
	 */
	void onStop();

	/**
	 * Callback for when {@link android.app.Fragment#onDestroy()}} or {@link android.app.Activity#onDestroy()} is
	 * called.
	 */
	void onDestroy();
}