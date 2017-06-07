package com.example.joybar.myaskunagjia.demo.lifecycle2.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.joybar.myaskunagjia.demo.lifecycle2.ActivityFragmentLifecycle;
import com.example.joybar.myaskunagjia.demo.lifecycle2.LifecycleListenerFragment;
import com.example.joybar.myaskunagjia.demo.lifecycle2.SupportLifecycleListenerFragment;
import com.example.joybar.myaskunagjia.demo.lifecycle2.interfaces.LifecycleListener;
import com.example.joybar.myaskunagjia.demo.lifecycle2.util.Util;

/**
 * Created by joybar on 2017/6/6.
 */

public class ActivityFragmentLifecycleManager {

	private static final String FRAGMENT_TAG = "ActivityFragmentLifecycle";
	private static final String TAG = "ActivityFragment";

	private static volatile ActivityFragmentLifecycleManager mInstance;

	private ActivityFragmentLifecycleManager() {

	}

	public static ActivityFragmentLifecycleManager getInstance() {
		if (null == mInstance) {
			synchronized (ActivityFragmentLifecycleManager.class) {
				if (null == mInstance) {
					mInstance = new ActivityFragmentLifecycleManager();
				}
			}
		}
		return mInstance;
	}

	public void ObserveLifecycle(Context context, LifecycleListener lifecycleListener) {

		if (context == null) {
			throw new IllegalArgumentException("You cannot start a load on a null Context");
		} else if (Util.isOnMainThread() && !(context instanceof Application)) {
			if (context instanceof FragmentActivity) {
				handle((FragmentActivity) context, lifecycleListener);
			} else if (context instanceof Activity) {
				handle((Activity) context, lifecycleListener);
			} else if (context instanceof ContextWrapper) {
				handle(((ContextWrapper) context).getBaseContext(), lifecycleListener);
			}
		}


	}


	public void ObserveLifecycle(Fragment fragment, LifecycleListener lifecycleListener) {
		if (fragment.getActivity() == null) {
			throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
		}
		FragmentManager fm = fragment.getChildFragmentManager();
		SupportLifecycleListenerFragment supportLifecycleListenerFragment = getSupportRequestManagerFragment(fm);
		ActivityFragmentLifecycle activityFragmentLifecycle = getActivitySupportFragmentLifecycle(supportLifecycleListenerFragment);//找到指定Fragment的ActLifeListenerManager
		activityFragmentLifecycle.addListener(lifecycleListener);

	}


	public void handle(FragmentActivity activity, LifecycleListener lifecycleListener) {
		Log.d(TAG, "this context type  is FragmentActivity");
		assertNotDestroyed(activity);
		FragmentManager fm = activity.getSupportFragmentManager();
		SupportLifecycleListenerFragment fragment = getSupportRequestManagerFragment(fm);
		ActivityFragmentLifecycle activityFragmentLifecycle = getActivitySupportFragmentLifecycle(fragment);//找到指定Fragment的ActLifeListenerManager
		activityFragmentLifecycle.addListener(lifecycleListener);
	}

	public void handle(Activity activity, LifecycleListener lifecycleListener) {
		Log.d(TAG, "this context type  is Activity");
		assertNotDestroyed(activity);
		android.app.FragmentManager fm = activity.getFragmentManager();
		LifecycleListenerFragment fragment = getSupportRequestManagerFragment(fm);
		ActivityFragmentLifecycle activityFragmentLifecycle = getActivityFragmentLifecycle(fragment);//找到指定Fragment的ActLifeListenerManager
		activityFragmentLifecycle.addListener(lifecycleListener);
	}

	public void handle(Context context, LifecycleListener lifecycleListener) {
		Log.d(TAG, "this context type is Context");
	}


	private LifecycleListenerFragment getSupportRequestManagerFragment(final android.app.FragmentManager fm) {
		LifecycleListenerFragment current = (LifecycleListenerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
		if (current == null) {
			current = new LifecycleListenerFragment();
			fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
		}
		return current;
	}


	private SupportLifecycleListenerFragment getSupportRequestManagerFragment(FragmentManager fm) {
		SupportLifecycleListenerFragment current = (SupportLifecycleListenerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
		if (current == null) {
			current = new SupportLifecycleListenerFragment();
			fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
		}
		return current;
	}

	private ActivityFragmentLifecycle getActivitySupportFragmentLifecycle(SupportLifecycleListenerFragment fragment) {
		ActivityFragmentLifecycle lifecycleListener = fragment.getLifecycle();
		if (null == lifecycleListener) {
			lifecycleListener = new ActivityFragmentLifecycle();
		}
		fragment.setLifecycle(lifecycleListener);
		return lifecycleListener;
	}


	private ActivityFragmentLifecycle getActivityFragmentLifecycle(LifecycleListenerFragment fragment) {
		ActivityFragmentLifecycle lifecycleListener = fragment.getLifecycle();
		if (null == lifecycleListener) {
			lifecycleListener = new ActivityFragmentLifecycle();
		}
		fragment.setLifecycle(lifecycleListener);
		return lifecycleListener;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private static void assertNotDestroyed(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
			throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
		}
	}


}
