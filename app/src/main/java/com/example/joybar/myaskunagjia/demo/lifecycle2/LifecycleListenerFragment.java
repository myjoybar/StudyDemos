package com.example.joybar.myaskunagjia.demo.lifecycle2;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;


/**
 * Created by joybar on 2017/6/6.
 */

public class LifecycleListenerFragment extends Fragment {

	private static  String TAG = "SupportLifecycleListenerFragment";
	private ActivityFragmentLifecycle activityFragmentLifecycle;


	public LifecycleListenerFragment() {
		this(new ActivityFragmentLifecycle());
	}

	// For testing only.
	@SuppressLint("ValidFragment")
	public LifecycleListenerFragment(ActivityFragmentLifecycle lifecycle) {
		this.activityFragmentLifecycle = lifecycle;
	}



	public ActivityFragmentLifecycle getLifecycle() {
		return activityFragmentLifecycle;
	}


	public void setLifecycle(ActivityFragmentLifecycle lifecycle){
		this.activityFragmentLifecycle = lifecycle;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
		activityFragmentLifecycle.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
		activityFragmentLifecycle.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		activityFragmentLifecycle.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
		activityFragmentLifecycle.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		activityFragmentLifecycle.onDestroy();
	}



	@Override
	public void onLowMemory() {
		super.onLowMemory();

	}


}
