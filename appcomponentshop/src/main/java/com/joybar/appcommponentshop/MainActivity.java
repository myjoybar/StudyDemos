package com.joybar.appcommponentshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.RegisterRouter;
import com.joybar.appcommponentlib.router1.routerlib.RouterActivity;
import com.joybar.appcommponentlib.router1.routerlib.Rule;
import com.joybar.appcommponentlib.router1.routermanager.RouterManager;


@RegisterRouter(module = "shop", scheme = "shop_main")
public class MainActivity extends AppCompatActivity {
	private static String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(TAG, "this.getPackageName()=" + this.getPackageName());
		Log.d(TAG, "this.getCallingPackage()=" + this.getCallingPackage());
		Log.d(TAG, "this.getLocalClassName()=" + this.getLocalClassName());
		Log.d(TAG, "this.getPackageCodePath()=" + this.getPackageCodePath());


		TextView tv = new TextView(this);
		tv.setTextSize(50);
		tv.setText("this is  Shop!!!, go to user");
		setContentView(tv);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent it = RouterManager.getInstance().invokeRouter(MainActivity.this, new Rule("user", RouterActivity.ACTIVITY_PATTERN, "user_main"));
				startActivity(it);
				finish();

			}
		});
	}
}
