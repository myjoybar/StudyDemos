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
import com.joybar.appcommponentlib.router3.RouterManager3;
import com.joybar.appcommponentlib.router3.Rule3;


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
	//	setClickForRouter1(tv);
		setClickForRouter3(tv);

	}



	private void  setClickForRouter1(TextView tv){
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent it = RouterManager.getInstance().invokeRouter(MainActivity.this, new Rule("user", RouterActivity.ACTIVITY_PATTERN, "user_main"));
				startActivity(it);
				finish();


			}
		});
	}


	private void  setClickForRouter3(TextView tv){


		Bundle mExtras = getIntent().getExtras();
		int id = mExtras.getInt("id", 0);
		tv.setText(tv.getText().toString()+"\n"+"传过来的ID："+id);

		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				RouterManager3.with(MainActivity.this)
						.buildRule(new Rule3("user","user_main"))
						.go();
				finish();

//				Intent mIntent = new Intent();
//				mIntent.putExtra("change01", "1000");
//				mIntent.putExtra("change02", "2000");
//				// 设置结果，并进行传送
//				MainActivity.this.setResult(3, mIntent);
//				finish();

			}
		});
	}
}
