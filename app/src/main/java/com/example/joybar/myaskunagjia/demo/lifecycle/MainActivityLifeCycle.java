package com.example.joybar.myaskunagjia.demo.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joybar.myaskunagjia.R;
import com.example.joybar.myaskunagjia.commom.L;
import com.example.joybar.myaskunagjia.demo.lifecycle2.interfaces.LifecycleListener;
import com.example.joybar.myaskunagjia.demo.lifecycle2.manager.ActivityFragmentLifecycleManager;

/**
 * Created by joybar on 15/11/4.
 */
public class MainActivityLifeCycle extends Activity {

	private static final String TAG = "MainActivityLifeCycle";

	// 静态变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	// public static final int CLICK_ADDRESS_ADD = 0;
	// 成员变量部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	// 成员变量View部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	private Button btn1, btn2, btn3, btn4, btn5;
	private TextView tv1, tv2;
	// 成员变量adapter部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	// 成员变量List部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	// 成员变量Bean部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


	//生命周期部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acopy_main);
		initView();
		initData();
	}

	@Override
	protected void onStart() {
		L.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		L.i(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		L.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		L.i(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		L.i(TAG, "onStop");
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		L.i(TAG, "onDestroy");
		super.onDestroy();
	}

	//初始化部分————————————————————————————————————————————————————————————————————————————————————————————————————————————————


	protected void initView() {
		btn1 = (Button) findViewById(R.id.btn1);
//        btn2 = fvById(R.id.btn2);
//        btn3 = fvById(R.id.btn3);
//        btn4 = fvById(R.id.btn4);
//        tv1 = fvById(R.id.tv1);
//        tv2 = fvById(R.id.tv2);

		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			//	Glide.with(MainActivityLifeCycle.this);
			//
				//
				//
				// 	Glide.with(MainActivityLifeCycle.this);

				ActivityFragmentLifecycleManager.getInstance().ObserveLifecycle(MainActivityLifeCycle.this, new LifecycleListener() {
					@Override
					public void onCreate(Bundle bundle) {
						Log.d("MainActivity","onCreate");
					}

					@Override
					public void onStart() {
						Log.d("MainActivity","onStart");
					}

					@Override
					public void onResume() {
						Log.d("MainActivity","onResume");
					}

					@Override
					public void onPause() {
						Log.d("MainActivity","onPause");
					}

					@Override
					public void onStop() {
						Log.d("MainActivity","onStop");
					}

					@Override
					public void onDestroy() {
						Log.d("MainActivity","onDestroy");
					}
				});
			}
		});

	}


	protected void initData() {

	}







}

