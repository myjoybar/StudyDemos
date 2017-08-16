package me.joybar.hostpluginapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import me.joybar.hostpluginapp.pluginutil.PluginUtil;
import test.IUserService;

public class MainActivity extends AppCompatActivity {

	private IUserService mService;
    //http://www.jianshu.com/p/125d82b9849d
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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


}
