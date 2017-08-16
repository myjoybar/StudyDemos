package me.dinhtone.tracker.loaderresource;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import me.dinhtone.tracker.loaderresource.loadPluginAppActivity.ActivityManager;
import me.dinhtone.tracker.loaderresource.loadPluginAppResouse.PluginUtil;
import me.dinhtone.tracker.loaderresource.loadResouse.LoadedResource;
import me.dinhtone.tracker.loaderresource.loadResouse.ResourceManager;
import test.IUserService;

public class MainActivity extends AppCompatActivity {

	//http://www.jianshu.com/p/ba34eb96c45f
	private IUserService mService;

	ImageView imageView;
	ActivityManager activityManager = ActivityManager.getInstance();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ResourceManager.init(this);
		activityManager.init(this);
		imageView = (ImageView) findViewById(R.id.imageView);

	}


	public void load(View v) {
		try {
			mService = PluginUtil.load(this);
			if (mService != null) {
				Toast.makeText(this, "加载插件APP成功", Toast.LENGTH_SHORT).show();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Toast.makeText(this, "加载插件APP失败", Toast.LENGTH_SHORT).show();
	}

	public void login(View v) {
		if (mService != null) {
			Toast.makeText(this, mService.login("August1996", "August1996"), Toast.LENGTH_SHORT).show();
		}
	}

	public void logout(View v) {
		if (mService != null) {
			Toast.makeText(this, mService.logout(), Toast.LENGTH_SHORT).show();
		}
	}


	/**
	 * 加载已安装APK资源
	 *
	 * @param v
	 */
	public void loadInstalledBundle(View v) {
		Drawable drawable = ResourceManager.installed().getDrawable("me.joybar.loaderapp", "ic_xiaodao");
		if (drawable != null) {
			imageView.setImageDrawable(drawable);
		}
	}

	/**
	 * 加载未安装APK资源
	 *
	 * @param v
	 */
	public void loadUninstalledBundle(View v) {
		String PLUGIN_NAME = "UserPlugin.apk";
		String src = this.getFilesDir().getAbsolutePath() + File.separator + PLUGIN_NAME;    // 插件APK存放的位置
		LoadedResource loadResource = ResourceManager.unInstalled().loadResource(src);
		//LoadedResource loadResource = ResourceManager.unInstalled().loadResource("/storage/sdcard0/bundle.apk");
		Drawable drawable = ResourceManager.unInstalled().getDrawable(loadResource.packageName, "ic_yuner");
		if (drawable != null) {
			imageView.setImageDrawable(drawable);
		}
	}


	/**
	 * 加载未安装的APK的Activity
	 *
	 * @param v
	 */
	public void loadUninstalledActivity(View v) {
		String PLUGIN_NAME = "UserPlugin.apk";
		String src = this.getFilesDir().getAbsolutePath() + File.separator + PLUGIN_NAME;    // 插件APK存放的位置

		//ActivityManager.getInstance().startActivity("com.example.plugin.MainActivity", "/storage/sdcard0/plugin.apk");
		ActivityManager.getInstance().startActivity1("me.joybar.loaderapp.PluginMainActivity", src,this);

	}
}
