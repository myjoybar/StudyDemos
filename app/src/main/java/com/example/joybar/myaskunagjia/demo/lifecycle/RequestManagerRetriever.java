package com.example.joybar.myaskunagjia.demo.lifecycle;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.joybar.myaskunagjia.commom.L1;
import com.example.joybar.myaskunagjia.demo.lifecycle.Util.Util;
import com.example.joybar.myaskunagjia.demo.lifecycle.view.SupportRequestManagerFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joybar on 2017/6/6.
 */

public class RequestManagerRetriever implements Handler.Callback {


	private static final String TAG = "RequestManagerRetriever";
	static final String FRAGMENT_TAG = "com.bumptech.glide.manager";


	private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
	// Visible for testing.
	/** Pending adds for SupportRequestManagerFragments. */
	final Map<FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments =
			new HashMap<FragmentManager, SupportRequestManagerFragment>();


	/** Main thread handler to handle cleaning up pending fragment maps. */
	private final Handler handler;


	private static final RequestManagerRetriever INSTANCE = new RequestManagerRetriever();

	public static RequestManagerRetriever get() {
		return INSTANCE;
	}

	// Visible for testing.
	RequestManagerRetriever() {
		handler = new Handler(Looper.getMainLooper(), this /* Callback */);
	}




	public RequestManager get(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("You cannot start a load on a null Context");
		} else if (Util.isOnMainThread() && !(context instanceof Application)) {
			if (context instanceof FragmentActivity) {
				return get((FragmentActivity) context);
			} else if (context instanceof Activity) {
				return get((Activity) context);
			} else if (context instanceof ContextWrapper) {
				return get(((ContextWrapper) context).getBaseContext());
			}
		}

		//return getApplicationManager(context);
		return null;
	}



	public RequestManager get(FragmentActivity activity) {
		if (Util.isOnBackgroundThread()) {
			return get(activity.getApplicationContext());
		} else {
			assertNotDestroyed(activity);
			FragmentManager fm = activity.getSupportFragmentManager();
			return supportFragmentGet(activity, fm);
		}
	}

	public RequestManager get(Fragment fragment) {
		if (fragment.getActivity() == null) {
			throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
		}
		if (Util.isOnBackgroundThread()) {
			return get(fragment.getActivity().getApplicationContext());
		} else {
			FragmentManager fm = fragment.getChildFragmentManager();
			return supportFragmentGet(fragment.getActivity(), fm);
		}
	}






	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private static void assertNotDestroyed(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
			throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
		}
	}



	RequestManager supportFragmentGet(Context context, FragmentManager fm) {
		L1.i(TAG, "supportFragmentGet");
		SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm);
		RequestManager requestManager = current.getRequestManager();
		if (requestManager == null) {
			L1.i(TAG, "requestManager == null");
			requestManager = new RequestManager(context, current.getLifecycle(), current.getRequestManagerTreeNode());
			current.setRequestManager(requestManager);
		}
		return requestManager;
	}



	public SupportRequestManagerFragment getSupportRequestManagerFragment(final FragmentManager fm) {
		SupportRequestManagerFragment current = (SupportRequestManagerFragment) fm.findFragmentByTag(


				FRAGMENT_TAG);
		if (current == null) {
			L1.i(TAG, "SupportRequestManagerFragment");
			current = pendingSupportRequestManagerFragments.get(fm);
			if (current == null) {
				current = new SupportRequestManagerFragment();
				pendingSupportRequestManagerFragments.put(fm, current);
				fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
				handler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
			}
		}
		return current;
	}

	@Override
	public boolean handleMessage(Message message) {
		boolean handled = true;
		Object removed = null;
		Object key = null;
		switch (message.what) {
			case ID_REMOVE_SUPPORT_FRAGMENT_MANAGER:
				FragmentManager supportFm = (FragmentManager) message.obj;
				key = supportFm;
				removed = pendingSupportRequestManagerFragments.remove(supportFm);
				break;
			default:
				handled = false;
		}
		if (handled && removed == null && Log.isLoggable(TAG, Log.WARN)) {
			Log.w(TAG, "Failed to remove expected request manager fragment, manager: " + key);
		}
		return handled;
	}
}
