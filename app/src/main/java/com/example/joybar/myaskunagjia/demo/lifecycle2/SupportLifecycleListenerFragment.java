package com.example.joybar.myaskunagjia.demo.lifecycle2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by joybar on 2017/6/6.
 */

public class SupportLifecycleListenerFragment extends Fragment {

	private static  String TAG = "SupportLifecycleListenerFragment";
	private ActivityFragmentLifecycle activityFragmentLifecycle;


	public SupportLifecycleListenerFragment() {
		this(new ActivityFragmentLifecycle());
	}

	// For testing only.
	@SuppressLint("ValidFragment")
	public SupportLifecycleListenerFragment(ActivityFragmentLifecycle lifecycle) {
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
