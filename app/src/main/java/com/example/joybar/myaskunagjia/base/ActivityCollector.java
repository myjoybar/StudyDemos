package com.example.joybar.myaskunagjia.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * 
 * TODO<ActivityCollector>
 * 
 * @author Wen
 * @data: 2015-9-2 上午9:52:35
 * @version: V1.0
 */
public class ActivityCollector {

	public static List<Activity> activities = new ArrayList<Activity>();

	public static ActivityCollector activityCollector;

	public static ActivityCollector getInstance() {
		if (activityCollector == null) {
			activityCollector = new ActivityCollector();
		}
		return activityCollector;
	}

	public static void addActivity(Activity activity) {
		activities.add(activity);
	}

	public static void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	public static void finishAll() {
		for (Activity activity : activities) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

}
