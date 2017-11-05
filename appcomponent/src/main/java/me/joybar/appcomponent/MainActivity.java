package me.joybar.appcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.RouterHelper;
import com.joybar.appcommponentlib.router1.routerlib.RouterActivity;
import com.joybar.appcommponentlib.router1.routermanager.RouterManager;

import me.joybar.appcomponent.InjectComponentUtil.InjectComponentUtil;

public class MainActivity extends AppCompatActivity {

	private static String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initRouter();

		findViewById(R.id.btn_main_user).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent it =  RouterManager.getInstance().invokeRouter(MainActivity.this, RouterActivity.ACTIVITY_SCHEME,"user_main");
				startActivity(it);
				finish();
			}
		});
	}

	private void initRouter() {


		InjectComponentUtil.inject("com.joybar.appcommponentuser.MainActivity");
		InjectComponentUtil.inject("com.joybar.appcommponentshop.MainActivity");


		//RouterManager.getInstance().addActivityRouter("user_main", com.joybar.appcommponentuser.MainActivity.class);
		//RouterManager.getInstance().addActivityRouter("shop_main", com.joybar.appcommponentshop.MainActivity.class);



	}
}
