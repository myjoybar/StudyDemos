package me.joybar.appcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joybar.appcommponentlib.router1.routerlib.RouterActivity;
import com.joybar.appcommponentlib.router1.routerlib.Rule;
import com.joybar.appcommponentlib.router1.routerlib.config.RouterConfig;
import com.joybar.appcommponentlib.router1.routermanager.RouterManager;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initRouter();

		findViewById(R.id.btn_main_user).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent it =  RouterManager.getInstance().invokeRouter(MainActivity.this, RouterActivity.ACTIVITY_SCHEME,"user.main");
				startActivity(it);
				finish();
			}
		});
	}

	private void initRouter() {
		RouterManager.getInstance().addActivityRouter("user.main", com.joybar.appcommponentuser.MainActivity.class);
		RouterManager.getInstance().addActivityRouter("shop.main", com.joybar.appcommponentshop.MainActivity.class);
	}
}
