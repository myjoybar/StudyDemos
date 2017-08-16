package me.dinhtone.tracker.loaderresource.loadPluginAppActivity;

import android.content.pm.PackageInfo;
import android.content.res.Resources;

/**
 * Created by joybar on 2017/8/16.
 */

public class PluginApk {

		public PackageInfo packageInfo;
		public Resources resources;
		public ClassLoader classLoader;

		public PluginApk(Resources resources) {
			this.resources = resources;
		}
}
